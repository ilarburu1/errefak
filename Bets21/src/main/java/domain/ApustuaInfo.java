package domain;

import java.util.Date;

public class ApustuaInfo {
    private final Apustua apustu;
    private final Date data;
    private final double zenbatekoa;
    private final Mugimendua m;
    private final String jarraitzailea;

    public ApustuaInfo(Apustua apustu, Date data, double zenbatekoa, Mugimendua m, String jarraitzailea) {
        this.apustu = apustu;
        this.data = data;
        this.zenbatekoa = zenbatekoa;
        this.m = m;
        this.jarraitzailea = jarraitzailea;
    }

	public Object getJarraitzailea() {
		return this.jarraitzailea;
	}

	public double getZenbatekoa() {
		return this.zenbatekoa;
	}

	public Mugimendua getMove() {
		return this.m;
	}

	public Apustua getApustua() {
		return this.apustu;
	}

	public Date getData() {
		return this.data;
	}
	
}


