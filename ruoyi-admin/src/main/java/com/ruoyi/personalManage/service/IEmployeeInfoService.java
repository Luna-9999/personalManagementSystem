package com.ruoyi.personalManage.service;

import java.util.List;
import com.ruoyi.personalManage.domain.EmployeeInfo;

/**
 * 人员基本信息Service接口
 * 
 * @author ruoyi
 * @date 2025-04-12
 */
public interface IEmployeeInfoService 
{
    /**
     * 查询人员基本信息
     * 
     * @param idCard 人员基本信息主键
     * @return 人员基本信息
     */
    public EmployeeInfo selectEmployeeInfoByIdCard(String idCard);

    /**
     * 查询人员基本信息列表
     * 
     * @param employeeInfo 人员基本信息
     * @return 人员基本信息集合
     */
    public List<EmployeeInfo> selectEmployeeInfoList(EmployeeInfo employeeInfo);

    /**
     * 新增人员基本信息
     * 
     * @param employeeInfo 人员基本信息
     * @return 结果
     */
    public int insertEmployeeInfo(EmployeeInfo employeeInfo);

    /**
     * 修改人员基本信息
     * 
     * @param employeeInfo 人员基本信息
     * @return 结果
     */
    public int updateEmployeeInfo(EmployeeInfo employeeInfo);

    /**
     * 批量删除人员基本信息
     * 
     * @param idCards 需要删除的人员基本信息主键集合
     * @return 结果
     */
    public int deleteEmployeeInfoByIdCards(String[] idCards);

    /**
     * 删除人员基本信息信息
     * 
     * @param idCard 人员基本信息主键
     * @return 结果
     */
    public int deleteEmployeeInfoByIdCard(String idCard);
}
