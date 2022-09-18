<template>
  <el-card style="max-width: 400px;margin: auto">
    <el-form label-width="80px" size="small">
      <el-upload
          style="text-align: center"
          class="avatar-uploader"
          action="http://localhost:9090/file/upload"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
      >
        <img v-if="form.avatarUrl" :src="form.avatarUrl" class="avatar" style="width:100px;border-radius: 50px">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
      <el-form-item label="用户名">
        <el-input v-model="form.username" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.nickname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.phone" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="form.address" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="save">更 新 信 息</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
export default {
  name: "Person",
  data() {
    return {
      form: {},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    this.getUser()

  },
  methods: {
    async getUser() {
      this.form = (await this.request.get('/user/username/' + this.user.username)).data


    },
    save() {
      this.request.post("/user", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
          /* 更新浏览器存储*/
          this.form.token=JSON.parse(localStorage.getItem("user")).token

          localStorage.setItem("user",JSON.stringify(this.form))
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    handleAvatarSuccess(res) {
      this.form.avatarUrl = res

    }
  }
}
</script>

<style scoped>
/*.avatar-uploader{*/
/*  position: relative;*/
/*}*/
/*.el-upload--text{*/
/*  position: absolute;*/
/*  left: 50%;*/
/*  top: 50%;*/
/*  transform: translateX(-50%) translateY(-50%);*/
/*}*/
/*.avatar-uploader {*/
/*  display: flex;*/
/*  justify-content: center;*/
/*  align-items: center;*/
/*}*/
</style>