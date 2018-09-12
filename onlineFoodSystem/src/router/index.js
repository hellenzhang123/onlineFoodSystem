import Vue from 'vue'
import Router from 'vue-router'
import ratings from '@/components/ratings'
import goods from '@/components/goods/goods'
import stores from '@/components/stores/stores'
import seller from '@/components/seller'
import order from '@/components/order/order'
import business from '@/components/business/business'
import frontStore from '@/components/frontStore/frontStore'
import frontMenu from '@/components/frontStore/frontMenu'
import shopCart from '@/components/frontStore/shopCart'
import shopOrder from '@/components/frontStore/shopOrder'
import info from '@/components/frontStore/info'
import address from '@/components/frontStore/address'
import myOrder from '@/components/frontStore/myOrder'
import register from '@/components/user/register'
import login from '@/components/user/login'
import avatar from '@/components/frontStore/avatar'
import orderResult from '@/components/frontStore/orderResult'


Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/front/frontStore'
    },
    {
      path: '/backend',
      redirect: '/backend/business'
    },
    {
      path: '/front/avatar',
      component: avatar
    },
    {
      path: '/front/orderResult',
      component: orderResult
    },
    {
      path: '/login',
      component: login
    },
    {
      path: '/register',
      component: register
    },
    {
      path: '/front/myOrder',
      component: myOrder
    },
    {
      path: '/front/address',
      component: address
    },
    {
      path: '/front/info',
      component: info
    },
    {
      path: '/front/shopOrder/:oid',
      name: 'shopOrder',
      component: shopOrder
    },
    {
      path: '/front/shopCart',
      component: shopCart
    },
    {
      path: '/front/frontStore',
      component: frontStore
    },
    {
      path: '/front/frontMenu/:id',
      name:'menu',
      component: frontMenu
    },
    {
      path: '/backend/ratings',
      component: ratings
    },
    {
      path: '/backend/goods',
      component: goods
    },
    {
      path: '/backend/stores',
      component: stores
    },
    {
      path: '/backend/seller',
      component: seller
    },
    {
      path: '/backend/order',
      component: order
    },
    {
      path: '/backend/business',
      component: business
    }
  ],
  linkActiveClass: 'active'
})
