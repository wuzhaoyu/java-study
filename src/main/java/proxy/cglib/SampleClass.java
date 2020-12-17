package proxy.cglib;

import net.sf.cglib.proxy.*;

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
public class SampleClass implements MethodInterceptor {

    public void test(){
        System.out.println("hello world test");
    }

    public String test1(){
        System.out.println("hello world test1");
        return "test";
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("hello ");
        Object o1 = methodProxy.invokeSuper(o, objects);
        return o1;
    }
    public static SampleClass newProxyInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new SampleClass());
        SampleClass sampleClass = (SampleClass)enhancer.create();
        return sampleClass;
    }
    public static void main(String[] args) {
        // 使用filter 对指定的方法进行代理
        CallbackHelper callbackHelper = new CallbackHelper(SampleClass.class,new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class){
                    return new FixedValue() {
                        @Override
                        public Object loadObject() throws Exception {
                            return "Hello cglib";
                        }
                    };
                }else {
                    return NoOp.INSTANCE;
                }
            }
        };
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallbackFilter(callbackHelper);
        enhancer.setCallbacks(callbackHelper.getCallbacks());
//        enhancer.setCallback(new SampleClass());
        SampleClass sampleClass = (SampleClass)enhancer.create();
        sampleClass.test1();

    }
}
