package kantseryk.pzks.demo;
import kantseryk.pzks.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
/**
 * Author: Alona Kantseryk
 */
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void shouldContain30ProductsInDatabase() {
        assertEquals(30, productService.countProducts());
    }
}