package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 类功能说明:  使用lua 脚本实现
 * 类修改者	创建日期2020/12/5
 * 修改说明
 *   解决锁重入问题
 *   数据格式hash : key ： { hashKey : value ,.....}
 *   步骤：
 *    一 ： 获取锁
 *      1. 判断是否存在 lock_key
 *       - 1) 不存在 直接创建 lock_key 并且 在hashKey(可使用当前线程ID),value为一，加上自动释放锁时间
 *       - 2) 存在情况 判断hashKey是否当前线程的ID
 *          - 1） 不是 ，则获取锁失败
 *          - 2） 是，则在hashKey 的value上加一 ，并且重置自动释放时间
 *
 *     二： 释放锁
 *       1. 判断是否存在重入 ，也就是判断value值 减一后是否为 0
 *         - 1） 是 则删除 lock_key
 *         - 2) 否 则在重入的计数的value上减一
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class RedisLockLua {

    private static JedisPool jedisPool = new JedisPool("127.0.0.1",6379);

    public static Long tryLock(){
        Jedis jedis = jedisPool.getResource();
        String s = jedis.scriptLoad("lock.lua");
        System.out.println(s);
        return 0L;
    }

    public void unLock(){

    }

    public static void main(String[] args) {
        RedisLockLua.tryLock();
    }

}
