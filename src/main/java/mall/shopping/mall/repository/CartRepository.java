package mall.shopping.mall.repository;

import mall.shopping.mall.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    Cart findByUserId(Long id);

}
