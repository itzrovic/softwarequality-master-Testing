package org.se.lab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleTableStub implements ArticleTable {

    protected List<Article> articles = new ArrayList<>();
    protected RuntimeException exception;

    public void insert(Article article) {
        if (exception != null)
            throw exception;

        articles.add(article);
    }

    public void update(Article p) {

    }

    public void delete(int id) {

    }

    public Article findById(int id) {
        return null;
    }

    public List<Article> findAll() {
        if (exception != null)
            throw exception;

        return Collections.unmodifiableList(articles);
    }
}
