package com.ekarts.dto;

import java.io.Serializable;
import java.util.Objects;

public class Client implements Serializable{   
    private int id;
    private String nif;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private double balance;

	public Client(int id, String nif, String name, String surname, String email, String phone, double balance) {
		this.id = id;
		this.nif = nif;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.balance = balance;
	}
	
	public Client(String nif, String name, String surname, String email, String phone, double balance) {
		this.nif = nif;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.balance = balance;
	}
	
	public Client(int id) {
		this.id = id;
	}
	
    public Client() {
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return id == other.id;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nif=" + nif + ", name=" + name + ", surname=" + surname + ", email=" + email
				+ ", phone=" + phone + ", balance=" + balance + "]";
	}



}
