package br.com.honorato.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.honorato.exception.EncriptException;

public class PasswordUtil {

	private Pattern pattern;
	private Matcher matcher;

	private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$!&%])(?!.*\\s).{6,20}$";
	//"((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	private static final char[] ALL_CHARS = new char[62];  
    private static final Random RANDOM = new Random();  
  
    static {  
        for (int i = 48, j = 0; i < 123; i++) {  
            if (Character.isLetterOrDigit(i)) {  
                ALL_CHARS[j] = (char) i;  
                j++;  
            }  
        }  
    }      
    
	public PasswordUtil(){
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}

	/**
	 * Validate password with regular expression
	 * @param password password for validation
	 * @return true valid password, false invalid password
	 */
	public boolean validate(final String password){

		matcher = pattern.matcher(password);
		return matcher.matches();

	}
	
	public static String getRandomPassword(final int length) {  
        final char[] result = new char[length];  
        for (int i = 0; i < length; i++) {  
            result[i] = ALL_CHARS[RANDOM.nextInt(ALL_CHARS.length)];  
        }  
        return new String(result);  
    }  
  
    public static String getRandomPasswordMD5(final int length) {
    	
    	String out = null;
    	
        try {
			out = Hashing.toMD5Hashing(getRandomPassword(length));
		} catch (EncriptException e) {
			// TODO Auto-generated catch block
			//TODO: LOGAR E RECUPERAR DO BUNDLE
			e.printStackTrace();
		}  
        return out;
    }  
  
}
