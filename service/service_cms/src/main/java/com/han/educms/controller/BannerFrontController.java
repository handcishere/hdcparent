package com.han.educms.controller;


import com.han.commonutils.R;
import com.han.educms.entity.CrmBanner;
import com.han.educms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {

    private CrmBannerService crmBannerService;

    public BannerFrontController(CrmBannerService crmBannerService) {
        this.crmBannerService = crmBannerService;
    }

    @ApiOperation("查询所有的Banner")
    @GetMapping("/getAllBanner")
    public R getAllBanner(){
        List<CrmBanner> bannerList = crmBannerService.selectAllBanner();
        return R.ok().data("list",bannerList);
    }
}

