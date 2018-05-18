package com.clouway.crm.core.addressfilter;


import org.junit.jupiter.api.Test;
import org.jmock.Mockery;
import org.jmock.Expectations;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class EndpointFilterTest {

    Mockery context = new Mockery();
    EndpointFilter filter;

    @Test
    public void matchesWithUrl(){

        final Endpoint endpoint = context.mock(Endpoint.class);
        final String url = "url";

        context.checking(new Expectations(){{

            oneOf (endpoint).matches(url); will(returnValue(true));

        }});

        filter = new EndpointFilter(endpoint);
        assertThat(filter.shouldFilter(url), is(true));

        context.assertIsSatisfied();

    }

    @Test
    public void doesNotMatchWithUrl(){

        final Endpoint endpoint = context.mock(Endpoint.class);
        final String url = "123";

        context.checking(new Expectations(){{
            allowing (endpoint).matches(url); will(returnValue(false));
        }});

        filter = new EndpointFilter(endpoint);
        assertThat(filter.shouldFilter(url), is(false));

        context.assertIsSatisfied();

    }

    @Test
    public void multipleMatch(){

        final Endpoint endpoint = context.mock(Endpoint.class);

        final String url = "123";
        final String anotherUrl = "not 123";

        context.checking(new Expectations(){{

            oneOf(endpoint).matches(url); will(returnValue(true));
            oneOf(endpoint).matches("123"); will(returnValue(true));

            exactly(4).of(endpoint).matches(anotherUrl); will(returnValue(false));

            never(endpoint).matches("never");

        }});

        filter = new EndpointFilter(endpoint, endpoint, endpoint, endpoint);

        assertThat(filter.shouldFilter(url), is(true));
        assertThat(filter.shouldFilter("123"), is(true));
        assertThat(filter.shouldFilter(anotherUrl), is(false));

        context.assertIsSatisfied();

    }

    @Test
    public void startsWithKeyword(){

        final StartsWithKeyword startsWith = context.mock(StartsWithKeyword.class);
        final String url = "keyword another word";
        final String keyword = "keyword";
        final String anotherUrl = "this wont return true";

        context.checking(new Expectations(){{

            oneOf(startsWith).matches(url); will(returnValue(true));
            exactly(2).of(startsWith).matches(anotherUrl); will(returnValue(false));

        }});

        filter = new EndpointFilter(startsWith, startsWith);

        assertThat(filter.shouldFilter(url), is(true));
        assertThat(filter.shouldFilter(anotherUrl), is(false));

        context.assertIsSatisfied();


    }

}