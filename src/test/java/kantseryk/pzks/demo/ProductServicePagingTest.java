package kantseryk.pzks.demo;

import kantseryk.pzks.demo.model.Product;
import kantseryk.pzks.demo.request.ProductPageRequest;
import kantseryk.pzks.demo.response.ApiResponse;
import kantseryk.pzks.demo.response.PaginationMetaData;
import kantseryk.pzks.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServicePagingTest {

    @Autowired
    private ProductService productService;

    @Test
    void whenHappyPathThenOk() {
        ProductPageRequest request = new ProductPageRequest(0, 5);

        ApiResponse<List<Product>> response = productService.getProductsPage(request);

        assertNotNull(response);
        assertNotNull(response.getMeta());
        assertInstanceOf(PaginationMetaData.class, response.getMeta());

        PaginationMetaData meta = (PaginationMetaData) response.getMeta();

        assertEquals(200, meta.getCode());
        assertTrue(meta.isSuccess());
        assertNull(meta.getErrorMessage());

        assertEquals(0, meta.getNumber());
        assertEquals(5, meta.getSize());
        assertEquals(30, meta.getTotalElements());
        assertEquals(6, meta.getTotalPages());
        assertTrue(meta.isFirst());
        assertFalse(meta.isLast());

        assertNotNull(response.getData());
        assertFalse(response.getData().isEmpty());
        assertEquals(5, response.getData().size());
    }

    @Test
    void whenSizeIs7AndPageIs4ThenLastTrueAndSizeEquals2() {
        ProductPageRequest request = new ProductPageRequest(4, 7);

        ApiResponse<List<Product>> response = productService.getProductsPage(request);
        PaginationMetaData meta = (PaginationMetaData) response.getMeta();

        assertNotNull(response);
        assertNotNull(meta);
        assertEquals(4, meta.getNumber());
        assertEquals(7, meta.getSize());
        assertEquals(30, meta.getTotalElements());
        assertEquals(5, meta.getTotalPages());
        assertFalse(meta.isFirst());
        assertTrue(meta.isLast());

        assertNotNull(response.getData());
        assertEquals(2, response.getData().size());
    }

    @Test
    void whenPageIs0AndSizeIs10ThenFirstTrueAndLastFalse() {
        ProductPageRequest request = new ProductPageRequest(0, 10);

        ApiResponse<List<Product>> response = productService.getProductsPage(request);
        PaginationMetaData meta = (PaginationMetaData) response.getMeta();

        assertNotNull(meta);
        assertTrue(meta.isFirst());
        assertFalse(meta.isLast());
        assertEquals(3, meta.getTotalPages());
        assertEquals(10, response.getData().size());
    }

    @Test
    void whenPageIs2AndSizeIs10ThenLastTrue() {
        ProductPageRequest request = new ProductPageRequest(2, 10);

        ApiResponse<List<Product>> response = productService.getProductsPage(request);
        PaginationMetaData meta = (PaginationMetaData) response.getMeta();

        assertNotNull(meta);
        assertFalse(meta.isFirst());
        assertTrue(meta.isLast());
        assertEquals(2, meta.getNumber());
        assertEquals(10, response.getData().size());
    }

    @Test
    void whenPageIsOutOfRangeThenDataIsEmptyAndMetaExists() {
        ProductPageRequest request = new ProductPageRequest(10, 5);

        ApiResponse<List<Product>> response = productService.getProductsPage(request);
        PaginationMetaData meta = (PaginationMetaData) response.getMeta();

        assertNotNull(response);
        assertNotNull(meta);
        assertNotNull(response.getData());
        assertTrue(response.getData().isEmpty());
        assertEquals(10, meta.getNumber());
        assertEquals(6, meta.getTotalPages());
        assertEquals(30, meta.getTotalElements());
    }
}