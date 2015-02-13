package it.siletto.ms.base.cors;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

class CorsResponseFilter implements ResourceFilter, ContainerResponseFilter {
	 
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
		return response;
	}
}