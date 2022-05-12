const supertest = require('supertest');
const mongoose = require('mongoose');

describe('Test Articles', function () {
    let server;
    let request;

    before(function () {
        server = require('../src/main');
        request = supertest.agent(server);
    });
    after(function (done) {
        mongoose.connection.close();
        server.close(done);
    });

    it('should respond with 200', function (done) {
        request
            .get('/api/articles')
            .set('Accept', 'application/json')
            .expect('Content-Type', 'application/json; charset=utf-8')
            .expect(200, [])
            .end(function (err) {
                if (err) done(err);
                done();
            });
    });
});
