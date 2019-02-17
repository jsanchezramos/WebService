package service;


import helpers.TokenHelper;
import model.Token;
import repository.TokenRepository;


public class ServiceAuthentication {
    private TokenRepository tokenRepository;
    private static final int INCREMENTAL_TOKEN = 1;
    private static final int CERO_TOKEN = 1;
    private static final int CERO_MINUTS = 0;

    public ServiceAuthentication(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Boolean checkToken(String token){
        Token tokenObject = tokenRepository.getToKen(token);
        Boolean isOk = true;

        if(tokenObject != null){
            Token tokenIncremental = validateNumTokenAndTime(tokenObject);
            isOk = tokenRepository.updateToken(tokenIncremental);

        }else{
            isOk = tokenRepository.addToken(new Token(token, TokenHelper.getCurrent(), INCREMENTAL_TOKEN));
        }

        return isOk;
    }

    private Token validateNumTokenAndTime(Token tokenObject){
        Token tokenIncremental = null;

        if(TokenHelper.checkDifferentsSecons(tokenObject.getAccesToken()) < 60
                && TokenHelper.checkMaxAuth(tokenObject.getNumToken())
                && TokenHelper.checkDifferentsMinutes(tokenObject.getAccesToken()) == CERO_MINUTS){

            tokenIncremental = new Token(tokenObject.getToken(),tokenObject.getAccesToken(),tokenObject.getNumToken() + INCREMENTAL_TOKEN);

        }else if (TokenHelper.checkDifferentsSecons(tokenObject.getAccesToken()) > 60
                && TokenHelper.checkMaxAuth(tokenObject.getNumToken())
                && TokenHelper.checkDifferentsMinutes(tokenObject.getAccesToken()) == CERO_MINUTS) {

            tokenIncremental = new Token(tokenObject.getToken(),tokenObject.getAccesToken(),CERO_TOKEN);

        }else if(TokenHelper.checkDifferentsMinutes(tokenObject.getAccesToken()) > CERO_MINUTS) {
            tokenIncremental = new Token(tokenObject.getToken(),TokenHelper.getCurrent(),CERO_MINUTS);
        }

        return tokenIncremental;
    }
}
