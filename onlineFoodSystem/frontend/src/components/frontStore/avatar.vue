<template>
    <div class="info">
        <div class="menuTop">
            <el-breadcrumb separator=">" class="menuBread">
            <el-breadcrumb-item :to="{ path: '/front/frontStore' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>个人中心</el-breadcrumb-item>
            <el-breadcrumb-item>上传头像</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="main">
            <div class="mainLeft">
                <el-menu
                    default-active="1-2"
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
                <div class="first">上传头像</div>
                <div class="seconds">
                   <div>
                       <el-upload
                            class="avatar-uploader"
                            action="http://localhost:8080/api/upload"
                            :show-file-list="false"
                            :on-success="handleAvatarSuccess">
                            <img v-if="imageUrl" :src="imageUrl" class="avatar">
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                </div>
                </div>
            </div>
        </div>
    </div>
  
</template>

<script type="text/ecmascript-6">

  export default {
    data(){
        
        return  {
        imageUrl: ''
        }
    },
   
    methods:{
        handleAvatarSuccess(res, file) {
        console.log(res);
        console.log(file);
        this.imageUrl = URL.createObjectURL(file.raw);
      }
        
    }
  };
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
    .info
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
                .first
                    padding: 1rem;
                    border-bottom: 1px solid #e0e0e0;
                    font-size: 20px;
                    color: black;
                .seconds
                    padding: 1rem 2rem;
                    font-size: 20px;
                    .wra
                        border-top: 1px solid #e0e0e0
                        .first
                            display: inline-block;
                            padding: 1.5rem;
                            border-right: 1px solid #e0e0e0;
                        .second
                            display: inline-block;
                            padding: 1.5rem;

</style>
