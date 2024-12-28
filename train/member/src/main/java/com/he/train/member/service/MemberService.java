package com.he.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.he.train.member.domain.Member;
import com.he.train.member.domain.MemberExample;
import com.he.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(
                memberMapper.countByExample(null)
        );
    }

    public long register(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        // check existing member
        if (CollUtil.isNotEmpty(list)) {
            // return list.get(0).getId();
            throw new RuntimeException("Mobile already registered");
        }

        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);

        memberMapper.insert(member);
        return member.getId();
    }
}
