package repository;

import model.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import static org.eclipse.jetty.http.HttpParser.LOG;

public class MemoryRepository implements TokenRepository {

    private List<Token> lToken = new ArrayList<>();

    @Override
    public Token getToKen(String token) {
        return lToken.stream().filter(t -> t.getToken().equals(token)).findFirst().orElse(null);
    }

    @Override
    public Boolean addToken(Token token) {
        Boolean isOk = true;
        try{
            lToken.add(token);
        }catch (Exception es){
            isOk = false;
            LOG.info("Error update token",es);
        }
        return isOk;
    }

    @Override
    public Boolean updateToken(Token token) {
        Boolean isOk = true;

        try{
            OptionalInt indexOpt = IntStream.range(0, lToken.size())
                    .filter(i -> token.getToken().equals(lToken.get(i).getToken()))
                    .findFirst();
            lToken.set(indexOpt.getAsInt(),token);

        }catch (Exception es){
            isOk = false;
            LOG.info("Error update token",es);
        }

        return isOk;
    }
}
