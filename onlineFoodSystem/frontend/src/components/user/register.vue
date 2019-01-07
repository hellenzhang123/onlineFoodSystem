<template>
  <div class="register">
    <div class="wrapper">
        <div class="header">注册</div>
        <div class="wrapForm">
            
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm registerTable">
                <el-form-item label="姓名" prop="name" >
                    <el-input v-model="ruleForm.name" placeholder="请输入您的姓名"></el-input>
                </el-form-item>
                <el-form-item label="学号" prop="stuNumber">
                    <el-input v-model="ruleForm.stuNumber" placeholder="请输入您的学号"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="gender">
                    <el-radio-group v-model="ruleForm.gender" placeholder="请选择您的性别" >
                    <el-radio label="先生"></el-radio>
                    <el-radio label="女士"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item  label="密码" prop="password">
                    <el-input type="password" v-model="ruleForm.password" auto-complete="off" placeholder="请输入您的密码"></el-input>
                </el-form-item>
                <el-form-item  label="重复" prop="checkPass">
                    <el-input type="password" v-model="ruleForm.checkPass" auto-complete="off" placeholder="请再次输入您的密码"></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                    <el-input v-model="ruleForm.phone" placeholder="请输入您的电话"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="ruleForm.email" placeholder="请输入您的邮箱"></el-input>
                </el-form-item>
                <el-form-item label="密保问题" prop="question">
                    <el-input v-model="ruleForm.question" placeholder="请输入您的密保问题"></el-input>
                </el-form-item>
                <el-form-item label="密保答案" prop="answer">
                    <el-input v-model="ruleForm.answer" placeholder="请输入您的密保答案"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">立即注册</el-button>
                    <el-button @click="resetForm('ruleForm')">重置表单</el-button>
                </el-form-item>
            </el-form>

        </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
        var validatePass = (rule, value, callback) => {
            if (value === '') {
            callback(new Error('请输入密码'));
            } else {
            if (this.ruleForm.checkPass !== '') {
                this.$refs.ruleForm.validateField('checkPass');
            }
            callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm.password) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
      return {
        ruleForm: {
          name: '',
          stuNumber: '',
          password: '',
          checkPass: '',
          phone: '',
          email: '',
          answer:'',
          question:'',
          address:''
        },
       
        rules: {
          name: [
            { required: true, message: '请输入您的姓名', trigger: 'blur' },
            { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
          ],
          stuNumber: [
            { required: true, message: '请输入您的学号', trigger: 'blur' },
            { min: 10, max: 10, message: '长度应为10字符', trigger: 'blur' }
          ],
          gender: [
            { required: true, message: '请选择您的性别', trigger: 'change' }
          ],
          phone: [
            { required: true, message: '请输入您的电话', trigger: 'blur' },
            { pattern: /^1[34578]\d{9}$/, message: '目前只支持中国大陆的手机号码' }
          ],
          email: [
            { pattern: /^1[34578]\d{9}$/, message: '目前只支持中国大陆的手机号码' }
          ],
          question: [
            { required: true, message: '请输入您的密保问题', trigger: 'blur' },
          ],
          answer: [
            { required: true, message: '请输入您的密保答案', trigger: 'blur' },
          ],
          password: [
            { required: true, message: '请输入您的密码', trigger: 'blur' },
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { required: true, message: '请输入您的确认密码', trigger: 'blur' },
            { validator: validatePass2, trigger: 'blur' }
          ]
        },
      }
    },
    methods:{
        submitForm(formName) {
            console.log(this.ruleForm.name);
            var params = new Object();
            params.name = this.ruleForm.name;
            params.stuNumber = this.ruleForm.stuNumber;
            params.gender = this.ruleForm.gender;
            params.password = this.ruleForm.password;
            params.phone = this.ruleForm.phone;
            params.email = this.ruleForm.email;
            console.log (params);
            this.$refs[formName].validate((valid) => {
            if (valid) {
                this.$http.post('http://localhost:8080/api/user/register', params).then((message) => {
                    this.$router.push({path: '/login'})
                    console.log('success');
                //这里处理成功回调
                })
                .catch((message) => {
                    console.log('error');
                //这里处理失败回调
                });
            } else {
                console.log('error submit!!');
                return false;
            }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
    }

};
</script>


<style lang="stylus" rel="stylesheet/stylus" scoped>
    .register
        background: url("/static/image/info.jpg")
        display: flex;
        height: 40rem;
        justify-content: center;
        align-items: center;
        .wrapper
            width: 450px;
            background: #ffffff;
            display: flex;
            flex-direction: column;
            .header
                display: flex;
                justify-content: center;
                padding: 1rem;
                width: 300px;
                border-bottom: 1px solid #40c4ff;
                margin-left: 60px;
                letter-spacing: 10px;
                color: #40c4ff;
            .wrapForm
                display: flex;
                justify-content: center;
                width: 500px;
                .registerTable
                    padding: 1rem 5rem 1rem 0rem;
                    width: 500px;
</style>