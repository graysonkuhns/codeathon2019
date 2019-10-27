package edu.ucmo.devet.jobworker.github.api;

/**
 * Repository language retrieval exception.
 *
 * @author Grayson Kuhns
 */
public class LanguageRetrievalException extends RuntimeException {

  /**
   * Constructor.
   *
   * @param cause The cause of the failure.
   */
  public LanguageRetrievalException(final Throwable cause) {
    super(cause);
  }
}
