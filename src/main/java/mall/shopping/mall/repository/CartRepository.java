package mall.shopping.mall.repository;

import mall.shopping.mall.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    Cart findByUserId(Long id);

}
