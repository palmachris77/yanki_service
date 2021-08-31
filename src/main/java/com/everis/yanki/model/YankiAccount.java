package com.everis.yanki.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class YankiAccount {
	@Id
	@Builder.Default
	private UUID id = UUID.randomUUID();
	
	private String documentType;
	private String documentNumber;
	private String cellphoneNumber;
	private String imei;
	private String email;
	private Double balance;
}
