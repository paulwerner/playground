import express from 'express';
import Category from '../models/category';

const router = express.Router();

// MIDDLEWARE
router.use((req, res, next) => {
    console.log('Logging category requests');

    next();
});

// ROUTES
router.route('/categories')
    .get((req, res) => {
        Category.find((err, categories) => {
            if (err)
                res.send(err);

            res.json(categories.map(category => category.toDto()));
        })
    })
    .post((req, res) => {
        const category = new Category();

        category.title = req.body.title;

        category.save(function (err) {
            if (err)
                res.send(err);

            res.json({message: 'Category created!'});
        });
    });

router.route('/categorys/:category_id')
    .get((req, res) => {
        Category.findById(
            req.params.category_id,
            (err, Category) => {
                if (err)
                    res.send(err);

                if (Category)
                    res.json(Category.toDto());
                else
                    res.json({error: 'category could not be found.'});
            })
    })
    .put((req, res) => {
        Category.findById(
            req.params.category_id,
            (err, Category) => {
                if (err)
                    res.send(err);

                Category.title = req.body.title;

                Category.save((err) => {
                    if (err)
                        res.send(err);

                    res.json({message: 'successfully updated!'});
                })
            }
        )
    })
    .delete((req, res) => {
        Category.remove({
            _id: req.params.category_id
        }, (err) => {
            if (err)
                res.send(err);

            res.json({message: "successfully deleted"})
        })
    });

module.exports = router;