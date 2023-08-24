package com.boot.dandelion.health.care.common.util;

import org.springframework.core.io.ClassPathResource;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @ClassName DownloadUtils
 * @Description
 * @Author shr
 * @Date 2022/07/20
 */
public class DownloadUtils {

    public static void download(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response, String returnName) throws IOException {
        response.setContentType("application/octet-stream");
        // 保存的文件名，必须和页面编码一致
        returnName = response.encodeURL(new String(returnName.getBytes(), "iso8859-1"));
        response.addHeader("content-disposition","attachment;filename="+returnName);
        response.setContentLength(byteArrayOutputStream.size());
        // 获得输出流
        ServletOutputStream outputStream = response.getOutputStream();
        // 写到输出流
        byteArrayOutputStream.writeTo(outputStream);
        // 关闭
        byteArrayOutputStream.close();
        // 刷数据
        outputStream.flush();
    }

    public static void downloadTemplateExcel(HttpServletRequest request, HttpServletResponse response, String returnName) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("excelTemplate/"+returnName);
        InputStream inputStream = classPathResource.getInputStream();
        response.setContentType("application/octet-stream");
        OutputStream out = response.getOutputStream();
        returnName = response.encodeURL(new String(returnName.getBytes(), "iso8859-1"));
        response.setHeader("Content-Disposition", "attachment; filename="+returnName);
        int b = 0;
        byte[] buffer = new byte[1024*1024];
        while (b != -1) {
            b = inputStream.read(buffer);
            if(b!=-1) {
                out.write(buffer, 0, b);
            }
        }
        inputStream.close();
        out.close();
        out.flush();
    }
}
