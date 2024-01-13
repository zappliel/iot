import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name:'MainManage',
        component: ()=>import('../views/MainManage.vue'),
        redirect:"/login",
        children:[
            { path: 'home', name: '首页', component: () => import('../views/HomePage.vue')},
            { path: 'devices', name: '信息统计', component: () => import('../views/ShowDevices.vue')},
            { path: 'setting', name: '设备配置', component: () => import('../views/SettingDevices.vue')},
            { path: 'map', name: '地图', component: () => import('../views/ShowMap.vue')},
        ]
    },
    {
        path: '/login',
        name: 'LoginPage',
        component: () => import('../views/LoginPage.vue')
    },
]

const router = new VueRouter({
    // 把常量routes放进来
    routes
})

//获取原型对象上的push函数
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}


// 导出router
export default router