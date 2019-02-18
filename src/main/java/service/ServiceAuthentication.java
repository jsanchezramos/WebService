package service;


import helpers.TokenHelper;
import model.Token;
import repository.TokenRepository;


public class ServiceAuthentication {
    private TokenRepository tokenRepository;
    private static final int INCREMENTAL_TOKEN = 1;
    private static final int ONE_TOKEN = 1;
    private static final int CERO_MINUTS = 0;

    public ServiceAuthentication(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Boolean checkToken(String token,int limitTimeSecons,int limitTimeMinuts){
        Token tokenObject = tokenRepository.getToKen(token);
        Boolean isOk = false;

        if(tokenObject != null){
            Token tokenIncremental = validateNumTokenAndTime(tokenObject,limitTimeSecons,limitTimeMinuts);
            isOk = tokenRepository.updateToken(tokenIncremental);

        }else if(token != null){
            isOk = tokenRepository.addToken(new Token(token, TokenHelper.getCurrent(), INCREMENTAL_TOKEN));
        }

        return isOk;
    }

    private Token validateNumTokenAndTime(Token tokenObject,int limitTimeSecons,int limitTimeMinuts){
        Token tokenIncremental = null;

        if(TokenHelper.checkDifferentsSecons(tokenObject.getAccesToken()) < limitTimeSecons
                && TokenHelper.checkMaxAuth(tokenObject.getNumToken())
                && TokenHelper.checkDifferentsMinutes(tokenObject.getAccesToken()) == CERO_MINUTS){

            tokenIncremental = new Token(tokenObject.getToken(),tokenObject.getAccesToken(),tokenObject.getNumToken() + INCREMENTAL_TOKEN);

        }else if (TokenHelper.checkDifferentsSecons(tokenObject.getAccesToken()) > limitTimeSecons
                && TokenHelper.checkMaxAuth(tokenObject.getNumToken())
                && TokenHelper.checkDifferentsMinutes(tokenObject.getAccesToken()) == CERO_MINUTS) {

            tokenIncremental = new Token(tokenObject.getToken(),tokenObject.getAccesToken(),ONE_TOKEN);

        }else if(TokenHelper.checkDifferentsMinutes(tokenObject.getAccesToken()) >= limitTimeMinuts) {
            tokenIncremental = new Token(tokenObject.getToken(),TokenHelper.getCurrent(),ONE_TOKEN);
        }

        return tokenIncremental;
    }
}
