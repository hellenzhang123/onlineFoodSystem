<template>
  <div class="frontMenu">
    <div class="frontContainer">
        <div class="menuTop">
            <el-breadcrumb separator=">" class="menuBread">
            <el-breadcrumb-item :to="{ path: '/front/frontStore' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>{{store.name}}</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="menuHeader">
            <div>
                <img v-bind:src="'http://localhost:8080/' + store.smallImg" class="menuImg">
                <div class="menuStore">
                    <div>{{store.name}}</div>
                    <div>
                        <el-rate class="itemScore"
                        v-model="store.score"
                        disabled
                        show-score
                        text-color="#ff9900"
                        disabled-void-color="#ff2d48"
                        score-template="5">
                        </el-rate>
                    </div>
                    <div>商户地址:{{store.address}}</div>
                    <div>商户简介:{{store.description}}</div>
                </div>
            </div>
            <div class="menuDelivery">
                <span>￥{{store.startPrice}}<br><br>起送价</span>
                <span>￥{{store.deliveryPrice}}<br><br>配送费</span>
                <span><i class="el-icon-time"></i>&nbsp;{{store.deliveryTime}}<br><br>送达时间</span>
            </div>
        </div>
        <div class="main">
            <div class="mainLeft">
                <div class="frontTop">
                    <span class="topSpan" @click="isMenu=true">商家菜品</span>
                    <span class="topSpan" @click="isMenu=false">原材料展示</span>
                </div>
                <div class="frontHeader">
                    <span class="fenlei" @click="findStoreByItem('')"><a>全部菜品</a></span>
                    <span class="fenlei" v-for="(item, index) in sortData" @click="findStoreByItem(item)"><a>{{item.name}}</a></span>
                </div>
            </div>
            <div class="mainRight">
                <div class="rightFirst">
                    &nbsp;&nbsp;商家公告
                </div>
                <div class="rightSecond">
                    <span>
                        本店接收食品安全监督，保证服务质量。餐厅联系电话：28191895，本区域投诉电话：13723412262。
                    </span>
                </div>
            </div>
            
        </div>
        <div>
            <div class="mainCenter">
                <div class="mainItem"  v-for="(item, index) in tableData" v-show="isMenu">
                    <div class="mainImg">
                        <img v-bind:src="'http://localhost:8080/' + item.smallImg" class="itemImg">
                    </div>
                    <div class="itemFirst">
                        <div>
                            <span>{{item.name}}</span>
                        </div>
                        <div><el-rate
                            v-model="item.score"
                            disabled
                            show-score
                            text-color="#ff9900"
                            disabled-void-color="#ff2d48"
                            score-template="5">
                            </el-rate></div>
                        <div><span>月销量：{{item.sellcount}}</span></div>
                        <div class="mainShop">
                            <span>￥{{item.price}}</span>
                            <span class="mainCart" @click="addItem(item, index)" v-if="item.buy_num == 0">加入购物车</span>
                            <!-- <span class="mainCartCount" v-if="item.buy_num > 0">
                                <el-input-number class="mainCart"  @change="handleChange(item, index)" :min="0" :max="10"  v-model="tableData[index].buy_num">
                                </el-input-number>
                            </span> -->

                            <span class="mainCartCount" v-if="item.buy_num > 0">
                                <div class="shop-cartctrl">
                                    <button class="minus" @click="sub(item, index)">-</button>
                                    <input type="text" onkeyup='this.value=this.value.replace(/\D/gi,"")' class="ctrl" min="1" v-model="item.buy_num" @change="changeNum(item, index)">
                                    <button class="plus" @click="plus(item, index)">+</button>
                                </div>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="seller-table" v-show="!isMenu">
                <el-table
                    ref="multipleTable"
                    :data="etableData"
                    tooltip-effect="dark"
                    style="width: 100%"
                    >
                    <el-table-column
                    prop="name"
                    label="材料名称"
                    width="200"
                    align="center">
                    </el-table-column>
                    <el-table-column
                    label="材料图片"
                    width="150"
                    align="center">
                    <template scope="scope">
                        <img v-bind:src="'http://localhost:8080/' + scope.row.img" width="100" height="100"></template>
                    </el-table-column>
                    <el-table-column
                    prop="number"
                    label="购买数量"
                    width="150"
                    align="center">
                    </el-table-column>
                    <el-table-column
                    prop="orderDate"
                    label="预订时间"
                    width="160"
                    align="center">
                    </el-table-column>
                    <el-table-column
                    prop="sellDate"
                    label="保质期限"
                    width="160"
                    align="center">
                    </el-table-column>
                    <el-table-column
                    prop="saveMethod"
                    label="储存方法"
                    width="200"
                    align="center">
                    </el-table-column>
                </el-table>
                </div>
            </div>
        </div>
        <div class="bottom">
            <el-pagination v-show="isMenu"
            background
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
            :page-size="limit"
            layout="total, prev, pager, next"
            :total="totalCount">
            </el-pagination>
            <!-- <el-pagination v-show="!isMenu"
            background
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
            :page-size="limit"
            layout="total, prev, pager, next"
            :total="etotalCount">
            </el-pagination> -->
        </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
      return {
            isMenu:true,
            etableData:[],
            sortData: [],
            totalData: [],
            tableData: [],
            currentPage: 1,
            totalCount: 0,
            limit: 6,
            store: {
                id:0,
                address: '',
                bigImg:'',
                deliveryPrice:'',
                deliveryTime:'',
                description:'',
                keyword:'',
                food:[],
                meterial : [],
                name:'',
                phone:'',
                rating:[],
                score:'',
                sellCount:'',
                smallImg:'',
                sort:'',
                startPrice:''
            },
            etotalData:[],
            etotalCount: 0,
            cart_list:[],
            num:1,
            id:0,
            cart: {}
      }
    },
    created() {
        var id = this.$route.params.id;
        this.id = id;
        var obj = window.localStorage.getItem("cart_list")
        obj = JSON.parse(obj);
        if (obj != null) {
            if (obj[this.id] == '[]' || obj[this.id] == null || obj[this.id] == undefined) {
            } else {
                //转化为json对象
                
                this.cart_list = obj[this.id];
            }
        }
        this.$http.get('http://localhost:8080/api/store/' + id).then((response) => {
            console.log(response)
            this.store = response.body;
            this.totalCount = this.store.food.length;
            var vm = this;
            if (vm.cart_list.length > 0) {
               for(var i = 0; i <  vm.store.food.length; i++){
                   vm.store.food[i].store_id = this.id;
                   vm.store.food[i].store_name = this.store.name;
                    for(var j = 0; j < vm.cart_list.length; j++) {
                        if (vm.store.food[i].id == vm.cart_list[j].id) {
                            vm.store.food[i].buy_num = vm.cart_list[j].buy_num;
                            break;
                        } else {
                            vm.store.food[i].buy_num = 0;
                        }
                    }
                } 
            } else {
                for(var i = 0; i <  vm.store.food.length; i++){
                    vm.store.food[i].store_id = this.id;
                    vm.store.food[i].store_name = this.store.name;
                    vm.store.food[i].buy_num = 0;
                } 
            }
            this.totalData = vm.store.food;
            this.sortData = this.store.msort
            this.tableData = this.totalData.slice(0, 0 + this.limit);
            // 响应成功回调
           
        }, (response) => {
            // 响应错误回调
        });
        this.$http.get('http://localhost:8080/api/meterial/list?storeId=' + id).then(response => {
          console.log(response);
          let data = response.body.content;
          console.log(data);
          this.etotalData = data;
          this.etotalCount = data.length;
          this.etableData = this.etotalData.slice(0, 0 + this.limit);
        }, response => {
          // error callback
        });
    },
    watch: {
      cart_list: {
        handler: function (newVal) {
            
            var getobj = window.localStorage.getItem("cart_list")
            //转化为json对象
            var getobj = JSON.parse(getobj);
            if (!(getobj == null || getobj == [])) {
                for (var n in getobj) {
                    this.cart[n] = getobj[n];
                }
            }
            this.cart[this.id] = newVal;
            console.log(this.cart);
            //将对象保存到localStorage中
            var obj = JSON.stringify(this.cart);
            window.localStorage.setItem('cart_list',obj);

          console.log(newVal)
        },
        deep: true
      }
    },
    methods: {
        sub(item, index) {
            if (item.buy_num > 1) {
                item.buy_num = item.buy_num - 1;
                item.goods_total = item.price * item.buy_num;
                this.$set(this.tableData, index, item);
                for (var i = 0; i < this.cart_list.length; i ++) {
                    if (this.cart_list[i].id = item.id) {
                        this.cart_list[i].buy_num = item.buy_num;
                        this.cart_list[i].goods_total = item.goods_total;
                    }
                }
            } else {
                //购物车中数量减少为0
                for (var i = 0; i < this.cart_list.length; i ++) {
                    if (this.cart_list[i].id = item.id) {
                        this.cart_list.splice(i, 1);
                    }
                }
            }
        },
        plus(item, index) {
            if (item.buy_num < 100) {
                item.buy_num = item.buy_num + 1;
                item.goods_total = item.price * item.buy_num;
                this.$set(this.tableData, index, item);
                for (var i = 0; i < this.cart_list.length; i ++) {
                    if (this.cart_list[i].id = item.id) {
                        this.cart_list[i].buy_num = item.buy_num;
                        this.cart_list[i].goods_total = item.goods_total;
                    }
                }
            } else {
                //提示
            }

        },
        changeNum(item, index) {
            var m = parseInt(item.buy_num);
            item.buy_num = m;
            // item.buy_num = item.buy_num + 1;
            item.goods_total = item.price * item.buy_num;
            this.$set(this.tableData, index, item);
            for (var i = 0; i < this.cart_list.length; i ++) {
                if (this.cart_list[i].id = item.id) {
                    this.cart_list[i].buy_num = item.buy_num;
                    this.cart_list[i].goods_total = item.goods_total;
                }
            }

        },
        handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            let pageStart = (val-1) * this.limit;
            if (this.isMenu) {
                this.tableData = this.totalData.slice(pageStart, pageStart + this.limit);
            } else {
                this.etableDate = this.etotalData.slice(pageStart, pageStart + this.limit);
            }
        },
        findStoreByItem(item) {
            var vm = this;
            if (item == '') {
                 this.$http.get('http://localhost:8080/api/food/list').then((response) => {
                console.log(response)
                this.totalCount = response.body.totalElements;
                this.totalData = response.body.content;
                this.tableData = this.totalData.slice(0, 0 + this.limit);
                // 响应成功回调

            if (vm.cart_list.length > 0) {
               for(var i = 0; i <  this.totalData.length; i++){
                   this.totalData[i].store_id = this.id;
                   this.totalData[i].store_name = this.store.name;
                    for(var j = 0; j < vm.cart_list.length; j++) {
                        if (this.totalData[i].id == vm.cart_list[j].id) {
                            this.totalData[i].buy_num = vm.cart_list[j].buy_num;
                            break;
                        } else {
                            this.totalData[i].buy_num = 0;
                        }
                    }
                } 
            } else {
                for(var i = 0; i <  this.totalData.length; i++){
                    this.totalData[i].store_id = this.id;
                    this.totalData[i].store_name = this.store.name;
                    this.totalData[i].buy_num = 0;
                } 
            }
                }, (response) => {
                    // 响应错误回调
                });
            } else {
                this.$http.get('http://localhost:8080/api/food/list?sortId=' + item.id).then((response) => {
                console.log(response)
                this.totalCount = response.body.totalElements;
                this.totalData = response.body.content;
                this.tableData = this.totalData.slice(0, 0 + this.limit);
                // 响应成功回调
                 if (vm.cart_list.length > 0) {
               for(var i = 0; i <  this.totalData.length; i++){
                   this.totalData[i].store_id = this.id;
                   this.totalData[i].store_name = this.store.name;
                    for(var j = 0; j < vm.cart_list.length; j++) {
                        if (this.totalData[i].id == vm.cart_list[j].id) {
                            this.totalData[i].buy_num = vm.cart_list[j].buy_num;
                            break;
                        } else {
                            this.totalData[i].buy_num = 0;
                        }
                    }
                } 
            } else {
                for(var i = 0; i <  this.totalData.length; i++){
                    this.totalData[i].store_id = this.id;
                    this.totalData[i].store_name = this.store.name;
                    this.totalData[i].buy_num = 0;
                } 
            }
                }, (response) => {
                    // 响应错误回调
                });
            }
           
        },
        addItem(item, index) {
            var food = new Object();
            item.buy_num = item.buy_num + 1;
            this.$set(this.tableData, index, item);
            food = item;
            food.goods_total = item.price;
            this.cart_list.push(food);
            // this.cart[this.id] = this.cart_list;
            // console.log(this.cart);
            // var getobj = window.localStorage.getItem("cart_list")
            // //转化为json对象
            // var getobj = JSON.parse(getobj);
            // if (!(getobj == null || getobj == [])) {
            //     for (var n in getobj) {
            //         this.cart[n] = getobj[n];
            //     }
            // }
            // //将对象保存到localStorage中
            // var obj = JSON.stringify(this.cart);
            // window.localStorage.setItem('cart_list',obj);

            //  window.localStorage.setItem('cart_list', this.cart_list);
        }
    }
  };
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .frontMenu
    background-color: #fafafa
    .el-input__inner
      padding-left: 30px;
      padding-right: 35px;
    .frontContainer
      width: 1024px
      margin: 0 auto
      .menuTop
        .menuBread
          margin-left: 1rem
          line-height: 1.8rem
        height: 2rem
      .menuHeader
        background-color: #ffffff
        display: flex
        justify-content: space-between
        div
          display: flex
      .menuImg
        width: 10rem
        height: 8rem
        margin-right: 1rem
      .menuStore
        display: flex
        flex-direction: column
        justify-content: space-evenly
      .menuDelivery
        span
          padding-left: 1rem
          padding-right: 1rem
        display: flex
        align-items: center
      .main
        width: 1024px
        height: 10rem
        display: flex
        .mainLeft
          width: 75%
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
            height: 5rem
        .mainRight 
          width: 25%
          margin: 0.5rem 0rem 1rem 1rem
          background-color: #ffffff
          .rightFirst
            height: 2.5rem
            background-color: #fffce3
            border-bottom: 1px solid #ff2d48
            line-height: 2.5rem
          .rightSecond
            margin-top: 1rem
            height: 5.2rem
            border-bottom: 2px solid #f4f4f4
            font-size: 16px
      .mainCenter
        display: flex
        justify-content: space-between
        flex-direction: row
        flex-wrap: wrap 
        .mainItem
          margin-top: 1rem 
          background-color: #ffffff
          width: 28rem
          display: flex
          height: 10rem
          .mainImg
            width: 40%
            .itemImg
              width: 10rem
              height: 10rem
          .itemFirst
            width: 60%
            display: flex
            flex-direction: column
            justify-content: space-evenly
            .mainShop
              display: flex
              justify-content: space-between
              line-height: 2rem
              .shop-cartctrl
                  width: 7rem;
                  display: flex;
                  outline: 0;
                  .ctrl
                    width: 2rem;
                    display: inline-block;
                    text-align: center;
                    line-height: 28px;
                    height: 25px;
                    border-bottom: 1px solid #ff2d40;
                    border-top: 1px solid #ff2d40;
                    display: inline-block;
                    padding: 0px;
                    margin: 0px;
                  .plus
                    display: inline-block;
                    height: 1.5rem;
                    width: 1.5rem;
                    border: 0px;
                    border-left: 1px solid #ddd;
                    border-radius: 0 20px 20px 0;
                    cursor: pointer;
                    background: #ff2d40;
                    font-size: 20px;
                    text-align: left;
                    color: #ffffff;
                  .minus
                    display: inline-block;
                    height: 1.5rem;
                    width: 1.5rem;
                    border: 0px;
                    border-right: 1px solid #ddd;
                    border-radius: 20px 0 0 20px;
                    cursor: pointer;
                    background: #ff2d40;
                    font-size: 20px;
                    color: #ffffff;
                    padding: 0px;
                    margin: 0px;
              .mainCart
                margin-right: 1rem
                width: 6rem
                height: 2rem
                display: inline-block
                color: #ffffff
                text-align: center
                line-height: 2rem
                background-color: #ff2d48
                border-radius: 6px
    .bottom
      margin-top: 1rem
      display: flex
      justify-content: center
</style>
