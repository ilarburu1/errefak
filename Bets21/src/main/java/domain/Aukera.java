package domain;

import java.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Aukera {
	
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer aukeraID;
	private String aukeraIzena;
	private float kuota;
	@XmlIDREF
	private Question galdera;
	private Vector<Apustua> apustuak = new Vector<Apustua>();
	private String egoera = "egoeraGabe";
	private boolean azkenaDa;
	

	public Aukera() {
		super();
	}
	
	public Aukera(String aukeraIzena, Question galdera, float kuota) {
		this.aukeraIzena=aukeraIzena;
		this.galdera=galdera;
		this.kuota=kuota;
	}

	public Aukera(int aukeraID, String aukeraIzena) {
		this.aukeraIzena = aukeraIzena;
		this.aukeraID = aukeraID;
	}
	
	public Aukera(int aukeraID, String aukeraIzena, float kuota) {
		this.aukeraIzena = aukeraIzena;
		this.aukeraID = aukeraID;
		this.kuota=kuota;
	}
	
	public int getAukeraID() {
		return aukeraID;
	}

	public void setAukeraID(int optionID) {
		this.aukeraID = optionID;
	}

	public String getAukeraIzena() {
		return aukeraIzena;
	}

	public void setAukeraIzena(String aukeraIzena) {
		this.aukeraIzena = aukeraIzena;
	}

	public float getKuota() {
		return kuota;
	}

	public void setKuota(float kuota2) {
		this.kuota = kuota2;
	}

	public Question getGaldera() {
		return galdera;
	}

	public void setGaldera(Question galdera) {
		this.galdera = galdera;
	}

	public Vector<Apustua> getApustuak() {
		return apustuak;
	}

	public void setApustuak(Vector<Apustua> apustuak) {
		this.apustuak = apustuak;
	}
	
	public void addApustua(Apustua a) {
		apustuak.add(a);
	}

	public String getEgoera() {
		return egoera;
	}

	public void setEgoera(String egoera) {
		this.egoera = egoera;
	}
	
	public boolean isAzkenaDa() {
		return azkenaDa;
	}

	public void setAzkenaDa(boolean azkenaDa) {
		this.azkenaDa = azkenaDa;
	}
}
