(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-02f45932"],{"1efa":function(e,t,s){},4488:function(e,t,s){"use strict";s("f61a")},"9ed6":function(e,t,s){"use strict";s.r(t);var o=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"login-container"},[s("el-form",{ref:"loginForm",staticClass:"login-form",attrs:{model:e.loginForm,rules:e.loginRules,"auto-complete":"on","label-position":"left"}},[s("div",{staticClass:"title-container"},[s("h3",{staticClass:"title"},[e._v(" 登录页面")])]),s("el-form-item",{attrs:{prop:"username"}},[s("span",{staticClass:"svg-container"},[s("svg-icon",{attrs:{"icon-class":"user"}})],1),s("el-input",{ref:"username",attrs:{placeholder:"Username",name:"username",type:"text",tabindex:"1","auto-complete":"on"},model:{value:e.loginForm.tel,callback:function(t){e.$set(e.loginForm,"tel",t)},expression:"loginForm.tel"}})],1),s("el-form-item",{attrs:{prop:"password"}},[s("span",{staticClass:"svg-container"},[s("svg-icon",{attrs:{"icon-class":"password"}})],1),s("el-input",{key:e.passwordType,ref:"password",attrs:{type:e.passwordType,placeholder:"Password",name:"password",tabindex:"2","auto-complete":"on"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleLogin(t)}},model:{value:e.loginForm.password,callback:function(t){e.$set(e.loginForm,"password",t)},expression:"loginForm.password"}}),s("span",{staticClass:"show-pwd",on:{click:e.showPwd}},[s("svg-icon",{attrs:{"icon-class":"password"===e.passwordType?"eye":"eye-open"}})],1)],1),s("el-button",{staticStyle:{width:"100%","margin-bottom":"30px"},attrs:{loading:e.loading,type:"primary"},nativeOn:{click:function(t){return t.preventDefault(),e.handleLogin(t)}}},[e._v(" Login")])],1)],1)},n=[],r=s("61f7"),a=s("c24f"),i=s("5f87"),l={name:"Login",data:function(){var e=function(e,t,s){Object(r["b"])(t)?s():s(new Error("Please enter the correct user name"))},t=function(e,t,s){t.length<3?s(new Error("The password can not be less than 6 digits")):s()};return{loginForm:{tel:"18993087505",password:"123"},loginRules:{tel:[{required:!0,trigger:"blur",validator:e}],passwd:[{required:!0,trigger:"blur",validator:t}]},loading:!1,passwordType:"password",redirect:void 0}},watch:{$route:{handler:function(e){this.redirect=e.query&&e.query.redirect},immediate:!0}},methods:{showPwd:function(){var e=this;"password"===this.passwordType?this.passwordType="":this.passwordType="password",this.$nextTick((function(){e.$refs.password.focus()}))},handleLogin:function(){var e=this;this.$refs.loginForm.validate((function(t){if(!t)return console.log("error submit!!"),!1;e.loading=!0,Object(a["e"])(e.loginForm).then((function(t){Object(i["c"])(t.data.token),console.log(e.$store.state.avatar),"200"==t.code&&(e.loading=!1,e.$message({message:"登录成功!",type:"success"}),e.$router.push("/"))}))}))}}},c=l,d=(s("de3e"),s("4488"),s("2877")),p=Object(d["a"])(c,o,n,!1,null,"62e9f767",null);t["default"]=p.exports},de3e:function(e,t,s){"use strict";s("1efa")},f61a:function(e,t,s){}}]);