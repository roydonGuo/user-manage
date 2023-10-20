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
            <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info"
                icon-color="red" title="您确定批量删除这些数据吗？" @confirm="delBatch">
                <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
        </div>
        <el-table v-loading="loading" :data="tableData" border stripe :header-cell-class-name="'headerBg'"
            @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" />
            <el-table-column prop="id" label="ID" />
            <el-table-column prop="name" label="名称" />
            <el-table-column label="头像" align="center" prop="avatar" width="160">
                <template slot-scope="scope">
                    <el-image style="height: 80px;border-radius: 8px;" lazy :src="scope.row.avatar" :fit="contain">
                        <div slot="placeholder" class="image-slot">
                            <i class="el-icon-loading"></i>加载中...
                        </div>
                        <div slot="error" class="image-slot">
                            <i class="el-icon-picture-outline"></i>
                        </div>
                    </el-image>
                </template>
            </el-table-column>
            <el-table-column prop="telephone" label="电话" />
            <el-table-column prop="regionCode" label="地区">
                <template slot-scope="scope">
                    <span>{{ getRegion(scope.row.regionCode) }}</span>
                </template>
            </el-table-column>
            <el-table-column prop="skills" label="专长领域" />
            <el-table-column label="操作" width="200" align="center">
                <template slot-scope="scope">
                    <el-button type="primary" @click="handleSkill(scope.row)">专长领域</el-button>
                    <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info"
                        icon-color="red" title="您确定删除吗？" @confirm="del(scope.row.id)">
                        <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <div style="padding: 10px 0">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
                :page-sizes="[5, 10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
        </div>

        <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="50%">
            <el-form label-width="80px" size="small">
                <el-form-item label="昵称">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-upload style="text-align: center" class="avatar-uploader" action="http://localhost:9090/file/upload"
                    :show-file-list="false" :on-success="handleAvatarSuccess">
                    <img v-if="form.avatar" :src="form.avatar" class="avatar" style="border-radius: 50px">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
                <el-form-item label="电话">
                    <el-input v-model="form.telephone" autocomplete="off"></el-input>
                </el-form-item>
                <div style="width:460px;">地址：
                    <el-cascader style="width:100%;" :options="options" v-model="selectedOptions"
                        @change="addressChoose"></el-cascader>
                </div>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="分配专长" :visible.sync="skillDialogVisible" width="30%">
            <el-tree :props="props" :data="skillData" show-checkbox node-key="id" ref="tree"
                :default-expanded-keys="expanded" :default-checked-keys="checked">
                <span class="custom-tree-node" slot-scope="{ node, data }">
                    <span>{{ data.name }}</span>
                </span>
            </el-tree>
            <div slot="footer" class="dialog-footer">
                <el-button @click="skillDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveLawerSkill">确 定</el-button>
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
            pageNum: 1,
            pageSize: 10,
            total: 0,
            form: {},
            contain: 'contain',
            dialogFormVisible: false,
            // 省市区级联
            options: regionData,
            selectedOptions: [],
            skillDialogVisible: false,
            skillData: [],
            expanded: [],
            checked: [],
            props: {
                label: 'name',
            },
            lawerId: "",
            skillIds: []
        }
    },
    created() {
        this.getPageList()
        this.getViplawerSkillList()
    },
    methods: {
        getPageList() {
            this.request.get("/viplawer/page", {
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    name: this.name,
                }
            }).then(res => {
                this.loading = false
                this.tableData = res.data.records
                this.total = res.data.total
            })
        },
        getViplawerSkillList() {
            this.request.get("/viplawerSkill/list").then(res => {
                this.skillIds = res.data
            })
        },
        del(id) {
            this.request.delete("/viplawer/" + id).then(res => {
                if (res.code === '200') {
                    this.$message.success("删除成功")
                    this.getPageList()
                } else {
                    this.$message.error("删除失败")
                }
            })
        },
        handleSelectionChange(val) {
            console.log(val)
            this.multipleSelection = val
        },
        delBatch() {
            let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]
            this.request.post("/22222222222222", ids).then(res => {
                if (res.code === '200') {
                    this.$message.success("批量删除成功")
                    this.getPageList()
                } else {
                    this.$message.error("批量删除失败")
                }
            })
        },
        reset() {
            this.name = ""
            this.getPageList()
        },
        handleSizeChange(pageSize) {
            this.pageSize = pageSize
            this.getPageList()
        },
        handleCurrentChange(pageNum) {
            this.pageNum = pageNum
            this.getPageList()
        },
        handleAdd() {
            this.dialogFormVisible = true
            this.form = {}
        },
        save() {
            this.request.post("/viplawer", this.form).then(res => {
                if (res.data) {
                    this.$message.success("保存成功")
                    this.dialogFormVisible = false
                    this.getPageList()
                } else {
                    this.$message.error("保存失败")
                }
            })
        },
        handleAvatarSuccess(res) {
            this.form.avatar = res
        },
        /**省市区三级联动 */
        addressChoose(value) {
            // console.log(this.provinceAndCityData);
            console.log("地址", value)
            console.log("地址编码", value[value.length - 1])
            this.form.provinceCode = value[0]
            this.form.cityCode = value[1]
            this.form.regionCode = value[2]
        },
        getRegion(code) {
            return code
        },
        handleSkill(row) {
            this.lawerId = row.id
            this.request.get("/viplawerSkill/list").then(res => {
                this.skillData = res.data
                this.expanded = this.skillData.map(v => v.id)
            })
            this.request.get("/viplawer/skillIdTree/" + this.lawerId).then(res => {
                this.checked = res.data
                this.$nextTick(() => {
                    this.$refs.tree.setCheckedKeys(this.checked);
                })
            })
            this.skillDialogVisible = true
        },
        saveLawerSkill() {
            // console.log(this.$refs.tree.getCheckedNodes())
            // console.log(this.$refs.tree.getCheckedKeys())
            this.request.post("/viplawer/skill/" + this.lawerId, this.$refs.tree.getCheckedKeys()).then(res => {
                if (res.code === '200') {
                    this.checked = res.data
                    this.$message.success('分配成功')
                    this.skillDialogVisible = false
                } else {
                    this.$message.error(res.msg)
                }
            })
        }
    }
}
</script>

<style scoped>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>
