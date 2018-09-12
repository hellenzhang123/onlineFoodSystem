<template>
  <div class="good">
    <el-row>
      <el-col :span="3">
        <div class="classify">
          <div class="classify-title">
            <span class="title">商品分类</span>
          </div>
          <div class="classify-body">
            <ul class="mdui-list">
              <li @click="selectClassify(cls,index)" :class="{active : selectedClassify == cls}"
                  v-for="(cls,index) in classify" class="mdui-list-item mdui-ripple">
                <div class="classify-name">
                  {{cls.name}}
                </div>
                <!--删除和编辑分类-->
                <div class="classify-toolbar">
                  <i class="el-icon-edit" @click="editClassify(cls)"></i>
                  <i class="el-icon-delete" @click="delClassify(cls), deleteClassifyVisible = true"></i>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </el-col>
      <el-col :span="21">
        <div class="goodTable">
          <div class="toolBar">
            <el-input
              size="small"
              placeholder="输入商品名查找"
              prefix-icon="el-icon-search"
              auto-complete="off"
              v-model.trim="searchText">
            </el-input>
            <transition name="fade">
              <el-button v-show="searchText.length > 0" size="mini">搜索</el-button>
            </transition>
            <el-button v-show="selectedClassify.name" @click="addGood" type="primary" size="mini">新增商品</el-button>
            <el-button @click="addClassify" type="primary" size="mini">新增分类</el-button>
            <el-button v-show="multipleSelection.length > 0" @click="deleteGoods" size="mini">删除商品</el-button>
            <el-button v-show="multipleSelection.length > 0" size="mini">批量分类</el-button>
          </div>
          <div class="goodTable">
            <el-table
              v-loading="loading"
              element-loading-text="拼命加载中"
              ref="multipleTable"
              :data="tableData"
              stripe
              tooltip-effect="dark"
              style="width: 100%"
              @selection-change="handleSelectionChange">
              <el-table-column
                type="selection"
                width="55">
              </el-table-column>
              <el-table-column
                label="图片"
                width="120">
                <template scope="scope"><img width="50" height="50" :src="scope.row.smallImg"></template>
              </el-table-column>
              <el-table-column
                prop="name"
                label="商品名"
                width="200">
              </el-table-column>
              <el-table-column
                prop="price"
                label="价格"
                width="120">
              </el-table-column>
              <el-table-column
                prop="sellcount"
                label="销量"
                width="120">
              </el-table-column>
              <el-table-column
                prop="description"
                label="简介"
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
        </div>
      </el-col>
    </el-row>
    <el-dialog
      title="新增商品"
      :visible.sync="addGoodVisible"
      width="50%">
      <span>
        <div class="addGoodDialog">
          <el-form :model="addGoodForm" :rules="rules" ref="addGoodForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="商品名称" prop="addName">
              <el-input v-model="addGoodForm.addName"></el-input>
            </el-form-item>
            <el-form-item label="商品分类" prop="addClassify">
              <el-input v-model="selectedClassify.name" readonly></el-input>
            </el-form-item>
            <el-form-item label="上传图片" prop="imageUrl">
              <el-upload
                class="avatar-uploader"
                action="http://localhost:8080/api/upload"
                :show-file-list="false"
                :on-success="handleAvatarSuccess">
                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
            <el-form-item label="商品价格" prop="addPrice">
               <el-input v-model.number="addGoodForm.addPrice"></el-input>
            </el-form-item>
            <el-form-item label="描述" prop="description">
               <el-input type="textarea" v-model="addGoodForm.description"></el-input>
            </el-form-item>
              <div class="form-btn-group">
                <el-button type="primary"
                           @click="submitForm('addGoodForm')">确认新增</el-button>
                <el-button @click="clearForm('addGoodForm'),addGoodVisible =false,imageUrl = ''">取消</el-button>
              </div>
          </el-form>
        </div>
      </span>
    </el-dialog>

    <el-dialog
      title="删除商品"
      :visible.sync="deleteVisible"
      width="30%">
      <span>确认删除商品【<span v-for="(good,index) in multipleSelection">{{good.name}} </span>】吗</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="deleteVisible = false">取 消</el-button>
    <el-button type="primary" @click="confirmDel">确 定</el-button>
  </span>
    </el-dialog>

    <el-dialog
      title="新增分类"
      :visible.sync="addClassifyVisible"
      width="30%">
      <span>
        <el-form :model="addClassifyForm" :rules="classifyRules" ref="addClassifyForm" label-width="100px"
                 class="demo-ruleForm">
            <el-form-item label="分类名称" prop="addClassifyName">
              <el-input v-model="addClassifyForm.addClassifyName"></el-input>
            </el-form-item>

              <div class="form-btn-group">
                <el-button type="primary" @click="submitClassifyForm('addClassifyForm')">确认新增</el-button>
                <el-button @click="clearForm('addClassifyForm'),addClassifyVisible = false">取消</el-button>
              </div>
          </el-form>
      </span>
    </el-dialog>

    <el-dialog
      title="编辑分类"
      :visible.sync="editClassifyVisible"
      width="30%">
      <span>
        <el-form :model="addClassifyForm" :rules="editClassifyRules" ref="editClassifyForm" label-width="100px"
                 class="demo-ruleForm">
            <el-form-item label="分类名称" prop="addClassifyName">
              <el-input v-model="editClassifyForm.editClassifyName"></el-input>
            </el-form-item>
              <div class="form-btn-group">
                <el-button type="primary" @click="submitEditClassifyForm('editClassifyForm')">确认修改</el-button>
                <el-button @click="editClassifyVisible = false">取消</el-button>
              </div>
          </el-form>
      </span>
    </el-dialog>

    <el-dialog
      title="删除分类"
      :visible.sync="deleteClassifyVisible"
      width="30%">
      <span>确认删除分类{{selectedClassify.name}}吗？</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="deleteClassifyVisible = false">取 消</el-button>
    <el-button type="primary" @click="confirmDelClassify">确 定</el-button>
  </span>
    </el-dialog>
    <!--编辑商品组件-->
    <good-form :editGoodFormVisible="editGoodFormVisible" :editGood="editGood"></good-form>
  </div>
