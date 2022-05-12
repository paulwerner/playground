from itsdangerous import (TimedJSONWebSignatureSerializer
                          as Serializer, BadSignature, SignatureExpired)
from passlib.apps import custom_app_context as pwd_context

from blog_app.config import settings
from blog_app.database import db

USER_ID = db.Sequence('user_id_seq', start=0)


class User(db.Model):
    __tablename__ = "users"

    id = db.Column(db.Integer, primary_key=True, server_default=USER_ID.next_value())
    email = db.Column(db.String(64))
    username = db.Column(db.String(32), index=True)
    password_hash = db.Column(db.String(128))

    def hash_password(self, password):
        self.password_hash = pwd_context.hash(password)

    def verify_password(self, password):
        return pwd_context.verify(password, self.password_hash)

    def generate_auth_token(self, expiration=600):
        s = Serializer(settings.SECRET_KEY, expires_in=expiration)
        return s.dumps({'id': self.id})

    def to_dict(self):
        return dict(id=self.id, email=self.email, username=self.username)

    @staticmethod
    def verify_auth_token(token):
        s = Serializer(settings.SECRET_KEY)
        try:
            data = s.loads(token)
        except SignatureExpired:
            return None  # valid token, but expired
        except BadSignature:
            return None  # invalid token
        user = User.query.get(data['id'])
        return user

    def __init__(self, username, email, password):
        self.username = username
        self.email = email
        self.hash_password(password)

    def __repr__(self):
        return '<User %r, E-Mail %r>' % self.username, self.email
