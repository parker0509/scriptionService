package mall.shopping.mall.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart; // 장바구니에 속하는 항목

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // 상품 정보

    private int quantity = 1; // 상품 수량, 기본값 1

    // 카트 항목의 총 금액 계산
    public int getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
