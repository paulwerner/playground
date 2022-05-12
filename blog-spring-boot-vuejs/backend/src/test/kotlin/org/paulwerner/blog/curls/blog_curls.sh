#!/usr/bin/env bash
curl -H "Content-Type: application/json" -X POST -d '{
        "name": "Drama"
}' http://localhost:8088/api/blog/categories;

curl -H "Content-Type: application/json" -X POST -d '{
        "name": "Fantasy"
}' http://localhost:8088/api/blog/categories

curl -H "Content-Type: application/json" -X POST -d '{
        "name": "Philosophy"
}' http://localhost:8088/api/blog/categories;


curl -H "Content-Type: application/json" -X POST -d '{
        "title": "The Obstacle Is the Way: The Timeless Art of Turning Trials into Triumph",
        "author": "Ryan Holiday",
        "content": "Obstacles are only problematic with the wrong perception",
        "categories": []
}' http://localhost:8088/api/blog/articles;

curl -H "Content-Type: application/json" -X POST -d '{
        "title": "1984",
        "author": "Gorge Orwell",
        "content": "Big Brother is watching you",
        "categories": [{"name": "Drama"}]
}' http://localhost:8088/api/blog/articles;

curl -H "Content-Type: application/json" -X POST -d '{
        "title": "The Ego is the enemy",
        "author": "Ryan Holiday",
        "content": "Using the ego correctly is not easy",
        "categories": [
            {"name": "Drama"},
            {"name":"Fantasy"}
        ]
}' http://localhost:8088/api/blog/articles;

curl -H "Content-Type: application/json" -X POST -d '{
        "title": "Deep Work: Rules for Focused Success in a Distracted World",
        "author": "Cal Newport",
        "content": "Rules for focused success in a distracted world",
        "categories" : []
}' http://localhost:8088/api/blog/articles;
