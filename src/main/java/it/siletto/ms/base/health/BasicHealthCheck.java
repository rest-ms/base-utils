package it.siletto.ms.base.health;


/**
 * Dumb healt check
 * 
 */
import com.codahale.metrics.health.HealthCheck;

public class BasicHealthCheck extends HealthCheck {

  public BasicHealthCheck() {
    
  }

  @Override
  protected Result check() throws Exception {
    return Result.healthy();
  }
}