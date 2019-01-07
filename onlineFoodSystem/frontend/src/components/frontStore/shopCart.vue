<template>
  <div class="shopCart">
    <div class="menuTop">
        <el-breadcrumb separator=">" class="menuBread">
        <el-breadcrumb-item :to="{ path: '/front/frontStore' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/">购物车</a></el-breadcrumb-item>
        </el-breadcrumb>
    </div>
    <div>
        <el-steps :active="0" finish-status="success" simple class="stepStyle">
        <el-step title="加入购物车" ></el-step>
        <el-step title="确认订单" ></el-step>
        <el-step title="进行支付" ></el-step>
        </el-steps>
    </div>
    
    <div v-for="(item, key, index) in cart">
        <div v-if="item.length > 0" class="cartTop">
            <span v-if="item.length > 0">{{index}}号购物车-{{item[0].store_name}}</span>
            <span><router-link :to="{ name: 'menu', params: {id: key}}">继续选购</router-link></span>
        </div>
        <div class="shopMain" v-if="item.length > 0">
            <el-table :data="item" @selection-change="handleSelectionChange" @row-click="hanleClickRow" width="100%">
                <el-table-column type="selection" align="center"></el-table-column>
        
                <el-table-column label="商品名称" prop="name" align="center"  header-align="center"></el-table-column>
                <el-table-column label="购买数量"  align="center"  header-align="center">
                        <template slot-scope="scope"><div class="div_number">
                            <el-input-number :min="1" @change="handleChange" size="mini" v-model="scope.row.buy_num"></el-input-number></div>
                        </template>
                </el-table-column>
                <el-table-column label="商品单价" prop="price"  align="center"  header-align="center"></el-table-column>
                <el-table-column label="小计" prop="goods_total"  align="center"  header-align="center">
                    <!-- <template slot-scope="scope">{{ scope.row.date }}</template> -->
                </el-table-column>
                <el-table-column label="操作"  align="center"  header-align="center">
                    <template slot-scope="scope">
                        <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="shopButton">
                <div>
                    <el-button @click="toggleSelection(key)">清空购物车 </el-button>
                </div>
                <div>
                    合计：{{total_price[key]}}元
                    <el-button type="primary" @click="submitCart(key)">去下单</el-button>
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
            cart: {},
            cart_list : [],
            check_list:[],
            orderformId: 0,
        }
    },
    created(){
        this.init()
    },
    computed:{
        total_price: function () {
            let sum_price = 0;
            let total = {};
            let id = 0;
            this.check_list.forEach((item)=>{
                sum_price += item.buy_num*item.price
                id = item.store_id;
            })
            total[id] = sum_price;
            // total[this.check_list[0].store_id] = sum_price;
            return total;
        }
    },
    watch: {
      cart: {
        handler: function (newVal) {
            console.log(newVal);
            // var getobj = window.localStorage.getItem("cart_list")
            // //转化为json对象
            // var getobj = JSON.parse(getobj);
            // if (!(getobj == null || getobj == [])) {
            //     for (var n in getobj) {
            //         this.cart[n] = getobj[n];
            //     }
            // }
            // this.cart[this.id] = newVal;
            // console.log(this.cart);
            //将对象保存到localStorage中
            var obj = JSON.stringify(newVal);
            window.localStorage.setItem('cart_list',obj);
        },
        deep: true
      }
    },
    methods:{
        init:function () {
            console.log(window.localStorage.getItem('cart_list'));
            // this.cart_list = window.localStorage.getItem('cart_list');
             //获取localStorage中的值
            var obj = window.localStorage.getItem("cart_list")
            //转化为json对象
            var obj = JSON.parse(obj);
            // this.cart_list = JSON.parse(obj)
            this.cart = obj;
            // for (var n in obj) {
            //     this.cart.push(obj[n]);
            // }
            // console.log(this.cart);
        },
        // handleSelectionChange(key) {
        //     console.log(key);

        //     this.a = function (val) {
        //         
        //     }
        //     this.a();
            
        // },
        handleSelectionChange: function(val) {
            console.log(val);
            this.isOperate = true
            this.check_list = val
            
            // var sum_price = 0;
            // this.check_list.forEach(element => {
            //     sum_price += element.buy_num*element.price
            // });
            // this.total_price[this.check_list[0].store_id] = sum_price;
            // console.log(this.check_list[0].store_id);
            // console.log(this.total_price[this.check_list[0].store_id]);
            console.log(this.check_list + 'kkkkk')
        },
        
        hanleClickRow:function(row) {
            row.goods_total = row.price * row.buy_num
            console.log(row)
            console.log(this.cart[row.store_id]);
            //将对象保存到localStorage中
            // var obj = JSON.stringify(this.cart_list);
            // window.localStorage.setItem('cart_list',obj);
        },
        handleChange:function (val) {
            // single_price = goods_price * val
            console.log(val)
        },
        handleDelete(index, row) {
            console.log(index, row);
            this.cart[row.store_id].splice(index, 1);
            //将对象保存到localStorage中
            // var obj = JSON.stringify(this.cart_list);
            // window.localStorage.setItem('cart_list',obj);
        },
        toggleSelection(key) {
            this.cart[key].splice(0, this.cart[key].length);
            
            //将对象保存到localStorage中
            // var obj = JSON.stringify(this.cart_list);
            // window.localStorage.setItem('cart_list',obj);
        },
        submitCart(key) {
            var orderform = new Object();
            orderform.orderdetail = new Array();
            console.log(key);
            console.log(this.cart[key]);
            // for(var i = 0; i < this.cart[key].length; i++) {
            //     orderform.orderdetail[i] = new Object();
            //     orderform.orderdetail[i].name = this.cart[key][i].name;
            //     orderform.orderdetail[i].number = this.cart[key][i].buy_num;
            //     orderform.orderdetail[i].price = this.cart[key][i].goods_total;
            // }
            for(var i = 0; i < this.check_list.length; i++) {
                orderform.orderdetail[i] = new Object();
                orderform.orderdetail[i].name = this.check_list[i].name;
                orderform.orderdetail[i].number = this.check_list[i].buy_num;
                orderform.orderdetail[i].price = this.check_list[i].goods_total;
                
            }
            let a = new Set(this.check_list);
            let b = new Set(this.cart[key]); 
            // ab差集
            let differenceABSet = new Set([...b].filter(x => !a.has(x)));
            let arr = Array.from(differenceABSet);
            this.cart[key] = arr;
            console.log(this.cart[key]);
            
            console.log(orderform);
            this.$http.post("http://localhost:8080/api/orderform?storeId=" + key,  orderform).then((response) => {
                   
                   console.log(response);
                    this.orderformId = response.body.id;
                    this.$router.push({name:'shopOrder',  params: {oid:this.orderformId}})
                    
                },
                (error) => {
                    console.log(error);
                }
                );
            }
    }
  };
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
    .shopCart
        .stepStyle
            background-color: #fffce3
        width: 1024px
        margin: 0 auto
        background-color: #fafafa
        .menuTop
            .menuBread
                margin-left: 1rem
                line-height: 1.8rem
                height: 2rem
        .shopMain
            width: 100%
            background-color: #ffffff
            border: 3px solid #f9e4e7
            border-radius: 5px
            box-shadow: 10px 10px 5px #888888
            .shopButton
                display: flex
                margin: 1rem 3rem 0rem 1rem
                justify-content: space-between
        .cartTop
            display: flex
            justify-content: space-between
            width: 984px
            padding: 20px
            margin-top: 1rem
            color: #ff2d48
            a
                margin-right:2.5rem
            

</style>
