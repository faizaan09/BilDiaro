package com.example.jaybhatt.bildiaro;

/**
 * Created by Jay Bhatt on 04-03-2016.
 */
import com.orm.dsl.Ignore;
import com.orm.dsl.Table;

/**
 * Created by Faizaan on 2/18/2016.
 */
@Table
public class User {
    @Ignore
    static Long idCount = 0L;
    private Long id;
    private String email;
    private String password;

    public User(String email, String password) {
        id = ++idCount;
        this.email = email;
        this.password = password;
    }

    public static Long getIdCount() {
        return idCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
