package kursnalista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class JSONRatesApiKomunikacija {

	
	private static final String appKey = "jr-ba8999934fc5a7ab64a4872fb4ed9af7";
	private static final String jsonRatesURL = "http://jsonrates.com/get/";
	
	
	public Valuta[] vratiIznosKurseva (String[] from, String to) {
		
		Valuta[] niz = new Valuta[from.length];
	
		
		try {
			for (int i = 0; i < from.length; i++) {
				String url = jsonRatesURL + "?" +
						"from=" + from[i] +
						"&to=" + to +
						"&apiKey=" + appKey;
				
				
			String result = sendGet(url);
			
			Gson gson = new GsonBuilder().create();
			JsonObject jsonResult = (JsonObject) gson.fromJson(result, JsonObject.class);
			
			Valuta v = new Valuta();
			v.setKurs(Double.parseDouble(jsonResult.get("rate").getAsString()));
			v.setNaziv(from[i]);
			niz[i] = v;
			
			
		}	  
			return niz;
		}catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
}



	private String sendGet(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		
		boolean endReading = false;
		String response = "";
		
		while (!endReading) {
			String s = in.readLine();
			
			if (s != null) {
				response += s;
			} else {
				endReading = true;
			}
		}
		in.close();
 
		return response.toString();
	}
	
}
