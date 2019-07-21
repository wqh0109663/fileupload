package top.show.upload.exception;
/**
 * @author 吴启欢
 * @version 1.0
 * @date 19-7-21 下午9:20
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
