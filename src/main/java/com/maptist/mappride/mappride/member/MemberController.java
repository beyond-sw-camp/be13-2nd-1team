package com.maptist.mappride.mappride.member;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok().body("ok");
    }
}
