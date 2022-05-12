import Api from '../services/Api'

export default {
    getAllCategories: function () {
        return Api().get('/categories')
    },
    getPostsForCategory: function (category) {
        return Api().get('/posts', {
            params: {
                category: category
            }
        })
    }
}
