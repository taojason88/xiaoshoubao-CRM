<template>
  <el-container>
    <!-- <el-header class="nav-container">
      <navbar
        :nav-index="2"
        @nav-items-click="navClick"/>
    </el-header> -->
    <el-container>
      <el-aside
        width="auto"
        class="aside-container">
        <sidebar
          :items="sidebarItems"
          :create-button-title="permissonProject ? '创建项目' : ''"
          create-button-icon="el-icon-plus"
          main-router="project"
          @quicklyCreate="quicklyCreate"/>
      </el-aside>
      <el-main id="project-main-container">
        <el-header class="nav-container">
      <navbar
        :nav-index="2"
        @nav-items-click="navClick"/>
    </el-header>
        <app-main/>
      </el-main>
    </el-container>
    <add-project
      v-if="isCreate"
      @close="isCreate = false"/>
  </el-container>
</template>

<script>
import { mapGetters } from 'vuex'
import { childrenMenu } from '@/router/modules/project/project'
import { Navbar, Sidebar, AppMain } from './components'
import { workIndexWorkListAPI } from '@/api/projectManagement/task'
import { workTasklableIndexAPI } from '@/api/projectManagement/tag'
import AddProject from '@/views/projectManagement/components/addProject'

export default {
  name: 'Layout',
  components: {
    Navbar,
    Sidebar,
    AppMain,
    AddProject
  },
  data() {
    return {
      isCreate: false,
      sidebarItems: []
    }
  },
  computed: {
    ...mapGetters(['project']),
    permissonProject() {
      return this.project && this.project.projectManage && this.project.projectManage.save
    }
  },
  created() {
    this.sidebarItems = childrenMenu
    this.getProjectMenu()
    this.getTagMenu()
    this.addNotification()
  },

  beforeDestroy() {
    this.$bus.$off('project-setting')
    this.$bus.$off('add-project')
    this.$bus.$off('delete-project')
    this.$bus.$off('recover-project')
  },
  methods: {
    navClick(index) {},

    quicklyCreate() {
      this.isCreate = true
    },

    /**
     * 获取项目菜单
     */
    getProjectMenu() {
      // 获取项目列表
      let projectMenu = null
      for (const item of this.sidebarItems) {
        if (item.meta && item.meta.title == '项目') {
          projectMenu = item
          break
        }
      }

      if (projectMenu) {
        workIndexWorkListAPI()
          .then(res => {
            projectMenu.children = []
            for (const item of res.data) {
              projectMenu.children.push({
                path: 'list/' + item.workId,
                meta: {
                  title: item.name,
                  id: item.workId
                }
              })
            }
          })
          .catch(() => {})
      }
    },

    /**
     * 获取标签菜单
     */
    getTagMenu() {
      let tagMenu = null
      for (const item of this.sidebarItems) {
        if (item.meta && item.meta.title == '标签') {
          tagMenu = item
          break
        }
      }

      if (tagMenu) {
        workTasklableIndexAPI()
          .then(res => {
            tagMenu.children = []
            for (const item of res.data) {
              tagMenu.children.push({
                path: 'tag/' + item.labelId,
                meta: {
                  title: item.name,
                  params: item
                }
              })
            }
          })
          .catch(() => {})
      }
    },

    /**
     * 通知更新
     */
    addNotification() {
      // 项目设置
      this.$bus.$on('project-setting', (name, id) => {
        for (const item of this.sidebarItems) {
          if (item.meta && item.meta.title == '项目') {
            for (const i in item.children) {
              if (item.children[i].meta.id == id) {
                item.children[i].meta.title = name
                break
              }
            }
          }
        }
      })

      // 项目新增
      this.$bus.$on('add-project', (name, id) => {
        for (const item of this.sidebarItems) {
          if (item.meta && item.meta.title == '项目') {
            item.children.push({
              path: 'list/' + id,
              meta: {
                title: name,
                id: id
              }
            })
            this.$nextTick(() => {
              this.$router.replace({
                name: 'project-list',
                params: {
                  id: id
                }
              })
            })
            break
          }
        }
      })

      // 恢复项目
      this.$bus.$on('recover-project', (name, id) => {
        for (const item of this.sidebarItems) {
          if (item.meta && item.meta.title == '项目') {
            item.children.push({
              path: 'list/' + id,
              meta: {
                title: name,
                id: id
              }
            })
          }
        }
      })

      // 项目删除
      this.$bus.$on('delete-project', id => {
        for (const item of this.sidebarItems) {
          if (item.meta && item.meta.title == '项目') {
            for (const i in item.children) {
              if (item.children[i].meta.id == id) {
                item.children.splice(i, 1)
                this.$router.replace({
                  name: 'my-task'
                })
                break
              }
            }
          }
        }
      })
    }
  }
}
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

#project-main-container {
  max-height: 100%;
}

.el-container {
  overflow: hidden;
}
.el-main{
  padding: 0;
}
</style>
