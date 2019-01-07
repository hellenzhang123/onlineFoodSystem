<template>
 
</template>

<script type="text/ecmascript-6">


  export default {
    props: {
    },
    data() {
      return {
        rules: {
          name: [
            { required: true, message: '请输入您的姓名', trigger: 'blur' },
            { min: 5, max: 10, message: '长度在 5 到 10 个字符', trigger: 'blur' }
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
        }
      }
    },

    methods: {
    }
  }
</script>

<style lang="stylus" rel="stylesheet/stylus">

</style>
