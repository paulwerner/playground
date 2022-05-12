import mongoose from 'mongoose';

const Schema = mongoose.Schema;

const categorySchema = new Schema({
    title: String
}, {
    timestamps: {
        createdAt: 'created_at',
        updatedAt: 'updated_at'
    }
});

categorySchema.methods.toDto = function () {
    return {
        id: this._id,
        title: this.title
    }
};

module.exports = mongoose.model('Category', categorySchema);