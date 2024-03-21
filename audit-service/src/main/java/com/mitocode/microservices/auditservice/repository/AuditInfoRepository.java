package com.mitocode.microservices.auditservice.repository;

import com.mitocode.microservices.commonmodels.model.entity.AuditInfo;
import org.springframework.data.repository.CrudRepository;

public interface AuditInfoRepository extends CrudRepository<AuditInfo, String> {
}