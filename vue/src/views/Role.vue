<template>
  <div name="el-zoom-in-center">

    <div style="margin: 0 0 10px;display: inline-block">
      <el-input style="width: 200px" placeholder="请输入角色名" suffix-icon="el-icon-search" class="mr-5"
        v-model="name"></el-input>
      <el-input style="width: 200px" placeholder="请输入描述" suffix-icon="el-icon-message" class="mr-5"
        v-model="description"></el-input>
      <el-input style="width: 200px" placeholder="请输入flag" suffix-icon="el-icon-position" class="mr-5"
        v-model="flag"></el-input>
      <el-button type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 0 0 10px;display: inline-block;float: right">
      <el-button type="primary" class="mr-5" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i>
      </el-button>
      <el-popconfirm class="mr-5" confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info" icon-color="red"
        title="您确定批量删除这些数据吗？" @confirm="delBatch">
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
      <el-upload class="mr-5" action="http://localhost:9090/role/import" :show-file-list="false" accept="xlsx"
        :on-success="handleExcelImportSuccess" style="display: inline-block">
        <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
      </el-upload>
      <el-button type="primary" @click="exp">导出 <i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="40"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="name" label="角色"></el-table-column>
      <el-table-column prop="description" label="描述"></el-table-column>
      <el-table-column prop="flag" label="flag"></el-table-column>
      <el-table-column label="操作" width="300" align="center">
        <template slot-scope="scope">
          <el-button type="info" @click="handleMenu(scope.row)">分配菜单 <i class="el-icon-menu"></i></el-button>
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info"
            icon-color="red" title="您确定删除吗？" @confirm="delRow(scope.row.id)">
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i>
            </el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
        :page-sizes="[5, 10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>
    <el-dialog title="角色信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="角色">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="form.description" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="flag">
          <el-input v-model="form.flag" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="分配菜单" :visible.sync="menuDialogVisible" width="30%">
      <el-tree :props="props" :data="menuData" show-checkbox node-key="id" ref="tree" :default-expanded-keys="expanded"
        :default-checked-keys="checked">
        <span class="custom-tree-node" slot-scope="{ node, data }">
          <span><i :class="data.icon"></i>{{ data.name }}</span>
        </span>
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click="menuDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveRoleMenu">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return {
      props: {
        label: 'name',
      },
      menuData: [],
      value: true,
      tableData: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      name: "",
      description: '',
      flag: '',
      form: {},
      dialogFormVisible: false,
      menuDialogVisible: false,
      multipleSelection: [],
      headerBg: 'headerBg',
      fileList: [],
      expanded: [],
      checked: [],
      roleId: 0,
      roleFlag: '',
      menuIds: [],
    }
  },
  created() {
    // 请求分页查询数据
    this.load()
  },
  methods: {
    load() {
      this.request.get("/role/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
          description: this.description,
          flag: this.flag,
        }
      }).then(res => {
        // console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
      this.request.get("/menu/ids").then(res => {
        this.menuIds = res.data
      })
    },
    reset() {
      this.name = ''
      this.description = ''
      this.flag = ''
      this.load()
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
      // this.request.get("/menu").then(res => {
      //   this.menuData = res.data
      //   this.expanded = this.menuData.map(v => v.id)
      // })
      this.dialogFormVisible = true
      this.form = {}
    },
    save() {
      this.request.post("/role", this.form).then(res => {
        if (res.data) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    saveRoleMenu() {
      // console.log(this.$refs.tree.getCheckedNodes())
      // console.log(this.$refs.tree.getCheckedKeys())
      this.request.post("/role/roleMenu/" + this.roleId, this.$refs.tree.getCheckedKeys()).then(res => {
        // console.log(res)
        if (res.code === '200') {
          this.checked = res.data
          this.$message.success('分配成功')
          this.menuDialogVisible = false
          if (this.roleFlag === 'ROLE_ADMIN') {
            this.$store.commit("logout")
          }
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleEdit(row) {
      this.form = { ...row }
      this.dialogFormVisible = true
    },
    delRow(id) {
      this.request.delete('/role/' + id).then(res => {
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
      this.request.delete('/role/del/batch', {
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
      window.open("http://localhost:9090/role/export")
    },
    handleExcelImportSuccess() {
      this.$message.success("导入成功")
      this.load()
    },
    handleMenu(row) {
      this.roleId = row.id
      this.roleFlag = row.flag
      this.request.get("/menu").then(res => {
        this.menuData = res.data
        this.expanded = this.menuData.map(v => v.id)
      })
      this.request.get("/role/roleMenu/" + this.roleId).then(res => {
        this.checked = res.data
        // this.menuIds.forEach(id => {
        //   if (!this.checks.includes(id)) {
        //     this.$nextTick(() => {
        //       this.$refs.tree.setChecked(id, false)
        //     })
        //   }
        // })
        this.$nextTick(() => {
          this.$refs.tree.setCheckedKeys(this.checked);
        })
      })
      this.menuDialogVisible = true

    },


  },
}
</script>

<style scoped>
.headerBg {
  background: #eee !important;
}
</style>