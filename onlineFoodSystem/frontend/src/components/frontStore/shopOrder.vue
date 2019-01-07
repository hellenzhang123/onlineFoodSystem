<template>
  <div>
  <div class="shopOrder" v-show="backhtml == ''">
    <div class="menuTop">
        <el-breadcrumb separator=">" class="menuBread">
        <el-breadcrumb-item :to="{ path: '/front/frontStore' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>购物车</el-breadcrumb-item>
        </el-breadcrumb>
    </div>
    <div>
        <el-steps :active="1" finish-status="success" simple class="stepStyle">
        <el-step title="加入购物车" ></el-step>
        <el-step title="确认订单" ></el-step>
        <el-step title="进行支付" ></el-step>
        </el-steps>
    </div>
    <div class="main">
        <div class="left">
            <div class="first">
                <span class="firstSpan">订单详情</span>
                <span><router-link to="/front/shopCart">返回购物车修改</router-link></span>
            </div>
            <div>
                <el-table :data="orderdetail" width="100%">
                    <el-table-column label="商品名称" prop="name" align="center"></el-table-column>
                    <el-table-column label="购买数量"  align="center" prop="number">
                            <!-- <template slot-scope="scope"><div class="div_number">
                                <el-input-number :min="1" @change="handleChange" size="mini" v-model="scope.row.buy_num"></el-input-number></div>
                            </template> -->
                    </el-table-column>
                    <el-table-column label="小计" prop="price"  align="center">
                        <!-- <template slot-scope="scope">{{ scope.row.date }}</template> -->
                    </el-table-column>
                </el-table>
            </div>
            <div class="peisong">
                <span><i class="el-icon-info"></i>&nbsp;配送费</span>
                <span>￥{{deliveryPrice}}</span>
            </div>
            <div class="total">
                合计： {{total_price}}元
            </div>
        </div>
        <div class="right">
            <div class="container">
                <div>
                    <div class="rightBlock">
                        <span class="firstSpan">收货地址</span>
                        <span @click="handleEdit()">添加收货地址</span>
                    </div>
                    <div class="second" v-if="!isShowMore && oldaddress.length > 0">
                        <div>
                            <img src="/static/image/position.png" class="imgItem">
                        </div>
                        <div class="inner">
                            <div>{{oldaddress[0].name}}{{oldaddress[0].gender}} {{oldaddress[0].phone}}</div>
                            <div>{{oldaddress[0].info}}</div>
                        </div>
                    </div>
                    <div class="second" v-if="isShowMore" v-for="(item, index) in oldaddress" @click="handleAddress(item)"> 
                        <div>
                            <img src="/static/image/position.png" class="imgItem">
                        </div>
                        <div class="inner" >
                            <div>{{item.name}}{{item.gender}} {{item.phone}}</div>
                            <div>{{item.info}}</div>
                        </div>
                    </div>
                </div>
                <div class="third" v-if="oldaddress.length > 1 && !isHide" @click="isShowMore = true, isHide = true">显示更多地址<i class="el-icon-arrow-down el-icon--right"></i></div>
                 <div class="third" v-if="isHide" @click="isShowMore = false, isHide = false">收起<i class="el-icon-arrow-up el-icon--right"></i></div>
                <div>
                    <div class="firstSpan rightBlock">付款方式</div>
                    <div class="rightBox">
                         <el-radio-group v-model="payway">
                            <div><el-radio label="在线支付" class="text">在线支付</el-radio></div>
                            <div class="boxInner">支持支付宝、微信、银行卡等多种支付方式</div>
                            <div><el-radio label="线下支付" class="text">线下支付</el-radio></div>
                            <div class="boxInner">自备零钱，不设找零</div>
                        </el-radio-group>
                        
                    </div>
                </div>
                <div>
                    <div class="firstSpan rightBlock">其他信息</div>
                    <div >
                        <div>订单备注：
                            <el-input class="botom"
                            type="textarea"
                            :rows="2"
                            placeholder="请输入内容"
                            v-model="note">
                            </el-input>
                        </div>
                    </div>
                </div>
                <el-button type="primary" class="botom bott" @click="submitOrder()">去结算</el-button>
            </div>
        </div>
    </div>
    <div class="address-form">
    <el-dialog
      title="添加新地址"
       :visible.sync="editAddressFormVisible.visible"
      width="50%">
      <span>
        <div class="addAddressDialog">
          <el-form :model="address" :rules="rules" ref="addressForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="address.name"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
              <el-radio-group v-model="address.gender">
                <el-radio label="先生"></el-radio>
                <el-radio label="女士"></el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="详细地址" prop="info">
               <el-input v-model="address.info"></el-input>
            </el-form-item>
            <el-form-item label="手机号" prop="phone">
               <el-input v-model="address.phone"></el-input>
            </el-form-item>
              <div class="form-btn-group">
                <el-button type="primary"
                           @click="submitForm('addressForm')">确认添加</el-button>
                <el-button @click="editAddressFormVisible.visible =false">取消</el-button>
              </div>
          </el-form>
        </div>
      </span>
    </el-dialog>
  </div> 
  
 </div> 
 <div id="test">
    
 </div>
  </div>
 
