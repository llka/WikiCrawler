package by.ilka.TestTaskForDevadminCrawler.exception;

/**
 * Here could be your advertisement +375 29 3880490
 */
public class ServiceException extends Exception {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
