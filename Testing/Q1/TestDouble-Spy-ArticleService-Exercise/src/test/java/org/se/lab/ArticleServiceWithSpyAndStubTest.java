package org.se.lab;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Provide an isolated test without DOCs
 * The test already includes a Stub to mimic the access to the database.
 *
 * Part 1:
 * Implement the Spy class to verify indirect outputs and inputs.
 * Implement only those parts which are relevant for the tests!
 *
 * Part 2:
 * Extend the Test to validate the provided parameters and returned values.
 *
 */
class ArticleServiceWithSpyAndStubTest {

    private ArticleTableStub stub;
    private ArticleTableSpy spy;
    private ArticleService service;

    @BeforeEach
    public void setup() {
        // service -> spy -> stub
        stub = new ArticleTableStub();
        spy = new ArticleTableSpy(stub);
        service = new ArticleService((ArticleTable) spy);
    }

    @Test
    void testAddArticle() {
        // setup
        Article book = new Article(1, "Effective Java", 3495);

        // exercise
        service.addArticle(book);

        // state verification
        Article a = stub.articles.get(0);
        Assertions.assertEquals(1, a.getId());
        Assertions.assertEquals("Effective Java", a.getDescription());
        Assertions.assertEquals(3495, a.getPrice());

        // behavioral verification
        Assertions.assertEquals(1, spy.logs.size());
        Assertions.assertTrue(spy.logs.get(0).getMethod().contains("ArticleTable.insert"));

        Assertions.assertEquals(spy.logs.get(0).getParameterValue(), a);
        Assertions.assertNull(spy.logs.get(0).getReturnValue());
    }

    @Test
    void testNumberOfArticles() {
        // setup
        stub.articles = Arrays.asList(
                new Article(1, "Effective Java", 3495),
                new Article(2, "Java Concurrency in Practice", 3895),
                new Article(3, "Clean Code: A Handbook of Agile Software Craftsmanship", 3095)
        );

        // exercise
        int number = service.numberOfArticles();

        // state verification
        Assertions.assertEquals(3, number);

        // behavioral verification
        Assertions.assertEquals(1, spy.logs.size());
        Assertions.assertTrue(spy.logs.get(0).getMethod().contains("ArticleTable.findAll()"));

        Assertions.assertNull(spy.logs.get(0).getParameterValue());
        Assertions.assertEquals(spy.logs.get(0).getReturnValue(), stub.articles);
    }
}
