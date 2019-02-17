import server.ServerWebService;

public class app {
    public static void main(String[] args) throws Exception {
        new ServerWebService().start(7000);
    }
}
