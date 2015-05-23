package kursnalista;

import java.util.LinkedList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ValutaJsonUtility {

	
	public static JsonArray serijalizujValute (LinkedList<Valuta> valute) {
		
		JsonArray valuteJson = new JsonArray();
		for (int i =0 ; i < valute.size(); i++) {
			Valuta v = valute.get(i);
			
			JsonObject valuta = new JsonObject();
			valuta.addProperty("naziv", v.getNaziv());
			valuta.addProperty("kurs", v.getKurs());
			
			valuteJson.add(valuta);
			
		}
		return valuteJson;
	}
	
	public static LinkedList<Valuta> parseValute (JsonArray valuteJson) {
		LinkedList<Valuta> valute = new LinkedList< Valuta>();
		
		for (int i = 0; i < valute.size(); i++) {
			JsonObject valutaJson = (JsonObject) valuteJson.get(i);
			
			Valuta v = new Valuta();
			v.setKurs((valutaJson.get("kurs")).getAsDouble());
			v.setNaziv(valutaJson.get("naziv").getAsString());
			
			valute.add(v);
		}
		
		return valute;
	
	}
}
