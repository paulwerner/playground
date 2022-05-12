<template>
  <v-content>
    <v-container fluid fill-height>
      <v-layout justify-center align-center>

        <div class="container-fluid font">

          <p class="error">{{error}}</p>

          <v-text-field v-model="title"></v-text-field>

          <markdown-editor
            v-model="editorContent"
            ref="markdownEditor"
          ></markdown-editor>

          <v-card>
            <v-select
              v-model="selectedCategories"
              multiple
              :options="categoryNames">
            </v-select>
          </v-card>

          <div class='buttons'>
            <v-btn dark class="button font" @click="createArticle()">
              create
            </v-btn>
          </div>

        </div>

      </v-layout>
    </v-container>
  </v-content>
</template>

<script>

import markdownEditor from 'vue-simplemde/src/markdown-editor'
import vSelect from 'vue-select'
import { AXIOS } from '../helper/http-helper'

export default {
  name: 'Editor',
  components: {
    markdownEditor,
    vSelect
  },
  data () {
    return {
      title: '',
      editorContent: '',
      categoryNames: [],
      selectedCategories: [],
      error: ''
    }
  },
  created () {
    this.fetchCategories()
  },
  computed: {
    simplemde () {
      return this.$refs.markdownEditor.simplemde
    }
  },
  methods: {
    createArticle () {
      let article = this.toArticle(this.editorContent)
      AXIOS.post('/api/blog/articles/', article, {
        headers: {
          'Accept': 'application/json',
          'Authorization': 'Bearer ' + this.$store.getters.getToken,
          'Content-Type': 'application/json'
        }
      }).then(response => {
        this.$router.push(`/articles/${response.data.slug}`)
      }).catch(error => {
        if (error.response.status === 409) {
          this.error = 'Article with this title already exists'
        } else {
          this.error = `unexpected error occurred`
        }
        console.log(`error=[${error.response.data.error}] with status=[${error.response.status}]`)
      })
    },
    fetchCategories () {
      AXIOS.get('/api/blog/categories')
        .then(response => {
          response.data
            .map(category => category.name)
            .reduce((list, categoryName) => {
              list.push(categoryName)
              return list
            }, this.categoryNames)
        })
    },
    toCategories (categories) {
      return categories
        .map(category => {
          return {
            name: category
          }
        }).reduce((list, category) => {
          list.push(category)
          return list
        }, [])
    },
    toArticle (editorContent) {
      let contentHtml = this.simplemde.markdown(editorContent)
      let intro = contentHtml.split('<p>')[1].split('</p>')[0]
      let categories = this.toCategories(this.selectedCategories)

      return {
        title: this.title,
        author: 'Paul Werner',
        intro: intro,
        content: contentHtml,
        categories: categories
      }
    }

  }
}
</script>

<style scoped>

  .buttons {
    text-align: center;
    padding-top: 10px;
  }

  .container-fluid {
    background-color: #2d2d2d;
    -webkit-box-shadow: 9px 8px 11px -4px rgba(0, 0, 0, 0.3);
    -moz-box-shadow: 9px 8px 11px -4px rgba(0, 0, 0, 0.3);
    box-shadow: 9px 8px 11px -4px rgba(0, 0, 0, 0.3);
    width: 70%;
    padding: 10px;
    margin: 20px 20px 20px;
  }

  @font-face {
    font-family: TravelingTypewriter;
    src: url(../assets/font/TravelingTypewriter.otf);
  }

  .error {
    text-align: center;
  }

  .font {
    color: #d8d6cf;
    font-weight: normal;
    font-size: 16px;
    font-family: TravelingTypewriter, sans-serif;
  }

  >>> .theme--light.v-input input {
    color: #d8d6cf;
  }

  >>> .primary--text {
    color: #d8d6cf !important;
    caret-color: #d8d6cf !important;

  }

</style>
