package token;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Игорь
 */
public final class VKToken {

    public static final String VK_URL = "https://vk.com";

    public static final String AUTH_URL = "https://oauth.vk.com/authorize?client_id=5251743&"
            + "scope=messages,friends&redirect_uri=https://oauth.vk.com/blank.html&"
            + "display=page&response_type=token&v=5.44";

    private static final String CHECKED_PROPERTIES = "p";

    private static Map<String, String> enter(String login, String password) throws IOException {
        Map<String, String> cookies;
        Connection.Response connection = Jsoup.connect(VK_URL).execute();
        String url = parseForm(connection);
        cookies = connection.cookies();
        Map<String, String> data = new HashMap<>();
        data.put("email", login);
        data.put("pass", password);
        connection = Jsoup.connect(url).cookies(cookies).data(data).execute();
        cookies = connection.cookies();
        if (!cookies.containsKey(CHECKED_PROPERTIES)) {
            throw new RuntimeException("Invalid login/password");
        }
        return cookies;
    }

    /**
     * create Token by parameters
     *
     * @return return access_token by String
     */
    public static String getToken(String appId, String scope, String login, String password) throws IOException {
        Map<String, String> loginCookies = enter(login, password);
        Connection.Response response = Jsoup.connect(String.format(AUTH_URL, appId, scope)).cookies(loginCookies).execute();
        if (response.url().getRef() == null) {
            String grantAccessAction = parseForm(response);
            response = Jsoup.connect(grantAccessAction).cookies(loginCookies).execute();
        }
        return response.url().getRef().split("&")[0].split("=")[1];
    }

    private static String parseForm(Connection.Response pageResponse) throws IOException {
        Document document = pageResponse.parse();
        Element form = document.getElementsByTag("form").get(0);
        return form.attr("action");
    }

}
