package org.testsigma.mockserver.service;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.testsigma.mockserver.model.Configuration;
import org.testsigma.mockserver.repository.ConfigurationRepository;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @Lazy}))
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;
    private final BeanNameUrlHandlerMapping beanNameHandlerMapping;

    public Configuration save(Configuration mockServerConfiguration) {
        String tenantId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.debug("Extracted Tenant ID: {}", tenantId);
        mockServerConfiguration.setTenantId(tenantId);
        return configurationRepository.save(mockServerConfiguration);
    }

    @Cacheable(cacheNames = "uriCache", key = "@cacheKeyService.getCacheKey('uri' , #request.requestURI, 'method', #request.method)")
    public Configuration getMockConfiguration(HttpServletRequest request, String body) {
        log.debug("Get Mock Configuration By Request {}", request);

        // Extract tenant ID from the security context
        String tenantId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.debug("Extracted Tenant ID: {}", tenantId);

        // prepare arguments
        String uri = request.getRequestURI();
        String method = request.getMethod();

        return configurationRepository.findByUriPathAndMethodAndTenantId(uri, method, tenantId);
    }
}
