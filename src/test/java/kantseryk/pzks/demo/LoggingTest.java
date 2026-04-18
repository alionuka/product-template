package kantseryk.pzks.demo;

import kantseryk.pzks.demo.model.Product;
import kantseryk.pzks.demo.request.ProductPageRequest;
import kantseryk.pzks.demo.response.ApiResponse;
import kantseryk.pzks.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(OutputCaptureExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoggingTest {

    @Autowired
    private ProductService underTest;

    @Test
    void testLoggingOutputBeforeMethodGetById(CapturedOutput output) {
        String id = underTest.getAllProducts().get(0).getId();

        Product product = underTest.getById(id);

        assertNotNull(product);
        assertTrue(output.toString().contains("Entering method:"));
        assertTrue(output.toString().contains("ProductService.getById"));
        assertTrue(output.toString().contains(id));
    }

    @Test
    void testLoggingOutputAfterMethodGetById(CapturedOutput output) {
        Product first = underTest.getAllProducts().get(0);

        Product product = underTest.getById(first.getId());

        assertNotNull(product);
        assertTrue(output.toString().contains("ProductService.getById"));
        assertTrue(output.toString().contains("completed successfully"));
        assertTrue(output.toString().contains(first.getId()));
        assertTrue(output.toString().contains(first.getName()));
    }

    @Test
    void testLoggingOutputBeforeMethodGetProductsPage(CapturedOutput output) {
        ProductPageRequest request = new ProductPageRequest(0, 5);

        ApiResponse<List<Product>> page = underTest.getProductsPage(request);

        assertNotNull(page);
        assertTrue(output.toString().contains("ProductService.getProductsPage"));
        assertTrue(output.toString().contains("0"));
        assertTrue(output.toString().contains("5"));
    }

    @Test
    void testLoggingOutputForGetAllProducts(CapturedOutput output) {
        List<Product> products = underTest.getAllProducts();

        assertNotNull(products);
        assertTrue(output.toString().contains("ProductService.getAllProducts"));
        assertTrue(output.toString().contains("Entering method:"));
        assertTrue(output.toString().contains("completed successfully"));
    }

    @Test
    void testLoggingOutputContainsPagingArguments(CapturedOutput output) {
        ProductPageRequest request = new ProductPageRequest(1, 5);

        ApiResponse<List<Product>> response = underTest.getProductsPage(request);

        assertNotNull(response);
        assertTrue(output.toString().contains("ProductService.getProductsPage"));
        assertTrue(output.toString().contains("1"));
        assertTrue(output.toString().contains("5"));
    }

    @Test
    void testLoggingOutputForCountProducts(CapturedOutput output) {
        long count = underTest.countProducts();

        assertTrue(count > 0);
        assertTrue(output.toString().contains("ProductService.countProducts"));
        assertTrue(output.toString().contains("completed successfully"));
    }

    @Test
    void testLoggingOutputContainsResultForGetById(CapturedOutput output) {
        String id = underTest.getAllProducts().get(0).getId();

        Product product = underTest.getById(id);

        assertNotNull(product);
        assertTrue(output.toString().contains("ProductService.getById"));
        assertTrue(output.toString().contains(product.getName()));
    }

    @Test
    void testLoggingOutputAfterMethodGetProductsPage(CapturedOutput output) {
        ProductPageRequest request = new ProductPageRequest(0, 5);

        ApiResponse<List<Product>> response = underTest.getProductsPage(request);

        assertNotNull(response);
        assertTrue(output.toString().contains("ProductService.getProductsPage"));
        assertTrue(output.toString().contains("completed successfully"));
    }
}