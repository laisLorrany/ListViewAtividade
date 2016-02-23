package com.example.linearlayoutexe;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity implements TextWatcher{
	
	ArrayAdapter<String> arrayAdapter;
	ArrayList<String> nomes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		EditText nomeEditText = (EditText) findViewById(R.id.nomeEditText);
		nomeEditText.addTextChangedListener(this);
		
		ListView listView = (ListView) findViewById(R.id.nomesListView);
		nomes = new ArrayList<String>();
		
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
		
		String nome = s.toString();
		nomes.add(nome);	
		arrayAdapter.notifyDataSetChanged();
		
	}
}
