package com.he.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.he.train.common.exception.BusinessException;
import com.he.train.common.exception.BusinessExceptionEnum;
import com.he.train.common.util.SnowUtil;
import com.he.train.member.domain.Member;
import com.he.train.member.domain.MemberExample;
import com.he.train.member.mapper.MemberMapper;
import com.he.train.member.req.MemberRegisterReq;
import com.he.train.member.req.MemberSendCodeReq;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);
    
    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        // check existing member
        if (CollUtil.isNotEmpty(list)) {
            // return list.get(0).getId();
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(
                SnowUtil.getSnowflakeNextId()
        );
        member.setMobile(mobile);

        memberMapper.insert(member);
        return member.getId();
    }

    public void sendCode(MemberSendCodeReq req) {
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        if (CollUtil.isEmpty(list)) {
            LOG.info("member not exist, create new member");
            Member member = new Member();
            member.setId(
                    SnowUtil.getSnowflakeNextId()
            );
            member.setMobile(mobile);
            memberMapper.insert(member);
        } else {
            LOG.info("member exist");
        }

        // generate code
        // String code = RandomUtil.randomString(4);
        String code = "8888";
        LOG.info("generated code: {}", code);

        // save code into DB / redis
        // e.g., mobile, code, expiration, isUsed, tye, sentAt, usedAt

        // send it with twilio
    }
}
