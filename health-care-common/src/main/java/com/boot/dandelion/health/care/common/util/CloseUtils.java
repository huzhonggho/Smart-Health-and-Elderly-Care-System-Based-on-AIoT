package com.boot.dandelion.health.care.common.util;

import java.io.Closeable;

/**
 * @ClassName CloseUtil
 * @Description 用于关闭各种连接，缺啥补啥
 * @Author shr
 * @Date 2022/07/19
 */
public class CloseUtils {
    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                // 静默关闭
            }
        }
    }

    public static void close(AutoCloseable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                // 静默关闭
            }
        }
    }
}
