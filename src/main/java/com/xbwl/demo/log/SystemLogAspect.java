package com.xbwl.demo.log;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

@Aspect
@Component
public class SystemLogAspect {

    // 本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
    
    private BusinessLogger businessLogger;
    
    private ThrowingLogger throwingLogger;

    // Service层切点
    @Pointcut("@annotation(com.xbwl.demo.log.SystemServiceLog)")
    public void serviceAspect() {
    }

    // Controller层切点
    @Pointcut("@annotation(com.xbwl.demo.log.SystemControllerLog)")
    public void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     * 
     * @param joinPoint
     *            切点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession();
        // 读取session中的用户
        // User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
        // 请求的IP
        String ip = request.getRemoteAddr();
        try {
            // *========控制台输出=========*//
            System.out.println("=====前置通知开始=====");
            System.out.println("请求方法:"
                    + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));
            // System.out.println("请求人:" + user.getName());
            System.out.println("请求IP:" + ip);
            // *========数据库日志=========*//
            
            System.out.println("=====前置通知结束=====");
            
//            Object[] obj=joinPoint.getArgs();
//            String[] parameters = getMethodParameters(joinPoint.getTarget().getClass().getName() ,joinPoint.getSignature().getName());
            
         // 业务日志演示
//            Map<String,Object> logData = Maps.newHashMap();
//            for(int i=0; i<obj.length; i++){
//                logData.put(parameters[i], obj[i]);
//            }
//            businessLogger.log(joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), "", logData);
            
        } catch (Exception e) {
            // 记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息:{}", e.getMessage());
        }
    }

    @Around("serviceAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String ip = request.getRemoteAddr();
        Object returnObj=null;
        // 获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                // params += JSONUtil.toJsonString(joinPoint.getArgs()[i]) + ";";
                params += joinPoint.getArgs()[i] + ";";
            }
        }
        try {
            /* ========控制台输出========= */
            logger.info("=====Service开始=====");
            logger.info("方法:"
                    + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            logger.info("请求IP:" + ip);
            logger.info("请求参数:" + params);
            long startTime = System.currentTimeMillis();
            returnObj = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            logger.info("消耗时间：" + (endTime - startTime) / 1000 + " 秒");
            logger.info("=====Service结束=====");
        } catch (Exception ex) {
            // 记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
        return returnObj;
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     * 
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowingController(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
//        HttpSession session = request.getSession();
        // 读取session中的用户
        // User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
        // 获取请求ip
        String ip = request.getRemoteAddr();
        // 获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                // params += JSONUtil.toJsonString(joinPoint.getArgs()[i]) + ";";
                params += joinPoint.getArgs()[i] + ";";
            }
        }
//        try {
            /* ========控制台输出========= */
//            System.out.println("=====异常通知开始=====");
//            System.out.println("异常代码:" + e.getClass().getName());
//            System.out.println("异常信息:" + e.getMessage());
//            System.out.println("异常方法:"
//                    + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//            System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));
//            // System.out.println("请求人:" + user.getName());
//            System.out.println("请求IP:" + ip);
//            System.out.println("请求参数:" + params);
            /* ==========数据库日志========= */
            // Log log = SpringContextHolder.getBean("logxx");
            // log.setDescription(getServiceMthodDescription(joinPoint));
            // log.setExceptionCode(e.getClass().getName());
            // log.setType("1");
            // log.setExceptionDetail(e.getMessage());
            // log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() +
            // "()"));
            // log.setParams(params);
            // log.setCreateBy(user);
            // log.setCreateDate(DateUtil.getCurrentDate());
            // log.setRequestIp(ip);
            // //保存数据库
            // logService.add(log);
//            System.out.println("=====异常通知结束=====");
//        } catch (Exception ex) {
//            // 记录本地异常日志
//            logger.error("==异常通知异常==");
//            logger.error("异常信息:{}", ex.getMessage());
//        }
        /* ==========记录本地异常日志========== */
//        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName()
//                + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);
        throwingLogger.log(joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), params,e.getClass().getName(), e.getMessage());

    }

    
    /**
     * 异常通知 用于拦截service层记录异常日志
     * 
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//                .getRequest();
//        HttpSession session = request.getSession();
        // 读取session中的用户
        // User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
        // 获取请求ip
//        String ip = request.getRemoteAddr();
        // 获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                // params += JSONUtil.toJsonString(joinPoint.getArgs()[i]) + ";";
                params += joinPoint.getArgs()[i] + ";";
            }
        }
//        try {
            /* ========控制台输出========= */
//            System.out.println("=====异常通知开始=====");
//            System.out.println("异常代码:" + e.getClass().getName());
//            System.out.println("异常信息:" + e.getMessage());
//            System.out.println("异常方法:"
//                    + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//            System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));
//            // System.out.println("请求人:" + user.getName());
//            System.out.println("请求IP:" + ip);
//            System.out.println("请求参数:" + params);
            /* ==========数据库日志========= */
            // Log log = SpringContextHolder.getBean("logxx");
            // log.setDescription(getServiceMthodDescription(joinPoint));
            // log.setExceptionCode(e.getClass().getName());
            // log.setType("1");
            // log.setExceptionDetail(e.getMessage());
            // log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() +
            // "()"));
            // log.setParams(params);
            // log.setCreateBy(user);
            // log.setCreateDate(DateUtil.getCurrentDate());
            // log.setRequestIp(ip);
            // //保存数据库
            // logService.add(log);
//            System.out.println("=====异常通知结束=====");
//        } catch (Exception ex) {
//            // 记录本地异常日志
//            logger.error("==异常通知异常==");
//            logger.error("异常信息:{}", ex.getMessage());
//        }
        /* ==========记录本地异常日志========== */
//        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName()
//                + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);

    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     * 
     * @param joinPoint
     *            切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getServiceMthodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * 
     * @param joinPoint
     *            切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }
    
    @SuppressWarnings("rawtypes")
    public static String[] getMethodParameters(String targetName, String targetMethodName) {
        String[] parameters = null;
        try {
            Class clazz = Class.forName(targetName);
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if(targetMethodName.equals(methodName)){
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    parameters = new String[parameterTypes.length];
                    for(int i=0; i< parameterTypes.length; i++){
                        String parameterName = parameterTypes[i].getName();
                        parameters[i] = parameterName;
                    }
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
           logger.error("反射java方法参数名称出错了");
        }
        return parameters;
    }
    
    public static void main(String[] args) {
//        String[] strArr = SystemLogAspect.getMethodParameters("java.util.ArrayList", "get");
//        for(String str:strArr){
//            System.out.println(str);
//        }
        Map<String, Object> logMap = Maps.newHashMap(new ImmutableMap.Builder<String, Object>()
                .put("pageNumber", 1).put("pageSize", 1).put("sortType", 1)
                .build());
    }
    
    
}
