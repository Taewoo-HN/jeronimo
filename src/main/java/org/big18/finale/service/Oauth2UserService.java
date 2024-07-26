package org.big18.finale.service;

import org.big18.finale.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;

@Service
public class Oauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private MemberRepository memberRepository;

}
