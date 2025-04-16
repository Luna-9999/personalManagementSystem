package com.ruoyi.personalManage.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 人员基本信息对象 employee_info
 * 
 * @author ruoyi
 * @date 2025-04-12
 */
public class EmployeeInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 身份证号（唯一主键） */
    @Excel(name = "身份证号", readConverterExp = "唯=一主键")
    private String idCard;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 出生年月 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthDate;

    /** 预计退休年月 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计退休年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date estimatedRetireDate;

    /** 部门 */
    @Excel(name = "部门")
    private String department;

    /** 职务 */
    @Excel(name = "职务")
    private String position;

    /** 是否正式人员 */
    @Excel(name = "是否正式人员")
    private String isFormal;

    /** 人员类别(1-正式男性 2-正式女性 3-临聘男性 4-临聘女性) */
    @Excel(name = "人员类别(1-正式男性 2-正式女性 3-临聘男性 4-临聘女性)")
    private Long employeeCategory;

    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }

    public void setBirthDate(Date birthDate) 
    {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() 
    {
        return birthDate;
    }

    public void setEstimatedRetireDate(Date estimatedRetireDate) 
    {
        this.estimatedRetireDate = estimatedRetireDate;
    }

    public Date getEstimatedRetireDate() 
    {
        return estimatedRetireDate;
    }

    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }

    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getPosition() 
    {
        return position;
    }

    public void setIsFormal(String isFormal) 
    {
        this.isFormal = isFormal;
    }

    public String getIsFormal() 
    {
        return isFormal;
    }

    public void setEmployeeCategory(Long employeeCategory) 
    {
        this.employeeCategory = employeeCategory;
    }

    public Long getEmployeeCategory() 
    {
        return employeeCategory;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("idCard", getIdCard())
            .append("name", getName())
            .append("gender", getGender())
            .append("birthDate", getBirthDate())
            .append("estimatedRetireDate", getEstimatedRetireDate())
            .append("department", getDepartment())
            .append("position", getPosition())
            .append("isFormal", getIsFormal())
            .append("employeeCategory", getEmployeeCategory())
            .toString();
    }
}
