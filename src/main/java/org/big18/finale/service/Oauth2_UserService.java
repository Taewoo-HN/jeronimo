package org.big18.finale.service;

import org.big18.finale.entity.AuthProvider;
import org.big18.finale.entity.NaverUser;
import org.big18.finale.repository.NaverUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Oauth2_UserService extends DefaultOAuth2UserService {

    @Autowired
    private NaverUserRepository naverRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        if ("naver".equals(registrationId)) {
            Map<String, Object> attributes = (Map<String, Object>) oauth2User.getAttributes().get("response");
            String email = (String) attributes.get("email");
            String name = (String) attributes.get("name");
            String phoneNumber = (String) attributes.get("phoneNumber");
            String id = (String) attributes.get("id");

            NaverUser user = naverRepository.findByEmail(email)
                    .orElseGet(() -> {
                        NaverUser newUser = new NaverUser();
                        newUser.setEmail(email);
                        newUser.setPhoneNumber(phoneNumber);
                        newUser.setName(name);
                        newUser.setProviderId(id);
                        newUser.setProvider(AuthProvider.NAVER);
                        return naverRepository.save(newUser);
                    });


            return oauth2User;
        }
        throw new OAuth2AuthenticationException("Unsupported provider: " + registrationId);
    }
}


