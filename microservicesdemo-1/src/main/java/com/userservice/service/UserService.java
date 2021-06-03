package com.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userservice.ValueObject.Department;
import com.userservice.ValueObject.ResponseTemplateVO;
import com.userservice.entity.User;
import com.userservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	public User saveUser(User user) {
		log.info("Inside the UserService save() method:");
		return userRepository.save(user);
	}

	public ResponseTemplateVO getUserWithDepartment(Long userId) {
		log.info("Inside the UserService getUserWithDepartment() method:");
		ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
		User user = userRepository.findByUserId(userId);
		Department departmentObj = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId(), Department.class);
		responseTemplateVO.setUser(user);
		responseTemplateVO.setDepartment(departmentObj);
		return responseTemplateVO;
	}
}
