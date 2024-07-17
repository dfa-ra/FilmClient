package com.example.first;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.reactivestreams.Subscription;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Stack;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    public interface RequestFilm {
        @Headers({
                "X-API-KEY: " + apikey,
                "Content-Type: application/json"
                })
        @GET("/api/v2.2/films/{id}")
        Observable<FilmData> getFilm(@Path("id") Integer id);
    }
    //https://kinopoiskapiunofficial.tech/
    private final static String Tag = "MoyaProgamma";
    private final static String BaseURL = "https://kinopoiskapiunofficial.tech/";
    private final static String apikey = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b";

    public static Integer count = 0;
    private TextView time;
    private TextView getText;
    private Disposable disposable;

    @SuppressLint({"CheckResult", "SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        time = findViewById(R.id.time);
//        getText = findViewById(R.id.getText);

        Retrofit retrofit = RetrofitClient.getClient(BaseURL);
        RequestFilm requestFilm = retrofit.create(RequestFilm.class);

        Observable<FilmData> observable = requestFilm.getFilm(301);

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()) // Обработка результата в главном потоке
                .subscribe(new Observer<FilmData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FilmData filmData) {
//                        getText.setText(filmData.nameRu); // Успешный ответ
                    }

                    @Override
                    public void onError(Throwable e) {
//                        getText.setText(e.getMessage()); // Ошибка
                    }

                    @Override
                    public void onComplete() {
                        // Здесь можно обработать завершение подписки, если нужно
                    }
                });

        disposable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread()) // Выполнение в фоновом потоке
                .observeOn(AndroidSchedulers.mainThread()) // Результаты получаем в основном потоке (UI)
                .subscribe(
                        tick -> updateTime(),
                        throwable -> Log.e(Tag, "Error in timer", throwable)
                );

    }
    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        time.setText(currentTime);
    }
}