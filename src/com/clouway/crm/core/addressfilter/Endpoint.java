package com.clouway.crm.core.addressfilter;

public interface Endpoint {
    boolean matches(String url);
}