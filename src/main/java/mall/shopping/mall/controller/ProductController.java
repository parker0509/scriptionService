package mall.shopping.mall.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import mall.shopping.mall.domain.Cart;
import mall.shopping.mall.domain.Product;
import mall.shopping.mall.service.cart.CartService;
import mall.shopping.mall.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Tag(name="Product API",description = "상품 관련 API")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;

    @GetMapping
    @Operation(summary = "홈페이지의 상품 목록 조회",description = "메인 홈에서 상품을 조회하는 기능")
    public String getHomeProductlist(Model model) {
        List<Product> product = productService.getAllProduct();
        model.addAttribute("products", product);
        return "home";

    }

    // 상품 목록 페이지
    @GetMapping("/list")
    @Operation(summary = "홈페이지의 모든 목록 조회",description = "product-list로 전달")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "product-list";
    }

    // 상품 상세 보기 페이지
    @GetMapping("/{id}")
    @Operation(summary = "상품 상세 정보를 조회")
    public String getProduct(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product-detail";
        } else {
            return "redirect:/products";
        }
    }

    // 상품 등록 페이지
    @GetMapping("/new")
    @Operation(summary = "상품 등록 페이지")
    public String showCreateProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    // 상품 등록 처리
    @PostMapping
    @Operation(summary = "새로운 상품을 등록")
    public String createProduct(@ModelAttribute Product product) {
        productService.createProduct(product);
        return "redirect:/products";
    }

    // 상품 수정 페이지
    @GetMapping("/edit/{id}")
    @Operation(summary = "상품 수정 페이지를 보여줌")
    public String showUpdateProductForm(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product-form";
        } else {
            return "redirect:/products";
        }
    }

    // 상품 수정 처리
    @PostMapping("/edit/{id}")
    @Operation(summary = "상품 정보를 수정")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute Product product) {

        productService.updateProduct(id, product);
        System.out.println("상품이 수정되었습니다.");
        return "redirect:/products";
    }

    // 상품 삭제
    @GetMapping("/delete/{id}")
    @Operation(summary = "상품을 삭제")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

}
