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
<<<<<<< HEAD
import android.view.Menu;
=======
import android.widget.Button;
>>>>>>> 3214e8b5bb8c77ba8d542d188e6289b1fd99d3cd
import android.widget.ListView;
import android.widget.TextView;

import com.sos.R;

public class Sos_Reserved_main extends Activity {

	// DB address
	private static String url = "http://hitstest.cafe24.com/get_all_reserves.php";
    
    /* USER�� �й��� �̸�.
     * ��ü������ �ϼ��Ǹ� user_id�� �α��� �� ID(�й�)�� �ְ�
     * user_name���� �α��� �� ������ �������� �� �̸��� ���� �� �ִ´�.
     */
    private String user_id = "b011000";
    private String user_name = "ȫ�浿";
    
	Sos_ReservedJsonAdapter Reserved_JsonAdapter; // ����Ʈ�信 ������ Ŀ���� �����
	ArrayList<Sos_Reserved_Data> ReservedList;// Ŀ���� ����Ϳ� ������ ���� �� ����Ʈ
 
	private ListView listView; // ListView ����
	private TextView userInfo;
<<<<<<< HEAD
=======
	Sos_ReservedAdapter Reserved_adapter; // Data�� ������ Reserved_adapter
	ArrayList<Sos_Reserved_Data> alist; // Data�� ���� �ڷᱸ��
	Button btnMore, btnCancel;
>>>>>>> 3214e8b5bb8c77ba8d542d188e6289b1fd99d3cd

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.sos_reserved);
<<<<<<< HEAD
		
		// Sos_Reserved_Data Ŭ���������� �ϴ� ArrayList�� �����Ѵ�
		ReservedList = new ArrayList<Sos_Reserved_Data>();
		Log.d("Adapter Connect","OK");
		
		// Sos_Reserved_Data Ŭ���� ������ ����Ʈ�� ����͸� �����Ų��
		Reserved_JsonAdapter = new Sos_ReservedJsonAdapter(this.getParent(), ReservedList);
		Log.d("ListView Connect","OK");
		
=======
		/*
		Log.e("ad55p", "ad55p");
>>>>>>> 3214e8b5bb8c77ba8d542d188e6289b1fd99d3cd
		listView = (ListView) findViewById(R.id.txtlist_sos_reserved_list);
		
		// ��׶��� �����带 ������ �غ��Ѵ�
		Accestask at = new Accestask();
		
		// ��׶���� url�� ���ڷ� ������ ��׶��� ������ �õ��Ѵ�.
		at.execute(url);
		
		// ������ adapter�� ����Ʈ�信 �����Ų��
		listView.setAdapter(Reserved_JsonAdapter);
		
		userInfo = (TextView) findViewById(R.id.txt_sos_reserved_title);
<<<<<<< HEAD
		userInfo.setText(user_id + " " + user_name);
	 }
=======
		userInfo.setText("B011128 ������ ¯¯¯¯");
		//btnMore = (Button) this.findViewById(R.id.btn_more);
		//btnCancel = (Button) this.findViewById(R.id.btn_cancel);

		alist = new ArrayList<Sos_Reserved_Data>(); // Data�� ������ Reserved_adapter
		Reserved_adapter = new Sos_ReservedAdapter(this.getParent().getParent(), alist); // �����͸� �ޱ� ���� �����;���� ��ü ����
		// ����Ʈ�信 ����� ����

		userInfo.setText("B011128 ������ ¯¯¯¯");

		Reserved_adapter.add(new Sos_Reserved_Data(getApplicationContext(), "HOLLYS\nCOFFEE",
				"5�� 25��\n7�ÿ� ����Ǽ̽��ϴ�.", R.drawable.ic_launcher));
		Reserved_adapter.add(new Sos_Reserved_Data(getApplicationContext(), "T606",
				"5�� 20��\n7�ÿ� ����Ǽ̽��ϴ�.", R.drawable.ic_launcher));
		Reserved_adapter.add(new Sos_Reserved_Data(getApplicationContext(), "CAFE\nBENEE",
				"9�� 20��\n3�ÿ� ����Ǽ̽��ϴ�.", R.drawable.ic_launcher));
	
		listView.setAdapter(Reserved_adapter);
		*/
		//btnMore.setOnClickListener(mClick);
		//btnCancel.setOnClickListener(mClick);
>>>>>>> 3214e8b5bb8c77ba8d542d188e6289b1fd99d3cd


