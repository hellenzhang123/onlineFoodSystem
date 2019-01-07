<template>
    <div class="myOrder">
        <div class="menuTop">
            <el-breadcrumb separator=">" class="menuBread">
            <el-breadcrumb-item :to="{ path: '/front/frontStore' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>个人中心</el-breadcrumb-item>
            <el-breadcrumb-item>我的订单</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="main">
            <div class="mainLeft">
                <el-menu
                    default-active="2"
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
                <div class="order">
                    近三个月订单
                </div>
                <div>
                    <el-table
                        :data="tableData"
                        style="width: 100%"
                       >
                       <el-table-column type="expand">
          <template scope="props">
            <div class="expandTitle">
              <el-row>
                <el-col :span="13">
                  <h1 class="goodTitle">商品详情</h1>
                </el-col>
                <el-col :span="3">
                  <h1 class="totalTitle">
                    合计
                  </h1>
                </el-col>
                <el-col :span="4">
                  <h1 class="remarkTitle">
                    备注
                  </h1>
                </el-col>
                <el-col :span="4">
                  <h1 class="addressTitle">
                    地址
                  </h1>
                </el-col>
              </el-row>
            </div>
            <el-row class="expandDetail">
              <el-col :span="13">
                <el-table
                  :data="props.row.orderdetail"
                  border
                  style="width: 100%">
                  <el-table-column
                    label="商品"
                    width="100">
                    <template scope="scope">
                      <span>{{scope.row.name}}</span>
                    </template>
                  </el-table-column>
                  
                  <el-table-column
                    label="购买数量">
                    <template scope="scope">
                      <p>{{scope.row.number}}</p>
                    </template>
                  </el-table-column>
                  <el-table-column
                    label="单个总价">
                    <template scope="scope">
                      <p style="color: #f36a5a">￥{{scope.row.price}}</p>
                    </template>
                  </el-table-column>
                </el-table>
              </el-col>
              <el-col :span="3">
                <div class="totalpriceWrapper">
                  <div class="totalprice">
                    ￥{{goodTotalPrice(props.row.orderdetail)}}
                    <!-- ￥{{props.row.totalprice}} -->
                  </div>
                  <div class="deliverCount">
                    ( 不含配送费: ￥{{props.row.deliveryPrice}} )
                  </div>
                  <div class="count">
                    共{{props.row.orderdetail.length}}件
                  </div>
                </div>
              </el-col>
              <el-col :span="4">
                <div class="remark">
                  {{props.row.note}}
                </div>
              </el-col>
              <el-col :span="4">
                <div class="address">
                  {{props.row.address}}
                </div>
              </el-col>
            </el-row>
          </template>
        </el-table-column>
                        <el-table-column
                            prop="createdAt"
                            label="下单时间"
                            width="180" header-align="center" align="center">
                        </el-table-column>
                        <el-table-column
                            prop="orderContent"
                            label="订单内容"
                            width="180" header-align="center" align="center">
                        </el-table-column>
                        <el-table-column
                            prop="totalPrice"
                            label="支付金额（元）" header-align="center" align="center">
                        </el-table-column>
                        <el-table-column
                            prop="state"
                            label="状态" header-align="center" align="center">
                        </el-table-column>
                        <el-table-column
                            label="操作" header-align="center" align="center">
                            <template slot-scope="scope">
                                <el-button plain
                                size="mini"
                                type="danger"
                               >删除订单</el-button>
                            </template>
                        </el-table-column>

                    </el-table>
                    <div class="bottom">
                        <el-pagination
                        background
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page.sync="currentPage"
                        :page-size="limit"
                        layout="prev, pager, next"
                        :total="totalCount">
                        </el-pagination>
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
            tableData : [],
            totalCount:0,
            tableData: [],
            currentPage: 1,
            limit: 6,
        }
    },
    created() {
       this.$http.get('http://localhost:8080/api/orderform/list').then((response) => {
            this.totalData = response.body.content;
            this.totalCount = response.body.totalElements
            this.totalData.forEach((item) => {
                if (item.state == 0) {
                   item.state = '等待接单'
                } else if (item.state == 1) {
                   item.state = '已完成'
                } else {
                   item.state = '未完成'
                }
                item.orderdetail.forEach((content) => {
                    var text = '';
                    text += content.name;
                    item.orderContent = text;
                });
           });
           this.tableData = this.totalData.slice(0, 0 + this.limit);
        }, (response) => {
        });
    },
    methods:{
        handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            let pageStart = (val-1) * this.limit;
            this.tableData = this.totalData.slice(pageStart, pageStart + this.limit);
        },
        goodTotalPrice(goods) {
        let count = 0;
        goods.forEach((good) => {
          count += good.price;
        });
        return count;
      },
    }
  };
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
    .myOrder
        width: 1024px
        margin: 0 auto
        .menuTop
            .menuBread
                margin-left: 1rem
                line-height: 1.8rem
                height: 2rem
        .main
            display: flex
            justify-content: space-between
            .mainLeft
                width: 20%
                background-color: #fafafa
            .mainRight
                width: 78%
                background-color: #ffffff
                border: 1px solid #e0e0e0
                border-border-radius: 5px
                .order
                    padding: 1rem 2rem
                    border-bottom: 1px solid #e0e0e0
                    color: #000000
                    font-font-weight: bold
                .bottom
                    display: flex
                    justify-content: center
                    margin-top: 1rem 
        .expandTitle
            text-align center
            padding 0.5rem 0
            font-size 0.8rem
            font-weight bold
            border-bottom 1px solid #e9e9e9
        .expandDetail
            border-bottom 1px solid #e9e9e9
        .expandWrpper
            .good-icon
                margin 0.5rem 0
                width 3rem
                height 3rem
            .goodname
                margin 0.5rem 0
            .goodprice
                margin 0.5rem 0
                text-align right
            .price
                margin-bottom 0.3rem
        .totalpriceWrapper
            text-align center
            margin-top 1rem
            .totalprice
                color: #f36a5a
                font-size: 0.8rem
                font-family: Arial
            .deliverCount
                font-size 0.6rem
                margin 0.2rem 0
            .count
                font-size 0.6rem
        .remark
            text-align center
            margin-top 1rem
        .address
            text-align center
            margin-top 1rem


</style>