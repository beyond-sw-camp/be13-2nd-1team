package com.maptist.mappride.mappride.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberSerivce {

    private final MemberRepository memberRepository;

    public Optional<Member> findByEmail(String email){
        return memberRepository.findByEmail(email);
    }

}
