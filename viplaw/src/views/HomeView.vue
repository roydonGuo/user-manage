<template>
  <div class="home">
    <div style="width:460px;">
      <el-cascader style="width:100%;" :options="options" v-model="selectedOptions" @change="addressChoose"></el-cascader>
    </div>
    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'">
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
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
        :page-sizes="[5, 10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue'
import {
  provinceAndCityData,
  regionData,
  provinceAndCityDataPlus,
  regionDataPlus,
  CodeToText,
  TextToCode
} from "element-china-area-data";

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  },
  data() {
    return {
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
    }
  },
  created() {
    this.getPageList()
  },
  methods: {
    /**省市区三级联动 */
    addressChoose(value) {
      // console.log(this.provinceAndCityData);
      console.log("地址", value)
      console.log("地址编码", value[value.length - 1])
    },
    getPageList() {
      this.request.get("/viplawer/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
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
    getRegion(code) {
      return code
    }
  }

}
</script>

<style>
.law-item {
  position: relative;
  padding-bottom: 20px;
  margin-bottom: 20px;
  font-family: 'Microsoft Yahei';
  border-bottom: 1px dotted #e0e0e0;
}

.law-pho {
  display: block;
  width: 130px;
  height: 123px;
}

.law-info {
  margin-left: 20px;
}

.left {
  float: left;
}

.law-info li {
  width: 418px;
  height: 24px;
}

.law-name {
  font-size: 14px;
  font-weight: 700;
}

li {
  list-style: none;
}

.law-info li {
  width: 418px;
  height: 24px;
}

.txtellipsis {
  white-space: nowrap;
  overflow: hidden;
  -webkit-text-overflow: ellipsis;
  -khtml-text-overflow: ellipsis;
  -icab-text-overflow: ellipsis;
  -moz-text-overflow: ellipsis;
  -o-text-overflow: ellipsis;
  text-overflow: ellipsis;
}

.btngroups {
  position: absolute;
  top: 26px;
  right: 0;
}
</style>
