package com.zgrelle.resttemplate_spring.model;


import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class Main {
    static final String URL_USERS = "http://91.241.64.178:7081/api/users";

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        User user = new User(3L, "James", "Brown", (byte) 20);

        HttpHeaders headers = new HttpHeaders();

        headers.add("Accept",MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> resultGet
                = restTemplate.exchange(URL_USERS, HttpMethod.GET, entity, String.class);

        headers.set(HttpHeaders.COOKIE, resultGet.getHeaders().getFirst(HttpHeaders.SET_COOKIE));


        HttpEntity<User> requestBody = new HttpEntity<>(user, headers);
        ResponseEntity<String> resultSave
                = restTemplate.exchange(URL_USERS, HttpMethod.POST, requestBody, String.class);


        user.setName("Thomas");
        user.setLastName("Shelby");
        requestBody = new HttpEntity<>(user, headers);
        ResponseEntity<String> resultUpdate
                = restTemplate.exchange(URL_USERS, HttpMethod.PUT, requestBody, String.class);

        requestBody = new HttpEntity<>(headers);
        ResponseEntity<String> resultDelete
                = restTemplate.exchange(URL_USERS + "/" + user.getId(), HttpMethod.DELETE, requestBody, String.class);


        System.out.print(resultSave.getBody());
        System.out.print(resultUpdate.getBody());
        System.out.print(resultDelete.getBody());


    }

}