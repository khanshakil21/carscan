package com.carscan.api.admin.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.carscan.api.admin.model.UserCreationRequest;
import com.carscan.utility.DateUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "USER_INFO")
public class User {
	public User(UserCreationRequest userCreationRequest) {
		BeanUtils.copyProperties(userCreationRequest, this);
			
			this.dob = userCreationRequest.getDob() != null  
					? DateUtils.fromString(userCreationRequest.getDob())
					: null;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1)
	private long id;

	@Column(nullable = false)
	private String fname;

	private String lname;

	private String mobileNumber;

	private String city;

	private LocalDate dob;
}