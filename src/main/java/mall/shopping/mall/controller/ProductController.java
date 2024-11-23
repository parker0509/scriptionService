package mall.shopping.mall.controller;

import mall.shopping.mall.domain.Product;
import mall.shopping.mall.repository.ProductRepository;
import mall.shopping.mall.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    // 상품 목록 페이지
    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "product-list";
    }

    // 상품 상세보기 페이지
    @GetMapping("/{id}")
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
    public String showCreateProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    // 상품 등록 처리
    @PostMapping
    public String createProduct(@ModelAttribute Product product) {
        productService.createProduct(product);
        return "redirect:/products";
    }

    // 상품 수정 페이지
    @GetMapping("/edit/{id}")
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
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute Product product) {

        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    // 상품 삭제
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
