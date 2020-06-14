<template>
  <el-container>
    <!-- <el-header class="nav-container">
      <navbar
        :nav-index="navActiveIndex"
        @nav-items-click="navClick"/>
    </el-header>-->
    <el-container>
      <el-main id="crm-main-container">
        <app-main />
        <el-header class="nav-container">
          <navbar :nav-index="navActiveIndex" @nav-items-click="navClick" />
        </el-header>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { Navbar, Sidebar, AppMain } from "./components";
import { mapGetters } from "vuex";

export default {
  name: "Layout",
  components: {
    Navbar,
    Sidebar,
    AppMain
  },
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["navActiveIndex"])
  },
  methods: {
    navClick(index) {}
  }
};
</script>

<style lang="scss" scoped>
.aside-container {
  position: relative;
  background-color: white;
  box-sizing: border-box;
  border-right: solid 1px #e6e6e6;
}

.nav-container {
  padding: 0;
  box-shadow: 0px 1px 2px #dbdbdb;
  z-index: 100;
  min-width: 1200px;
}
.el-main {
  padding: 0;
}
</style>
