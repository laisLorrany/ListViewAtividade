package br.edu.ifpb.list.asynctask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import br.edu.ifpb.list.activity.MainActivity;
import br.edu.ifpb.list.entidades.Pessoa;
import br.edu.ifpb.list.util.HttpService;
import br.edu.ifpb.list.util.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BuscaAsyncTask  extends AsyncTask<Pessoa, Void, Response> {
	
	Response response;
	MainActivity mainActivity;
	ListView list;
	
	public BuscaAsyncTask(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	@Override
	protected Response doInBackground(Pessoa... params) {

		try {
			Gson gson = new Gson();
			String pessoa = gson.toJson(params[0]);
			JSONObject json = new JSONObject(pessoa);
			response = HttpService.sendJSONPostRequest("get-byname", json);
			
		} catch (MalformedURLException e) {
			Log.e("BuscaAsyncTask - doInBackground", e.getMessage());
			
		} catch (IOException e) {
			Log.e("BuscaAsyncTask - doInBackground", e.getMessage());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		 int codeHttp = response.getStatusCodeHttp();

	        Log.i("EditTextListener", "Código HTTP: " + codeHttp
	                + " Conteúdo: " + response.getContentValue());

	        if (codeHttp == HttpURLConnection.HTTP_OK) {
	        	Gson gson = new Gson();
	            List<Pessoa> pessoas = gson.fromJson(response.getContentValue(),
	                    new TypeToken<ArrayList<Pessoa>>(){}.getType());

	            mainActivity.backBuscarNome(pessoas);
	        }
			
	}
	
}
