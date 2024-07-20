package org.big18.finale.service;

import org.big18.finale.entity.Member;
import org.big18.finale.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public class UserService {

    private final MemberRepository memberRepository;

    public UserService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean login(String user_id, String user_pw){
        Member member = new Member();
        member = memberRepository.findById(user_id).orElse(null);
        if(member == null){
            return false;
        }else{
            return member.getUserPw().equals(user_pw);
        }
    }
    public void join(Member member , Model model) {
        if (memberRepository.findById(member.getUserId()).orElse(null) != null) {
            model.addAttribute("error", "이미 존재하는 아이디입니다.");
        } else{
            memberRepository.save(member);
            model.addAttribute("msg", "회원가입이 완료되었습니다.");
        }
    }
}
