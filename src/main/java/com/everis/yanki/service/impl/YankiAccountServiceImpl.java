package com.everis.yanki.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.yanki.dao.repository.YankiAccountRepository;
import com.everis.yanki.model.YankiAccount;
import com.everis.yanki.service.YankiAccountService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class YankiAccountServiceImpl implements YankiAccountService{
	@Autowired
	private YankiAccountRepository yankiRepository;
	
	@Override
	public Mono<YankiAccount> save(YankiAccount y) {
		y.setId(UUID.randomUUID());
		return yankiRepository.save(y);
	}

	@Override
	public Mono<YankiAccount> update(YankiAccount y) {
		return yankiRepository.save(y);
	}

	@Override
	public Flux<YankiAccount> findAll() {
		return yankiRepository.findAll();
	}

	@Override
	public Mono<YankiAccount> findById(UUID id) {
		return yankiRepository.findById(id);
	}

	@Override
	public Mono<Void> deleteById(UUID id) {
		return yankiRepository.deleteById(id);
	}

}
