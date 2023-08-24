package com.boot.dandelion.e.cigarette.core;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@SpringBootTest
class ECigaretteCoreApplicationTests {

    @Test
    void contextLoads() {
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();



        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }


         String root = "/upload";


         String img = "/img";

         String http = "http://";

         String path = http+localHost.getHostAddress()+":8082"+root+img+"/32"+"/1.png";

        List<String> list = new ArrayList<>();
        list.add(path);

        String s = JSON.toJSONString(list);

        System.out.println(s);


        System.out.println("localhost: " + localHost);
        System.out.println("getHostAddress:  " + localHost.getHostAddress());
        System.out.println("getHostName:  " + localHost.getHostName());
    }

    @Test
    void test2() throws URISyntaxException {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                String name = intf.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipaddress = inetAddress.getHostAddress().toString();
                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                                ip = ipaddress;
                                System.out.println(ipaddress);
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            System.out.println("获取ip地址异常");
            ip = "127.0.0.1";
            ex.printStackTrace();
        }
        System.out.println("IP:"+ip);
    }

}
