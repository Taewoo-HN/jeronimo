package org.big18.finale.service;

import org.big18.finale.entity.UserRole;
import org.big18.finale.entity.Users;
import org.big18.finale.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    private UserRole userRole;

    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        Users member = memberRepository.findById(user_id).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return org.springframework.security.core.userdetails.User
                .withUsername(member.getUserId())
                .password(member.getUserPw())
                .build();

    }

    public void register(String user_id, String user_pw, String email
                        , String nickname, String phone_number) throws Exception{
        Users member = new Users();
        if(memberRepository.findById(user_id).isPresent()){
            throw new Exception("이미 존재하는 아이디입니다.");
        }
        member.setUserId(user_id);
        member.setUserPw(user_pw);
        member.setEmail(email);
        member.setUserName(nickname);
        member.setPhoneNumber(phone_number);
        memberRepository.save(member);
    }

}
