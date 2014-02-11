package com.example.salepocket;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Banco extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_NAME = "BDPocket";

	public Banco(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}
	

	//  ------- INICIALIZAR BANCO Verificaçãode Tabelas Criadas
	public String VerificaTabelas(SQLiteDatabase db) {

		String Erro = "Ok";

		String sql = "CREATE TABLE  IF NOT EXISTS clientes" // tabela de
				+ "(ID INTEGER PRIMARY KEY autoincrement, RAZAO TEXT, ENDERECO TEXT, TELEFONE TEXT);";

		try {
			db.execSQL(sql);
		} catch (Exception erro) {

			Erro = "" + erro;

		}

		sql = "CREATE TABLE IF NOT EXISTS produtos" // tabela de produtos
				+ "(ID INTEGER PRIMARY KEY autoincrement, PROD_DESCRICAO TEXT, VALOR FLOAT, ESTOQUE INTEGER);";

		try {
			db.execSQL(sql);
		} catch (Exception erro) {
			Erro = "" + erro;
		}

		sql = "CREATE TABLE IF NOT EXISTS condpgto" // tabela condição de
													// pagamento
				+ "(ID INTEGER PRIMARY KEY autoincrement, PGTO_DESCRICAO TEXT);";

		try {
			db.execSQL(sql);
		} catch (Exception erro) {
			Erro = "" + erro;
		}
		
		sql = "CREATE TABLE IF NOT EXISTS caixa" // tabela caixa
				+ "(ID INTEGER PRIMARY KEY autoincrement, CAIXA_DATA DATE,"
				+ "SALDO_INICIAL DOUBLE,"
				+ "SALDO_FINAL DOUBLE,";

		try {
			db.execSQL(sql);
		} catch (Exception erro) {
			Erro = "" + erro;
		}		

		sql = "CREATE TABLE IF NOT EXISTS venda" // tabela venda
				+ "(ID INTEGER PRIMARY KEY autoincrement, VD_DATA DATE,"
				+ "ID_CLIENTES INTEGER NOT NULL, "
				+ "ID_CONDPGTO INTERGER NOT NULL,"
				+ "FOREIGN KEY (ID_CONDPGTO) REFERENCES condpgto(ID)"
				+ "FOREIGN KEY (ID_CLIENTES) REFERENCES clientes(ID));";

		try {
			db.execSQL(sql);
		} catch (Exception erro) {
			Erro = "" + erro;
		}

		sql = "CREATE TABLE IF NOT EXISTS itemvenda"// tabela itemvenda
				+ "(ID INTEGER PRIMARY KEY autoincrement, QTD INTEGER, VALORTOTAL FLOAT,"
				+ "ID_VENDA INTEGER NOT NULL, "
				+ "ID_PRODUTOS INTEGER NOT NULL,"
				+ "FOREIGN KEY (ID_VENDA) REFERENCES venda (ID),"
				+ "FOREIGN KEY (ID_PRODUTOS) REFERENCES produtos (ID));";

		try {
			db.execSQL(sql);
		} catch (Exception erro) {
			Erro = "" + erro;
		}

		return Erro;

	}
	
	public String InserirProduto( Produto prod ) {
	
		String Erro = "Ok";
		
		try {
			
			SQLiteDatabase db = this.getWritableDatabase();
			//Montando Vetor para inserir
			ContentValues vetorprod = new ContentValues();
			vetorprod.put( "PROD_DESCRICAO", prod.getDESCRICAO() );
			vetorprod.put( "VALOR", prod.getVLR_UNITARIO() );
			
			db.insert("produtos", null, vetorprod);
		} catch (Exception e) {
			Erro = "" + e;
		}
		
		return Erro;
	}
	
	
	public String InserirCliente(Cliente cli) {
		
		String Erro = "Ok";		
		
		try {
			
			SQLiteDatabase db = this.getWritableDatabase();
			//Montanto Vetor para inserir
			ContentValues vetorcli = new ContentValues();
			vetorcli.put("RAZAO",cli.getRAZAO());
			vetorcli.put("ENDERECO",cli.getENDERECO());
			vetorcli.put("TELEFONE",cli.getTELEFONE());
			
			db.insert("clientes",null,vetorcli);			
			
		} catch (Exception e) {
			// TODO: handle exception
			Erro = "" + e;
		}		
		
		return Erro;		
	}
	

	public List<String> ListarCliente() {
		
		//Selecao do Banco
				String retornoquery = "SELECT * FROM clientes";
				
				SQLiteDatabase db = this.getWritableDatabase();
				Cursor cursor = db.rawQuery(retornoquery, null);
				
				List<String> retorno = new ArrayList<String>();
						
				
				//Laço do cursor retorno da listagem dos Clientes
				if(cursor.moveToFirst()){
					do{

						String cliente;
						cliente = cursor.getString(1);// 1 = RAZAO

						//Adicionando no vetor
						
						retorno.add(cliente); 

						
					}while(cursor.moveToNext());
				}
				
				db.close();
				return retorno; // Retorna Lista de Clientes
		
	}
	
	public List<String> ListarProduto() {
		
		//Selecao do Banco
				String retornoquery = "SELECT * FROM produtos";
				
				SQLiteDatabase db = this.getWritableDatabase();
				Cursor cursor = db.rawQuery(retornoquery, null);
				
				List<String> retorno = new ArrayList<String>();
										
				//Laço do cursor retorno da listagem dos Clientes
				if(cursor.moveToFirst()){
					do{

						String produto;
						produto = cursor.getString(1);// 1 = RAZAO

						//Adicionando no vetor
						
						retorno.add(produto); 
						
					}while(cursor.moveToNext());
				}
				
				db.close();
				return retorno; // Retorna Lista de Clientes		
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
