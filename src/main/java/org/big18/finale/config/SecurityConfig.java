package org.big18.finale.config;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.big18.finale.service.CustomAuthenticationProvider;
import org.big18.finale.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/", "/main", "/login", "/chatting", "/logging", "/register", "/css/**", "/img/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                ).formLogin(formlogin -> formlogin
                        .loginPage("/login")
                        .loginProcessingUrl("/logging")
                        .successHandler((request, response, authentication) -> {
                            // 세션에 사용자 정보 저장
                            HttpSession session = request.getSession();
                            session.setAttribute("username", authentication.getName());
                            session.setAttribute("authorities", authentication.getAuthorities());
                            // 메인 페이지로 리디렉션
                            response.sendRedirect("/main");
                        })
                        .failureHandler((request, response, exception) -> {
                            response.setContentType("application/json");
                            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                            response.getWriter().write("{\"success\": false, \"message\": \"로그인 실패: " + exception.getMessage() + "\"}");
                        })
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()
                ).logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .deleteCookies("JSESSIONID")
                ).sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 세션이 필요할 때만 생성
                        .maximumSessions(1) // 동시 로그인 세션 수 제한
                        .maxSessionsPreventsLogin(true) // 새로운 로그인을 막음
                )
                .rememberMe(rememberMe -> rememberMe
                        .tokenRepository(new InMemoryTokenRepositoryImpl())
                        .tokenValiditySeconds(1800) // remember-me 쿠키 유효 기간 (하루)
                        .key("uniqueAndSecret")
                );
        // CustomAuthenticationProvider를 SecurityFilterChain에 설정합니다.
        http.authenticationProvider(customAuthenticationProvider());

        return http.build();
    }

    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(customUserService, passwordEncoder); // 빈 주입
    }

}