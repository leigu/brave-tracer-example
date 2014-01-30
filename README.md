# brave-tracer-example #
Brave [brave](https://github.com/kristofa/brave) is a Java client side implementation of [Zipkin](https://github.com/twitter/zipkin/)
that sends tracing information to Zipkin collector through Flume. Zipkin can then show tracing information through a web UI.

The current implementation of Brave only supports one level of nested client call and there is only one example of showing how to integrate
Brave with RestEasy, which doesn't support what we need. 

So I forked Brave and create a new [branch](https://github.com/leigu/brave) and created a BraveTracer class. This fork will support the following,
* Unlimited level of nested client tracing
* A simple and easy to use API that can be used to trace any block of Java code or method

## How to Install and Run ##

### Download and Install
First, pull the Brave fork,

	git clone https://github.com/leigu/brave	
	cd brave
	mvn clean install

Then pull the brave-tracer-example,

	git clone https://github.com/leigu/brave-tracer-example
	cd brave-tracer-example
	mvn compile


### Execute the Sample Code
Execute the following line,

mvn exec:java -Dexec.mainClass="com.github.leigu.brave.tracerexample.BraveTracerSample"


Here is the output from the above code,

	14:16:26,487 INFO  [main] support.AbstractApplicationContext (AbstractApplicationContext.java:510) - Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@71a1a5f3: startup date [Thu Jan 30 14:16:26 EST 2014]; root of context hierarchy
	14:16:26,595 INFO  [main] xml.XmlBeanDefinitionReader (XmlBeanDefinitionReader.java:315) - Loading XML bean definitions from class path resource [brave-context.xml]
	14:16:27,068 INFO  [main] support.DefaultListableBeanFactory (DefaultListableBeanFactory.java:596) - Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@72aff016: defining beans [org.springframework.context.annotation.internalConfigurationAnnotationProcessor,org.springframework.context.annotation.internalAutowiredAnnotationProcessor,org.springframework.context.annotation.internalRequiredAnnotationProcessor,org.springframework.context.annotation.internalCommonAnnotationProcessor,org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor#0,com.github.leigu.brave.tracerexample.BraveTracerSample#0,com.github.leigu.brave.tracerexample.BraveTracerConfiguration#0,org.springframework.context.annotation.ConfigurationClassPostProcessor.importAwareProcessor,spanCollector,traceFilters,clientTracer,serverTracer,endPointSubmitter,braveTracer]; root of factory hierarchy
	14:16:28,706 INFO  [main] brave.LoggingSpanCollectorImpl (LoggingSpanCollectorImpl.java:51) - Span(trace_id:7389971831162427730, name:ThirdLevelClient(), id:-5970295777494030555, parent_id:7389971831162427730, annotations:[Annotation(timestamp:1391109388454000, value:cs, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall)), Annotation(timestamp:1391109388704000, value:cr, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall))], binary_annotations:[BinaryAnnotation(key:request, value:54 68 69 72 64 4C 65 76 65 6C 43 6C 69 65 6E 74 28 29, annotation_type:STRING, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall))])
	14:16:28,706 INFO  [main] brave.LoggingSpanCollectorImpl (LoggingSpanCollectorImpl.java:51) - Span(trace_id:7389971831162427730, name:SecondLevelClient, id:4721949608125765124, parent_id:7389971831162427730, annotations:[Annotation(timestamp:1391109388204000, value:cs, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall)), Annotation(timestamp:1391109388706000, value:cr, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall))], binary_annotations:[BinaryAnnotation(key:request, value:53 65 63 6F 6E 64 4C 65 76 65 6C 43 6C 69 65 6E 74, annotation_type:STRING, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall))])
	14:16:28,707 INFO  [main] brave.LoggingSpanCollectorImpl (LoggingSpanCollectorImpl.java:51) - Span(trace_id:7389971831162427730, name:FirstLevelClient, id:3858694657894992599, parent_id:7389971831162427730, annotations:[Annotation(timestamp:1391109387704000, value:cs, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall)), Annotation(timestamp:1391109387704000, value:begin sleep marker, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall)), Annotation(timestamp:1391109387954000, value:end sleep marker, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall)), Annotation(timestamp:1391109388706000, value:cr, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall))], binary_annotations:[BinaryAnnotation(key:request, value:46 69 72 73 74 4C 65 76 65 6C 43 6C 69 65 6E 74, annotation_type:STRING, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall)), BinaryAnnotation(key:Some Interesting Contaxt Value, value:73 65 73 73 69 6F 6E 20 69 64 20 69 73 20 31 32 33, annotation_type:STRING, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall))])
	14:16:29,707 INFO  [main] brave.LoggingSpanCollectorImpl (LoggingSpanCollectorImpl.java:51) - Span(trace_id:7389971831162427730, name:ThirdLevelClient(), id:7755191266288750642, parent_id:7389971831162427730, annotations:[Annotation(timestamp:1391109389457000, value:cs, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall)), Annotation(timestamp:1391109389707000, value:cr, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall))], binary_annotations:[BinaryAnnotation(key:request, value:54 68 69 72 64 4C 65 76 65 6C 43 6C 69 65 6E 74 28 29, annotation_type:STRING, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall))])
	14:16:29,707 INFO  [main] brave.LoggingSpanCollectorImpl (LoggingSpanCollectorImpl.java:51) - Span(trace_id:7389971831162427730, name:SecondLevelClient, id:-3712131391325292342, parent_id:7389971831162427730, annotations:[Annotation(timestamp:1391109389207000, value:cs, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall)), Annotation(timestamp:1391109389707000, value:cr, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall))], binary_annotations:[BinaryAnnotation(key:request, value:53 65 63 6F 6E 64 4C 65 76 65 6C 43 6C 69 65 6E 74, annotation_type:STRING, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall))])
	14:16:29,707 INFO  [main] brave.LoggingSpanCollectorImpl (LoggingSpanCollectorImpl.java:51) - Span(trace_id:7389971831162427730, name:FirstLevelClient, id:3511395203602466542, parent_id:7389971831162427730, annotations:[Annotation(timestamp:1391109388707000, value:cs, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall)), Annotation(timestamp:1391109388707000, value:begin sleep marker, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall)), Annotation(timestamp:1391109388957000, value:end sleep marker, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall)), Annotation(timestamp:1391109389707000, value:cr, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall))], binary_annotations:[BinaryAnnotation(key:request, value:46 69 72 73 74 4C 65 76 65 6C 43 6C 69 65 6E 74, annotation_type:STRING, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall)), BinaryAnnotation(key:Some Interesting Contaxt Value, value:73 65 73 73 69 6F 6E 20 69 64 20 69 73 20 31 32 33, annotation_type:STRING, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall))])
	14:16:29,708 INFO  [main] brave.LoggingSpanCollectorImpl (LoggingSpanCollectorImpl.java:51) - Span(trace_id:7389971831162427730, name:ServerCall, id:7389971831162427730, annotations:[Annotation(timestamp:1391109387201000, value:sr, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall)), Annotation(timestamp:1391109389708000, value:ss, host:Endpoint(ipv4:2130706433, port:0, service_name:ServerCall))], binary_annotations:null)


