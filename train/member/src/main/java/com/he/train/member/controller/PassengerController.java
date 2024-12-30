package com.he.train.member.controller;

import com.he.train.common.resp.CommonResp;
import com.he.train.member.req.PassengerSaveReq;
import com.he.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Object> save(
            @Valid @RequestBody PassengerSaveReq req
    ) {
        passengerService.save(req);
        return new CommonResp<>();
    }
}
