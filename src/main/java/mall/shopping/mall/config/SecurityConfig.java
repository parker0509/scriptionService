package mall.shopping.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf
                            .ignoringRequestMatchers("/products/edit/**", "/products/delete/**"))  // 수정, 삭제 경로에 대해서만 CSRF 보호를 비활성화
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers("/", "/home", "/css/**", "/images/**", "/js/**", "/login/**", "/logout/**").permitAll()
                            .requestMatchers("/product").permitAll()
                            .requestMatchers("/products", "/products/**", "/products/new", "/products/{id}").permitAll()
                            .requestMatchers("/products/edit/{id}", "/products/delete/**").permitAll()  // 관리자는 'ADMIN' 권한
                            .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                            .requestMatchers("/homepage.jpg","/homepage2.jpg","/homepage3.jpg").permitAll()
                            .anyRequest().authenticated()
                    );
            return http.build();
        }
    }