</template>
<script type="text/ecmascript-6">
 import addressForm from './addressForm';
  export default {
    data(){
        return  {
            oid: 0,
            oldaddress:[],
            selectAddress: {},
            orderform:{},
            isShowMore: false,
            isHide: false,
            user:{},
            address: {
                name:'',
                info:'',
                gender:'',
                phone:''
            },
            note:'',
            payway:'',
            rules: {
                name: [
                    { required: true, message: '请输入您的姓名', trigger: 'blur' },
                    { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
                ],
                info:[
                    { required: true, message: '请输入地址信息', trigger: 'blur' }
                
                ],
                gender: [
                    { required: true, message: '请选择您的性别', trigger: 'change' }
                ],
                phone: [
                    { required: true, message: '请输入您的电话', trigger: 'blur' },
                    { pattern: /^1[34578]\d{9}$/, message: '目前只支持中国大陆的手机号码' }
                ]
            },
            orderdetail: [],
            deliveryPrice: 0,
            editAddressFormVisible: {
                visible: false
            },
            chooseAddressFormVisible: {
                visible: false
            },
            backhtml:'',
        }
    },
    created(){
        this.oid = this.$route.params.oid;
        console.log(this.oid);
        // var oid = this.$router.params.oid;
        // console.log(oid);
        this.$http.get('http://localhost:8080/api/orderform/' + this.oid).then((response) => {
            console.log(response)
            this.deliveryPrice = response.body.store.deliveryPrice;
            this.orderform = response.body;
            this.orderdetail = response.body.orderdetail;
            this.oldaddress = response.body.user.address;
            if (this.oldaddress.length > 0) {
                this.selectAddress = this.oldaddress[0];
            }
            this.user = response.body.user;
            console.log(this.orderdetail);
            // 响应成功回调
           
        }, (response) => {
            // 响应错误回调
        });
        
    },
    computed:{
        total_price : function () {
            let sum_price = 0
            this.orderdetail.forEach((item)=>{
                sum_price += item.price
            })
            sum_price += this.deliveryPrice;
            return sum_price
        }
    },
    methods:{
        handleEdit() {
            this.editAddressFormVisible.visible = true;
            
            },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
            if (valid) {
                this.$message.success("添加地址成功");
                this.editAddressFormVisible.visible = false;
                this.user.address.unshift(this.address);
                // console.log(this.user);
                // console.log(this.user.address);
                this.$http.put('http://localhost:8080/api/user/' + this.user.id, this.user).then((response) => {

                    console.log(response)
                }, (response) => {
                    // 响应错误回调
                });
                // bus.$emit('editSuccess',this.good);
            } else {
                console.log('error submit!!');
                return false;
            }
            });
        },
        handleAddress(val) {
            this.selectAddress = val;
        },
        submitOrder() {
            console.log(this.payway);
            console.log(this.note);
            if (this.payway == '') {
                this.$message.error("请选择支付方式");
            } else if (this.selectAddress == null) {
                this.$message.error('请选择收货地址');
            } else {
                this.orderform.payWay = this.payway;
                this.orderform.note = this.note;
                this.orderform.address = this.selectAddress.name + this.selectAddress.gender + this.selectAddress.info + this.selectAddress.phone;
                this.orderform.totalPrice = this.total_price;
                this.orderform.deliveryPrice = this.deliveryPrice;
                console.log(this.selectAddress);
                console.log(this.orderform);
                 this.$http.put('http://localhost:8080/api/orderform/' + this.oid, this.orderform).then((response) => {

                    console.log(response)
                }, (response) => {
                    // 响应错误回调
                });
                if (this.payway == '在线支付') {
                    this.$http.post('http://localhost:8080/api/aplipay?out_trade_no=' + this.orderform.id + '&&total_amount=' + this.orderform.totalPrice + '&&subject=test&&body=test').then((response) => {

                    console.log(response)
                   
                    var bhtml = response.bodyText;
                    document.getElementById("test").innerHTML = bhtml;
                    document.forms[0].submit();
                    // console.log(bhtml);
                    // var index = bhtml.indexOf('<script>');
                    // console.log(index);
                    // var beforeCunt = bhtml.substring(0,index);
                    //  this.backhtml = beforeCunt;
                    // console.log(beforeCunt);
                    // var cut = bhtml.substring(index);
                    // console.log(cut);
                    // document.createElement(cut);
                    // script.type = 'text/javascript';
                    // script.src = 'http://xxx.xxx';
                    // document.head.appendChild(script);


                    }, (response) => {
                        // 响应错误回调
                    });
                } else {
                    this.$router.push({path: '/front/orderResult'})
                }
                
            }
        }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus" scoped>
    .shopOrder
        .botom
            margin-top: 1rem
        .bott
            float: right
        .form-btn-group {
            text-align center
        }
        .el-radio
            .el-radio__label
                font-size: 1rem
        .stepStyle
            background-color: #fffce3
        width: 1024px
        margin: 0 auto
        .menuTop
            .menuBread
                margin-left: 1rem
                line-height: 1.8rem
                height: 2rem
        .main
            .firstSpan
                font-size: 25px
            margin-top: 1rem
            
            display: flex
            justify-content: space-between
            .left
                width: 40%
                border: 1px solid #f7f7f7
                background-color: #ffffff
                .first
                    display: flex
                    flex-direction: row
                    justify-content: space-between
                    margin: 0rem 1rem
                    height: 3rem;
                    line-height: 3rem;
                .peisong
                    display: flex;
                    justify-content: space-between;
                    padding: 1rem;
                .total
                    display: flex
                    justify-content: flex-end
                    padding-right: 1rem
            .right
                width: 58%
                border: 1px solid #f7f7f7
                background-color: #ffffff
                .container
                    .second
                        display: flex
                        height: 4rem
                        border: 1px solid #e0e0e0
                        margin-bottom: 1rem
                        img
                            width: 2rem
                            height: 2rem
                            margin: 1rem 2rem 0rem 1rem
                            border-right: 1px solid #f5f5f5
                        .inner
                            line-height: 2rem
                    margin: 0rem 1rem
                    .third
                        margin-top: 1rem
                        font-size: 16px
                        color: #a5a5a5
                    .rightBox
                        border: 1px solid #e0e0e0
                        height: 5rem
                        padding: 0.5rem 1rem
                        lint-height: 1.5rem
                        
                        .boxInner
                            margin-top: 0.5rem
                            font-size: 16px
                            color: gray
                            margin-left: 1.5rem
                            margin-bottom: 0.5rem
                    .rightBlock
                        margin: 1rem 0rem;
                        display: flex;
                        justify-content: space-between;
                        
            
</style>