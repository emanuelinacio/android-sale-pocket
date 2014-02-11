package com.example.salepocket;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class CaixaActivity extends Fragment {
	
	//Inicializando Tela
	private Activity caixa;
	View fragcaixa;
	
	//Banco de Dados
	private Banco db;
	  SQLiteDatabase banco;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if (container == null) {
			return null;
		}
		
		fragcaixa = (RelativeLayout) inflater.inflate(R.layout.caixa_main,container, false);
		
	
		return fragcaixa;
	}
	
}
