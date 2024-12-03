package mall.shopping.mall.repository;

import mall.shopping.mall.domain.Cart;
import mall.shopping.mall.domain.CartItem;
import mall.shopping.mall.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartAndProduct(Cart cart,Product product) ;


}
