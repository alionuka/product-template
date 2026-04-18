package kantseryk.pzks.demo;

import kantseryk.pzks.demo.model.Product;
import kantseryk.pzks.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void shouldContain30ProductsInDatabase() {
        assertEquals(30, productService.countProducts());
    }

    @Test
    void shouldReturn30ProductsFromGetAllProducts() {
        List<Product> products = productService.getAllProducts();

        assertNotNull(products);
        assertEquals(30, products.size());
    }
}