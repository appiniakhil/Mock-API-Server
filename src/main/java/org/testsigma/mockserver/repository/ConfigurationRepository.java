package org.testsigma.mockserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.testsigma.mockserver.model.Configuration;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
        Configuration findByUriPathAndMethodAndTenantId(String uriPath, String method, String tenantId);
}
