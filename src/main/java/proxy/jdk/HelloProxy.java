package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/16
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class HelloProxy implements InvocationHandler {

    private HelloServiceImpl object;

    public HelloProxy(HelloServiceImpl o){
        this.object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理成功");
        return  method.invoke(object,args);
    }

    public static HelloService newProxyInstance(HelloServiceImpl service){
        HelloService proxyInstance = (HelloService)Proxy.newProxyInstance(HelloService.class.getClassLoader(),new Class[]{HelloService.class},new HelloProxy(service));
        return proxyInstance;
    }
    public static void main(String[] args) {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        HelloService service = (HelloService)Proxy.newProxyInstance(HelloProxy.class.getClassLoader(),new Class[]{HelloService.class},new HelloProxy(new HelloServiceImpl()));
        System.out.println(HelloService.class.getName());
        service.test(1);
        service.getMsg1();

    }
}
