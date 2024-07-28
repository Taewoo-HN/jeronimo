package org.big18.finale.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.spec.SecretKeySpec;

@Configuration
public class JwtConfig {

    @Value("${naver-client-secret}")
    private String naverSecret;

    @Bean
    public JwtEncoder jwtEncoder(){
        SecretKeySpec sckey = new SecretKeySpec(naverSecret.getBytes(), "HmacSHA");
        JWKSource<SecurityContext> jwkeys = new ImmutableSecret<>(sckey);
        return new NimbusJwtEncoder(jwkeys);    }
}
