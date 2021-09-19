package com.carscan.api.admin.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.carscan.api.admin.entity.User;
import com.carscan.api.admin.model.UserCreationRequest;
import com.carscan.api.admin.model.UserDTO;
import com.carscan.api.admin.model.UserUpdateRequest;
import com.carscan.api.admin.repository.UserRepository;
import com.carscan.exception.ResourceNotFoundException;
import com.carscan.utility.DateUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

	private final UserRepository userRepository;
	public User createUser(UserCreationRequest userCreationRequest) {
		log.info("===========User Creation ===========", userCreationRequest);
		User user = new User(userCreationRequest);

		return this.save(user);
	}

	public User updateUser(UserUpdateRequest userUpdateRequest) {
		log.info("===========User Update ======={} ", userUpdateRequest);
		User user = this.getUser(userUpdateRequest.getId());

		BeanUtils.copyProperties(userUpdateRequest, user);

        LocalDate dob= userUpdateRequest.getDob() != null ? DateUtils.fromString(userUpdateRequest.getDob()): null;

		user.setDob(dob);
		return this.save(user);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User getUser(long id) throws ResourceNotFoundException {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(" User id "+id +" not found"));
	}
	
	public List<UserDTO> getAllUser() {
		return userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
							  .stream().map(UserDTO::new)
							  .collect(Collectors.toList());
	}

	public void deleteUser(long id) {
		log.info("===========User Delete ======{}=====", id);
		this.getUser(id);
		userRepository.deleteById(id);
	}
}