package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;

public class DisplayDevice {
	
	private static final int COUNT = 10;
		
	public static void main (String[] args) {
		
		System.out.println("Display starting ...");
		
		Client session = new Client("display", Common.BROKERHOST, Common.BROKERPORT);
		
		session.connect();
		
		session.createTopic(Common.TEMPTOPIC);
		
		session.subscribe(Common.TEMPTOPIC);
		
		for(int i = 0; i < COUNT; i++)
		{
			session.receive();
		}
		
		session.unsubscribe(Common.TEMPTOPIC);
		
		session.disconnect();
		
		
		System.out.println("Display stopping ... ");
		
	}
}
