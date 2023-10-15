package domain;


import java.util.ArrayList;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Apustua {
	
	@Id @GeneratedValue
	private int ApustuID;
	private String log;
	private double zenbatekoa;
	boolean asmatua = false;
	boolean bukatua = false;
	private ArrayList<Aukera> erantzunak = new ArrayList<Aukera>();
	private int erantzunKop;
	
	public Apustua() {
		super();
	}
	
	public Apustua(String log,double zenbatekoa) {
		this.log = log;
		this.zenbatekoa = zenbatekoa;
		this.asmatua = false;
		this.erantzunKop = 0;
	}
	
	public int getErantzunKop() {
		return erantzunKop;
	}

	public void setErantzunKop(int erantzunKop) {
		this.erantzunKop = erantzunKop;
	}

	public int getApustuID() {
		return ApustuID;
	}

	public void setApostuID(int apustuID) {
		ApustuID = apustuID;
	}

	public boolean isAsmatua() {
		return asmatua;
	}

	public void setAsmatua(boolean asmatua) {
		this.asmatua = asmatua;
	}
	
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}

	public double getZenbatekoa() {
		return zenbatekoa;
	}

	public void setZenbatekoa(double zenbatekoa) {
		this.zenbatekoa = zenbatekoa;
	}
	
	
	public ArrayList<Aukera> getErantzunak() {
		return erantzunak;
	}

	public void setErantzunak(ArrayList<Aukera> erantzunak) {
		this.erantzunak = erantzunak;
	}
	
	public void addAukera(Aukera a) {
		erantzunak.add(a);
		erantzunKop++;
	}
	
	public boolean isBukatua() {
		return bukatua;
	}

	public void setBukatua(boolean bukatua) {
		this.bukatua = bukatua;
	}

}
