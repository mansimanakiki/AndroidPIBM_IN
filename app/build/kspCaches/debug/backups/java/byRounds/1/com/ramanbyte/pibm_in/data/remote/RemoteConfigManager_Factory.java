package com.ramanbyte.pibm_in.data.remote;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.Gson;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class RemoteConfigManager_Factory implements Factory<RemoteConfigManager> {
  private final Provider<FirebaseRemoteConfig> remoteConfigProvider;

  private final Provider<Gson> gsonProvider;

  public RemoteConfigManager_Factory(Provider<FirebaseRemoteConfig> remoteConfigProvider,
      Provider<Gson> gsonProvider) {
    this.remoteConfigProvider = remoteConfigProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public RemoteConfigManager get() {
    return newInstance(remoteConfigProvider.get(), gsonProvider.get());
  }

  public static RemoteConfigManager_Factory create(
      Provider<FirebaseRemoteConfig> remoteConfigProvider, Provider<Gson> gsonProvider) {
    return new RemoteConfigManager_Factory(remoteConfigProvider, gsonProvider);
  }

  public static RemoteConfigManager newInstance(FirebaseRemoteConfig remoteConfig, Gson gson) {
    return new RemoteConfigManager(remoteConfig, gson);
  }
}
