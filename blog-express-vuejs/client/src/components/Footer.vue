<template>
    <v-footer class="footer font" :fixed="state.fixed" app>
        <div class="panel">
            &copy; 2018 by Paul Werner
        </div>
    </v-footer>
</template>

<script>
    export default {
        name: "Footer",
        data() {
            return {
                state: {
                    fixed: false
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
        font-family: Menlo, sans-serif;
        font-weight: bold;
    }

    .footer {
        color: #404040;
        background-color: #abb1ba;
        padding: 5px;
    }

</style>
