<template>
  <div class="home">
    <div>
      <div class="full-div" style="background-color: black; display: flex">
        <span style="color: aliceblue; margin-left: 200px">欢迎致电</span>
        <i class="el-icon-phone" style="color: green"></i>
        <span style="color: aliceblue">推广咨询电话</span>
        <el-input
            style="width: 150px; margin-left: 800px"
            placeholder="请输入内容"
            prefix-icon="el-icon-search"
        ></el-input>
        <el-button-group style="margin-left: 5px">
          <el-button type="warning">登录</el-button>
          <el-button type="primary">注册</el-button>
        </el-button-group>
      </div>
      <div class="full-div" style="display: flex">
        <img
            src="../assets/tpls.png"
            style="width: 100px; height: 50px; margin-left: 200px"
        />
        <div style="margin-left: 400px">
          <el-menu
              :default-active="activeIndex"
              :underline="false"
              class="el-menu-demo"
              mode="horizontal"
              @select="handleSelect"
              active-text-color="#ffc000"
          >
            <el-menu-item index="1">首页</el-menu-item>
            <el-menu-item index="2">关于我们</el-menu-item>
            <el-menu-item index="3">金牌律师</el-menu-item>
            <el-menu-item index="4">处专家咨询</el-menu-item>
            <el-menu-item index="5">优质案例</el-menu-item>
            <el-menu-item index="6">法律咨询</el-menu-item>
            <el-menu-item index="7">联系我们</el-menu-item>
          </el-menu>
        </div>
      </div>
      <div class="text-div">
        <h3 style="color: aliceblue; size: 24px; text-align: center;">铜牌律师网，特别公告！</h3>
        <p style="color: #d3f8ff; size: 14px; text-align: center; margin-top: 1px;">
          为了提升公司客户的服务质量，享受的服务，公司特设立服务监督部，欢迎合作律师来电指导工作.</p>
        <p style="color: #d3f8ff; size: 14px;text-align: center;margin-top: 1px;">总经理服务监督电话：18559301258
          工作时间：上午9：00-12：00 下午14：00-18：00</p>
      </div>
    </div>
    <div style="width:460px;">
      <el-cascader style="width:100%;" :options="options" v-model="selectedOptions"
                   @change="addressChoose"></el-cascader>
    </div>
    <el-table v-loading="loading" :data="tableData" border stripe :header-cell-class-name="'headerBg'">
      <el-table-column prop="name" label="名称"/>
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
      <el-table-column prop="telephone" label="电话"/>
      <el-table-column prop="regionCode" label="地区">
        <template slot-scope="scope">
          <span>{{ getRegion(scope.row.regionCode) }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="skills" label="专长领域"/>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
                     :page-sizes="[5, 10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
                     :total="total">
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
      activeIndex: "1"
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
        this.loading = false
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
    },
    handleSelect() {

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


.full-div {
  width: auto;
  height: 100%;
  display: flex; /* 或者 grid */
  align-items: center;
}

.text-div {
  height: 136px;
  display: grid; /* 或者 grid */
  background-color: #680c11;
}
</style>
