package org.se.lab;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class SetOrderIdTest {

    private OrderService orderService;

    @BeforeEach
    public void setup() {
        orderService = new OrderService();
    }

    // Part 1:
    // Identify Equivalence Classes
    // Use the Boundary Value Analysis to define border values for invalid cases
    // Equivalence Class correct length
    // 12
    // 1234567890
    // Equivalence Class incorrect length
    // 1 (untere Schranke)
    // 12345678910 (obere Schranke)
    // Equivalence Class correct sign
    // 0123456789
    // Equivalence Class incorrect sign
    // ::: (untere Schranke)
    // /// (obere Schranke)


    // Part 2:
    // Use a parametrized test for all test variants for parameter
    @ParameterizedTest
    @ValueSource(strings = {"123", "1234567890", "0123456789"})
    void setOrderId_ShouldNotThrowAnException(String orderId) {
        assertDoesNotThrow(() -> orderService.setOrderId(orderId));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "12345678910", ":::", "///"})
    void setOrderId_ShouldThrowAnException(String orderId) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.setOrderId(orderId));
        assertTrue(exception.getMessage().toLowerCase().contains("wrong order id: " + orderId));
    }

}
