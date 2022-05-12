<template>
    <v-content>
        <v-container class="container-fluid font text-center">
            <v-slide-y-transition mode="out-in">
                <v-layout column align-center>

                    <img src="../assets/avatar_small.png" class="img-circle img-width" alt="Paul Werner">

                    <h2>Hello World</h2>

                    <p>
                        This blog is currently under development...
                    </p>

                </v-layout>
            </v-slide-y-transition>
        </v-container>
    </v-content>
</template>

<script>
    import store from '../Store'

    export default {
        name: "Content",
        data() {
            return {
                store: store
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
        margin-top: 0;
        margin-bottom: 20px;
        max-width: 1000px;
        padding: 20px;
    }

    .font {
        color: #d8d6cf;
        font-weight: normal;
        font-size: 16px;
        font-family: Courier, sans-serif;
    }

</style>
