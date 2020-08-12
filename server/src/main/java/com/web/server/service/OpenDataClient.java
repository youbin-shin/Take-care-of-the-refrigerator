package com.web.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class OpenDataClient {

//    @Value("${opendata.mafra.key}")
//    private final String API_KEY;
//
//    @Value("${opendata.mafra.url}")
//    private final String API_URL;

    private final String API_KEY = "test1";
    private final String API_URL = "test2";

    private String setRequestURL(String url,
                                 String key,
                                 String type,
                                 String apiUrl,
                                 int startIndex,
                                 int endIndex) {
        StringBuilder resultUrl = new StringBuilder();

        resultUrl.append(url).
                append(key).append("/").
                append(type).append("/").
                append(apiUrl).append("/").
                append(startIndex).append("/").
                append(endIndex);

        return resultUrl.toString();
    }

    public Map<String, Object> requestOpenData(String arg) {
        try {
            String apitUrl = setRequestURL(API_URL, API_KEY, "json", arg, 1, 5);
            URL url = new URL(apitUrl);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//            wr.writeBytes(params);
            wr.flush();
            wr.close();
            int responceCode = con.getResponseCode();
            BufferedReader br;
            if(responceCode == 200) {   // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {                    // 에러 호출
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
