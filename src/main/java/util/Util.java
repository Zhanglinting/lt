package util;

import org.apache.commons.codec.digest.DigestUtils;

public class Util {
	private static final String SALT="��ѧjava";
		
		/**��װ��������㷨*/
		public static String saltMd5(String data){
			return DigestUtils.md5Hex(data+SALT);
	
	}

}
