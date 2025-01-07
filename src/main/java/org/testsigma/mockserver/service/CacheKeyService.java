package org.testsigma.mockserver.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class CacheKeyService {
    public String getCacheKey(String... components) {
        return String.join("_", components);
    }
}
