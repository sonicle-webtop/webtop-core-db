/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sonicle.webtop.core.bol;

import com.sonicle.commons.LangUtils;
import com.sonicle.webtop.core.jooq.tables.pojos.Users;
import java.util.Locale;

/**
 *
 * @author gbulfon
 */
public class OUser extends Users {

	public OUser() {
		
	}
	
	public Locale getLocale() {
		return LangUtils.languageTagToLocale(getLanguageTag());
	}
	
	public void setLocale(Locale locale) {
		setLanguageTag(locale.toString());
	}
}
