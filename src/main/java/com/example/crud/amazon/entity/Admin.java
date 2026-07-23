package com.example.crud.amazon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="adminlog")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	
	@Id
	private Long id;
	private String aemail;
	private String apassword;
	
}
