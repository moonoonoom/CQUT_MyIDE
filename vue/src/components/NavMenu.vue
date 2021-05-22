<template>
<el-menu
  :default-active="activeIndex"
  class="el-menu-demo"
  mode="horizontal"
  @select="handleSelect"
  background-color="#545c64"
  text-color="#fff"
  active-text-color="#ffd04b">
   <el-submenu index="1">
    <template slot="title">文件</template>
    <el-menu-item index="1-1" @click="postFile()">打开</el-menu-item>
    <el-menu-item index="1-2">保存</el-menu-item>
    <el-menu-item index="1-3">另存为</el-menu-item>
    </el-submenu>
  <el-submenu index="2">
    <template slot="title">编辑</template>
    <el-menu-item index="2-1" disabled>撤销</el-menu-item>
    <el-menu-item index="2-2" disabled>剪切</el-menu-item>
    <el-menu-item index="2-3" disabled>复制</el-menu-item>
    <el-menu-item index="2-4" disabled>粘贴</el-menu-item>
    <el-submenu index="2-5">
      <template slot="title">选项4</template>
      <el-menu-item index="2-5-1">选项1</el-menu-item>
      <el-menu-item index="2-5-2">选项2</el-menu-item>
      <el-menu-item index="2-5-3">选项3</el-menu-item>
    </el-submenu>
  </el-submenu>
  <el-menu-item index="3" :disabled="isDisForLex" @click="scan()">词法分析</el-menu-item>
  <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">超链接</a></el-menu-item>
</el-menu>
</template>

<script>
  export default {
    name: 'NavMenu',
    props: {
      isDisForLex: {
        type: Boolean,
        default: true
      },
      oriCode: String
    },
    data() {
      return {
        activeIndex: '0',
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      postFile(){
        this.$axios
          .post('/File/fileContent',{
            ss:"ssss"
          })
          .catch(failResponse =>{

          })
      },
      scan(){
        this.$axios
          .get('/LexicalAnalysis/Scan',{
            params:{
              str: this.oriCode
            }
          })
          .then((response) => {
            console.log(response.data);
          })
          
          .catch(failResponse =>{

          })
        
      }
    }
  }
</script>