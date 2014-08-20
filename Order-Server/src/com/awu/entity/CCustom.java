package com.awu.entity;

public class CCustom {
	private String m_FullName;
	private int m_SexId;
	
	public CCustom(String fullName,int sexId){
		m_FullName = fullName;
		m_SexId = sexId;
	}
	
	public String getFullName(){
		return m_FullName;
	}
	
	public int getSexId(){
		return m_SexId;
	}
}
