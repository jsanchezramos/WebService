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

        if(checkDifferentsTimeAndAccesTokenMinorLimitTimeSeconAndMinutsIsZero(tokenObject,limitTimeSecons)){
            tokenIncremental = new Token(tokenObject.getToken(),tokenObject.getAccesToken(),tokenObject.getNumToken() + INCREMENTAL_TOKEN);
        }else if (checkDifferentsTimeAndAccesTokenMayorLimitTimeSeconAndMinutsIsZero(tokenObject,limitTimeSecons)) {
            tokenIncremental = new Token(tokenObject.getToken(),tokenObject.getAccesToken(),ONE_TOKEN);
        }else if(checkDifferentsTimeInMinuts(tokenObject,limitTimeMinuts)) {
            tokenIncremental = new Token(tokenObject.getToken(),TokenHelper.getCurrent(),ONE_TOKEN);
        }
        return tokenIncremental;
    }

    private Boolean checkDifferentsTimeAndAccesTokenMinorLimitTimeSeconAndMinutsIsZero(Token tokenObject,int limitTimeSecons){
        if(TokenHelper.checkDifferentsSecons(tokenObject.getAccesToken()) < limitTimeSecons
                && TokenHelper.checkMaxAuth(tokenObject.getNumToken())
                && TokenHelper.checkDifferentsMinutes(tokenObject.getAccesToken()) == CERO_MINUTS){
            return true;
        }else
            return false;
    }
    private Boolean checkDifferentsTimeAndAccesTokenMayorLimitTimeSeconAndMinutsIsZero(Token tokenObject,int limitTimeSecons) {
        if(TokenHelper.checkDifferentsSecons(tokenObject.getAccesToken()) > limitTimeSecons
                && TokenHelper.checkMaxAuth(tokenObject.getNumToken())
                && TokenHelper.checkDifferentsMinutes(tokenObject.getAccesToken()) == CERO_MINUTS){
            return true;
        }else
            return false;
    }
    private Boolean checkDifferentsTimeInMinuts(Token tokenObject,int limitTimeMinuts){
        if(TokenHelper.checkDifferentsMinutes(tokenObject.getAccesToken()) >= limitTimeMinuts){
            return true;
        }else
            return false;
    }
}
