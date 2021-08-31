package com.everis.yanki.service;

import com.everis.yanki.dto.MovementRequest;

import reactor.core.publisher.Mono;

public interface YankiAccountKafkaService {
	Mono<Void> withdrawAccount(MovementRequest movement);
	Mono<Void> depositAccount(MovementRequest movement);
}
