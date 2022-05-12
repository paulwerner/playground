"""

initial database setup

Revision ID: 06d456ae41df
Revises: 
Create Date: 2017-08-06 23:10:55.150293

"""
import sqlalchemy as sa
from alembic import op

revision = '06d456ae41df'
down_revision = None
branch_labels = None
depends_on = None

CATEGORY_ID = sa.schema.Sequence("category_id_seq")
ARTICLE_ID = sa.schema.Sequence("article_id_seq")


def upgrade():
    op.execute(sa.schema.CreateSequence(CATEGORY_ID))
    op.create_table('categories',
                    sa.Column('id', sa.Integer(), server_default=sa.text("nextval('category_id_seq')"), nullable=False),
                    sa.Column('name', sa.String(length=50), nullable=True),
                    sa.PrimaryKeyConstraint('id')
                    )
    op.execute(sa.schema.CreateSequence(ARTICLE_ID))
    op.create_table('articles',
                    sa.Column('id', sa.Integer(), server_default=sa.text("nextval('article_id_seq')"), nullable=False),
                    sa.Column('title', sa.String(length=80), nullable=True),
                    sa.Column('content', sa.Text(), nullable=True),
                    sa.Column('pub_date', sa.DateTime(), nullable=True),
                    sa.Column('category_id', sa.Integer(), nullable=True),
                    sa.ForeignKeyConstraint(['category_id'], ['categories.id'], ),
                    sa.PrimaryKeyConstraint('id')
                    )


def downgrade():
    op.drop_table('articles')
    op.drop_table('categories')
    op.execute(sa.schema.DropSequence(CATEGORY_ID))
    op.execute(sa.schema.DropSequence(ARTICLE_ID))
