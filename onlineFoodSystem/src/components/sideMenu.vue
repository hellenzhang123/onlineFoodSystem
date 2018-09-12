<template>
  <div class="side-menu">
    <div class="title-wrapper">
      <h1 class="title">订餐后台系统</h1>
    </div>
    <el-menu
      class="el-menu-vertical-demo"
      background-color="#323641"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="1">
        <router-link to="/backend/business"><span class="title">我的生意</span></router-link>
      </el-menu-item>

      <el-menu-item index="2-1">
        <router-link to="/backend/order"><span class="title">订单中心</span><el-badge :value="newOrder" class="item"></el-badge></router-link>
      </el-menu-item>

      <el-submenu index="3">
        <template slot="title">店铺管理</template>
        <el-menu-item index="3-1">
          <router-link to="/backend/goods"><span class="title">菜品管理</span></router-link>
        </el-menu-item>
        <el-menu-item index="3-2">
          <router-link to="/backend/ratings"><span class="title">评论管理</span></router-link>
        </el-menu-item>
        <el-menu-item index="3-3">
          <router-link to="/backend/seller"><span class="title">原材料管理</span></router-link>
        </el-menu-item>
      </el-submenu>
      <el-submenu index="4">
        <template slot="title">食堂管理</template>
        <el-menu-item index="4-1">
          <router-link to="/backend/stores"><span class="title">商家管理</span></router-link>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    props: {
      newOrder: {
        type: Number,
        default: 0
      }
    }
  };
</script>

<style scoped>
  .el-menu-item, .el-submenu__title {
    padding: 0 !important;
  }

  .el-submenu__title {
    padding-left: 0 !important;
  }

  .el-submenu__title .title {
    font-size: 0.5rem;
  }

  a {
    color: #bfcbd9;
  }

  .side-menu .title-wrapper {
    height: 3rem;
    line-height: 3rem;
    background: #5491de;
  }

  .side-menu .title-wrapper .title {
    font-size: 0.8rem;
    color: white;
  }

  .side-menu .active {
    color: #fff;
    background: #5491de;
  }

  .el-menu {
    border: none;
  }

  .el-submenu .el-menu-item {
    min-width: 7.4rem;
  }

  .el-submenu li {
    padding: 0 !important;
  }

  .side-menu a {
    display: inline-block;
    width: 100%;
  }

  .title {
    margin-left: 20px;
  }

  .el-submenu .title {
    font-size: 0.6rem;
    margin-left: 30px
  }

  .side-menu {
    position: fixed;
    z-index: 1314;
    top: 0;
    left: 0;
    width: 7.5rem;
    height: 100%;
    background: #323641;
  }
  .el-badge {
    margin-left: 0.5rem;
  }
</style>
