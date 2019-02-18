package orientacion.com;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
	TextView version;
	private SpotsDialog dialog;
	private APIInterface apiInterface;
	private NetworkState networkState;
	private Dialog alertDialog;
	private static final int REQUEST_CONFIG_CONNECTION = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conectar_ip);

		networkState = new NetworkState();
		networkState.addListener(this);
		this.registerReceiver(networkState, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

		dialog=new SpotsDialog(ConectarIP.this);
		btnConectarIP=(Button)findViewById(R.id.btnConectar);
		edtIP = (EditText)findViewById(R.id.edtIP);
		version = (TextView) findViewById(R.id.version);

		btnConectarIP.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String coneccionIP = edtIP.getText().toString();
				//primero preguntamos si ahy conexion internet
				if (Hardware.isConnectionEnabled(ConectarIP.this)) {
					//despues validamos caja texto
					if (!coneccionIP.equals("")) {
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

		//initChar();
	}

	private void initChar() {
		String[] numeros = {".", ".", ".", "."};
		int contador=0;
		for (int i=0; i<numeros.length; i++){
			contador++;
		}
		Log.i("RESPUESTA: ","Resultado: "+ contador);

	}


	private void conectarIP(final String coneccionIP) {
		Log.i("RESPUESTA: ", ""+coneccionIP);
		try {
			apiInterface = APIClient.getClient(coneccionIP).create(APIInterface.class);
			Call<ResponseBase> call3 = apiInterface.conectarRemoto(coneccionIP);
			call3.enqueue(new Callback<ResponseBase>() {
				@Override
				public void onResponse(Call<ResponseBase> call, Response<ResponseBase> response) {
					try {
						if (response.body().isEstatus()){
							//Log.i("RESPUESTA: ", ""+response.body().getMensaje());
							pasarSiguiente(response, coneccionIP);
						}else {
							dialog.dismiss();
						}
					}catch (Exception e){
						intMensage("Error de conexión.");
					}

				}
				@Override
				public void onFailure(Call<ResponseBase> call, Throwable t) {
					//Log.e("ERROR", ""+t.getMessage()+"");
					try {
						dialog.dismiss();
						FancyToast.makeText(ConectarIP.this,"Verificar dirección IP, no se pudo conectar",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
						//Toast.makeText(ConectarIP.this, "IP incorrecto", Toast.LENGTH_LONG).show();
						//call.cancel();
					}catch (Exception e){
						intMensage("Error de conexión.");
					}
				}
			});
		}catch (NullPointerException e){
			version.setText(coneccionIP);
			intMensage("1. Error de conexión. Verifica su red o IP");
		} catch (IllegalArgumentException e){
			version.setText(coneccionIP);
			intMensage("2. Error de conexión. Verifica su red o IP");
		} catch (Exception e){
			version.setText(coneccionIP);
			intMensage("3. Error de conexión. Verifica su red o IP");
		}
	}

	private void intMensage(String message) {
		FancyToast.makeText(ConectarIP.this,message,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
		dialog.dismiss();
	}

	private void pasarSiguiente(final Response<ResponseBase> response, final String coneccionIP) {
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				final Handler handler = new Handler();
				long millisDelayTime = 5000;
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						dialog.dismiss();
						FancyToast.makeText(ConectarIP.this,""+response.body().getMensaje(), FancyToast.LENGTH_LONG, FancyToast.SUCCESS,false).show();
						Intent intent = new Intent(ConectarIP.this, AccederCurp.class);
						intent.putExtra("key_direccionIP", coneccionIP);
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
