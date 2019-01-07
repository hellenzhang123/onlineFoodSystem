<template>
  <div class="order">
    <div class="toolBar">
      <el-button size="small">打印订单</el-button>
    </div>
    <div class="order-bd-top"></div>
    <div class="order-table">
      <el-table
        v-loading="loading"
        element-loading-text="拼命加载中"
        :data="tableData"
        @selection-change="handleSelectionChange"
        style="width: 100%">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
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
                    width="300">
                    <template scope="scope">
                      <span>{{scope.row.name}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column
                    label="单价">
                    <template scope="scope">
                      <p style="color: #f36a5a">￥{{scope.row.price}}</p>
                    </template>
                  </el-table-column>
                  <el-table-column
                    label="购买数量">
                    <template scope="scope">
                      <p>{{scope.row.number}}</p>
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
        <el-table-column label="操作">
          <template scope="scope">
            <transition name="fade" mode="out-in">
              <el-button @click="distributeTask(scope.$index, scope.row)" v-if="scope.row.state == '已接收'" type="primary" size="mini">分配骑手</el-button>
              <el-button v-if="scope.row.state == '未接单'"
                         size="mini"
                         type="success"
                         @click="accept(scope.$index, scope.row)"><i class="el-icon-check"></i>接单</el-button>
            </transition>
            <el-button v-if="scope.row.state == '未接单'"
                       size="mini"
                       type="danger"
                       @click="refuse(scope.$index, scope.row)"><i class="el-icon-close"></i>拒绝</el-button>
            <span v-if="scope.row.refuseMsg">理由：{{scope.row.refuseMsg}}</span>
            <span v-if="scope.row.rider"><strong>正在由骑手{{scope.row.rider}}配送</strong></span>
          </template>
        </el-table-column>
        <el-table-column
          label="订单号"
          prop="id">
        </el-table-column>
        <el-table-column
          label="买家">
          <template scope="scope">
              <img :src="'http://localhost:8080/' + scope.row.user.avatar" width="50px" height="50px" style="border-radius: 50%">
              <span>{{scope.row.user.name}}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="手机号"
          >
          <template scope="scope">
              <span>{{scope.row.user.phone}}</span>
          </template>
        </el-table-column>
        <!-- <el-table-column
          label="预约时间"
          prop="requireTime">
        </el-table-column> -->
        <el-table-column
          label="下单时间"
          prop="createdAt">
        </el-table-column>
        <el-table-column
          label="处理状态"
          prop="state">
          <template scope="scope">
            <el-tag v-if="scope.row.state === '未接单'" type="info">{{scope.row.state}}</el-tag>
            <el-tag v-if="scope.row.state === '正在配送中...'" type="success">{{scope.row.state}}</el-tag>
            <el-tag v-if="scope.row.state === '已接收'">{{scope.row.state}}</el-tag>
            <el-tag v-if="scope.row.state === '已拒绝'" type="danger">{{scope.row.state}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          label="支付方式"
          prop="payWay">
        </el-table-column>
      </el-table>
      <select-rider ref="selectRider" @distributed="distributed"></select-rider>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import selectRider from './selectRider';

  export default {
    data() {
      return {
        multipleSelection: [],
        tableData: [],
        loading: true
      }
    },
    created() {
      this.$http.get('http://localhost:8080/api/orderform/store?storeId=1').then(response => {
        console.log(response);
        // get body data
        
        this.tableData = response.body;
        this.loading = false;
      }, response => {
        // error callback
      });
    },
    activated() {
      this.clearNew();
    },
    methods: {
      clearNew() {
        this.$store.state.newOrder = 0;
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      goodTotalPrice(goods) {
        let count = 0;
        goods.forEach((good) => {
          count += good.price * good.number;
        });
        return count;
      },
      accept(index,row) {
        row.state = '已接收';
        this.$http.put('http://localhost:8080/api/orderform/' + row.id, row).then(response => {
          console.log(response);
          this.$message.success('成功接收订单！');
          this.tableData[index].state='已接收';
        }, response => {
           this.$message.error('操作失败');
        });
       
      },
      refuse(index,row) {
        this.$prompt('请输入拒单理由', '提示', {
          cancelButtonText: '取消',
          confirmButtonText: '确定'
        }).then(({ value }) => {
            row.state = '已拒绝';
            row.refuseMsg = value;
            this.$http.put('http://localhost:8080/api/orderform/' + row.id, row).then(response => {
            this.$message.error('已拒绝订单！');
            this.tableData[index].state='已拒绝';
            this.tableData[index].refuseMsg= value;
          }, response => {
            this.$message.error('操作失败');
          });
        });

      },
      distributeTask(index,row) {
        this.$refs.selectRider.showDialog(index);
      },
      distributed(obj) {
        this.tableData[obj.index].state= '正在配送中...';
        this.tableData[obj.index].rider = obj.name
      },
//      tableRowClassName(row, index) {
//        if (row.state === '已拒绝') {
//          return 'refused';
//        } else if (row.state === '已接收') {
//          return 'accepted';
//        } else if (row.state === '正在配送中...') {
//          return 'distribute';
//        }
//        return '';
//      }
    },
    components: {
      selectRider
    }
  };
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .order-bd-top {
    margin 1.5rem 1.5rem 0 0
    height: 4px;
    background: linear-gradient(transparent, transparent) border-box, repeating-linear-gradient(-45deg, #b8635e 0, #b8635e 8px, transparent 0, transparent 16px, #315baf 0, #315baf 24px, transparent 0, transparent 32px) !important;
  }
  .el-table .refused {
    background: #fedddd;
  }

  .el-table .accepted {
    background: #c9e5f5;
  }
  .el-table .distribute {
    background: #e2f0e4;
  }

  .el-table__expanded-cell {
    padding-top 0!important;
  }
  .demo-table-expand {
    font-size: 0;
  }

  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }

  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }

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


  .order
    margin 1.5rem 0 0 1.5rem
    .order-table
      margin 1rem
</style>
