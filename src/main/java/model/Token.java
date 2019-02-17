package model;

import java.sql.Timestamp;

public final class Token {

    final private String token;
    final private Timestamp accesToken;
    final private int numToken;

    public Token(String token, Timestamp accesToken,int numToken) {
        this.token = token;
        this.accesToken = accesToken;
        this.numToken = numToken;
    }

    public String getToken() {
        return token;
    }
    public Timestamp getAccesToken() {
        return accesToken;
    }
    public int getNumToken() {
        return numToken;
    }
}
