//package capstone.market.config;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
///************
// * @info : Redis Repository Config 클래스
// * @name : RedisRepositoryConfig
// * @version : 1.0.0
// * @Description : Lettuce 사용(비동기 요청 처리), RedisRepository 방식.
// ************/
//@Configuration
//@RequiredArgsConstructor
//@EnableRedisRepositories
//@EnableCaching
//
//public class RedisRepositoryConfig {
//
//    private final RedisProperties redisProperties;
//
//    // lettuce
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//       // return new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
//        return new LettuceConnectionFactory("redis", 6379); // Docker Compose에서 Redis 컨테이너와 연결
//
//    }
//
//    // Redis template
//    @Bean
//    public RedisTemplate<?, ?> redisTemplate() {
//        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory());   //connection
//        redisTemplate.setKeySerializer(new StringRedisSerializer());    // key
//        redisTemplate.setValueSerializer(new StringRedisSerializer());  // value
//        return redisTemplate;
//    }
//}