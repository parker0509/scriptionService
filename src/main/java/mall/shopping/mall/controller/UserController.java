package mall.shopping.mall.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mall.shopping.mall.entity.User;
import mall.shopping.mall.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User API", description = "유저 관련 API")
@RequestMapping("/join")
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    // 회원가입 폼 이동
    @GetMapping
    public String joinForm(Model model){

        model.addAttribute("user",new User());

        return "join-form";
    }


    //회원가입
    @PostMapping
    @Operation(summary = "회원가입")
    public String joinUser(@ModelAttribute User user){

        userService.createUser(user);

        return "redirect:/login";
    }



}
