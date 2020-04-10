package xyz.taoqz.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :almostTao
 * @date :Created in 2020/3/20 9:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult {

    private Integer errno;
    private String errmsg;
    private Object data;

    public BaseResult(Integer errno, String errmsg) {
        this.errno = errno;
        this.errmsg = errmsg;
    }
}
