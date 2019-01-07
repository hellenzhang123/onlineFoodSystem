<template>
    <div class="address">
        <div class="menuTop">
            <el-breadcrumb separator=">" class="menuBread">
            <el-breadcrumb-item :to="{ path: '/front/frontStore' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>个人中心</el-breadcrumb-item>
            <el-breadcrumb-item>我的地址</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="main">
            <div class="mainLeft">
                <el-menu
                    default-active="3"
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
                <div class="addressManage">
                    地址管理
                </div>
                <div class="cardManage">
                    <div class="card" v-for="(item,index) in gaddress">
                        <div class="first">
                            <div>{{item.name}}&nbsp;{{item.gender}}</div>
                            <div><span  @click="handleEdit(item,index)">修改</span>&nbsp;<span @click="deleteGoods(item)">删除</span></div>
                        </div>
                        <div class="second">
                            <div>{{item.info}}</div>
                            <div>{{item.phone}}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="address-form">
    <el-dialog
      title="添加新地址"
       :visible.sync="editAddressFormVisible.visible"
      width="50%">
      <span>
        <div class="addAddressDialog">
          <el-form :model="address" :rules="rules" ref="addressForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="address.name"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
              <el-radio-group v-model="address.gender">
                <el-radio label="先生"></el-radio>
                <el-radio label="女士"></el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="详细地址" prop="info">
               <el-input v-model="address.info"></el-input>
            </el-form-item>
            <el-form-item label="手机号" prop="phone">
               <el-input v-model="address.phone"></el-input>
            </el-form-item>
              <div class="form-btn-group">
                <el-button type="primary"
                           @click="submitForm('addressForm')">确认添加</el-button>
                <el-button @click="editAddressFormVisible.visible =false">取消</el-button>
              </div>
          </el-form>
        </div>
      </span>
    </el-dialog>
  </div>  
  <el-dialog
      title="删除地址"
      :visible.sync="deleteVisible"
      width="30%">
      <span>确认删除</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="deleteVisible = false">取 消</el-button>
    <el-button type="primary" @click="confirmDel">确 定</el-button>
  </span>
    </el-dialog>
    </div>
</template>

<script type="text/ecmascript-6">

  export default {
    data(){
        return  {
            gaddress:[],
            editAddressFormVisible: {
                visible: false
            },
            deleteitem: {},
            deleteVisible: false,
            user:{},
             address: {
                name:'',
                info:'',
                gender:'',
                phone:''
            },
            rules: {
                name: [
                    { required: true, message: '请输入您的姓名', trigger: 'blur' },
                    { min: 5, max: 10, message: '长度在 5 到 10 个字符', trigger: 'blur' }
                ],
                info:[
                    { required: true, message: '请输入地址信息', trigger: 'blur' }
                
                ],
                gender: [
                    { required: true, message: '请选择您的性别', trigger: 'change' }
                ],
                phone: [
                    { required: true, message: '请输入您的电话', trigger: 'blur' },
                    { pattern: /^1[34578]\d{9}$/, message: '目前只支持中国大陆的手机号码' }
                ]
            }
        }
    },
    created() {
       this.$http.get('http://localhost:8080/api/user/current').then((response) => {
           console.log(response)
           this.user = response.body;
           this.gaddress = response.body.address;
           console.log(this.gaddress)
        }, (response) => {
            // 响应错误回调
        });
    },
    methods:{
        handleEdit(item, index) {
            this.address = item;
            this.editAddressFormVisible.visible = true;
            
        },
        deleteGoods(item) {
            this.deleteVisible = true;
            this.deleteitem = item;
        },
        confirmDel() {
            for (var i = 0; i < this.gaddress.length; i++) {
                if (this.gaddress[i].id == this.deleteitem.id) {
                    this.gaddress.splice(i,1);
                } 
            }
            this.user.address = this.gaddress;
            this.$http.put('http://localhost:8080/api/user/' + this.user.id, this.user).then((response) => {

                console.log(response)
            }, (response) => {
                // 响应错误回调
            });
            this.deleteVisible = false;
        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
            if (valid) {
                this.$message.success("添加地址成功");
                this.editAddressFormVisible.visible = false;
                this.gaddress.forEach((item)=>{
                    if (item.id == this.address.id) {
                        item = this.address;
                    }
                });
                this.user.address = this.gaddress;
                // console.log(this.user);
                // console.log(this.user.address);
                this.$http.put('http://localhost:8080/api/user/' + this.user.id, this.user).then((response) => {

                    console.log(response)
                }, (response) => {
                    // 响应错误回调
                });
                // bus.$emit('editSuccess',this.good);
            } else {
                console.log('error submit!!');
                return false;
            }
            });
        }
    }
  };
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
    .address
        width: 1024px
        margin: 0 auto
        .menuTop
            .menuBread
                margin-left: 1rem
                line-height: 1.8rem
                height: 2rem
        .main
            display: flex
            height: 30rem
            justify-content: space-between
            .mainLeft
                width: 20%
                background-color: #fafafa
            .mainRight
                width: 78%
                background-color: #ffffff
                border: 1px solid #e0e0e0
                border-border-radius: 5px
                .addressManage
                    padding: 1rem 2rem
                    border-bottom: 1px solid #e0e0e0
                    color: #000000
                    font-font-weight: bold
                .cardManage
                    padding: 1rem
                    display: flex
                    flex-direction: row
                    justify-content:left
                    flex-wrap: wrap
                    .card
                        width: 13rem
                        height: 7rem
                        border: 1px solid #e0e0e0
                        margin: 0rem 1rem 1rem 0rem
                        .first
                            display: flex
                            justify-content: space-between
                            padding: 1rem
                            font-size: 16px
                        .second
                            padding: 0rem 1rem
                            font-size: 16px
                            line-height: 1.5rem
                            
</style>