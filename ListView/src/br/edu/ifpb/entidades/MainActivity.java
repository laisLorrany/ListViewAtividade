package br.edu.ifpb.entidades;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import br.edu.ifpb.list.asynctask.BuscaAsyncTask;
import br.edu.ifpb.listview.R;

public class MainActivity extends Activity implements TextWatcher{
	
	final int TAMANHO_MINIMO = 4;

	ArrayAdapter<String> arrayAdapter;
	ArrayList<String> nomes = new ArrayList<String>();
	ListView listView;
	JSONObject json = new JSONObject();	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		EditText nomeEditText = (EditText) findViewById(R.id.nomeEditText);
		nomeEditText.addTextChangedListener(this);
				
		listView = (ListView) findViewById(R.id.nomesListView);		
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes);
		listView.setAdapter(arrayAdapter);
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		Log.i("afterTextChanged", s.toString());
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		Log.i("beforeTextChanged", s.toString());
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		
		Log.i("onTextChanged", s.toString());
		
		/*String nome = s.toString();
		nomes.add(nome);	
		arrayAdapter.notifyDataSetChanged();*/
		if(s.length() >= TAMANHO_MINIMO){
			try {	
				json.put("fullName", s.toString());
			} catch (JSONException e) {
				Log.e("Main Activity", e.getMessage());
			}
			
			BuscaAsyncTask buscaAsyncTask = new BuscaAsyncTask(this, listView);
			buscaAsyncTask.execute(json);
		}else{
			nomes.clear();
			arrayAdapter.notifyDataSetChanged();
		}
		
	}
}
