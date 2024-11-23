package mall.shopping.mall.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mall.shopping.mall.domain.Product;
import mall.shopping.mall.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Tag(name = "Home", description = "쇼핑몰 홈 페이지 관련 API")
@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    // 홈 페이지 (쇼핑몰 소개 및 상품 목록)
    @Operation(summary = "홈 페이지", description = "쇼핑몰의 홈 페이지로, 최신 상품 목록을 보여줍니다.")
    @ApiResponse(responseCode = "200", description = "홈 페이지 로드 성공", content = @Content(mediaType = "text/html", examples = {@ExampleObject("홈 페이지가 정상적으로 로드되었습니다.")}))
    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productService.getAllProduct();  // 상품 목록 가져오기
        model.addAttribute("products", products);  // 상품 목록을 모델에 추가
        return "home";  // home.html 파일로 리턴 (이 파일은 홈 페이지를 렌더링합니다.)
    }

    // Swagger에서 API 설명을 위한 헬퍼 메소드
    @Operation(summary = "홈 페이지 확인", description = "홈 페이지가 정상적으로 동작하는지 확인하는 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "text/plain", examples = {@ExampleObject("홈 페이지가 정상적으로 동작합니다.")}))
    @GetMapping("/api/home")
    public String checkHomeApi() {
        return "OK";  // 홈 페이지가 정상적으로 동작하는지 확인하는 API
    }
}
