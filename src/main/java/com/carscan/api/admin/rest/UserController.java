package com.carscan.api.admin.rest;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.carscan.api.admin.model.AllUserResponse;
import com.carscan.api.admin.model.UserCreationRequest;
import com.carscan.api.admin.model.UserDTO;
import com.carscan.api.admin.model.UserUpdateRequest;
import com.carscan.api.admin.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "carscans/v1/user")
@Api(tags = { "User Management" })
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create a user.", notes = "Returns the newly created user")
	public UserDTO createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
		return new UserDTO(this.userService.createUser(userCreationRequest));
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update a user.", notes = "Returns updated user")
	public UserDTO updateUser(@RequestBody @Valid UserUpdateRequest userUpdateRequest) {

		return new UserDTO(this.userService.updateUser(userUpdateRequest));
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get a all user list", notes = "The User list")
	public AllUserResponse getAllUser() {
		return new AllUserResponse(this.userService.getAllUser());
	}

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get a user", notes = "The User")
	public UserDTO getUserById(@PathVariable("id") long id) {
		return new UserDTO(this.userService.getUser(id));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete a user ", notes = "Delete User")
	public ResponseEntity<Boolean> delete(@PathVariable("id") long id) {
		this.userService.deleteUser(id);
		return ResponseEntity.ok().body(true);
	}

}