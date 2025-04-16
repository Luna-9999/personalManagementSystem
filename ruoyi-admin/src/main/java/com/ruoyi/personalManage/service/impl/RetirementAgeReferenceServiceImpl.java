package com.ruoyi.personalManage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.personalManage.mapper.RetirementAgeReferenceMapper;
import com.ruoyi.personalManage.domain.RetirementAgeReference;
import com.ruoyi.personalManage.service.IRetirementAgeReferenceService;

/**
 * 退休年龄政策对照Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-04-12
 */
@Service
public class RetirementAgeReferenceServiceImpl implements IRetirementAgeReferenceService 
{
    @Autowired
    private RetirementAgeReferenceMapper retirementAgeReferenceMapper;

    /**
     * 查询退休年龄政策对照
     * 
     * @param employeeCategory 退休年龄政策对照主键
     * @return 退休年龄政策对照
     */
    @Override
    public RetirementAgeReference selectRetirementAgeReferenceByEmployeeCategory(Long employeeCategory)
    {
        return retirementAgeReferenceMapper.selectRetirementAgeReferenceByEmployeeCategory(employeeCategory);
    }

    /**
     * 查询退休年龄政策对照列表
     * 
     * @param retirementAgeReference 退休年龄政策对照
     * @return 退休年龄政策对照
     */
    @Override
    public List<RetirementAgeReference> selectRetirementAgeReferenceList(RetirementAgeReference retirementAgeReference)
    {
        return retirementAgeReferenceMapper.selectRetirementAgeReferenceList(retirementAgeReference);
    }

    /**
     * 新增退休年龄政策对照
     * 
     * @param retirementAgeReference 退休年龄政策对照
     * @return 结果
     */
    @Override
    public int insertRetirementAgeReference(RetirementAgeReference retirementAgeReference)
    {
        return retirementAgeReferenceMapper.insertRetirementAgeReference(retirementAgeReference);
    }

    /**
     * 修改退休年龄政策对照
     * 
     * @param retirementAgeReference 退休年龄政策对照
     * @return 结果
     */
    @Override
    public int updateRetirementAgeReference(RetirementAgeReference retirementAgeReference)
    {
        return retirementAgeReferenceMapper.updateRetirementAgeReference(retirementAgeReference);
    }

    /**
     * 批量删除退休年龄政策对照
     * 
     * @param employeeCategorys 需要删除的退休年龄政策对照主键
     * @return 结果
     */
    @Override
    public int deleteRetirementAgeReferenceByEmployeeCategorys(Long[] employeeCategorys)
    {
        return retirementAgeReferenceMapper.deleteRetirementAgeReferenceByEmployeeCategorys(employeeCategorys);
    }

    /**
     * 删除退休年龄政策对照信息
     * 
     * @param employeeCategory 退休年龄政策对照主键
     * @return 结果
     */
    @Override
    public int deleteRetirementAgeReferenceByEmployeeCategory(Long employeeCategory)
    {
        return retirementAgeReferenceMapper.deleteRetirementAgeReferenceByEmployeeCategory(employeeCategory);
    }
}
