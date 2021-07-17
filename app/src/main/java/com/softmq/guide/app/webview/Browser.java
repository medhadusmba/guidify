package com.softmq.guide.app.webview;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.webkit.WebSettings.PluginState;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.andrognito.flashbar.Flashbar;
import com.softmq.guide.app.R;
import com.softmq.guide.app.Config;

import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class Browser implements AdvancedWebView.Listener, SwipeRefreshLayout.OnRefreshListener {

    private static final String PUSH_URL = null;
    private  Activity activity;
    //Layouts
    public FrameLayout rl;
    public AdvancedWebView webView;
    public SwipeRefreshLayout swipeLayout;
    public ProgressBar progressBar;

    //WebView Clients
    public WebToAppChromeClient chromeClient;
    public WebToAppWebClient webClient;

    //WebView Session
    public String mainUrl = null;
    static String URL = "url";
    public int firstLoad = 0;
    private boolean clearHistory = false;
    private View loader;
    private Bundle args;
    private Consumer<String> onPageStarted=(url)->{};
    private Consumer<Object> onPageFinished=(url)->{};

    public Browser(Activity activity, String url) {
        args = new Bundle();
        args.putString(URL, url);
        this.activity = activity;
        if (getArguments() != null && mainUrl == null) {
            mainUrl = getArguments().getString(URL);
            firstLoad = 0;
        }
    }

    public void setBaseUrl(String url) {
        this.mainUrl = url;
        this.clearHistory = true;
        webView.loadUrl(mainUrl);
    }

    private Bundle getArguments() {
        return args;
    }
    public void show(ViewGroup container) {
        rl = (FrameLayout) View.inflate(activity.getApplicationContext(), R.layout.browser, null);
        container.addView(rl);
        progressBar = (ProgressBar) rl.findViewById(R.id.webview_progress);

        loader = rl.findViewById(R.id.webview_loader);
        webView = (AdvancedWebView) rl.findViewById(R.id.scrollable);
        swipeLayout = (SwipeRefreshLayout) rl.findViewById(R.id.swipe_container);
        onActivityCreated();
    }
    private void onActivityCreated() {
        if (Config.PULL_TO_REFRESH)
            swipeLayout.setOnRefreshListener(this);
        else
            swipeLayout.setEnabled(false);

        // Setting the webview listeners
        webView.setListener(getActivity(), this);

        // Setting the scroll listeners (if applicable)
//        if (MainActivity.getCollapsingActionBar()) {
//
//            ((MainActivity) getActivity()).showToolbar(this);
//
//            browser.setOnScrollChangeListener(browser, new ToolbarWebViewScrollListener() {
//                @Override
//                public void onHide() {
//                    ((MainActivity) getActivity()).hideToolbar();
//                }
//
//                @Override
//                public void onShow() {
//                    ((MainActivity) getActivity()).showToolbar(WebFragment.this);
//                }
//            });
//
//        }

        // set javascript and zoom and some other settings
        webView.requestFocus();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        // Below required for geolocation
        webView.setGeolocationEnabled(true);
        // 3RD party plugins (on older devices)
        webView.getSettings().setPluginState(PluginState.ON);

        if (Config.MULTI_WINDOWS) {
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setSupportMultipleWindows(true);
        }

        webClient = new WebToAppWebClient(this, webView);
        webView.setWebViewClient(webClient);

        chromeClient = new WebToAppChromeClient(this, rl, webView, swipeLayout, progressBar);
        webView.setWebChromeClient(chromeClient);

        // load url (if connection available
        if (webClient.hasConnectivity(mainUrl, true)) {
            String pushurl = PUSH_URL;
            if (pushurl != null) {
                webView.loadUrl(pushurl);
            } else {
                webView.loadUrl(mainUrl);
            }
        } else {
            onPageFinished(mainUrl);
        }
    }

    public Activity getActivity() {
        return activity;
    }

    @Override
    public void onRefresh() {
        webView.reload();
    }

    @SuppressLint("NewApi")
    public void onPause() {
        webView.onPause();
    }

    public void onDestroy() {
        webView.onDestroy();
    }

    @SuppressLint("NewApi")
    public void onResume() {
        webView.onResume();
    }

    @SuppressLint("NewApi")
    @Override
    public void onDownloadRequested(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
        if (!hasPermissionToDownload(getActivity())) return;

        String filename = null;
        try {
            filename = new GetFileInfo().execute(url).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if (filename == null) {
            String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(url);
            filename = URLUtil.guessFileName(url, null, fileExtenstion);
        }


        if (AdvancedWebView.handleDownload(getActivity(), url, filename)) {
            new Flashbar.Builder(activity)
                    .gravity(Flashbar.Gravity.BOTTOM)
                    .title(R.string.success_title)
                    .titleColorRes(R.color.white)
                    .messageColorRes(R.color.white)
                    .backgroundColorRes(R.color.color_primary_dark)
                    .message(activity.getResources().getString(R.string.download_done))
                    .vibrateOn(Flashbar.Vibration.SHOW, Flashbar.Vibration.DISMISS)
                    .dismissOnTapOutside()
                    .enableSwipeToDismiss()
                    .build().show();
        } else {
            new Flashbar.Builder(activity)
                    .gravity(Flashbar.Gravity.BOTTOM)
                    .titleColorRes(R.color.white)
                    .messageColorRes(R.color.white)
                    .enableSwipeToDismiss()
                    .dismissOnTapOutside()
                    .title(R.string.error_title)
                    .backgroundColorRes(R.color.color_primary_dark)
                    .message(activity.getResources().getString(R.string.download_fail))
                    .vibrateOn(Flashbar.Vibration.SHOW, Flashbar.Vibration.DISMISS)
                    .build().show();
        }
    }

    private static boolean hasPermissionToDownload(final Activity context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED)
            return true;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.download_permission_explaination);
        builder.setPositiveButton(R.string.common_permission_grant, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Fire off an async request to actually get the permission
                // This will show the standard permission request dialog UI
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    context.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        return false;
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        loader.setVisibility(View.VISIBLE);
        firstLoad = 1;
        onPageStarted.accept(url);
    }

    @Override
    public void onPageFinished(String url) {
        loader.setVisibility(View.GONE);


        if (clearHistory) {
            clearHistory = false;
            webView.clearHistory();
        }

        hideErrorScreen();
        onPageFinished.accept(url);
    }
    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onExternalPageRequest(String url) {
        // TODO Auto-generated method stub
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        webView.onActivityResult(requestCode, resultCode, data);
    }

    // sharing
    public void shareURL() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String appName = getActivity().getString(R.string.app_name);
        shareIntent
                .putExtra(
                        Intent.EXTRA_TEXT,
                        String.format(getActivity().getString(R.string.share_body), webView.getTitle(), appName + " https://play.google.com/store/apps/details?id=" + getActivity().getPackageName()));
        getActivity().startActivity(Intent.createChooser(shareIntent,
                getActivity().getText(R.string.sharetitle)));
    }

    public void showErrorScreen(String message) {
        final View stub = rl.findViewById(R.id.empty_view);
        stub.setVisibility(View.VISIBLE);

        ((TextView) stub.findViewById(R.id.title)).setText(message);
        stub.findViewById(R.id.retry_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webView.getUrl() == null) {
                    webView.loadUrl(mainUrl);
                } else {
                    webView.loadUrl("javascript:document.open();document.close();");
                    webView.reload();
                }
            }
        });
    }

    public void hideErrorScreen() {
        final View stub = rl.findViewById(R.id.empty_view);
        if (stub.getVisibility() == View.VISIBLE)
            stub.setVisibility(View.GONE);
    }

    public boolean onBackPressed() {
        return webView.onBackPressed();
    }

    public void setOnPageStarted(Consumer<String> onPageStarted) {
        this.onPageStarted = onPageStarted;
    }

    public void setOnPageFinished(Consumer<Object> onPageFinished) {
        this.onPageFinished = onPageFinished;
    }
}
