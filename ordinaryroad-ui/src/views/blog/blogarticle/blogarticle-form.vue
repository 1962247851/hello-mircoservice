<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    @close="closeDialog()"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
    <el-form-item label="ID" prop="id" v-if="dataForm.id">
        <el-input v-model="dataForm.id" placeholder="ID" disabled></el-input>
    </el-form-item>
    <el-form-item label="文章标题" prop="title">
        <el-input v-model="dataForm.title" placeholder="文章标题"></el-input>
    </el-form-item>
    <el-form-item label="封面地址" prop="imgUrl">
        <el-input v-model="dataForm.imgUrl" placeholder="封面地址"></el-input>
    </el-form-item>
    <el-form-item label="文章摘要" prop="articleAbstract">
        <el-input v-model="dataForm.articleAbstract" placeholder="文章摘要"></el-input>
    </el-form-item>
    <el-form-item label="文章排序" prop="sort">
        <el-input v-model="dataForm.sort" placeholder="文章排序"></el-input>
    </el-form-item>
    <el-form-item label="文章内容" prop="content">
        <el-input v-model="dataForm.content" placeholder="文章内容"></el-input>
    </el-form-item>
    <el-form-item label="是否可以评论" prop="canComment">
        <el-input v-model="dataForm.canComment" placeholder="是否可以评论"></el-input>
    </el-form-item>
    <el-form-item label="是否可以打赏" prop="canReward">
        <el-input v-model="dataForm.canReward" placeholder="是否可以打赏"></el-input>
    </el-form-item>
    <el-form-item label="是否原创" prop="original">
        <el-input v-model="dataForm.original" placeholder="是否原创"></el-input>
    </el-form-item>
    <el-form-item label="是否展示" prop="shown">
        <el-input v-model="dataForm.shown" placeholder="是否展示"></el-input>
    </el-form-item>
    <el-form-item label="文章分类UUID" prop="typeUuid">
        <el-input v-model="dataForm.typeUuid" placeholder="文章分类UUID"></el-input>
    </el-form-item>
    <el-form-item label="文章状态值" prop="status">
        <el-input v-model="dataForm.status" placeholder="文章状态值"></el-input>
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
    import {getObj, addObj, putObj} from '@/api/blogarticle'

    export default {
    data () {
      return {
        visible: false,
        canSubmit: false,
        dataForm: {
          id: 0,
          title: '',
          imgUrl: '',
          articleAbstract: '',
          sort: '',
          content: '',
          canComment: '',
          canReward: '',
          original: '',
          shown: '',
          typeUuid: '',
          status: '',
          createTime: '',
          createBy: '',
          updateTime: '',
          updateBy: '',
          delFlag: ''
        },
        dataRule: {
          title: [
            { required: true, message: '文章标题不能为空', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '文章排序不能为空', trigger: 'blur' }
          ],
          content: [
            { required: true, message: '文章内容不能为空', trigger: 'blur' }
          ],
          canComment: [
            { required: true, message: '是否可以评论不能为空', trigger: 'blur' }
          ],
          canReward: [
            { required: true, message: '是否可以打赏不能为空', trigger: 'blur' }
          ],
          original: [
            { required: true, message: '是否原创不能为空', trigger: 'blur' }
          ],
          shown: [
            { required: true, message: '是否展示不能为空', trigger: 'blur' }
          ],
          typeUuid: [
            { required: true, message: '文章分类UUID不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '文章状态值不能为空', trigger: 'blur' }
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
