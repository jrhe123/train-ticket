package com.he.train.member.controller;

import com.he.train.common.resp.CommonResp;
import com.he.train.member.req.MemberRegisterReq;
import com.he.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Integer> count() {
        int count = memberService.count();
        return new CommonResp<>(count);
    }

    @PostMapping("/register")
    public CommonResp<Long> register(MemberRegisterReq req) {
        long register = memberService.register(req);
        return new CommonResp<>(register);
    }
}
