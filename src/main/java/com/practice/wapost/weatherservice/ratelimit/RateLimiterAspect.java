package com.practice.wapost.weatherservice.ratelimit;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.revinate.guava.util.concurrent.RateLimiter;

@Aspect
@Component
public class RateLimiterAspect {
	private static final Logger logger = LoggerFactory.getLogger(RateLimiterAspect.class);
	private static final String RATE_LIMIT_PRE_CONDITION_FAIL="precondition failed";
	private final ConcurrentHashMap<String,RateLimiter> limiters= new ConcurrentHashMap<>();

    @Before("@annotation(rateLimit)")
    public void rateLimit(JoinPoint jp, RateLimit rateLimit) throws Exception {
        RateLimiter limiter = limiters.computeIfAbsent(createKey(jp), createLimiter(rateLimit));
        boolean acquired = limiter.tryAcquire(1,TimeUnit.MILLISECONDS);
        if(acquired) {
        	logger.debug("acquired rate limit permission");
        }else {
        	logger.debug("not acquired rate limit permission");
        	throw new Exception(RATE_LIMIT_PRE_CONDITION_FAIL);
        }
    }
 
    private Function<String, RateLimiter> createLimiter(RateLimit limit) {
        return name -> RateLimiter.create(limit.value());
    }
 
    private String createKey(JoinPoint jp) {
    	Object[] args = jp.getArgs();
    	if(args.length <= 0) {
    		throw new IllegalArgumentException(RATE_LIMIT_PRE_CONDITION_FAIL);
    	}

		Object lastParam = args[args.length-1];
		if(lastParam instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) lastParam;
			String ip = request.getHeader("X-FORWARDED-FOR");
			if(ip == null) {
				ip = request.getRemoteAddr();
			}
				return ip;
			}else {
				throw new IllegalArgumentException(RATE_LIMIT_PRE_CONDITION_FAIL);
			}
		
    }
}