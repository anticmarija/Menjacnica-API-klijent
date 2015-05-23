package kursnalista;



public class KursnaLista {

	private String datum;
	
	private Valuta[] valute = new Valuta[50];

	
	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public Valuta[] getValute() {
		return valute;
	}

	public void setValute(Valuta[] valute) {
		this.valute = valute;
	}
	

}
