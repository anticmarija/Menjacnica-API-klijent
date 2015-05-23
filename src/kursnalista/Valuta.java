package kursnalista;

public class Valuta {

	private String naziv;
	private double kurs;
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public double getKurs() {
		return kurs;
	}
	public void setKurs(double kurs) {
		this.kurs = kurs;
	}
	
	public String toString () {
		return "Valuta: " +naziv+ ", kurs: " +kurs;
	}
}
