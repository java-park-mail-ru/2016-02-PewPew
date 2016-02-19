package com.pewpew.pewpew.servelet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pewpew.pewpew.additional.BufferRead;
import com.pewpew.pewpew.additional.Validate;
import com.pewpew.pewpew.model.User;
import com.pewpew.pewpew.mongo.MongoManager;
import com.pewpew.pewpew.mongo.MongoModule;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class RegistrationService extends HttpServlet {
    private MongoModule mongoModule = MongoModule.getInstanse();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferRead bufferRead = new BufferRead(request);
        StringBuffer jsonBuffer = bufferRead.getStringBuffer();
        if (jsonBuffer == null) {
            ResponseManager.errorResponse("Error reading input stream", response);
            return;
        }
        Gson gson = new Gson();
        User user = gson.fromJson(jsonBuffer.toString(), User.class);

        if (!Validate.user(user)) {
            ResponseManager.errorResponse("Some fiels is missing", response);
            return;
        }

        if (!MongoManager.userExist(user)) {
            ResponseManager.errorResponse("User already exist", response);
            return;
        }

        String newToken = UUID.randomUUID().toString();
        user.setToken(newToken);
        mongoModule.provideDatastore().save(user);

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("token", newToken);
        String stringResponse = gson.toJson(jsonResponse);

        response.setStatus(200);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().println(stringResponse);
    }
}
