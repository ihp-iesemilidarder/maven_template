package com.ekarts.dto;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Objects;

public class CliKar {
	private Client cxk_cli_id;
	private Kart cxk_kar_id;
	private LocalDate cxk_date;
	private LocalTime cxk_time_start;
	private LocalTime cxk_time_finish;
	private String cxk_circuit;
	private int cxk_position;
	
	public CliKar() {}
	
	public CliKar(Client cxk_cli_id,Kart cxk_kar_id, LocalDate cxk_date,LocalTime cxk_time_start,LocalTime cxk_time_finish,String cxk_circuit,int cxk_position) {
		this.cxk_cli_id = cxk_cli_id;
		this.cxk_kar_id = cxk_kar_id;
		this.cxk_date = cxk_date;
		this.cxk_time_start = cxk_time_start;
		this.cxk_time_finish = cxk_time_finish;
		this.cxk_circuit = cxk_circuit;
		this.cxk_position = cxk_position;
	}

	public LocalTime getCxk_time_start() {
		return cxk_time_start;
	}

	public void setCxk_time_start(LocalTime cxk_time_start) {
		this.cxk_time_start = cxk_time_start;
	}

	public LocalTime getCxk_time_finish() {
		return cxk_time_finish;
	}

	public void setCxk_time_finish(LocalTime cxk_time_finish) {
		this.cxk_time_finish = cxk_time_finish;
	}

	public String getCxk_circuit() {
		return cxk_circuit;
	}

	public void setCxk_circuit(String cxk_circuit) {
		this.cxk_circuit = cxk_circuit;
	}

	public int getCxk_position() {
		return cxk_position;
	}

	public void setCxk_position(int cxk_position) {
		this.cxk_position = cxk_position;
	}

	public LocalDate getCxk_date() {
		return cxk_date;
	}

	public void setCxk_date(LocalDate cxk_date) {
		this.cxk_date = cxk_date;
	}

	public Client getCxk_cli_id() {
		return cxk_cli_id;
	}

	public Kart getCxk_kar_id() {
		return cxk_kar_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cxk_cli_id, cxk_kar_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CliKar other = (CliKar) obj;
		return cxk_cli_id == other.cxk_cli_id && cxk_kar_id == other.cxk_kar_id;
	}

	@Override
	public String toString() {
		return "CliKar [cxk_cli_id=" + cxk_cli_id + ", cxk_kar_id=" + cxk_kar_id + ", cxk_date=" + cxk_date
				+ ", cxk_time_start=" + cxk_time_start + ", cxk_time_finish=" + cxk_time_finish + ", cxk_circuit="
				+ cxk_circuit + ", cxk_position=" + cxk_position + "]";
	}
	
}
