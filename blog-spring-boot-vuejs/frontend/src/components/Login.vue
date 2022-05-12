<template>
  <v-content>
    <v-container fluid fill-height>
      <v-layout justify-center align-center>
        <div class="container-fluid font">

          <h2>Login</h2>

          <v-form>

            <p class="error">{{error}}</p>

            <v-text-field
              v-model="username"
              :rules="nameRules"
              label="name"
              required
            ></v-text-field>
            <v-text-field
              :type="'password'"
              v-model="password"
              :rules="passwordRules"
              label="password"
              required
            ></v-text-field>

            <v-btn dark class="button font" @click="login()">
              login
            </v-btn>

          </v-form>

        </div>
      </v-layout>
    </v-container>
  </v-content>
</template>

<script>
import * as types from '../store/mutation-types'

export default {
  name: 'Login',
  data () {
    return {
      username: '',
      password: '',
      error: '',
      nameRules: [
        v => !!v || 'name is required'
      ],
      passwordRules: [
        v => !!v || 'password is required'
      ]
    }
  },
  methods: {
    login () {
      const self = this
      this.$store.dispatch(types.LOGIN, {
        username: this.username,
        password: this.password
      }).then((response) => {
        this.$store.commit(types.LOGIN_SUCCESS, {
          token: response.data,
          username: self.username
        })
        this.$router.push('/')
      }).catch((error) => {
        if (error.response) {
          if (error.response.status === 404) {
            this.error = 'username could not be found'
          } else if (error.response.status === 422) {
            this.error = 'wrong credentials'
          } else {
            this.error = 'unexpected error occurred'
          }
          console.log(error.response.data.error)
          this.$store.commit(types.LOGIN_WRONG_CREDENTIALS)
          this.$router.push('/login')
        } else {
          console.log(error)
          this.error = 'unexpected error occurred'
          this.$store.commit(types.LOGIN_WRONG_CREDENTIALS)
          this.$router.push('/login')
        }
      })
    }
  }
}
</script>

<style scoped>

  .container-fluid {
    background-color: #2d2d2d;
    -webkit-box-shadow: 9px 8px 11px -4px rgba(0, 0, 0, 0.3);
    -moz-box-shadow: 9px 8px 11px -4px rgba(0, 0, 0, 0.3);
    box-shadow: 9px 8px 11px -4px rgba(0, 0, 0, 0.3);
    width: 50%;
    text-align: center;
    padding: 80px;
    margin: 20px auto auto;
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

  .error {
    margin: 5px;
  }

  >>> .theme--light.v-input input {
    color: #d8d6cf;
  }

  >>> .primary--text {
    color: #d8d6cf !important;
  }

</style>
