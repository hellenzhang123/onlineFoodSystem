<template>
  <div class="orderResult">
    <div class="menuTop">
        <el-breadcrumb separator=">" class="menuBread">
        <el-breadcrumb-item :to="{ path: '/front/frontStore' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>等待接单</el-breadcrumb-item>
        </el-breadcrumb>
    </div>
    <div class="top">
      <div><img src="/static/image/result.png" class="imgitem"></div>
      <div class="center">
        <div class="sencond">
          您的订单已经成功支付~
        </div>
        <div class="sencond">
          正在等待商家接单！
        </div>
        <div>
          如接单成功，将在40分钟内送达。请您耐心等待~
        </div>
      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">

  // export default {
  //   data(){
  //       return  {
  //           tableData : [],
  //           totalCount:0,
  //           tableData: [],
  //           currentPage: 1,
  //           limit: 6,
  //       }
  //   },
  //   created() {
  //      this.$http.get('http://localhost:8080/api/orderform/list').then((response) => {
  //           this.totalData = response.body.content;
  //           this.totalCount = response.body.totalElements
  //           this.totalData.forEach((item) => {
  //               if (item.state == 0) {
  //                  item.state = '等待接单'
  //               } else if (item.state == 1) {
  //                  item.state = '已完成'
  //               } else {
  //                  item.state = '未完成'
  //               }
  //               item.orderdetail.forEach((content) => {
  //                   var text = '';
  //                   text += content.name;
  //                   item.orderContent = text;
  //               });
  //          });
  //          this.tableData = this.totalData.slice(0, 0 + this.limit);
  //       }, (response) => {
  //       });
  //   },
  //   methods:{
  //   }
  // };
</script>
<style lang="stylus" rel="stylesheet/stylus" scoped>
  .orderResult
    height: 10rem;
    width: 1024px;
    margin: 0 auto;
    height: 15rem;
    margin-bottom: 1rem
    .menuTop
      .menuBread
          margin-left: 1rem
          line-height: 1.8rem
          height: 2rem
    .top
      background: #ffffff
      padding: 2rem
      display: flex
      flex-direction: row
      justify-content: space-between
      .imgitem
        width: 500px
    .center
        display: flex;
        flex-direction: column;
        justify-content: space-around;
      
            

</style>