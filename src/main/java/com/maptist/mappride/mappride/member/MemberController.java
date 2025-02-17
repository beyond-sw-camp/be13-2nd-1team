package com.maptist.mappride.mappride.member;

import com.maptist.mappride.mappride.config.jwt.DTO.SecurityUserDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MemberController {

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok().body("ok");
    }
}
