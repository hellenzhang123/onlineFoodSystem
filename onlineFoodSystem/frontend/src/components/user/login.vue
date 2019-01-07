<template>
    <div class="login">
      <div class="wrapper">
        <div class="header">登录</div>
        <div class="ltable">
        
            <div v-show="islogin"> 
                <el-form :model="ruleForm"  ref="ruleForm" label-width="100px" class="demo-ruleForm registerTable">
                    <el-form-item prop="name" class="kk">
                        <el-input v-model="ruleForm.name"  placeholder="姓名"></el-input>
                    </el-form-item>
                    <el-form-item  prop="password">
                        <el-input type="password" v-model="ruleForm.password" auto-complete="off" placeholder="密码"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')" class="loginBtn">登录</el-button>
                    
                    </el-form-item>
                    <span class="itemSpan">
                        <span><router-link to="/register">还没有账号?立即注册</router-link></span>
                        <span @click="islogin = false , isforget = true">忘记密码</span>
                    </span>
                </el-form>
            </div>
            <div v-show="isforget">
                <el-form :model="forget" ref="forget" label-width="100px" class="demo-ruleForm registerTable">
                    <el-form-item prop="name" class="kk">
                        <el-input v-model="forget.name"  placeholder="用户名"></el-input>
                    </el-form-item>
                    <el-form-item prop="question" class="kk">
                        <el-input v-model="forget.question"  placeholder="密保问题"></el-input>
                    </el-form-item>
                    <el-form-item  prop="answer">
                        <el-input v-model="forget.answer" auto-complete="off" placeholder="密保答案"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitforget('forget')" class="loginBtn">立即找回</el-button>
                    
                    </el-form-item>
                </el-form>
            </div>
            <div v-show="ispassword">
                <el-form :model="mopassword" ref="mopassword" label-width="100px" class="demo-ruleForm registerTable">
                    
                    <el-form-item prop="password" class="kk">
                        <el-input v-model="mopassword.question" type="password" placeholder="密码"></el-input>
                    </el-form-item>
                    <el-form-item  prop="confrim">
                        <el-input v-model="mopassword.confrim" placeholder="确认密码"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitpassword('mopassword')" class="loginBtn">立即修改</el-button>
                    
                    </el-form-item>
                </el-form>
            </div>
            </div>
        </div>

    </div>
</template>

<script type="text/ecmascript-6">

  export default {
    data(){
        return  {
          name: '',
          islogin: true,
          isforget: false,
          ispassword: false,
          forget: {
            question:'',
            answers:''
          },
          ruleForm: {
            name: '',
            password: '',
          },
          mopassword: {
            password: '',
            confirm:''
          }
            
        }
    },
   
    methods:{
       submitForm(formName) {
            this.$http.post('http://localhost:8080/api/user/login?' + 'name=' + this.ruleForm.name + '&&password=' + this.ruleForm.password).then((message) => {
                    console.log(message);
                    const token = message.bodyText;
                    console.log(token);
                    window.localStorage.setItem('token', token)
                    window.location.pathname = '/'
                    if (this.ruleForm.name == 'zoe') {
                        this.$router.push({path: '/backend'})
                    } else if (this.ruleForm.name == 'kitty') {
                        this.$router.push({path: '/backend'})
                    } else {
                        this.$router.push({path: '/front/frontStore'})
                    }
                   
                    console.log('success');
                //这里处理成功回调
                })
                .catch((message) => {
                    this.$message.error('用户名或密码错误');
                    console.log('error');
                //这里处理失败回调
                });
       },
      submitforget(formName) {
            this.name = this.forget.name;
            this.$http.post('http://localhost:8080/api/user/forget?' + 'name=' + this.forget.name + '&&question=' + this.forget.question + '&&answer=' + this.forget.answer).then((message) => {
                    console.log(message);
                    if (message.body == true) {
                        this.isforget = false;
                        this.ispassword = true;
                    } else {
                        this.$message.error('密保问题没有通过验证');
                    }
                //这里处理成功回调
                })
                .catch((message) => {
                    console.log('error');
                //这里处理失败回调
                });
      },
      submitpassword(formName) {
            this.$http.post('http://localhost:8080/api/user/password?' + 'password=' + this.mopassword.password + '&&name=' + this.name).then((message) => {
                    console.log(message);
                    this.ispassword = false;
                    this.islogin = true;
                //这里处理成功回调
                })
                .catch((message) => {
                    console.log('error');
                //这里处理失败回调
                });
                
      }
    }
  };
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
    .login
        background: url("/static/image/info.jpg")
        display: flex
        justify-content: center 
        align-items: center
        height:40rem
        flex-direction: column
        .wrapper
            background: #ffffff
            margin-right: 1rem
            .header
                border-bottom: 1px solid #cccccc;
                width: 20rrem;
                width: 20rem;
                color: #66b1ff;
                margin-left: 2.5rem;
                font-size: 25px;
                text-align: center;
                letter-spacing: 10px;
                padding: 1rem 0rem;
            .ltable
                width: 20rem
                margin: 0 auto
                padding: 2rem 5rem 2rem 0rem
                display: flex
                justify-content: center

                .loginBtn
                    width: 20rem
                    margin-bottom: -0.5rem
                .itemSpan
                    font-size: 14px;
                    margin-left: 100px;
                    cursor pointer
                    display: flex 
                    justify-content: space-between
</style>