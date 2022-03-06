package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		// simulated / virtual temperature sensor
		TemperatureSensor sn = new TemperatureSensor();
		
		Client session = new Client("sensor", Common.BROKERHOST, Common.BROKERPORT);
		
		session.connect();
		
		for(int i = 0; i < COUNT; i++)
		{
			session.publish(Common.TEMPTOPIC, String.valueOf(sn.read()));
		}
		
		session.disconnect();

		System.out.println("Temperature device stopping ... ");

	}
}
