<template>
  <div class="seller">
    <div class="toolBar">
      <el-button type="primary" size="mini" @click="addMeterail">新增</el-button>
      <el-button size="mini" @click="deleteMeterial()">删除</el-button>
    </div>
    <div class="seller-table">
      <el-table
        v-loading="loading"
        element-loading-text="拼命加载中"
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="80">
        </el-table-column>
        <el-table-column
          prop="name"
          label="材料名称"
          width="150">
        </el-table-column>
        <el-table-column
          label="材料图片"
          width="150">
          <template scope="scope"><img :src="'http://localhost:8080/' + scope.row.img" width="100" height="100"></template>
        </el-table-column>
        <el-table-column
          prop="number"
          label="购买数量"
          width="150">
        </el-table-column>
        <el-table-column
          prop="orderDate"
          label="预订时间"
          width="150">
        </el-table-column>
        <el-table-column
          prop="sellDate"
          label="到货时间"
          width="150">
        </el-table-column>
        <el-table-column
          prop="saveMethod"
          label="储存方法"
          width="200">
        </el-table-column>
        <el-table-column label="操作">
          <template scope="scope">
            <el-button
              size="small"
              @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog
      title="新增商品"
      :visible.sync="addMeterialVisible"
      width="50%">
      <span>
        <div class="addMeterialDialog">
          <el-form :model="addMeterialForm" :rules="rules" ref="addMeterialForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="材料名称" prop="addName">
              <el-input v-model="addMeterialForm.addName"></el-input>
            </el-form-item>
            <el-form-item label="预订时间" prop="addOrderDate">
              <el-date-picker
                v-model="addMeterialForm.addOrderDate"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
              <!-- <el-input v-model="selectedClassify.name" readonly></el-input> -->
            </el-form-item>
            <el-form-item label="上传图片" prop="img">
              <el-upload
                class="avatar-uploader"
                action="http://localhost:8080/api/upload"
                :show-file-list="false"
                :on-success="handleAvatarSuccess">
                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
            <el-form-item label="预订数量" prop="addNumber">
               <el-input v-model.number="addMeterialForm.addNumber"></el-input>
            </el-form-item>
            <el-form-item label="到货时间" prop="addSellDate">
              <el-date-picker
                v-model="addMeterialForm.addSellDate"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
               <!-- <el-input v-model="addMeterialForm.addPhone"></el-input> -->
            </el-form-item>
            <el-form-item label="储存方法" prop="addSaveMethod">
               <el-input type="textarea" v-model="addMeterialForm.addSaveMethod"></el-input>
            </el-form-item>
              <div class="form-btn-group">
                <el-button type="primary"
                           @click="submitForm('addMeterialForm')">确认新增</el-button>
                <el-button @click="clearForm('addMeterialForm'),addMeterialVisible =false,imageUrl = ''">取消</el-button>
              </div>
          </el-form>
        </div>
      </span>
    </el-dialog>
    <el-dialog
      title="编辑商品"
      :visible.sync="editMeterialVisible"
      width="50%">
      <span>
        <div class="editMeterialDialog">
          <el-form :model="editMeterialForm" :rules="rules" ref="editMeterialForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="材料名称" prop="name">
              <el-input v-model="editMeterialForm.name"></el-input>
            </el-form-item>
            <el-form-item label="预订时间" prop="orderDate">
              <el-date-picker
                v-model="editMeterialForm.orderDate"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
              <!-- <el-input v-model="editMeterialForm.orderDate" readonly></el-input> -->
            </el-form-item>
            <el-form-item label="上传图片" prop="img">
              <el-upload
                class="avatar-uploader"
                action="http://localhost:8080/api/upload"
                :show-file-list="false"
                :on-success="handleAvatarSuccess">
                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
            <el-form-item label="预订数量" prop="number">
               <el-input v-model.number="editMeterialForm.number"></el-input>
            </el-form-item>
            <el-form-item label="到货时间" prop="sellDate">
              <el-date-picker
                v-model="editMeterialForm.sellDate"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
               <!-- <el-input v-model="editMeterialForm.sellDate"></el-input> -->
            </el-form-item>
            <el-form-item label="储存方法" prop="saveMethod">
               <el-input type="textarea" v-model="editMeterialForm.saveMethod"></el-input>
            </el-form-item>
              <div class="form-btn-group">
                <el-button type="primary"
                           @click="submitEditForm('editMeterialForm')">确认修改</el-button>
                <el-button @click="clearForm('editMeterialForm'),editMeterialVisible =false,imageUrl = ''">取消</el-button>
              </div>
          </el-form>
        </div>
      </span>
    </el-dialog>
    <el-dialog
      title="删除原材料"
      :visible.sync="deleteVisible"
      width="30%">
      <span>确认删除原材料【<span v-for="(good,index) in multipleSelection">{{good.name}} </span>】吗</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="deleteVisible = false">取 消</el-button>
    <el-button type="primary" @click="confirmDel">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script type="text/ecmascript-6">

  export default {
      data() {
        return {
          editMeterial:{},
          loading: true,
          tableData: [],
          addMeterialVisible:false,
          editMeterialVisible:false,
          deleteVisible:false,
          addMeterialForm: {
          addName: '',
          addOrderDate: '',
          addNumber: 0,
          addSellDate: '',
          addSaveMethod:''
        },
        multipleSelection:[],
        imageUrl:'',
        editMeterialForm: {
          name: '',
          orderDate: '',
          number: 0,
          sellDate: '',
          saveMethod:''
        },
         rules: {
          addName: [
            {required: true, message: '请输入商品名称', trigger: 'change'}
          ],
          addOrderDate: [
            {required: true, message: '请输入预订时间'},
          ],
          addNumber:[
            {required: true, message: '请输入预订数量'},
            {type: 'number', message: '价格必须是数字'}
          ],
          addSellDate:[
            {required: true, message: '请输入购买时间'},
          ],
          addSaveMethod:[
            {required: true, message: '请输入储存方式'},
          ]
        },
        }
      },
      created: function () {
        this.$http.get('http://localhost:8080/api/meterial/list?storeId=1').then(response => {
          let data = response.body.content;
          console.log(data);
          this.tableData = data;
          console.log(this.tableData);
          this.loading = false;
        }, response => {
          // error callback
        });
      },
      methods: {
        handleSelectionChange(val) {
          this.multipleSelection = val;
        },
        handleAvatarSuccess(res, file) {
        console.log(res);
        console.log(file);
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      addMeterail() {
        this.addMeterialVisible = true;
      },
      clearForm(formsname) {
        console.log(this.$refs[formsname]);
        this.$refs[formsname].resetFields();
      },
      submitForm(formsname) {
        console.log(this.$refs[formsname]);
        this.$refs[formsname].validate((valid) => {
          if (valid) {
            var meterial = new Object();
            meterial.name = this.addMeterialForm.addName;
            meterial.orderDate = this.addMeterialForm.addOrderDate;
            meterial.number = this.addMeterialForm.addNumber;
            meterial.sellDate = this.addMeterialForm.addSellDate;
            meterial.saveMethod = this.addMeterialForm.addSaveMethod;
            meterial.img = this.imageUrl;
            console.log(this.tableData);
            console.log(valid);
            this.$http.post("http://localhost:8080/api/meterial?storeId=1",  meterial).then((response) => {
                   console.log('success');
                   console.log(response)
                   this.tableData.unshift(response.body);
                },
                (error) => {
                    console.log(error);
                }
                );
            this.$message.success("新增原材料成功");
            this.addMeterialVisible = false;
            this.clearForm('addMeterialForm');
            this.imageUrl = '';
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      handleEdit(index, row) {
        console.log(index, row);
        this.editMeterialVisible = true;
        this.editMeterialForm = row;
        console.log(row);
      },
      submitEditForm(formsname) {
        console.log(this.$refs[formsname]);
        this.$refs[formsname].validate((valid) => {
          if (valid) {
            console.log(this.editMeterialForm);
            console.log(this.tableData);
            console.log(valid);
            this.editMeterialForm.img = this.imageUrl;
            this.$http.put("http://localhost:8080/api/meterial/" + this.editMeterialForm.id,  this.editMeterialForm).then((response) => {
                  console.log('success');
                  console.log(response)
                  for(var i = 0; i < this.tableData.length; i++) {
                    if (this.tableData[i].id == this.editMeterialForm.id) {
                      this.tableData[i] = this.editMeterialForm;
                      break;
                    }
                  }
                  this.editMeterialVisible = false;
                  this.$message.success("修改原材料成功");
                  this.clearForm('editMeterialForm');
                  this.imageUrl = '';
                },
                (error) => {
                    console.log(error);
                }
                );
           
          } else {
            console.log('error submit!!');
            return false;
          }
        });
        
      },
      deleteMeterial() {
        if (this.multipleSelection.length === 0) {
          this.$message.error('请先选中要删除的商品');
          return;
        }
        this.deleteVisible = true;
      },
      confirmDel() {
        console.log(this.multipleSelection);
          var ids = new Array();
          this.multipleSelection.forEach(element => {
             ids.push(element.id);
          });
          
         console.log(ids);
        this.$http.delete('http://localhost:8080/api/meterial',{body: ids}).then(response => {
          console.log(response);
          let a = new Set(this.multipleSelection);
          let b = new Set(this.tableData); 
          // ab差集
          let differenceABSet = new Set([...b].filter(x => !a.has(x)));
          let arr = Array.from(differenceABSet);
          console.log(arr);
          this.tableData = arr;
        }, response => {
        });
        // } else {
        //   var id = this.multipleSelection[0].id;
        //   console.log(this.multipleSelection);
        //   console.log(id);
        //   this.$http.delete('http://localhost:8080/api/food/' + id).then(response => {
        //   console.log(response);
          
        // }, response => {
        // });
        // }
        
        this.deleteVisible = false;
      },
      }
  };
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .seller
    margin 1.5rem 0 0 1.5rem
    .toolBar
      margin-bottom 1rem
</style>
