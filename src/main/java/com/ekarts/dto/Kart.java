package com.ekarts.dto;

import java.util.Objects;

public class Kart {
	private int kar_id;
	private String kar_name;
	private TipusKart kar_tipus;
	private double kar_power;
	private double kar_price_minute;
	
	public Kart() {}
	
	public Kart(int kar_id, String kar_name, TipusKart kar_tipus, double kar_power, double kar_price_minute) {
		this.kar_id = kar_id;
		this.kar_name = kar_name;
		this.kar_tipus = kar_tipus;
		this.kar_power = kar_power;
		this.kar_price_minute = kar_price_minute;
	}
	public Kart(String kar_name, TipusKart kar_tipus, double kar_power, double kar_price_minute) {
		this.kar_name = kar_name;
		this.kar_tipus = kar_tipus;
		this.kar_power = kar_power;
		this.kar_price_minute = kar_price_minute;
	}

	public String getKar_name() {
		return kar_name;
	}

	public void setKar_name(String kar_name) {
		this.kar_name = kar_name;
	}

	public TipusKart getKar_tipus() {
		return kar_tipus;
	}

	public void setKar_tipus(TipusKart kar_tipus) {
		this.kar_tipus = kar_tipus;
	}

	public double getKar_power() {
		return kar_power;
	}

	public void setKar_power(double kar_power) {
		this.kar_power = kar_power;
	}

	public double getKar_price_minute() {
		return kar_price_minute;
	}

	public void setKar_price_minute(double kar_price_minute) {
		this.kar_price_minute = kar_price_minute;
	}

	public int getKar_id() {
		return kar_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(kar_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kart other = (Kart) obj;
		return kar_id == other.kar_id;
	}

	@Override
	public String toString() {
		return "Kart [kar_id=" + kar_id + ", kar_name=" + kar_name + ", kar_tipus=" + kar_tipus + ", kar_power="
				+ kar_power + ", kar_price_minute=" + kar_price_minute + "]";
	}
	
}