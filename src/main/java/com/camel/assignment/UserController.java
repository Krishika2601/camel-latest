package com.camel.assignment;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {

	 @Autowired
	    private ProducerTemplate producerTemplate;

    @GetMapping("/filter")
    public List<User> filterUsersByIds(@RequestParam List<Integer> ids) {
        // The Camel route will automatically fetch the users using the timer
        return new ArrayList<>(); // Placeholder return value for now
    }

	
	@GetMapping("/all")
	public List<User> getAllUsers() {
	    // Fetch the users from the Camel route using producerTemplate
	    List<User> users = producerTemplate.requestBody("direct:getUsers", null, List.class);
	    return users;
	}
}
