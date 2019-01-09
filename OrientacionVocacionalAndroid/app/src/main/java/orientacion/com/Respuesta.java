package orientacion.com;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import orientacion.com.basedatos.DBHelper;

public class Respuesta extends AppCompatActivity {

    private TextView txtResultado, txtUsuario;
    private String respuestas="", nombre="";

    int p1Final = 0;
    int p2Final = 0;
    int p3Final = 0;
    int p4Final = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.respuesta);

        txtResultado = (TextView)findViewById(R.id.tvResultado);
        txtUsuario = (TextView)findViewById(R.id.usuario);
        Bundle bundle = getIntent().getExtras();
        nombre = bundle.getString("nombre");
        txtUsuario.setText(nombre+" TU VOCACION ES:");


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
        int pInUno = Integer.parseInt(String.valueOf(total.charAt(0))), pInDos = Integer.parseInt(String.valueOf(total.charAt(4))), pInTres = Integer.parseInt(String.valueOf(total.charAt(8))), pInCuatro = Integer.parseInt(String.valueOf(total.charAt(12)));
        p1Final =  pInUno + pInDos + pInTres + pInCuatro ;
        Log.i("RESULTADO", "RESULTADO P1: "+  p1Final );
        Log.i("RESULTADO", "POSICION 1: "+  total.charAt(0) );
        Log.i("RESULTADO", "POSICION 2: "+  total.charAt(4) );
        Log.i("RESULTADO", "POSICION 3: "+  total.charAt(8) );
        Log.i("RESULTADO", "POSICION 4: "+  total.charAt(12) );
        Log.i("RESULTADO", "________________________________" );

        int pCosUno = Integer.parseInt(String.valueOf(total.charAt(1))), pCosDos = Integer.parseInt(String.valueOf(total.charAt(5))), pCosTres = Integer.parseInt(String.valueOf(total.charAt(9))), pCosCuatro = Integer.parseInt(String.valueOf(total.charAt(13)));
        p2Final = pCosUno + pCosDos + pCosTres + pCosCuatro ;
        Log.i("RESULTADO", "RESULTADO P2: "+  p2Final );
        Log.i("RESULTADO", "POSICION 1: "+ total.charAt(1) );
        Log.i("RESULTADO", "POSICION 2: "+ total.charAt(5) );
        Log.i("RESULTADO", "POSICION 3: "+ total.charAt(9) );
        Log.i("RESULTADO", "POSICION 4: "+ total.charAt(13) );
        Log.i("RESULTADO", "________________________________" );

        int pCpUno = Integer.parseInt(String.valueOf( total.charAt(2))), pCpDos = Integer.parseInt(String.valueOf( total.charAt(6))), pCpTres =  Integer.parseInt(String.valueOf(total.charAt(10))), pCpCuatro =  Integer.parseInt(String.valueOf(total.charAt(14)));
        p3Final = pCpUno + pCpDos + pCpTres + pCpCuatro ;
        Log.i("RESULTADO", "RESULTADO P3: "+  p3Final );
        Log.i("RESULTADO", "POSICION 1: "+ total.charAt(2) );
        Log.i("RESULTADO", "POSICION 2: "+ total.charAt(6) );
        Log.i("RESULTADO", "POSICION 3: "+ total.charAt(10) );
        Log.i("RESULTADO", "POSICION 4: "+ total.charAt(14) );
        Log.i("RESULTADO", "________________________________" );

        int pCUno = Integer.parseInt(String.valueOf(total.charAt(3))), pCDos = Integer.parseInt(String.valueOf(total.charAt(7))), pCTres = Integer.parseInt(String.valueOf(total.charAt(11))), pCCuatro = Integer.parseInt(String.valueOf(total.charAt(15)));
        p4Final = pCUno + pCDos + pCTres + pCCuatro ;
        Log.i("RESULTADO", "RESULTADO P4: "+  p4Final );
        Log.i("RESULTADO", "POSICION 1: "+ total.charAt(3) );
        Log.i("RESULTADO", "POSICION 2: "+ total.charAt(7) );
        Log.i("RESULTADO", "POSICION 3: "+ total.charAt(11) );
        Log.i("RESULTADO", "POSICION 4: "+ total.charAt(15) );

        /* Tabla de ejemplos
        * 4 - 3 - 2 - 1
          0 - 4 - 3 - 2
          1 - 0 - 4 - 3
          2 - 1 - 0 - 4
          _____________
   Total: 7  8   9   10
        */


        /* Tabla de ejemplos AREAS
          4 - 3 - 2 - 1 - 3
          0 - 4 - 3 - 2 - 4
          1 - 0 - 4 - 3 - 1
          2 - 1 - 0 - 4 - 2
          0 - 1 - 1 - 0 - 0
          0 - 2 - 0 - 4 - 3
          2 - 1 - 2 - 2 - 1
          3 - 0 - 0 - 4 - 1
          2 - 1 - 4 - 0 - 0
          4 - 1 - 0 - 4 - 0
          __________________
   Total: 27  18  9   10  12

        */


  /* Tabla de ejemplos CAPACITACION
        * 4 - 3 - 2 - 1
          0 - 4 - 3 - 2
          1 - 0 - 4 - 3
          2 - 1 - 0 - 4
          4 - 1 - 0 - 1
          2 - 2 - 3 - 4
          1 - 1 - 0 - 0
          2 - 1 - 2 - 4
          ______________
   Total: 12  18   9   10


        */



        /* Aqui se valida para poder sacar los resultados */
        // POSICION NUMERO UNO  =====> INFORMÁTICA
        if (p1Final > p2Final){
            if (p1Final > p3Final){
                if (p1Final > p4Final){
                    txtResultado.setText(" INFORMÁTICA ");
                }else {
                    txtResultado.setText("EMPRENDEDOR: Pero necesita fijar un objetivo en su vida");
                }
            }else if (p3Final > p4Final){
                txtResultado.setText(" CORTE Y PEINADO ");
            }else {
                txtResultado.setText("EMPRENDEDOR: Pero necesita fijar un objetivo en su vida");
            }
        }else
            // POSICION NUMERO DOS  =====> COSTURA
            if (p2Final > p1Final){
               if (p2Final > p3Final){
                if (p2Final > p4Final){
                    txtResultado.setText(" COSTURA ");
                }else {
                    txtResultado.setText("EMPRENDEDOR: Pero necesita fijar un objetivo en su vida");
                }
            }if (p3Final > p4Final){
                    txtResultado.setText(" CORTE Y PEINADO ");
                }if (p4Final > p1Final){
                    if (p4Final > p2Final){
                        if (p4Final > p3Final){
                            txtResultado.setText(" COCINA ");
                        }
                    }else {
                        txtResultado.setText("EMPRENDEDOR: Pero necesita fijar un objetivo en su vida");
                    }
                } else {
                    txtResultado.setText("EMPRENDEDOR: Pero necesita fijar un objetivo en su vida");
                }
        }else
            // POSICION NUMERO TRES  =====> CORTE Y PEINADO
            if (p3Final > p1Final){
               if (p3Final > p2Final){
                if (p3Final > p4Final){
                    txtResultado.setText(" CORTE Y PEINADO ");
                }else {
                    txtResultado.setText("EMPRENDEDOR: Pero necesita fijar un objetivo en su vida");
                }
            }if (p2Final > p3Final){
                    if (p2Final > p4Final){
                        txtResultado.setText(" COSTURA ");
                    }else {
                        txtResultado.setText("EMPRENDEDOR: Pero necesita fijar un objetivo en su vida");
                    }
                }if (p3Final > p4Final){
                    txtResultado.setText(" CORTE Y PEINADO ");
                }else {
                    txtResultado.setText("EMPRENDEDOR: Pero necesita fijar un objetivo en su vida");
                }
        }else
            // POSICION NUMERO CUATRO  =====> COCINA
            if (p4Final > p1Final){
               if (p4Final > p2Final){
                if (p4Final > p3Final){
                    txtResultado.setText(" COCINA ");
                }else {
                    txtResultado.setText("EMPRENDEDOR: Pero necesita fijar un objetivo en su vida");
                }
            }if (p2Final > p3Final){
                    if (p2Final > p4Final){
                        txtResultado.setText(" COSTURA ");
                    }else {
                        txtResultado.setText("EMPRENDEDOR: Pero necesita fijar un objetivo en su vida");
                    }
                }else if (p3Final > p4Final){
                    txtResultado.setText(" CORTE Y PEINADO ");
                }else {
                    txtResultado.setText("EMPRENDEDOR: Pero necesita fijar un objetivo en su vida");
                }
        }


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

