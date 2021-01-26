package thread;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 类功能说明:
 * 类修改者	创建日期2021/1/18
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class ThreadLocalRandomTest {

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();
    public static void main(String[] args) {
        random.nextInt(100);

    }
}
