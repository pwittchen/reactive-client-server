package com.github.pwittchen.client;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
  private final static String TAG = "MainActivity";
  private final static String EMPTY = "";
  private final static String URL = "http://10.0.2.2:8080/";
  private final static String PATH = "sensor?reading=";
  private final static MediaType TEXT = MediaType.parse("text/plain; charset=utf-8");

  private final Call.Factory client = new OkHttpClient();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.btn_send_message).setOnClickListener(view -> createSubscription());
  }

  private void createSubscription() {
    sendRequest().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CompletableObserver() {
          @Override public void onSubscribe(Disposable d) {
          }

          @Override public void onComplete() {
            showSnackBar("message sent");
            Log.d(TAG, "message sent");
          }

          @Override public void onError(Throwable e) {
            showSnackBar("error: message NOT sent");
            Log.d(TAG, "error: message NOT sent");
          }
        });
  }

  private Completable sendRequest() {
    return Completable.create(emitter -> client.newCall(createRequest()).enqueue(new Callback() {
      @Override public void onFailure(@NonNull Call call, @NonNull IOException exception) {
        emitter.onError(exception);
      }

      @Override public void onResponse(@NonNull Call call, @NonNull Response response)
          throws IOException {
        emitter.onComplete();
      }
    }));
  }

  private Request createRequest() {
    final RequestBody body = RequestBody.create(TEXT, EMPTY);
    final String url = URL.concat(PATH).concat("123,456,789");
    return new Request.Builder().url(url).post(body).build();
  }

  private void showSnackBar(String message) {
    Snackbar.make(findViewById(R.id.cl_main), message, BaseTransientBottomBar.LENGTH_SHORT).show();
  }
}
