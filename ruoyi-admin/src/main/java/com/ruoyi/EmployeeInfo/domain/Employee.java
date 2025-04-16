package com.ruoyi.EmployeeInfo.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 职工基本信息对象 employee
 * 
 * @author carmellia
 * @date 2025-04-14
 */
public class Employee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 默认编号 */
    private Long id;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idNumber;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthDate;

    /** 预计退休日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计退休日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date retirementDate;

    /** 部门 */
    @Excel(name = "部门")
    private String department;

    /** 职位 */
    @Excel(name = "职位")
    private String position;

    /** 是否正式 */
    @Excel(name = "是否正式")
    private String isFormal;

    /** 人员类型（1、2、3、4） */
    @Excel(name = "人员类型", readConverterExp = "1=、2、3、4")
    private Long personType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setIdNumber(String idNumber) 
    {
        this.idNumber = idNumber;
    }

    public String getIdNumber() 
    {
        return idNumber;
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

    public void setRetirementDate(Date retirementDate) 
    {
        this.retirementDate = retirementDate;
    }

    public Date getRetirementDate() 
    {
        return retirementDate;
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

    public void setPersonType(Long personType) 
    {
        this.personType = personType;
    }

    public Long getPersonType() 
    {
        return personType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("idNumber", getIdNumber())
            .append("name", getName())
            .append("gender", getGender())
            .append("birthDate", getBirthDate())
            .append("retirementDate", getRetirementDate())
            .append("department", getDepartment())
            .append("position", getPosition())
            .append("isFormal", getIsFormal())
            .append("personType", getPersonType())
            .toString();
    }
}
