package orientacion.com.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.HashSet;
import java.util.Set;

public class NetworkState extends BroadcastReceiver {


	protected Set<NetworkStateReceiverListener> listeners;
	protected Boolean connected;

	public NetworkState() {
		this.listeners = new HashSet<NetworkStateReceiverListener>();
		this.connected = null;
	}

	public void onReceive(Context context, Intent intent) {
		if(intent == null || intent.getExtras() == null)
			return;

		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = manager.getActiveNetworkInfo();
		NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		//boolean mobileConnected = mobileInfo.getState() == NetworkInfo.State.CONNECTED;

		if(ni != null && ni.getState() == NetworkInfo.State.CONNECTED || mobileInfo.getState() == NetworkInfo.State.CONNECTED ) {
			connected = true;
		} else if(intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,Boolean.FALSE) || mobileInfo.getState() == NetworkInfo.State.DISCONNECTED) {
			connected = false;
		}
		notifyStateToAll();
	}

	private void notifyStateToAll() {
		for(NetworkStateReceiverListener listener : listeners)
			notifyState(listener);
	}

	private void notifyState(NetworkStateReceiverListener listener) {
		if(connected == null || listener == null)
			return;

		if(connected) {
			listener.networkAvailable();
		}
		else if(!connected) {
			listener.networkUnavailable();
		}
	}

	public void addListener(NetworkStateReceiverListener l) {
		listeners.add(l);
		notifyState(l);
	}

	public void removeListener(NetworkStateReceiverListener l) {
		listeners.remove(l);
	}

	public interface NetworkStateReceiverListener {
		public void networkAvailable();
		public void networkUnavailable();
		public void unRegister(BroadcastReceiver broadcastReceiver);
	}
}

