package com.hongikapp.utility.sos.reserved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

import com.sos.R;

public class Sos_Reserved_main extends Activity {

	// DB address
	private static String url = "http://hitstest.cafe24.com/get_all_reserves.php";

	/*
	 * USER의 학번과 이름. 전체적으로 완성되면 user_id에 로그인 한 ID(학번)을 넣고 user_name에는 로그인 시 가져온
	 * 개인정보 중 이름을 갖고 와 넣는다.
	 */
	private String user_id = "b011000";
	private String user_name = "홍길동";

	Sos_ReservedJsonAdapter Reserved_JsonAdapter; // 리스트뷰에 연결할 커스텀 어댑터
	ArrayList<Sos_Reserved_Data> ReservedList;// 커스텀 어댑터에 연결할 값이 들어갈 리스트

	private ListView listView; // ListView 선언
	private TextView userInfo;
	Sos_ReservedJsonAdapter Reserved_adapter; // Data를 연결할 Reserved_adapter
	ArrayList<Sos_Reserved_Data> alist; // Data를 담을 자료구조

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.sos_reserved);

		// Sos_Reserved_Data 클래스단위로 하는 ArrayList를 생성한다
		ReservedList = new ArrayList<Sos_Reserved_Data>();
		Log.d("Adapter Connect", "OK");

		// Sos_Reserved_Data 클래스 단위의 리스트와 어댑터를 연결시킨다
		Reserved_JsonAdapter = new Sos_ReservedJsonAdapter(this.getParent(),
				ReservedList);
		Log.d("ListView Connect", "OK");

		listView = (ListView) findViewById(R.id.txtlist_sos_reserved_list);

		// 백그라운드 쓰레드를 실행할 준비를한다
		Accestask at = new Accestask();

		// 백그라운드로 url을 인자로 보내서 백그라운드 접속을 시도한다.
		at.execute(url);

		// 구성된 adapter를 리스트뷰에 연결시킨다
		listView.setAdapter(Reserved_JsonAdapter);

		userInfo = (TextView) findViewById(R.id.txt_sos_reserved_title);
		userInfo.setText(user_id + " " + user_name);
	}

	private class Accestask extends AsyncTask<String, Integer, String> {
		/*
		 * AsyncTask의 동작 순서 onPreExecute() -> doInBackground() ->
		 * onPostExecute(); AsyncTask란 백그라운드 작업을 가능하게 해주는 클래스 AsyncTask가 필요한 이유
		 * 인터넷 접속은 매인 쓰레드에서 작동하면 안되기 때문에 백그라운드 쓰레드를 이용하여 인터넷 접속을 시도해야한다. JSON
		 * 파싱을 하기위해서 인터넷에 접속하여야 하는데 그러기위해서 백그라운드 쓰레드를 이용하기위해 extend AsyncTask를
		 * 이용한다.
		 */
		String data = null; // 결과값을 저장할 변수

		@Override
		// 받을 인자는 String 배열형으로 Url을 입력받는다.
		protected String doInBackground(String... url) {
			try {
				/*
				 * 입력받은 Url은 한개이기 때문에 Url[0]을 매개변수로 getJSONFromUrl을 호출한다. 그리고 그
				 * 반환값을 data에 저장한다.
				 */
				data = getJSONFromUrl(url[0]);
				Log.d("Background Task", data.toString());
			} catch (Exception e) {

				// 연결이 실패할경우 Tag를 "BackgroundTask" 로 에러 원인을 Logcat에 전송한다.
				Log.d("Background Task", e.toString());
			}
			return data; // 여기서 리턴 값은 onPostExecut의 인자로 들어간다.
		}

		@Override
		// doInBackground의 리턴값을 인자로 받는다.
		protected void onPostExecute(String result) {
			try {
				/*
				 * doInBackground의 리턴값이 파싱할 값이므로 JSONArray로 해서 생성한다. 파싱할 값의
				 * 데이터레이블이 없으므로 JSONObject jsonObject = new JSONObject(String);
				 * JSONArray jsonArray = (JSONArray) jsonObject.get(String);
				 * 형식으로 파싱할 수 없다 (인터넷에서 안드로이드 JSON 파싱을 검색하면 가장 많이나오는 파싱 방법 ) 그렇기
				 * 때문에 아에 string을 JSONArray로 지정한다.
				 */
				JSONObject jsonObject = new JSONObject(result);
				JSONArray menuitemArray = jsonObject
						.getJSONArray("sos_reserved");

				// 입력한 JSONArray의 크기만큼 반복문을 돌린다.
				for (int i = 0; i < menuitemArray.length(); i++) {

					// Login 했을 때의 ID(학번)과 같은 학번을 가진 row만 출력
					String tmp_uid = menuitemArray.getJSONObject(i)
							.getString("uid").toString();
					if (tmp_uid.equals(user_id)) {
						Reserved_JsonAdapter.add(new Sos_Reserved_Data(
								getApplicationContext(),
								menuitemArray.getJSONObject(i)
										.getString("uid").toString(),
								menuitemArray.getJSONObject(i)
										.getString("reserve_place").toString(),
								menuitemArray.getJSONObject(i)
										.getString("reserve_month").toString(),
								menuitemArray.getJSONObject(i)
										.getString("reserve_day").toString(),
								menuitemArray.getJSONObject(i)
										.getString("start_hour").toString(),
								menuitemArray.getJSONObject(i)
										.getString("start_min").toString()));
					}
				}
				Log.d("Input Item at Adapter", "OK");
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}

	public String getJSONFromUrl(String Url) {
		String data = ""; // 파싱할 값을 최종적으로 입력받아 저장할 String 변수를 선언
		InputStream InStr = null; // 인터넷에 접속하여 값을 가져오는데 사용할 Stream 선언

		try {
			URL url = new URL(Url); // 인자로 받은 url을 URL객체로 생성

			// 입력받은 URL로 연결할 HTTP 커넷션을 생성
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();

			urlConnection.connect(); // HTTP 커네션을 이용하여 URL로 연결
			InStr = urlConnection.getInputStream(); // 연결된 URL에서 입력받을
													// InputStream을 지정

			// InputStream으로 들어올 값을 저장할 버퍼 선언
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					InStr));
			StringBuilder sb = new StringBuilder(); // 버퍼를 통해 들어오는 값을 합칠
													// StringBuilder 생성
			String line = null; // 버퍼에서 읽어드린 값을 임시로 저장할 변수 선언

			// 버퍼에 값을 한줄 단위로 읽어서 더이상 값이 없을때까지 읽고 읽은 줄의 정보는 임시변수인 line에 저장
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n"); // 버퍼에서 읽은 값인 line을 StringBuilder에 합친다.
			}
			InStr.close(); // 입력을 다완료했으면 스트림 연결을 끊는다
			data = sb.toString(); // StringBuilder로 만들어진 문자열을 data에 저장한다.

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			Log.d("Exception while downloading url", e.toString());
		}
		return data; // 완성된 data를 반환한다.
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
};