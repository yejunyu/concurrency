package test;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * @author: YeJunyu
 * @description:
 * @email: yyyejunyu@gmail.com
 * @date: 2020/11/6
 */
@JSONType(seeAlso = {ARequest.class, BRequest.class})
public interface Request {

    String getUrl();

    String getName();

}
