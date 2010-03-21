package jp.ikikko.bti.backlogapi.util;

public class StringUtil {

	public static String fill(final String s, int len, final char c) {
		final boolean left = len < 0;
		len = Math.abs(len);

		if (s.length() >= len) {
			return s;
		}

		final StringBuffer buf = new StringBuffer();
		len -= s.length();
		while (len > 0) {
			buf.append(c);
			len--;
		}

		if (left) {
			buf.append(s);
			return buf.toString();
		}
		return s + buf.toString();
	}

}
