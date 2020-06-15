package com.ibm.fsb.entities.enums;

public enum EUserType {

	investor("investor"),
	admin("admin");

	private EUserType(String label) {
		this.label = label;
	}

	private String label;

	public String getLabel() {
		return this.label;
	}
}
