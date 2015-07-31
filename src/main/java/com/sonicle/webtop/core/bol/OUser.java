/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sonicle.webtop.core.bol;

import com.sonicle.commons.LangUtils;
import com.sonicle.webtop.core.jooq.tables.pojos.Users;
import java.util.Locale;
import java.util.TimeZone;
import org.joda.time.DateTimeZone;

/**
 *
 * @author gbulfon
 */
public class OUser extends Users {

	public Locale getLocale() {
		return LangUtils.languageTagToLocale(getLanguageTag());
	}
	
	public void setLocale(Locale locale) {
		setLanguageTag(locale.toString());
	}
	
	public DateTimeZone getTimeZone() {
		return DateTimeZone.forID(getTimezone());
	}
	
	public void setTimeZone(DateTimeZone tz) {
		setTimezone(tz.getID());
	}
}
