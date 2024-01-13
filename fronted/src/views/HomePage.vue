<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/home' }">系统</el-breadcrumb-item>
            <el-breadcrumb-item>主页</el-breadcrumb-item>
        </el-breadcrumb>
        <div class="chart-container">
        <!-- 饼图 -->
        <v-chart ref="pieChart" :options="pieChartOptions" class="chart"></v-chart>
        <!-- 折线图 -->
        <v-chart ref="lineChart" :options="lineChartOptions" class="chart"></v-chart>
    </div>
    </div>
</template>

<script>
import VueECharts from 'vue-echarts';
import 'echarts/lib/chart/pie'; // 引入饼图图表类型
import 'echarts/lib/chart/line'; // 引入折线图图表类型
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';

export default {
    components: {
        'v-chart': VueECharts,
    },
    data() {
        return {
            d:0,
            pieChartData: {
                labels: ['在线设备', '不在线设备'],
                data: [0, 0],
            },
            lineChartData: {
                online_count: [],
                times: ['1','2','3'],
            },
        };
    },
    watch:{
      pieChartData: {
          handler(newVal, oldVal) {
              console.log({newVal,oldVal});
              this.renderPieChart();
          },
          deep: true
      },
      lineChartData: {
            handler(newVal, oldVal) {
                console.log({newVal,oldVal});
                this.renderLineChart();
            },
          deep: true
        },
    },
    created() {
        this.load()
    },
    methods: {
        load(){
            //载入饼图信息
            this.request.get("http://localhost:9090/api/log/online")
                .then(response => {
                    if(response.code===200){
                        console.log(response);
                        if(response.result>0) {
                            this.d=response.result
                            this.pieChartData.data = [this.d-1,1];
                        }
                    }else{
                        console.error('fail to get data')
                    }
                })

            //载入折线图信息
            this.request.get("http://localhost:9090/api/log/countall")
                .then(response => {
                    if(response.code===200){
                        console.log(response.result);
                        /*this.lineChartData = response.result.map(item => ({
                                online_count: item.online_count,
                                times:item.times,
                            })
                        );*/
                        let onlineCounts = response.result.map(item => item.online_count)
                        let timec = response.result.map(item => item.times)
                        console.log("onlineCounts:")
                        console.log(onlineCounts)
                        console.log("timec:")
                        console.log(timec)
                        this.lineChartData.online_count = onlineCounts
                        this.lineChartData.times = timec
                    }else{
                        console.error('fail to get data')
                    }
                })
        },
        renderCharts() {
            this.renderPieChart();
            this.renderLineChart();
        },
        renderPieChart() {
            // 渲染饼图的逻辑
            const pieChart = this.$refs.pieChart;
            const option = this.pieChartOptions();
            pieChart.setOption(option);
        },
        renderLineChart() {
            // 渲染折线图的逻辑
            const lineChart = this.$refs.lineChart;
            const option = this.lineChartOptions();
            lineChart.setOption(option);
        },
        pieChartOptions() {
            return {
                title: {
                    text: '设备统计',
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)',
                },
                series: [
                    {
                        name: '设备数',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '50%'],
                        data: this.pieChartData.labels.map((label, index) => ({
                            name: label,
                            value: this.pieChartData.data[index],
                        })),
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)',
                            },
                        },
                    },
                ],
            };
        },
        lineChartOptions() {
            console.log(this.lineChartData.times)
            console.log(this.lineChartData.online_count)
            return {
                title: {
                    text: '设备数据量统计',
                },
                xAxis: {
                    type: 'category',
                    data: this.lineChartData.times,
                },
                yAxis: {
                    type: 'value',
                    name: '数据量（单位）',
                },
                series: [{
                    name: '数据量',
                    type: 'line',
                    data: this.lineChartData.online_count,
                }],
            };
        },
    },
    mounted() {
        this.$nextTick(() => {
            this.renderCharts();
        });
    },
};
</script>

<style scoped>
.chart-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh; /* 设置容器高度为视口高度 */
}

.chart {
    width: 50%; /* 设置图表宽度 */
    height: 400px; /* 设置图表高度 */
    margin: 10px; /* 设置图表之间的间隔 */
}
</style>