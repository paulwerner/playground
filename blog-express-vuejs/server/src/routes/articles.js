import express from 'express';
import Article from '../models/article';

const router = express.Router();

// MIDDLEWARE
router.use((req, res, next) => {
    console.log('Middleware is logging after request');

    next();
});

// ROUTES
router.route('/articles')
    .get((req, res) => {
        Article.find((err, articles) => {
            if (err)
                res.send(err);

            res.json(articles.map(article => article.toDto()));
        })
    })
    .post((req, res) => {
        const article = new Article();

        article.title = req.body.title;
        article.content = req.body.content;

        article.save(function (err) {
            if (err)
                res.send(err);

            res.json({message: 'Article created!'});
        })
    });

router.route('/articles/:article_id')
    .get((req, res) => {
        Article.findById(
            req.params.article_id,
            (err, article) => {
                if (err)
                    res.send(err);

                if (article)
                    res.json(article.toDto());
                else
                    res.json({error: 'article could not be found.'});
            })
    })
    .put((req, res) => {
        Article.findById(
            req.params.article_id,
            (err, article) => {
                if (err)
                    res.send(err);

                article.title = req.body.title;
                article.content = req.body.content;

                article.save((err) => {
                    if (err)
                        res.send(err);

                    res.json({message: 'successfully updated!'});
                })
            }
        )
    })
    .delete((req, res) => {
        Article.remove({
            _id: req.params.article_id
        }, (err) => {
            if (err)
                res.send(err);

            res.json({message: "successfully deleted"})
        })
    });

module.exports = router;