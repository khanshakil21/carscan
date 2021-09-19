package com.carscan.api.admin.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class UserRequest {

	@NotNull(message = "fname is required")
	private String fname;

	private String lname;
	
	@ApiModelProperty(
			  value = "Mobile Number the user",
			  name = "Mobile Number",
			  dataType = "String",
			  example = "9999999999")
	private String mobileNumber;
	
	private String city;

	@ApiModelProperty(
			  value = "DOB  of the user",
			  name = "DOB",
			  dataType = "String",
			  example = "1995-12-12")
	private String dob;	   
}
