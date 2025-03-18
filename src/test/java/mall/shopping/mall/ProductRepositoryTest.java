package mall.shopping.mall;


import mall.shopping.mall.entity.Product;
import mall.shopping.mall.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;


    @Test
    @Transactional
    private void testCreateProduct(){


        //given
        Product product = new Product("Test Product", "Description of Test Product", new BigDecimal("20000"), 100);
        //When
        productRepository.save(product);
        // Then
        assertThat(product.getId()).isNotNull();  // 아이디가 생성되었는지 확인
    }

    @Test
    @Transactional
    public void testUpdateProduct() {
        // Given
        Product product = new Product("Test Product", "Description of Test Product", new BigDecimal("19.99"), 100);
        productRepository.save(product);

        // When
        product.setName("Updated Product");
        productRepository.save(product);

        // Then
        Product updatedProduct = productRepository.findById(product.getId()).orElse(null);
        assertThat(updatedProduct).isNotNull();
        assertThat(updatedProduct.getName()).isEqualTo("Updated Product");
    }

    @Test
    @Transactional
    public void testDeleteProduct() {
        // Given
        Product product = new Product("Test Product", "Description of Test Product", new BigDecimal("19.99"), 100);
        productRepository.save(product);

        // When
        productRepository.delete(product);

        // Then
        Product deletedProduct = productRepository.findById(product.getId()).orElse(null);
        assertThat(deletedProduct).isNull();
    }
}
