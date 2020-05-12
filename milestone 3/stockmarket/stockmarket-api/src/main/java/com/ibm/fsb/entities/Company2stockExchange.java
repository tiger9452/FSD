package com.ibm.fsb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

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
@Table(name = "company2stock_exchange", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"stock_code" }) })
@EntityListeners(Company2stockExchange.class)
public class Company2stockExchange extends AbstractEntityBean {
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_company_id", nullable = false)
	private Company company;
	
	@Transient
	private Long fkCompanyId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_stock_exchange_id", nullable = false)
	private StockExchange stockExchange;
	
	@Transient
	private Long fkStockExchangeId;
	
	@Column(length = 60, name = "stock_code")
	private String stockCode;
	
	@Column(length = 10, name = "db_source")
	private String dbSource;
}
