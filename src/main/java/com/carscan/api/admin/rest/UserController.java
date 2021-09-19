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
import com.carscan.exception.ErrorBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "carscans/v1/user")
@Api(tags = "User Management", description = "Everthing about User management")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create a new user.", notes = "API to create a new User")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = UserDTO.class),
			@ApiResponse(code = 400, message = "Invalid Input", response = ErrorBody.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorBody.class) })
	public UserDTO createUser(
			@ApiParam(name = "Request Body", value = "User object that needs to be added", required = true) @RequestBody @Valid UserCreationRequest userCreationRequest) {
		return new UserDTO(this.userService.createUser(userCreationRequest));
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update an existing user.", notes = "API to update an exisitng user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = UserDTO.class),
			@ApiResponse(code = 400, message = "Invalid Input", response = ErrorBody.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorBody.class) })
	public UserDTO updateUser(
			@ApiParam(name = "Request Body", value = "User object that needs to be updated", required = true) @RequestBody @Valid UserUpdateRequest userUpdateRequest) {

		return new UserDTO(this.userService.updateUser(userUpdateRequest));
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Return all existing user.", notes = "API to return all user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = AllUserResponse.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorBody.class) })
	public AllUserResponse getAllUser() {
		return new AllUserResponse(this.userService.getAllUser());
	}

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Return a specific user ", notes = "API to get a specific user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = UserDTO.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorBody.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorBody.class) })
	public UserDTO getUserById(
			@ApiParam(name = "id", value = "Id of User to be return", required = true) @PathVariable("id") long id) {
		return new UserDTO(this.userService.getUser(id));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete a existing user ", notes = "API to delete a specific user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Boolean.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorBody.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorBody.class) })
	public ResponseEntity<Boolean> delete(
			@ApiParam(name = "id", value = "Id of User to be deleted", required = true) @PathVariable("id") long id) {
		this.userService.deleteUser(id);
		return ResponseEntity.ok().body(true);
	}
}