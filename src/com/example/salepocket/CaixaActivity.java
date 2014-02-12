package com.example.salepocket;

import java.util.Calendar;

import com.example.salepocket.R.id;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class CaixaActivity extends Fragment {
	
	//Inicializando Tela
	private Activity caixa;
	View fragcaixa;
	
	//starting components
	private
	ImageButton imgbtnedtsale, imgbtnedtexpense, imgbtnopencash;
	EditText edttextinitialbalance, edttextclosingbalance, edttextsale, edttextexpense, edttextdate;
	
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
		
		Load_components();
		
		//Button Open Cash
		imgbtnopencash.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

					
					String TMPDATE = edttextdate.getText().toString();

					if( TMPDATE == "" ){
					edttextdate.setFocusableInTouchMode(true);
					edttextdate.requestFocus();
					
				}
			}
		});

		//Button Edit Sale
		imgbtnedtsale.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				

				
			}
		});
		
	
		return fragcaixa;
	}
	
	private void Load_components()
	{
		//Upload Component
		
		imgbtnedtsale = (ImageButton) fragcaixa.findViewById(id.imgbtnedtsale);
		imgbtnedtexpense = (ImageButton) fragcaixa.findViewById(id.imgbtnedtexpense);
		imgbtnopencash = (ImageButton) fragcaixa.findViewById(id.imgbtnopencash);
		
		edttextinitialbalance = (EditText) fragcaixa.findViewById(id.edttextinitialbalance);
		edttextclosingbalance = (EditText) fragcaixa.findViewById(id.edttextclosingbalance);
		edttextsale = (EditText) fragcaixa.findViewById(id.edttextsale);
		edttextexpense = (EditText) fragcaixa.findViewById(id.edttextexpense);
		edttextdate = (EditText) fragcaixa.findViewById(id.edttextdate);
	}
	

	
}
