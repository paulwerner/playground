<template>
    <v-app class="app">
        <Toolbar/>
        <Content/>
        <Footer/>
    </v-app>
</template>

<script>
    import Content from './components/Content'
    import Footer from './components/Footer'
    import NavDrawer from './components/NavDrawer'
    import Toolbar from './components/Toolbar'

    export default {
        components: {
            Content,
            Footer,
            NavDrawer,
            Toolbar
        },
    }
</script>

<style scoped>

    .app {
        background-color: #1a1a1a;
        padding: 20px;
        width: 100%;
        height: 100%;
        top: 0;
        left: 0;
    }

</style>
