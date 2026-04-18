package kantseryk.pzks.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 * Author: Alona Kantseryk
 */

public class Product {

    @Id
    private String id;

    private String name;
    private String article;
    private String description;

    public Product(String name, String article, String description) {
        this.name = name;
        this.article = article;
        this.description = description;
    }
}
