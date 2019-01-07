<template>
  <div class="wrapper">
    <div class="nav-menu">
      <span>
        <span><img src='/static/image/logo2.png' class="logimg"></span>
        <span class="logfont">山大外卖</span>
      </span>
      <div class="itemSelect">
        <el-input
          placeholder="请输入关键词搜索"
          prefix-icon="el-icon-search"
          v-model="searchKey"
          @keyup.enter.native="searchEnterFun">
        </el-input>
        <el-select v-model="canteen" placeholder="请选择餐厅">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </div>
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" background-color="#ff2d48"
  text-color="#fff"
  active-text-color="#ffd04b">
      <el-menu-item index="1"><router-link to="/front/myOrder">我的订单</router-link></el-menu-item>
      <el-menu-item index="2"><router-link to="/front/shopCart">查看购物车</router-link></el-menu-item>
      <el-menu-item index="3" v-if="!isLogin"><router-link to="/front/login">登录</router-link></el-menu-item>
      <el-submenu index="3" v-if="isLogin" >
          <template slot="title"><div class="user-headimage">
          <img :src="'http://localhost:8080/' + user.avatar" width="100%" height="100%">
        </div>&nbsp;&nbsp;&nbsp;{{user.name}}</template>
          <el-menu-item index="2-1" text-color="#fff"><router-link to="/front/info">个人中心</router-link></el-menu-item>
          <el-menu-item index="2-2"><router-link to="/front/address">我的地址</router-link></el-menu-item>
          <el-menu-item index="2-3" @click="outLogin"><router-link to="/login">退出登录</router-link></el-menu-item>
      </el-submenu>
      </el-menu>
    </div>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1',
        isLogin: false,
        searchKey:'',
        canteen:'',
        position: '山西大学',
        options: [{
          value: '选项1',
          label: '令德食堂'
        }, {
          value: '选项2',
          label: '文瀛食堂'
        }],
        searchData:{
          items:[],
          pageCount:0,
        },
        user: {

        }
      };
    },
    created() {
       this.$http.get('http://localhost:8080/api/user/current').then(response => {
         this.user = response.body;
         if (this.user != null) {
           this.isLogin = true;
         } else {
           this.isLogin = false;
         }
        }, response => {
          // error callback
          this.isLogin = false;
        });
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      searchEnterFun:function(e){
        var keyCode = window.event? e.keyCode:e.which;
      //  console.log('回车搜索',keyCode,e);
        var path = window.location.href;
        var request = '';
        if (path.indexOf('frontMenu') > 0) {
          request = 'food';
        } else {
          request = 'store';
        }
        if(keyCode == 13 && this.searchKey){
           this.$http.get('http://localhost:8080/api/' + request + '/list?name=' + this.searchKey).then((message) => {
              this.searchData.items = message.body.content;
              this.searchData.pageCount = message.body.content.length;
              console.log('success');
          //这里处理成功回调
          })
          .catch((message) => {
              console.log('error');
          //这里处理失败回调
          });
           console.log('监听到了！！')
        }
    },
    outLogin() {
      window.localStorage.clear();
    }
     }
  }
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .wrapper
    background-color: #ff2d48
    width: 100%
    .nav-menu
      width: 1024px
      margin: 0 auto
      height: 3.4rem
      .logimg
        width: 50px
        height: 50px
      .logfont
        font-size: 25px;
        color: #ffffff;
        display: inline-block;
      .itemSelect
        display: inline
        width: 20rem
        margin-left: 5rem
      .el-input
        width: 10rem;
        line-height: 3.4rem;
        margin-left: -1rem;
      .el-menu
        height: 3rem
        float: right 
    .user-headimage
        display inline-block
        vertical-align top
        height:2.5rem
        width 2.5rem
        border-radius 50%
        img
          border-radius 50%

</style>
