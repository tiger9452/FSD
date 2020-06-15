package com.ibm.fsb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
@Table(name = "sectors")
@EntityListeners(Sector.class)
public class Sector extends AbstractEntityBean {
	
	@Column(length = 60, nullable = false)
	private String name;
	
	@Column(length = 255, nullable = true)
	private String brief;
	
	@Column(length = 10, name = "db_source")
	private String dbSource;

}
