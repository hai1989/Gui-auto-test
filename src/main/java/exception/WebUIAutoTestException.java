package exception;

/**
 * @Author: zhaowei
 * @Date: 2019/4/15 3:37 PM
 * @Version 1.0
 */
public class WebUIAutoTestException extends RuntimeException{
    public WebUIAutoTestException() {
    }

    public WebUIAutoTestException(String message) {
        super(message);
    }

    public WebUIAutoTestException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebUIAutoTestException(Throwable cause) {
        super(cause);
    }

}
