package ua.nure.sharov.Airlines.currency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;

public class Currency {
	private static final Logger LOG = Logger.getLogger(Currency.class);
	private static final String URL_PRIVAT = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

	private static String getJson() throws ApplicationException {
		HttpURLConnection urlConnection = null;
		BufferedReader reader = null;
		String resultJson = "";
		try {
			URL url = new URL(URL_PRIVAT);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.connect();

			InputStream inputStream = urlConnection.getInputStream();
			StringBuilder buffer = new StringBuilder();

			reader = new BufferedReader(new InputStreamReader(inputStream));

			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			resultJson = buffer.toString();
		} catch (IOException e) {
			LOG.error(Messages.ERR_CANT_GET_CURRENCY, e);
			throw new ApplicationException(Messages.ERR_CANT_GET_CURRENCY, e);
		}
		return resultJson;
	}

	/**
	 * 
	 * @return Returns the value which represents current exchange rates 1 USD = UAH
	 * @throws ApplicationException
	 */
	public Double getUSDCurrency() throws ApplicationException {

		JSONParser parser = new JSONParser();
		Object obj = null;
		String resultJson = getJson();
		try {
			obj = parser.parse(resultJson);
		} catch (ParseException e) {
			LOG.error(Messages.ERR_CANT_GET_CURRENCY, e);
			throw new ApplicationException(Messages.ERR_CANT_GET_CURRENCY, e);
		}
		JSONArray jsonAr = (JSONArray) obj;
		obj = jsonAr.get(2);
		JSONObject jObj = (JSONObject) obj;
		String currency = (String) jObj.get("sale");
		Double cur = Double.valueOf(currency);
		return cur;
	}
}