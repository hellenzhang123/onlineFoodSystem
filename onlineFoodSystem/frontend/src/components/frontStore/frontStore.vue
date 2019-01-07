<template>
  <div class="frontStore">
    <div class="frontContainer">
        <div class="block">
          <el-carousel :interval="4000" type="card" height="200px">
            <el-carousel-item v-for="item in images" :key="item">
              <!-- <h3>{{ item }}</h3> -->
              <img v-bind:src="item" class="itemImg">
            </el-carousel-item>
          </el-carousel>
      </div>
        <div class="frontTop">
            <span class="topSpan">商家分类</span>
        </div>
        <div class="frontHeader">
            <span class="fenlei" @click="getSortStore('')"><a>全部商家</a></span>
            <span class="fenlei" v-for="(item, index) in sortData" @click="getSortStore(item)"><a>{{item.name}}</a></span>
        </div>
        <div class="frontMain">
            <span class="mainItem"  v-for="(item,index) in tableData">
              <router-link :to="{ name: 'menu', params: {id: item.id}}"><img v-bind:src="item.smallImg" class="itemImg"></router-link>
                <span class="itemName">{{item.name}}</span>
                <el-rate class="itemScore"
                  v-model="item.score"
                  disabled
                  text-color="#ff2d48"
                  disabled-void-color="#cccccc"
                  score-template="5">
                  </el-rate>
                <span>
                    <span>起送{{item.startPrice}}</span>
                    <span>配送{{item.deliveryPrice}}</span>
                    <span>时间:{{item.deliveryTime}}</span>
                </span>
                
            </span>
            
        </div>

        <div class="bottom">
        <el-pagination
          background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          :page-size="limit"
          layout="total, prev, pager, next"
          :total="totalCount">
        </el-pagination>
        </div>
    </div>
  </div>

</template>

<script type="text/ecmascript-6">
 import headerMenu from '../headerMenu';
  export default {
    data() {
      return {
        sortData: [],
        totalData: [],
        tableData: [],
        currentPage: 1,
        totalCount: 0,
        limit: 12,
        images:['/static/image/1.jpg','/static/image/2.jpg','/static/image/3.jpg','/static/image/4.jpg','/static/image/5.jpg','/static/image/6.jpg'],
        store: {
          score: ''
        }
      }
    },
     created() {
       this.$http.get('http://localhost:8080/api/store/list').then((response) => {
         
          console.log(response)
            // 响应成功回调
            this.totalData = response.body.content;
            this.totalData.forEach(element => {
              element.smallImg = 'http://localhost:8080/' + element.smallImg;
            });
            this.totalCount = response.body.totalElements
            for(var i = 0; i <  this.totalData.length; i++){
                this.totalData[i].buy_num = 0;
            }
            console.log(this.totalData);
            this.tableData = this.totalData.slice(0, 0 + this.limit);
        }, (response) => {
            // 响应错误回调
        });
        this.$http.get('http://localhost:8080/api/storeSort/list').then((response) => {
          console.log(response)
            // 响应成功回调
            this.sortData = response.body;
        }, (response) => {
            // 响应错误回调
        });
    },

    methods: {
      handleChange(item, index, value) {
            console.log(index);
            console.log(item.buy_num);
        },
      getStores() {
        this.$http.get('http://localhost:8080/api/store/list').then((response) => {
          console.log(response)
            // 响应成功回调
        }, (response) => {
            // 响应错误回调
        });
      },
      getSortStore(item) {
        if (item == '') {
          this.$http.get('http://localhost:8080/api/store/list').then((response) => {
            this.totalData = response.body.content;
            this.totalData.forEach(element => {
              element.smallImg = 'http://localhost:8080/' + element.smallImg;
            });
            this.totalCount = response.body.totalElements
            for(var i = 0; i <  this.totalData.length; i++){
                this.totalData[i].buy_num = 0;
            }
            console.log(this.totalData);
            this.tableData = this.totalData.slice(0, 0 + this.limit);
            console.log(response)
              // 响应成功回调
          }, (response) => {
              // 响应错误回调
          });
        } else {
          this.$http.get('http://localhost:8080/api/store/list?sortId=' + item.id).then((response) => {
            this.totalData = response.body.content;
            this.totalData.forEach(element => {
              element.smallImg = 'http://localhost:8080/' + element.smallImg;
            });
            this.totalCount = response.body.totalElements
            for(var i = 0; i <  this.totalData.length; i++){
                this.totalData[i].buy_num = 0;
            }
            console.log(this.totalData);
            this.tableData = this.totalData.slice(0, 0 + this.limit);
            console.log(response)
              // 响应成功回调
          }, (response) => {
              // 响应错误回调
          });
        }
      },
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        let pageStart = (val-1) * this.limit;
        this.tableData = this.totalData.slice(pageStart, pageStart + this.limit);
      },
      
    //   requestData() {
    //     this.$http.get('/static/bussiness.json').then(response => {
    //     // get body data
    //     let data = response.body;
    //     this.tableData = data.reverse();
    //     }, response => {
    //       // error callback
    //     })
    //   }
    },
    components: {
    }
  };
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
  .frontStore
    background-color: #fafafa
    .block
      margin-top: 1rem
      .itemImg
        width: 500px
        height: 300px
    .frontContainer
      width: 1024px
      margin: 0 auto
    .frontTop
      height: 3rem
      border-bottom: 2px solid #f4f4f4
      margin-bottom: 0.5rem
      .topSpan
        font-size: 1rem
        line-height: 3rem
        display: inline-block
        margin-left: 0.5rem
        color: #ff2d48
        border-bottom: 1px solid #ff2d48
    .frontHeader
      .fenlei
        a:hover
          color: #ff2d48
        padding: 1rem 2rem 1rem 0.5rem
        display: inline-block
      background-color: #ffffff
      display: flex
      flex-wrap: wrap 
      justify-content: left
    .frontMain
      display: flex
      justify-content: left
      flex-direction: row
      flex-wrap: wrap
      .mainItem
        width: 13.5rem
        display: flex
        height: 12rem
        background-color: #ffffff
        align-items: center
        flex-direction: column
        font-size: 14px
        margin-top: 1rem
        margin-right: 0.72rem
        .itemImg
          width: 13.5rem;
          height: 8rem;
          margin-bottom: 0.3rem
        .itemName
          margin-bottom: 0.3rem
        .itemScore
          margin-bottom: 0.3rem
    .bottom
      display: flex
      justify-content: center

</style>
