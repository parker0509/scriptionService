package mall.shopping.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) // iframe을 사용할 경우 필요

                .formLogin(form -> form
                        .loginPage("/login") // 로그인 페이지 경로
                        .permitAll() // 로그인 페이지는 누구나 접근 가능
                        .defaultSuccessUrl("/", true) // 로그인 성공 후 리디렉션
                        .failureUrl("/login?error=true") // 로그인 실패 시 리디렉션
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/home", "/css/**", "/images/**", "/js/**", "/login/**", "/logout/**").permitAll() // 기본 공개 URL
                        .requestMatchers("/product", "/products", "/products/**", "/products/new", "/products/{id}").permitAll() // 상품 관련 공개 URL
                        .requestMatchers("/products/edit/{id}", "/products/delete/**").permitAll() // 상품 편집/삭제 공개
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll() // Swagger UI 공개
                        .requestMatchers("/homepage.jpg", "/homepage2.jpg", "/homepage3.jpg").permitAll() // 이미지 공개
                        .requestMatchers("/cart/**", "/cart/add/{productId}", "/cart/{productId}").permitAll() // 장바구니 관련 공개
                        .requestMatchers("/product/list/**", "/list", "/join/**", "/join").permitAll() // 제품 목록, 가입 관련 공개
                        .requestMatchers("/order", "/order/**").permitAll() // 주문 관련 공개
                        .requestMatchers("/products/cart").permitAll() // 장바구니 관련 공개
                        .requestMatchers("/payment/**").permitAll() // 결제 관련 공개
                        .requestMatchers("/static/**", "/css/**", "/js/**", "/images/**").permitAll() // 정적 리소스 공개
                        .anyRequest().authenticated() // 그 외의 요청은 인증된 사용자만 접근 가능
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/") // 로그아웃 후 메인 페이지로 리디렉션
                        .invalidateHttpSession(true) // 세션 무효화
                        .clearAuthentication(true)  // 인증 정보 삭제
                        .deleteCookies("JSESSIONID") // 세션 쿠키 삭제
                );

        return http.build();
    }
}
