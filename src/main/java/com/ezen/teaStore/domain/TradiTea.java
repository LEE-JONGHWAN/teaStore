package com.ezen.teaStore.domain;

import java.util.Date;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
public class TradiTea {
	private int teaID;
	private String teaName;
	private int stock;
	private Date prodDate;
	private String amount;
	
	@Setter(AccessLevel.NONE)
	private double price;
	public void setPrice(double price) {
		this.price = price;
		this.priceStr = String.format("%,.0f", price);
	}
	
	@Setter(AccessLevel.NONE)
	private double priceOriginal;
	public void setPriceOrignal(double price) {
		this.priceOriginal = price;
		this.priceOriginalStr = String.format("%,.0f", price);
	}
	
	private String priceStr;
	private String priceOriginalStr;
	
	private String prodDesc;
}
