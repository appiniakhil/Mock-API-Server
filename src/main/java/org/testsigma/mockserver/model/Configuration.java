package org.testsigma.mockserver.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "configuration")
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tenant_id")
    private String tenantId;
    @Column(name = "uri_path")
    private String uriPath;

    @Column(name = "method")
    private String method;

    @Column(name = "request_body")
    private String requestBody;

    @Column(name = "response_body")
    private String responseBody;

    @Column(name = "status_code")
    private Integer statusCode;

    @Column(name = "delay")
    private Long delay;

    @OneToMany(mappedBy = "configuration", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ResponseHeader> responseHeaders;
}
