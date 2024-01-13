<template>
    <div style="line-height: 60px; display: flex">
        <div style="flex: 1; font-size: 20px">
            <span :class="collapseBtnClass" style="cursor: pointer; font-size: 18px" @click="collapse"></span>

        </div>
        <el-dropdown style="width: 200px; cursor: pointer">
            <span>浙江大学IOT管理平台</span>
            <i class="el-icon-arrow-down" style="margin-left: 10px"></i>

            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="turnHome">主页</el-dropdown-item>
                <el-dropdown-item @click.native="turnQuit">退出</el-dropdown-item>
            </el-dropdown-menu>

        </el-dropdown>
    </div>
</template>

<script>
export default {
    name: "MainHeader",
    props: {
        collapseBtnClass: String,
    },
    //computed: {
    //currentPathName () {
    //    return this.$store.state.currentPathName;
    //}
    //}
    data() {
        return {

        }
    },
    methods: {
        collapse() {
            // this.$parent.$parent.$parent.$parent.collapse()  // 通过4个 $parent 找到父组件，从而调用其折叠方法
            this.$emit("asideCollapse")
        },
        turnHome(){
            localStorage.clear();
            this.$router.push({ path: "/home" });
        },
        turnQuit(){
            localStorage.clear();
            this.$router.push({ path: "/login" });
        }
    },

}
</script>

<style>

</style>