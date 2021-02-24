package com.han.eduservice.client;

import com.han.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public R removeAlyVideo(String videoId) {
        return R.error().message("time out");
    }

    @Override
    public R deleteBatch(List videoIdList) {
        return R.error().message("time out");
    }
}