/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: MessNullPoint
 * Author:   11580
 * Date:     2019/10/2 0002 16:27
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.exception;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/10/2 0002
 * @since 1.0.0
 */
public class MessNullPoint extends RuntimeException {
    public MessNullPoint() {
        super("发布内容不能为空");
    }
}
 

