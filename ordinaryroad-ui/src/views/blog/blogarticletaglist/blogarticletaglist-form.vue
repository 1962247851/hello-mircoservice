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
    <el-form-item label="文章UUID" prop="articleUuid">
        <el-input v-model="dataForm.articleUuid" placeholder="文章UUID"></el-input>
    </el-form-item>
    <el-form-item label="标签UUID" prop="tagUuid">
        <el-input v-model="dataForm.tagUuid" placeholder="标签UUID"></el-input>
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
    import {getObj, addObj, putObj} from '@/api/blogarticletaglist'

    export default {
    data () {
      return {
        visible: false,
        canSubmit: false,
        dataForm: {
          id: 0,
          articleUuid: '',
          tagUuid: '',
          createTime: '',
          createBy: '',
          updateTime: '',
          updateBy: '',
          delFlag: ''
        },
        dataRule: {
          articleUuid: [
            { required: true, message: '文章UUID不能为空', trigger: 'blur' }
          ],
          tagUuid: [
            { required: true, message: '标签UUID不能为空', trigger: 'blur' }
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
