package com.han.educenter.controller;

import com.han.commonutils.JwtUtils;
import com.han.commonutils.R;
import com.han.educenter.entity.UcenterMember;
import com.han.educenter.entity.vo.RegisterVo;
import com.han.educenter.service.UcenterMemberService;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-25
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;
    @PostMapping
    public R loginUser(@RequestBody UcenterMember member){
        String token=memberService.login(member);
        return R.ok().data("token",token);
    }
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo register){
        memberService.register(register);
        return R.ok();
    }
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }

}

