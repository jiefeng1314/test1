package com.oscarbase;

import org.openqa.selenium.JavascriptExecutor;


import org.openqa.selenium.WebDriver;

public class ReadOnlyInputField {

	/**
	 * readonly text box or richtext box input, finding elements by element id.
	 * 
	 * @param elementId
	 *            the id of the element
	 * @param text
	 *            the text you want to input to element
	 * @throws RuntimeException
	 * @throws IllegalArgumentException
	 */
	public static void sendKeysById(WebDriver driver, String elementId, String text) {
		sendKeysByDOM(driver, "Id", elementId, text, 0);
	}

	/**
	 * readonly text box or richtext box input, finding elements by element
	 * name.
	 * 
	 * @param elementName
	 *            the name of the element
	 * @param text
	 *            the text you want to input to element
	 * @param elementIndex
	 *            the index of the elements shared the same name, begins with 0
	 * @throws RuntimeException
	 * @throws IllegalArgumentException
	 */
	public static void sendKeysByName(WebDriver driver, String elementName,
			String text, int elementIndex) {
		sendKeysByDOM(driver, "Name", elementName, text, elementIndex);
	}

	/**
	 * readonly text box or richtext box input, finding elements by element tag
	 * name.
	 * 
	 * @param elementTagName
	 *            the tag name of the element
	 * @param text
	 *            the text you want to input to element
	 * @param elementIndex
	 *            the index of the elements shared the same tag name, begins
	 *            with 0
	 * @throws RuntimeException
	 * @throws IllegalArgumentException
	 */
	public static void sendKeysByTagName(WebDriver driver, String elementTagName,
			String text, int elementIndex) {
		sendKeysByDOM(driver, "TagName", elementTagName, text, elementIndex);
	}

	/**
	 * readonly text box or richtext box input.
	 * 
	 * @param by
	 *            the attribute of the element, default support is
	 *            TagName/Name/Id
	 * @param byValue
	 *            the attribute value of the element
	 * @param text
	 *            the text you want to input to element
	 * @param index
	 *            the index of the elements shared the same attribute value
	 * @throws RuntimeException
	 * @throws IllegalArgumentException
	 */
	protected static void sendKeysByDOM(WebDriver driver, String by, String byValue,
			String text, int index) {
		String js = null;
		boolean isSucceed = false;
		
		if (by.equalsIgnoreCase("tagname")) {
			js = "document.getElementsByTagName('" + byValue + "')[" + index
					+ "].value='" + text + "'";
		} else if (by.equalsIgnoreCase("name")) {
			js = "document.getElementsByName('" + byValue + "')[" + index
					+ "].value='" + text + "'";
		} else if (by.equalsIgnoreCase("id")) {
			js = "document.getElementById('" + byValue + "').value='" + text
					+ "'";
		} else {
			throw new IllegalArgumentException(
					"only can find element by TagName/Name/Id");
		}

		try {
			
			((JavascriptExecutor)driver).executeScript(js);
			isSucceed = true;
		}  catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
