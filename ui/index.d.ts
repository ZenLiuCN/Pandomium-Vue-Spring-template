import Vue from 'vue'
import VueRouter, {Route} from 'vue-router'
import {Store} from 'vuex'
import axios from '@nuxtjs/axios'
declare module '*.vue' {
    const _default: Vue
    export default _default
}
declare module 'vue/types/vue' {
    interface Vue {
        $router: VueRouter
        $route: Route
        $store:Store<any>
        $axios:axios
    }
}
