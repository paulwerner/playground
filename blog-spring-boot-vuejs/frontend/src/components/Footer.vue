<template>
  <v-footer class="footer font" :fixed="state.fixed" app>
    <div class="panel">
      &copy; 2018 by Paul Werner
    </div>
    <div class="buttons">
      <button @click="state.dialog.disclaimer = true">disclaimer</button>
    </div>

    <!-- DISCLAIMER -->
    <v-layout row justify-center>
      <v-dialog v-model="state.dialog.disclaimer" width="600px">
        <v-card>
          <v-container class="container-fluid font">

            <v-card-title class="justify-center">
              <h2>&lt;disclaimer&gt;</h2>
            </v-card-title>
            <v-card-text>
              <div>

                <p>
                  The information on this Website is for general information purposes only.
                  Paul Werner assumes no responsibility for errors or omissions in the contents
                  of this Website.
                </p>

                <p> Paul Werner also reserves the right to make additions, deletions, modifications to the
                  contents of the Website or even shut down the Website at any time without prior notice.
                </p>

                <p>
                  Paul Werner does not guarantee that the Website is free of viruses or other harmful components.
                </p>

                <p>
                  The Website contains links to external websites. Paul Werner is not liable or responsible for
                  these external websites and contents. Should you have questions about these external websites,
                  please direct them to the respecting providers.
                </p>
                <div>
                  Icons made by
                  <a href="https://www.flaticon.com/authors/dave-gandy" title="Dave Gandy">Dave Gandy</a> from
                  <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> are licensed by
                  <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">
                    CC 3.0 BY
                  </a>
                  <br>
                  Last updated: December 1, 2018
                </div>

              </div>
            </v-card-text>

            <v-card-title class="justify-center">
              <h2>&lt;legal&gt;</h2>
            </v-card-title>
            <v-card-text>
              <div>
                <p>Information pursuant to sec. 5 German Telemedia Act</p>
                <p>
                  Paul Werner<br/>
                  Holstenstr. 111<br/>
                  22767 Hamburg</p>
                <p>
                  email: contact@paulwerner.org
                </p>
                <p>
                  The content and works on this website (“Website”) are subject to German
                  copyright law. The reproduction, processing, distribution and any kind
                  of exploitation outside the limits of copyright require the written consent
                  of Paul Werner or the respective author.
                </p>
              </div>
            </v-card-text>
            <v-card-title class="justify-center">
              <h2>&lt;privacy&gt;</h2>
            </v-card-title>
            <v-card-text>
              <div>
                <p>
                  Controller of this website (“Website”) pursuant to the General Data Protection
                  Regulation (“GDPR”) is Paul Werner.
                </p>
                <p>
                  When you visit this Website your IP address of the requesting computer, date and
                  time of access, name and URL of the retrieved file, website from which the access
                  is made (referrer URL), and the browser used and, if applicable, the operating
                  system of your computer and the name of your access provider are collected and
                  processed. These data are processed to ensure a smooth connection setup of the
                  website, a comfortable use of the Website, evaluation of system security and stability
                  and for other administrative purposes. The legal basis for processing is
                  Art. 6 (1) f) GDPR. The data is deleted as soon as it is not longer needed for the
                  above mentioned purposes.
                </p>
                <p>
                  When you contact Paul Werner via the above mentioned email, your email address and the
                  personal data you might mention in it are processed for the purpose of answering you
                  and/or responding to your corresponding request
                </p>
                <p>
                  As a data subject you have the rights pursuant to chapter 3 <br> (Art. 12 – 23) GDPR.
                </p>
              </div>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <button @click="state.dialog.disclaimer = false">close</button>
            </v-card-actions>
          </v-container>
        </v-card>
      </v-dialog>
    </v-layout>

  </v-footer>
</template>

<script>
export default {
  name: 'Footer',
  data () {
    return {
      state: {
        fixed: false,
        dialog: {
          disclaimer: false,
          privacy: false,
          legal: false
        }
      }
    }
  }
}
</script>

<style scoped>

  .panel {
    background-color: #abb1ba;
    margin-bottom: 0;
    padding: 5px;
  }

  .font {
    color: #d8d6cf;
    font-weight: bold;
    font-size: 16px;
    font-family: Courier, sans-serif;
  }

  .footer {
    color: #404040;
    background-color: #abb1ba;
    padding: 5px;
  }

  .buttons {
    position: absolute;
    right: 5px;
    padding: 5px;
  }

  button:focus {
    cursor: pointer;
    outline: none;
  }

  .container-fluid {
    background-color: #2d2d2d;
    -webkit-box-shadow: 9px 8px 11px -4px rgba(0, 0, 0, 0.3);
    -moz-box-shadow: 9px 8px 11px -4px rgba(0, 0, 0, 0.3);
    box-shadow: 9px 8px 11px -4px rgba(0, 0, 0, 0.3);
    width: 600px;
    padding: 20px;
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

</style>
