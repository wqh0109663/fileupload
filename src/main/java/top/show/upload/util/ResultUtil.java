package top.show.upload.util;

import top.show.upload.constant.HttpCodeConstant;
import top.show.upload.vo.ResultVO;

/**
 * @author wqh
 * @date 2019-7-27
 */
public class ResultUtil {
    public static ResultVO success(Object object,String msg){
        return ResultVO.builder()
                .code(HttpCodeConstant.OK)
                .dataObject(object)
                .msg(msg).build();
    }

    public static ResultVO fail(Integer code,  String msg) {
        return ResultVO.builder()
                .msg(msg)
                .code(code)
                .build();
    }

}
