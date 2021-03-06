package com.han.educenter.service;

import com.han.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.han.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-25
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    UcenterMember getByOpenid(String openid) ;


    String login(UcenterMember member);

    void register(RegisterVo register);
}
