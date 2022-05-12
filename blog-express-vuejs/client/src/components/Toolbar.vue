<template>
    <v-toolbar
            app
            class="nav">

        <v-toolbar-title v-text="state.title"/>

    </v-toolbar>
</template>

<script>
    import store from '../Store'

    export default {
        name: "ToolBar",
        data() {
            return {
                store: store,
                state: {
                    title: "Blog"
                }
            }
        }
    }
</script>

<style scoped>

    .nav {
        font-family: Menlo, sans-serif;
        background-color: #6d6e6a;
    }

</style>