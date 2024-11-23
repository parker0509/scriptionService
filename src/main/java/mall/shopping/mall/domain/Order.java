package mall.shopping.mall.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
/*@Table(name = "`order`")*/
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 하나의 주문은 하나의 고객
    @JoinColumn(name = "custom_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "order_product", // 중간 테이블 이름
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    private LocalDateTime localDateTime;

    private String status;

    public Order(Long id, Customer customer, List<Product> products, LocalDateTime localDateTime, String status) {
        this.id = id;
        this.customer = customer;
        this.products = products;
        this.localDateTime = localDateTime;
        this.status = status;
    }

}
