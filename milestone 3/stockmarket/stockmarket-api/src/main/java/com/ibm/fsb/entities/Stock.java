package com.ibm.fsb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)

@Entity
@Table(name = "stock")
@EntityListeners(Stock.class)
public class Stock extends AbstractEntityBean {
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_company_id", nullable = false)
	private Company company;
	
	@Transient
	private Long fkCompanyId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_stock_exchange_id", nullable = false)
	private StockExchange stockExchange;
	
	@Transient
	private Long fkStock_exchangeId;
	
	@Column(nullable = true, columnDefinition = "Decimal(9,3)")
	private Double price;
	
	@Column(length = 10, nullable = true)
	private String unit;
	
	@Column(length = 30, nullable = true)
	private String date4price;
	
	@Column(length = 30, nullable = true)
	private String time4price;
	
	@Column(length = 10, name = "db_source")
	private String dbSource;
}
