package kantseryk.pzks.demo.service;

import kantseryk.pzks.demo.model.Product;
import kantseryk.pzks.demo.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}