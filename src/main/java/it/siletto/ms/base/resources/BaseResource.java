package it.siletto.ms.base.resources;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base Resource from dropwizard hello world
 *
 */
public abstract class BaseResource {
  protected final Logger log = LoggerFactory.getLogger(this.getClass());

  @Context
  protected UriInfo uriInfo;

  @Context
  protected HttpHeaders httpHeaders;

  @Context
  protected HttpServletRequest request;

  @Context
  protected HttpServletResponse response;

  public BaseResource() {

  }

  /**
   * @return The most appropriate locale for the upstream request (never null)
   */
  public Locale getLocale() {
    // TODO This should be a configuration setting
    Locale defaultLocale = Locale.UK;

    Locale locale;
    if (httpHeaders == null) {
      locale = defaultLocale;
    } else {
      locale = httpHeaders.getLanguage();
      if (locale == null) {
        locale = defaultLocale;
      }
    }
    return locale;
  }

}
