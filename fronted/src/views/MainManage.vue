<template>
    <el-container style="min-height: 100vh">

        <el-aside :width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246); box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
            <MainAside :isCollapse="isCollapse" :logoTextShow="logoTextShow" />
        </el-aside>

        <el-container>

            <el-header style="border-bottom: 1px solid #ccc">
                <MainHeader :collapseBtnClass="collapseBtnClass" @asideCollapse="collapse" :collapse="isCollapse" />
            </el-header>

            <el-main>
                <router-view />
            </el-main>

        </el-container>
    </el-container>
</template>

<script>

import MainAside from "@/components/MainAside.vue";
import MainHeader from "@/components/MainHeader.vue";

export default {
    name: 'MainManage',
    data() {
        return {
            collapseBtnClass: 'el-icon-s-fold',
            isCollapse: false,
            sideWidth: 200,
            logoTextShow: true,
        }
    },
    components:{
        MainAside,
        MainHeader
    },
    methods: {
        collapse() {  // 点击收缩按钮触发
            this.isCollapse = !this.isCollapse
            if (this.isCollapse) {  // 收缩
                this.sideWidth = 64
                this.collapseBtnClass = 'el-icon-s-unfold'
                this.logoTextShow = false
            } else {   // 展开
                this.sideWidth = 200
                this.collapseBtnClass = 'el-icon-s-fold'
                this.logoTextShow = true
            }
        }
    }
}
</script>

<style>

</style>