package org.big18.finale.service;

import org.big18.finale.entity.Member;
import org.big18.finale.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserService {

    private final MemberRepository memberRepository;


    public UserService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    public void register(Model model) {
//        Member member =  new Member();
//        member.setUserId(model.getAttribute("user_id").toString());
//        member.setNickname(model.getAttribute("nickname").toString());
//        member.setUserPw(model.getAttribute("user_pw").toString());
//        member.setStockAccount(model.getAttribute("stock_account").toString());
//        member.setSecretKey(model.getAttribute("secret_key").toString());
//        member.setUPhonenum(model.getAttribute("u_phonenum").toString());
//        memberRepository.save(member);
//    }


}
