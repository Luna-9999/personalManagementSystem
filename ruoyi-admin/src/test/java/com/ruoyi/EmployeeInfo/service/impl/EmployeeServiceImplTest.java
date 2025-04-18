package com.ruoyi.EmployeeInfo.service.impl;

import com.ruoyi.EmployeeInfo.domain.Employee;
import com.ruoyi.EmployeeInfo.mapper.EmployeeMapper;
import com.ruoyi.common.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * 职工数据导入功能测试
 *
 * @author Luna-9999
 * @date 2025-04-18
 */
public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeMapper employeeMapper;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 创建测试数据
     */
    private List<Employee> createTestEmployeeList() throws ParseException {
        List<Employee> employeeList = new ArrayList<>();
        
        // 添加测试数据
        employeeList.add(createEmployee("陈××", "男", "1974-03-10", "332623197403100039", "是", 
                "党委纪检组", "副组长（正股级，协助主持日常工作）、二级主办"));
        
        employeeList.add(createEmployee("孙××", "女", "1991-07-20", "331003199107200027", "是", 
                "党委纪检组", "副组长（副股级）、四级主办"));
        
        employeeList.add(createEmployee("汪××", "女", "1990-01-21", "331081199001215123", "是", 
                "党委纪检组", "副组长（副股级）、四级主办"));
        
        employeeList.add(createEmployee("缪××", "男", "1993-08-07", "331002199308070613", "是", 
                "党委纪检组", "一级行政执法员"));
        
        employeeList.add(createEmployee("郑××", "男", "1970-07-16", "332623197007163839", "是", 
                "党委纪检组", "二级主办"));
        
        return employeeList;
    }
    
    /**
     * 创建单个Employee对象
     */
    private Employee createEmployee(String name, String gender, String birthDate, String idNumber, 
            String isFormal, String department, String position) throws ParseException {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setGender(gender);
        employee.setBirthDate(dateFormat.parse(birthDate));
        employee.setIdNumber(idNumber);
        employee.setIsFormal(isFormal);
        employee.setDepartment(department);
        employee.setPosition(position);
        return employee;
    }

    @Test
    @DisplayName("测试导入空数据集")
    public void testImportEmptyEmployeeList() {
        // 准备测试数据
        List<Employee> emptyList = new ArrayList<>();
        String operName = "admin";
        
        // 执行测试并验证异常
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            employeeService.importEmployee(emptyList, false, operName);
        });
        
        assertEquals("导入职工数据不能为空！", exception.getMessage());
        
        // 验证没有调用mapper方法
        verify(employeeMapper, never()).insertEmployee(any(Employee.class));
        verify(employeeMapper, never()).updateEmployee(any(Employee.class));
    }
    
    @Test
    @DisplayName("测试成功导入新员工数据")
    public void testImportNewEmployeeSuccess() throws ParseException {
        // 准备测试数据
        List<Employee> employeeList = new ArrayList<>(createTestEmployeeList().subList(0, 1));
        String operName = "admin";
        
        // 模拟mapper行为
        when(employeeMapper.selectEmployeeByIdNumber(anyString())).thenReturn(null);
        when(employeeMapper.insertEmployee(any(Employee.class))).thenReturn(1);
        
        // 执行测试
        String result = employeeService.importEmployee(employeeList, false, operName);
        
        // 验证结果
        assertTrue(result.contains("恭喜您，数据已全部导入成功！共 1 条"));
        assertTrue(result.contains("陈××"));
        assertTrue(result.contains("332623197403100039"));
        verify(employeeMapper, times(1)).insertEmployee(any(Employee.class));
        verify(employeeMapper, never()).updateEmployee(any(Employee.class));
    }
    
    @Test
    @DisplayName("测试导入已存在员工数据但不更新")
    public void testImportExistingEmployeeWithoutUpdate() throws ParseException {
        // 准备测试数据
        List<Employee> employeeList = new ArrayList<>(createTestEmployeeList().subList(0, 1)); // 只使用第一条数据进行测试
        
        Employee existingEmployee = new Employee();
        existingEmployee.setId(1L);
        existingEmployee.setIdNumber("332623197403100039");
        existingEmployee.setName("陈××");
        
        String operName = "admin";
        
        // 模拟mapper行为
        when(employeeMapper.selectEmployeeByIdNumber(anyString())).thenReturn(existingEmployee);
        
        // 执行测试并验证异常
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            employeeService.importEmployee(employeeList, false, operName);
        });
        
        // 验证结果
        assertTrue(exception.getMessage().contains("已存在"));
        assertTrue(exception.getMessage().contains("陈××"));
        verify(employeeMapper, never()).insertEmployee(any(Employee.class));
        verify(employeeMapper, never()).updateEmployee(any(Employee.class));
    }
    
    @Test
    @DisplayName("测试导入已存在员工数据并更新")
    public void testImportExistingEmployeeWithUpdate() throws ParseException {
        // 准备测试数据
        List<Employee> employeeList = new ArrayList<>(createTestEmployeeList().subList(0, 1)); // 只使用第一条数据进行测试
        
        Employee existingEmployee = new Employee();
        existingEmployee.setId(1L);
        existingEmployee.setIdNumber("332623197403100039");
        existingEmployee.setName("陈××");
        
        String operName = "admin";
        
        // 模拟mapper行为
        when(employeeMapper.selectEmployeeByIdNumber(anyString())).thenReturn(existingEmployee);
        when(employeeMapper.updateEmployee(any(Employee.class))).thenReturn(1);
        
        // 执行测试
        String result = employeeService.importEmployee(employeeList, true, operName);
        
        // 验证结果
        assertTrue(result.contains("恭喜您，数据已全部导入成功！共 1 条"));
        assertTrue(result.contains("更新成功"));
        verify(employeeMapper, never()).insertEmployee(any(Employee.class));
        verify(employeeMapper, times(1)).updateEmployee(any(Employee.class));
    }
    
    @Test
    @DisplayName("测试批量导入多条数据")
    public void testImportMultipleEmployees() throws ParseException {
        // 准备测试数据 - 使用全部5条测试数据
        List<Employee> employeeList = createTestEmployeeList();
        String operName = "admin";
        
        // 模拟mapper行为 - 所有数据都是新增
        when(employeeMapper.selectEmployeeByIdNumber(anyString())).thenReturn(null);
        when(employeeMapper.insertEmployee(any(Employee.class))).thenReturn(1);
        
        // 执行测试
        String result = employeeService.importEmployee(employeeList, false, operName);
        
        // 验证结果
        assertTrue(result.contains("恭喜您，数据已全部导入成功！共 5 条"));
        verify(employeeMapper, times(5)).insertEmployee(any(Employee.class));
        verify(employeeMapper, never()).updateEmployee(any(Employee.class));
    }
    
    @Test
    @DisplayName("测试批量导入混合情况(新增、更新、存在)")
    public void testImportMixedEmployees() throws ParseException {
        // 准备测试数据
        List<Employee> employeeList = createTestEmployeeList();
        String operName = "admin";
        
        // 为前两个员工创建已存在记录
        Employee existingEmployee1 = new Employee();
        existingEmployee1.setId(1L);
        existingEmployee1.setIdNumber("332623197403100039");
        existingEmployee1.setName("陈××");
        
        Employee existingEmployee2 = new Employee();
        existingEmployee2.setId(2L);
        existingEmployee2.setIdNumber("331003199107200027");
        existingEmployee2.setName("孙××");
        
        // 模拟mapper行为：
        // - 第1个员工已存在，将更新
        // - 第2个员工已存在，但不更新
        // - 第3-5个员工为新增
        when(employeeMapper.selectEmployeeByIdNumber("332623197403100039")).thenReturn(existingEmployee1);
        when(employeeMapper.selectEmployeeByIdNumber("331003199107200027")).thenReturn(existingEmployee2);
        when(employeeMapper.selectEmployeeByIdNumber("331081199001215123")).thenReturn(null);
        when(employeeMapper.selectEmployeeByIdNumber("331002199308070613")).thenReturn(null);
        when(employeeMapper.selectEmployeeByIdNumber("332623197007163839")).thenReturn(null);
        
        when(employeeMapper.updateEmployee(any(Employee.class))).thenReturn(1);
        when(employeeMapper.insertEmployee(any(Employee.class))).thenReturn(1);
        
        // 执行测试 - 支持更新
        String result = employeeService.importEmployee(employeeList, true, operName);
        
        // 验证结果
        assertTrue(result.contains("恭喜您，数据已全部导入成功！共 5 条"));
        verify(employeeMapper, times(3)).insertEmployee(any(Employee.class));
        verify(employeeMapper, times(2)).updateEmployee(any(Employee.class));
    }
    
    @Test
    @DisplayName("测试部分导入失败的情况")
    public void testPartialImportFailure() throws ParseException {
        // 准备测试数据
        List<Employee> employeeList = createTestEmployeeList();
        String operName = "admin";
        
        // 模拟mapper行为
        // - 第1个正常插入
        // - 第2个抛出异常
        // - 第3个正常插入
        // - 第4个抛出异常
        // - 第5个正常插入
        when(employeeMapper.selectEmployeeByIdNumber(anyString())).thenReturn(null);
        when(employeeMapper.insertEmployee(any(Employee.class)))
            .thenReturn(1) // 第1个正常
            .thenThrow(new RuntimeException("数据异常")) // 第2个异常
            .thenReturn(1) // 第3个正常
            .thenThrow(new RuntimeException("违反唯一约束")) // 第4个异常
            .thenReturn(1); // 第5个正常
        
        // 执行测试并验证异常
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            employeeService.importEmployee(employeeList, false, operName);
        });
        
        // 验证结果
        assertTrue(exception.getMessage().contains("很抱歉，导入失败！共 2 条"));
        assertTrue(exception.getMessage().contains("数据异常"));
        assertTrue(exception.getMessage().contains("违反唯一约束"));
        verify(employeeMapper, times(5)).selectEmployeeByIdNumber(anyString());
        verify(employeeMapper, times(5)).insertEmployee(any(Employee.class));
    }
}