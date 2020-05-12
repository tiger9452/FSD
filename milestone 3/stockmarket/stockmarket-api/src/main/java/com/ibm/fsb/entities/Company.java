package com.ibm.fsb.entities;

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
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)

@Entity
@Table(name = "company")
@EntityListeners(Company.class)
public class Company extends AbstractEntityBean {
	
	@Column(length = 60, nullable = false)
	private String name;
	
	@Column(length = 20, nullable = false)
	private String code;
	
	@Column(length = 30, nullable = false)
	private String turnover;
	
	@Column(length = 50, nullable = true)
	private String ceo;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "board_directors", nullable = true, columnDefinition = "Text")
	private String boardDirectors;
	
	@Transient
	private Long fkSectorId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_sector_id", nullable = false)
	private Sector sector;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "brief_writeup", nullable = true, columnDefinition = "Text")
	private String briefWriteup;
	
	@Column(nullable = false, columnDefinition = "BIT DEFAULT b'1'")
	private Boolean active;
	
	@Column(length = 10, name = "db_source")
	private String dbSource;

}
