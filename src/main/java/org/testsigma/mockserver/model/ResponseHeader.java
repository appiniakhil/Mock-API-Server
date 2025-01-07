package org.testsigma.mockserver.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "response_headers")
public class ResponseHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "configuration_id", referencedColumnName = "id")
    private Configuration configuration;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;
}
