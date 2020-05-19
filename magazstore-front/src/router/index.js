import Vue from 'vue'
import VueRouter from 'vue-router'
import Dashboard from "../components/Dashboard";
import Categories from "../components/Categories";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Dashboard,
        meta: {
            showInMenu: true,
            header: 'Главная',
            icon: 'mdi-home',
        }
    },
    {
        path: '/categories',
        name: 'Categories',
        component: Categories,
        meta: {
            showInMenu: true,
            header: 'Категории',
            icon: 'mdi-view-list'
        }
    }
]

const router = new VueRouter({
    mode: 'history',
    routes
})

export default router
