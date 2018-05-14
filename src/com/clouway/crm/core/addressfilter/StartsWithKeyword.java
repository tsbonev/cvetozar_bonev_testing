package com.clouway.crm.core.addressfilter;

public interface StartsWithKeyword extends Endpoint{

    @Override
    boolean matches(String url);

}
