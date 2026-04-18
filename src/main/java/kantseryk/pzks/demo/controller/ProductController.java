package kantseryk.pzks.demo.controller;

import kantseryk.pzks.demo.model.Product;
import kantseryk.pzks.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kantseryk.pzks.demo.response.ApiResponse;
import kantseryk.pzks.demo.response.BaseMetaData;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")

/**
 * Author: Alona Kantseryk
 */

public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ApiResponse<List<Product>> getAllProducts() {

        return ApiResponse.<List<Product>>builder()
                .meta(BaseMetaData.builder().build())
                .data(productService.getAllProducts())
                .build();
    }}