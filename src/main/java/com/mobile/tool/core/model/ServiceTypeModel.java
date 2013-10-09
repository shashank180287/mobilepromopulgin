package com.mobile.tool.core.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

public class ServiceTypeModel {

	@JsonProperty
	private String code;
	
	@JsonProperty
	private String name;

	public ServiceTypeModel() {
		super();
	}
	
	public ServiceTypeModel(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceTypeModel)) return false;

        ServiceTypeModel serTypeModel = (ServiceTypeModel) o;
        return new EqualsBuilder()
                .append(this.code, serTypeModel.code)
                .append(this.name, serTypeModel.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.code)
                .append(this.name)
                .hashCode();
    }

}
