package org.testsigma.mockserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.testsigma.mockserver.model.Configuration;

import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__({ @Autowired, @Lazy}))
public class DelayHandlerService {

    public void handleDelay(Map<String, String> parameters, Configuration configuration) {
        log.debug("Handling delay if required");

        Long delay = getDelayFromParameters(parameters);
        if (delay == null) {
            delay = configuration.getDelay();
        }
        if (delay == null) {
            log.debug("Delay is not configured");
            return;
        }
        doSleep(delay);
    }

    private Long getDelayFromParameters(Map<String, String> parameters) {
        log.debug("Handling delay parameters: {}", parameters);
        if (parameters == null || parameters.isEmpty()) {
            return null;
        }

        String delay = parameters.get("delay");
        if (delay == null || delay.isEmpty()) {
            return null;
        }
        return Long.valueOf(delay);
    }

    private void doSleep(Long delay) {
        log.debug("Sleeping for delay: {}", delay);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
