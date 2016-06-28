package com.xbwl.demo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 打印业务日志，格式为:
 * 
 * 日期,实体类型,操作类型 ,操作用户,json格式的扩展字段
 * 
 */
@Component
public class ThrowingLogger {
	public static final String ERROR_LOGGER_NAME = "errorlog";

	private Logger errorLogger = LoggerFactory.getLogger(ERROR_LOGGER_NAME);

	public void log(String className, String method, String params, String errorType, String errormsg) {
		errorLogger.info("异常类:{}异常方法:{}参数:{}异常信息:{}", className, method, params, errorType, errormsg);
	}
}
