package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Erregistratua extends User{
	
	private String NAN;
	private double kontuDirua;
	private boolean banned = false;
	private Date banEndDate;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Apustua> Apustuak =new Vector<Apustua>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Mugimendua> mugimenduak= new ArrayList<Mugimendua>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<String> jarraitzaileak= new ArrayList<String>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Mezua> jasotakoMezuak= new ArrayList<Mezua>();
	

	public Erregistratua() {
		super();
	}
	
	public Erregistratua(String user, String password, String NAN, double kontuDirua) {
		super(user, password);
		this.NAN = NAN;
		this.kontuDirua = kontuDirua;
		this.mugimenduak= new ArrayList<Mugimendua>();
		
	}

	public ArrayList<Mugimendua> getMugimenduak() {
		return mugimenduak;
	}
	
	public void addMezua (Mezua m) {
		this.jasotakoMezuak.add(m);
	}

	public void setMugimenduak(ArrayList<Mugimendua> mugimenduak) {
		this.mugimenduak = mugimenduak;
	}

	public double getKontuDirua() {
		return kontuDirua;
	}

	public void setKontuDirua(double kontuDirua) {
		this.kontuDirua = kontuDirua;
	}

	public Vector<Apustua> getApustuak() {
		return Apustuak;
	}

	public void setApustuak(Vector<Apustua> Apustuak) {
		this.Apustuak = Apustuak;
	}

	public void setNAN(String NAN) {
		this.NAN = NAN;
	}
	
	public String getNAN() {
		return this.NAN;
	}
	
	@Override
	public String toString() {
		return "Erregistratua [NAN=" + NAN + ", kontuDirua=" + kontuDirua + ", Apustuak=" + Apustuak + ", mugimenduak="
				+ mugimenduak + ", jarraitzaileak=" + jarraitzaileak + ", jasotakoMezuak=" + jasotakoMezuak + "]";
	}
	
	public void addApustua(Apustua a) {
		Apustuak.add(a);
	}
	
	public void addMovement(Mugimendua m) {
		mugimenduak.add(m);
	}
	
	public void addJarraitzailea(String erreg) {
		this.jarraitzaileak.add(erreg);
	}
	
	
	public void removeJarraitzailea(String erreg) {
		jarraitzaileak.remove(erreg);
	}
	

	public ArrayList<String> getJarraitzaileak() {
		return jarraitzaileak;
	}

	
	
	public ArrayList<Mezua> getJasotakoMezuak() {
		return jasotakoMezuak;
	}
	
	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public Date getBanEndDate() {
		return banEndDate;
	}

	public void setBanEndDate(Date banEndDate) {
		this.banEndDate = banEndDate;
	}


}