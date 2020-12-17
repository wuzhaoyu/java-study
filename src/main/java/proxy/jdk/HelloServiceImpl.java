package proxy.jdk;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/16
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class HelloServiceImpl implements HelloService {
    @Override
    public void test(int i) {
        System.out.println("hello" + i);
    }

    @Override
    public void getMsg1() {
        System.out.println("hello1");
    }
}
