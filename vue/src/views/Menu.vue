<template>
  <div name="el-zoom-in-center">

    <div style="margin: 0 0 10px;display: inline-block">
      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" class="mr-5"
                v-model="name"></el-input>
      <el-button type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 0 0 10px;display: inline-block;float: right">
      <el-button type="primary" class="mr-5" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i>
      </el-button>
      <el-popconfirm
          class="mr-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定批量删除这些数据吗？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
      <el-upload class="mr-5" action="http://localhost:9090/menu/import" :show-file-list="false" accept="xlsx"
                 :on-success="handleExcelImportSuccess" style="display: inline-block">
        <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
      </el-upload>
      <el-button type="primary" @click="exp">导出 <i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"
              row-key="id"
              default-expand-all
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="40"></el-table-column>
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="path" label="路径"></el-table-column>
      <el-table-column label="图标">
        <template slot-scope="scope">
          <i :class="scope.row.icon">{{ scope.row.icon }}</i>
        </template>
      </el-table-column>
      <el-table-column prop="description" label="描述"></el-table-column>
      <el-table-column prop="pid" label="pid"></el-table-column>
      <el-table-column prop="pagePath" label="路径"></el-table-column>
      <el-table-column prop="sortNum" label="sort_num"></el-table-column>

      <el-table-column label="操作" width="300" align="center">

        <template slot-scope="scope">
          <el-button type="primary" v-if="!scope.row.pid && !scope.row.path" @click="handleAddChildren(scope.row.id)">
            新增子菜单 <i class="el-icon-edit"></i></el-button>
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="delRow(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i>
            </el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!--    <div style="padding: 10px 0">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[5, 10, 20]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>
        </div>-->
    <el-dialog title="menu信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="名称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="路径">
          <el-input v-model="form.path" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="icon">
          <template slot-scope="scope">
            <el-select clearable v-model="form.icon" placeholder="请选择图标" style="width: 100%">
              <el-option v-for="item in options"
                         :key="item.name"
                         :label="item.name"
                         :value="item.value">
                <i :class="item.value">{{ item.name }}</i>
              </el-option>
            </el-select>
          </template>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="form.description" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="pid">
          <el-input v-model="form.pid" autocomplete="off"></el-input>

        </el-form-item>
        <el-form-item label="路径">
          <el-input v-model="form.pagePath" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="sortNum">
          <el-input v-model="form.sortNum" autocomplete="off"></el-input>

        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return {
      value: true,
      tableData: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      name: "",
      path: '',
      icon: '',
      description: '',
      pid: '',
      pagePath: '',
      sortNum: '',
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      headerBg: 'headerBg',
      fileList: [],
      options: [],
    }
  },
  created() {
    // 请求分页查询数据
    this.load()
  },
  methods: {
    load() {
      this.request.get("/menu", {
        params: {
          name: this.name,
        }
      }).then(res => {
        this.tableData = res.data
      })
    },
    reset() {
      this.name = ''
      this.load()
    },

    handleAddChildren(pid) {
      this.dialogFormVisible = true
      this.form = {}
      if (pid) {
        this.form.pid = pid
      }
    },
    handleSizeChange(pageSize) {
      // console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      // console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    save() {
      this.request.post("/menu", this.form).then(res => {
        if (res.data) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    handleEdit(row) {
      this.form = {...row}
      this.dialogFormVisible = true
      this.request.get("/menu/icons").then(res => {
        this.options = res.data
      })
    },
    delRow(id) {
      this.request.delete('/menu/' + id).then(res => {
        if (res.data) {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })

    },
    handleSelectionChange(rows) {
      // console.log(rows)
      this.multipleSelection = rows
    },
    delBatch() {
      let ids = this.multipleSelection.map(row => row.id) // [{}, {}, {}] => [1,2,3]
      // console.log(ids)
      this.request.delete('/menu/del/batch', {
        data: ids
      }).then(res => {
        if (res.data) {
          this.$message.success("批量删除成功")
          this.pageNum = 1
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })

    },
    exp() {
      window.open("http://localhost:9090/menu/export")
    },
    handleExcelImportSuccess() {
      this.$message.success("导入成功")
      this.load()
    }

  },
}
</script>

<style scoped>
.headerBg {
  background: #eee !important;
}
</style>