package mall.shopping.mall.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    // 상품명
    private String name;

    // 상풍 설명
    private String description;

    // 상품 가격
    private BigDecimal bigDecimal;

    // 재고 수량
    private int stockQuantity;

    @ManyToMany(mappedBy = "products")  // 매핑된 필드 이름을 사용하여 관계 반영
    private List<Order> orders;  // 여러 주문에 포함될 수 있음

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
