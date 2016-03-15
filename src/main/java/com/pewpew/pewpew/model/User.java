package com.pewpew.pewpew.model;

import com.pewpew.pewpew.annotations.UserInfo;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

import javax.validation.constraints.Size;

@Entity(value = "users")
public class User {
    @SuppressWarnings({"unused", "InstanceVariableNamingConvention"})

    @UserInfo
    @Id
    private ObjectId id;

    @NotNull
    @Size(min=1)
    private String password;

    @UserInfo
    @Indexed(unique = true)
    @NotNull
//    @Email
    private String email;

    @UserInfo
    @NotNull
    @Size(min=1)
    private String login;

    @Nullable
    private Integer rating;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    @NotNull
    public String getLogin() {
        return login;
    }

    public void setLogin(@NotNull String login) {
        this.login = login;
    }

    @Nullable
    public Integer getRating() {
        return rating;
    }

    public void setRating(@Nullable Integer rating) {
        this.rating = rating;
    }
}
