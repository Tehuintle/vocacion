package orientacion.com.tics;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import orientacion.com.R;
import orientacion.com.api.APIClient;
import orientacion.com.api.APIInterface;
import orientacion.com.api.response.ResponseBase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuTerminales extends AppCompatActivity {

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


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_tics);
		ButterKnife.bind(this);

		Bundle bundle = getIntent().getExtras();
		coneccionIP = bundle.getString("key_direccionIP");
		curp = bundle.getString("key_Curp");
		idAlumno = bundle.getString("key_IdAlumno");

		setCheckBox();
	}


	private void setCheckBox() {
		dialog = new SpotsDialog(MenuTerminales.this);
		preguntaNameTxt.setText(getResources().getString(R.string.tics1));
		radioButtonA.setText(getResources().getString(R.string.pregunta1));
		radioButtonB.setText(getResources().getString(R.string.pregunta2));
		radioButtonC.setText(getResources().getString(R.string.pregunta3));
		radioButtonD.setText(getResources().getString(R.string.pregunta4));
		radioButtonE.setText(getResources().getString(R.string.pregunta5));
		radioButtonF.setText(getResources().getString(R.string.pregunta6));
		radioButtonH.setText(getResources().getString(R.string.pregunta7));
		radioButtonI.setText(getResources().getString(R.string.pregunta8));
		radioButtonJ.setText(getResources().getString(R.string.pregunta9));
		radioButtonK.setText(getResources().getString(R.string.pregunta10));


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
		});radioButtonC.setOnCheckedChangeListener((buttonView, isChecked) -> {
			if (isChecked){
				tics3 = "SI";
				isSelected = true;
			}else {
				tics3 = "NO";
			}
		});radioButtonD.setOnCheckedChangeListener((buttonView, isChecked) -> {
			if (isChecked){
				tics4 = "SI";
				isSelected = true;
			}else {
				tics4 = "NO";
			}
		});radioButtonE.setOnCheckedChangeListener((buttonView, isChecked) -> {
			if (isChecked){
				tics5 = "SI";
				isSelected = true;
			}else {
				tics5 = "NO";
			}
		});radioButtonF.setOnCheckedChangeListener((buttonView, isChecked) -> {
			if (isChecked){
				tics6 = "SI";
				isSelected = true;
			}else {
				tics6 = "NO";
			}
		});radioButtonH.setOnCheckedChangeListener((buttonView, isChecked) -> {
			if (isChecked){
				tics7 = "SI";
				isSelected = true;
			}else {
				tics7 = "NO";
			}
		});radioButtonI.setOnCheckedChangeListener((buttonView, isChecked) -> {
			if (isChecked){
				tics8 = "SI";
				isSelected = true;
			}else {
				tics8 = "NO";
			}
		});radioButtonJ.setOnCheckedChangeListener((buttonView, isChecked) -> {
			if (isChecked){
				tics9 = "SI";
				isSelected = true;
			}else {
				tics9 = "NO";
			}
		});radioButtonK.setOnCheckedChangeListener((buttonView, isChecked) -> {
			if (isChecked){
				tics10 = "SI";
				isSelected = true;
			}else {
				tics10 = "NO";
			}
		});
	}

	@OnClick(R.id.btnGuardar) void submit() {
		if (isSelected) {
			dialog.show();
			dialog.setMessage("Loanding..");
			initService();
			isSelected = false;
			//pasarSiguiente(null);
		}else {
			FancyToast.makeText(MenuTerminales.this, "Debes seleccionar una respuesta",
					FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
		}
	}


	private APIInterface apiInterface;

	private void initService() {
		Log.i("RESPUESTA: ", ""+coneccionIP+ "  curp: "+curp);
		apiInterface = APIClient.getClient(coneccionIP).create(APIInterface.class);
		Call<ResponseBase> call = apiInterface.registrarTicsTerminales(idAlumno, tics1, tics2, tics3, tics4, tics5, tics6, tics7, tics8, tics9, tics10);
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
				FancyToast.makeText(MenuTerminales.this, message,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
				call.cancel();
			}
		});
	}

	private void pasarSiguiente(Response<ResponseBase> response) {
		Intent intent = new Intent(MenuTerminales.this, MenuRedes.class);
		intent.putExtra("key_direccionIP", coneccionIP);
		intent.putExtra("key_Curp", curp);
		intent.putExtra("key_IdAlumno", idAlumno);
		startActivityForResult(intent, 1);
		finish();
	}


	private void showMessaje(String messaje){
		FancyToast.makeText(MenuTerminales.this, messaje, FancyToast.LENGTH_LONG, FancyToast.SUCCESS,false).show();
	}

}
