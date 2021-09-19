package com.carscan.api.admin.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class UserUpdateRequest extends UserRequest{

	@NotNull(message = "id is required")
	private Long id;
}
