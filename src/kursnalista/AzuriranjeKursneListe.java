package kursnalista;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;



public class AzuriranjeKursneListe {

	private static final String  putanjaDoFajlaKursnaLista = "data/kursnaLista.json";
	
	
	public LinkedList<Valuta> ucitajValute () {
		
		LinkedList<Valuta> valuteLista = new LinkedList<Valuta>();
		try {
			FileReader reader = new FileReader(putanjaDoFajlaKursnaLista);
			
			Gson gson = new GsonBuilder().create();
			
			KursnaLista kursnaLista = gson.fromJson(reader, KursnaLista.class);
			Valuta[] valute = kursnaLista.getValute();
			
			for (int i = 0; i < valute.length; i++) {
				valuteLista.add(valute[i]);
			}
			
			return valuteLista;
			
		} catch (FileNotFoundException e) {
			System.out.println("Greska: " +e.getMessage());
			return null;
		}
		
	}
	
	public static void upisiUJsonFajl (LinkedList<Valuta> valute, GregorianCalendar datum) {
		
		JsonArray nizValuta = 
				ValutaJsonUtility.serijalizujValute(valute);
		
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(putanjaDoFajlaKursnaLista)));

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
			String nizValutaString = gson.toJson(nizValuta);
			
			String datumString = gson.toJson(datum);
			
			out.println(datumString);
			out.println(nizValutaString);
			out.close();
		} catch (Exception e) {
			System.out.println("Greska: " +e.getMessage());
		}
		
		
	}
	
	
	public void upisiValute (LinkedList<Valuta> valuteZaDanas, GregorianCalendar datum) {
	
			LinkedList<Valuta> valutaLista = ucitajValute();
			
			for (int i = 0; i < valuteZaDanas.size(); i++) {
				for (int j = 0; j < valutaLista.size(); j++) {
					
					if (valuteZaDanas.get(i).getNaziv().equals(valutaLista.get(j).getNaziv())) {
						valutaLista.get(j).setKurs(valuteZaDanas.get(i).getKurs());
					}	
				}
				
				AzuriranjeKursneListe.upisiUJsonFajl(valutaLista, datum);
	
			}
		
		
	}
	
	
	public void azurirajValute () {
		
		LinkedList<Valuta> valuteIzFajla = ucitajValute();
		
		String[] nazivi = new String[valuteIzFajla.size()];
		
		for (int i = 0; i < nazivi.length; i++) {
			nazivi[i] = valuteIzFajla.get(i).getNaziv();
		}
		
		
		Valuta[] valuteJSONRates = new JSONRatesApiKomunikacija().vratiIznosKurseva(nazivi, "RSD");
		
		LinkedList<Valuta> noveValute = new LinkedList<Valuta>();
		
		for (int i = 0; i < valuteJSONRates.length; i++) {
			noveValute.add(valuteJSONRates[i]);
		}
		
		upisiUJsonFajl(noveValute, new GregorianCalendar());
	}
}
