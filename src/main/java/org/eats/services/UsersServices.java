package org.eats.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eats.errors.ApiExceptions;
import org.eats.repository.Connections;
import org.eats.repository.models.Users;
import org.eats.response.ResponseApi;

public class UsersServices {

    private Connections database;
    private ObjectMapper maper;

    public UsersServices(ObjectMapper maper){
        this.maper = maper;
    }

    public void init(){
        this.database = new Connections(maper);
    }

    public ResponseApi register(String username, String password, String email, String name) throws JsonProcessingException {
        int count = database.count("users", "username", username);
        if(count > 0){
            throw new ApiExceptions(400, "username already exist");
        }
        database.createUsers(username, password, email, name);
        Users data = database.getUnique("users", "username", username);
        return new ResponseApi(200, "successfully register", data, null, false);
    }


}
