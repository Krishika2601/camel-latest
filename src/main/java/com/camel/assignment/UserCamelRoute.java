package com.camel.assignment;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class UserCamelRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:fetchUsers?period=10s")
            .setHeader("pageNo").constant(2)
            .to("direct:getUsers")
            .split().body()
            .filter().jsonpath("$[?(@.id in $.headers.ids)]")
            .marshal().json(JsonLibrary.Jackson)
            .end()
            .log("Filtered Users: ${body}");

        from("direct:getUsers")
            .setHeader("Accept", constant("application/json"))
            .setHeader("page", simple("${headers.pageNo}"))
            .to("http:https://reqres.in/api/users")
            .unmarshal().json(JsonLibrary.Jackson, UserResponseWrapper.class) // Unmarshal to UserResponseWrapper
            .process(exchange -> {
                // Get the list of users from the response and set it in the exchange body
                UserResponseWrapper userResponse = exchange.getIn().getBody(UserResponseWrapper.class);
                List<User> users = userResponse.getData();
                exchange.getMessage().setBody(users);
            });
    }

	@Override
	public Set<String> updateRoutesToCamelContext(CamelContext context) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
