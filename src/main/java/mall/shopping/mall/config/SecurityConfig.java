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
                .csrf(csrf -> csrf.disable())  // CSRF 보호 비활성화 (개발 환경에서만 사용)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/home", "/css/**", "/images/**", "/js/**", "/login/**", "/logout/**").permitAll()
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()// 특정 경로는 허용
                        .anyRequest().authenticated()  // 나머지 경로는 인증 필요
                );
        return http.build();
    }
}
