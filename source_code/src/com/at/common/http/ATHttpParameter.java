package com.at.common.http;

/**
 * A data class representing HTTP Post parameter
 * 
 * @author lichs_000
 */
public class ATHttpParameter implements java.io.Serializable, Comparable {
	String name;
	String value;
	private static final long serialVersionUID = -8708108746980739212L;

	public ATHttpParameter(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + value.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (obj instanceof ATHttpParameter) {
			ATHttpParameter that = (ATHttpParameter) obj;
			return this.name.equals(that.name) && this.value.equals(that.value);
		}
		return false;
	}

	public int compareTo(Object o) {
		int compared;
		ATHttpParameter that = (ATHttpParameter) o;
		compared = name.compareTo(that.name);
		if (0 == compared) {
			compared = value.compareTo(that.value);
		}
		return compared;
	}
}
