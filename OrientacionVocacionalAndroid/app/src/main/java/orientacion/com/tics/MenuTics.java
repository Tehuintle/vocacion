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

import java.util.ArrayList;

import orientacion.com.DatosAuxiliar;
import orientacion.com.MenuActivity;
import orientacion.com.R;
import orientacion.com.api.response.TicsResponse;
import orientacion.com.basedatos.DBHelper;
import orientacion.com.util.LoadJSONFromAssetTics;

public class MenuTics extends AppCompatActivity {

    private SwipePlaceHolderView mSwipeView;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private int indexOfTarget = 1, contadorRegistroIteracionDos = 0;
    private DBHelper admin;
    private SQLiteDatabase bdatos;
    private ContentValues values;
    private String convertirTextoResultValidacion = "2", convertirTextoResult = "", coneccionIP="", curp="";


    ArrayList<DatosAuxiliar> arrayListPreguntas = new ArrayList<DatosAuxiliar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_preguntas);
        mSwipeView = (SwipePlaceHolderView)findViewById(R.id.swipeView);

        Bundle bundle = getIntent().getExtras();
        coneccionIP = bundle.getString("key_direccionIP");
        curp = bundle.getString("key_Curp");

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
				mSwipeView.doSwipe(false);
				indexOfTarget++;
				if (indexOfTarget == 4 ){
					Intent intent = new Intent(MenuTics.this, MenuActivity.class);
					intent.putExtra("key_direccionIP", coneccionIP);
					intent.putExtra("key_Curp", curp);
					startActivityForResult(intent,1);
					finish();
				}

			}
        });
    }


    /* Recibimos el valor selecionado */
    public void setValorSeleccionado(final int resultado) {
        Log.d("RESULTADO", "seleccionado: "+resultado);
        convertirTextoResult = String.valueOf(resultado);
        convertirTextoResultValidacion = String.valueOf(resultado);
    }
}
