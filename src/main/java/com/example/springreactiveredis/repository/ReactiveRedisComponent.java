package com.example.springreactiveredis.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@SuppressWarnings("rawtypes")
@Slf4j
@Component
public class ReactiveRedisComponent {

    private final ReactiveRedisOperations<String, Object> redisOperations;

    public ReactiveRedisComponent(ReactiveRedisOperations<String, Object> redisOperations) {
        this.redisOperations = redisOperations;
    }

    /**
     * Set key and value into a hash key
     * @param key key value - must not be null.
     * @param hashKey hash key value -  must not be null.
     * @param val Object value
     * @return Mono of object
     */
    public Mono<Object> set(String key, String hashKey, Object val) {
        return redisOperations.opsForHash().put(key, hashKey, val).map(b -> val);
    }
    /**
     * @param key key value - must not be null.
     * @return Flux of Object
     */
    public Flux<Object> get(@NotNull String key){
       return redisOperations.opsForHash().values(key);
    }

    /**
     * Get value for given hashKey from hash at key.
     * @param key key value - must not be null.
     * @param hashKey hash key value -  must not be null.
     * @return Object
     */
    public Mono<Object> get(String key, Object hashKey) {
        return redisOperations.opsForHash().get(key, hashKey);
    }

    /**
     * Delete a key that contained in a hash key.
     * @param key key value - must not be null.
     * @param hashKey hash key value -  must not be null.
     * @return 1 Success or 0 Error
     */
    public Mono<Long> remove(String key, Object hashKey) {
        return redisOperations.opsForHash().remove(key, hashKey);
    }

}
