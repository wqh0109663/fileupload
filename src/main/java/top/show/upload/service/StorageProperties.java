package top.show.upload.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * @author 吴启欢
 * @version 1.0
 * @date 19-7-21 下午9:20
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
