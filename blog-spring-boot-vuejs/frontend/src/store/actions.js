import * as types from './mutation-types'
import router from '../router'
import { AXIOS } from '../helper/http-helper'
import qs from 'qs'

const login = ({commit}, creds) => {
  commit(types.LOGIN)
  return AXIOS.post('/api/users/login', qs.stringify(creds))
}

const logout = ({commit}) => {
  commit(types.LOGOUT)
  router.push('/')
}

export default {
  [types.LOGIN]: login,
  [types.LOGOUT]: logout
}
