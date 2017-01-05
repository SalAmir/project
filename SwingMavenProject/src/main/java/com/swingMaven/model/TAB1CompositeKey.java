package com.swingMaven.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TAB1CompositeKey implements Serializable{
	private static final long serialVersionUID = 5550242209209050148L;
	private Integer col1;
	private String col2;
	
	public Integer getCOL1(){
		return col1;
	}

	public String getCOL2(){
		return col2;
	}
	
	private void setCOL1(Integer col1){
		this.col1 = col1;
	}

	private void setCOL2(String col2){
		this.col2 = col2;
	}
	
	public TAB1CompositeKey(){}
	
	public TAB1CompositeKey(Integer col1, String col2){
		this.col1 = col1;
		this.col2 = col2;
	}
}
