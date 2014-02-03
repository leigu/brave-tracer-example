package com.github.leigu.brave.tracerexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.kristofa.brave.BraveTracer;

@Configuration
public class BraveTracerSample {

	@Autowired
	BraveTracer braveTracer;

	public BraveTracerSample()
	{
	}
	public void trace() throws Exception
	{		
		braveTracer.startServerTracer("ServerCall");
		serverCall();
		clientCall();
		clientCall();		
		braveTracer.stopServerTracer();
	}
	public static void main(String[] args) throws Exception {
		ApplicationContext context = 
		    	  new ClassPathXmlApplicationContext(new String[] {"brave-context.xml"});
		 
		BraveTracerSample braveTest = context.getBean(BraveTracerSample.class);
		braveTest.trace();
		
	}
	private void serverCall() throws Exception
	{
		Thread.sleep(500);
	}
	private void clientCall() throws Exception
	{
		braveTracer.startClientTracer("FirstLevelClient");
		braveTracer.submitAnnotation("Marker 1", "begin sleep marker");
		braveTracer.submitBinaryAnnotation("Some Interesting Contaxt Value", "session id is 123");
		Thread.sleep(250);
		braveTracer.submitAnnotation("Marker 2", "end sleep marker");
		Thread.sleep(250);
		secondLevelClientCall();
		braveTracer.stopClientTracer();
	}
	private void secondLevelClientCall() throws Exception
	{
		braveTracer.startClientTracer("SecondLevelClient");
		Thread.sleep(250);
		thirdLevelClient();
		braveTracer.stopClientTracer();
	}
	private void thirdLevelClient() throws Exception
	{
		braveTracer.startClientTracer("ThirdLevelClient()");
		Thread.sleep(250);
		braveTracer.stopClientTracer();
	}

}
