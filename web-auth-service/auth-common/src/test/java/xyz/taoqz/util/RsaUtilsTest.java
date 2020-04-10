package xyz.taoqz.util;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author :almostTao
 * @date :Created in 2020/4/9 19:14
 */
public class RsaUtilsTest {

    /**
     * 测试RSAUtils工具类的使用
     *
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // rsa.pub文件名随意，例如：rsa.pub、rsa.io、pub.opp、rsapub.tyrf、rsa.txt、、、、
        String pubKeyPath = "d:\\yxq\\rsa.pub";
        String priKeyPath = "d:\\yxq\\rsa.pri";
//        // 明文
//        String secret = "czxy-ytjh-qqwd";
//        RsaUtils.generateKey(pubKeyPath,priKeyPath,secret);
//
//
//        System.out.println("ok");

        /**************解密**************/
        PublicKey publicKey = RsaUtils.getPublicKey(pubKeyPath);
        System.out.println("公钥:"+publicKey);


        PrivateKey privateKey = RsaUtils.getPrivateKey(priKeyPath);
        System.out.println("私钥："+privateKey);

    }
}
