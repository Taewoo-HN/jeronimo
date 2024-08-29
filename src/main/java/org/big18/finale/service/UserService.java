package org.big18.finale.service;

import org.big18.finale.entity.UserRole;
import org.big18.finale.entity.Users;
import org.big18.finale.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;


@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MemberRepository memberRepository;


    public void register(String user_id, String user_pw, String email,
                         String nickname, String phone_number) throws Exception {
        if (memberRepository.existsByEmail(email)) {
            throw new Exception("동일한 이메일로 회원이 존재합니다.");
        }
        if (memberRepository.existsByUserId(user_id)) {
            throw new Exception("동일한 입력으로 회원이 존재합니다.");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user_pw);

        Users member = new Users();
        member.setUserId(user_id);
        member.setUserPw(encodedPassword);
        member.setEmail(email);
        member.setUserName(nickname);
        member.setPhoneNumber(phone_number);
        member.setRole(UserRole.ROLE_USER); // 기본 역할 설정

        memberRepository.save(member);
    }
}
