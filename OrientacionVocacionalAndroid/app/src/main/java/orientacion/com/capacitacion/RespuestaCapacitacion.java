package orientacion.com.capacitacion;

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

import static orientacion.com.areas.RetornarValores.getSumarCapacitacion1;
import static orientacion.com.areas.RetornarValores.getSumarCapacitacion2;
import static orientacion.com.areas.RetornarValores.getSumarCapacitacion3;
import static orientacion.com.areas.RetornarValores.getSumarCapacitacion4;

public class RespuestaCapacitacion extends AppCompatActivity {

	private RadioGroup radioGroup;
	private RadioButton vocacionUno, vocacionDos, vocacionTres;

    private TextView txtResultado, txtUsuario;
	private String respuestas="", posicionVocacion1 ="", posicionVocacion2="", posicionVocacion3="",
			coneccionIP="", curp="";
	private static String resultVocacion1="", resultVocacion2="", resultVocacion3="";
	private int INF, COST, COC, BE;
	private int primerNumero, segundoNumero, tercerNumero;
	private int posicion1, posicion2, posicion3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.respuesta);

		radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
		vocacionUno = (RadioButton) findViewById(R.id.vocacionUno);
		vocacionDos = (RadioButton) findViewById(R.id.vocacionDos);
		vocacionTres = (RadioButton) findViewById(R.id.vocacionTres);

        txtResultado = (TextView)findViewById(R.id.tvResultado);
        txtUsuario = (TextView)findViewById(R.id.usuario);
        Bundle bundle = getIntent().getExtras();
		coneccionIP = bundle.getString("key_direccionIP");
		curp = bundle.getString("key_Curp");


        DBHelper admin = new DBHelper(this,null,null,1);
        SQLiteDatabase db = admin.getWritableDatabase();


        /* AQUI CONSULTAMOS LA TABLA Y SACAMOS TODOS LOS PUNTOS */
        Cursor columna1 = db.rawQuery("SELECT puntos FROM usuarios " , null);
        if (columna1.moveToFirst()) {
            do{
                respuestas += String.valueOf(columna1.getInt(0));
            }while(columna1.moveToNext());
        }
        Log.i("RESULTADO", "TOTAL: "+respuestas);

        /* AQUI LOS SUMAMOS LOS PUNTOS */
        String total = respuestas;

		INF = getSumarCapacitacion1(total); COST = getSumarCapacitacion2(total); COC = getSumarCapacitacion3(total); BE = getSumarCapacitacion4(total);
		int[] listaNumeros = {INF, COST, COC, BE};
		//OBTENEMOS LOS PRIMEROS 3 NUMEROS MAYORES
		buscarNumerosMayores(listaNumeros);

		//Despues pintar los 3 resultados
		seleccionar3Resultados();
	}

	/*
	* RESPUESTA: Capacitacion1: 27
01-24 01:40:23.806 6064-6064/orientacion.com D/RESPUESTA: Capacitacion2: 19
01-24 01:40:23.807 6064-6064/orientacion.com D/RESPUESTA: Capacitacion3: 18
01-24 01:40:23.807 6064-6064/orientacion.com D/RESPUESTA: Capacitacion4: 10
*/
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
				if (x != posicion1) {
					segundoNumero = listaNumeros[x];
					posicion2 = x;
				}
			}
		}
		//segundoNumero = iNumeroMayor;
		Log.d("RESULTADO", "Posicion2: " + posicion2 + "  Es mayor2: " + segundoNumero);
		buscarNumero3(listaNumeros, posicion1, posicion2);
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
		validacionCapacitaciones();
	}

	private void validacionCapacitaciones() {
		// 0.-INF, 1.-COST, 2.-COC, 3.-BE;
		if (posicion1 == 0 ){
			vocacionUno.setText("Informática");
			posicionVocacion1 = "Informática";
		}else if (posicion1 == 1 ){
			vocacionUno.setText("Costura");
			posicionVocacion1 = "Costura";
		}else if (posicion1 == 2 ){
			vocacionUno.setText("Cocina");
			posicionVocacion1 = "Cocina";
		}else if (posicion1 == 3 ){
			vocacionUno.setText("Belleza (Corte y Peínado)");
			posicionVocacion1 = "Belleza (Corte y Peínado)";
		}


		if (posicion2 == 0 ){
			vocacionDos.setText("Informática");
			posicionVocacion2 = "Informática";
		}else if (posicion2 == 1 ){
			vocacionDos.setText("Costura");
			posicionVocacion2 = "Costura";
		}else if (posicion2 == 2 ){
			vocacionDos.setText("Cocina");
			posicionVocacion2 = "Cocina";
		}else if (posicion2 == 3 ){
			vocacionDos.setText("Belleza (Corte y Peínado)");
			posicionVocacion2 = "Belleza (Corte y Peínado)";
		}


		if (posicion3 == 0 ){
			vocacionTres.setText("Informática");
			posicionVocacion3 = "Informática";
		}else if (posicion3 == 1 ){
			vocacionTres.setText("Costura");
			posicionVocacion3 = "Costura";
		}else if (posicion3 == 2 ){
			vocacionTres.setText("Cocina");
			posicionVocacion3 = "Cocina";
		}else if (posicion3 == 3 ){
			vocacionTres.setText("Belleza (Corte y Peínado)");
			posicionVocacion3 = "Belleza (Corte y Peínado)";
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
			FancyToast.makeText(RespuestaCapacitacion.this, "Favor seleccionar una vocación", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
		}
	}


	private APIInterface apiInterface;
	private void initService() {
		Log.i("RESPUESTA: ", ""+coneccionIP+ "  curp: "+curp);
		apiInterface = APIClient.getClient(coneccionIP).create(APIInterface.class);
		Call<ResponseBase> call3 = apiInterface.registrarCapacitacion(curp, resultVocacion1, resultVocacion2, resultVocacion3);
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
				Log.e("ERROR",""+t.getMessage());
				//dialog.dismiss();
				FancyToast.makeText(RespuestaCapacitacion.this,"Verificar dirección IP, no se pudo conectar",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
				//Toast.makeText(ConectarIP.this, "IP incorrecto", Toast.LENGTH_LONG).show();
				call.cancel();
			}
		});
	}

	private void pasarSiguiente(Response<ResponseBase> response) {
		FancyToast.makeText(RespuestaCapacitacion.this,""+response.body().mensaje, FancyToast.LENGTH_LONG, FancyToast.SUCCESS,false).show();
		Intent intent = new Intent(RespuestaCapacitacion.this, MenuActivity.class);
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
		Log.d("RESULTADO", "Ordenacion:::::::: Resultado fin: " +data);
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

    //  21043401 = 15
	//  01402240 = 13
	//  12342123 = 18
	//  40034013 = 15
}

