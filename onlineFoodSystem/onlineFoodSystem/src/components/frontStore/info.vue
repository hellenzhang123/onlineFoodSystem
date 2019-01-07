<template>
    <div class="myOrder">
        <div class="menuTop">
            <el-breadcrumb separator=">" class="menuBread">
            <el-breadcrumb-item :to="{ path: '/front/frontStore' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>个人中心</el-breadcrumb-item>
            <el-breadcrumb-item>修改个人资料</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="main">
            <div class="mainLeft">
                <el-menu
                    default-active="1-1"
                    class="el-menu-vertical-demo infoMenu"
                    background-color="#fafafa"
                    text-color="#000"
                    active-text-color="#ff2d48">
                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-document"></i>
                            <span>个人资料</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="1-1"><router-link to="/front/info">修改个人资料</router-link></el-menu-item>
                            <el-menu-item index="1-2"><router-link to="/front/avatar">上传头像</router-link></el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-menu-item index="2">
                        <i class="el-icon-menu"></i>
                        <span slot="title"><router-link to="/front/myOrder">我的订单</router-link></span>
                    </el-menu-item>
                    <el-menu-item index="3">
                        <i class="el-icon-location"></i>
                        <span slot="title"><router-link to="/front/address">我的地址</router-link></span>
                    </el-menu-item>
                </el-menu>
            </div>
            <div class="mainRight">
                <div class="first">修改个人资料</div>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm registerTable">
                <el-form-item label="姓名" prop="name" >
                    <el-input v-model="ruleForm.name" ></el-input>
                </el-form-item>
                <el-form-item label="学号" prop="stuNumber">
                    <el-input v-model="ruleForm.stuNumber"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="gender">
                    <el-radio-group v-model="ruleForm.gender">
                    <el-radio label="先生"></el-radio>
                    <el-radio label="女士"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="ruleForm.password" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPass">
                    <el-input type="password" v-model="ruleForm.checkPass" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                    <el-input v-model="ruleForm.phone"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="ruleForm.email"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">修改</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>

            </div>
        </div>
    </div>
</template>

<script type="text/ecmascript-6">

  export default {
    data(){
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
        return  {
        ruleForm: {
          name: '',
          stuNumber: '',
          password: '',
          checkPass: '',
          phone: '',
          email: '',
          address:''
        },
       
        rules: {
          name: [
            { required: true, message: '请输入您的姓名', trigger: 'blur' },
            { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
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
            password: [
                { validator: validatePass, trigger: 'blur' }
            ],
            checkPass: [
                { validator: validatePass2, trigger: 'blur' }
            ]
            }
        }
    },
   created() {
       this.$http.get('http://localhost:8080/api/user/current').then((response) => {
           console.log(response)
           this.ruleForm = response.body;
        }, (response) => {
            // 响应错误回调
        });
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
                this.$http.put('http://localhost:8080/api/user/' + this.ruleForm.id, params).then((message) => {
                    console.log('success');
                //这里处理成功回调
                })
                .catch((message) => {
                    console.log('error');
                //这里处理失败回调
                });
                alert('submit!');
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
    .myOrder
        width: 1024px
        margin: 0 auto
        .menuTop
            .menuBread
                margin-left: 1rem
                line-height: 1.8rem
                height: 2rem
        .main
            display: flex
            
            justify-content: space-between
            .mainLeft
                width: 20%
                background-color: #fafafa
            .mainRight
                width: 78%
                background-color: #ffffff
                border: 1px solid #e0e0e0
                border-border-radius: 5px
                padding-right: 1rem
                .first
                    padding: 1rem;
                    font-size: 20px;
                    color: black;
</style>