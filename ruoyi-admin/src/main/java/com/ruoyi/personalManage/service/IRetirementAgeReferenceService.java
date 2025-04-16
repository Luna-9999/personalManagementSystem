package com.ruoyi.personalManage.service;

import java.util.List;
import com.ruoyi.personalManage.domain.RetirementAgeReference;

/**
 * 退休年龄政策对照Service接口
 * 
 * @author ruoyi
 * @date 2025-04-12
 */
public interface IRetirementAgeReferenceService 
{
    /**
     * 查询退休年龄政策对照
     * 
     * @param employeeCategory 退休年龄政策对照主键
     * @return 退休年龄政策对照
     */
    public RetirementAgeReference selectRetirementAgeReferenceByEmployeeCategory(Long employeeCategory);

    /**
     * 查询退休年龄政策对照列表
     * 
     * @param retirementAgeReference 退休年龄政策对照
     * @return 退休年龄政策对照集合
     */
    public List<RetirementAgeReference> selectRetirementAgeReferenceList(RetirementAgeReference retirementAgeReference);

    /**
     * 新增退休年龄政策对照
     * 
     * @param retirementAgeReference 退休年龄政策对照
     * @return 结果
     */
    public int insertRetirementAgeReference(RetirementAgeReference retirementAgeReference);

    /**
     * 修改退休年龄政策对照
     * 
     * @param retirementAgeReference 退休年龄政策对照
     * @return 结果
     */
    public int updateRetirementAgeReference(RetirementAgeReference retirementAgeReference);

    /**
     * 批量删除退休年龄政策对照
     * 
     * @param employeeCategorys 需要删除的退休年龄政策对照主键集合
     * @return 结果
     */
    public int deleteRetirementAgeReferenceByEmployeeCategorys(Long[] employeeCategorys);

    /**
     * 删除退休年龄政策对照信息
     * 
     * @param employeeCategory 退休年龄政策对照主键
     * @return 结果
     */
    public int deleteRetirementAgeReferenceByEmployeeCategory(Long employeeCategory);
}
