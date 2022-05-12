import mongoose from 'mongoose';

const Schema = mongoose.Schema;

const articleSchema = new Schema({
    title: String,
    content: String,
}, {
    timestamps: {
        createdAt: 'created_at',
        updatedAt: 'updated_at'
    }
});

articleSchema.methods.toDto = function () {
    return {
        id: this._id,
        title: this.title,
        content: this.content
    }
};

module.exports = mongoose.model('Article', articleSchema);