<template>
    <div class="base">
        <!-- 注册登录界面 -->
        <div  class="loginAndRegist">
            <!--登录表单-->
            <div  class="loginArea">
                <transition
                        name="animate__animated animate__bounce"
                        enter-active-class="animate__fadeInUp"
                        leave-active-class="animate__zoomOut"
                        appear
                >
                    <!-- 标语 -->
                    <div v-show="isShow" class="title">
                        LOGIN
                    </div>
                </transition>
                <transition
                        name="animate__animated animate__bounce"
                        enter-active-class="animate__fadeInUp"
                        leave-active-class="animate__zoomOut"
                        appear
                >
                    <!-- 密码框和用户名框 -->
                    <el-form :model="loginUser" status-icon :rules="LoginRules" ref="loginUser" v-show="isShow" class="pwdArea">
                        <el-form-item style="flex: 1;justify-content: center;display: flex;align-items: center" prop="username">
                            <el-input class="username" v-model="loginUser.username" style="width: 165px"  placeholder="用户名"></el-input>
                        </el-form-item>
                        <el-form-item style="flex: 1;justify-content: center;display: flex;align-items: center" prop="password">
                            <el-input placeholder="密码"  v-model="loginUser.password" style="width: 165px" show-password></el-input>
                        </el-form-item>
                    </el-form>
                </transition>
                <transition
                        name="animate__animated animate__bounce"
                        enter-active-class="animate__fadeInUp"
                        leave-active-class="animate__zoomOut"
                        appear
                >
                    <!-- 登录注册按钮 -->
                    <div v-show="isShow" class="btnArea">
                        <el-button type="success" round  style="background-color: #3333CC;border: 1px solid #ffffff" @click="UserLogin">登录</el-button>
                    </div>
                </transition>
            </div>
            <!-- 注册表单 -->
            <div class="registArea">
                <transition
                        name="animate__animated animate__bounce"
                        enter-active-class="animate__fadeInUp"
                        leave-active-class="animate__zoomOut"
                        appear
                >
                    <!--  注册表头-->
                    <div v-show="!isShow" class="rigestTitle">
                        REGISTER
                    </div>
                </transition>
                <p></p>
                <transition
                        name="animate__animated animate__bounce"
                        enter-active-class="animate__fadeInUp"
                        leave-active-class="animate__zoomOut"
                        appear
                >
                    <!--            注册表单-->
                    <el-form :model="regUser" status-icon :rules="RegisterRules" ref="regUser" v-show="!isShow" class="registForm">
                        <el-form-item style="flex: 1;display: flex;justify-content: center;align-items: center" prop="username">
                            用&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;名:
                            <el-input
                                    placeholder="请输入用户名"
                                    v-model="regUser.username"
                                    style="width: 165px;margin-left: 10px"
                                    clearable
                            >
                            </el-input>
                        </el-form-item>
                        <el-form-item style="flex: 1;display: flex;justify-content: center;align-items: center" prop="password">
                            密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:
                            <el-input placeholder="请输入密码" style="width: 165px;margin-left: 10px" v-model="regUser.password" show-password></el-input>
                        </el-form-item>
                        <el-form-item style="flex: 1;display: flex;justify-content: center;align-items: center" prop="checkPassword">
                            确&nbsp;认&nbsp;密&nbsp;码:
                            <el-input placeholder="请再次输入密码" style="width: 165px;margin-left: 10px" v-model="regUser.checkPassword" show-password></el-input>
                        </el-form-item>
                        <el-form-item style="flex: 1;display: flex;justify-content: center;align-items: center" prop="email">
                            邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:
                            <el-input
                                placeholder="请输入用户名"
                                v-model="regUser.email"
                                style="width: 165px;margin-left: 10px"
                                clearable
                            >
                            </el-input>
                        </el-form-item>
                    </el-form>
                </transition>
                <transition
                        name="animate__animated animate__bounce"
                        enter-active-class="animate__fadeInUp"
                        leave-active-class="animate__zoomOut"
                        appear
                >
                    <!--            注册按钮-->
                    <div  v-show="!isShow" class="registBtn">
                        <el-button type="success" round style="background-color: #3333CC;border: 1px solid #ffffff;" @click="userRegister">注册</el-button>
                    </div>
                </transition>
            </div>
            <!-- 信息展示界面 -->
            <div id="aaa" class="showInfo"
                 :style="{
             borderTopRightRadius:styleObj.bordertoprightradius,
             borderBottomRightRadius:styleObj.borderbottomrightradius,
             borderTopLeftRadius:styleObj.bordertopleftradius,
             borderBottomLeftRadius:styleObj.borderbottomleftradius,
             right:styleObj.rightDis
            }"
                 ref="showInfoView">

                <transition
                        name="animate__animated animate__bounce"
                        enter-active-class="animate__fadeInUp"
                        leave-active-class="animate__zoomOut"
                        appear
                >
                    <!-- 没有用户输入用户名或者找不到用户名的时候 -->
                    <div v-show="isShow" style="display: flex;flex-direction: column;align-items: center;justify-content: center;width: 100%;height: 100%">
                        <!-- 欢迎语 -->
                        <div style="flex: 2;display: flex;align-items: center;font-size: 22px;color: #FFFFFF;font-weight: bold">
                            欢迎登录浙江大学IOT管理平台
                        </div>
                        <!-- 欢迎图片 -->
                        <div style="flex: 2">
                            <el-button type="success"  style="background-color:#3333CC;border: 1px solid #ffffff;" round @click="changeToRegiest">还没有账户？点击注册</el-button>
                        </div>
                    </div>
                </transition>
                <!-- 用户输入用户名时展示头像以及姓名 -->
                <!--           <div>-->

                <!--           </div>-->
                <transition
                        name="animate__animated animate__bounce"
                        enter-active-class="animate__fadeInUp"
                        leave-active-class="animate__zoomOut"
                        appear
                >
                    <!-- 用户注册的时候展示信息 -->
                    <div v-show="!isShow" style="display: flex;flex-direction: column;align-items: center;justify-content: center;width: 100%;height: 100%">
                        <!-- 欢迎语 -->
                        <div style="flex: 2;display: flex;align-items: center;font-size: 22px;color: #FFFFFF;font-weight: bold">
                            欢迎注册
                        </div>
                        <!-- 欢迎图片 -->
                        <div style="flex: 2">
                            <el-button type="success"  style="background-color:#3333CC;border: 1px solid #ffffff;" round @click="changeToLogin">已有账户？点击登录</el-button>
                        </div>
                    </div>
                </transition>
            </div>
        </div>

    </div>
