package com.example.salepocket;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

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

public class ClienteActivity extends Fragment {

	private Button btnclicad;
	private Activity cli;
	View fragcli;
	//Banco
	private Banco db;
	  SQLiteDatabase banco;
	
	// Componentes
	ImageButton imgbtncadcli; 
	TextView edt_cli_razao, edt_cli_endereco, edt_cli_contato; //Cadastro
	TextView edt_filtro_cliente;
	ListView list_cliente;
	
	//Variaveis Gerais
	private List<String> listparagrid = new ArrayList<String>();
	private String[] templistparagrid;
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if (container == null) {
			return null;
		}
		
		db = new Banco(getActivity());

		fragcli = (RelativeLayout) inflater.inflate(R.layout.cliente_main,container, false);
		;
		
		//Inicializando componentes
		btnclicad = (Button) fragcli.findViewById(R.id.btn_cad_cli);
		edt_filtro_cliente = (TextView) fragcli.findViewById(R.id.edt_filtro_cli); //Listagem
		list_cliente = (ListView) fragcli.findViewById(R.id.list_cliente); // Listagem
		
		
		
		//Listagem Clientes List View
		
		listparagrid = db.ListarCliente();
		
		templistparagrid = new String[listparagrid.size()];
		
		int i = 0;
		
		for (String itemcliente : listparagrid) {
			
			templistparagrid[i] = itemcliente;
			i++;
			
		}
		
		//listagem no list view
	 	
	   ArrayAdapter<String> ad = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, listparagrid);
		
	   list_cliente.setAdapter(ad);
	   
	   CarregarEncontradosListView();
	   
	   edt_filtro_cliente.addTextChangedListener(new TextWatcher() {
		
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
		    	  list_cliente.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, listparagrid));
		      }
	});
		
       // Botão Cadastro Cliente

		btnclicad.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Tela_cad_cliente();

			}

			private void Tela_cad_cliente() {
				// TODO Auto-generated method stub

				cli = getActivity();

				cli.setContentView(R.layout.cliente_cad);

				imgbtncadcli = (ImageButton) cli.findViewById(id.imgbtncad_prod);

				edt_cli_razao = (TextView) cli
						.findViewById(id.edt_valor_cad_prod);
				edt_cli_endereco = (TextView) cli
						.findViewById(id.edt_desc_prod);
				edt_cli_contato = (TextView) cli
						.findViewById(id.edt_contato_cad_cli);

				imgbtncadcli.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						String TMPRAZAO = edt_cli_razao.getText().toString();
						String TMPEND = edt_cli_endereco.getText().toString();
						String TMPCONTATO = edt_cli_contato.getText()
								.toString();

						Cliente insertcli = new Cliente(TMPRAZAO, TMPEND,
								TMPCONTATO);
						
						 String Logerro = db.InserirCliente(insertcli);
						 
						 if(Logerro == "Ok") {
							 Log.e("BANCO", "CLIENTE CADASTRADO");
							 							 							 
							 
						 }else {
							 Log.e("ERRO DO BANCO", Logerro);
						 }						

					}
				});

			}
		});

		return fragcli;
		
		
	}
	
	public void CarregarEncontradosListView() // Metodo para carregar dados listview de Clientes
    {
        int textlength = edt_filtro_cliente.getText().length();
 
        listparagrid.clear();
   
        for (int i = 0; i < templistparagrid.length; i++)
        {
            if (textlength <= templistparagrid[i].length())
            {
                if(edt_filtro_cliente.getText().toString().equalsIgnoreCase((String)templistparagrid[i].subSequence(0, textlength)))
                {
                	listparagrid.add(templistparagrid[i]);
                }
            }
        }
    }	


}