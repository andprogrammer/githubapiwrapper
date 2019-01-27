import com.mashape.unirest.http.exceptions.UnirestException;

public class Application {

    public static void main(String[] args) throws UnirestException {

        runApplication();
    }

    private static void runApplication() throws UnirestException {

        RestClient client = new RestClient("https://api.github.com/repos/andprogrammer/DBHandler", 20);
        Repository repository = client.request("andprogrammer", "DBHandler");
        System.out.println(repository);
    }
}
