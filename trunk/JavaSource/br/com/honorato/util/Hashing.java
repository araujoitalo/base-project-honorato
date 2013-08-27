package br.com.honorato.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.honorato.exception.EncriptException;

public class Hashing {

	public static String MD5 = "MD5";

	public static String toMD5Hashing(String strToMD5)throws EncriptException {

		return Hashing.toHashing(strToMD5, Hashing.MD5);

	}	

	public static String toHashing(String strToHash, String type)throws EncriptException {

		switch (type) {
		case "MD5":
			break;
		default:
			//TODO: BUNDLE
			throw new SecurityException("Não foi possível criptografar. Tipo de criptografia inválido");
		}

		MessageDigest md;
		try {
			md = MessageDigest.getInstance(type);
			md.update(strToHash.getBytes());

			byte byteData[] = md.digest();

			//convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			//convert the byte to hex format method 2
			StringBuffer hexString = new StringBuffer();
			for (int i=0;i<byteData.length;i++) {
				String hex=Integer.toHexString(0xff & byteData[i]);
				if(hex.length()==1) hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();

		} catch (NoSuchAlgorithmException | NullPointerException e) {
			// TODO Bundle
			if (e instanceof NullPointerException) {
				throw new EncriptException("EncriptException","Erro ao tentar encriptar string nula");
			}else if(e instanceof NoSuchAlgorithmException){
				throw new EncriptException("EncriptException","Erro ao tentar encriptar string com algoritimo inválido");	
			}
		}
		
		return null;

	}
}