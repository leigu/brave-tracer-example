package com.github.leigu.brave.tracerexample;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.BraveTracer;
import com.github.kristofa.brave.ClientTracer;
import com.github.kristofa.brave.EndpointSubmitter;
import com.github.kristofa.brave.FixedSampleRateTraceFilter;
import com.github.kristofa.brave.LoggingSpanCollector;
import com.github.kristofa.brave.ServerTracer;
import com.github.kristofa.brave.SpanCollector;
import com.github.kristofa.brave.TraceFilter;
import com.github.kristofa.brave.TraceFilters;
import com.github.kristofa.brave.zipkin.ZipkinSpanCollector;

@Configuration
public class BraveTracerConfiguration {
    @Bean
    @Scope(value = "singleton")
    public SpanCollector spanCollector() {

        // For development purposes we use the logging span collector.
        //return new LoggingSpanCollector();
    	return new ZipkinSpanCollector("52.88.91.102", 9410);
    }
    
    @Bean
    @Scope(value = "singleton")
    public TraceFilters traceFilters() {
        // Sample rate = 1 means every request will get traced.
        return new TraceFilters(Arrays.<TraceFilter>asList(new FixedSampleRateTraceFilter(1)));
    }
    
	@Bean
	@Scope(value = "singleton")
	public ClientTracer clientTracer()
	{
		return Brave.getClientTracer(spanCollector(), traceFilters().getTraceFilters());
	}
	
	@Bean
	@Scope(value = "singleton")
	public ServerTracer serverTracer()
	{
		return Brave.getServerTracer(spanCollector(), traceFilters().getTraceFilters());
	}
	
	@Bean
	public EndpointSubmitter endPointSubmitter()
	{
		return Brave.getEndpointSubmitter();
	}
	
	@Bean
	@Scope(value = "singleton")
	public BraveTracer braveTracer()
	{
		return new BraveTracer(
				clientTracer(),
				serverTracer(),
				endPointSubmitter());
	}
}
