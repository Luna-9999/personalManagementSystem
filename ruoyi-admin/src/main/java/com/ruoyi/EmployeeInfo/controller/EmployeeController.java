package com.ruoyi.EmployeeInfo.controller;

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
import com.ruoyi.EmployeeInfo.domain.Employee;
import com.ruoyi.EmployeeInfo.service.IEmployeeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 职工基本信息Controller
 * 
 * @author carmellia
 * @contributor Luna-9999
 * @date 2025-04-18
 */
@RestController
@RequestMapping("/EmployeeInfo/Employee")
public class EmployeeController extends BaseController
{
    @Autowired
    private IEmployeeService employeeService;

    /**
     * 查询职工基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('EmployeeInfo:Employee:list')")
    @GetMapping("/list")
    public TableDataInfo list(Employee employee)
    {
        startPage();
        List<Employee> list = employeeService.selectEmployeeList(employee);
        return getDataTable(list);
    }

    /**
     * 导出职工基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('EmployeeInfo:Employee:export')")
    @Log(title = "职工基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Employee employee)
    {
        List<Employee> list = employeeService.selectEmployeeList(employee);
        ExcelUtil<Employee> util = new ExcelUtil<Employee>(Employee.class);
        util.exportExcel(response, list, "职工基本信息数据");
    }

    /**
     * 获取职工基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('EmployeeInfo:Employee:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(employeeService.selectEmployeeById(id));
    }

    /**
     * 新增职工基本信息
     */
    @PreAuthorize("@ss.hasPermi('EmployeeInfo:Employee:add')")
    @Log(title = "职工基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Employee employee)
    {
        return toAjax(employeeService.insertEmployee(employee));
    }

    /**
     * 修改职工基本信息
     */
    @PreAuthorize("@ss.hasPermi('EmployeeInfo:Employee:edit')")
    @Log(title = "职工基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Employee employee)
    {
        return toAjax(employeeService.updateEmployee(employee));
    }

    /**
     * 删除职工基本信息
     */
    @PreAuthorize("@ss.hasPermi('EmployeeInfo:Employee:remove')")
    @Log(title = "职工基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(employeeService.deleteEmployeeByIds(ids));
    }

    /**
     * 导入职工基本信息
     */
    @PreAuthorize("@ss.hasPermi('EmployeeInfo:Employee:import')")
    @Log(title = "职工基本信息", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<Employee> util = new ExcelUtil<Employee>(Employee.class);
        List<Employee> employeeList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = employeeService.importEmployee(employeeList, updateSupport, operName);
        return success(message);
    }

    /**
     * 下载导入模板
     */
    @PreAuthorize("@ss.hasPermi('EmployeeInfo:Employee:import')")
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<Employee> util = new ExcelUtil<Employee>(Employee.class);
        util.importTemplateExcel(response, "职工数据");
    }
}
