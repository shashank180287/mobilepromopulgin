package com.mobile.tool.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
		@NamedQuery(name=SubCategoryDimension.FIND_BY_SERVICE_CODE_AND_SUB_CAT, query="from SubCategoryDimension where serviceType.code= :serviceCode and name= :subCatName"),
		@NamedQuery(name=SubCategoryDimension.FIND_ALL_BY_SERVICE_CODE, query="from SubCategoryDimension where serviceType.code= :serviceCode")
})
@Table(name="sub_category_dimension")
public class SubCategoryDimension {

	public static final String FIND_BY_SERVICE_CODE_AND_SUB_CAT = "findByServiceTypeCodeAndSubCategoryName";
	public static final String FIND_ALL_BY_SERVICE_CODE = "findAllByServiceTypeCode";
	private Integer subCategoryId;
	private ServiceType serviceType;
	private String name;
	private String code;
	
	public SubCategoryDimension() {
		super();
	}
	
	public SubCategoryDimension(ServiceType serviceType, String name, String code) {
		super();
		this.serviceType= serviceType;
		this.name = name;
		this.code = code;
	}

	@Column(name="code")
	public String getCode() {
		return code;
	}
	
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	@ManyToOne
    @JoinColumn(name = "service_type_id", nullable = false)
	public ServiceType getServiceType() {
		return serviceType;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}
}
