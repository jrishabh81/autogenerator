package org.utility.autogenerate.hob.helpers;

import java.util.*;


public class StringResolver {
	private Map<String, Locale> localeInfoMap = new HashMap<>();

	public String resolveString(String baseBundle, String key, String localeInfo) {
		if (baseBundle == null || baseBundle.isEmpty()){
			return key;
		}
		try {
			ResourceBundle resourceBundle = ResourceBundle.getBundle(baseBundle, getLocale(localeInfo));
			if (resourceBundle == null)
				return key;

			return resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			return key;
		}
	}

	public String resolveString(String key, String localeInfo) {
		return resolveString("login",key,localeInfo);
	}

	private Locale getLocale(String localeInfo) {
		localeInfoMap.put("en-GB", Locale.UK);
		localeInfoMap.put("en-US", Locale.US);
		localeInfoMap.put("fr-FR", Locale.FRANCE);
	    return localeInfoMap.get(localeInfo)!=null? localeInfoMap.get(localeInfo): Locale.ROOT;
	}
}
