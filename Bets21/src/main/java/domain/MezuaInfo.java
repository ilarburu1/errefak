package domain;

import java.util.Date;

public class MezuaInfo {
    private final Date data;
    private final Admin admin;
    private final String bidaltzailea;
    private final String mezua;

    public MezuaInfo(Date data, Admin admin, String bidaltzailea,String mezua) {
        this.data = data;
        this.admin = admin;
        this.bidaltzailea = bidaltzailea;
        this.mezua = mezua;
    }

	public Admin getAdmin() {
		return admin;
	}

	public String getBidaltzailea() {
		return bidaltzailea;
	}

	public String getMezua() {
		return mezua;
	}

	public Date getData() {
		return this.data;
	}
	
}

