package com.github.pwittchen.client;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.pwittchen.reactivesensors.library.ReactiveSensorEvent;
import com.github.pwittchen.reactivesensors.library.ReactiveSensorFilter;
import com.github.pwittchen.reactivesensors.library.ReactiveSensors;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
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
  // address of the localhost visible from device emulator
  private final static String URL = "http://10.0.2.2:8080/";
  private final static String PATH = "sensor?reading=";
  private final static MediaType TEXT = MediaType.parse("text/plain; charset=utf-8");

  private final Call.Factory client = new OkHttpClient();
  private ReactiveSensors reactiveSensors;
  private Disposable disposable;

  @BindView(R.id.cl_main) ConstraintLayout mainLayout;
  @BindView(R.id.tv_readings) TextView tvReadings;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    reactiveSensors = new ReactiveSensors(this);
  }

  @OnClick(R.id.btn_start_reading) public void startReadingSensors() {

  }

  private String getSensorReading(ReactiveSensorEvent reactiveSensorEvent) {
    SensorEvent event = reactiveSensorEvent.getSensorEvent();
    float x = event.values[0];
    float y = event.values[1];
    float z = event.values[2];
    return String.format(Locale.getDefault(), "%f,%f,%f", x, y, z);
  }

  @OnClick(R.id.btn_stop_reading) public void stopReadingSensors() {
    safelyDisposeDisposable();
  }

  private Completable performRequest(final String message) {
    return Completable.create(
        emitter -> client.newCall(createRequest(message)).enqueue(new Callback() {
          @Override public void onFailure(@NonNull Call c, @NonNull IOException e) {
            emitter.onError(e);
          }

          @Override public void onResponse(@NonNull Call c, @NonNull Response r)
              throws IOException {
            emitter.onComplete();
          }
        }));
  }

  private Request createRequest(final String message) {
    final String url = URL.concat(PATH).concat(message);
    final RequestBody body = RequestBody.create(TEXT, EMPTY);
    return new Request.Builder().url(url).post(body).build();
  }

  @Override protected void onPause() {
    super.onPause();
    safelyDisposeDisposable();
  }

  private void safelyDisposeDisposable() {
    if (disposable != null && !disposable.isDisposed()) {
      disposable.dispose();
    }
  }
}
