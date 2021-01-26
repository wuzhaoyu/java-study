package spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 类功能说明:
 *  SPI ，全称为 Service Provider Interface，是一种服务发现机制。它通过在ClassPath路径下的META-INF/services文件夹查找文件，自动加载文件里所定义的类
 * 类修改者	创建日期2021/1/26
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class SPITest {

    public static void main(String[] args) {

        ServiceLoader<SPIService> serviceLoader =  ServiceLoader.load(SPIService.class);
        Iterator<SPIService> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            SPIService next = iterator.next();
            next.execute();
        }
    }
}
