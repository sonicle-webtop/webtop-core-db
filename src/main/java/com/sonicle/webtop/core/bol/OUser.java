/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sonicle.webtop.core.bol;

import com.sonicle.commons.LangUtils;
import com.sonicle.webtop.core.jooq.tables.pojos.Users;
import java.util.Locale;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTimeZone;

/**
 *
 * @author gbulfon
 */
public class OUser extends Users {
	public static final String USER_TYPE = "U";
	public static final String GROUP_TYPE = "G";

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
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(getDomainId())
				.append(getUserId())
				.append(getUserUid())
				.append(getRoleUid())
				.toString();
	}
}
