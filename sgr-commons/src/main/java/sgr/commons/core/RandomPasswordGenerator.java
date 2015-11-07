package sgr.commons.core;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author dawbes
 */
public class RandomPasswordGenerator
{
   private static final char[] DEFAULT_CODEC = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
         .toCharArray();
   private static final int LENGHT = 6;

   private static Random random = new SecureRandom();

   public static String generate()
   {
      byte[] verifierBytes = new byte[LENGHT];
      random.nextBytes(verifierBytes);
      return getAuthorizationCodeString(verifierBytes);
   }

   private static String getAuthorizationCodeString(byte[] verifierBytes)
   {
      char[] chars = new char[verifierBytes.length];
      for (int i = 0; i < verifierBytes.length; i++)
      {
         chars[i] = DEFAULT_CODEC[((verifierBytes[i] & 0xFF) % DEFAULT_CODEC.length)];
      }
      return new String(chars);
   }
}
