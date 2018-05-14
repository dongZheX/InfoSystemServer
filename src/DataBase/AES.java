package DataBase;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;;

/**
 * @version V1.0
 * @desc AES 加密工具类
 */
public class AES {
	private final static String transferKey ="1097300052dz      ";
	 /**
	   * 传输用加密
	   * @param content
	   * @return
	   * @throws Exception
	   */
	  public static String aesTransferEncrypt(String content) throws Exception {
	    return base64Encode(aesEncryptToBytes(content, transferKey));
	  }

	  /**
	   * 传输用解密
	   * @param content
	   * @return
	   * @throws Exception
	   */
	  public static String aesTransferDncrypt(String encryptStr) throws Exception {
	    return aesDecryptByBytes(base64Decode(encryptStr), transferKey);
	  }

	  /**
	   * base 64 encode
	   * @param bytes 待编码的byte[]
	   * @return 编码后的base 64 code
	   */
	  private static String base64Encode(byte[] bytes) {
	    return new BASE64Encoder().encode(bytes);
	  }

	  /**
	   * base 64 decode
	   * @param base64Code 待解码的base 64 code
	   * @return 解码后的byte[]
	   * @throws Exception
	   */
	  private static byte[] base64Decode(String base64Code) throws Exception {
	    return new BASE64Decoder().decodeBuffer(base64Code);
	  }

	  /**
	   * AES加密
	   * @param content 待加密的内容
	   * @param encryptKey 加密密钥
	   * @return 加密后的byte[]
	   * @throws Exception
	   */
	  private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
	    KeyGenerator kgen = KeyGenerator.getInstance("AES");
	    SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
	    secureRandom.setSeed(encryptKey.getBytes());
	    kgen.init(128, secureRandom);
	    Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.ENCRYPT_MODE, kgen.generateKey());
	    return cipher.doFinal(content.getBytes("UTF-8"));
	  }

	  /**
	   * AES解密
	   * @param encryptBytes 待解密的byte[]
	   * @param decryptKey 解密密钥
	   * @return 解密后的String
	   * @throws Exception
	   */
	  private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
	    KeyGenerator kgen = KeyGenerator.getInstance("AES");
	    SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
	    secureRandom.setSeed(decryptKey.getBytes());
	    kgen.init(128, secureRandom);
	    Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.DECRYPT_MODE,kgen.generateKey());
	    byte[] decryptBytes = cipher.doFinal(encryptBytes);
	    return new String(decryptBytes,"UTF-8");
	  }
	}

