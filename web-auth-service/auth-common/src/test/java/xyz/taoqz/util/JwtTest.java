package xyz.taoqz.util;

import org.junit.Test;
import xyz.taoqz.entity.UserInfo;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author :almostTao
 * @date :Created in 2020/4/9 19:18
 */
public class JwtTest {


    private static final String pubKeyPath = "D:\\rsa\\rsa.pub";

    private static final String priKeyPath = "D:\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    /**
     * 根据 secret生成公钥和私钥到指定的路径文件中
     * @throws Exception
     */
    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    /**
     * before 会在执行test之前执行,会提取公钥和私钥赋值给对象
     * @throws Exception
     */
//    @Before
    @Test
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }


    /**
     * 根据载荷信息生成token
     * @throws Exception
     */
    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20, "jack"),
                privateKey, 5);
        System.out.println("token = " + token);
    }

    /**
     * 跟据token解析
     * @throws Exception
     */
    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU4NjQzMjE2Mn0.dsI_MQe_uCU2whYv9z1cYrdDeigO15QZcjgweCxB_EhwCbZ1U5t9IDLqLgDxo7x6QpbLPnuliGoWRdScQSFSko4w61FU2MsnPfItcAXxxV-eT-jnhducDdkP8ctCoxskV3t33f6hCBzpRMSc7St9f7rWk5GSen8rHj2bF-QHKyI";
        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }

}
