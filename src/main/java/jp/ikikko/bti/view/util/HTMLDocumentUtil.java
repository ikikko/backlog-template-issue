package jp.ikikko.bti.view.util;

import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;

public class HTMLDocumentUtil {

	private HTMLDocumentUtil() {
	}

	/**
	 * {@link HTMLDocument} 中の {@code body} 要素を取得します。
	 */
	public static Element getBodyElement(final HTMLDocument document) {
		final Element body = findBodyElement(document.getDefaultRootElement());
		if (body == null) {
			throw new IllegalArgumentException(
					"Not found <body> tag in given document.");
		}

		return body;
	}

	private static Element findBodyElement(final Element element) {
		if (element.getName().equals("p")) {
			return element.getElement(0);
		}

		for (int i = 0; i < element.getElementCount(); i++) {
			final Element child = findBodyElement(element.getElement(i));
			if (child != null) {
				return child;
			}
		}

		return null;
	}

}
