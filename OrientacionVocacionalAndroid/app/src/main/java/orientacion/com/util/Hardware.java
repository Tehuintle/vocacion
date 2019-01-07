package orientacion.com.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

public class Hardware {

	public static String getMacAddress(){
		String mac = null;
		try {
			List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
			for (NetworkInterface networkInterface : interfaces) {
				if(networkInterface.toString().contains("wlan")){
					byte [] macBytes = networkInterface.getHardwareAddress();
					StringBuilder sb = new StringBuilder(18);
					for (byte b : macBytes) {
						if (sb.length() > 0)
							sb.append(':');
						sb.append(String.format("%02x", b));
					}
					mac = sb.toString();
					return mac;
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return mac;
	}

	public static boolean isConnectionEnabled(Activity activity){
		boolean connected = false;
		ConnectivityManager conMan = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo.State mobile = conMan.getNetworkInfo(0).getState();
		NetworkInfo.State wifi = conMan.getNetworkInfo(1).getState();
		if ((mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) ||
				(wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING)) {
			connected = true;
		}
		return connected;
	}
}
