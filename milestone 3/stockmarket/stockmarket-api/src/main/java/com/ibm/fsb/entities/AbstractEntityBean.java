package com.ibm.fsb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@EqualsAndHashCode
@Setter
@Getter
@Accessors(chain = true)

@MappedSuperclass
public abstract class AbstractEntityBean implements Serializable {
	
	/** Serial version UID, required when using this class in e.g. remote session bean calls */
	private static final long serialVersionUID = -1100613511539119018L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    
    /**
     * @return true if this entity has no persistent identity
     */
    @Transient
    public boolean isNew() {
        return id == 0L;
    }
    
}