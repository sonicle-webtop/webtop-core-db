/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sonicle.webtop.core.bol;

import com.sonicle.webtop.core.jooq.core.tables.pojos.Users;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author gbulfon
 */
public class OUser extends Users {
	public static final String TYPE_USER = "U";
	public static final String TYPE_GROUP = "G";
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(getDomainId())
				.append(getUserId())
				.append(getUserUid())
				.toString();
	}
}
