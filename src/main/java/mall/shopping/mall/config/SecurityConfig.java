package mall.shopping.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http

                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))

                .formLogin(form -> form
                        .loginPage("/api/login")  // 로그인 페이지 경로
                        .permitAll()           // 로그인 페이지는 누구나 접근 가능
                        .defaultSuccessUrl("/", true)  // 로그인 성공 후 리디렉션
                        .failureUrl("/api/login?error=true")  // 로그인 실패 시 리디렉션  // 로그인 실패 시 리디렉션할 URL
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/")  // 로그아웃 후 메인 페이지로 리디렉션
                        .invalidateHttpSession(true)  // 세션 무효화
                        .clearAuthentication(true)   // 인증 정보 삭제
                        .deleteCookies("JSESSIONID") // 세션 쿠키 삭제
                )

                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/swagger-ui/**","/api/room/booking","/api/contents/content-form","api/contents/content-detail/**")
                        .authenticated()
                        .requestMatchers("/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().permitAll());


        return http.build();
    }


}