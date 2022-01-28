package com.springboot.practice.core.cache.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductCacheConfig {
    @Bean
    public Config cacheConfig() {
        return new Config().setInstanceName("hasel-instance")
                .addMapConfig(new MapConfig().setName("student-cache").setTimeToLiveSeconds(3000));
    }
}
