import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;

public class ApiJsonData implements Serializable {
    private String currency;

    public ApiJsonData(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ReqAndResponse getResult() {
        String address = getApi("https://api.currencyfreaks.com/v2.0/rates/latest?apikey=0c940eb9b9334ca488b0db09606d1cf0");

        JSONObject jsonObject = new JSONObject(address);
        double result = jsonObject.getJSONObject("rates").getDouble(currency);
        ReqAndResponse reqAndResponse = new ReqAndResponse(result);

        return reqAndResponse;
    }

    public String getApi(String url) {
        StringBuffer buffer = new StringBuffer();

        try {
            URL url1 = new URL(url);
            URLConnection urlConnection = url1.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String data = null;

            while ((data = bufferedReader.readLine()) != null) {
                buffer.append(data);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
