(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-c94339c2"],{2291:function(e,t,a){},8492:function(e,t,a){"use strict";a.d(t,"c",(function(){return l})),a.d(t,"d",(function(){return o})),a.d(t,"g",(function(){return s})),a.d(t,"e",(function(){return i})),a.d(t,"a",(function(){return n})),a.d(t,"f",(function(){return c})),a.d(t,"b",(function(){return m}));a("99af");var r=a("b775");function l(e,t,a){return Object(r["a"])({url:"/merchant/getAllMerchantsByPageForAdmin/".concat(e,"/").concat(t),method:"post",headers:{"Content-Type":"multipart/form-data"},data:a})}function o(e){return Object(r["a"])({url:"/merchant/getMerchantForAdmin/".concat(e),method:"get"})}function s(e){return Object(r["a"])({url:"/merchant/imageUpload",method:"post",headers:{"Content-Type":"multipart/form-data"},data:e})}function i(e){return Object(r["a"])({url:"/merchant/saveImg",method:"post",headers:{"Content-Type":"multipart/form-data"},data:e})}function n(e){return Object(r["a"])({url:"/merchant/add",method:"post",data:e})}function c(e){return Object(r["a"])({url:"/merchant/updateMerchant",method:"post",data:e})}function m(e){return Object(r["a"])({url:"/merchant/delMerchant",method:"delete",data:e})}},"9a68":function(e,t,a){"use strict";a("fab2e")},a695:function(e,t,a){"use strict";a.d(t,"a",(function(){return r}));a("ac1f"),a("466d"),a("ace4"),a("d3b7"),a("5cc6"),a("9a8c"),a("a975"),a("735e"),a("c1ac"),a("d139"),a("3a7b"),a("d5d6"),a("82f8"),a("e91f"),a("60bd"),a("5f96"),a("3280"),a("3fcc"),a("ca91"),a("25a1"),a("cd26"),a("3c5d"),a("2954"),a("649e"),a("219c"),a("170b"),a("b39a"),a("72f7"),a("b0c0"),a("00b4");var r=function e(t,a){var r=.5,l=1,o=0;e.prototype.dataUrlToFile=function(e,t){var a=e.split(","),r=a[0].match(/:(.*?);/)[1],l=atob(a[1]),o=l.length,s=new Uint8Array(o);while(o--)s[o]=l.charCodeAt(o);return new File([s],t,{type:r})},e.prototype.compress=function(e,s){var i=this;i.imgBase64(e,(function(n,c){var m=c.toDataURL(e.type,r),p=i.dataUrlToFile(m,e.name.split(".")[0]),f=p.size;f>a?(l=r,r=(r+o)/2,i.compress(e,s)):f<t?(o=r,r=(r+l)/2,i.compress(e,s)):s(p)}))},e.prototype.imgBase64=function(e,t){if(e&&window.FileReader){var a=new Image;if(a.onload=function(){var e=document.createElement("canvas"),l=e.getContext("2d");l.clearRect(0,0,e.width,e.height),e.width=a.width*r,e.height=a.height*r,l.drawImage(a,0,0,e.width,e.height),t(a,e)},/^image/.test(e.type)){var l=new FileReader;l.readAsDataURL(e),l.onload=function(){a.src=this.result}}}}}},fab2e:function(e,t,a){},feb3:function(e,t,a){"use strict";a("2291")},fefb:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"container"},[a("el-form",{ref:"merchantUploadForm",attrs:{model:e.formData,rules:e.rules,"label-position":"left","label-width":"150px",size:"medium"},nativeOn:{submit:function(e){e.preventDefault()}}},[a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{staticClass:"required",attrs:{label:"许可证号",prop:"licenseId"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.licenseId,callback:function(t){e.$set(e.formData,"licenseId",t)},expression:"formData.licenseId"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{staticClass:"required",attrs:{label:"企业(字号)",prop:"merchant"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.merchant,callback:function(t){e.$set(e.formData,"merchant",t)},expression:"formData.merchant"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{staticClass:"required",attrs:{label:"经营地址",prop:"address"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.address,callback:function(t){e.$set(e.formData,"address",t)},expression:"formData.address"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{attrs:{label:"区域",prop:"region"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.region,callback:function(t){e.$set(e.formData,"region",t)},expression:"formData.region"}})],1)],1),a("el-col",{attrs:{span:7,offset:1,"label-width":"200px"}},[a("el-form-item",{staticClass:"required",attrs:{label:"负责人(经营者)姓名",prop:"name"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.name,callback:function(t){e.$set(e.formData,"name",t)},expression:"formData.name"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{staticClass:"required",attrs:{label:"联系人",prop:"contact"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.contact,callback:function(t){e.$set(e.formData,"contact",t)},expression:"formData.contact"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{staticClass:"required",attrs:{label:"联系人电话",prop:"tel"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.tel,callback:function(t){e.$set(e.formData,"tel",t)},expression:"formData.tel"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{staticClass:"required",attrs:{label:"身份证号",prop:"id"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.id,callback:function(t){e.$set(e.formData,"id",t)},expression:"formData.id"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{staticClass:"required",attrs:{label:"供货单位",prop:"delivery"}},[a("el-autocomplete",{attrs:{"popper-class":"my-autocomplete","fetch-suggestions":e.deliveryQuerySearch,placeholder:"请输入内容"},scopedSlots:e._u([{key:"default",fn:function(t){var r=t.item;return[a("div",{staticClass:"name",attrs:{title:r.value}},[e._v(e._s(r.value))])]}}]),model:{value:e.formData.delivery,callback:function(t){e.$set(e.formData,"delivery","string"===typeof t?t.trim():t)},expression:"formData.delivery"}},[a("i",{staticClass:"el-icon-edit el-input__icon",attrs:{slot:"suffix"},slot:"suffix"})])],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{staticClass:"required",attrs:{label:"工商营业执照编号",prop:"business"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.business,callback:function(t){e.$set(e.formData,"business",t)},expression:"formData.business"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{staticClass:"required",attrs:{label:"工商营业执照有效期",prop:"permitLimit"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.formData.permitLimit,callback:function(t){e.$set(e.formData,"permitLimit",t)},expression:"formData.permitLimit"}},e._l(e.permitLimitOptions,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value,disabled:e.disabled}})})),1)],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{staticClass:"required",attrs:{label:"制证日期",prop:"chargeDate"}},[a("el-date-picker",{staticClass:"full-width-input",attrs:{type:"date",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",clearable:""},model:{value:e.formData.chargeDate,callback:function(t){e.$set(e.formData,"chargeDate",t)},expression:"formData.chargeDate"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{staticClass:"required",attrs:{label:"发证日期",prop:"issueDate"}},[a("el-date-picker",{staticClass:"full-width-input",attrs:{type:"date",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",clearable:""},model:{value:e.formData.issueDate,callback:function(t){e.$set(e.formData,"issueDate",t)},expression:"formData.issueDate"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{attrs:{label:"领证(送达)日期",prop:"gotDate"}},[a("el-date-picker",{staticClass:"full-width-input",attrs:{type:"date",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",clearable:""},model:{value:e.formData.gotDate,callback:function(t){e.$set(e.formData,"gotDate",t)},expression:"formData.gotDate"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{staticClass:"required",attrs:{label:"发证机关",prop:"office"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.formData.office,callback:function(t){e.$set(e.formData,"office",t)},expression:"formData.office"}},e._l(e.officeOptions,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value,disabled:e.disabled}})})),1)],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{staticClass:"required",attrs:{label:"许可范围",prop:"permitRange"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.permitRange,callback:function(t){e.$set(e.formData,"permitRange",t)},expression:"formData.permitRange"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{attrs:{label:"租赁开始期限",prop:"renderStartDate"}},[a("el-date-picker",{staticClass:"full-width-input",attrs:{type:"date",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",clearable:""},model:{value:e.formData.renderStartDate,callback:function(t){e.$set(e.formData,"renderStartDate",t)},expression:"formData.renderStartDate"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{attrs:{label:"租赁截止期限",prop:"renderEndDate"}},[a("el-date-picker",{staticClass:"full-width-input",attrs:{type:"date",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",clearable:""},model:{value:e.formData.renderEndDate,callback:function(t){e.$set(e.formData,"renderEndDate",t)},expression:"formData.renderEndDate"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{staticClass:"required",attrs:{label:"零售户身份证类型",prop:"storeIdType"}},[a("el-select",{staticClass:"full-width-input",model:{value:e.formData.storeIdType,callback:function(t){e.$set(e.formData,"storeIdType",t)},expression:"formData.storeIdType"}},e._l(e.IdTypeOptions,(function(e,t){return a("el-option",{key:t,attrs:{label:e.label,value:e.value,disabled:e.disabled}})})),1)],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{staticClass:"required",attrs:{label:"零售户身份证号码",prop:"storeId"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.storeId,callback:function(t){e.$set(e.formData,"storeId",t)},expression:"formData.storeId"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{staticClass:"required",attrs:{label:"零售户身份证地址",prop:"storeIdAddr"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.storeIdAddr,callback:function(t){e.$set(e.formData,"storeIdAddr",t)},expression:"formData.storeIdAddr"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{staticClass:"required",attrs:{label:"零售户地址",prop:"storeAddr"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.storeAddr,callback:function(t){e.$set(e.formData,"storeAddr",t)},expression:"formData.storeAddr"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{staticClass:"required",attrs:{label:"零售业态",prop:"saleType"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.saleType,callback:function(t){e.$set(e.formData,"saleType",t)},expression:"formData.saleType"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{staticClass:"required",attrs:{label:"特殊群体类型",prop:"groupType"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.groupType,callback:function(t){e.$set(e.formData,"groupType",t)},expression:"formData.groupType"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{staticClass:"required",attrs:{label:"地区类型",prop:"regionType"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.regionType,callback:function(t){e.$set(e.formData,"regionType",t)},expression:"formData.regionType"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{staticClass:"required",attrs:{label:"企业类型(组织形式)",prop:"organization"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.organization,callback:function(t){e.$set(e.formData,"organization",t)},expression:"formData.organization"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{staticClass:"required",attrs:{label:"经营面积(平方米/m²)",prop:"storeArea"}},[a("el-input-number",{staticClass:"full-width-input",attrs:{"controls-position":"right",min:0,max:1e4,precision:2,step:1},model:{value:e.formData.storeArea,callback:function(t){e.$set(e.formData,"storeArea",t)},expression:"formData.storeArea"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{attrs:{label:"是否连锁",prop:"isChainStore"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.isChainStore,callback:function(t){e.$set(e.formData,"isChainStore",t)},expression:"formData.isChainStore"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{attrs:{label:"是否总店",prop:"isHeadStore"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.isHeadStore,callback:function(t){e.$set(e.formData,"isHeadStore",t)},expression:"formData.isHeadStore"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{attrs:{label:"老许可证号",prop:"oldPermitId"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.oldPermitId,callback:function(t){e.$set(e.formData,"oldPermitId",t)},expression:"formData.oldPermitId"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{attrs:{label:"注册资本",prop:"capital"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.capital,callback:function(t){e.$set(e.formData,"capital",t)},expression:"formData.capital"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{attrs:{label:"代理人身份证类型",prop:"proxyType"}},[a("el-select",{staticClass:"full-width-input",model:{value:e.formData.proxyType,callback:function(t){e.$set(e.formData,"proxyType",t)},expression:"formData.proxyType"}},e._l(e.IdTypeOptions,(function(e,t){return a("el-option",{key:t,attrs:{label:e.label,value:e.value,disabled:e.disabled}})})),1)],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{attrs:{label:"代理人身份证号",prop:"proxyId"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.proxyId,callback:function(t){e.$set(e.formData,"proxyId",t)},expression:"formData.proxyId"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{attrs:{label:"代理人",prop:"proxy"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.proxy,callback:function(t){e.$set(e.formData,"proxy",t)},expression:"formData.proxy"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{staticClass:"required",attrs:{label:"首次制证日期",prop:"firstDate"}},[a("el-date-picker",{staticClass:"full-width-input",attrs:{type:"date",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",clearable:""},model:{value:e.formData.firstDate,callback:function(t){e.$set(e.formData,"firstDate",t)},expression:"formData.firstDate"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{staticClass:"required",attrs:{label:"许可证开始期限",prop:"permitStartDate"}},[a("el-date-picker",{staticClass:"full-width-input",attrs:{type:"date",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",clearable:""},model:{value:e.formData.permitStartDate,callback:function(t){e.$set(e.formData,"permitStartDate",t)},expression:"formData.permitStartDate"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{staticClass:"required",attrs:{label:"许可证截止期限",prop:"permitEndDate"}},[a("el-date-picker",{staticClass:"full-width-input",attrs:{type:"date",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",clearable:""},model:{value:e.formData.permitEndDate,callback:function(t){e.$set(e.formData,"permitEndDate",t)},expression:"formData.permitEndDate"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{staticClass:"required",attrs:{label:"当前状态",prop:"status"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.status,callback:function(t){e.$set(e.formData,"status",t)},expression:"formData.status"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{staticClass:"required",attrs:{label:"所属单位",prop:"dept"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.dept,callback:function(t){e.$set(e.formData,"dept",t)},expression:"formData.dept"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{staticClass:"required",attrs:{label:"注销日期",prop:"logoutDate"}},[a("el-date-picker",{staticClass:"full-width-input",attrs:{type:"date",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",clearable:""},model:{value:e.formData.logoutDate,callback:function(t){e.$set(e.formData,"logoutDate",t)},expression:"formData.logoutDate"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{attrs:{label:"暂停期限(日)",prop:"pauseDate"}},[a("el-date-picker",{staticClass:"full-width-input",attrs:{type:"date",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",clearable:""},model:{value:e.formData.pauseDate,callback:function(t){e.$set(e.formData,"pauseDate",t)},expression:"formData.pauseDate"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{attrs:{label:"停业日期起",prop:"closedStartDate"}},[a("el-date-picker",{staticClass:"full-width-input",attrs:{type:"date",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",clearable:""},model:{value:e.formData.closedStartDate,callback:function(t){e.$set(e.formData,"closedStartDate",t)},expression:"formData.closedStartDate"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{attrs:{label:"停业日期止",prop:"closedEndDate"}},[a("el-date-picker",{staticClass:"full-width-input",attrs:{type:"date",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",clearable:""},model:{value:e.formData.closedEndDate,callback:function(t){e.$set(e.formData,"closedEndDate",t)},expression:"formData.closedEndDate"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{attrs:{label:"专卖管理所",prop:"managerOffice"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.managerOffice,callback:function(t){e.$set(e.formData,"managerOffice",t)},expression:"formData.managerOffice"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{attrs:{label:"专卖员",prop:"manager"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.manager,callback:function(t){e.$set(e.formData,"manager",t)},expression:"formData.manager"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{attrs:{label:"所属网格",prop:"grid"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.grid,callback:function(t){e.$set(e.formData,"grid",t)},expression:"formData.grid"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{attrs:{label:"经度",prop:"longitude"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.longitude,callback:function(t){e.$set(e.formData,"longitude",t)},expression:"formData.longitude"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{attrs:{label:"纬度",prop:"latitude"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.latitude,callback:function(t){e.$set(e.formData,"latitude",t)},expression:"formData.latitude"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{attrs:{label:"品牌",prop:"brand"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.brand,callback:function(t){e.$set(e.formData,"brand",t)},expression:"formData.brand"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{attrs:{label:"备注",prop:"remark"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.remark,callback:function(t){e.$set(e.formData,"remark",t)},expression:"formData.remark"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:7}},[a("el-form-item",{attrs:{label:"信息填报时间",prop:"createDate"}},[a("el-date-picker",{staticClass:"full-width-input",attrs:{type:"date",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",clearable:""},model:{value:e.formData.createDate,callback:function(t){e.$set(e.formData,"createDate",t)},expression:"formData.createDate"}})],1)],1),a("el-col",{attrs:{span:7,offset:1}},[a("el-form-item",{attrs:{label:"信息来源",prop:"source"}},[a("el-input",{attrs:{type:"text",clearable:""},model:{value:e.formData.source,callback:function(t){e.$set(e.formData,"source",t)},expression:"formData.source"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:24}},[a("el-form-item",{attrs:{label:"商户照片",prop:"image"}},[a("img",{staticStyle:{"max-height":"700px",border:"1px solid #ccc","border-radius":"5px",padding:"5px"},attrs:{src:e.image}}),a("input",{ref:"uploadImg",staticStyle:{display:"none"},attrs:{type:"file",accept:"image/jpeg,image/png"}})])],1)],1),a("br"),a("el-row",[a("el-col",{staticClass:"btns",attrs:{span:24}},[a("el-button",{attrs:{type:"primary"},on:{click:e.changeImg}},[e._v("修改图片")]),a("el-button",{on:{click:e.resetForm}},[e._v("重置表单")]),a("el-button",{on:{click:e.goback}},[e._v("取消并返回")]),a("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("确定修改")])],1)],1)],1)],1)},l=[],o=a("c7eb"),s=a("1da1"),i=a("ade3"),n=(a("ac1f"),a("466d"),a("c19f"),a("ace4"),a("d3b7"),a("5cc6"),a("9a8c"),a("a975"),a("735e"),a("c1ac"),a("d139"),a("3a7b"),a("d5d6"),a("82f8"),a("e91f"),a("60bd"),a("5f96"),a("3280"),a("3fcc"),a("ca91"),a("25a1"),a("cd26"),a("3c5d"),a("2954"),a("649e"),a("219c"),a("170b"),a("b39a"),a("72f7"),a("b0c0"),a("3ca3"),a("ddb0"),a("2b3d"),a("9861"),a("4de4"),a("8492")),c=a("a695"),m={data:function(){var e;return{deliveryOptions:[{value:"中国烟草总公司山东省公司(电子烟)",label:"中国烟草总公司山东省公司(电子烟)"}],officeOptions:[{value:"兖州区烟草专卖局",label:"兖州区烟草专卖局"}],permitLimitOptions:[{value:"长期",label:"长期"},{value:"短期",label:"短期"}],formData:(e={licenseId:"",merchant:"",address:"",region:"",name:"",contact:"",tel:"",id:"",business:"",permitLimit:null,delivery:"",chargeDate:null,issueDate:null,gotDate:null,office:"",permitRange:"",ownership:"",renderStartDate:null,renderEndDate:null,storeIdType:"居民身份证",storeId:"",storeIdAddr:"",storeAddr:"",groupType:"",saleType:"",regionType:"",organization:"",storeArea:0,createDate:null,isChainStore:"",isHeadStore:"",oldPermitId:"",proxy:"",proxyType:"居民身份证",proxyId:"",firstDate:null,capital:"",permitStartDate:null,permitEndDate:null,status:"",logoutDate:null,dept:"",pauseDate:null,closedStartDate:null,closedEndDate:null,grid:"",managerOffice:"",manager:"",longitude:"",latitude:"",brand:""},Object(i["a"])(e,"createDate",null),Object(i["a"])(e,"source",""),Object(i["a"])(e,"remark",""),e),rules:{licenseId:[{required:!0,message:"许可证号不可为空"}],merchant:[{required:!0,message:"企业(字号)不可为空"}],name:[{required:!0,message:"负责人不可为空"}],contact:[{required:!0,message:"联系人不可为空"}],tel:[{required:!0,message:"联系电话不可为空"}],id:[{required:!0,message:"身份证号不可为空"},{pattern:/^[A-Za-z0-9]+$/,trigger:["blur","change"],message:"请输入身份证正确格式"}],business:[{required:!0,message:"工商营业证编号不可为空"},{pattern:/^[A-Za-z0-9]+$/,trigger:["blur","change"],message:"请输入正确格式"}],delivery:[{required:!0,message:"供货单位不可为空"}],chargeDate:[{required:!0,message:"制证日期不可为空"}],issueDate:[{required:!0,message:"发证日期不可为空"}],office:[{required:!0,message:"发证机关不可为空"}]},IdTypeOptions:[{label:"居民身份证",value:"居民身份证"},{label:"临时身份证",value:"临时身份证"},{label:"居民户口薄",value:"居民户口薄"},{label:"军官证",value:"军官证"},{label:"武警警官证",value:"武警警官证"},{label:"士兵证",value:"士兵证"},{label:"军队学员证",value:"军队学员证"},{label:"军队文职干部证",value:"军队文职干部证"},{label:"军队离退休干部证和军队职工证",value:"军队离退休干部证和军队职工证"},{label:"港澳同胞回乡证",value:"港澳同胞回乡证"},{label:"港澳居民来往内地通行证",value:"港澳居民来往内地通行证"},{label:"中华人民共和国往来港澳通行证",value:"中华人民共和国往来港澳通行证"},{label:"台湾居民来往大陆通行证",value:"台湾居民来往大陆通行证"},{label:"大陆居民往来台湾通行证",value:"大陆居民往来台湾通行证"},{label:"外国人居留证",value:"外国人居留证"}],mid:null,image:"",file:null}},created:function(){this.mid=this.$route.query.mid,this.getInfoByMid()},methods:{submitForm:function(){var e=this;this.$refs["merchantUploadForm"].validate(function(){var t=Object(s["a"])(Object(o["a"])().mark((function t(a){var r,l,s;return Object(o["a"])().wrap((function(t){while(1)switch(t.prev=t.next){case 0:if(a){t.next=3;break}return e.$message({message:"还有必填字段未填写",type:"warning"}),t.abrupt("return");case 3:return t.next=5,Object(n["f"])(e.formData);case 5:return r=t.sent,"200"!=r.code&&e.$message({message:r.msg,type:"error"}),l=new FormData,l.append("mid",e.mid),t.next=11,Object(n["e"])(l);case 11:s=t.sent,"200"!=s.code&&e.$message({message:s.msg,type:"error"}),e.$message({message:"商户信息修改成功",type:"success"}),e.$router.push("/example/merchant");case 15:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}())},resetForm:function(){this.$refs["merchantUploadForm"].resetFields(),this.getInfoByMid(),this.$refs["uploadImg"].value=""},goback:function(){this.$router.go(-1)},getInfoByMid:function(){var e=this;return Object(s["a"])(Object(o["a"])().mark((function t(){var a;return Object(o["a"])().wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,Object(n["d"])(e.mid);case 2:if(a=t.sent,200==a.code){t.next=6;break}return e.$message({message:a.msg,type:"error"}),t.abrupt("return");case 6:""==a.data.photo1||null==a.data.photo1?e.image="":e.image=JSON.parse(a.data.photo1)[0],e.formData=a.data;case 8:case"end":return t.stop()}}),t)})))()},base64ToBlob:function(e){for(var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"image/png",a=e.split(","),r=a[0].match(/:(.*?);/)[1]||t,l=window.atob(a[1]),o=new ArrayBuffer(l.length),s=new Uint8Array(o),i=0;i<l.length;i++)s[i]=l.charCodeAt(i);return new Blob([o],{type:r})},changeImg:function(){var e=this;this.$refs["uploadImg"].onchange=function(t){if(void 0!=t.srcElement.files[0]){e.file=t.srcElement.files[0];var a=1048576,r=0,l=new c["a"](r,a);l.compress(e.file,(function(t){var a=new FileReader;a.readAsDataURL(t),a.onload=function(){var t=Object(s["a"])(Object(o["a"])().mark((function t(a){var r,l,s,i;return Object(o["a"])().wrap((function(t){while(1)switch(t.prev=t.next){case 0:return r=e.file.name,e.file=e.base64ToBlob(this.result),l=new FormData,l.append("file",e.file,r),l.append("mid",e.mid),t.next=7,Object(n["g"])(l);case 7:if(s=t.sent,200==s.code){t.next=11;break}return e.$message({message:s.msg,type:"error"}),t.abrupt("return");case 11:i=window.URL||window.webkitURL,e.image=i.createObjectURL(e.file);case 13:case"end":return t.stop()}}),t,this)})));return function(e){return t.apply(this,arguments)}}()}))}},this.$refs["uploadImg"].click()},deliveryQuerySearch:function(e,t){var a=this.deliveryOptions,r=e?a.filter(this.createFilter(e)):a;t(r)},createFilter:function(e){return function(t){return 0===t.value.toLowerCase().indexOf(e.toLowerCase())}}}},p=m,f=(a("feb3"),a("9a68"),a("2877")),d=Object(f["a"])(p,r,l,!1,null,"6a5d500b",null);t["default"]=d.exports}}]);