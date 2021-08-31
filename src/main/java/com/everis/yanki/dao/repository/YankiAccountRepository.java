package com.everis.yanki.dao.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.everis.yanki.model.YankiAccount;

import reactor.core.publisher.Mono;

@Repository
public interface YankiAccountRepository extends ReactiveMongoRepository<YankiAccount, UUID>{
	Mono<YankiAccount> findByCellphoneNumber(String cellphoneNumber);
}
