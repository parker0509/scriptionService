package mall.shopping.mall.repository;

import mall.shopping.mall.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepsoitory extends JpaRepository<Order,Long> {
}
