package com.boot.dandelion.health.care.core.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName RsaProperties
 * @Description Ras私钥
 * @Author shr
 * @Date 2022/07/14
 */
@Data
@Component
public class RsaProperties {

    public static String rsaPublicKey;
    public static String rsaPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwgg" +
            "JdAgEAAoGBAJy8inzXLKRO9Pd78Z5NMSUzE4lrzjoR43egj9eJ6Jqg" +
            "BnHsREJjwU703AZrgpZEZYnO0D0pPUBNaH9S37z2D/NmZq3JAR7EUn" +
            "2SLu342wb8d1LBAUv92HCn4guWVvBAyBq3//pQwOTm1ZSS4fr/U4nB" +
            "PExTz5PMXIx0chCELWABAgMBAAECgYBc9xfNZHN+Ibb68/EkS/TSc" +
            "7Eq2V7A4stgsUOgPJRS4ofwGouCNVj7VBENXcdfCcKQMrMhE8z" +
            "YzxEkNCuxEOH0Nv+zdu+n/VXbgznbINJWU9p8STy/mjaLwnQDRY" +
            "J0vjHl30qk4CF+Sbj6Gy+SY7IcUFHFuNM8F0u8L1+YtrW/YQJBANCF" +
            "GNWLosJrK8iaRUR4AC4+JJPVlRQgcl5MHRKhKVOF3FnCz7NR8h" +
            "9SFMwJ/jacM0rQFBGMunfsmSO+MrtTYl8CQQDAbOtdVjI262xi" +
            "91mojJnqgp+VlkrPBO2G3FW3QG5bl3Zdf3hp3WNY+9xe77ZE3" +
            "edVFdP7E461zTvlTd2+OBmfAkEAmsCMo79T9bIP9nr3uU7N44P" +
            "fJZA3aY6DEpSuZusQfpoz5LH973l7ld8W7C7maaUXNAilPDkTEnm" +
            "XVb2MbRX/RQJBAJwzDi+6+5IHMvl3Dgm68Goj1IUp9dnshx+VFe" +
            "NmYu7omUW2ZrmQ48dk03MhJRTsCI/R4zyJmKvNSJSPy7NQeMMC" +
            "QA77CRSvw9yPCMEOc4z4zJpOcgPrlfX6qAuJ/u6vNBm+w/+V41" +
            "FQO2+5rCBaVHnDtzgHyAeP21fS2bY/96xaTVc=";

    @Value("${rsa.public.key}")
    public void setRsaPublicKey(String rsaPublicKey) {
        RsaProperties.rsaPublicKey = rsaPublicKey;
    }


}
