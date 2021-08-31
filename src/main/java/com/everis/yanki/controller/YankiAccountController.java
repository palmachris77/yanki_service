package com.everis.yanki.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.yanki.model.YankiAccount;
import com.everis.yanki.service.YankiAccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/yanki-account")
public class YankiAccountController {
	@Autowired
	private YankiAccountService yankiService;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<YankiAccount>>> findAll(){
		Flux<YankiAccount> fluxAccounts = yankiService.findAll();
		return Mono.just(ResponseEntity
						.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(fluxAccounts));
	}
	@GetMapping("/{id}")
	public Mono<ResponseEntity<YankiAccount>> findById(@PathVariable("id") UUID id){
		return yankiService.findById(id)
				.map(e -> ResponseEntity
						.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(e));
				
	}
	
	@PostMapping
	public Mono<ResponseEntity<YankiAccount>> create(@RequestBody YankiAccount entity, final ServerHttpRequest req){

		return yankiService.save(entity)
				.map( y -> ResponseEntity
							.created(URI.create(req.getURI().toString().concat("/").concat(y.getId().toString())))
							.contentType(MediaType.APPLICATION_JSON)
							.body(y));
		
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteById(@PathVariable("id") UUID id){
		
		return yankiService.findById(id)
				.flatMap(y ->  yankiService.deleteById(y.getId())
							.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
				)
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
		
	}
}
