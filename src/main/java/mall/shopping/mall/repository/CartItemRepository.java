package mall.shopping.mall.repository;

import mall.shopping.mall.entity.Cart;
import mall.shopping.mall.entity.CartItem;
import mall.shopping.mall.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartAndProduct(Cart cart,Product product) ;


}
