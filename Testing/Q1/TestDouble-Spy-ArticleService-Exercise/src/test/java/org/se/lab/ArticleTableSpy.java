package org.se.lab;

import java.util.ArrayList;
import java.util.List;

public class ArticleTableSpy implements ArticleTable {
    public static class LogEntry <P,R> {
        private final String method;
        private final P parameterValue;
        private final R returnValue;

        public LogEntry(String key, P parameterValue, R returnValue) {
            this.method = key;
            this.parameterValue = parameterValue;
            this.returnValue = returnValue;
        }

        public String getMethod() {
            return method;
        }

        public R getReturnValue() {
            return returnValue;
        }

        public P getParameterValue() {
            return parameterValue;
        }
    }

    private final ArticleTable stub;
    protected final List<LogEntry> logs = new ArrayList<>();


    public ArticleTableSpy(ArticleTable stub) {
        this.stub = stub;
    }

    @Override
    public void insert(Article p) {
        logs.add(new LogEntry("ArticleTable.insert", p, null));
        stub.insert(p);
    }

    @Override
    public void update(Article p) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Article findById(int id) {
        return null;
    }

    @Override
    public List<Article> findAll() {
        logs.add(new LogEntry("ArticleTable.findAll()", null, stub.findAll()));
        return stub.findAll();
    }
}
