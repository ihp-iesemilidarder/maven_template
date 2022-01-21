package com.ekarts.dto;

import java.util.Objects;

public class TipusKart {
	private String tka_name;
	
	public TipusKart() {}
	
	public TipusKart(String tka_name) {
		this.tka_name = tka_name;
	}

	public String getTka_name() {
		return tka_name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tka_name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipusKart other = (TipusKart) obj;
		return Objects.equals(tka_name, other.tka_name);
	}

	@Override
	public String toString() {
		return "TipusKart [tka_name=" + tka_name + "]";
	}
	
}