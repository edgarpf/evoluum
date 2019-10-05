package com.evoluum.localidade.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheClear {
	
	@Autowired 
    private CacheManager cacheManager;  
	
	@Scheduled(fixedRate = 60000)
	public void cleanCache() {
		cacheManager.getCacheNames().forEach(nome -> cacheManager.getCache(nome).clear()); 
	}
}
