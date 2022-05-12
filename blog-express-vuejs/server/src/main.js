import bodyParser from 'body-parser';
import express from 'express';
import fs from 'fs';
import mongoose from 'mongoose';
import morgan from 'morgan';


// =================================================
//                      Config
// =================================================

const node_env = process.env.NODE_ENV || "prod",
    port = process.env.SERVER_PORT || 3000,
    db_host = process.env.DB_HOST || 'localhost',
    db_port = process.env.DB_PORT || '27017',
    database = process.env.DATABASE || 'blog';


// =================================================
//                      Setup
// =================================================

const app = express();

app.set('trust proxy', true);
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

// morgan request logging
let flag,
    log_dir;

if ("dev" !== node_env) {
    log_dir = './log/access.log';
    flag = 'a';
    app.use(morgan('combined'));

} else {
    log_dir = './test/access.log';
    flag = 'w';
    app.use(morgan('dev'));
}

app.use(morgan('common', {stream: fs.createWriteStream(log_dir, {flags: flag})}));

// mongodb connection
const db_url = `mongodb://${db_host}:${db_port}/${database}`;
mongoose.connect(db_url)
    .then(() => {
        console.log('successfully connected to ' + db_url);
    });

// =================================================
//                      Routes
// =================================================

const articles = require('./routes/articles');
const categories = require('./routes/categories');

app.use('/api', articles);
app.use('/api', categories);


// start application
const server = app.listen(port);

module.exports = server;
