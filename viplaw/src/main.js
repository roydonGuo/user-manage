import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.config.productionTip = false
Vue.use(ElementUI);

import request from "@/utils/request"
Vue.prototype.request = request;

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')