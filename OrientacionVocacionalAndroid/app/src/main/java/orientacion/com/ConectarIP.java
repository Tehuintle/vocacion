package orientacion.com;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import dmax.dialog.SpotsDialog;
import orientacion.com.api.APIClient;
import orientacion.com.api.APIInterface;
import orientacion.com.api.response.ResponseBase;
import orientacion.com.util.Hardware;
import orientacion.com.util.NetworkState;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConectarIP extends AppCompatActivity implements NetworkState.NetworkStateReceiverListener {

	private  Button btnConectarIP;
	private EditText edtIP;
	private SpotsDialog dialog;
	private APIInterface apiInterface;
	private NetworkState networkState;
	private Dialog alertDialog;
	private static final int REQUEST_CONFIG_CONNECTION = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conectar_ip);

		SharedPreferences preferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
		String coneccionIP = preferences.getString("IP", "");
		String curp = preferences.getString("CURP", "");
		Log.i("RESPUESTA: ", ""+coneccionIP+ "  curp: "+curp);


		networkState = new NetworkState();
		networkState.addListener(this);
		this.registerReceiver(networkState, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

		dialog=new SpotsDialog(ConectarIP.this);
		btnConectarIP=(Button)findViewById(R.id.btnConectar);
		edtIP = (EditText)findViewById(R.id.edtIP);

		btnConectarIP.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String coneccionIP = edtIP.getText().toString();
				//primero preguntamos si ahy conexion internet
				if (Hardware.isConnectionEnabled(ConectarIP.this)) {
					//despues validamos caja texto
					if (!coneccionIP.isEmpty()) {
						dialog.show();
						dialog.setMessage("Conectando..");
						conectarIP(coneccionIP);
					} else {
						FancyToast.makeText(ConectarIP.this, "Favor digite su dirección IP", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
					}
				}else {
					makeConfigDialog();
				}
			}
		});
	}


	private void conectarIP(final String coneccionIP) {
		apiInterface = APIClient.getClient(coneccionIP).create(APIInterface.class);
		Call<ResponseBase> call3 = apiInterface.conectarRemoto(coneccionIP);
		call3.enqueue(new Callback<ResponseBase>() {
			@Override
			public void onResponse(Call<ResponseBase> call, Response<ResponseBase> response) {
				if (response.body().estatus){
					Log.i("RESPUESTA: ", ""+response.body().mensaje);
					pasarSiguiente(response);
					SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedpreferences.edit();
					editor.putString("IP", coneccionIP);
					editor.commit();
				}
			}
			@Override
			public void onFailure(Call<ResponseBase> call, Throwable t) {
				Log.e("ERROR",t.getMessage());
				dialog.dismiss();
				FancyToast.makeText(ConectarIP.this,"Verificar dirección IP, no se pudo conectar",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
				//Toast.makeText(ConectarIP.this, "IP incorrecto", Toast.LENGTH_LONG).show();
				call.cancel();
			}
		});
	}

	private void pasarSiguiente(final Response<ResponseBase> response) {
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				final Handler handler = new Handler();
				long millisDelayTime = 5000;
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						dialog.dismiss();
						FancyToast.makeText(ConectarIP.this,""+response.body().mensaje, FancyToast.LENGTH_LONG, FancyToast.SUCCESS,false).show();
						//Toast.makeText(ConectarIP.this, ""+response.body().mensaje, Toast.LENGTH_LONG).show();
						Intent intent = new Intent(ConectarIP.this, AccederCurp.class);
						startActivityForResult(intent,1);
					}
				}, millisDelayTime);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			switch (requestCode) {
				case REQUEST_CONFIG_CONNECTION: {
					if (resultCode == RESULT_OK) {
						if (alertDialog != null) {
							alertDialog.dismiss();
						}
					} else {
						if (alertDialog != null) {
							if (Hardware.isConnectionEnabled(this)) {
								alertDialog.dismiss();
							} else {
								alertDialog.show();
							}
						}
					}
				}
			}
		}catch (NullPointerException e){
			e.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(networkState);
	}

	@Override
	public void networkAvailable() {
	}

	@Override
	public void networkUnavailable() {
		makeConfigDialog();
	}

	@Override
	public void unRegister(BroadcastReceiver broadcastReceiver) {
		unregisterReceiver(broadcastReceiver);
	}

	private void makeConfigDialog() {
		alertDialog =
				new LovelyStandardDialog(ConectarIP.this, LovelyStandardDialog.ButtonLayout.VERTICAL)
						.setTopColor(getResources().getColor(R.color.colorPrimary))
						.setIconTintColor(getResources().getColor(R.color.colorWhite))
						.setPositiveButtonColor(getResources().getColor(R.color.colorPrimary))
						.setCancelable(false)
						.setIcon(R.drawable.ic_signal_wifi_off_white)
						.setTitle("¡No te encuentras conectado a internet!")
						.setMessage("Para poder utilizar la aplicación es necesario que te conectes a internet.")
						.setPositiveButton(R.string.ok, new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								startActivityForResult(new Intent(Settings.ACTION_SETTINGS), REQUEST_CONFIG_CONNECTION);
							}
						})
						.setNegativeButton(android.R.string.no, new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog.dismiss();
							}
						})
						.show();
	}


}
