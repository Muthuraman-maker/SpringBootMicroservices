package com.departmentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.departmentservice.entity.Department;
import com.departmentservice.repository.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public Department saveDepartment(Department department) {
		log.info("Inside save department of DepartmentService....");
		return departmentRepository.save(department);
	}
	
	public Department findDepartmentById(Long departmentId) {
		log.info("Inside find Department of DepartmentService");
		return departmentRepository.findByDepartmentId(departmentId);
	}
}
