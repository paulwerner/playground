<template>
    <v-navigation-drawer
            fixed
            v-model="store.state.drawer"
            app
    >

        <v-list>
            <v-list-group
                    v-model="category.active"
                    v-for="(category, i) in state.categories"
                    :key="i"
                    no-action
            >

                <v-list-tile slot="activator">
                    <v-list-tile-content @click="setPostsForCategory(category.title)">
                        <v-list-tile-title>{{ category.title }}</v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile>

                <v-list-tile
                        v-if="category.active"
                        v-for="(post, j) in state.posts"
                        :key="j"
                        @click="setContent(post.content)"
                >
                    <v-list-tile-content>
                        <v-list-tile-title>
                            {{ post.title }}
                        </v-list-tile-title>
                    </v-list-tile-content>

                </v-list-tile>

            </v-list-group>
        </v-list>

    </v-navigation-drawer>
</template>

<script>
    import store from '../Store'
    import BlogService from '../services/BlogService'

    export default {
        name: "NavDrawer",
        data() {
            return {
                store: store,
                state: {
                    categories: [],
                    posts: []
                }
            }
        },
        created() {
            this.setAllCategories()
        },
        methods: {
            async setAllCategories() {
                const response = await BlogService.getAllCategories();
                this.state.categories = response.data;
            },
            async setPostsForCategory(category) {
                const response = await BlogService.getPostsForCategory(category);
                this.state.posts = response.data;
            },
            setContent(content) {
                this.store.state.content = content;
            }
        }
    }
</script>