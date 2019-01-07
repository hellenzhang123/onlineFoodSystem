<template>
  <div id="app">
    <div v-if="jugePage() == 'back'">
      <navmenu></navmenu>
      <sidemenu :newOrder="newOrder"></sidemenu>
      <div class="mainContent">
        <!-- <keep-alive> -->
        <router-view></router-view>
        <!-- </keep-alive> -->
      </div>
    </div>
    <div v-if="jugePage() == 'front'">
      <headerMenu></headerMenu>
      <div class="frontMainContent">
        <!-- <keep-alive> -->
        <router-view></router-view>
        <!-- </keep-alive> -->
        <div class="bottom">
          <bfooter></bfooter>
        </div>
        <div class="bbottom"></div>
      </div>
    </div>
    <div v-if="jugePage() == 'else'">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
  import navmenu from './components/navMenu'
  import sidemenu from './components/sideMenu'
  import headerMenu from './components/headerMenu'
  import bfooter from './components/bfooter'

  export default {
    name: 'app',
    data() {
      return {
        allData: []
      }
    },
    created: function () {
      setInterval(this.addOne, 10000);
    },
//    created: function () {
//      this.$http.post("http://localhost:8888/manage/listUser", {
//        limit: 15,
//        offset: 0
//      }, {emulateJSON: true}).then((response) => {
//          console.log(response);
//        },
//        (error) => {
//          console.log(error);
//        }
//      );
//    },
    watch: {
      newOrder: {
        handler(val) {
          if (location.href.indexOf('order') > 0) {
            this.$store.state.newOrder = 0;
          }
          if (val === 0) {
            return;
          }
          this.$message({
            message: '您有新订单了，快去查看吧！',
            type: 'success',
            customClass: 'info',
            iconClass: 'el-icon-info'
          });
          var audio = new Audio("/static/new.mp3");
          audio.play();
        }
      }
    },
    methods: {
      // isBack() {
      //   console.log(window.location.href)
      //   if (window.location.href.indexOf("backend") > 0 || window.location.href === 'http://localhost:8081/#/') {
      //     return true;
      //   } else if (){
      //     return false;
      //   }
      //   // return true;
      // },
      jugePage() {
        console.log(window.location.href)
        if (window.location.href.indexOf("backend") > 0 || window.location.href === 'http://localhost:8081/#/') {
          return 'back';
        } else if (window.location.href.indexOf("front") > 0){
          return 'front';
        } else {
          return 'else'
        }
        // return true;
      },
      addOne() {
        if (this.$store.state.storeState) {
          this.$store.commit('addOrder');
        }
      }
    },
    computed: {
      newOrder() {
        return this.$store.state.newOrder;
      }
    },
    components: {
      navmenu,
      sidemenu,
      headerMenu,
      bfooter
    }
  }

</script>

<style lang="stylus" rel="stylesheet/stylus">

  #app
    height: 100%
    .mainContent
      padding: 3rem 0 0 7.5rem
    .frontMainContent
      background-color: #fafafa
    .bottom
      width:100%
      background: #ffffff
      margin-top: 1rem
    .bbottom
      height: 2rem
      width: 100%
      background: #fafafa
  .info
    background: #f2f6fc
    color: #878d99
    .el-icon-info
      color: #5491de!important
      margin-right: 0.5rem

</style>
