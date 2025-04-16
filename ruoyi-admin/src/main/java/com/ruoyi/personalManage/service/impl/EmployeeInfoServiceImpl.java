package com.ruoyi.personalManage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.personalManage.mapper.EmployeeInfoMapper;
import com.ruoyi.personalManage.domain.EmployeeInfo;
import com.ruoyi.personalManage.service.IEmployeeInfoService;

/**
 * 人员基本信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-04-12
 */
@Service
public class EmployeeInfoServiceImpl implements IEmployeeInfoService 
{
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    /**
     * 查询人员基本信息
     * 
     * @param idCard 人员基本信息主键
     * @return 人员基本信息
     */
    @Override
    public EmployeeInfo selectEmployeeInfoByIdCard(String idCard)
    {
        return employeeInfoMapper.selectEmployeeInfoByIdCard(idCard);
    }

    /**
     * 查询人员基本信息列表
     * 
     * @param employeeInfo 人员基本信息
     * @return 人员基本信息
     */
    @Override
    public List<EmployeeInfo> selectEmployeeInfoList(EmployeeInfo employeeInfo)
    {
        return employeeInfoMapper.selectEmployeeInfoList(employeeInfo);
    }

    /**
     * 新增人员基本信息
     * 
     * @param employeeInfo 人员基本信息
     * @return 结果
     */
    @Override
    public int insertEmployeeInfo(EmployeeInfo employeeInfo)
    {
        return employeeInfoMapper.insertEmployeeInfo(employeeInfo);
    }

    /**
     * 修改人员基本信息
     * 
     * @param employeeInfo 人员基本信息
     * @return 结果
     */
    @Override
    public int updateEmployeeInfo(EmployeeInfo employeeInfo)
    {
        return employeeInfoMapper.updateEmployeeInfo(employeeInfo);
    }

    /**
     * 批量删除人员基本信息
     * 
     * @param idCards 需要删除的人员基本信息主键
     * @return 结果
     */
    @Override
    public int deleteEmployeeInfoByIdCards(String[] idCards)
    {
        return employeeInfoMapper.deleteEmployeeInfoByIdCards(idCards);
    }

    /**
     * 删除人员基本信息信息
     * 
     * @param idCard 人员基本信息主键
     * @return 结果
     */
    @Override
    public int deleteEmployeeInfoByIdCard(String idCard)
    {
        return employeeInfoMapper.deleteEmployeeInfoByIdCard(idCard);
    }
}
