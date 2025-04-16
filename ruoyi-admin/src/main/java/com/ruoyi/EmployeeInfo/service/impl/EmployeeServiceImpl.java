package com.ruoyi.EmployeeInfo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.EmployeeInfo.mapper.EmployeeMapper;
import com.ruoyi.EmployeeInfo.domain.Employee;
import com.ruoyi.EmployeeInfo.service.IEmployeeService;

/**
 * 职工基本信息Service业务层处理
 * 
 * @author carmellia
 * @date 2025-04-14
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService 
{
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 查询职工基本信息
     * 
     * @param id 职工基本信息主键
     * @return 职工基本信息
     */
    @Override
    public Employee selectEmployeeById(Long id)
    {
        return employeeMapper.selectEmployeeById(id);
    }

    /**
     * 查询职工基本信息列表
     * 
     * @param employee 职工基本信息
     * @return 职工基本信息
     */
    @Override
    public List<Employee> selectEmployeeList(Employee employee)
    {
        return employeeMapper.selectEmployeeList(employee);
    }

    /**
     * 新增职工基本信息
     * 
     * @param employee 职工基本信息
     * @return 结果
     */
    @Override
    public int insertEmployee(Employee employee)
    {
        return employeeMapper.insertEmployee(employee);
    }

    /**
     * 修改职工基本信息
     * 
     * @param employee 职工基本信息
     * @return 结果
     */
    @Override
    public int updateEmployee(Employee employee)
    {
        return employeeMapper.updateEmployee(employee);
    }

    /**
     * 批量删除职工基本信息
     * 
     * @param ids 需要删除的职工基本信息主键
     * @return 结果
     */
    @Override
    public int deleteEmployeeByIds(Long[] ids)
    {
        return employeeMapper.deleteEmployeeByIds(ids);
    }

    /**
     * 删除职工基本信息信息
     * 
     * @param id 职工基本信息主键
     * @return 结果
     */
    @Override
    public int deleteEmployeeById(Long id)
    {
        return employeeMapper.deleteEmployeeById(id);
    }
}
