package com.softmq.guide.app.common.ui.internet;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import com.softmq.guide.app.common.core.Provider;

public class InternetStatus {
    private final Provider<ConnectivityManager> connectivity;

    public InternetStatus(Activity activity) {
        this(activity.getApplicationContext());

    }

    public InternetStatus(Context context) {

        this(() -> ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)));
    }

    public InternetStatus(Provider<ConnectivityManager> connectivity) {
        this.connectivity = connectivity;
    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager = connectivity.get();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = connectivityManager.getActiveNetwork();
            if (network == null) return false;
            NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(network);
            return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
        } else {
            NetworkInfo network = connectivityManager.getActiveNetworkInfo();
            return network != null && network.isConnected();
        }

    }
}
