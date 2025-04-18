package com.ruoyi.EmployeeInfo.service;

import java.util.List;
import com.ruoyi.EmployeeInfo.domain.Employee;

/**
 * 职工基本信息Service接口
 * 
 * @author carmellia
 * @contributor Luna-9999
 * @date 2025-04-18
 */
public interface IEmployeeService 
{
    /**
     * 查询职工基本信息
     * 
     * @param id 职工基本信息主键
     * @return 职工基本信息
     */
    public Employee selectEmployeeById(Long id);

    /**
     * 查询职工基本信息列表
     * 
     * @param employee 职工基本信息
     * @return 职工基本信息集合
     */
    public List<Employee> selectEmployeeList(Employee employee);

    /**
     * 新增职工基本信息
     * 
     * @param employee 职工基本信息
     * @return 结果
     */
    public int insertEmployee(Employee employee);

    /**
     * 修改职工基本信息
     * 
     * @param employee 职工基本信息
     * @return 结果
     */
    public int updateEmployee(Employee employee);

    /**
     * 批量删除职工基本信息
     * 
     * @param ids 需要删除的职工基本信息主键集合
     * @return 结果
     */
    public int deleteEmployeeByIds(Long[] ids);

    /**
     * 删除职工基本信息信息
     * 
     * @param id 职工基本信息主键
     * @return 结果
     */
    public int deleteEmployeeById(Long id);

    /**
     * 导入职工数据
     * 
     * @param employeeList 职工数据列表
     * @param updateSupport 是否支持更新
     * @param operName 操作人
     * @return 结果
     */
    public String importEmployee(List<Employee> employeeList, boolean updateSupport, String operName);
}
