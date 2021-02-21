package com.hdc.servicebase.exceptionhandler;

import com.hdc.commonutils.R;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public R error(Exception e){
		e.printStackTrace();
		return R.error().message("全局异常处理");
	}
}