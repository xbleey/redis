/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: SHA1Utils
 * Author:   11580
 * Date:     2019/10/4 0004 19:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.compent;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;


/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/10/4 0004
 * @since 1.0.0
 */
public class SHA1Utils {
    public static String encodePassword(String psw) {
        if (StringUtils.isEmpty(psw)) {
            return null;
        } else {
            return DigestUtils.sha1Hex(psw);
        }
    }
}
 

