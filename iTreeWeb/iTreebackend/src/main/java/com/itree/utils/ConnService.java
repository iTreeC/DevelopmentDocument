package com.itree.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnService {

	public String Conntce(URL url, String method, String input) throws IOException {
		URL targetUrl = url;
		HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
		httpConnection.setDoOutput(true);
		httpConnection.setRequestMethod(method);
		httpConnection.setRequestProperty("Content-Type", "application/json");

		// String input = "{\"rid\":1,\"pid\":2}";
		OutputStream outputStream = httpConnection.getOutputStream();
		outputStream.write(input.getBytes());
		outputStream.flush();

		
		
		if (httpConnection.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + httpConnection.getResponseCode());
		}

		BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));

		String output;
			System.out.println("Output from Server:\n");
		while ((output = responseBuffer.readLine()) != null) {
			System.out.println(output);
			return output;
		}

		httpConnection.disconnect();

		return null;
	}
}
