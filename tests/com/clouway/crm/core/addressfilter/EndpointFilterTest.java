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
    public void mockMatchesWithUrl(){

        final Endpoint endpoint = context.mock(Endpoint.class);
        final String url = "url";

        context.checking(new Expectations(){{

            oneOf (endpoint).matches(url); will(returnValue(url.matches("url")));

        }});

        filter = new EndpointFilter(endpoint);
        assertThat(filter.shouldFilter(url), is(true));

        context.assertIsSatisfied();

    }

    @Test
    public void mockDoesNotMatchWithUrl(){

        final Endpoint endpoint = context.mock(Endpoint.class);
        final String url = "123";

        context.checking(new Expectations(){{
            allowing (endpoint).matches(url); will(returnValue(url.matches("url")));
        }});

        filter = new EndpointFilter(endpoint);
        assertThat(filter.shouldFilter(url), is(false));

        context.assertIsSatisfied();

    }

    @Test
    public void multipleMocksMatch(){

        final Endpoint endpoint = context.mock(Endpoint.class);

        final String url = "123";
        final String anotherUrl = "not 123";

        context.checking(new Expectations(){{

            oneOf(endpoint).matches(url); will(returnValue(url.matches("123")));
            oneOf(endpoint).matches("123"); will(returnValue("123".matches(url)));

            exactly(4).of(endpoint).matches(anotherUrl); will(returnValue(anotherUrl.matches(url)));

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

            oneOf(startsWith).matches(url); will(returnValue(url.startsWith(keyword)));
            exactly(2).of(startsWith).matches(anotherUrl); will(returnValue(anotherUrl.startsWith(keyword)));

        }});

        filter = new EndpointFilter(startsWith, startsWith);

        assertThat(filter.shouldFilter(url), is(true));
        assertThat(filter.shouldFilter(anotherUrl), is(false));

        context.assertIsSatisfied();


    }

}