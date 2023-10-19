<template>
    <div class="viplaw">
        <div style="margin: 0 0 10px;display: inline-block">
            <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
            <el-button class="ml-5" type="primary" @click="getPageList">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>
        <div style="margin: 0 0 10px;display: inline-block;float: right">
            <el-button type="primary" class="mr-5" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i>
            </el-button>
        </div>
        <el-table v-loading="loading" :data="tableData" border stripe row-key="id">
            <el-table-column prop="id" label="ID" />
            <el-table-column prop="name" label="名称" />
            <el-table-column label="操作" width="200" align="center">
                <template slot-scope="scope">
                    <el-button type="primary" v-if="!scope.row.pid" @click="handleAddChildren(scope.row.id)">
                        新增子菜单 </el-button>
                    <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info"
                        icon-color="red" title="您确定删除吗？" @confirm="del(scope.row.id)">
                        <el-button type="danger" slot="reference">删除</el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="50%">
            <el-form label-width="80px" size="small">
                <el-form-item label="技能名称">
                    <el-input v-model="form.name" autocomplete="off" />
                </el-form-item>
                <el-form-item label="父id" v-if="handleAddChildrenOption">
                    <el-input v-model="form.pid" autocomplete="off" />
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
import {
    provinceAndCityData,
    regionData,
    provinceAndCityDataPlus,
    regionDataPlus,
    CodeToText,
    TextToCode
} from "element-china-area-data";

export default {
    name: "Viplaw",
    data() {
        return {
            loading: true,
            tableData: [],
            name: '',
            multipleSelection: [],
            form: {},
            contain: 'contain',
            dialogFormVisible: false,
            // 省市区级联
            options: regionData,
            selectedOptions: [],
            handleAddChildrenOption: false
        }
    },
    created() {
        this.getPageList()
    },
    methods: {
        getPageList() {
            this.request.get("/viplawerSkill/list", {
                params: {
                    name: this.name,
                }
            }).then(res => {
                this.loading = false
                this.tableData = res.data
            })
        },
        del(id) {
            this.request.delete("/viplawerSkill/" + id).then(res => {
                if (res.code === '200') {
                    this.$message.success("删除成功")
                    this.getPageList()
                } else {
                    this.$message.error("删除失败")
                }
            }
            )
        },
        reset() {
            this.name = ""
            this.getPageList()
        },
        handleAdd() {
            this.dialogFormVisible = true
            this.form = {}
        },
        save() {
            this.request.post("/viplawerSkill", this.form).then(res => {
                if (res.data) {
                    this.$message.success("保存成功")
                    this.dialogFormVisible = false
                    this.handleAddChildrenOption = false
                    this.getPageList()
                } else {
                    this.$message.error("保存失败")
                }
            })
        },
        handleAddChildren(pid) {
            this.handleAddChildrenOption = true
            this.dialogFormVisible = true
            this.form = {}
            if (pid) {
                this.form.pid = pid
            }
        },
    }
}
</script>
