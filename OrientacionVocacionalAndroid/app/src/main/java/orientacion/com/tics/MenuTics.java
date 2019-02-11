package orientacion.com.tics;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import orientacion.com.MenuActivity;
import orientacion.com.R;
import orientacion.com.api.APIClient;
import orientacion.com.api.APIInterface;
import orientacion.com.api.response.ResponseBase;
import orientacion.com.api.response.TicsResponse;
import orientacion.com.basedatos.DBHelper;
import orientacion.com.util.LoadJSONFromAssetTics;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuTics extends AppCompatActivity {

    private SwipePlaceHolderView mSwipeView;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private int indexOfTarget = 1;
    private DBHelper admin;
    private SQLiteDatabase bdatos;
    private ContentValues values;
    private String coneccionIP="", curp="", idAlumno="";
    private String tics1 ="NO", tics2 ="NO", tics3 ="NO", tics4 ="NO", tics5 ="NO",
					tics6 ="NO", tics7 ="NO", tics8 ="NO", tics9 ="NO", tics10 ="NO";
    private String message = "Verificar dirección IP, no se pudo conectar";
	private boolean isSelected = false;
	private SpotsDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_preguntas);

        mSwipeView = (SwipePlaceHolderView)findViewById(R.id.swipeView);
		dialog = new SpotsDialog(MenuTics.this);

        Bundle bundle = getIntent().getExtras();
        coneccionIP = bundle.getString("key_direccionIP");
        curp = bundle.getString("key_Curp");
		idAlumno = bundle.getString("key_IdAlumno");

		TextView tvTitle = (TextView)findViewById(R.id.tvTitle);
		tvTitle.setText("Orientacion Vocacional");
		Button btnSiguiente = (Button)findViewById(R.id.btnSiguiente);
		btnSiguiente.setText("Guardar");

        //CONFIGURAMOS CARDVIEW PARA LAS PREGUNTAS
        mSwipeView.disableTouchSwipe();
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f));


        //PINTAMOS LAS RESPUESTAS
        try {
            for(TicsResponse datos : LoadJSONFromAssetTics.loadContent(this.getApplicationContext(), "tics_table.json")){
                mSwipeView.addView(new CardViewTics(this, datos, mSwipeView));
                arrayList.add(datos.getPregunta());
            }
        }catch (NullPointerException e){
            Log.i("RESULTADO", "No se encontro resultados");
        }


        /* conectamos la base de datos */
        admin = new DBHelper(this,null,null,1);
        bdatos = admin.getWritableDatabase();
        /* Limpiamos la tabla cada que inicie sesion al usuario */
        bdatos.execSQL("delete from usuarios");
        bdatos.execSQL("delete from sqlite_sequence where name='usuarios' ");
        values = new ContentValues();


        //boton siguiente -- tiene contador por cada click
        findViewById(R.id.btnSiguiente).setOnClickListener(new View.OnClickListener() {
            @Override
			public void onClick(View v) {
				if (isSelected) {
					mSwipeView.doSwipe(false);
					indexOfTarget++;
					//dialog.show();
					//dialog.setMessage("Loanding..");
					if (indexOfTarget == 2) {//primeras 10 preguntas
						//initService();
						Log.d("RESULTADO", "seleccionado 1: "+indexOfTarget);
						isSelected = false;
					}
					if (indexOfTarget == 3) {//seguda 6 preguntas
						Log.d("RESULTADO", "seleccionado 2: "+indexOfTarget);
						//initService();
						isSelected = false;
					}
					if (indexOfTarget == 4) {//ultimos 2 preguntas
						Log.d("RESULTADO", "seleccionado 3: "+indexOfTarget);
						//initService();
					}
				}else {
					FancyToast.makeText(MenuTics.this, "Debes seleccionar una respuesta",
							FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
				}
			}
        });
    }


    /* Recibimos el valor selecionado */
    public void setValorSeleccionado1(String result) {
        Log.d("RESULTADO", "seleccionado 1: "+result);
		tics1 = result;
		isSelected = true;
    }
	public void setValorSeleccionado2(String result) {
		Log.d("RESULTADO", "seleccionado 2: "+result);
		tics2 = result;
		isSelected = true;
	}
	public void setValorSeleccionado3(String result) {
		Log.d("RESULTADO", "seleccionado 3: "+result);
		tics3 = result;
		isSelected = true;
	}
	public void setValorSeleccionado4(String result) {
		Log.d("RESULTADO", "seleccionado 4: "+result);
		tics4 = result;
		isSelected = true;
	}
	public void setValorSeleccionado5(String result) {
		Log.d("RESULTADO", "seleccionado 5: "+result);
		tics5 = result;
		isSelected = true;
	}
	public void setValorSeleccionado6(String result) {
		Log.d("RESULTADO", "seleccionado 6: "+result);
		tics6 = result;
		isSelected = true;
	}
	public void setValorSeleccionado7(String result) {
		Log.d("RESULTADO", "seleccionado 7: "+result);
		tics7 = result;
		isSelected = true;
	}
	public void setValorSeleccionado8(String result) {
		Log.d("RESULTADO", "seleccionado 8: "+result);
		tics8 = result;
		isSelected = true;
	}
	public void setValorSeleccionado9(String result) {
		Log.d("RESULTADO", "seleccionado 9: "+result);
		tics9 = result;
		isSelected = true;
	}
	public void setValorSeleccionado10(String result) {
		Log.d("RESULTADO", "seleccionado 10: "+result);
		tics10 = result;
		isSelected = true;
	}


	private APIInterface apiInterface;
    private Call<ResponseBase> call;
	private void initService() {
		Log.i("RESPUESTA: ", ""+coneccionIP+ "  curp: "+curp);
		apiInterface = APIClient.getClient(coneccionIP).create(APIInterface.class);
		if (indexOfTarget == 2){
			call = apiInterface.registrarTicsTerminales(idAlumno, tics1, tics2, tics3, tics4, tics5, tics6, tics7, tics8, tics9, tics10);
		}
		if (indexOfTarget == 3){
			call = apiInterface.registrarTicsRedes(idAlumno, tics1, tics2, tics3, tics4, tics5, tics6);
		}
		if (indexOfTarget == 4){
			call = apiInterface.registrarTicsAlmacenamiento(idAlumno, tics1, tics2);
		}
		call.enqueue(new Callback<ResponseBase>() {
			@Override
			public void onResponse(Call<ResponseBase> call, Response<ResponseBase> response) {
				if (response.body().isEstatus()){
					dialog.dismiss();
					showMessaje(response.body().getMensaje());
					if (indexOfTarget == 2 ){
						resetValores();
					}if (indexOfTarget == 3 ){
						resetValores();
					}
					if (indexOfTarget == 4 ){
						pasarSiguiente(response);
					}
					Log.i("RESPUESTA: ", ""+response.body().getMensaje());
				}else
					dialog.dismiss();
			}
			@Override
			public void onFailure(Call<ResponseBase> call, Throwable t) {
				Log.e("ERROR", ""+t.getMessage()+"");
				dialog.dismiss();
				FancyToast.makeText(MenuTics.this, message,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
				//Toast.makeText(ConectarIP.this, "IP incorrecto", Toast.LENGTH_LONG).show();
				call.cancel();
			}
		});
	}

	private void pasarSiguiente(Response<ResponseBase> response) {
		Intent intent = new Intent(MenuTics.this, MenuActivity.class);
		intent.putExtra("key_direccionIP", coneccionIP);
		intent.putExtra("key_Curp", curp);
		intent.putExtra("key_IdAlumno", idAlumno);
		startActivityForResult(intent, 1);
		finish();
	}


	private void showMessaje(String messaje){
		FancyToast.makeText(MenuTics.this, messaje, FancyToast.LENGTH_LONG, FancyToast.SUCCESS,false).show();
	}

	private void resetValores() {
		tics1 ="NO"; tics2 ="NO"; tics3 ="NO"; tics4 ="NO"; tics5 ="NO";
		tics6 ="NO"; tics7 ="NO"; tics8 ="NO"; tics9 ="NO"; tics10 ="NO";
	}


}
