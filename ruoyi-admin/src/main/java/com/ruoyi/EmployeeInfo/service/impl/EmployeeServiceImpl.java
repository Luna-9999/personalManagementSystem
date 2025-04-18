package com.ruoyi.EmployeeInfo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.EmployeeInfo.mapper.EmployeeMapper;
import com.ruoyi.EmployeeInfo.domain.Employee;
import com.ruoyi.EmployeeInfo.service.IEmployeeService;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 职工基本信息Service业务层处理
 * 
 * @author carmellia
 * @contributor Luna-9999
 * @date 2025-04-18
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService 
{
    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

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

    /**
     * 导入职工数据
     * 
     * @param employeeList 职工数据列表
     * @param updateSupport 是否支持更新
     * @param operName 操作人
     * @return 结果
     */
    @Override
    public String importEmployee(List<Employee> employeeList, boolean updateSupport, String operName)
    {
        if (StringUtils.isNull(employeeList) || employeeList.size() == 0)
        {
            throw new ServiceException("导入职工数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        for (Employee employee : employeeList)
        {
            try
            {
                // 验证是否存在该身份证号的职工
                Employee existEmployee = employeeMapper.selectEmployeeByIdNumber(employee.getIdNumber());
                if (StringUtils.isNull(existEmployee))
                {
                    employee.setCreateBy(operName);
                    this.insertEmployee(employee);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、身份证号 " + employee.getIdNumber() + " 的职工 " + employee.getName() + " 导入成功");
                }
                else if (updateSupport)
                {
                    employee.setId(existEmployee.getId());
                    employee.setUpdateBy(operName);
                    this.updateEmployee(employee);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、身份证号 " + employee.getIdNumber() + " 的职工 " + employee.getName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、身份证号 " + employee.getIdNumber() + " 的职工 " + employee.getName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、身份证号 " + employee.getIdNumber() + " 的职工 " + employee.getName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }

        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
