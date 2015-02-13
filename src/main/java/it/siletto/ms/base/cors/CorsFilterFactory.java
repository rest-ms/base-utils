package it.siletto.ms.base.cors;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.OPTIONS;

import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ResourceFilter;
import com.sun.jersey.spi.container.ResourceFilterFactory;

public class CorsFilterFactory implements ResourceFilterFactory {
	 
		@Override
		public List<ResourceFilter> create(AbstractMethod am) {
			if (am.isAnnotationPresent(Cors.class) && !am.isAnnotationPresent(OPTIONS.class)) {
				return Collections.<ResourceFilter> singletonList(new CorsResponseFilter());
			} else if(am.isAnnotationPresent(CorsPreflight.class) && am.isAnnotationPresent(OPTIONS.class)){
				return Collections.<ResourceFilter> singletonList(new CorsPreflightResponseFilter(am.getAnnotation(CorsPreflight.class).headers()));
			} else {
				return Collections.emptyList();
			}
		}

}
