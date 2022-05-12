<template>
  <div>

    <v-navigation-drawer
      id="drawer"
      app
      clipped
      fixed
      v-model="drawer"
      class="font"
    >
      <v-list dense>

        <v-list-tile
          v-if="$store.getters.isLoggedIn"
          @click="$router.push('/articles/editor')"
        >
          <v-list-tile-action>
            <v-icon>create</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>new article</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile @click="$router.push('/')">
          <v-list-tile-action>
            <v-icon>dashboard</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>dashboard</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-group
          prepend-icon="category"
          v-model="expanded"
        >
          <v-list-tile slot="activator">
            <v-list-tile-content>
              <v-list-tile-title>categories</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>

          <v-list-tile
            v-for="(category, index) in categories"
            :key="index"
            @click="$router.push(`/categories/${category}`)"
          >
            <v-list-tile-action>
              <v-icon></v-icon>
            </v-list-tile-action>
            <v-list-tile-content>
              <v-list-tile-title>{{category}}</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </v-list-group>

      </v-list>
    </v-navigation-drawer>
    <v-toolbar
      id="toolbar"
      app
      fixed
      clipped-left
    >
      <v-toolbar-side-icon @click.stop="drawer = !drawer">
        <v-icon>menu</v-icon>
      </v-toolbar-side-icon>

      <span @click="$router.push('/login')">
        <img class="avatar" src="../assets/avatar_small.png"/>
      </span>

      <v-toolbar-title id="toolbar-title">Paul Werner's Blog</v-toolbar-title>

      <v-spacer></v-spacer>

      <div>
        <a :href="mail_lnk">
          <img class="img-icon" src="../assets/icon_mail.png">
        </a>
        <a :href="github_lnk" target="_blank">
          <img class="img-icon" src="../assets/icon_github.png">
        </a>
        <a :href="linkedin_lnk" target="_blank">
          <img class="img-icon" src="../assets/icon_linkedin.png">
        </a>
        <a :href="xing_lnk" target="_blank">
          <img class="img-icon" src="../assets/icon_xing.png">
        </a>
      </div>

    </v-toolbar>

  </div>
</template>

<script>
import { AXIOS } from '../helper/http-helper'

export default {
  name: 'Toolbar',
  data () {
    return {
      categories: [],
      expanded: true,
      drawer: false,
      mail_lnk: 'mailto:contact@paulwerner.org',
      github_lnk: 'https://github.com/pashc',
      xing_lnk: 'https://www.xing.com/profile/Paul_Werner23',
      linkedin_lnk: 'https://de.linkedin.com/in/paul-werner'
    }
  },
  created () {
    this.fetchCategories()
  },
  methods: {
    fetchCategories () {
      AXIOS.get('/api/blog/categories')
        .then(response => {
          response.data
            .map(category => category.name)
            .reduce((list, categoryName) => {
              list.push(categoryName)
              return list
            }, this.categories)
        })
    }
  }
}
</script>

<style scoped>

  #drawer {
    background-color: #ebedef;
  }

  >>> .v-list__group__header--active .v-list__group__header__prepend-icon .v-icon {
    color: #1a1a1a;
  }

  #toolbar {
    background-color: #6d6e6a;
    font-weight: bold;
    font-size: 18px;
    font-family: Courier, sans-serif;
  }

  img.avatar {
    height: 40px;
    border-radius: 50%;
    margin: 0 -9px 0 6px;
  }

  img.img-icon {
    height: 50px;
    padding: 5px;
    margin: 0 auto;
    float: left;
  }

  .font {
    color: #d8d6cf;
    font-weight: bold;
    font-size: 16px;
    font-family: Courier, sans-serif;
  }

</style>