<<<<<<< HEAD
	 private class Accestask extends AsyncTask<String, Integer, String>{
		/*
		 * AsyncTask�� ���� ���� onPreExecute() -> doInBackground() -> onPostExecute();
		 * AsyncTask�� ��׶��� �۾��� �����ϰ� ���ִ� Ŭ����
		 * AsyncTask�� �ʿ��� ���� ���ͳ� ������ ���� �����忡�� �۵��ϸ� �ȵǱ� ������
		 * ��׶��� �����带 �̿��Ͽ� ���ͳ� ������ �õ��ؾ��Ѵ�.
		 * JSON �Ľ��� �ϱ����ؼ� ���ͳݿ� �����Ͽ��� �ϴµ� �׷������ؼ�
		 * ��׶��� �����带 �̿��ϱ����� extend AsyncTask�� �̿��Ѵ�.
		 */
		 String data = null; // ������� ������ ����
       @Override
       // ���� ���ڴ� String �迭������ Url�� �Է¹޴´�.
       protected String doInBackground(String... url) {
           try{
        	   /* �Է¹��� Url�� �Ѱ��̱� ������ Url[0]�� �Ű������� getJSONFromUrl�� ȣ���Ѵ�.
        	    * �׸��� �� ��ȯ���� data�� �����Ѵ�.
        	    */
               data = getJSONFromUrl(url[0]);
               Log.d("Background Task",data.toString());
           }
           catch(Exception e){
        	   
        	   //������ �����Ұ�� Tag�� "BackgroundTask" �� ���� ������ Logcat�� �����Ѵ�.
               Log.d("Background Task",e.toString());
           }
           return data;   // ���⼭ ���� ���� onPostExecut�� ���ڷ� ����.
       }

       @Override
       //doInBackground�� ���ϰ��� ���ڷ� �޴´�.
       protected void onPostExecute(String result) {
        	try{
        		/* doInBackground�� ���ϰ��� �Ľ��� ���̹Ƿ� JSONArray�� �ؼ� �����Ѵ�.
        		 * �Ľ��� ���� �����ͷ��̺��� �����Ƿ� JSONObject jsonObject = new JSONObject(String);
        		 * JSONArray jsonArray = (JSONArray) jsonObject.get(String);
        		 * �������� �Ľ��� �� ���� (���ͳݿ��� �ȵ���̵� JSON �Ľ��� �˻��ϸ� ���� ���̳����� �Ľ� ��� )
        		 * �׷��� ������ �ƿ� string�� JSONArray�� �����Ѵ�.
				 */
	            JSONObject jsonObject = new JSONObject(result);
        		JSONArray menuitemArray = jsonObject.getJSONArray("sos_reserved");
        		
        		//�Է��� JSONArray�� ũ�⸸ŭ �ݺ����� ������.
	        	for(int i=0; i<menuitemArray.length(); i++)
	        	{
	        		// Login ���� ���� ID(�й�)�� ���� �й��� ���� row�� ���
	        		String tmp_uid=menuitemArray.getJSONObject(i).getString("uid").toString();
	        		if(tmp_uid.equals(user_id)){
		        		Reserved_JsonAdapter.add(new Sos_Reserved_Data(getApplicationContext()
		        				,menuitemArray.getJSONObject(i).getString("uid").toString()
		        				,menuitemArray.getJSONObject(i).getString("reserve_place").toString()
		        				,menuitemArray.getJSONObject(i).getString("reserve_month").toString()
		        				,menuitemArray.getJSONObject(i).getString("reserve_day").toString()
		        				,menuitemArray.getJSONObject(i).getString("start_hour").toString()
		        				,menuitemArray.getJSONObject(i).getString("start_min").toString()));
	        		}
	        	}
	            Log.d("Input Item at Adapter", "OK");
        	}catch(Exception e){
        		e.getStackTrace();
        	}
       }
    }
	 
	public String getJSONFromUrl(String Url){
		String data = "";					// �Ľ��� ���� ���������� �Է¹޾� ������ String ������ ����
		InputStream InStr = null;			// ���ͳݿ� �����Ͽ� ���� �������µ� ����� Stream ����
		
		try {
			URL url = new URL(Url);			// ���ڷ� ���� url�� URL��ü�� ����
			
			//�Է¹��� URL�� ������ HTTP Ŀ�ݼ��� ����
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			
			urlConnection.connect();				// HTTP Ŀ�׼��� �̿��Ͽ� URL�� ����
			InStr = urlConnection.getInputStream();	// ����� URL���� �Է¹��� InputStream�� ����
			
			// InputStream���� ���� ���� ������ ���� ����
			BufferedReader reader = new BufferedReader(new InputStreamReader(InStr));
			StringBuilder sb = new StringBuilder();		// ���۸� ���� ������ ���� ��ĥ StringBuilder ����
			String line = null;				//���ۿ��� �о�帰 ���� �ӽ÷� ������ ���� ����
			
			// ���ۿ� ���� ���� ������ �о ���̻� ���� ���������� �а� ���� ���� ������ �ӽú����� line�� ����
			while((line = reader.readLine())!=null){
				sb.append(line+"\n");		//���ۿ��� ���� ���� line�� StringBuilder�� ��ģ��.
			}
			InStr.close();					// �Է��� �ٿϷ������� ��Ʈ�� ������ ���´�
			data = sb.toString();			// StringBuilder�� ������� ���ڿ��� data�� �����Ѵ�.

		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}catch(ClientProtocolException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			  Log.d("Exception while downloading url", e.toString());
		}
		return data;	// �ϼ��� data�� ��ȯ�Ѵ�.
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
=======
/*
	OnClickListener mClick = new OnClickListener() {
>>>>>>> 3214e8b5bb8c77ba8d542d188e6289b1fd99d3cd

		@SuppressWarnings("deprecation")
		public void onClick(View v) {
			if (v == btnMore) {
				Intent intent = new Intent(
						Sos_Reserved_main.this,
						com.hongikapp.utility.sos.reservingin.Sos_ReservingInSelectSpace.class);
				Sos_TabHost_ReservingIn.ReservingIn_Group
						.replaceView(Sos_TabHost_ReservingIn.ReservingIn_Group
								.getLocalActivityManager()
								.startActivity(
										"Utility_Sos_reserved",
										intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
								.getDecorView());
			} else if (v == btnCancel) {
				// ������ �� �ֵ���
				//Reserved_adapter.delete(new Sos_Reserved_Data(getApplicationContext(), "CAFE\nBENE",
				//		"9�� 20��\n3�ÿ� ����Ǽ̽��ϴ�.", R.drawable.ic_launcher));
				//
			}
		}
	};*/
}
