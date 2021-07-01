/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: MessageDao
 * Author:   11580
 * Date:     2019/10/2 0002 15:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.dto;

import com.xbleey.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/10/2 0002
 * @since 1.0.0
 */
@Repository
public interface MessageDao extends JpaRepository<Message, Integer> {
    @Query(value = "select * from message order by date desc limit 10", nativeQuery = true)
    public List<Message> findTen();

    @Query(value = "select * from message where date like ?1 order by date desc", nativeQuery = true)
    public List<Message> getListToday(String startDate);

    @Override
    @Query(value = "select * from message order by date desc", nativeQuery = true)
    public List<Message> findAll();

    @Query(value = "select count(id) from message", nativeQuery = true)
    public Integer getNums();
}