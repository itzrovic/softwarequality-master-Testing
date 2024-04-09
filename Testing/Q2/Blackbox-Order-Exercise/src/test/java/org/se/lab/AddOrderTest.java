package org.se.lab;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AddOrderTest {
    private OrderService orderService;

    @BeforeEach
    public void setupFresh() {
        orderService = new OrderService();
    }

    // Part 1:
    // Identify Equivalence Classes
    // Use the Boundary Value Analysis to define border values for invalid cases
    // articleNr = [0,10000]
    // quantity = [1,10]
    // Equivalence Class correct quantity
    // 1
    // 10
    // Equivalence Class incorrect quantity
    // 0 (untere Schranke)
    // 11 (obere Schranke)
    // Equivalence Class correct articleNr
    // 0
    // 10000
    // Equivalence Class incorrect articleNr
    // -1 (untere Schranke)
    // 10001 (obere Schranke)

    // Part 2:
    // Use a parametrized test for all test variants for parameter
    @ParameterizedTest
    @ValueSource(ints = {1, 10})
    void addOrder_correctQuantity_ShouldNotThrowAnException(int quantity) {
        assertDoesNotThrow(() -> orderService.addOrder(quantity, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 11})
    void addOrder_incorrectQuantity_ShouldThrowAnException(int quantity) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.addOrder(quantity, 1));
        assertTrue(exception.getMessage().toLowerCase().contains("wrong quantity: " + quantity));
    }

    @ParameterizedTest
    @ValueSource(longs = {0, 10000})
    void addOrder_correctArticleNr_ShouldNotThrowAnException(long articleNr) {
        assertDoesNotThrow(() -> orderService.addOrder(1, articleNr));
    }

    @ParameterizedTest
    @ValueSource(longs = {-1, 10001})
    void addOrder_incorrectArticleNr_ShouldThrowAnException(long articleNr) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.addOrder(1, articleNr));
        assertTrue(exception.getMessage().toLowerCase().contains("wrong articlenr: " + articleNr));
    }

}
