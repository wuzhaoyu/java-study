package proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import proxy.jdk.HelloService;
import proxy.jdk.HelloServiceImpl;

import java.lang.reflect.Method;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/17
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class HelloCglibProxy implements MethodInterceptor {

    public void test(){
        System.out.println("hello world test");
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("hello ");
        Object o1 = methodProxy.invokeSuper(o, objects);
        return o1;
    }
    public static HelloService newProxyInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloServiceImpl.class);
        enhancer.setCallback(new HelloCglibProxy());
        HelloService sampleClass = (HelloService)enhancer.create();
        return sampleClass;
    }
}
