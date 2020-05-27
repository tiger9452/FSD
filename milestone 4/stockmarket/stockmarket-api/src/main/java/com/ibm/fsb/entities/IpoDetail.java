
package com.ibm.fsb.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "ipo_details")
@EntityListeners(IpoDetail.class)
public class IpoDetail extends AbstractEntityBean {
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
	
	@Column(nullable = true, columnDefinition = "Decimal(9,3)")
	private Double priceperShare;
	
	@Column(name = "total_number_shares", columnDefinition = "INT")
	private Integer totalNumberShares;
	
	@Column(name = "open_datetime", columnDefinition = "DATETIME")
	private Date openDatetime;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(nullable = true, columnDefinition = "Text")
	private String remarks;
	
	@Column(length = 10, name = "db_source")
	private String dbSource;

}
