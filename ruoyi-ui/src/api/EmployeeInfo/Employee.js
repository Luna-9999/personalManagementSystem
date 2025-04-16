import request from '@/utils/request'

export function listEmployee(query) {
  return request({
    url: '/EmployeeInfo/Employee/list',
    method: 'get',
    params: query
  })
}

export function getEmployee(id) {
  return request({
    url: '/EmployeeInfo/Employee/' + id,
    method: 'get'
  })
}

export function addEmployee(data) {
  return request({
    url: '/EmployeeInfo/Employee',
    method: 'post',
    data: data
  })
}

export function updateEmployee(data) {
  return request({
    url: '/EmployeeInfo/Employee',
    method: 'put',
    data: data
  })
}

export function delEmployee(id) {
  return request({
    url: '/EmployeeInfo/Employee/' + id,
    method: 'delete'
  })
}
