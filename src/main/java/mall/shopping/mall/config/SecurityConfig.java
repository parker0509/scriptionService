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
                .csrf(csrf -> csrf.disable()) // CSRF 보호 완전 비활성화
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/home", "/css/**", "/images/**", "/js/**", "/login/**", "/logout/**").permitAll()
                        .requestMatchers("/product", "/products", "/products/**", "/products/new", "/products/{id}").permitAll()
                        .requestMatchers("/products/edit/{id}", "/products/delete/**").permitAll()
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/homepage.jpg", "/homepage2.jpg", "/homepage3.jpg").permitAll()
                        .requestMatchers("/cart/**","cart/add/{productId}","/cart/{productId}").permitAll()
                        .requestMatchers("/product/list/**", "/list", "/join/**", "/join").permitAll()
                        .requestMatchers("/order", "/order/**").permitAll()
                        .requestMatchers("/products/cart").permitAll()
                                .anyRequest()
                                .authenticated()
                        // 인증된 사용자만 접근 허용
                )
                .logout(logout -> logout
                        .permitAll()  // 로그아웃은 누구나 접근 가능
                        .logoutSuccessUrl("/home")  // 로그아웃 후 리다이렉트될 페이지
                );
        return http.build();
    }
}

