package service;


import helpers.TokenHelper;
import model.Token;
import repository.TokenRepository;


public class ServiceAuthentication {
    private TokenRepository tokenRepository;
    private static final int INCREMENTAL_TOKEN = 1;
    private static final int ONE_TOKEN = 1;
    private static final int ZERO_MINUTS = 0;

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

        if(check_differents_in_less_in_limit_time_secons_and_max_auth_minutes_is_zero(tokenObject,limitTimeSecons)){
            tokenIncremental = new Token(tokenObject.getToken(),tokenObject.getAccesToken(),tokenObject.getNumToken() + INCREMENTAL_TOKEN);
        }else if (check_differents_in_higher_limit_time_secons_and_minutes_is_zero(tokenObject,limitTimeSecons)) {
            tokenIncremental = new Token(tokenObject.getToken(),tokenObject.getAccesToken(),ONE_TOKEN);
        }else if(check_acces_token_and_check_minuts_is_mayor_and_same(tokenObject,limitTimeMinuts)) {
            tokenIncremental = new Token(tokenObject.getToken(),TokenHelper.getCurrent(),ONE_TOKEN);
        }
        return tokenIncremental;
    }

    private Boolean check_differents_in_less_in_limit_time_secons_and_max_auth_minutes_is_zero(Token tokenObject, int limitTimeSecons){
        if(TokenHelper.checkDifferentsSecons(tokenObject.getAccesToken()) < limitTimeSecons
                && TokenHelper.checkMaxAuth(tokenObject.getNumToken())
                && TokenHelper.checkDifferentsMinutes(tokenObject.getAccesToken()) == ZERO_MINUTS){
            return true;
        }else
            return false;
    }
    private Boolean check_differents_in_higher_limit_time_secons_and_minutes_is_zero(Token tokenObject, int limitTimeSecons) {
        if(TokenHelper.checkDifferentsSecons(tokenObject.getAccesToken()) > limitTimeSecons
                && TokenHelper.checkMaxAuth(tokenObject.getNumToken())
                && TokenHelper.checkDifferentsMinutes(tokenObject.getAccesToken()) == ZERO_MINUTS){
            return true;
        }else
            return false;
    }
    private Boolean check_acces_token_and_check_minuts_is_mayor_and_same(Token tokenObject, int limitTimeMinuts){
        if(TokenHelper.checkDifferentsMinutes(tokenObject.getAccesToken()) >= limitTimeMinuts){
            return true;
        }else
            return false;
    }
}
