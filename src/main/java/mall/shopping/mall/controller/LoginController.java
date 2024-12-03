package mall.shopping.mall.controller;

import jakarta.servlet.http.HttpSession;
import mall.shopping.mall.domain.User;
import mall.shopping.mall.repository.UserRepository;
import mall.shopping.mall.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
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

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }


    // Post 기능
    /*
    TODO : 실제 로그인 기능 구현
            : 로그인 성공 후 로직 구현
             : 로그인 실패 후 로직 구현
    */

    @PostMapping("/login")
    @ResponseBody
    public String postLoginPage(HttpSession httpSession, @RequestParam("email") String email,@RequestParam("password") String password){


        try {
            // 로그인 서비스 호출
            User user = loginService.loginUserService(email, password, httpSession);

            System.out.println("http Session: "+httpSession);
            // 로그인 성공 시 처리
            return "login-success";

        } catch (IllegalArgumentException e) {
            // 로그인 실패 시 처리
            return e.getMessage(); // 예외 메시지 반환 (아이디 또는 비밀번호 오류)
        }
    }

}
