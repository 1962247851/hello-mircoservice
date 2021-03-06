/*
 * Copyright (c) 2020 ordinaryroad4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.ordinaryroad.ordinaryroad.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import tech.ordinaryroad.ordinaryroad.admin.api.entity.SysMenu;
import tech.ordinaryroad.ordinaryroad.admin.api.entity.SysRoleMenu;
import tech.ordinaryroad.ordinaryroad.admin.mapper.SysMenuMapper;
import tech.ordinaryroad.ordinaryroad.admin.mapper.SysRoleMenuMapper;
import tech.ordinaryroad.ordinaryroad.admin.service.SysMenuService;
import tech.ordinaryroad.ordinaryroad.common.core.constant.CacheConstants;
import tech.ordinaryroad.ordinaryroad.common.core.constant.CommonConstants;
import tech.ordinaryroad.ordinaryroad.common.core.constant.enums.MenuTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * ??????????????? ???????????????
 * </p>
 *
 * @author lengleng
 * @since 2017-10-29
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

	private final SysRoleMenuMapper sysRoleMenuMapper;

	@Override
	@Cacheable(value = CacheConstants.MENU_DETAILS, key = "#roleIds  + '_menu'", unless = "#result == null")
	public Set<SysMenu> findMenuByRoleId(String roleIds) {
		return baseMapper.listMenusByRoleId(roleIds);
	}

	/**
	 * ??????????????????
	 * @param id ??????ID
	 * @return true??????, false??????
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
	public Boolean removeMenuById(Integer id) {
		// ???????????????????????????????????????
		List<SysMenu> menuList = this.list(Wrappers.<SysMenu>query().lambda().eq(SysMenu::getParentId, id));

		Assert.isTrue(CollUtil.isEmpty(menuList), "??????????????????????????????");

		sysRoleMenuMapper.delete(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getMenuId, id));
		// ?????????????????????????????????
		return this.removeById(id);
	}

	@Override
	@CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
	public Boolean updateMenuById(SysMenu sysMenu) {
		return this.updateById(sysMenu);
	}

	/**
	 * ??????????????? 1. ???????????????????????????????????? 2. ?????????????????????parentId ?????? 2.1 ???????????????????????????ID -1
	 * @param lazy ??????????????????
	 * @param parentId ?????????ID
	 * @return
	 */
	@Override
	public List<Tree<Integer>> treeMenu(boolean lazy, Integer parentId) {
		if (!lazy) {
			List<TreeNode<Integer>> collect = baseMapper
					.selectList(Wrappers.<SysMenu>lambdaQuery().orderByAsc(SysMenu::getSort)).stream()
					.map(getNodeFunction()).collect(Collectors.toList());

			return TreeUtil.build(collect, CommonConstants.MENU_TREE_ROOT_ID);
		}

		Integer parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;

		List<TreeNode<Integer>> collect = baseMapper
				.selectList(
						Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getParentId, parent).orderByAsc(SysMenu::getSort))
				.stream().map(getNodeFunction()).collect(Collectors.toList());

		return TreeUtil.build(collect, parent);
	}

	/**
	 * ????????????
	 * @param all ????????????
	 * @param parentId ?????????ID
	 * @return
	 */
	@Override
	public List<Tree<Integer>> filterMenu(Set<SysMenu> all, Integer parentId) {
		List<TreeNode<Integer>> collect = all.stream()
				.filter(menu -> MenuTypeEnum.LEFT_MENU.getType().equals(menu.getType()))
				.filter(menu -> StrUtil.isNotBlank(menu.getPath())).map(getNodeFunction()).collect(Collectors.toList());
		Integer parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
		return TreeUtil.build(collect, parent);
	}

	@NotNull
	private Function<SysMenu, TreeNode<Integer>> getNodeFunction() {
		return menu -> {
			TreeNode<Integer> node = new TreeNode<>();
			node.setId(menu.getMenuId());
			node.setName(menu.getName());
			node.setParentId(menu.getParentId());
			node.setWeight(menu.getSort());
			// ????????????
			Map<String, Object> extra = new HashMap<>();
			extra.put("icon", menu.getIcon());
			extra.put("path", menu.getPath());
			extra.put("type", menu.getType());
			extra.put("permission", menu.getPermission());
			extra.put("label", menu.getName());
			extra.put("sort", menu.getSort());
			extra.put("keepAlive", menu.getKeepAlive());
			node.setExtra(extra);
			return node;
		};
	}

}
