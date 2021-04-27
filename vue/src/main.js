import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Resource from 'vue-resource';

Vue.use(ElementUI);
Vue.use(Resource);

var axios = require('axios');
axios.defaults.baseURL = 'http://localhost:8080/api';
Vue.prototype.$axios = axios;// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
