package com.boot.sharding.interceptor;

import com.boot.sharding.annotation.DatabaseStrategy;
import com.boot.sharding.config.DynamicDataSourceContextHolder;
import com.boot.sharding.strategy.DatabaseShardingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * 分库拦截器：最重要的注解@Order(-1)
 * Spring是通过切面去做事务管理的，所以需要将切换数据源的切面提前，要不然无法生效
 * 为什么不和表切换放在一起，主要是因为事务的问题，如果使用了事务的注解，此时在进入执行mybatis之前就已经获取了链接
 * @author lizhifu
 * @date 2021/1/5
 */
@Component
@Aspect
@Slf4j
@Order(-1)
public class DataSourceAdvice{
    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.boot.sharding.annotation.DatabaseStrategy)")
    public void pointCut() {}
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取当前切点方法对象
        Method method = methodSignature.getMethod();
        DatabaseStrategy databaseStrategy = this.getMethodAnnotation(joinPoint,DatabaseStrategy.class);
        if(databaseStrategy == null){
            return;
        }
        String logicSchema = databaseStrategy.logicSchema();
        String shardingKey = databaseStrategy.shardingKey();
        // 参数名
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        //方法参数的类型
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object shardingKeyValue = "";
        for (int i = 0; i < argNames.length; i++) {
            if(shardingKey.contains(argNames[i])){
                Class clas = parameterTypes[i];
                if (!clas.isPrimitive() && hasGet(clas) && !map.containsKey(clas.getName())) {
                    if(shardingKey.contains(".")){
                        shardingKey = shardingKey.split("\\.")[1];
                    }
                    shardingKeyValue = getGetMethod(obj[i],shardingKey);
                }else {
                    shardingKeyValue = obj[i];
                }
            }
        }
        Class<? extends DatabaseShardingStrategy> databaseShardingStrategy = databaseStrategy.databaseShardingStrategy();
        String schemaName = databaseShardingStrategy.newInstance().getSchemaName(logicSchema,shardingKeyValue);
        DynamicDataSourceContextHolder.setCurrentDatasource(schemaName);
        log.info("[库名称] schemaName:{}", schemaName);
        log.info("参数为：{}",obj);
    }
    /**
     * 后置操作，设置回默认的数据源id
     */
    @AfterReturning("pointCut()")
    public void doAfter() {
        DynamicDataSourceContextHolder.clearDataSource();
    }

    private <T extends Annotation> T getMethodAnnotation(JoinPoint joinPoint, Class<T> clazz) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(clazz);
    }
    /**
     * 根据属性，获取get方法
     * @param ob 对象
     * @param name 属性名
     * @return
     * @throws Exception
     */
    public static Object getGetMethod(Object ob,String name)throws Exception{
        Method[] methods = ob.getClass().getMethods();
        for(int i = 0;i < methods.length;i++){
            if(("get"+name).toLowerCase().equals(methods[i].getName().toLowerCase())){
                return methods[i].invoke(ob);
            }
        }
        return null;
    }
    public static boolean hasGet(Class clas){
        Method[] methods = clas.getMethods();
        for(int i = 0;i < methods.length;i++){
            if(methods[i].getName().contains("get")){
                return true;
            }
        }
        return false;
    }
    private static HashMap<String, Class> map = new HashMap<String, Class>() {
        {
            put("java.lang.Integer", int.class);
            put("java.lang.Double", double.class);
            put("java.lang.Float", float.class);
            put("java.lang.Long", long.class);
            put("java.lang.Short", short.class);
            put("java.lang.Boolean", boolean.class);
            put("java.lang.Char", char.class);
        }
    };
}
