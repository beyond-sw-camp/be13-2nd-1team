package com.maptist.mappride.mappride.member;

import com.maptist.mappride.mappride.member.DTO.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Long> register(RegisterDto registerDto) {
        Member member = Member.createMember(registerDto);

        return ResponseEntity.ok().body(memberRepository.save(member));
    }
}
