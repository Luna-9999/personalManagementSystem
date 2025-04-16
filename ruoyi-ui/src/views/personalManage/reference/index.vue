<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标准退休年月">
        <el-date-picker
          v-model="daterangeStandardRetireDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['personalManage:reference:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['personalManage:reference:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['personalManage:reference:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['personalManage:reference:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="referenceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="人员类别" align="center" prop="employeeCategory" />
      <el-table-column label="出生年月" align="center" prop="birthDate" />
      <el-table-column label="标准退休年月" align="center" prop="standardRetireDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.standardRetireDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['personalManage:reference:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['personalManage:reference:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改退休年龄政策对照对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标准退休年月" prop="standardRetireDate">
          <el-date-picker clearable
            v-model="form.standardRetireDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择标准退休年月">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listReference, getReference, delReference, addReference, updateReference } from "@/api/personalManage/reference";

export default {
  name: "Reference",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 退休年龄政策对照表格数据
      referenceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 标准退休年月时间范围
      daterangeStandardRetireDate: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        standardRetireDate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        standardRetireDate: [
          { required: true, message: "标准退休年月不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询退休年龄政策对照列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeStandardRetireDate && '' != this.daterangeStandardRetireDate) {
        this.queryParams.params["beginStandardRetireDate"] = this.daterangeStandardRetireDate[0];
        this.queryParams.params["endStandardRetireDate"] = this.daterangeStandardRetireDate[1];
      }
      listReference(this.queryParams).then(response => {
        this.referenceList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        employeeCategory: null,
        birthDate: null,
        standardRetireDate: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeStandardRetireDate = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.employeeCategory)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加退休年龄政策对照";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const employeeCategory = row.employeeCategory || this.ids
      getReference(employeeCategory).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改退休年龄政策对照";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.employeeCategory != null) {
            updateReference(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReference(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const employeeCategorys = row.employeeCategory || this.ids;
      this.$modal.confirm('是否确认删除退休年龄政策对照编号为"' + employeeCategorys + '"的数据项？').then(function() {
        return delReference(employeeCategorys);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('personalManage/reference/export', {
        ...this.queryParams
      }, `reference_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
