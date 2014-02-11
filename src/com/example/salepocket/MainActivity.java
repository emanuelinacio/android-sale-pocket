package com.example.salepocket;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;

public class MainActivity extends FragmentActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

	 private TabHost mTabHost;
	  private ViewPager mViewPager;
	  private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, MainActivity.TabInfo>();
	  private ViewPagerAdapter mPagerAdapter;
	  //Banco
	  Banco db = new Banco(this);
	  SQLiteDatabase banco;

	  // Informação da Tab
	  private class TabInfo {
	    private String tag;
	    private Class<?> clss;
	    private Bundle args;
	    private Fragment fragment;

	    TabInfo(String tag, Class<?> clazz, Bundle args) {
	      this.tag = tag;
	      this.clss = clazz;
	      this.args = args;
	    }
	  }

	  // Um simples factory que retorna View para o TabHost
	  class TabFactory implements TabContentFactory {

	    private final Context mContext;

	    public TabFactory(Context context) {
	      mContext = context;
	    }

	    public View createTabContent(String tag) {
	      View v = new View(mContext);
	      v.setMinimumWidth(0);
	      v.setMinimumHeight(0);
	      return v;
	    }
	  }

	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    // Infla o layout
	    setContentView(R.layout.activity_main);
	    // Inicializa o TabHost
	    this.initialiseTabHost(savedInstanceState);
	    if (savedInstanceState != null) {
	      // Define a Tab de acordo com o estado salvo
	      mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
	    }
	    // Inicializa o ViewPager
	    this.intialiseViewPager();
	    
	    
	    //Inicializar Banco de Dados	   	   
	    InicializarBanco();
	    
	  }
	  
	  public void InicializarBanco() {
		  
		banco = openOrCreateDatabase("BDPocket", MODE_WORLD_WRITEABLE, null);
		
		String Verificatabela = db.VerificaTabelas(banco);
		
		if (Verificatabela == "Ok") {
			
			Log.e("BANCO","BANCO DE DADSO CRIADO COM SUCESSO");
			
		}else {
			
			Log.e("BANCO","Erro" + Verificatabela);
		}
		  
	  }

	  protected void onSaveInstanceState(Bundle outState) {
	    // salva a Tab selecionada
	    outState.putString("tab", mTabHost.getCurrentTabTag());
	    super.onSaveInstanceState(outState);
	  }

	  private void intialiseViewPager() {
//new Vector<fragment>();
	    List<Fragment> fragments = new Vector();
	    fragments.add(Fragment.instantiate(this, ClienteActivity.class.getName()));
	    //fragments.add(Fragment.instantiate(this, TabFragmentB.class.getName()));
	    fragments.add(Fragment.instantiate(this, ProdutoActivity.class.getName()));
	    fragments.add(Fragment.instantiate(this, CaixaActivity.class.getName()));
	    this.mPagerAdapter = new ViewPagerAdapter(
	         super.getSupportFragmentManager(), fragments);
	    this.mViewPager = (ViewPager) super.findViewById(R.id.viewpager);
	    this.mViewPager.setAdapter(this.mPagerAdapter);
	    this.mViewPager.setOnPageChangeListener(this);
	  }

	  private void initialiseTabHost(Bundle args) {
	    mTabHost = (TabHost) findViewById(android.R.id.tabhost);
	    mTabHost.setup();
	    TabInfo tabInfo = null;
	    MainActivity.AddTab(this, this.mTabHost,
	         this.mTabHost.newTabSpec("Tab1").setIndicator("Clientes"),
	         (tabInfo = new TabInfo("Tab1", ClienteActivity.class, args)));
	    this.mapTabInfo.put(tabInfo.tag, tabInfo);
	    MainActivity.AddTab(this, this.mTabHost,
	  this.mTabHost.newTabSpec("Tab2").setIndicator("Produtos"),
	  (tabInfo = new TabInfo("Tab2", ProdutoActivity.class, args)));
	    this.mapTabInfo.put(tabInfo.tag, tabInfo);
	    MainActivity.AddTab(this, this.mTabHost,
	  this.mTabHost.newTabSpec("Tab3").setIndicator("Caixa"),
	  (tabInfo = new TabInfo("Tab3", CaixaActivity.class, args)));
	    this.mapTabInfo.put(tabInfo.tag, tabInfo);
	    mTabHost.setOnTabChangedListener(this);
	  }

	  private static void AddTab(MainActivity activity, TabHost tabHost,
	        TabHost.TabSpec tabSpec, TabInfo tabInfo) {
	    // Attach uma Tab view factory para o spec
	    tabSpec.setContent(activity.new TabFactory(activity));
	    tabHost.addTab(tabSpec);
	  }

	  public void onTabChanged(String tag) {
	    // Avisa para o mViewPager qual a Tab que está ativa
	    int pos = this.mTabHost.getCurrentTab();
	    this.mViewPager.setCurrentItem(pos);
	  }

	  @Override
	  public void onPageScrolled(int position, float positionOffset,
	 int positionOffsetPixels) {
	  }

	  @Override
	  public void onPageSelected(int position) {
	    this.mTabHost.setCurrentTab(position);
	  }

	  @Override
	  public void onPageScrollStateChanged(int state) {
	  
	  }
	}