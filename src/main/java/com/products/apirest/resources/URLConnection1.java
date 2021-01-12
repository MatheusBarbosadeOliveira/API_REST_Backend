package com.products.apirest.resources;

import com.products.apirest.models.Product;
import java.lang.reflect.Type;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class URLConnection1 {

	private static final String GET_URL = "http://localhost:8080/api/products";

	public static List<Product> sendGET() throws IOException {
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { 
			// success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response);
			
			Type listType = new TypeToken<ArrayList<Product>>(){}.getType();
			List<Product> meterList = new Gson().fromJson(response.toString(), listType);

			return meterList;
			
		} else {
			System.out.println("GET request not worked");
			return null;
		}
		

	}
}