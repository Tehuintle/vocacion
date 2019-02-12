package orientacion.com.areas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import orientacion.com.MenuActivity;
import orientacion.com.R;
import orientacion.com.api.APIClient;
import orientacion.com.api.APIInterface;
import orientacion.com.api.response.ResponseBase;
import orientacion.com.basedatos.DBHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static orientacion.com.areas.RetornarValores.getSumarArea1;
import static orientacion.com.areas.RetornarValores.getSumarArea10;
import static orientacion.com.areas.RetornarValores.getSumarArea2;
import static orientacion.com.areas.RetornarValores.getSumarArea3;
import static orientacion.com.areas.RetornarValores.getSumarArea4;
import static orientacion.com.areas.RetornarValores.getSumarArea5;
import static orientacion.com.areas.RetornarValores.getSumarArea6;
import static orientacion.com.areas.RetornarValores.getSumarArea7;
import static orientacion.com.areas.RetornarValores.getSumarArea8;
import static orientacion.com.areas.RetornarValores.getSumarArea9;

public class RespuestaAreas extends AppCompatActivity {

	private RadioGroup radioGroup;
	private RadioButton vocacionUno, vocacionDos, vocacionTres;

    private TextView txtUsuario;
    private String respuestas="", posicionVocacion1 ="", posicionVocacion2="", posicionVocacion3="", coneccionIP="", curp="";
    private String resultVocacion1="", resultVocacion2="", resultVocacion3="";
    private int SS, EP, V, AP, MS, OG, CT, CI, MC, AL;
	private int primerNumero, segundoNumero, tercerNumero;
	private int posicion1, posicion2, posicion3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.respuesta);

        //txtResultado = (TextView)findViewById(R.id.tvResultado);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
		vocacionUno = (RadioButton) findViewById(R.id.vocacionUno);
		vocacionDos = (RadioButton) findViewById(R.id.vocacionDos);
		vocacionTres = (RadioButton) findViewById(R.id.vocacionTres);

        txtUsuario = (TextView)findViewById(R.id.usuario);
        Bundle bundle = getIntent().getExtras();
		coneccionIP = bundle.getString("key_direccionIP");
		curp = bundle.getString("key_Curp");


        DBHelper admin = new DBHelper(this,null,null,1);
        SQLiteDatabase db = admin.getWritableDatabase();


        /* AQUI CONSULTAMOS LA TABLA Y SACAMOS TODOS LOS PUNTOS */
        Cursor columna1 = db.rawQuery("SELECT puntos FROM areas " , null);
        if (columna1.moveToFirst()) {
            do{
                respuestas += String.valueOf(columna1.getInt(0));
            }while(columna1.moveToNext());
        }
        Log.i("RESULTADO", "TOTAL: "+respuestas);
        /* AQUI LOS SUMAMOS LOS PUNTOS */
        String total = respuestas;

		//OBTENEMOS LA SUMA DE CADA AREA
		SS = getSumarArea1(total); EP = getSumarArea2(total); V = getSumarArea3(total); AP = getSumarArea4(total); MS = getSumarArea5(total);
		OG = getSumarArea6(total); CT = getSumarArea7(total); CI = getSumarArea8(total); MC = getSumarArea9(total); AL = getSumarArea10(total);
		int[] listaNumeros = {SS, EP, V, AP, MS, OG, CT, CI, MC, AL};

		//OBTENEMOS LOS PRIMEROS 3 NUMEROS MAYORES
		buscarNumerosMayores(listaNumeros);

		//Despues pintar los 3 resultados
		seleccionar3Resultados();
    }

	public void buscarNumerosMayores(int[] listaNumeros) {
		for (int x = 0; x < listaNumeros.length; x++) {
			if (listaNumeros[x] > primerNumero) {
				primerNumero = listaNumeros[x];
				posicion1 = x;
			}
		}
		Log.d("RESULTADO", "Posicion1: " + posicion1 + "    Es mayor1: " + primerNumero);
		buscarNumero2(listaNumeros, posicion1/*primerNumero*/);
	}

	private void buscarNumero2(int[] listaNumeros, int posicion1) {
		for (int x = 1; x < listaNumeros.length; x++) {
			if (listaNumeros[x] > segundoNumero) {
				//if (listaNumeros[x] != primerNumero) {
				if (x != posicion1) {
					segundoNumero = listaNumeros[x];
					posicion2 = x;
				}
			}
		}
		//segundoNumero = iNumeroMayor;
		Log.d("RESULTADO", "Posicion2: " + posicion2 + "  Es mayor2: " + segundoNumero);
		buscarNumero3(listaNumeros, posicion1/*primerNumero*/, posicion2/*segundoNumero*/);
	}

	private void buscarNumero3(int[] listaNumeros, int primerNumero, int segundoNumero) {
		for (int x = 1; x < listaNumeros.length; x++) {
			if (listaNumeros[x] > tercerNumero) {
				if (x != primerNumero) {
					if (x != segundoNumero) {
						tercerNumero = listaNumeros[x];
						posicion3 = x;
					}
				}
			}
		}
		Log.d("RESULTADO", "Posicion3: " + posicion3 + " Es mayor3: " + tercerNumero);
		validacionAreas();
	}

	private void validacionAreas() {
		// 0.-SS, 1.-EP, 2.-V, 3.-AP, 4.-MS, 5.-OG, .6.-CT, 7.-CI, 8.-MC, 9.-AL;
		if (posicion1 == 0 ){
			vocacionUno.setText("Humanidades"); //Servicio Social
			posicionVocacion1 = "Humanidades";
		}else if (posicion1 == 1 ){
			vocacionUno.setText("Económico Administrativo");//Ejecutivo Persuasivo
			posicionVocacion1 = "Económico Administrativo";
		}else if (posicion1 == 2 ){
			vocacionUno.setText("Humanidades");//Verbal
			posicionVocacion1 = "Humanidades";
		}else if (posicion1 == 3 ){
			vocacionUno.setText("Químico Biológico"); //Artistico Plástico
			posicionVocacion1 = "Químico Biológico";
		}else if (posicion1 == 4 ){
			vocacionUno.setText("Humanidades"); //Músical
			posicionVocacion1 = "Humanidades";
		}else if (posicion1 == 5 ){
			vocacionUno.setText("Humanidades"); //Organización
			posicionVocacion1 = "Humanidades";
		}else if (posicion1 == 6 ){
			vocacionUno.setText("Físico Matemático"); //Científico
			posicionVocacion1 = "Físico Matemático";
		}else if (posicion1 == 7 ){
			vocacionUno.setText("Físico Matemático");//Cálculo
			posicionVocacion1 = "Físico Matemático";
		}else if (posicion1 == 8 ){
			vocacionUno.setText("Físico Matemático");//Mecánico Constructivo
			posicionVocacion1 = "Físico Matemático";
		}else if (posicion1 == 9 ){
			vocacionUno.setText("Químico Biológico");//Trabajo al áire libre
			posicionVocacion1 = "Químico Biológico";
		}

		if (posicion2 == 0 ){
			vocacionDos.setText("Humanidades");
			posicionVocacion2 = "Humanidades";
		}else if (posicion2 == 1 ){
			vocacionDos.setText("Económico Administrativo");
			posicionVocacion2 = "Económico Administrativo";
		}else if (posicion2 == 2 ){
			vocacionDos.setText("Humanidades");
			posicionVocacion2 = "Humanidades";
		}else if (posicion2 == 3 ){
			vocacionDos.setText("Químico Biológico");
			posicionVocacion2 = "Químico Biológico";
		}else if (posicion2 == 4 ){
			vocacionDos.setText("Humanidades");//Músical
			posicionVocacion2 = "Humanidades";
		}else if (posicion2 == 5 ){
			vocacionDos.setText("Humanidades");//Organización
			posicionVocacion2 = "Humanidades";
		}else if (posicion2 == 6 ){
			vocacionDos.setText("Físico Matemático");
			posicionVocacion2 = "Físico Matemático";
		}else if (posicion2 == 7 ){
			vocacionDos.setText("Físico Matemático");
			posicionVocacion2 = "Físico Matemático";
		}else if (posicion2 == 8 ){
			vocacionDos.setText("Físico Matemático");
			posicionVocacion2 = "Físico Matemático";
		}else if (posicion2 == 9 ){
			vocacionDos.setText("Químico Biológico");
			posicionVocacion2 = "Químico Biológico";
		}


		if (posicion3 == 0 ){
			vocacionTres.setText("Humanidades");
			posicionVocacion3 = "Humanidades";
		}else if (posicion3 == 1 ){
			vocacionTres.setText("Económico Administrativo");
			posicionVocacion3 = "Económico Administrativo";
		}else if (posicion3 == 2 ){
			vocacionTres.setText("Humanidades");
			posicionVocacion3 = "Humanidades";
		}else if (posicion3 == 3 ){
			vocacionTres.setText("Químico Biológico");
			posicionVocacion3 = "Químico Biológico";
		}else if (posicion3 == 4 ){
			vocacionTres.setText("Humanidades");
			posicionVocacion3 = "Humanidades";
		}else if (posicion3 == 5 ){
			vocacionTres.setText("Humanidades");
			posicionVocacion3 = "Humanidades";
		}else if (posicion3 == 6 ){
			vocacionTres.setText("Físico Matemático");
			posicionVocacion3 = "Físico Matemático";
		}else if (posicion3 == 7 ){
			vocacionTres.setText("Físico Matemático");
			posicionVocacion3 = "Físico Matemático";
		}else if (posicion3 == 8 ){
			vocacionTres.setText("Físico Matemático");
			posicionVocacion3 = "Físico Matemático";
		}else if (posicion3 == 9 ){
			vocacionTres.setText("Químico Biológico");
			posicionVocacion3 = "Químico Biológico";
		}
	}


	private boolean checkSelectVocation = false;
	private void seleccionar3Resultados() {
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(vocacionUno.isChecked()){
					posicionVocacion1 = vocacionUno.getText().toString();
					posicionVocacion1 = "1"+posicionVocacion1;
					posicionVocacion2 = vocacionDos.getText().toString();
					posicionVocacion2 = "2"+posicionVocacion2;
					posicionVocacion3 = vocacionTres.getText().toString();
					posicionVocacion3 = "3"+posicionVocacion3;
					Log.i("RESULTADO", "resultado: "+ posicionVocacion1);
					checkSelectVocation = true;
				}else{
					if(vocacionDos.isChecked()){
						posicionVocacion2 = vocacionDos.getText().toString();
						posicionVocacion2 = "1"+posicionVocacion2;
						posicionVocacion1 = vocacionUno.getText().toString();
						posicionVocacion1 = "2"+posicionVocacion1;
						posicionVocacion3 = vocacionTres.getText().toString();
						posicionVocacion3 = "3"+posicionVocacion3;
						Log.i("RESULTADO", "resultado: "+ posicionVocacion2);
						checkSelectVocation = true;
					}else{
						if(vocacionTres.isChecked()){
							posicionVocacion3 = vocacionTres.getText().toString();
							posicionVocacion3 = "1"+posicionVocacion3;
							posicionVocacion1 = vocacionUno.getText().toString();
							posicionVocacion1 = "2"+posicionVocacion1;
							posicionVocacion2 = vocacionDos.getText().toString();
							posicionVocacion2 = "3"+posicionVocacion2;
							Log.i("RESULTADO", "resultado: "+ posicionVocacion3);
							checkSelectVocation = true;
						}
					}
				}
			}
		});
	}


	public void viewGuardar(View v){
		if (checkSelectVocation == true){
			ordenarLista(posicionVocacion1, posicionVocacion2, posicionVocacion3);
			//finish();
		}else {
			FancyToast.makeText(RespuestaAreas.this, "Favor seleccionar una vocación", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
		}
	}

	private APIInterface apiInterface;
	private void initService() {
		Log.i("RESPUESTA: ", ""+coneccionIP+ "  curp: "+curp);
		apiInterface = APIClient.getClient(coneccionIP).create(APIInterface.class);
		Call<ResponseBase> call3 = apiInterface.registrarAreas(curp, resultVocacion1, resultVocacion2, resultVocacion3);
		call3.enqueue(new Callback<ResponseBase>() {
			@Override
			public void onResponse(Call<ResponseBase> call, Response<ResponseBase> response) {
				if (response.body().estatus){
					Log.i("RESPUESTA: ", ""+response.body().mensaje);
					pasarSiguiente(response);
				}
			}
			@Override
			public void onFailure(Call<ResponseBase> call, Throwable t) {
				Log.e("ERROR", ""+t.getMessage());
				//dialog.dismiss();
				FancyToast.makeText(RespuestaAreas.this,"Verificar dirección IP, no se pudo conectar",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
				//Toast.makeText(ConectarIP.this, "IP incorrecto", Toast.LENGTH_LONG).show();
				call.cancel();
			}
		});
	}

	private void pasarSiguiente(Response<ResponseBase> response) {
		FancyToast.makeText(RespuestaAreas.this,""+response.body().mensaje, FancyToast.LENGTH_LONG, FancyToast.SUCCESS,false).show();
		Intent intent = new Intent(RespuestaAreas.this, MenuActivity.class);
		intent.putExtra("key_direccionIP", coneccionIP);
		intent.putExtra("key_Curp", curp);
		startActivityForResult(intent, 1);
		this.finish();
	}


	private void ordenarLista(String vocacion1, String vocacion2, String vocacion3) {
		List theList = new ArrayList<>();
		theList.add(vocacion1);
		theList.add(vocacion2);
		theList.add(vocacion3);
		Log.d("RESULTADO", ":::::::: Lista original: -----");
		//showList(theList);
		Collections.sort(theList);
		Log.d("RESULTADO", ":::::::: Lista ordenada: -----");
		showList(theList);
	}

	private void showList(List theList) {
		String data = "";
		int size = theList.size();
		for(int i=0; i<size; i++){
			data += String.valueOf(theList.get(i))+"\n";
			if (String.valueOf(theList.get(i)).contains("1")){
				String variable1 = String.valueOf(theList.get(i));
				resultVocacion1 = variable1.substring(1);
				Log.d("RESULTADO", "Ordenacion:::::::: contains 1: " +resultVocacion1);
			}
			if (String.valueOf(theList.get(i)).contains("2")){
				String variable2 = String.valueOf(theList.get(i));
				resultVocacion2 = variable2.substring(1);
				Log.d("RESULTADO", "Ordenacion:::::::: contains 2: " +resultVocacion2);
			}
			if (String.valueOf(theList.get(i)).contains("3")){
				String variable3 = String.valueOf(theList.get(i));
				resultVocacion3 = variable3.substring(1);
				Log.d("RESULTADO", "Ordenacion:::::::: contains 3: " +resultVocacion3);
			}
		}
		Log.d("RESULTADO", "Ordenacion:::::::: Resultado: " +data);
		initService();
	}



	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.salir, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.idSalir:
                finish();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}

