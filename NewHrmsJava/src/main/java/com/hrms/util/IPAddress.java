package com.hrms.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;

public class IPAddress {

	public static void main(String[] args) throws UnknownHostException {
		IPAddress.getCurrentIp();

	}

	public static String getCurrentIp() {
		
		try {
			// Fetch the public IP address from an external API
			URL ipApi = new URL("https://api.ipify.org?format=text");
			BufferedReader in = new BufferedReader(new InputStreamReader(ipApi.openStream()));
			String ipAddress = in.readLine();
			in.close();
			System.out.println("Public IP Address: " + ipAddress);
			return ipAddress.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	       
		
		
//		try {
//			InetAddress localhost = InetAddress.getLocalHost();
//			System.out.println("System IP Address : " +(localhost.getHostAddress()).trim());
//			return localhost.toString();
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//			return null;
//		}

//		 try {
//	            String hostName = "www.google.com";
//	            InetAddress address = InetAddress.getByName(hostName);
//	            System.out.println("IP address of " + ": " + address.getHostAddress());
//	            return address.toString();
//	            
//	        } catch (UnknownHostException ex) {
//	            System.out.println("Could not find IP address for " );
//	            return null;
//	        }
//		 
		
	}

}
