<template>
    <div>
        <div style="margin-bottom: 30px">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/home' }">系统</el-breadcrumb-item>
                <el-breadcrumb-item>地图显示</el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <el-button @click="drawer = true" type="primary" style="margin-left: 16px;">
            打开连接设备表
        </el-button>

        <el-drawer
            title="连接设备表"
            :visible.sync="drawer"
            :direction="direction">
            <el-table :data="form">
                <el-table-column prop="deviceid" label="设备ID" width="120"></el-table-column>
                <el-table-column prop="devicename" label="设备名称" width="120"></el-table-column>
                <el-table-column label="操作"  width="180" align="center">
                    <template slot-scope="scope">
                        <el-button type="info" @click="handleShowPath(scope.row)">显示 <i class="el-icon-picture-outline-round"></i></el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-drawer>

        <baidu-map v-if="mapLoaded"
                   class="map"
                   ref="bmMapRef"
                   :center="map.center"
                   :zoom="map.zoom"
                   :scroll-wheel-zoom="true"
                   @moving="syncCenterAndZoom"
                   @moveend="syncCenterAndZoom"
                   @zoomend="syncCenterAndZoom">
            <bm-context-menu>
                <bm-context-menu-item :callback="drawTable" text="显示设备表"></bm-context-menu-item>
                <bm-context-menu-item :callback="reset" text="刷新设备表"></bm-context-menu-item>
            </bm-context-menu>
            <bm-polyline
                :path="points"
                :editing="true"
                :stroke-color=color
                :stroke-opacity="0.5"
                :stroke-weight="2"></bm-polyline>
        </baidu-map>
    </div>
</template>

<script>
export default {
    data() {
        return {
            mapLoaded: false,
            map:{
                center: {
                    lng: 120.120794,
                    lat: 30.259157
                },
                zoom: 12,
            },
            points:[],
            drawer: false,
            direction: 'rtl',
            form:[],
            target:{},
            deviceState:0,
            color:"blue"
        };
    },
    mounted() {
        this.mapLoaded = true;
    },
    created() {
        this.load()
    },
    methods: {
        load(){
            this.request.get("http://localhost:9090/api/join/list")
                .then(response => {
                    if(response.code===200){
                        console.log(response);
                        this.form = response.result;
                    }else{
                        console.error('fail to get data')
                    }
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });
        },
        handleShowPath(row){
            this.target = row
            this.showPath(this.target.deviceid)
        },
        syncCenterAndZoom (e) {
            const { lng, lat } = e.target.getCenter()
            this.map.center.lng = lng
            this.map.center.lat = lat
            this.map.zoom = e.target.getZoom()
        },
        reset(){
          this.load()
        },
        drawTable(){
            this.drawer = true;
        },
        showPath(deviceid){
            console.log("deviceid:"+deviceid)
            this.request.get("http://localhost:9090/api/log/path",{
                params:{deviceid:deviceid}
            })
                .then(res=>{
                    if(res.code===200){
                        console.log("res:"+res.result);
                        this.points=[]
                        this.points = res.result.map(item => ({
                            lng: item.ing,
                            lat: item.lat,
                        }));
                        console.log("this.points:"+this.points);
                        if (res.result[0]) {
                            this.map.center.lng = res.result[0].ing
                            this.map.center.lat = res.result[0].lat
                        }
                    }
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });

            this.request.get("http://localhost:9090/api/log/state",{
                params:{deviceid: deviceid}
            })
                .then(res=>{
                    if(res.code===200){
                        console.log(res);
                        this.deviceState=res.result;
                    }
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });

            if(this.deviceState == 0){
                this.color = "blue";
            }
            else{
                this.color = "red";
            }
        }
    },
};
</script>

<style>
.map {
    width: 100%;
    height: 600px;
}
</style>