package orientacion.com.tics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import orientacion.com.MenuActivity;
import orientacion.com.R;
import orientacion.com.api.APIClient;
import orientacion.com.api.APIInterface;
import orientacion.com.api.response.ResponseBase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuAlmacenamiento extends AppCompatActivity {

	@BindView(R.id.preguntaNameTxt)
	TextView preguntaNameTxt;
	//-------->
	@BindView(R.id.radioButtonA)
	CheckBox radioButtonA;
	@BindView(R.id.radioButtonB)
	CheckBox radioButtonB;
	@BindView(R.id.radioButtonC)
	CheckBox radioButtonC;
	@BindView(R.id.radioButtonD)
	CheckBox radioButtonD;
	@BindView(R.id.radioButtonE)
	CheckBox radioButtonE;
	@BindView(R.id.radioButtonF)
	CheckBox radioButtonF;
	@BindView(R.id.radioButtonH)
	CheckBox radioButtonH;
	@BindView(R.id.radioButtonI)
	CheckBox radioButtonI;
	@BindView(R.id.radioButtonJ)
	CheckBox radioButtonJ;
	@BindView(R.id.radioButtonK)
	CheckBox radioButtonK;

	private String tics1 ="NO", tics2 ="NO", tics3 ="NO", tics4 ="NO", tics5 ="NO",
			tics6 ="NO", tics7 ="NO", tics8 ="NO", tics9 ="NO", tics10 ="NO";
	private String message = "Verificar dirección IP, no se pudo conectar";
	private String coneccionIP="", curp="", idAlumno="";
	private boolean isSelected = false;
	private SpotsDialog dialog;
	private Activity activity;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_tics);
		ButterKnife.bind(this);
		activity = this;

		Bundle bundle = getIntent().getExtras();
		coneccionIP = bundle.getString("key_direccionIP");
		curp = bundle.getString("key_Curp");
		idAlumno = bundle.getString("key_IdAlumno");

		setCheckBox();
	}


	private void setCheckBox() {
		dialog = new SpotsDialog(activity);
		preguntaNameTxt.setText(getResources().getString(R.string.tics3));
		radioButtonA.setText(getResources().getString(R.string.pregunta17));
		radioButtonB.setText(getResources().getString(R.string.pregunta18));
		radioButtonC.setVisibility(View.GONE);
		radioButtonD.setVisibility(View.GONE);
		radioButtonE.setVisibility(View.GONE);
		radioButtonF.setVisibility(View.GONE);
		radioButtonH.setVisibility(View.GONE);
		radioButtonI.setVisibility(View.GONE);
		radioButtonJ.setVisibility(View.GONE);
		radioButtonK.setVisibility(View.GONE);


		//Metodos OnClick que enviara un (SI) o (NO)
		radioButtonA.setOnCheckedChangeListener((buttonView, isChecked) -> {
			if (isChecked){
				tics1 = "SI";
				isSelected = true;
			}else {
				tics1 = "NO";
			}
		});radioButtonB.setOnCheckedChangeListener((buttonView, isChecked) -> {
			if (isChecked){
				tics2 = "SI";
				isSelected = true;
			}else {
				tics2 = "NO";
			}
		});
	}

	@OnClick(R.id.btnGuardar) void submit() {
		if (isSelected) {
			dialog.show();
			dialog.setMessage("Loanding..");
			initService();
			//pasarSiguiente(null);
			isSelected = false;
		}else {
			FancyToast.makeText(activity, "Debes seleccionar una respuesta",
					FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
		}
	}


	private APIInterface apiInterface;

	private void initService() {
		Log.i("RESPUESTA: ", ""+coneccionIP+ "  curp: "+curp);
		apiInterface = APIClient.getClient(coneccionIP).create(APIInterface.class);
		Call<ResponseBase> call = apiInterface.registrarTicsAlmacenamiento(idAlumno, tics1, tics2);
		call.enqueue(new Callback<ResponseBase>() {
			@Override
			public void onResponse(Call<ResponseBase> call, Response<ResponseBase> response) {
				if (response.body().isEstatus()){
					dialog.dismiss();
					showMessaje(response.body().getMensaje());
					pasarSiguiente(response);
					Log.i("RESPUESTA: ", ""+response.body().getMensaje());
				}else
					dialog.dismiss();
			}
			@Override
			public void onFailure(Call<ResponseBase> call, Throwable t) {
				Log.e("ERROR", ""+t.getMessage()+"");
				dialog.dismiss();
				FancyToast.makeText(activity, message,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
				call.cancel();
			}
		});
	}

	private void pasarSiguiente(Response<ResponseBase> response) {
		Intent intent = new Intent(activity, MenuActivity.class);
		intent.putExtra("key_direccionIP", coneccionIP);
		intent.putExtra("key_Curp", curp);
		intent.putExtra("key_IdAlumno", idAlumno);
		startActivityForResult(intent, 1);
		finish();
	}


	private void showMessaje(String messaje){
		FancyToast.makeText(activity, messaje, FancyToast.LENGTH_LONG, FancyToast.SUCCESS,false).show();
	}

}
