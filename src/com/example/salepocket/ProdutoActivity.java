package com.example.salepocket;

import java.util.ArrayList;
import java.util.List;

import com.example.salepocket.R.id;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProdutoActivity extends Fragment {
	
	private Button btnprodcad;
	private Activity prod;
	View fragprod;
	
	//Banco
	private Banco db;
	  SQLiteDatabase banco;
	  
    //Componentes
	  EditText edt_filtro_pro;
	  TextView edt_prod_desc, edt_cad_vlr_prod;
	  ListView list_prod;
	  ImageButton btnprodcadconfirm;
	  
	  //Variavéis Gerais
	  private List<String> lstparagridprod = new ArrayList<String>();
	  private String[] templstparagridprod;
	
	
	 @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	          Bundle savedInstanceState) {
	  
	    if (container == null) {
	      return null;
	    }
	    
	    db = new Banco(getActivity());
	    
	    fragprod = (RelativeLayout) inflater.inflate(R.layout.produto_main, container, false);
	    
	    //Iniciando Componentes	    
	    btnprodcad = (Button) fragprod.findViewById(R.id.btn_cad_prod);
	    edt_filtro_pro = (EditText) fragprod.findViewById(id.edt_filtro_prod);
	    list_prod = (ListView) fragprod.findViewById(R.id.list_produto);
	  
	    //Listagem Produtos List View
	    lstparagridprod = db.ListarProduto();
	    
	    templstparagridprod = new String[lstparagridprod.size()];
	    
	    int i = 0;
	    
	    for (String teste : lstparagridprod) {
	    	
	    	templstparagridprod[i] = teste;
	    	i++;
	    }
	    
	    //listagem no list view produtos
	    
	    ArrayAdapter<String> ad = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, lstparagridprod);
	    
	    list_prod.setAdapter(ad);
	  		
	  	   list_prod.setAdapter(ad);
	  	   
	  	   CarregarEncontradosListView();
	  	   
	  	   edt_filtro_pro.addTextChangedListener(new TextWatcher() {
	  		
	  		   public void afterTextChanged(Editable s)
	  		      {
	  		          // Abstract Method of TextWatcher Interface.
	  		      }

	  		      public void beforeTextChanged(CharSequence s, int start, int count, int after)
	  		      {
	  		          // Abstract Method of TextWatcher Interface.
	  		      }

	  		      //Evento acionado quando o usuário teclar algo
	  		      //na caixa de texto "Procurar"
	  		      public void onTextChanged(CharSequence s, int start, int before, int count)
	  		      {
	  		    	  CarregarEncontradosListView();

	  		          //Carrega o listview com os itens encontrados
	  		    	list_prod.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, lstparagridprod));
	  		      }
	  	});
	    
	    
	    
	    //Botão cadastrar produto
	    btnprodcad.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TelaCadastroProduto();
			}
			

    
		    private void TelaCadastroProduto() {
		    	
		    	prod = getActivity();
		    	
		    	prod.setContentView(R.layout.produto_cad);
		    	
		    	//Carregar componentes
			    edt_cad_vlr_prod = (TextView) fragprod.findViewById(R.id.edt_valor_cad_prod);
			    edt_prod_desc = (TextView) fragprod.findViewById(R.id.edt_desc_prod);
			    btnprodcadconfirm = (ImageButton) fragprod.findViewById(R.id.imgbtncad_prod);
		    	
		    	
			    btnprodcadconfirm.setOnClickListener( new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					
						Produto produto = new Produto();
						
						//Converte String para double
						String TMPVLRPROD = edt_cad_vlr_prod.getText().toString();
						produto.setDESCRICAO( edt_prod_desc.getText().toString() );
						produto.setVLR_UNITARIO ( 	Double.parseDouble( TMPVLRPROD )  );
					
						String logerro = db.InserirProduto(produto);
						
						if(logerro == "Ok" ){
							Log.e("BANCO", logerro);
						}else{
							Log.e("BANCO", logerro);
						}
						
						
					}
				});
		    }
		    
		});	    
 
	    return fragprod;
	  }
	 
	public void CarregarEncontradosListView() // Metodo para carregar dados listview de Clientes
    {
        int textlength = edt_filtro_pro.getText().length();
 
        lstparagridprod.clear();
   
        for (int i = 0; i < templstparagridprod.length; i++)
        {
            if (textlength <= templstparagridprod[i].length())
            {
                if(edt_filtro_pro.getText().toString().equalsIgnoreCase((String)templstparagridprod[i].subSequence(0, textlength)))
                {
                	lstparagridprod.add(templstparagridprod[i]);
                }
            }
        }
    }	

}
