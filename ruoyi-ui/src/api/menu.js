import request from '@/utils/request'

// 获取路由
export const getRouter = () => {
  return request({
    url: '/getRouter',
    method: 'get'
  })
}