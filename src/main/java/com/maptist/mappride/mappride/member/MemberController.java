package com.maptist.mappride.mappride.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @GetMapping("/loginSuccess")
    public ResponseEntity<String> login(){
        /*
        로그인 메서드
         */
        return ResponseEntity.ok().body("login success");
    }
}
