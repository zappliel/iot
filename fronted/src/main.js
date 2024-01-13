import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
import './assets/global.css'
import BaiduMap from 'vue-baidu-map'
import request from "./utils/request.js";
import * as echarts from 'echarts'
import "echarts";

Vue.config.productionTip = false


Vue.use(ElementUI,{size:"small"})

Vue.prototype.request = request;
Vue.prototype.$echarts = echarts

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

Vue.use(BaiduMap, {
  ak: 'j1IRG5PNVlWG5UK8EV0btB0jeBQ8QOzb'
});