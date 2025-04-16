import request from '@/utils/request'

// 查询退休年龄政策对照列表
export function listReference(query) {
  return request({
    url: '/personalManage/reference/list',
    method: 'get',
    params: query
  })
}

// 查询退休年龄政策对照详细
export function getReference(employeeCategory) {
  return request({
    url: '/personalManage/reference/' + employeeCategory,
    method: 'get'
  })
}

// 新增退休年龄政策对照
export function addReference(data) {
  return request({
    url: '/personalManage/reference',
    method: 'post',
    data: data
  })
}

// 修改退休年龄政策对照
export function updateReference(data) {
  return request({
    url: '/personalManage/reference',
    method: 'put',
    data: data
  })
}

// 删除退休年龄政策对照
export function delReference(employeeCategory) {
  return request({
    url: '/personalManage/reference/' + employeeCategory,
    method: 'delete'
  })
}
