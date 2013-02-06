//package obe.coherence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TwitterClient {

	// http://localhost:8080/RESTfulExample/json/product/get
	public static void main(String[] args) {

		try {

			if (null != args && args.length != 3) {
				
				System.out.println("Please pass enter command in the following format:\n");
							
				System.out.println("1. To Get tweets for a particular user");
				System.out.println("   java twitterclient GET username true/false (true - for unread tweets and false - for all tweets) \n");
				
				System.out.println("2. To add new tweet for a particular user");
				System.out.println("   java twitterclient POST username tweetmessage");
				return;
			}

			if(args[0].equalsIgnoreCase("GET")) {
				URL url = new URL("http://localhost:8080/gettweets/" + args[1]
						+ "?unread=" + args[2]);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
	
				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}
	
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));
	
				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}
	
				conn.disconnect();
			} else if(args[0].equalsIgnoreCase("POST"))  {
				URL url = new URL(
						"http://localhost:8080/newtweet");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");

				String input = "{\"username\":\"" + args[1] + "\",\"tweet\":\"" + args[2] + "\"}";
				System.out.println(input);

				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();

				/*if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}*/

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}

				conn.disconnect();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
