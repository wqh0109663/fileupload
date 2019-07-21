package top.show.upload.exception;
/**
 * @author 吴启欢
 * @version 1.0
 * @date 19-7-21 下午9:18
 */
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}