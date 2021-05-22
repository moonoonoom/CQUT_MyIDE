<template>
    <div>
        <NavMenu :isDisForLex="!inputCode" :oriCode="inputCode" />
        <el-form>
            <el-col :span="8">
                <el-input class="codeText" type="textarea" rows="30" v-model="inputCode" ></el-input>
            </el-col>
            <el-col :span="16">
                <el-row :span="12">
                    <el-input type="textarea" rows="15" v-model="input" ></el-input>
                </el-row>
                <el-row :span="12">
                    <el-input type="textarea" rows="15" v-model="input" ></el-input>
                </el-row>
            </el-col>
        </el-form>
        
    </div>
</template>



<script>

import NavMenu from '../components/NavMenu'

export default{
    components:{
        NavMenu
    },
    data(){
        return{
            input:'',
            inputCode:''
        }
    }
}

</script>

<style>

/* .codeText{
    height: 100%;
    width: 50%;
} */

</style>