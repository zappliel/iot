<template>
    <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
             background-color="#000093"
             text-color="#fff"
             active-text-color="#ffd04b"
             :collapse-transition="false"
             :collapse="isCollapse"
             router
    >
        <div style="height: 60px; line-height: 60px; text-align: center">
            <img src="../assets/IOT.png" alt="" style="width: 40px; position: relative; top: 5px">
            <b style="color: white" v-show="logoTextShow">物联网设备管理</b>
        </div>

        <el-menu-item index="/home">
                <i class="el-icon-house"></i>
                <span slot="title">主页</span>
        </el-menu-item>

        <el-submenu index="">
            <template slot="title">
                <i class="el-icon-setting"></i>
                <span slot="title">系统管理</span>
            </template>
            <el-menu-item index="/setting">
                <i class="el-icon-edit-outline"></i>
                <span slot="title">设备配置</span>
            </el-menu-item>
            <el-menu-item index="/devices">
                <i class="el-icon-tickets"></i>
                <span slot="title">信息统计</span>
            </el-menu-item>
        </el-submenu>

        <el-menu-item index="/map">
                <i class="el-icon-location-outline"></i>
                <span slot="title">地图显示</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
import {defineComponent} from 'vue'

export default defineComponent({
    name: "MainAside",
    props: {
        isCollapse: Boolean,
        logoTextShow: Boolean
    }
})
</script>

<style>

</style>