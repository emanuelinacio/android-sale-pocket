package com.example.salepocket;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.salepocket.R.id;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
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
	
	//Global Class
	private Caixa obj_caixa;
	
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
					
				SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");
				
				String TMPDATECASH = edttextdate.getText().toString();
				String TMPOPENINGBALANCE = edttextinitialbalance.getText().toString();
				
				if( TMPDATECASH.equals("") || TMPOPENINGBALANCE.equals("") ){
					edttextdate.setFocusableInTouchMode(true);
					edttextdate.requestFocus();
					return;
				}
				

				Date datecash = new Date();
				
				//Convert date
				try {
					datecash = formater.parse(TMPDATECASH);
				} catch (Exception e) {
					// TODO: handle exception

				}
				
				Caixa obj_caixa = new Caixa( datecash, Double.valueOf( TMPOPENINGBALANCE.replace(",", ".") ) );
				
				Log.e("TESTE", "TESTE");
				
					
			}
		});

		//Button Edit Sale
		imgbtnedtsale.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ScreenSale();
				
			}
		});
		

		
	
		return fragcaixa;
	}
	
	private void ScreenSale()
	{
		caixa = getActivity();
		
		caixa.setContentView(R.layout.sale_main);
		
	}

	
	private void Load_components()
	{
		//Upload Component
		
		imgbtnedtsale = (ImageButton) fragcaixa.findViewById(id.imgbtnedtsale);
		imgbtnedtexpense = (ImageButton) fragcaixa.findViewById(id.imgbtnedtexpense);
		imgbtnopencash = (ImageButton) fragcaixa.findViewById(id.imgbtnopencash);
		
		edttextinitialbalance = (EditText) fragcaixa.findViewById(id.edttxtinitialbalance);
		edttextclosingbalance = (EditText) fragcaixa.findViewById(id.edttextclosingbalance);
		edttextsale = (EditText) fragcaixa.findViewById(id.edttextsale);
		edttextexpense = (EditText) fragcaixa.findViewById(id.edttextexpense);
		edttextdate = (EditText) fragcaixa.findViewById(id.edttextdate);
	}
	

	
}
