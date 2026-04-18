package kantseryk.pzks.demo.service;

import kantseryk.pzks.demo.model.Product;
import kantseryk.pzks.demo.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import kantseryk.pzks.demo.request.ProductPageRequest;
import kantseryk.pzks.demo.response.ApiResponse;
import kantseryk.pzks.demo.response.PaginationMetaData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor

/**
 * Author: Alona Kantseryk
 */

public class ProductService {

    private final ProductRepository productRepository;

    @PostConstruct
    public void init() {
        productRepository.deleteAll();
        productRepository.saveAll(createTestProducts());
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public long countProducts() {
        return productRepository.count();
    }

    private List<Product> createTestProducts() {
        List<Product> products = new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            products.add(new Product(
                    "Product " + i,
                    "ART-" + i,
                    "Description for product " + i
            ));
        }

        return products;
    }

    public ApiResponse<List<Product>> getProductsPage(ProductPageRequest request) {
        Pageable pageable = PageRequest.of(
                request.page(),
                request.size(),
                Sort.by(Sort.Direction.ASC, "name")
        );

        Page<Product> page = productRepository.findAll(pageable);

        PaginationMetaData metaData = PaginationMetaData.builder()
                .code(200)
                .success(true)
                .errorMessage(null)
                .number(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();

        return ApiResponse.<List<Product>>builder()
                .meta(metaData)
                .data(page.getContent())
                .build();
    }

    public Product getById(String id) {
        return productRepository.findById(id).orElse(null);
    }
}