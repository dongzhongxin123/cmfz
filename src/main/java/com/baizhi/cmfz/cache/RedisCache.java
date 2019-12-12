package com.baizhi.cmfz.cache;


import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;




public class RedisCache implements Cache {

    private String id;//这个id存的是传过来的方法的全限定名（mapper文件中的namespace）

    public RedisCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object o, Object o1) {

        //将查出来的数据放入到redis中   //stringRedisTemplate
        RedisTemplate redisTemplate =(RedisTemplate) MyApplication.getBeanByName("redisTemplate");
        System.out.println("数据放进去了");
        redisTemplate.opsForHash().put(id,o,o1);
    }

    @Override
    public Object getObject(Object o) {
        RedisTemplate redisTemplate =(RedisTemplate) MyApplication.getBeanByName("redisTemplate");
       return null;
    }

    @Override
    public Object removeObject(Object o) {
        System.out.println("MyCache.removeObject");
        return null;
    }

    @Override
    public void clear() {
        RedisTemplate redisTemplate =(RedisTemplate) MyApplication.getBeanByName("redisTemplate");
        redisTemplate.delete(id);
        System.out.println("MyCache.clear");
    }

    @Override
    public int getSize() {
        return 0;
    }
}

