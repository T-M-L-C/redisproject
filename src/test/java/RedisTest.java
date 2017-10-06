import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author liuchao
 * @since 04/10/2017
 */
public class RedisTest {

    /**
     * 连接到Redis服务器
     * redis 默认端口是6379
     */
    @Test
    public void connect(){
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        // 查看服务是否运行
        System.out.println("Server is running: " + jedis.ping());
    }

    /**
     * Redis和Java String(字符串)实例
     */
    @Test
    public void redisToJava(){
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        // 设置 redis 字符串数据
        jedis.set("target", "京东金融");
        // 获取存储的数据并输出
        System.out.println("Stored string in redis:: " + jedis.get("target"));
    }

    /**
     * Redis和Java List(列表)实例
     */
    @Test
    public void redisToJavaList(){
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        // 存储数据到列表中
        jedis.lpush("language-list", "Java");
        jedis.lpush("language-list", "Redis");
        jedis.lpush("language-list", "PHP");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("language-list", 0, 5);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Stored string in redis:: " + list.get(i));
        }
    }

    /**
     * Redis和Java的Keys(键)实例
     */
    @Test
    public void redisToKeyValue(){
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");

        // 获取数据并输出
        Set<String> sets = jedis.keys("*");
        Iterator<String> ite = sets.iterator();
        while (ite.hasNext()) {
            Object obj1 = ite.next();
            System.out.println("stored keys:: " + obj1);
        }
    }

    @Test
    public void redisToHash(){
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");

        jedis.hset("hash","name","liuchao");
        //hset 将哈希表 key 中的域 field 的值设为 value 。
        //hget 返回哈希表 key 中给定域 field 的值。
        //hdel 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
        Assert.assertTrue(jedis.hset("hash","key","value")==1);
        Assert.assertTrue(jedis.hget("hash","key").equals("value"));
        Assert.assertTrue(jedis.hdel("hash","key")==1);
        Assert.assertTrue(jedis.hget("hash","key")==null);
        System.out.println(jedis.hget("hash","name"));
    }


}
