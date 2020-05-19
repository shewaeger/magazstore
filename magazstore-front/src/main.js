import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from './router'
import VueAxios from './plugins/axios'
import VueLodash from './plugins/lodash'
import 'vuetify/dist/vuetify.min.css'

Vue.config.productionTip = false
Vue.use(VueAxios);
Vue.use(VueLodash);
new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')
