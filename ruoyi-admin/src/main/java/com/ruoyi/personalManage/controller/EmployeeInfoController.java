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
import com.ruoyi.personalManage.domain.EmployeeInfo;
import com.ruoyi.personalManage.service.IEmployeeInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 人员基本信息Controller
 * 
 * @author ruoyi
 * @date 2025-04-12
 */
@RestController
@RequestMapping("/personalManage/info")
public class EmployeeInfoController extends BaseController
{
    @Autowired
    private IEmployeeInfoService employeeInfoService;

    /**
     * 查询人员基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('personalManage:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(EmployeeInfo employeeInfo)
    {
        startPage();
        List<EmployeeInfo> list = employeeInfoService.selectEmployeeInfoList(employeeInfo);
        return getDataTable(list);
    }

    /**
     * 导出人员基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('personalManage:info:export')")
    @Log(title = "人员基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EmployeeInfo employeeInfo)
    {
        List<EmployeeInfo> list = employeeInfoService.selectEmployeeInfoList(employeeInfo);
        ExcelUtil<EmployeeInfo> util = new ExcelUtil<EmployeeInfo>(EmployeeInfo.class);
        util.exportExcel(response, list, "人员基本信息数据");
    }

    /**
     * 获取人员基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('personalManage:info:query')")
    @GetMapping(value = "/{idCard}")
    public AjaxResult getInfo(@PathVariable("idCard") String idCard)
    {
        return success(employeeInfoService.selectEmployeeInfoByIdCard(idCard));
    }

    /**
     * 新增人员基本信息
     */
    @PreAuthorize("@ss.hasPermi('personalManage:info:add')")
    @Log(title = "人员基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EmployeeInfo employeeInfo)
    {
        return toAjax(employeeInfoService.insertEmployeeInfo(employeeInfo));
    }

    /**
     * 修改人员基本信息
     */
    @PreAuthorize("@ss.hasPermi('personalManage:info:edit')")
    @Log(title = "人员基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EmployeeInfo employeeInfo)
    {
        return toAjax(employeeInfoService.updateEmployeeInfo(employeeInfo));
    }

    /**
     * 删除人员基本信息
     */
    @PreAuthorize("@ss.hasPermi('personalManage:info:remove')")
    @Log(title = "人员基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{idCards}")
    public AjaxResult remove(@PathVariable String[] idCards)
    {
        return toAjax(employeeInfoService.deleteEmployeeInfoByIdCards(idCards));
    }
}
