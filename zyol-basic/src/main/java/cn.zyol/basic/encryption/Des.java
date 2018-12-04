package cn.zyol.basic.encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

public class Des {

   // private final static String iv       = "01234567";
    
    private final static String iv       = "58671943";

    private final static String encoding = "utf-8";

    /**
     * 3DES加密
     * 
     * @param secretKey 密匙
     * @param plainText 普通文本
     * @return
     * @throws Exception
     */
    public static String encode(String secretKey, String plainText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
        return Base64.encode(encryptData);
    }

    /**
     * 3DES解密
     * 
     * @param secretKey 密匙
     * @param encryptText 加密文本
     * @return
     * @throws Exception
     */
    public static String decode(String secretKey, String encryptText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));
        return new String(decryptData, encoding);
    }

    public static void main(String[] args) throws Exception {
        try {
            // 密钥
            //String secretKey = "liuyunqiang@lx100$#365#$"; // 24个字符
            String secretKey = "4444444444444444444445555"; // 24个字符
            String content = "888888888888888888888";
            System.out.println("加密前：" + content);

            String encrypt = encode(secretKey, content);
            System.out.println("加密后：" + encrypt);

            String decrypt = decode(secretKey, encrypt);
            System.out.println("解密后：" + decrypt);
            // System.out.println("qqqq====="+SHAUtil.encode("11111111"));
        } catch (Exception e) {
            System.out.println("dd=====" + e);
        }
    }
}
