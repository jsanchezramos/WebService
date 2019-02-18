package repository;

import helpers.TokenHelper;
import model.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TokenRepositoryTest {

    private TokenRepository tokenRepository = new MemoryRepository();
    @Test
    public void add_token(){
        Token tokenObject = new Token("stringToken", TokenHelper.getCurrent(),0);
        assertTrue(tokenRepository.addToken(tokenObject));
    }
    @Test
    public void get_token(){
        Token tokenObject = new Token("stringToken", TokenHelper.getCurrent(),0);
        tokenRepository.addToken(tokenObject);
        Token obj = tokenRepository.getToKen(tokenObject.getToken());
        assertNotNull(obj);
    }
    @Test
    public void update_token(){
        Token tokenObject = new Token("stringToken", TokenHelper.getCurrent(),0);
        tokenRepository.addToken(tokenObject);
        Token tokenUpdate = new Token(tokenObject.getToken(),tokenObject.getAccesToken(),2);
        assertTrue(tokenRepository.updateToken(tokenUpdate));

        Token obj = tokenRepository.getToKen(tokenUpdate.getToken());
        assertEquals(obj.getNumToken(),2);
    }
}
