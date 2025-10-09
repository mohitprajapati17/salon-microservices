package com.mohitmac.userService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import com.mohitmac.userService.payload_response.KeyCloakRoleDTO;
import com.mohitmac.userService.payload_response.dto.Credentials;
import com.mohitmac.userService.payload_response.dto.KeyCloakUserDTO;
import com.mohitmac.userService.payload_response.dto.SignupDTO;
import com.mohitmac.userService.payload_response.dto.UserRequest;
import com.mohitmac.userService.payload_response.response.TokenResponse;



@Service
@AllArgsConstructor
public class KeycloakService {
    private static final  String  KEYCLOAK_BASE_URL = "http://localhost:8080";
    private static final  String  KEYCLOAK_ADMIN_API = KEYCLOAK_BASE_URL+"/admin/realms/master/users";
    private static final  String  TOKEN_URL = KEYCLOAK_BASE_URL+"/realms/master/protocol/openid-connect/token";

    private static final String CLIENT_ID = "salon-booking-client";
    private static final String CLIENT_SECRET = "evhO44qvqdInCmJubw1zzCTwPuIYaLyy";
    private static final String GRANT_TYPE = "password";
    private static final String USERNAME = "mohit";
    private static final String PASSWORD = "mohit";
    private static final String scope="openid profile email";
    private static final String client_id="f01375eb-3e6b-4b93-8220-df216fde2cf0";

    
    private final RestTemplate restTemplate;

    public  void createUser(SignupDTO signupDTO) throws Exception {
        String ACCESS_TOKEN=getAdminAccessToken(USERNAME , PASSWORD , GRANT_TYPE , null).getAccessToken();
        Credentials credentials = new Credentials();
        credentials.setTemporary(false);
        credentials.setType("password");
        credentials.setValue(signupDTO.getPassword());

        UserRequest userRequest = new UserRequest();

        userRequest.setFirstName(signupDTO.getFullName());
        userRequest.setEmail(signupDTO.getEmail());
        userRequest.setUsername(signupDTO.getUsername());
        userRequest.setEnabled(true);
        userRequest.setCredentials(List.of(credentials));

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(ACCESS_TOKEN);

        HttpEntity <UserRequest> request = new HttpEntity<>(userRequest, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            KEYCLOAK_ADMIN_API, 
            HttpMethod.POST, 
            request, 
            String.class
        );

        if(response.getStatusCode()==HttpStatus.CREATED){
            System.out.println("User created successfully");

            KeyCloakUserDTO user = fetchFirstUserByUsername(signupDTO.getUsername(),ACCESS_TOKEN);
             KeyCloakRoleDTO role = getRoleByName(client_id,ACCESS_TOKEN,signupDTO.getUserRole().toString());

             List<KeyCloakRoleDTO> roles = new ArrayList<>();
             roles.add(role);
             
             assignRoleToUser(user.getId(),client_id, roles,ACCESS_TOKEN);
        }
        else{
            System.out.println("User not created");
            throw new Exception(response.getBody());
        }
    
        
    }

    public TokenResponse getAdminAccessToken(String  username ,String password , String grantType ,   String  refreshToken) throws Exception{

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

         MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", grantType);
        
        // Only add parameters if they are not null
        if(username != null) {
            requestBody.add("username", username);
        }
        if(password != null) {
            requestBody.add("password", password);
        }
        if(refreshToken != null) {
            requestBody.add("refresh_token", refreshToken);
        }
        
        requestBody.add("client_id", CLIENT_ID);
        requestBody.add("client_secret", CLIENT_SECRET);
        requestBody.add("scope", scope);
        
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<TokenResponse> response = restTemplate.exchange(
                TOKEN_URL,
                HttpMethod.POST,
                requestEntity,
                TokenResponse.class
            );
            
            if(response.getStatusCode()==HttpStatus.OK && response.getBody()!=null){
                return response.getBody();
            }
            else throw new Exception("Failed to get access token: " + response.getStatusCode());
        } catch (Exception e) {
            throw new Exception("Authentication failed: " + e.getMessage());
        }

    }

    public KeyCloakRoleDTO getRoleByName(String  clientId , String token ,String role) throws Exception{

        String url=KEYCLOAK_BASE_URL+"/admin/realms/master/clients/"+clientId+"/roles/"+role;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
       

        
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<KeyCloakRoleDTO> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            requestEntity,
            KeyCloakRoleDTO.class
        );


        if(response.getBody()!=null){
            return response.getBody();
        }
        else throw new Exception("Failed to get role");
        
    }


    public KeyCloakUserDTO fetchFirstUserByUsername(String username,String token  ) throws Exception{
        String url=KEYCLOAK_BASE_URL+"/admin/realms/master/users?username="+username;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
       

        
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<KeyCloakUserDTO[]> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            requestEntity,
            KeyCloakUserDTO[].class
        );

        KeyCloakUserDTO[] users = response.getBody();
        if(users!=null&&users.length>0){
            return users[0];
        }
        else throw new Exception("Failed to get user");
    }

    public void assignRoleToUser(String userId,String clientId ,  List<KeyCloakRoleDTO> roles ,String token) throws Exception{
        
        String url=KEYCLOAK_BASE_URL+"/admin/realms/master/users/"+userId+"/role-mappings/clients/"+clientId;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
       

        
        HttpEntity<List<KeyCloakRoleDTO>> requestEntity = new HttpEntity<>(roles,headers);

        
        try{

            ResponseEntity<KeyCloakRoleDTO> response = restTemplate.exchange(
            url,
            HttpMethod.POST,
            requestEntity,
            KeyCloakRoleDTO.class
        );

        }catch(Exception e){
            throw new Exception("Failed to assign role to user"+e.getMessage()
            );
        }

        

    





    }
    public KeyCloakUserDTO fetchUserProfileByJWT(String token) throws Exception{
        System.out.println("keycloak profile token " + token);
        String url = KEYCLOAK_BASE_URL + "/realms/master/protocol/openid-connect/userinfo";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);
        
        // Create an HttpEntity with the headers
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        try {
            // Send the GET request
            ResponseEntity<KeyCloakUserDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                KeyCloakUserDTO.class
            );
            
            // Extract and return the first user object
            return response.getBody();
        } catch (Exception e) {
            System.out.println("Failed to fetch user details: " + e.getMessage());
            throw new Exception("Failed to fetch user details: " + e.getMessage());
        }

        



    }



    
}


