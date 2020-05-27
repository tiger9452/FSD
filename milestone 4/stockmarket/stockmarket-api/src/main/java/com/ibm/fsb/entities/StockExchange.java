package com.ibm.fsb.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

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
@Table(name = "stock_exchange")
@EntityListeners(StockExchange.class)
public class StockExchange extends AbstractEntityBean {
	
	@Column(length = 60, nullable = false)
	private String name;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "brief_writeup", nullable = true, columnDefinition = "Text")
	private String briefWriteup;
	
	@Column(name = "contact_addr", length = 255, nullable = false)
	private String contactAddr;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(nullable = true, columnDefinition = "Text")
	private String remarks;
	
	@Column(length = 10, name = "db_source")
	private String dbSource;

}
