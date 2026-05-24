package kantseryk.pzks.demo;

import kantseryk.pzks.demo.model.Product;
import kantseryk.pzks.demo.request.ProductPageRequest;
import kantseryk.pzks.demo.response.ApiResponse;
import kantseryk.pzks.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(OutputCaptureExtension.class)
@SpringBootTest
class LoggingTest {

    @Autowired
    private ProductService productService;

    @Test
    void whenGetAllProductsThenLogMethodName(CapturedOutput output) {

        List<Product> products = productService.getAllProducts();

        assertNotNull(products);

        assertTrue(output.toString()
                .contains("ProductService.getAllProducts"));

        assertTrue(output.toString()
                .contains("Entering method:"));

        assertTrue(output.toString()
                .contains("completed successfully"));
    }

    @Test
    void whenGetProductsPageThenLogPagingArguments(CapturedOutput output) {

        ProductPageRequest request =
                new ProductPageRequest(1, 5);

        ApiResponse<List<Product>> response =
                productService.getProductsPage(request);

        assertNotNull(response);

        assertTrue(output.toString()
                .contains("ProductService.getProductsPage"));

        assertTrue(output.toString().contains("1"));

        assertTrue(output.toString().contains("5"));
    }

    @Test
    void whenPageIsOutOfRangeThenLogOutOfRange(CapturedOutput output) {

        ProductPageRequest request =
                new ProductPageRequest(10, 5);

        ApiResponse<List<Product>> response =
                productService.getProductsPage(request);

        assertNotNull(response);

        assertTrue(output.toString()
                .contains("ProductService.getProductsPage"));

        assertTrue(output.toString()
                .contains("completed successfully"));
    }
}