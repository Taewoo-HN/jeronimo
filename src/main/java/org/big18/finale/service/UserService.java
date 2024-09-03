package org.big18.finale.service;

import org.big18.finale.entity.UserRole;
import org.big18.finale.entity.Users;
import org.big18.finale.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Autowired
    public UserService(BCryptPasswordEncoder passwordEncoder, MemberRepository memberRepository) {
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
    }


    public void register(String user_id, String user_pw, String email,
                         String nickname, String phone_number) throws Exception {
        if (memberRepository.existsByEmail(email)) {
            throw new Exception("동일한 이메일로 회원이 존재합니다.");
        } else if (memberRepository.existsByUserId(user_id)) {
            throw new Exception("이미 존재하는 아이디입니다.");
        } else if(memberRepository.existsByPhoneNumber(phone_number)){
            throw new Exception("이미 존재하는 전화번호입니다.");
        }

        String encodedPassword = passwordEncoder.encode(user_pw);

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
