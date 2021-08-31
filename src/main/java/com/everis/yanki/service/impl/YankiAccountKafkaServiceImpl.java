package com.everis.yanki.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.everis.yanki.dao.repository.YankiAccountRepository;
import com.everis.yanki.dto.MovementRequest;
import com.everis.yanki.service.YankiAccountKafkaService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class YankiAccountKafkaServiceImpl implements YankiAccountKafkaService{
	private static final String TOPIC_WITHDRAW = "withdraw-yanki-topic";
	private static final String TOPIC_DEPOSIT = "deposit-yanki-topic";
	
	@Autowired
	private YankiAccountRepository yankiRepository;

	@Override
	@KafkaListener(topics = TOPIC_WITHDRAW, groupId = "everis")
	public Mono<Void> withdrawAccount(MovementRequest movement) {
		log.info(String.format("#### -> Consumed message -> %s", movement));
						
		yankiRepository.findByCellphoneNumber(movement.getCellphoneNumber())
				.doOnNext( m -> {
					m.setBalance( m.getBalance() - movement.getAmount());
					log.info(String.format("#### -> Transaction -> %s", m));
					yankiRepository.save(m).subscribe();
				})
				.subscribe();
		return Mono.empty();
	}

	@Override
	@KafkaListener(topics = TOPIC_DEPOSIT, groupId = "everis")
	public Mono<Void> depositAccount(MovementRequest movement) {
		log.info(String.format("#### -> Consumed message -> %s", movement));
		
		yankiRepository.findByCellphoneNumber(movement.getCellphoneNumber())
			.doOnNext( m -> {
				m.setBalance( m.getBalance() + movement.getAmount());
				log.info(String.format("#### -> Transaction Deposit -> %s", m));
				yankiRepository.save(m).subscribe();
			})
			.subscribe();
return Mono.empty();
	}
}
