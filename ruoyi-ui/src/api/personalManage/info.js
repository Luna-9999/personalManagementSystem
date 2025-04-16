import request from '@/utils/request'

// 查询人员基本信息列表
export function listInfo(query) {
  return request({
    url: '/personalManage/info/list',
    method: 'get',
    params: query
  })
}

// 查询人员基本信息详细
export function getInfo(idCard) {
  return request({
    url: '/personalManage/info/' + idCard,
    method: 'get'
  })
}

// 新增人员基本信息
export function addInfo(data) {
  return request({
    url: '/personalManage/info',
    method: 'post',
    data: data
  })
}

// 修改人员基本信息
export function updateInfo(data) {
  return request({
    url: '/personalManage/info',
    method: 'put',
    data: data
  })
}

// 删除人员基本信息
export function delInfo(idCard) {
  return request({
    url: '/personalManage/info/' + idCard,
    method: 'delete'
  })
}
