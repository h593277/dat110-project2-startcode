package no.hvl.dat110.broker;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import no.hvl.dat110.messagetransport.Connection;

public class Storage {

	// data structure for managing subscriptions
	// maps from a topic to set of subscribed users
	protected ConcurrentHashMap<String, Set<String>> subscriptions;
	
	// data structure for managing currently connected clients
	// maps from user to corresponding client session object
	
	protected ConcurrentHashMap<String, ClientSession> clients;

	public Storage() {
		subscriptions = new ConcurrentHashMap<String, Set<String>>();
		clients = new ConcurrentHashMap<String, ClientSession>();
	}

	public Collection<ClientSession> getSessions() {
		return clients.values();
	}

	public Set<String> getTopics() {

		return subscriptions.keySet();

	}

	// get the session object for a given user
	// session object can be used to send a message to the user
	
	public ClientSession getSession(String user) {
		
		return clients.get(user);
	}

	public Set<String> getSubscribers(String topic) {

		return (subscriptions.get(topic));

	}

	public void addClientSession(String user, Connection connection) {

		ClientSession session = new ClientSession(user, connection);
		clients.put(user, session);
		
	}

	public void removeClientSession(String user) {

		clients.get(user).disconnect();
		clients.remove(user);
		
	}

	public void createTopic(String topic) {

		// TODO: create topic in the storage
		subscriptions.put(topic, new HashSet<String>());
	
	}

	public void deleteTopic(String topic) {

		// TODO: delete topic from the storage
		subscriptions.remove(topic);
		
	}

	public void addSubscriber(String user, String topic) {

		// TODO: add the user as subscriber to the topic
		Set<String> subs = new HashSet<String>();
		if(subscriptions.get(topic) != null)
		{
			subs = subscriptions.get(topic);
		}
		subs.add(user);
		subscriptions.put(topic, subs);
		
	}

	public void removeSubscriber(String user, String topic) {

		// TODO: remove the user as subscriber to the topic
		Set<String> subs = new HashSet<String>();
		if(subscriptions.get(topic) != null)
		{
			subs = subscriptions.get(topic);
			subs.remove(user);
			subscriptions.put(topic, subs);
		}
		
		
	}
}
