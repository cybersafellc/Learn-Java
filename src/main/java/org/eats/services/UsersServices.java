package org.eats.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eats.errors.ApiExceptions;
import org.eats.models.Connections;
import org.eats.response.ResponseApi;

import java.sql.ResultSet;

public class UsersServices {

    private Connections database;
    private ObjectMapper maper;

    public UsersServices(ObjectMapper maper){
        this.maper = maper;
    }

    public void init(){
        this.database = new Connections();
    }

    public ResponseApi register(String username, String password, String email, String name) throws JsonProcessingException {
        int count = database.count("users", "username", username);
        if(count > 0){
            throw new ApiExceptions(400, "username already exist");
        }
        database.createUsers(username, password, email, name);
        ResultSet data = database.getUnique("users", "username", username);
        return new ResponseApi(200, "successfully register", maper.writeValueAsString(data), null, false);
    }


}
