package it.siletto.ms.base.cors;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

class CorsPreflightResponseFilter implements ResourceFilter, ContainerResponseFilter {
	
	private String[] headers;
	
	public CorsPreflightResponseFilter(String[] headers) {
		this.headers = headers;
	}
	
	@Override
	public ContainerRequestFilter getRequestFilter() {
		return null;
	}

	@Override
	public ContainerResponseFilter getResponseFilter() {
		return this;
	}

	@Override
	public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
		response.getHttpHeaders().putSingle("Access-Control-Allow-Origin", request.getHeaderValue("Origin"));
		response.getHttpHeaders().putSingle("Access-Control-Allow-Credentials", "true");
		response.getHttpHeaders().putSingle("Access-Control-Allow-Headers", convert(headers));
		return response;
	}
	
	public static String convert(String[] name) { 
		StringBuilder sb = new StringBuilder();
	    for (String st : name)
	        sb.append(st).append(',');	    
	    if (name.length != 0)
	    	sb.deleteCharAt(sb.length()-1);
	    return sb.toString();
	}
}