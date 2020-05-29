package yu.mthgh123.booksmall.common;

public class BooksMallException extends RuntimeException {

    public BooksMallException() {
    }

    public BooksMallException(String message) {
        super(message);
    }

    /**
     * 丢出一个异常
     *
     * @param message
     */
    public static void fail(String message) {
        throw new BooksMallException(message);
    }

}
