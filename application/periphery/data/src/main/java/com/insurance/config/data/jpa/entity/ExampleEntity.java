package com.insurance.config.data.jpa.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class ExampleEntity {

    @Version
    protected int version;
}
