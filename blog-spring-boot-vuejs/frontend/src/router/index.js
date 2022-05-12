import Article from '@/components/Article'
import Categories from '@/components/Categories'
import Dashboard from '@/components/Dashboard'
import Editor from '@/components/Editor'
import Login from '@/components/Login'
import Vue from 'vue'
import Router from 'vue-router'
import store from '../store'
import * as types from '../store/mutation-types'

const hasToken = (to, from, next) => {
  let token = localStorage.getItem('JWT')
  let username = localStorage.getItem('username')
  if (token) {
    store.commit(types.LOGIN_SUCCESS, {token, username})
    router.push('/Dashboard')
  } else {
    next()
  }
}

const requireAuth = (to, from, next) => {
  if (store.getters.isLoggedIn) {
    next()
  } else {
    router.push('/')
  }
}

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'dashboard',
      component: Dashboard
    },
    {
      path: '/articles/editor',
      name: 'editor',
      component: Editor,
      beforeEnter: requireAuth
    },
    {
      path: '/articles/:slug',
      name: 'articles',
      component: Article
    },
    {
      path: '/categories/:category',
      name: 'categories',
      component: Categories
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
      beforeEnter: hasToken
    },
    {
      path: '/*',
      redirect: '/'
    }
  ]
})

export default router
