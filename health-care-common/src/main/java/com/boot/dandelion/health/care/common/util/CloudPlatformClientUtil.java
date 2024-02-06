package com.boot.dandelion.health.care.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CloudPlatformClientUtil {
    private static final String SERVER_HOST = "www.sdhtjr.com";
    private static final int SERVER_PORT = 5858;

    public static String sendCommand(String command) throws IOException {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT)) {
            // 获取输出流
            OutputStream outputStream = socket.getOutputStream();
            // 使用gb2312字符集编码指令
            String encodedCommand = new String(command.getBytes("gb2312"));
            // 发送指令
            outputStream.write(encodedCommand.getBytes());
            // 刷新输出流
            outputStream.flush();

            // 接收返回数据
            return receiveData(socket);
        }
    }

    private static String receiveData(Socket socket) throws IOException {
        // 获取输入流
        InputStream inputStream = socket.getInputStream();
        // 读取返回数据
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        // 使用gb2312字符集解码返回数据
        return new String(byteArrayOutputStream.toByteArray(), "gb2312");
    }

    public static String handleResponse(String responseData) {
        if (responseData.startsWith("cdb9")) {
            responseData = responseData.substring(4);
        } else if (responseData.startsWith("cda5")) {
            responseData = responseData.substring(4);
        } else if (responseData.startsWith("cdb8")) {
            responseData = responseData.substring(4);
        } else if (responseData.startsWith("cdg2")) {
            responseData = responseData.substring(4);
        } else if (responseData.startsWith("cda9")) {
            responseData = responseData.substring(4);
        } else if (responseData.startsWith("cdac")) {
            responseData = responseData.substring(4);
        } else if (responseData.startsWith("cda8")) {
            responseData = responseData.substring(4);
        } else if (responseData.startsWith("cdaf")) {
            responseData = responseData.substring(4);
        }else if (responseData.startsWith("cdae")) {
            responseData = responseData.substring(4);
        }
        return responseData;
    }
}
