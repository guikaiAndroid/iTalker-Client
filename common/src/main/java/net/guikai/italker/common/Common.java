package net.guikai.italker.common;

/**
 * Description: 全局常量
 * Crete by Anding on 2020-02-15
 */
public class Common {
    /**
     * 一些不可变的永恒参数
     *  通常用于一些配置
     */
    public interface Constance {
        // 手机号的正则,11位手机号
        String REGEX_MOBILE = "[1][3,4,5,7,8][0-9]{9}$";

        // 基础的网络请求地址
        // 本地地址，需要自己配置为本地局域网电脑ip地址
         String API_URL = "http://192.168.1.2:8080/api/";

    }
}
