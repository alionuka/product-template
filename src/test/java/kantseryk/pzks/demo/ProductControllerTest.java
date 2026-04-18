package kantseryk.pzks.demo;

/**
 * Author: Alona Kantseryk
 */

import kantseryk.pzks.demo.controller.ProductController;
import kantseryk.pzks.demo.model.Product;
import kantseryk.pzks.demo.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @Test
    void shouldReturnNotNullApiResponse() {
        ApiResponse<List<Product>> response = productController.getAllProducts();

        assertNotNull(response);
    }

    @Test
    void shouldReturnSuccessMetaCode200() {
        ApiResponse<List<Product>> response = productController.getAllProducts();

        assertNotNull(response.getMeta());
        assertEquals(200, response.getMeta().getCode());
        assertTrue(response.getMeta().isSuccess());
    }

    @Test
    void shouldReturnNullErrorMessageForSuccessfulResponse() {
        ApiResponse<List<Product>> response = productController.getAllProducts();

        assertNotNull(response.getMeta());
        assertNull(response.getMeta().getErrorMessage());
    }

    @Test
    void shouldReturn30ProductsInsideResponseData() {
        ApiResponse<List<Product>> response = productController.getAllProducts();

        assertNotNull(response.getData());
        assertEquals(30, response.getData().size());
    }

    @Test
    void shouldReturnNonEmptyProductList() {
        ApiResponse<List<Product>> response = productController.getAllProducts();

        assertNotNull(response.getData());
        assertFalse(response.getData().isEmpty());
    }

    @Test
    void shouldReturnProductWithCorrectName() {
        ApiResponse<List<Product>> response = productController.getAllProducts();

        Product first = response.getData().get(0);

        assertNotNull(first.getName());
        assertTrue(first.getName().contains("Product"));
    }

    @Test
    void shouldReturnProductWithArticle() {
        ApiResponse<List<Product>> response = productController.getAllProducts();

        Product first = response.getData().get(0);

        assertNotNull(first.getArticle());
        assertTrue(first.getArticle().startsWith("ART-"));
    }

    @Test
    void shouldReturnProductWithDescription() {
        ApiResponse<List<Product>> response = productController.getAllProducts();

        Product first = response.getData().get(0);

        assertNotNull(first.getDescription());
    }

    @Test
    void shouldReturnProductWithId() {
        ApiResponse<List<Product>> response = productController.getAllProducts();

        Product first = response.getData().get(0);

        assertNotNull(first.getId());
    }

    @Test
    void shouldReturnMetaObject() {
        ApiResponse<List<Product>> response = productController.getAllProducts();

        assertNotNull(response.getMeta());
    }

    @Test
    void shouldReturnDataObject() {
        ApiResponse<List<Product>> response = productController.getAllProducts();

        assertNotNull(response.getData());
    }

    @Test
    void shouldReturnExactly30ProductsInResponse() {
        ApiResponse<List<Product>> response = productController.getAllProducts();

        assertEquals(30, response.getData().size());
    }

}