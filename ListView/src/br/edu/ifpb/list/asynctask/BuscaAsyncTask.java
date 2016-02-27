package br.edu.ifpb.list.asynctask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.edu.ifpb.list.util.HttpService;
import br.edu.ifpb.list.util.Response;

public class BuscaAsyncTask  extends AsyncTask<JSONObject, Void, Response> {
	
	Response response;
	Context context;
	ListView list;
	
	public BuscaAsyncTask(Context context, ListView list) {
		this.context = context;
		this.list = list;
	}

	@Override
	protected Response doInBackground(JSONObject... params) {

		try {
			response = HttpService.sendJSONPostRequest("get-byname", params[0]);
			
		} catch (MalformedURLException e) {
			Log.e("BuscaAsyncTask - doInBackground", e.getMessage());
			
		} catch (IOException e) {
			Log.e("BuscaAsyncTask - doInBackground", e.getMessage());
			
		}
		
		return response;
	}
	
	@Override
	protected void onPostExecute(Response result) {
		
		/*String nomes = result.getContentValue();
		try{
			Gson gson = new Gson();
			JSONObject json = gson.fromJson(nomes, null);
			
		}catch(Exception e){
			Log.e("BuscaAsyncTask", e.getMessage());
		}
		*/
		ArrayList<String> nomes = new ArrayList<String>();
		
		if(result != null){
			try {
				JSONArray jsonResult = new JSONArray(result.getContentValue());
				
				for(int i=0; i < jsonResult.length(); i++){
					JSONObject json = jsonResult.getJSONObject(i);
					String nome = json.getString("fullName");
					nomes.add(nome);
				}
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>
				(context, android.R.layout.simple_list_item_1, nomes);
				adapter.notifyDataSetChanged();
				list.setAdapter(adapter);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			
			nomes.clear();
			list.getAdapter().notify();
		}
			
	}
	
}
