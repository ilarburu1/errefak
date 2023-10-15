package domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Mezua {
	
	@Id@GeneratedValue
	private int mezuaID;
	private String mezua;
	private Date data;
	private String bidaltzailea;
	
	
	public Mezua() {
		super();
	}
	
	public Mezua(Date data, String bidaltzailea, String mezua) {
		super();
		this.data = data;
		this.bidaltzailea = bidaltzailea;
		this.mezua = mezua;
	}
	
	public int getMezuaID() {
		return mezuaID;
	}

	public void setMezuaID(int mezuaID) {
		this.mezuaID = mezuaID;
	}

	public Date getData() {
		return data;
	}

	public String getBidaltzailea() {
		return bidaltzailea;
	}

	public String getMezua() {
		return mezua;
	}

	@Override
	public String toString() {
		return "Mezua [data=" + data + ", bidaltzailea=" + bidaltzailea + ", mezua=" + mezua + "]";
	}
	
	
	
	

}
