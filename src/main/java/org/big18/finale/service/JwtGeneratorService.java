package org.big18.finale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

//@Service
//public class JwtGeneratorService {
//
//    private final JwtEncoder jwtEncoder;
//
//    @Autowired
//    public JwtGeneratorService(JwtEncoder jwtEncoder) {
//        this.jwtEncoder = jwtEncoder;
//    }
//
//    public String generateNaverOauthJwt(String clientId, String audience) {
//            Instant now = Instant.now();
//            JwtClaimsSet claims = JwtClaimsSet.builder()
//                    .issuer(clientId)
//                    .subject(clientId)
//                    .audience(Collections.singletonList(audience))
//                    .issuedAt(now)
//                    .expiresAt(now.plus(1, ChronoUnit.HOURS))
//                    .build();
//
//            return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
//        }
//}
