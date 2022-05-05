package com.tiger.questionoftheday;

import com.tiger.questionoftheday.factoryBean.TestBean01;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author shihujiang
 * @date 2022/1/17
 */
@Component
public class BasicDataType implements SmartInitializingSingleton, InitializingBean, DisposableBean {

//    @Autowired
//    TestBean01 testBean01;

    public BasicDataType(@Value("${param3:default33333}") String param3) {
        System.out.println("BasicDataType的构造方法被调用param3:" + param3);
        this.param3 = param3;
    }

    private String param3;

    @Value("${param1:default-000}")
    private String param1;

    @Value("${param2:2222default}")
    private String param2;

//    public BasicDataType() {
//        System.out.println("BasicDataType的无参构造方法被调用");
//    }


    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("BasicDataType 单例实例化之后 .param2:" + this.param2);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet:BasicDataType 参数赋值完成.param2:" + param1);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(this.getClass() + " was destroy.");
    }

    @Override
    public String toString() {
        return "BasicDataType{" +
                "param1='" + param1 + '\'' +
                ", param2='" + param2 + '\'' +
                '}';
    }
}
