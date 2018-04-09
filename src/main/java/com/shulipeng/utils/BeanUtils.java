package com.shulipeng.utils;



import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.misc.ReflectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Iterator;

/**
 * @author pengxianyang
 * @date 2018/4/4
 * @company QingDao Airlines
 * @description
 */
public  class BeanUtils {

    private static final Logger log = LoggerFactory.getLogger(BeanUtils.class);

    /**
     * 属性文件向Bean赋值
     * @param clazz
     * @param prop
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T propertyToBean(Class clazz, PropertiesConfiguration prop){
        if(prop == null){
            return null;
        }
        T bean = null;
        try {
            bean  = (T) ReflectUtil.newInstance(clazz);

            Iterator keyIt = prop.getKeys();
            while (keyIt.hasNext()){
                try {
                    String key =(String)keyIt.next();
                    String value = prop.getString(key);
                    Field field = clazz.getDeclaredField(key);
                    Class fieldClass = field.getType();
                    Method method = clazz.getDeclaredMethod("set" + StringUtils.capitalize(key),fieldClass);
                    String simpleName = fieldClass.getSimpleName();
                    if("boolean".equalsIgnoreCase(simpleName)){
                        method.invoke(bean,Boolean.parseBoolean(value));
                    }else if("String".equals(simpleName)){
                        method.invoke(bean,value);
                    }else if("String[]".equals(simpleName)){
                        String[] values = prop.getStringArray(key);
                        Method methodToExecute = clazz.getDeclaredMethod("set" + StringUtils.capitalize(key), new Class[]{String[].class});
                        methodToExecute.invoke(bean, new Object[]{values});
                    }else {
                        BigDecimal bd = new BigDecimal(value);
                        Method convertMethod = bd.getClass().getDeclaredMethod(simpleName+"Value");
                        method.invoke(bean, convertMethod.invoke(bd));
                    }

                } catch (NoSuchFieldException e) {
                    continue;
                } catch (Exception  ex) {
                    log.error(ex.getMessage(),ex);
                }
            }
        } catch (InstantiationException e) {
            log.error(e.getMessage(),e);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(),e);
        }
        return bean;
    }

    /**
     * Bean向属性文件赋值
     * @param bean
     * @param prop
     */
    public static void beanToProperty(Object bean, PropertiesConfiguration prop){
        if(prop == null){
            return;
        }
        Field[] fields = bean.getClass().getDeclaredFields();
        for(Field f : fields){
            f.setAccessible(true);
            try {
                String simpleName = f.getType().getSimpleName();
                if("String[]".equals(simpleName)){
                    String[] arr = (String[])f.get(bean);
                    prop.setProperty(f.getName(), arr != null ?  StringUtils.join((String[])f.get(bean),",") : "");
                }else if("Boolean".equals(simpleName)){
                    prop.setProperty(f.getName(),f.get(bean));
                }else{
                    prop.setProperty(f.getName(),(String)f.get(bean));
                }
            } catch (IllegalAccessException e) {
                log.error(e.getMessage(),e);
            }
        }
    }
}
