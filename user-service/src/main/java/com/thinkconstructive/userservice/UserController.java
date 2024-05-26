package com.thinkconstructive.userservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    
    @Value("${useservice.base.url}")
    private String postServiceUrl;

    @GetMapping(value="/{userId}")
    public User getUser(@PathVariable("userId") String userId)
    {
        User userOne = new User(userId, "User Name " + userId,
                "xxxxx" + userId);

        Posts posts = restTemplate.getForObject(
                postServiceUrl+"post/1", Posts.class);

        userOne.setPosts(posts);

        Notifications notifications = restTemplate.getForObject(
                "http://notification-service/notification/1", Notifications.class
        );
        userOne.setNotifications(notifications);

        return userOne;
    }
    
    // @GetMapping(value="/{deviceName}/{tenantname}/{vendor}/{tech}")
    // public String redirect(@PathVariable("deviceName") String deviceName,@PathVariable("tenantname") String tenantname,@PathVariable("vendor") String vendor,@PathVariable("tech") String tech)
    // {

    //     url="http://deviceName/{tenantname}/{vendor}/{tech}"
    //     Posts posts = restTemplate.getForObject(
    //             "http://post-service/post/1", Posts.class);
        

    //     Notifications notifications = restTemplate.getForObject(
    //             "http://notification-service/notification/1", Notifications.class
    //     );
    //     userOne.setNotifications(notifications);

    //     return userOne;
    // }

}