</template>

<script>
import 'animate.css';
// eslint-disable-next-line no-unused-vars
import axios, {Axios as request} from "axios";
//import Qs from 'qs'
export default {

    name:'LoginPage',
    data(){
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.regUser.password) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return{
            //看看用不用转成用户对象
            loginUser:{
                username:'',
                password:''
            },
            LoginRules: {
                username: [
                    {required: true, message: '请输入用户名', trigger: 'blur'},
                    {min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                ],
                password: [
                    {required: true, message: '请输入密码', trigger: 'blur'},
                    {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
                ],
            },
            admins:{},
            ////看看用不用转成用户对象
            regUser:{
                username:'',
                password:'',
                checkPassword:'',
                email:"",
            },
            user:{},
            RegisterRules: {
                username: [
                    {required: true, message: '请输入用户名', trigger: 'blur'},
                    {min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                ],
                password: [
                    {required: true, message: '请输入密码', trigger: 'blur'},
                    {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
                ],
                checkPassword: [
                    {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'},
                    {validator: validatePass, trigger: 'blur' }
                ],
                email: [
                    { required: true, message: '请输入邮箱地址', trigger: 'blur' },
                    { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
                ],
            },
            styleObj:{
                bordertoprightradius:'15px',
                borderbottomrightradius: '15px',
                bordertopleftradius:'0px',
                borderbottomleftradius:'0px',
                rightDis:'0px'
            },
            isShow:true
        }
    },
    methods:{
        changeToRegiest(){
            this.styleObj.bordertoprightradius= '0px'
            this.styleObj.borderbottomrightradius='0px'
            this.styleObj.bordertopleftradius='15px'
            this.styleObj.borderbottomleftradius='15px'
            this.styleObj.rightDis='50%'
            this.isShow = !this.isShow
        },
        changeToLogin(){
            this.styleObj.bordertoprightradius= '15px'
            this.styleObj.borderbottomrightradius='15px'
            this.styleObj.bordertopleftradius='0px'
            this.styleObj.borderbottomleftradius='0px'
            this.styleObj.rightDis='0px'
            this.isShow = !this.isShow
        },
        //用户登录
        UserLogin(){
            console.log(this.loginUser.username)
            this.request.get("http://localhost:9090/api/user/get",
                {
                    params: {
                        username: this.loginUser.username
                    }
                }).then(res=>{
                if(res.code === 200){
                    if(res.result.pass === this.loginUser.password){
                        this.$router.push("/home")
                        this.$message.success("登录成功！")
                    }else {
                        this.$message.error("密码错误！")
                    }
                }
                else{
                    this.$message.error("用户名不存在！")
                }
            })
        },
        //用户注册
        userRegister(){
            if(this.regUser.username===""){
                this.$message.error("用户名不能为空！")
                return false
            }else{
                /*let myconfig= {
                    params: {
                        name: this.regUser.username,
                        pass: this.regUser.password,
                        email: this.regUser.email
                    }
                };*/
                //console.log(myconfig)
                this.request.post("http://localhost:9090/api/user/add",
                    {
                        name: this.regUser.username,
                        pass: this.regUser.password,
                        email: this.regUser.email
                    }).then(res => {
                            if (res.msg === "success") {
                                this.$message.success("注册成功！")
                                this.regUser = {
                                    username: '',
                                    password: '',
                                    checkPassword: '',
                                    email: ''
                                }
                                this.changeToLogin()
                            }
                        })
            }
        }
    },
}
</script>

<style>
/* @import '../assets/css/Login.css' */
.base{
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-image: url("../assets/background.png");
    background-size: 100%;
}
.loginAndRegist{
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
}
.loginArea{
    background-color: rgba(255,255,255,0.8);
    border-top-left-radius: 15px;
    border-bottom-left-radius: 15px;
    height: 400px;
    width: 350px;
    z-index: 1;
    display: flex;
    flex-direction: column;
    align-items: center;
    overflow: hidden;
}
.registArea{
    border-top-right-radius: 15px;
    border-bottom-right-radius: 15px;
    height: 400px;
    width: 350px;
    background-color: rgba(255,255,255,0.8);
    z-index: 1;
    display: flex;
    flex-direction: column;
    justify-content:center ;
    align-items: center;
}
.showInfo{
    border-top-right-radius: 15px;
    border-bottom-right-radius: 15px;
    position: absolute;
    height: 400px;
    width: 350px;
    z-index:2;
    top: 0;
    right: 0;
    background-image: url("../assets/welcome.png");
    background-size: 90%;
}
.showInfo:hover{
    background-size: 100%;
    background-position: -50px -50px;
}
.title{
    width: 70%;
    flex:1;
    border-bottom: 1px solid #3333CC;
    display: flex;
    align-items: center;
    color: #3333CC;
    font-weight: bold;
    font-size: 24px;
    display: flex;
    justify-content: center;
}
#aaa{
    transition: 0.3s linear;
}
.pwdArea{
    width: 100%;
    flex:2;
    display: flex;
    flex-direction: column;
    display: flex;
    flex-direction: column;


}
.pwdArea input{
    outline: none;
    height: 30%;
    border-radius:13px ;
    padding-left: 10px;
    font-size: 12px;
    border: 1px solid gray;
}
.pwdArea input:focus{
    border: 2px solid #3333CC;
}
.btnArea{
    flex:1;
    width: 100%;
    display: flex;
    justify-content: space-around;
    align-items: center;
}
.rigestTitle{
    width: 70%;
    flex: 1;
    color: #3333CC;
    font-weight: bold;
    font-size: 24px;
    display: flex;
    justify-content: center;
    align-items: center;
    border-bottom: 1px solid #3333CC;
}
.registForm{
    flex: 2;
    display: flex;
    flex-direction: column;
    color: #3333CC;
    font-size: 16px;
}

.registForm input{
    outline: none;
    height: 30%;
    border-radius:13px ;
    padding-left: 10px;
    font-size: 12px;
    border: 1px solid gray;
}
.registForm input:focus{
    border: 2px solid #257B5E;
}
#elselect:focus{
    border: 2px solid #257B5E;
}
.registBtn{
    flex: 1;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>