The default SpanCollector writes to the log file. In order to work with a remote span collector, change the following line from file
`com.github.leigu.brave.tracerexample.BraveTracerConfiguration`, comment line 29 and uncomment line 30 for method spanCollector(). The method
`spanCollector` should look like this after the change,

    public SpanCollector spanCollector() {

        // For development purposes we use the logging span collector.
        //return new LoggingSpanCollectorImpl();
    	return new ZipkinSpanCollector("localhost", 9410);
    }
     
After the above change, the sample will write to SpanCollector running on localhost and port 9410. Make sure you point to the host that runs Zipkin span collector
or local Flume agent.

Run the sample code, and then switch to Zipkin UI and one should see the following picture,
![Zipkin UI Top View:](./brave-tracer.png?raw=true)

As we can see, the UI shows the top level server trace that includes three levels of nested client traces.

We can add client tracing annotations, which will create circle marker in the diagram. Tracing annotations are important if we want to add additional tracing information within the client trace.

Click on the first client trace, which brings up the following picture,
![First Level Client Trace](./brave-tracer-firstlevel.png?raw=true)


## Under the Hook
The main sample class is [BraveTracerSample](https://github.com/leigu/brave-tracer-example/blob/master/src/main/java/com/github/leigu/brave/tracerexample/BraveTracerSample.java). 

The sample code uses sprint for autowiring and injection. Here is the [spring configuration file](https://github.com/leigu/brave-tracer-example/blob/master/src/main/resources/brave-context.xml).


We must start server trace at the hight level as the following,
	braveTracer.startServerTracer("ServerCall");
	
When we are done server tracing, 
	braveTracer.stopServerTracer();
	
	
To start a client tracing,
	braveTracer.startClientTracer("FirstLevelClient");
	
To end a client tracing,
	braveTracer.stopClientTracer();
	
To add a internal client trace marker,
	braveTracer.submitAnnotation("Marker 1", "begin sleep marker");
	
To add additional context to the client trace,	
	braveTracer.submitBinaryAnnotation("Some Interesting Contaxt Value", "session id is 123");
	
Nested client trace can be down as the following,

	// start first level tracing
	braveTracer.startClientTracer("FirstLevelClient");
	//do stuff
	
	// start second level tracing
	braveTracer.startClientTracer("SecondLevelClient");
	// do some more stuff
	
	// done second level tracking
	braveTracer.stopClientTracer();
	
	// done first level tracing
	braveTracer.stopClientTracer();

