package com.he.train.member.controller;

import com.he.train.common.resp.CommonResp;
import com.he.train.common.resp.MemberLoginResp;
import com.he.train.member.req.MemberLoginReq;
import com.he.train.member.req.MemberRegisterReq;
import com.he.train.member.req.MemberSendCodeReq;
import com.he.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
    public CommonResp<Long> register(
            @Valid MemberRegisterReq req
    ) {
        long register = memberService.register(req);
        return new CommonResp<>(register);
    }

    @PostMapping("/send-code")
    public CommonResp<Long> sendCode(
            @Valid @RequestBody MemberSendCodeReq req
    ) {
        memberService.sendCode(req);
        return new CommonResp<>();
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(
            @Valid @RequestBody MemberLoginReq req
    ) {
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<>(resp);
    }
}
