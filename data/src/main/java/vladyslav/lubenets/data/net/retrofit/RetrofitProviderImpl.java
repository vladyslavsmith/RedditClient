package vladyslav.lubenets.data.net.retrofit;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import vladyslav.lubenets.domain.AppInfoProvider;

public class RetrofitProviderImpl implements RetrofitProvider {

    private AppInfoProvider appInfoProvider;
    private Gson gson;

    @Inject
    RetrofitProviderImpl(AppInfoProvider appInfoProvider, Gson gson) {
        this.appInfoProvider = appInfoProvider;
        this.gson = gson;
    }

    @Override
    public Retrofit provide() {
        //todo: add Internet connection check
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor(new StethoInterceptor());
        return new Retrofit.Builder()
                .baseUrl(appInfoProvider.provideBaseEndpoint())
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
