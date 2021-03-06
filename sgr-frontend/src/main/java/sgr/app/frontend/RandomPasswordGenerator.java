package sgr.app.frontend;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author dawbes89
 */
public class RandomPasswordGenerator implements Serializable
{

	private static final long serialVersionUID = 3763153537818133347L;

	private static final char[] DEFAULT_CODEC = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

	private static final int LENGHT = 6;

	private static final Random random = new SecureRandom();

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
