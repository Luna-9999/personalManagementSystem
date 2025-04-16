package com.ruoyi.personalManage.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 退休年龄政策对照对象 retirement_age_reference
 * 
 * @author ruoyi
 * @date 2025-04-12
 */
public class RetirementAgeReference extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 人员类别(1-正式男性 2-正式女性 3-临聘男性 4-临聘女性) */
    private Long employeeCategory;

    /** 出生年月 */
    private Date birthDate;

    /** 标准退休年月 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "标准退休年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date standardRetireDate;

    public void setEmployeeCategory(Long employeeCategory) 
    {
        this.employeeCategory = employeeCategory;
    }

    public Long getEmployeeCategory() 
    {
        return employeeCategory;
    }
    public void setBirthDate(Date birthDate) 
    {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() 
    {
        return birthDate;
    }
    public void setStandardRetireDate(Date standardRetireDate) 
    {
        this.standardRetireDate = standardRetireDate;
    }

    public Date getStandardRetireDate() 
    {
        return standardRetireDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("employeeCategory", getEmployeeCategory())
            .append("birthDate", getBirthDate())
            .append("standardRetireDate", getStandardRetireDate())
            .toString();
    }
}
