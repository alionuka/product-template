package kantseryk.pzks.demo.repository;
import kantseryk.pzks.demo.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
/**
 * Author: Alona Kantseryk
 */

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}