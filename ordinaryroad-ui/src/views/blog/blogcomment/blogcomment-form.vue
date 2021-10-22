<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    @close="closeDialog()"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="ID" prop="id" v-if="dataForm.id">
        <el-input v-model="dataForm.id" placeholder="ID" disabled></el-input>
    </el-form-item>
    <el-form-item label="UUID" prop="uuid">
        <el-input v-model="dataForm.uuid" placeholder="UUID"></el-input>
    </el-form-item>
    <el-form-item label="评论内容" prop="content">
        <el-input v-model="dataForm.content" placeholder="评论内容"></el-input>
    </el-form-item>
    <el-form-item label="评论排序" prop="sort">
        <el-input v-model="dataForm.sort" placeholder="评论排序"></el-input>
    </el-form-item>
    <el-form-item label="是否置顶" prop="topped">
        <el-input v-model="dataForm.topped" placeholder="是否置顶"></el-input>
    </el-form-item>
    <el-form-item label="主评论UUID" prop="originalUuid">
        <el-input v-model="dataForm.originalUuid" placeholder="主评论UUID"></el-input>
    </el-form-item>
    <el-form-item label="被回复评论UUID" prop="parentUuid">
        <el-input v-model="dataForm.parentUuid" placeholder="被回复评论UUID"></el-input>
    </el-form-item>
    <el-form-item label="用户UUID" prop="userUuid">
        <el-input v-model="dataForm.userUuid" placeholder="用户UUID"></el-input>
    </el-form-item>
    <el-form-item label="文章UUID" prop="articleUuid">
        <el-input v-model="dataForm.articleUuid" placeholder="文章UUID"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime" v-if="dataForm.id">
        <el-input v-model="dataForm.createTime" placeholder="创建时间" disabled></el-input>
    </el-form-item>
    <el-form-item label="创建者" prop="createBy" v-if="dataForm.id">
        <el-input v-model="dataForm.createBy" placeholder="创建者" disabled></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updateTime" v-if="dataForm.id">
        <el-input v-model="dataForm.updateTime" placeholder="更新时间" disabled></el-input>
    </el-form-item>
    <el-form-item label="更新者" prop="updateBy" v-if="dataForm.id">
        <el-input v-model="dataForm.updateBy" placeholder="更新者" disabled></el-input>
    </el-form-item>
    <el-form-item label="是否删除" prop="delFlag">
        <el-input v-model="dataForm.delFlag" placeholder="是否删除"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()" v-if="canSubmit">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
    import {getObj, addObj, putObj} from '@/api/blogcomment'

    export default {
    data () {
      return {
        visible: false,
        canSubmit: false,
        dataForm: {
          id: 0,
          uuid: '',
          content: '',
          sort: '',
          topped: '',
          originalUuid: '',
          parentUuid: '',
          userUuid: '',
          articleUuid: '',
          createTime: '',
          createBy: '',
          updateTime: '',
          updateBy: '',
          delFlag: ''
        },
        dataRule: {
          uuid: [
            { required: true, message: 'UUID不能为空', trigger: 'blur' }
          ],
          content: [
            { required: true, message: '评论内容不能为空', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '评论排序不能为空', trigger: 'blur' }
          ],
          topped: [
            { required: true, message: '是否置顶不能为空', trigger: 'blur' }
          ],
          originalUuid: [
            { required: true, message: '主评论UUID不能为空', trigger: 'blur' }
          ],
          parentUuid: [
            { required: true, message: '被回复评论UUID不能为空', trigger: 'blur' }
          ],
          userUuid: [
            { required: true, message: '用户UUID不能为空', trigger: 'blur' }
          ],
          articleUuid: [
            { required: true, message: '文章UUID不能为空', trigger: 'blur' }
          ],
          delFlag: [
            { required: true, message: '是否删除不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.visible = true;
        this.canSubmit = true;
        this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
            if (id) {
            getObj(id).then(response => {
                this.dataForm = response.data.data
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.canSubmit = false;
            if (this.dataForm.id) {
                putObj(this.dataForm).then(data => {
                    this.$notify.success('修改成功')
                    this.visible = false
                    this.$emit('refreshDataList')
                }).catch(() => {
                    this.canSubmit = true;
                });
            } else {
                addObj(this.dataForm).then(data => {
                    this.$notify.success('添加成功')
                    this.visible = false
                    this.$emit('refreshDataList')
                }).catch(() => {
                    this.canSubmit = true;
                });
            }
          }
        })
      },
      //重置表单
      closeDialog() {
          this.$refs["dataForm"].resetFields()
      }
    }
  }
</script>
