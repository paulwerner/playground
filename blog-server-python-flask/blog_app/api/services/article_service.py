from blog_app.api.errors.article_not_found_error import ArticleNotFoundException
from blog_app.api.errors.invalid_arguments_for_creation_error import InvalidArgumentsForCreationException
from blog_app.api.services import category_service
from blog_app.database import db
from blog_app.database.models.blog.article import Article


def find(article_id):
    found_article = Article.query.get(article_id)

    if found_article is None:
        raise ArticleNotFoundException(article_id)

    return found_article


def paginate(data):
    page = data.get('page', 1)
    per_page = data.get('per_page', 10)

    return Article.query.paginate(page, per_page, error_out=False).items


def create(data):
    errors = []

    title = __get_valid(data, 'title', errors)
    content = __get_valid(data, 'content', errors)
    category_id = __get_valid(data, 'category_id', errors)

    if len(errors) > 0:
        raise InvalidArgumentsForCreationException(errors)

    article = Article(title=title,
                      content=content,
                      category_id=category_id)

    db.session.add(article)
    db.session.commit()

    return article


def update(article_id, data):
    article = find(article_id)

    title = data.get('title')
    content = data.get('content')
    category_id = data.get('category_id')

    if title:
        article.title = title
    if content:
        article.content = content
    if category_id:
        article.category = category_service.find(category_id)

    db.session.add(article)
    db.session.commit()


def delete(article_id):
    article = find(article_id)

    db.session.delete(article)
    db.session.commit()


def __get_valid(data, key, errors):
    value = data.get(key)
    if not value:
        errors.append("article %s can't be empty" % key)
    return value
