package domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Mugimendua {
	
	@Id @GeneratedValue
	private int mugimenduID;
	private Date data;
	private double diruKop; 
	private String deskribapena;

	public Mugimendua() {
		super();
	}
	
	public Mugimendua(Date data, double diruKop, String deskribapena) {
		this.data=data;
		this.diruKop=diruKop;
		this.deskribapena=deskribapena;
		
	}
	@Override
	public String toString() {
		return "Mugimenduak [data=" + data + "deskribapena=" + deskribapena + diruKop+ " € ]";
	}

	public Date getData() {
		return data;
	}
	public double getDiruKop() {
		return diruKop;
	}
	public String getDeskribapena() {
		return deskribapena;
	}
	
	public void setDeskribapena(String deskribapena) {
		this.deskribapena = deskribapena;
	}
	
	public int getMugimenduID() {
		return mugimenduID;
	}

	public void setMugimenduID(int mugimenduID) {
		this.mugimenduID = mugimenduID;
	}

	

}
