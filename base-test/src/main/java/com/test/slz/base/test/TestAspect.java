package com.test.slz.base.test;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class TestAspect {

    public static final List<String> INSTANCE_ID_FIELD_NAMES = Arrays.asList("processInstanceId", "procInstId", "instanceId");
    public static final List<String> PROC_DEF_IDS = Arrays.asList("processDefinitionId", "procDefId");

    @Pointcut("execution(* com.test.slz.base.test.controller.*.* (..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws NoSuchMethodException {
        getVersion(joinPoint);


        System.out.println();
    }

    private String getVersion(JoinPoint joinPoint) throws NoSuchMethodException {
        //拦截的实体类
        Object target = joinPoint.getTarget();
        //拦截的方法名称
        String methodName = joinPoint.getSignature().getName();
        //拦截的方法参数
        Object[] argsa = joinPoint.getArgs();
        //拦截的放参数类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class[] parameterTypes = signature.getMethod().getParameterTypes();
        Method method = target.getClass().getMethod(methodName, parameterTypes);
        String[] parameterNames = signature.getParameterNames();
        String version = " ";
        boolean flag=false;
        for (int i = 0; i < parameterNames.length; i++) {
            if (parameterNames[i].equals("clientId")) {
                version = getVersionByClientId(argsa[i].toString());
            } else if (INSTANCE_ID_FIELD_NAMES.contains(parameterNames[i])) {
                //
                flag=true;
            }
        }

        if (!flag) {
            for (int i = 0; i < parameterTypes.length; i++) {
                try {
                    Field clientId = parameterTypes[i].getDeclaredField("clientId");
                    clientId.setAccessible(true);
                    Object o = clientId.get(argsa[i]);
                    if (o != null && StringUtils.isNotBlank(o.toString())) {
                        version = getVersionByClientId(o.toString());
                        flag=true;
                        break;
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                }
            }
        }
        if(!flag){
            for (int i = 0; i < parameterTypes.length; i++) {
                for (String instanceIdFieldName : INSTANCE_ID_FIELD_NAMES) {
                    try {
                        Field clientId = parameterTypes[i].getDeclaredField(instanceIdFieldName);
                        clientId.setAccessible(true);
                        Object o = clientId.get(argsa[i]);
                        if (o != null && StringUtils.isNotBlank(o.toString())) {
                            version = getVersionByInstanceId(o.toString());
                            flag=true;
                            break;
                        }
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                    }
                }
            }
        }
        return version;
    }

    private String getVersionByInstanceId(String toString) {
        return null;
    }

    private String getVersionByClientId(String clientId) {
        return null;
    }
}
