package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.AuditLog;
import com.example.demo.repo.AuditLogRepository;



@Service
public class AuditService {

	private final AuditLogRepository auditLogRepository;
	
	public AuditService(AuditLogRepository auditLogRepository) {
		this.auditLogRepository=auditLogRepository;
	}
	
	//This creates a new transaction always
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void log(String message) {
		auditLogRepository.save(new AuditLog(message));
	}
}
