package com.han.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.han.commonutils.JwtUtils;
import com.han.commonutils.MD5;
import com.han.educenter.entity.vo.RegisterVo;
import com.han.educenter.service.UcenterMemberService;
import com.han.educenter.entity.UcenterMember;
import com.han.educenter.mapper.UcenterMemberMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.han.servicebase.exception.GuliException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-25
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public String login(UcenterMember member) {
        String mobile = member.getMobile();
        String password = member.getPassword();
        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            throw new GuliException(20001,"登录失败");
        }
        QueryWrapper<UcenterMember> wrapper=new QueryWrapper();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        if(mobileMember==null||!MD5.encrypt(password).equals(mobileMember.getPassword())||mobileMember.getIsDisabled())
            throw new GuliException(20001,"登录失败");

        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return jwtToken;
    }

    @Override
    public void register(RegisterVo register) {
        String code = register.getCode();
        String mobile = register.getMobile();
        String nickname = register.getNickname();
        String password = register.getPassword();
        if(StringUtils.isEmpty(code)||StringUtils.isEmpty(mobile)
                ||StringUtils.isEmpty(nickname)||StringUtils.isEmpty(password)){
            throw new GuliException(20001,"注册失败");
        }
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(redisCode)){
            throw new GuliException(20001,"注册失败");
        }
        QueryWrapper<UcenterMember> wrapper=new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if(count>0){
            throw new GuliException(20001,"注册失败");
        }
        UcenterMember member=new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("");
        baseMapper.insert(member);
    }
}
