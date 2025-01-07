package org.testsigma.mockserver.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testsigma.mockserver.model.Configuration;
import org.testsigma.mockserver.service.ConfigurationService;
@Log4j2
@RestController
@RequestMapping("/api/mock")
@RequiredArgsConstructor(onConstructor = @__({ @Autowired, @Lazy}))
public class ConfigurationController {
    private final ConfigurationService configurationService;

    @PostMapping("/config")
    public Configuration create(@RequestBody Configuration config) {
        log.debug("Received the request to save mock server config {}", config);
        return configurationService.save(config);
    }

}
