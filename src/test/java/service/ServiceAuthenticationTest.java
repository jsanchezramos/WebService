package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.MemoryRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceAuthenticationTest {

    private ServiceAuthentication serviceAuthentication;
    private String STRING_TOKEN = "stringToken";
    private int LIMIT_60_SECONS = 60;
    private int LIMIT_5_SECONS = 5;
    private int LIMIT_1_MINUTS = 1;
    private int LIMIT_0_MINUTS = 0;

    @BeforeEach
    public void setup(){
        serviceAuthentication = new ServiceAuthentication(new MemoryRepository());
    }


    @Test
    public void check_token_simple(){
        assertTrue(serviceAuthentication.checkToken(STRING_TOKEN, LIMIT_60_SECONS,LIMIT_1_MINUTS));
    }

    /**
     * Check token 3 times, and 4 is false token.
     */
    @Test
    public void valid_three_times_token_and_four_is_false(){
        assertTrue(serviceAuthentication.checkToken(STRING_TOKEN, LIMIT_60_SECONS,LIMIT_1_MINUTS));
        assertTrue(serviceAuthentication.checkToken(STRING_TOKEN, LIMIT_60_SECONS,LIMIT_1_MINUTS));
        assertTrue(serviceAuthentication.checkToken(STRING_TOKEN, LIMIT_60_SECONS,LIMIT_1_MINUTS));
        assertFalse(serviceAuthentication.checkToken(STRING_TOKEN, LIMIT_60_SECONS,LIMIT_1_MINUTS));
    }
    @Test
    public void valid_time_tokens_in_wait_five_secons(){

        try {
            assertTrue(serviceAuthentication.checkToken(STRING_TOKEN, LIMIT_60_SECONS,LIMIT_1_MINUTS));
            assertTrue(serviceAuthentication.checkToken(STRING_TOKEN, LIMIT_60_SECONS,LIMIT_1_MINUTS));
            assertTrue(serviceAuthentication.checkToken(STRING_TOKEN, LIMIT_60_SECONS,LIMIT_1_MINUTS));
            assertFalse(serviceAuthentication.checkToken(STRING_TOKEN, LIMIT_60_SECONS,LIMIT_1_MINUTS));
            Thread.sleep(5000);

            assertTrue(serviceAuthentication.checkToken(STRING_TOKEN, LIMIT_5_SECONS,LIMIT_0_MINUTS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
