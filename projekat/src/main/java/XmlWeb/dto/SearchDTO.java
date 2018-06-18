package XmlWeb.dto;

import java.util.List;

import XmlWeb.model.Enums.DodatneUsluge;
import XmlWeb.model.Enums.KategorijaSmestaja;
import XmlWeb.model.Enums.TipSmestaja;

import java.util.Date;

public class SearchDTO {
	
	private String destination;
	
	private Date from;
	
	private Date to;
	
	private int howManyPeople;
	
	private List<TipSmestaja> types;
	
	private List<DodatneUsluge> services;
	
	private List<KategorijaSmestaja> cats;

	public SearchDTO() {
		super();
	}

	public SearchDTO(String destination, Date from, Date to, int howManyPeople, List<TipSmestaja> types,
			List<DodatneUsluge> services, List<KategorijaSmestaja> cats) {
		super();
		this.destination = destination;
		this.from = from;
		this.to = to;
		this.howManyPeople = howManyPeople;
		this.types = types;
		this.services = services;
		this.cats = cats;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public int getHowManyPeople() {
		return howManyPeople;
	}

	public void setHowManyPeople(int howManyPeople) {
		this.howManyPeople = howManyPeople;
	}

	public List<TipSmestaja> getTypes() {
		return types;
	}

	public void setTypes(List<TipSmestaja> types) {
		this.types = types;
	}

	public List<DodatneUsluge> getServices() {
		return services;
	}

	public void setServices(List<DodatneUsluge> services) {
		this.services = services;
	}

	public List<KategorijaSmestaja> getCats() {
		return cats;
	}

	public void setCats(List<KategorijaSmestaja> cats) {
		this.cats = cats;
	}


	
}
