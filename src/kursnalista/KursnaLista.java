package kursnalista;

import java.util.GregorianCalendar;



public class KursnaLista {

	private GregorianCalendar datum;
	
	private Valuta[] valute = new Valuta[50];


	public GregorianCalendar getDatum() {
		return datum;
	}

	public void setDatum(GregorianCalendar datum) {
		this.datum = datum;
	}

	public Valuta[] getValute() {
		return valute;
	}

	public void setValute(Valuta[] valute) {
		this.valute = valute;
	}
	

}
