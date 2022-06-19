package org.cuit.xueyian.api.system;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.cuit.xueyian.model.Hr;
import org.cuit.xueyian.model.OpLog;
import org.cuit.xueyian.model.RespBean;
import org.cuit.xueyian.service.OplogService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import sun.net.httpserver.HttpServerImpl;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    OplogService oplogService;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     //     * 设置操作日志切入点   在注解的位置切入代码
     //     */
    @Pointcut("@annotation(org.cuit.xueyian.api.system.OperationLog)")
    public void LogPointCut(){
    }

    /**
     //     * 记录操作日志
     //     * @param joinPoint 方法的执行点
     //     * @param result  方法返回值
     //     * @throws Throwable
     //     */
    @AfterReturning(returning = "respBean", value = "LogPointCut()")
    public void saveOperationLog(JoinPoint joinPoint, RespBean respBean) throws Throwable{
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();



        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        try{
            //将返回值转换成map集合
//            Map<String,String> map = (Map<String, String>) result;
//            System.out.println(respBean.getMsg());
            OpLog opLog = new OpLog();
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();
            //获取操作
            OperationLog annotation = method.getAnnotation(OperationLog.class);
            if (annotation != null){
                opLog.setOperate(annotation.operDesc());
                opLog.setModel(annotation.operModel());
            }
            //操作时间
            opLog.setAdddate(Timestamp.valueOf(sdf.format(new Date())));

            //操作用户
            Hr hr = (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            opLog.setUsername(hr.getUsername());
            opLog.setHrid(hr.getId());

            opLog.setResult(respBean.getMsg());
            oplogService.addOplog(opLog);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}


