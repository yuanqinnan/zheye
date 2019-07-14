package com.yuanqn.admin.common.config;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import com.pugwoo.wooutils.lang.DateUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Date;

/**
 * @author:yuanqinnan
 * @date: 2019/7/13 14:20
 * @des:
 */
public class P6spySqlFormatConfigure implements MessageFormattingStrategy {

    /**
     * 过滤掉定时任务的 SQL
     */
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql) ? DateUtils.format(new Date(), DateUtils.FORMAT_STANDARD)
                + " | 耗时 " + elapsed + " ms | SQL 语句：" + StringUtils.LF + sql.replaceAll("[\\s]+", StringUtils.SPACE) + ";" : StringUtils.EMPTY;
    }
}
