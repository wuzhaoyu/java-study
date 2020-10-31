package thread.juc;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/10/11
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class AtomicTest {

    public static void main(String[] args) {

        //
        AtomicInteger atomicInteger = new AtomicInteger(0);

        // ++i i++
        atomicInteger.incrementAndGet();
        atomicInteger.getAndIncrement();

        atomicInteger.updateAndGet(value -> 10*value);
        System.out.println(atomicInteger.get());

        // 原子引用
        AtomicReference<BigDecimal> reference = new AtomicReference<BigDecimal>(new BigDecimal(10));


    }
}
