package top.show.upload.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 * @date 2019-7-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultVO {
    private Integer code;
    private String msg;
    private Object dataObject;
}
