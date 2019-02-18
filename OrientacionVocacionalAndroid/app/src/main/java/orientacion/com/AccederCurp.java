package orientacion.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shashank.sony.fancytoastlib.FancyToast;

import dmax.dialog.SpotsDialog;
import orientacion.com.api.APIClient;
import orientacion.com.api.APIInterface;
import orientacion.com.api.response.ResponseCURP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccederCurp extends AppCompatActivity {

	private Button btnCURP;
	private EditText edtCURP;
	private SpotsDialog dialog;
	private APIInterface apiInterface;
	private String coneccionIP="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acceder_curp);

		Bundle bundle = getIntent().getExtras();
		coneccionIP = bundle.getString("key_direccionIP", "error");

		dialog=new SpotsDialog(AccederCurp.this);
		btnCURP=(Button)findViewById(R.id.btnValidarCurp);
		edtCURP = (EditText)findViewById(R.id.edtCURP);

		btnCURP.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String curp = edtCURP.getText().toString();
				if (!curp.isEmpty()) {
					if (validarCurp(curp)) {
						dialog.show();
						dialog.setMessage("Conectando..");
						consultarCURP(curp);
					}else {
						showMessaje("Favor digite su CURP de 18 carácteres", "");
					}
				}else {
					showMessaje("Favor digite su CURP", "");
				}
			}
		});
	}

	private void consultarCURP(String curp) {
		apiInterface = APIClient.getClient(coneccionIP).create(APIInterface.class);
		Call<ResponseCURP> call3 = apiInterface.logInCurp(curp);
		call3.enqueue(new Callback<ResponseCURP>() {
			@Override
			public void onResponse(Call<ResponseCURP> call, Response<ResponseCURP> response) {
				if (response.body().isEstatus()){
					dialog.dismiss();
					pasarSiguiente(response);
					showMessaje(response.body().getMensaje(), "succes");
				}else {
					dialog.dismiss();
					showMessaje(response.body().getMensaje(), "error");
				}
			}
			@Override
			public void onFailure(Call<ResponseCURP> call, Throwable t) {
				Log.e("ERROR", ""+t.getMessage()+"");
				dialog.dismiss();
				showMessaje("Verificar dirección IP, no se pudo conectar", "error");
				call.cancel();
			}
		});
	}

	private void pasarSiguiente(Response<ResponseCURP> response) {
		Log.i("RESPUESTA: ", ""+response.body().getMensaje());
		Intent intent = new Intent(AccederCurp.this, MenuActivity.class);
		intent.putExtra("key_direccionIP", coneccionIP);
		intent.putExtra("key_Curp", response.body().getCurp());
		intent.putExtra("key_IdAlumno", response.body().getIdAlumno());
		startActivityForResult(intent, 1);
	}

	private boolean validarCurp(String curp) {
		boolean isCurp;
		if (curp.length() == 18){
			isCurp = true;
		}else {
			isCurp = false;
		}
		return isCurp;
	}

	private void showMessaje(String messaje, String tipoMensaje){
		if (tipoMensaje.equals("error")){
			FancyToast.makeText(AccederCurp.this,messaje,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
		}if (tipoMensaje.equals("succes")){
			FancyToast.makeText(AccederCurp.this, messaje, FancyToast.LENGTH_LONG, FancyToast.SUCCESS,false).show();
		} if (tipoMensaje.equals("")){
			FancyToast.makeText(AccederCurp.this, messaje, FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
		}
	}

}
