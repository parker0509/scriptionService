package mall.shopping.mall.service.login;


import jakarta.servlet.http.HttpSession;
import mall.shopping.mall.domain.User;
import mall.shopping.mall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession httpSession;

    @Autowired
    public LoginService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.httpSession = httpSession;
    }

    // !!로그인에 필요한 기능
    // 로그인 검증 (email 로 검증)

    /*
     * 로그인에 필요한 기능
     * 1. 로그인시 UserRepository와 검증
     * 2. Session 과 JWT 구분
     * 3. 추후 생각 날시 적기
     *
     * */

    public User loginUserService(String email, String password, HttpSession httpSession) {


        User user = userRepository.findByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {

            // 로그인 성공, 세션에 사용자 정보 저장
            httpSession.setAttribute("user", user);

            System.out.println("User 정보 : " + user);
            return user;  // 장바구니 페이지로 리다이렉트

        } else {

            // 로그인 실패

            throw new IllegalArgumentException("아이디 또는 비밀번호가 잘못되었습니다.");
        }

    }

}
