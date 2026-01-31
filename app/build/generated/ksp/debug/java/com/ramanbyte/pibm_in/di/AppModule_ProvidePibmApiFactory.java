package com.ramanbyte.pibm_in.di;

import com.ramanbyte.pibm_in.data.remote.PibmApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AppModule_ProvidePibmApiFactory implements Factory<PibmApi> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  public AppModule_ProvidePibmApiFactory(Provider<OkHttpClient> okHttpClientProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public PibmApi get() {
    return providePibmApi(okHttpClientProvider.get());
  }

  public static AppModule_ProvidePibmApiFactory create(
      Provider<OkHttpClient> okHttpClientProvider) {
    return new AppModule_ProvidePibmApiFactory(okHttpClientProvider);
  }

  public static PibmApi providePibmApi(OkHttpClient okHttpClient) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providePibmApi(okHttpClient));
  }
}
