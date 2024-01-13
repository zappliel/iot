<template>
    <div>
        <div style="margin-bottom: 30px">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/home' }">系统管理</el-breadcrumb-item>
                <el-breadcrumb-item>信息统计</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入关键词" suffix-icon="el-icon-collection-tag" class="ml-5" v-model="keywords"></el-input>
            <el-button type="primary" @click="keyword">关键词搜索 <i class="el-icon-s-promotion"></i></el-button>
        </div>
        <el-table :data="tableData"  border stripe :header-cell-class-name="headerBg">
            <el-table-column prop="deviceid" label="设备ID" width="120">
            </el-table-column>
            <el-table-column prop="devicename" label="设备名称" width="160">
            </el-table-column>
            <el-table-column prop="type" label="设备类型" width="160">
            </el-table-column>
            <el-table-column prop="ing" label="设备经度" width="120">
            </el-table-column>
            <el-table-column prop="lat" label="设备纬度" width="120">
            </el-table-column>
            <el-table-column prop="info" label="最后信息">
            </el-table-column>
            <el-table-column label="操作"  width="200" align="center">
                <template slot-scope="{}">
                    <el-button type="info" @click="reset" >刷新 <i class="el-icon-files"></i></el-button>
                </template>
            </el-table-column>
        </el-table>

        <div style="padding: 10px 0">
            <el-pagination
                    :page-sizes="[5, 10, 15, 20]"
                    :page-size="10"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="50">
            </el-pagination>
        </div>
    </div>
</template>

<script>
import {defineComponent} from 'vue'

export default defineComponent({
    name: "ShowDevices",
    data(){
        return{
            tableData: [],
            keywords: '',
            headerBg: 'headerBg'
        }
    },
    created() {
        this.load()
    },
    methods:{
        load(){
            this.request.get("http://localhost:9090/api/join/list")
                .then(response => {
                if(response.code===200){
                    console.log(response);
                    this.tableData = response.result.map(item => ({
                        deviceid: item.deviceid,
                        devicename: item.devicename,
                        type: item.type,
                        ing: item.ing,
                        lat: item.lat,
                        info: item.info,
                    }));
                }else{
                    console.error('fail to get data')
                }
            })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });
        },
        //
        reset(){
            this.load();
        },
        //
        keyword(){
            this.request.get("http://localhost:9090/api/join/search",{
                params:{
                    keyword:this.keywords
                }
            })
                .then(response => {
                    if(response.code===200){
                        console.log(response);
                        this.tableData=[]
                        this.tableData = response.result.map(item => ({
                            deviceid: item.deviceid,
                            devicename: item.devicename,
                            type: item.type,
                            ing: item.ing,
                            lat: item.lat,
                            info: item.info,
                        }));
                    }else{
                        console.error('fail to get data')
                    }
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });
        }
    }
})
</script>

<style>
.headerBg {
    background: #eee!important;
}
</style>