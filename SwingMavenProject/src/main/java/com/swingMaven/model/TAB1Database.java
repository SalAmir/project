package com.swingMaven.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TAB1", uniqueConstraints = {
		@UniqueConstraint(columnNames = "COL1"),
		@UniqueConstraint(columnNames = "COL2")})
public class TAB1Database implements Serializable{

	private static final long serialVersionUID = -1662693751023182481L;

	@Id
	@Column(name = "COL1", unique = true, nullable = false)
	private Integer col1;

	@Column(name = "COL2", unique = true, nullable = false)
	private String col2;

	@Column(name = "COL3", unique = false, nullable = true)
	private Integer col3;

	@Column(name = "COL4", unique = false, nullable = true)
	private String col4;

	public Integer getCOL1(){
		return col1;
	}

	public String getCOL2(){
		return col2;
	}

	public Integer getCOL3(){
		return col3;
	}

	public String getCOL4(){
		return col4;
	}

	public void setCOL1(Integer col1){
		this.col1 = col1;
	}

	public void setCOL2(String col2){
		this.col2 = col2;
	}

	public void setCOL3(Integer col3){
		this.col3 = col3;
	}

	public void setCOL4(String col4){
		this.col4 = col4;
	}
}
