//package org.big18.finale.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//
//@RequiredArgsConstructor
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecuirtyConfig {
//    private final CustomOAuth2UserService customOAuth2UserService;
//    private final OAuth2SuccessHandler oAuth2SuccessHandler;
//    private final TokenAuthenticationFilter tokenAuthenticationFilter;
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() { // security를 적용하지 않을 리소스
//        return web -> web.ignoring()
//                // error endpoint를 열어줘야 함, favicon.ico 추가!
//                .requestMatchers("/error", "/favicon.ico");
//    }
//}
