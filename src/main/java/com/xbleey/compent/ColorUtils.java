/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: ColorUtils
 * Author:   11580
 * Date:     2019/10/14 0014 18:59
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.compent;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Random;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/10/14 0014
 * @since 1.0.0
 */
@Component
public class ColorUtils {
    private HashMap<Integer, String> colors;
    private Random random;

    public ColorUtils() {
        colors = new HashMap<>();
        random = new Random();

        colors.put(1, "#FFEFDB");
        colors.put(2, "#CD4F39");
        colors.put(3, "#66CD00");
        colors.put(4, "#00FFFF");
        colors.put(5, "#FF4040");
        colors.put(6, "#9B30FF");
        colors.put(7, "#DBDBDB");
        colors.put(8, "#FFF68F");
        colors.put(9, "#9AFF9A");
        colors.put(10, "#8B2500");
        colors.put(11, "#00B2EE");
    }

    public String getOneColor() {
        return colors.get(random.nextInt(11));
    }
}
 

