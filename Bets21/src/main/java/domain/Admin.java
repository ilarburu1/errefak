package domain;

import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Admin extends User{
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Mezua> jasotakoMezuak= new ArrayList<Mezua>();
	public Admin() {
		super();
	}
	
	public Admin(String user, String password) {
		super(user, password);
	}

	public ArrayList<Mezua> getJasotakoMezuak() {
		return jasotakoMezuak;
	}
	
	public void addMezua(Mezua m) {
		this.jasotakoMezuak.add(m);
	}


}
