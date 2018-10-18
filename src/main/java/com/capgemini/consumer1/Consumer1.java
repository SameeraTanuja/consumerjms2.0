package com.capgemini.consumer1;



import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;



public class Consumer1 {
	public static void main(String args[]) {
		
		ConnectionFactory connectionFactory=null;
		Queue queue=null;
		
		try {
			InitialContext initialContext=new InitialContext();
			queue=(Queue)initialContext.lookup("jms/RegisterUser");
			connectionFactory=(ConnectionFactory)initialContext.lookup("jms/__defaultConnectionFactory");
			
		}
		catch(NamingException e) {
			e.printStackTrace();
		}
		try(JMSContext context=connectionFactory.createContext()){
			JMSConsumer consumer=context.createConsumer(queue);
			String body=consumer.receiveBody(String.class);
			System.out.println(body);
		}
		
	}

}
