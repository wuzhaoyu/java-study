package proxy;

import proxy.cglib.HelloCglibProxy;
import proxy.cglib.SampleClass;
import proxy.jdk.HelloProxy;
import proxy.jdk.HelloService;
import proxy.jdk.HelloServiceImpl;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/17
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class ProxyPerformanceTest {

    public static void main(String[] args) {
        //创建测试对象
        HelloService nativeTest = new HelloServiceImpl();
        HelloService dynamicProxy = HelloProxy.newProxyInstance(new HelloServiceImpl());
        HelloService cglibProxy = HelloCglibProxy.newProxyInstance();

        //预热一下
        int preRunCount = 10000;
        runWithoutMonitor(nativeTest, preRunCount);
        runWithoutMonitor(cglibProxy, preRunCount);
        runWithoutMonitor(dynamicProxy, preRunCount);

        //执行测试
        Map<String, HelloService> tests = new LinkedHashMap<String, HelloService>();
        tests.put("Native   ", nativeTest);
        tests.put("Dynamic  ", dynamicProxy);
        tests.put("Cglib    ", cglibProxy);
        int repeatCount = 3;
        int runCount = 1000000;
        runTest(repeatCount, runCount, tests);
        runCount = 50000000;
        runTest(repeatCount, runCount, tests);
    }

    private static void runTest(int repeatCount, int runCount, Map<String, HelloService> tests) {
        System.out.println(
                String.format("\n===== run test : [repeatCount=%s] [runCount=%s] [java.version=%s] =====",
                        repeatCount, runCount, System.getProperty("java.version")));
        for (int i = 0; i < repeatCount; i++) {
            System.out.println(String.format("\n--------- test : [%s] ---------", (i + 1)));
            for (String key : tests.keySet()) {
                runWithMonitor(tests.get(key), runCount, key);
            }
        }
    }

    private static void runWithoutMonitor(HelloService target, int runCount) {
        for (int i = 0; i < runCount; i++) {
            target.test(i);
        }
    }

    private static void runWithMonitor(HelloService target, int runCount, String tag) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < runCount; i++) {
            target.test(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("[" + tag + "] Total Time:" + (end - start) + "ms");
    }
}
