package mall.shopping.mall.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import mall.shopping.mall.entity.User;
import mall.shopping.mall.repository.UserRepository;
import mall.shopping.mall.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/login")
public class LoginController {

    private final UserRepository userRepository;
    private final LoginService loginService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(UserRepository userRepository, LoginService loginService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.loginService = loginService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getLoginPage(){
        return "login";
    }


    // Post 기능
    /*
    TODO : 실제 로그인 기능 구현
            : 로그인 성공 후 로직 구현
             : 로그인 실패 후 로직 구현
    */

    @PostMapping("/auth")
    public String postLoginPage(HttpSession httpSession, @RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            // 로그인 서비스 호출
            User user = loginService.loginUserService(email, password, httpSession);

            // 로그인 성공 시 리다이렉션
            return "redirect:/";  // 로그인 후 홈 페이지로 리다이렉션
        } catch (IllegalArgumentException e) {
            // 로그인 실패 시 처리
            return "login";  // 로그인 페이지로 돌아감
        }
    }

}
