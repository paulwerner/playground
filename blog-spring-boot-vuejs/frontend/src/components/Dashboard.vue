<template>
  <div>

    <v-content>
      <v-container fluid fill-height>
        <v-layout justify-center align-center>
          <v-flex shrink>

            <div
              class="container-fluid font"
              v-bind:key="index"
              v-for="(article, index) in articles">
              <div class="text-center">
                <h2>{{article.title}}</h2>

                {{article.author}} @ {{formatDate(article.createdDate)}}

                <br>

                <span :key="index" v-for="(category, index) in article.categories">
                  {{category.name}}
              </span>
                <v-divider></v-divider>
              </div>

              <p v-html="article.intro"></p>

              <div class="text-center">
                <router-link
                  :to="{ name: 'articles', params: {slug: article.slug} }"
                >
                  Read more..
                </router-link>
              </div>

            </div>

          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
  </div>
</template>

<script>

import { AXIOS } from '../helper/http-helper'

export default {
  name: 'Dashboard',
  data () {
    return {
      articles: []
    }
  },
  methods: {
    fetchArticles () {
      AXIOS.get('/api/blog/articles')
        .then(response => {
          this.articles = response.data
        })
    },
    formatDate (dateToFormat) {
      const options = {weekday: 'long', year: 'numeric', month: 'long', day: 'numeric'}
      return new Date(dateToFormat).toLocaleDateString('en-US', options)
    }
  },
  created () {
    this.fetchArticles()
  }
}
</script>

<style scoped>

  span {
    margin: 5px;
  }

  a {
    outline: none;
    text-decoration: none;
    padding: 2px 1px 0;
  }

  a:link {
    color: #d8d6cf;
  }

  a:visited {
    color: #d8d6cf;
  }

  a:focus {
    border-bottom: 1px solid;
  }

  a:hover {
    border-bottom: 1px solid;
  }

  a:active {
    color: #d8d6cf;
  }

  .container-fluid {
    background-color: #2d2d2d;
    -webkit-box-shadow: 9px 8px 11px -4px rgba(0, 0, 0, 0.3);
    -moz-box-shadow: 9px 8px 11px -4px rgba(0, 0, 0, 0.3);
    box-shadow: 9px 8px 11px -4px rgba(0, 0, 0, 0.3);
    width: 70%;
    padding: 30px;
    margin: 20px auto auto;
  }

  .text-center {
    text-align: center;
  }

  @font-face {
    font-family: TravelingTypewriter;
    src: url(../assets/font/TravelingTypewriter.otf);
  }

  .font {
    color: #d8d6cf;
    font-weight: normal;
    font-size: 16px;
    font-family: TravelingTypewriter, sans-serif;
  }

  hr.v-divider {
    margin-top: 10px;
    margin-bottom: 10px;
    border-color: #1a1a1a;
  }

</style>
