package com.everis.yanki.service;

import java.util.UUID;

import com.everis.yanki.model.YankiAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface YankiAccountService {
	Mono<YankiAccount> save(YankiAccount y);
	Mono<YankiAccount> update(YankiAccount y);
	Flux<YankiAccount> findAll();
	Mono<YankiAccount> findById(UUID id);
	Mono<Void> deleteById(UUID id);
	
}
