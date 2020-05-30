package thread.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/3/31
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
public class UnSafeInstance {

    /**
     * 获取unsafe实例
     * @return
     */
    public static Unsafe reflectGetUnSafe() {
        try {
            Field theUnsafe =  theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe unsafe = (Unsafe) theUnsafe.get(null);
            return unsafe;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
