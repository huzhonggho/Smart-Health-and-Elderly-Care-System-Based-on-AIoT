package com.boot.dandelion.health.care.common.util;

public class ToCampUtils {

    /**
     * 下划线对应的ASCII
     */
    private static final byte ASCII_UNDER_LINE = 95;

    /**
     * 小写字母a的ASCII
     */
    private static final byte ASCII_a = 97;

    /**
     * 大写字母A的ASCII
     */
    private static final byte ASCII_A = 65;

    /**
     * 小写字母z的ASCII
     */
    private static final byte ASCII_z = 122;

    /**
     * 字母a和A的ASCII差距(a-A的值)
     */
    private static final byte ASCII_a_A = ASCII_a - ASCII_A;

    /**
     * 将参数b转换为大写字母,小写字母ASCII范围(97~122)
     * 0. 判断参数是否为小写字母
     * 1. 将小写字母转换为大写字母(减去32)
     */
    private static byte toUpper(byte b) {
        if (b >= ASCII_a && b <= ASCII_z) {
            return (byte) (b - ASCII_a_A);
        }
        return b;
    }

    /**
     * 交换下划线和其后面字符的下标
     * 将column从下划线命名方式转换成驼峰命名方式
     * 0. 找到`_`符号的ASCII码(95)对应的下标
     * 1. 将下划线的下标的下一个元素转换为大写字段(如果是小写字母的话)并放到下划线对应的下标
     * 2. 将下划线下标的下一个元素设置为下划线
     * 3. 返回数组
     *
     * @param column 字段名称
     */
    private static byte[] changeIdx(String column) {
        byte[] bytes = column.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == ASCII_UNDER_LINE) {
                if (i < bytes.length - 1) {
                    bytes[i] = toUpper(bytes[i + 1]);
                    bytes[i + 1] = ASCII_UNDER_LINE;
                    i++;
                }
            }
        }
        return bytes;
    }

    /**
     * 去除所有下划线
     * 0. 新创建一个数组
     * 1. 将所有非下划线字符都放入新数组中
     *
     * @param bytes 原始数组
     * @return 处理后的字节数组
     */
    private static byte[] removeUnderLine(byte[] bytes) {
        // 存放非下划线字符的数量
        int count = 0;
        for (byte b : bytes) {
            if (b == ASCII_UNDER_LINE) {
                continue;
            }
            count++;
        }
        byte[] nBytes = new byte[count];
        count = 0;
        for (byte b : bytes) {
            if (b == ASCII_UNDER_LINE) {
                continue;
            }
            nBytes[count] = b;
            count++;
        }
        return nBytes;
    }

    /**
     * 将下划线命名转换驼峰式命名
     * 0. 转换成大写,交换下标
     * 1. 去除所有下划线
     *
     * @param column 原始字段
     * @return 新字段
     */
    public static String change(String column) {
        byte[] bytes = changeIdx(column);
        bytes = removeUnderLine(bytes);
        return new String(bytes);
    }

    public static void main(String[] args) {
        //打印结果: userId
        System.out.println(change("user_id"));
        //打印结果: helloWorldToLuckyMan
        System.out.println(change("hello_world_to_lucky_man"));
    }
}
