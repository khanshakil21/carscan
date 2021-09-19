package com.carscan.api.admin.model;

import org.springframework.beans.BeanUtils;

import com.carscan.api.admin.entity.User;
import com.carscan.utility.DateUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {

	public UserDTO(User user) {
		BeanUtils.copyProperties(user, this);
		if (user.getDob() != null)
			this.dob = DateUtils.convertDateToString(user.getDob());
		
	}
	
	private long id;
	
	private String fname;

	private String lname;

	private String mobileNumber;

	private String city;

	private String dob;
}
