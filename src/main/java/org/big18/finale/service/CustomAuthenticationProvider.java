package org.big18.finale.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    public CustomAuthenticationProvider(CustomUserService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        logger.debug("Comparing passwords for user: " + username);
        logger.debug("Input password: " + password);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        logger.debug("Stored password: " + userDetails.getPassword());

        if (userDetails == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        } else if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("잘못된 비밀번호입니다.");
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}


