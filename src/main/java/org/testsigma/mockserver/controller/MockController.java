package org.testsigma.mockserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.testsigma.mockserver.model.Configuration;
import org.testsigma.mockserver.model.ResponseHeader;
import org.testsigma.mockserver.service.ConfigurationService;
import org.testsigma.mockserver.service.DatafakerFormatConverterService;
import org.testsigma.mockserver.service.DelayHandlerService;
import org.testsigma.mockserver.service.MockResponseBodyProcessorService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @Lazy}))
public class MockController {

    private final ConfigurationService configurationService;
    private final MockResponseBodyProcessorService mockResponseBodyProcessorService;
    private final DelayHandlerService delayHandlerService;
    private final DatafakerFormatConverterService datafakerFormatConverterService;

    @RequestMapping(value = "/**", method = {RequestMethod.GET,
            RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> handleAllRequest(HttpServletRequest request,
                                                   @RequestBody(required = false) String body,
                                                   @RequestParam(required = false) Map<String, String> parameters) {
        log.debug("Handling mock server request {}", request);

        // get the configuration
        Configuration configuration = configurationService.getMockConfiguration(request, body);

        if (configuration == null) {
            return new ResponseEntity<>("No matching configuration found", HttpStatus.NOT_FOUND);
        }

        // get the res body and process
        String responseBody = configuration.getResponseBody();
        if (!request.getMethod().equals("DELETE")) {
            responseBody = mockResponseBodyProcessorService.process(body, responseBody);
        }

        // handle delay
        delayHandlerService.handleDelay(parameters, configuration);

        // Save response headers
        saveResponseHeaders(request, configuration, responseBody);

        // Set Content-Type to application/json explicitly
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.status(HttpStatus.valueOf(configuration.getStatusCode()))
                .headers(headers)
                .body(responseBody);
    }

    private void saveResponseHeaders(HttpServletRequest request, Configuration configuration, String responseBody) {
        List<String> headerNames = Collections.list(request.getHeaderNames());
        for (String headerName : headerNames) {
            String headerValue = request.getHeader(headerName);
            ResponseHeader responseHeader = new ResponseHeader();
            responseHeader.setConfiguration(configuration);
            responseHeader.setName(headerName);
            responseHeader.setValue(headerValue);
            configuration.getResponseHeaders().add(responseHeader);
        }

        ResponseHeader contentTypeHeader = new ResponseHeader();
        contentTypeHeader.setConfiguration(configuration);
        contentTypeHeader.setName(HttpHeaders.CONTENT_TYPE);
        contentTypeHeader.setValue("application/json");
        configuration.getResponseHeaders().add(contentTypeHeader);

        // Add Content-Length header
        ResponseHeader contentLengthHeader = new ResponseHeader();
        contentLengthHeader.setConfiguration(configuration);
        contentLengthHeader.setName(HttpHeaders.CONTENT_LENGTH);
        contentLengthHeader.setValue(String.valueOf(responseBody.length()));
        configuration.getResponseHeaders().add(contentLengthHeader);

        configurationService.save(configuration);
    }
}