</template>

<script type="text/ecmascript-6">
  import goodForm from './goodForm';
  import {bus} from '../../bus';

  export default {
    data() {
      return {
        loading: true,
        storeId:0,
        goodsData: [],
        tableData: [],
        multipleSelection: [],
        classify: [],
        selectedClassify: {},
        searchText:'',
        addGoodVisible: false,
        deleteVisible: false,
        addClassifyVisible: false,
        deleteClassifyVisible: false,
        deletedClassify:{},
        editClassifyVisible: false,
        editGoodFormVisible: {
          visible: false
        },
        editGood: {},
        editImageUrl: "",
        imageUrl: '',
        addGoodForm: {
          addName: '',
          addClassify: '',
          addPrice: '',
          description: '',
        },
        addClassifyForm: {
          addClassifyName: ''
        },
        editClassifyForm: {
          editClassifyName: ''
        },
        rules: {
          addName: [
            {required: true, message: '请输入商品名称', trigger: 'change'}
          ],
          addPrice: [
            {required: true, message: '请输入商品价格'},
            {type: 'number', message: '价格必须是数字'}
          ]
        },
        classifyRules: {
          addClassifyName: [
            {required: true, message: '请输入分类名称', trigger: 'change'}
          ]
        },
        editClassifyRules: {
          editClassifyName: [
            {required: true, message: '请输入分类名称', trigger: 'change'}
          ]
        }
      }
    },
    created: function () {
      this.storeId = 1;
      // this.$http.get('http://localhost:8080/api/store/' + id)
      // this.$http.get('/static/data.json').then(response => {
      this.$http.get('http://localhost:8080/api/sort/list').then(response => {
        // get body data
        let data = response.body;
        console.log(data);
        // data.forEach((good) => {
        //   this.classify.push(good.name);
        // });
        this.classify = data;
        console.log(this.classify);
      }, response => {
        // error callback
      });
      this.$http.get('http://localhost:8080/api/store/1').then(response => {
        // get body data
        let data = response.body;
        console.log(data);
        this.tableData = data.food;
        this.tableData.forEach(element => {
          element.smallImg = 'http://localhost:8080/' + element.smallImg;
        });
        this.loading = false;
      }, response => {
        // error callback
      });
      bus.$on('editSuccess',(good) => {
        console.log(this.tableData);
        for(var i = 0; i < this.tableData.length; i++) {
          if (this.tableData[i].id == good.id) {
            console.log('11111');
            console.log(this.tableData[i]);
            console.log(good);
            this.tableData[i].push(good);
          }
        }
        this.$http.put("http://localhost:8080/api/food/" + good.id,  good).then((response) => {
          console.log(response);
          console.log('success');
        },
        (error) => {
            console.log(error);
        }
        );
      })
    },
    watch: {
      searchText: {
        handler(text) {
          if (text.length === 0){
            alert('重置');
          }
        }
      }
    },
    methods: {
      selectClassify(cls, index) {
        console.log(cls.id);
        this.$http.get('http://localhost:8080/api/food/list?sortId=' + cls.id).then(response => {
          this.tableData  = response.body.content;
          this.tableData.forEach(element => {
            element.smallImg = 'http://localhost:8080/' + element.smallImg;
          });
          this.selectedClassify = cls;
        }, response => {
        });
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handleEdit(index, row) {
        console.log(index, row);
        this.editGoodFormVisible.visible = true;
        console.log(this.editGood);
        this.editGood = row;
      },
      deleteGoods() {
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
        this.$http.delete('http://localhost:8080/api/food',{body: ids}).then(response => {
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
        
        this.deleteVisible = false;
      },
      clearForm(formName) {
        this.$refs[formName].resetFields();
      },
      addGood() {
        if (!this.selectedClassify.name) {
          this.$message.error("请先选中新增商品的分类");
          return;
        }
        this.addGoodVisible = true;
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            var food = new Object();
            food.name = this.addGoodForm.addName,
            food.smallImg = this.imageUrl,
            food.price = this.addGoodForm.addPrice,
            food.sort = this.selectedClassify,
            food.description = this.addGoodForm.description,
            
            console.log(this.tableData);
            console.log(food);
            this.$http.post("http://localhost:8080/api/food?storeId=" + this.storeId,  food).then((response) => {
                   console.log('success');
                   console.log(response)
                   this.tableData.unshift(response.body);
                },
                (error) => {
                    console.log(error);
                }
                );
            this.$message.success("新增商品成功");
            this.addGoodVisible = false;
            this.clearForm('addGoodForm');
            this.imageUrl = '';
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      submitClassifyForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            var classifyObj = new Object();
            classifyObj.name = this.addClassifyForm.addClassifyName;
            this.$http.post("http://localhost:8080/api/sort?storeId=1",  classifyObj).then((response) => {
              console.log(response);
              classifyObj.id = response.body.id
              console.log(classifyObj)
              this.classify.push(classifyObj);
              this.$message.success("新增分类成功");
              this.addClassifyVisible = false;
                  console.log('success');
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
      submitEditClassifyForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log(this.editClassifyForm)
            console.log(this.selectedClassify)
            this.selectedClassify.name = this.editClassifyForm.editClassifyName;
            this.$http.put("http://localhost:8080/api/sort/" + this.selectedClassify.id,  this.selectedClassify).then((response) => {
             
            this.$message.success("修改分类成功");
            this.editClassifyVisible = false;
              console.log('success');
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
      handleAvatarSuccess(res, file) {
        console.log(res);
        console.log(file);
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      addClassify() {
        this.addClassifyVisible = true;
      },
      delClassify(cls) {
        this.deletedClassify = cls;
      },
      confirmDelClassify() {
        this.$http.delete("http://localhost:8080/api/sort/" + this.deletedClassify.id).then((response) => {
              for(var i = 0; i < this.classify.length; i++) {
                if (this.classify[i].id == this.deletedClassify.id) {
                  
                  this.classify.slice(i,1);
                }
              }
              this.$message.success('删除分类成功');
              this.deleteClassifyVisible = false;
                  console.log('success');
              },
              (error) => {
                  console.log(error);
              }
              );
      },
      editClassify(cls) {
        this.editClassifyVisible = true;
        this.editClassifyForm.editClassifyName = cls.name
      },
      editSuccess(text) {
        alert(text);
      }
    },
    components: {
      goodForm
    }
  }
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .form-btn-group {
    text-align center
  }

  .good
    margin 1.5rem 0 0 1.5rem
    .classify
      height: 40.9rem
      background: #f9f9f9
      .classify-title
        height: 2.2rem
        background: #f1f1f1
        text-align: center
        line-height: 2.2rem
        .title
          font-size: 0.9rem
          font-weight: bold
      .classify-body
        .mdui-list-item
          font-size 0.7rem
          border-bottom 1px solid rgba(7, 17, 87, 0.1)
          &.active
            background #878D99
            color #fff
          .classify-name
            width 75%
          .classify-toolbar
            position absolute
            right 1px
            font-size 0.6rem
            padding-right 0.5rem
            i
              margin-right 0.2rem
    .toolBar
      margin 0 1rem 1rem 1rem
    .goodTable
      margin 0 1rem

</style>
