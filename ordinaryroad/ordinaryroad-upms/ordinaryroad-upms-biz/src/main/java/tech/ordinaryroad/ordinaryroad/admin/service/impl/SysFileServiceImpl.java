/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the ordinaryroad4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package tech.ordinaryroad.ordinaryroad.admin.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.amazonaws.services.s3.model.S3Object;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.plugin.oss.OssProperties;
import com.pig4cloud.plugin.oss.service.OssTemplate;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.tasks.UnsupportedFormatException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tech.ordinaryroad.ordinaryroad.admin.api.entity.SysFile;
import tech.ordinaryroad.ordinaryroad.admin.mapper.SysFileMapper;
import tech.ordinaryroad.ordinaryroad.admin.service.SysFileService;
import tech.ordinaryroad.ordinaryroad.common.core.util.R;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件管理
 *
 * @author Luckly
 * @date 2019-06-18 17:18:42
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements SysFileService {

	private final OssProperties ossProperties;

	private final OssTemplate minioTemplate;

	/**
	 * 上传文件
	 *
	 * @param file
	 * @return
	 */
	@Override
	public R uploadFile(MultipartFile file) {
		String defaultBucketName = ossProperties.getBucketName();
		String fileOriginalFilename = file.getOriginalFilename();

		String fileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(fileOriginalFilename);
		Map<String, String> resultMap = new HashMap<>(4);
		resultMap.put("bucketName", defaultBucketName);
		resultMap.put("fileName", fileName);
		resultMap.put("url", String.format("/admin/sys-file/%s/%s", defaultBucketName, fileName));

		try {
			minioTemplate.putObject(defaultBucketName, fileName, file.getInputStream());
			// 如果是图片，保存缩略图
			saveThumbnailIfImage(fileName, file);
			// 文件管理数据记录,收集管理追踪文件
			fileLog(defaultBucketName, fileOriginalFilename, file.getSize(), fileName);
		} catch (Exception e) {
			log.error("上传失败", e);
			return R.failed(e.getLocalizedMessage());
		}
		return R.ok(resultMap);
	}

	private void saveThumbnailIfImage(String fileName, MultipartFile file) throws IOException {
		String thumbnailBucketName = "thumbnail";
		boolean isPicture = true;
		// 缩略图
		InputStream inputStream = file.getInputStream();
		try {
			File outFile = FileUtil.createTempFile(thumbnailBucketName + "_" + FileUtil.getPrefix(fileName),
					StrUtil.DOT + FileUtil.getSuffix(fileName), FileUtil.getTmpDir(), false);
			Thumbnails.of(inputStream).scale(1.0f).outputQuality(0.5f).toFile(outFile);

			minioTemplate.createBucket(thumbnailBucketName);
			minioTemplate.putObject(thumbnailBucketName, fileName, new FileInputStream(outFile));
			// 文件管理数据记录,收集管理追踪文件
			fileLog(thumbnailBucketName, file.getOriginalFilename(), outFile.length(), fileName);
		} catch (IOException e) {
			// 不是图片不用管，isPicture = false
			if (e instanceof UnsupportedFormatException) {
				isPicture = false;
			} else {
				e.printStackTrace();
			}
			// 图片报错
			if (isPicture) {
				log.error("图片压缩异常: {}", e.getLocalizedMessage());
			}
		}
	}

	/**
	 * 读取文件
	 *
	 * @param bucket
	 * @param fileName
	 * @param response
	 */
	@Override
	public void getFile(String bucket, String fileName, HttpServletResponse response) {
		try (S3Object s3Object = minioTemplate.getObject(bucket, fileName)) {
			response.setContentType("application/octet-stream; charset=UTF-8");
			IoUtil.copy(s3Object.getObjectContent(), response.getOutputStream());
		} catch (Exception e) {
			log.error("文件读取异常: {}", e.getLocalizedMessage());
		}
	}

	/**
	 * 删除文件
	 *
	 * @param id
	 * @return
	 */
	@Override
	@SneakyThrows
	@Transactional(rollbackFor = Exception.class)
	public Boolean deleteFile(Long id) {
		SysFile file = this.getById(id);
		minioTemplate.removeObject(file.getBucketName(), file.getFileName());
		return this.removeById(id);
	}

	/**
	 * 文件管理数据记录,收集管理追踪文件
	 *
	 * @param bucketName 桶名称
	 * @param originalFileName 原始文件名
	 * @param size 文件大小
	 * @param fileName 文件名
	 */
	private void fileLog(String bucketName, String originalFileName, Long size, String fileName) {
		SysFile sysFile = new SysFile();
		sysFile.setFileName(fileName);
		sysFile.setOriginal(originalFileName);
		sysFile.setFileSize(size);
		sysFile.setType(FileUtil.extName(originalFileName));
		sysFile.setBucketName(bucketName);
		this.save(sysFile);
	}

}
