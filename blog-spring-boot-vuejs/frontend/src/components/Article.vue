<template>
  <v-content>
    <v-container fluid fill-height>
      <v-layout justify-center align-center>

        <div class="container-fluid font">

          <div class="text-center">

            <h2>{{article.title}}</h2>

            by {{article.author}} @ {{formatDate(article.createdDate)}} <br>

            <span
              :key="index"
              v-for="(category, index) in article.categories"
            >
              {{category.name}}
            </span>
          </div>

          <v-divider></v-divider>

          <p v-html="article.content"></p>

        </div>
      </v-layout>
    </v-container>
  </v-content>

</template>

<script>

import { AXIOS } from '../helper/http-helper'

export default {
  name: 'Article',
  data () {
    return {
      article: {}
    }
  },
  methods: {
    fetchArticle (slug) {
      AXIOS.get(`/api/blog/articles/${slug}`)
        .then(response => {
          this.article = response.data
        })
        .catch(error => {
          console.log(error.message)
          this.$router.push('/')
        })
    },
    formatDate (dateToFormat) {
      const options = {weekday: 'long', year: 'numeric', month: 'long', day: 'numeric'}
      return new Date(dateToFormat).toLocaleDateString('en-US', options)
    }
  },
  mounted () {
    this.fetchArticle(this.$route.params.slug)
  }
}
</script>
<style scoped>

  >>> h1, >>> h2, >>> h3, >>> h4 {
    text-align: center;
    margin: 15px auto 15px auto;
  }

  >>> a {
    outline: none;
    padding: 2px 1px 0;
  }

  >>> a:link {
    color: #d8d6cf;
  }

  >>> a:visited {
    color: #d8d6cf;
  }

  >>> a:focus {
    border-bottom: 1px solid;
  }

  >>> a:hover {
    border-bottom: 1px solid;
    text-decoration: none;
  }

  >>> a:active {
    color: #d8d6cf;
  }

  >>> blockquote {
    border-left: 3px solid #666;
    display: block;
    padding: 1em;
  }

  >>> code {
    background: #ebedef;
    border-left: 3px solid #666;
    color: #666;
    page-break-inside: avoid;
    font-family: "Courier New", Courier monospace;
    font-size: 15px;
    line-height: 1.4em;
    margin-bottom: 1.6em;
    max-width: 100%;
    overflow: auto;
    padding: 1em;
    display: block;
    word-wrap: break-word;
  }

  >>> th {
    border: 1px #1a1a1a solid;
    padding: .5em .5em .5em .5em;
  }

  >>> td {
    border: 1px #1a1a1a solid;
    padding: .5em .5em .5em .5em;
  }

  >>> table {
    background: #ebedef;
    border: 3px #1a1a1a solid;
    border-collapse: collapse;
    color: #666;
    width: 100%;
    font-size: 15px;
    font-weight: bold;
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

  @font-face {
    font-family: TravelingTypewriter;
    src: url(../assets/font/TravelingTypewriter.otf);
  }

  .text-center {
    text-align: center;
  }

  .font {
    color: #d8d6cf;
    font-weight: normal;
    font-size: 16px;
    font-family: TravelingTypewriter, sans-serif;
  }

  span {
    padding: 5px;
  }

  hr.v-divider {
    margin-top: 10px;
    margin-bottom: 10px;
    border-color: #1a1a1a;
  }

  >>> img {
    border-radius: 8px;
    border: 1px #1a1a1a solid;
    display: block;
    margin-left: auto;
    margin-right: auto;
    max-height: 480px;
    max-width: 90%;
  }

</style>
