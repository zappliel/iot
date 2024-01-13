<template>
    <div>
        <div style="margin-bottom: 30px">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/home' }">系统管理</el-breadcrumb-item>
                <el-breadcrumb-item>设备配置</el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入设备ID" suffix-icon="el-icon-collection-tag" class="ml-5" v-model="ID"></el-input>
            <el-input style="width: 200px" placeholder="请输入设备类型" suffix-icon="el-icon-search" class="ml-5" v-model="type"></el-input>
            <el-button class="ml-5" type="primary" @click="searchId">按ID检索</el-button>
            <el-button class="ml-5" type="primary" @click="searchType">按类型检索</el-button>
        </div>

        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="primary" @click="reset">刷新 <i class="el-icon-refresh"></i></el-button>
        </div>

        <el-table :data="tableData"  border stripe :header-cell-class-name="headerBg">
            <el-table-column prop="deviceid" label="设备ID" width="160"></el-table-column>
            <el-table-column prop="devicename" label="设备名称" width="300"></el-table-column>
            <el-table-column prop="type" label="设备类型" width="490"></el-table-column>
            <el-table-column
                prop="tag"
                tag="state"
                label="状态"
                width="70"
                :filters="[{ text: '正常', value: 0 }, { text: '告警', value: 1 }]"
                :filter-method="filterTag"
                filter-placement="bottom-end">
                <template slot-scope="scope">
                    <el-tag
                        :type="scope.row.state === 0 ? 'success' : 'danger'"
                        disable-transitions>{{scope.row.state === 0 ? '正常' : '告警'}}</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作"  width="200" align="center">
                <template slot-scope="scope">
                    <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
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

        <el-dialog title="用户信息" :visible.sync="dialogFormVisibleAdd" width="30%" >
            <el-form label-width="80px" size="small">
                <el-form-item label="设备ID">
                    <el-input v-model="form.deviceid" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="设备名称">
                    <el-input v-model="form.devicename" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="设备类型">
                    <el-input v-model="form.type" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="是否告警(是或否)">
                    <el-input v-model="form.state" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisibleAdd = false">取 消</el-button>
                <el-button type="primary" @click="saveAdd">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="用户信息" :visible.sync="dialogFormVisibleEdit" width="30%" >
            <el-form label-width="80px" size="small">
                <el-form-item label="设备名称">
                    <el-input v-model="form.devicename" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="设备类型">
                    <el-input v-model="form.type" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisibleEdit = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确认修改</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
import {defineComponent} from 'vue'

export default defineComponent({
    name: "SettingDevices",
    data(){
        return{
            ID: '',
            type: '',
            tableData: [],
            form: {},
            dialogFormVisibleAdd: false,
            dialogFormVisibleEdit:false,
            headerBg: 'headerBg'
        }
    },
    created() {
        this.load()
    },
    methods:{
        filterTag(value, row) {
            return row.state === value;
        },
        //载入数据
        load(){
            this.request.get("http://localhost:9090/api/device/list")
                .then(res=>{
                    if(res.code===200){
                        console.log(res);
                        this.tableData=res.result;
                    }else{
                        console.error('fail to get data')
                    }
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });
        },
        //按id检
        searchId(){
            console.log(this.ID)
            this.request.get("http://localhost:9090/api/device/get",{
                params:{
                    deviceid: this.ID
                }
            })
                .then(res=>{
                    if(res.code===200){
                        console.log(res);
                        this.tableData=[]
                        this.tableData[0]=res.result
                        console.log("tableData:"+this.tableData)
                    }
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });
        },
        //按类型检索
        searchType(){
            this.request.get("http://localhost:9090/api/device/type",{
                params:{type:this.type}
            })
                .then(res=>{
                    if(res.code===200){
                        console.log(res);
                        this.tableData=[]
                        this.tableData[0]=res.result
                    }
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });
        },
        //刷新，即重新导入
        reset() {
            this.load()
        },
        saveAdd(){
            this.request.get("http://localhost:9090/api/device/add",{
                params: {
                    deviceid:this.form.deviceid,
                    devicename:this.form.devicename,
                    type:this.form.type,
                    state:(this.form.state === '是' ? 1 : 0)
                }
            })
                .then(res =>{
                    if(res.code===200){
                        console.log(res);
                    }else{
                        console.error('fail to add');
                    }
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });
        },
        handleAdd(){
            this.form = {}
            this.dialogFormVisibleAdd = true
        },
        saveEdit(){
            this.request.post("http://localhost:9090/api/device/update",
                {
                    deviceid:this.form.deviceid,
                    devicename:this.form.devicename,
                    type:this.form.type,
                    state:1
                })
                .then(res =>{
                    if(res.code===200){
                        console.log(res);
                    }else{
                        console.error('fail to add');
                    }
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });
        },
        handleEdit(row){
            this.form = row
            this.dialogFormVisibleEdit = true
        }
    }
})
</script>

<style>
.headerBg {
    background: #eee!important;
}
</style>