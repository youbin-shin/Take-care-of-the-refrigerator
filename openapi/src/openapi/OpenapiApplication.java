package openapi;

import java.io.BufferedInputStream;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class OpenapiApplication {
	
	private static final String BASE_URL = "http://openapi.foodsafetykorea.go.kr/api/";
	private static final String BASE_KEY = "87bbd3eb96fa4636a4fb";
	static int START_INDEX = 1;
	static int END_INDEX = 5; // 1000;
	
	
	public OpenapiApplication() throws Exception {
		JSONParser jsonparser = new JSONParser();
	    JSONObject jsonobject = (JSONObject)jsonparser.parse(readUrl());
	    JSONObject json =  (JSONObject) jsonobject.get("COOKRCP01");
	    JSONObject jsonResult =  (JSONObject) json.get("RESULT");
	    boolean successFlag = jsonResult.get("CODE").equals("INFO-000");
	    String jsonTotal =  (String) json.get("total_count");

	    String manual = "MANUAL";
	    String manualImg = "MANUAL_IMG";

		// successFlag 값을 기준으로 실행해야함...
	    JSONArray array = (JSONArray)json.get("row");
		for(int i = 0 ; i < array.size(); i++){

			JSONObject entity = (JSONObject)array.get(i);
			String recipeName = (String) entity.get("RCP_NM");
			System.out.println(recipeName);

			// MANUAL01 ~ MANUAL99 까지 있는거 확인해야하는데...
			int j = 1;

			for(; j < 30; j++) {
				String manualText = (String) entity.get(manual + String.format("%02d",j));
				String manualImagePath = (String) entity.get(manualImg + String.format("%02d",j));
				if(manualText.isEmpty()) {
					break;
				}

				System.out.println(manualText);				// 확인 출력
				if(!manualImagePath.isEmpty()) {
					System.out.println(manualImagePath);	// 확인 출력
				}
			}
	    }
		System.out.println();
	}
	
	
	private static String readUrl() throws Exception {
		StringBuilder apiUrl = new StringBuilder();
		apiUrl.append(BASE_URL)
			.append(BASE_KEY).append("/")		// 키 입력
			.append("COOKRCP01").append("/")	// 서비스명 입력
			.append("json").append("/")			// 요청파일 타입 입력
			.append(START_INDEX).append("/")	// 요청 시작 위치
			.append(END_INDEX);					// 요청 종료 위치
		
        BufferedInputStream reader = null;
        try {
            URL url = new URL(apiUrl.toString());
            reader = new BufferedInputStream(url.openStream());
            StringBuffer buffer = new StringBuffer();
            int i;
            byte[] b = new byte[4096];
            while( (i = reader.read(b)) != -1){
              buffer.append(new String(b, 0, i));
            }
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
	
	

	public static void main(String[] args) {
		try {
			new OpenapiApplication();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
