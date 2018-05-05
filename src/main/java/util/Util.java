package util;

import org.apache.commons.codec.digest.DigestUtils;

public class Util {
	private static final String SALT="我学java";
		
		/**封装密码加密算法*/
		public static String saltMd5(String data){
			return DigestUtils.md5Hex(data+SALT);
	
	}

}
