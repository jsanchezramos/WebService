package repository;

import model.Token;

public interface TokenRepository {
    Token getToKen(String token);
    Boolean addToken(Token token);
    Boolean updateToken(Token token);
}
