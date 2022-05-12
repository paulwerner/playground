<template>
  <div id="app">
    <v-app>
      <Toolbar></Toolbar>
      <router-view/>
      <Footer></Footer>
    </v-app>
  </div>
</template>

<script>

import Toolbar from './components/Toolbar'
import Footer from './components/Footer'

export default {
  name: 'App',
  components: {
    Toolbar,
    Footer
  },
  data () {
    return {
      drawer: true,
      mail_lnk: 'mailto:paulwerner@protonmail.com',
      github_lnk: 'https://github.com/pashc',
      xing_lnk: 'https://www.xing.com/profile/Paul_Werner23',
      linkedin_lnk: 'https://de.linkedin.com/in/paul-werner'
    }
  }
}
</script>

<style scoped>

  #app {
    background-color: #1a1a1a;
  }

</style>
