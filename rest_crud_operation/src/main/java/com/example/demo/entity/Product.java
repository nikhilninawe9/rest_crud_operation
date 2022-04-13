package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer prodId;
	private String prodCode;
	private String prodName;
	private Double prodCost;
	private String prodCompany;
	private String prodCountryOrigin;
}
