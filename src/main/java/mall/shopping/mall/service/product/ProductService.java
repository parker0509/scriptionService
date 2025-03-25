package mall.shopping.mall.service.product;

import mall.shopping.mall.entity.Product;
import mall.shopping.mall.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Create
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    //Read
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }


    //ReadID
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    //Update
    public Product updateProduct(Long id, Product updatedProduct) {

       Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setDescription(updatedProduct.getDescription());
        return productRepository.save(product);
    }

    //Delete
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

