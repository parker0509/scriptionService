package mall.shopping.mall.controller;


import jakarta.servlet.http.HttpSession;
import mall.shopping.mall.domain.Cart;
import mall.shopping.mall.domain.CartItem;
import mall.shopping.mall.domain.Product;
import mall.shopping.mall.domain.User;
import mall.shopping.mall.repository.CartRepository;
import mall.shopping.mall.repository.ProductRepository;
import mall.shopping.mall.service.cart.CartService;
import mall.shopping.mall.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;


    @GetMapping
    public String viewCart(
            @RequestParam(value = "cartId") Long cartId,
            Model model) {
        if (cartId == 0) {
            model.addAttribute("errorMessage", "카트 ID가 제공되지 않았습니다.");
            return "error"; // 기본값 0이면 오류 페이지로 이동
        }
        try {
            // 장바구니 총 금액 계산
            Cart cart = cartService.getCart(cartId);
            String formattedTotalPrice = cartService.getFormattedTotalPrice(cartId);

            // 숫자에 천 단위 쉼표 추가 (한국식 숫자 포맷)
            DecimalFormat formatter = new DecimalFormat("#,###");

            model.addAttribute("cart", cart);
            // 뷰로 포맷된 총 금액 전달
            model.addAttribute("formattedTotalPrice", formattedTotalPrice); // 총 금액을 모델에 추가
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", "Cart ID not found: " + cartId);
            return "error";
        }
        return "cart"; // 정상적인 뷰
    }

    @PostMapping("/add/{id}")
    @ResponseBody
    public String addCartAndProductToCartItem(@PathVariable(name="id") Long id, @RequestParam(name="quantity") int quantity,HttpSession session){

        User user = (User)session.getAttribute("user");

        if (user == null) {

            return "로그인이 필요합니다.";

        }


        // 상품을 DB에서 조회
        Product product = productService.getProductById(id).orElse(null);

        if (product == null) {

            return "상품을 찾을 수 없습니다.";
        }
        // CartService의 addProductToCart 메서드 호출
        cartService.addProductToCart(user, product, quantity);

        return "장바구니에 추가되었습니다.";

    }



}

