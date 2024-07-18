package org.big18.finale.service;

import org.big18.finale.entity.Member;
import org.big18.finale.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserService {

    @Autowired
    private final MemberRepository memberRepository;

    public UserService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

}
