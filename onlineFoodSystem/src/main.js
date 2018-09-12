// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import App from './App'
import vueResource from 'vue-resource'
import router from './router/index'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/mdui-v0.3.0/css/mdui.min.css'
import star from './components/common/star/star'
import './assets/base.js'
import './assets/base.css'
import 'font-awesome/css/font-awesome.css'
import store from './store/store'

Vue.config.productionTip = false
Vue.use(vueResource);
Vue.use(ElementUI);
Vue.component('star',star);

// router.beforeEach((to, from, next) => {
//   if(!login){
//     next("/login");
//   }else{
//     next();
//   }
// });

Vue.http.interceptors.push((request, next) => {
  console.log('被拦截了额!');
  console.log(window.localStorage.getItem('token'));
  if (window.localStorage.getItem('token')) {
    console.log('有哦')
    // Vue.http.options.headers.Authorization='Bearer ' + window.localStorage.getItem('token')
    // request.credentials = true;
    // request.headers.set('Authorization', 'Bearer ' + window.localStorage.getItem('token'));
    Vue.http.headers.common['Authorization'] = 'Bearer ' + window.localStorage.getItem('token')
  }
  next((response) => {
    if (response.status === 401) {
      console.log('状态401')
      del('token')
      window.location.pathname = '/front/login'
    }
  })
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
});
