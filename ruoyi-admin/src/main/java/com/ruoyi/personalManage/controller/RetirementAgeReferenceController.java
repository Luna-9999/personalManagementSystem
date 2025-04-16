package com.ruoyi.personalManage.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.personalManage.domain.RetirementAgeReference;
import com.ruoyi.personalManage.service.IRetirementAgeReferenceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 退休年龄政策对照Controller
 * 
 * @author ruoyi
 * @date 2025-04-12
 */
@RestController
@RequestMapping("/personalManage/reference")
public class RetirementAgeReferenceController extends BaseController
{
    @Autowired
    private IRetirementAgeReferenceService retirementAgeReferenceService;

    /**
     * 查询退休年龄政策对照列表
     */
    @PreAuthorize("@ss.hasPermi('personalManage:reference:list')")
    @GetMapping("/list")
    public TableDataInfo list(RetirementAgeReference retirementAgeReference)
    {
        startPage();
        List<RetirementAgeReference> list = retirementAgeReferenceService.selectRetirementAgeReferenceList(retirementAgeReference);
        return getDataTable(list);
    }

    /**
     * 导出退休年龄政策对照列表
     */
    @PreAuthorize("@ss.hasPermi('personalManage:reference:export')")
    @Log(title = "退休年龄政策对照", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RetirementAgeReference retirementAgeReference)
    {
        List<RetirementAgeReference> list = retirementAgeReferenceService.selectRetirementAgeReferenceList(retirementAgeReference);
        ExcelUtil<RetirementAgeReference> util = new ExcelUtil<RetirementAgeReference>(RetirementAgeReference.class);
        util.exportExcel(response, list, "退休年龄政策对照数据");
    }

    /**
     * 获取退休年龄政策对照详细信息
     */
    @PreAuthorize("@ss.hasPermi('personalManage:reference:query')")
    @GetMapping(value = "/{employeeCategory}")
    public AjaxResult getInfo(@PathVariable("employeeCategory") Long employeeCategory)
    {
        return success(retirementAgeReferenceService.selectRetirementAgeReferenceByEmployeeCategory(employeeCategory));
    }

    /**
     * 新增退休年龄政策对照
     */
    @PreAuthorize("@ss.hasPermi('personalManage:reference:add')")
    @Log(title = "退休年龄政策对照", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RetirementAgeReference retirementAgeReference)
    {
        return toAjax(retirementAgeReferenceService.insertRetirementAgeReference(retirementAgeReference));
    }

    /**
     * 修改退休年龄政策对照
     */
    @PreAuthorize("@ss.hasPermi('personalManage:reference:edit')")
    @Log(title = "退休年龄政策对照", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RetirementAgeReference retirementAgeReference)
    {
        return toAjax(retirementAgeReferenceService.updateRetirementAgeReference(retirementAgeReference));
    }

    /**
     * 删除退休年龄政策对照
     */
    @PreAuthorize("@ss.hasPermi('personalManage:reference:remove')")
    @Log(title = "退休年龄政策对照", businessType = BusinessType.DELETE)
	@DeleteMapping("/{employeeCategorys}")
    public AjaxResult remove(@PathVariable Long[] employeeCategorys)
    {
        return toAjax(retirementAgeReferenceService.deleteRetirementAgeReferenceByEmployeeCategorys(employeeCategorys));
    }
}
