package mall.shopping.mall.service.product;

import mall.shopping.mall.domain.Product;
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
    public void updateProduct(Long id, Product productDetails){

        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());
            product.setStockQuantity(productDetails.getStockQuantity());
            product.setImageUrl(productDetails.getImageUrl());
            productRepository.save(product);
        }

    }
    //Delete
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

