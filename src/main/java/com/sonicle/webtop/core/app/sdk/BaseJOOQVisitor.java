/*
 * Copyright (C) 2019 Sonicle S.r.l.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License version 3 as published by
 * the Free Software Foundation with the addition of the following permission
 * added to Section 15 as permitted in Section 7(a): FOR ANY PART OF THE COVERED
 * WORK IN WHICH THE COPYRIGHT IS OWNED BY SONICLE, SONICLE DISCLAIMS THE
 * WARRANTY OF NON INFRINGEMENT OF THIRD PARTY RIGHTS.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, see http://www.gnu.org/licenses or write to
 * the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301 USA.
 *
 * You can contact Sonicle S.r.l. at email address sonicle[at]sonicle[dot]com
 *
 * The interactive user interfaces in modified source and object code versions
 * of this program must display Appropriate Legal Notices, as required under
 * Section 5 of the GNU Affero General Public License version 3.
 *
 * In accordance with Section 7(b) of the GNU Affero General Public License
 * version 3, these Appropriate Legal Notices must retain the display of the
 * Sonicle logo and Sonicle copyright notice. If the display of the logo is not
 * reasonably feasible for technical reasons, the Appropriate Legal Notices must
 * display the words "Copyright (C) 2019 Sonicle S.r.l.".
 */
package com.sonicle.webtop.core.app.sdk;

import com.github.rutledgepaulv.qbuilders.nodes.AndNode;
import com.github.rutledgepaulv.qbuilders.nodes.ComparisonNode;
import com.github.rutledgepaulv.qbuilders.nodes.OrNode;
import com.github.rutledgepaulv.qbuilders.operators.ComparisonOperator;
import com.github.rutledgepaulv.qbuilders.visitors.AbstractVoidContextNodeVisitor;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;

/**
 *
 * @author malbinola
 */
public abstract class BaseJOOQVisitor extends AbstractVoidContextNodeVisitor<Condition> {
	protected final boolean ignoreCase;
	
	public BaseJOOQVisitor() {
		this(false);
	}
	
	public BaseJOOQVisitor(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}
	
	abstract protected Condition toCondition(String fieldName, ComparisonOperator operator, Collection<?> values, ComparisonNode node);

	@Override
	protected Condition visit(AndNode node) {
		//Condition condition = DSL.and(conditions); in jOOQ 3.6+
		Condition result = DSL.trueCondition();
		List<Condition> conditions = node.getChildren().stream()
				.map(this::visitAny).collect(Collectors.toList());
		for (Condition condition : conditions) {
			result = result.and(condition);
		}
		return result;
	}

	@Override
	protected Condition visit(OrNode node) {
		//Condition condition = DSL.and(conditions); in jOOQ 3.6+
		Condition result = DSL.trueCondition();
		List<Condition> conditions = node.getChildren().stream()
				.map(this::visitAny).collect(Collectors.toList());
		for (Condition condition : conditions) {
			result = result.or(condition);
		}
		return result;
	}
	
	@Override
	protected Condition visit(ComparisonNode node) {
		String fieldName = node.getField().asKey();
		ComparisonOperator operator = node.getOperator();
		Collection<?> values = node.getValues().stream().collect(Collectors.toList());
		return toCondition(fieldName, operator, values, node);
	}
	
	protected <T> Condition defaultCondition(Field<T> field, ComparisonOperator operator, Collection<?> values) {
		if (ComparisonOperator.EQ.equals(operator)) {
			if (hasStringType(field)) {
				String value = (String)single(values);
				if (valueContainsWildcard(value)) {
					return ignoreCase ? field.likeIgnoreCase(valueToLikePattern(value)) : field.like(valueToLikePattern(value));
				} else if (ignoreCase) {
					return field.equalIgnoreCase(value);
				}
			}
			return field.equal(field.getDataType().convert(single(values)));

		} else if (ComparisonOperator.NE.equals(operator)) {
			if (hasStringType(field)) {
				String value = (String)single(values);
				if (valueContainsWildcard(value)) {
					return ignoreCase ? field.notLikeIgnoreCase(valueToLikePattern(value)) : field.notLike(valueToLikePattern(value));
				} else if (ignoreCase) {
					return field.notEqualIgnoreCase(value);
				}
			}
			return field.notEqual(field.getDataType().convert(single(values)));
			
		} else if (ComparisonOperator.EX.equals(operator)) {
			throw new UnsupportedOperationException("Operator not supported: " + operator);
			
		} else if (ComparisonOperator.GT.equals(operator)) {
			return field.greaterThan(field.getDataType().convert(single(values)));
			
		} else if (ComparisonOperator.LT.equals(operator)) {
			return field.lessThan(field.getDataType().convert(single(values)));
			
		} else if (ComparisonOperator.GTE.equals(operator)) {
			return field.greaterOrEqual(field.getDataType().convert(single(values)));
			
		} else if (ComparisonOperator.LTE.equals(operator)) {
			return field.lessOrEqual(field.getDataType().convert(single(values)));
			
		} else if (ComparisonOperator.IN.equals(operator)) {
			return field.in(field.getDataType().convert(values));
			
		} else if (ComparisonOperator.NIN.equals(operator)) {
			return field.notIn(field.getDataType().convert(values));
			
		} else if (ComparisonOperator.RE.equals(operator)) {
			return field.likeRegex((String)single(values));
			
		} else if (ComparisonOperator.SUB_CONDITION_ANY.equals(operator)) {
			throw new UnsupportedOperationException("Operator not supported: " + operator);
			
		} else {
			throw new UnsupportedOperationException("Operator not supported: " + operator);
		}
	}
	
	protected boolean hasStringType(Field<?> field) {
		return field.getType().equals(java.lang.String.class);
	}
	
	protected String singleAsString(Collection<?> values) {
		return (String)single(values);
	}
	
	protected String valueToSmartLikePattern(String value) {
		if (valueContainsWildcard(value)) {
			return valueToLikePattern(value);
		} else {
			return valueToLikePattern("*" + value + "*");
		}
	}
	
	protected boolean valueContainsWildcard(String value) {
		int escapedAsterisks = StringUtils.countMatches(value, "\\*");
		int asterisks = StringUtils.countMatches(value, "*");
		return asterisks > escapedAsterisks;
	}
	
	protected String valueToLikePattern(String value) {
		value = StringUtils.replace(value, "*", "%");
		value = StringUtils.replace(value, "\\*", "*");
		return value;
	}
	
	/*
	protected static class DefaultNormalizer implements Function<Object, Object> {

		@Override
		public Object apply(Object t) {
			if (o instanceof Instant) {
				
			}
			return o;
		}
	}
	*/
}
