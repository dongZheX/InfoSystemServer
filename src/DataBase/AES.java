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
 * @desc AES ���ܹ�����
 */
public class AES {
	private final static String transferKey ="1097300052dz      ";
	 /**
	   * �����ü���
	   * @param content
	   * @return
	   * @throws Exception
	   */
	  public static String aesTransferEncrypt(String content) throws Exception {
	    return base64Encode(aesEncryptToBytes(content, transferKey));
	  }

	  /**
	   * �����ý���
	   * @param content
	   * @return
	   * @throws Exception
	   */
	  public static String aesTransferDncrypt(String encryptStr) throws Exception {
	    return aesDecryptByBytes(base64Decode(encryptStr), transferKey);
	  }

	  /**
	   * base 64 encode
	   * @param bytes �������byte[]
	   * @return ������base 64 code
	   */
	  private static String base64Encode(byte[] bytes) {
	    return new BASE64Encoder().encode(bytes);
	  }

	  /**
	   * base 64 decode
	   * @param base64Code �������base 64 code
	   * @return ������byte[]
	   * @throws Exception
	   */
	  private static byte[] base64Decode(String base64Code) throws Exception {
	    return new BASE64Decoder().decodeBuffer(base64Code);
	  }

	  /**
	   * AES����
	   * @param content �����ܵ�����
	   * @param encryptKey ������Կ
	   * @return ���ܺ��byte[]
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
	   * AES����
	   * @param encryptBytes �����ܵ�byte[]
	   * @param decryptKey ������Կ
	   * @return ���ܺ��String
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

