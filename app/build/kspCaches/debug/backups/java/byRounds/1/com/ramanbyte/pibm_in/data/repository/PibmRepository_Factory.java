package com.ramanbyte.pibm_in.data.repository;

import com.ramanbyte.pibm_in.data.local.NavigationDao;
import com.ramanbyte.pibm_in.data.remote.RemoteConfigManager;
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
public final class PibmRepository_Factory implements Factory<PibmRepository> {
  private final Provider<NavigationDao> navigationDaoProvider;

  private final Provider<RemoteConfigManager> remoteConfigManagerProvider;

  public PibmRepository_Factory(Provider<NavigationDao> navigationDaoProvider,
      Provider<RemoteConfigManager> remoteConfigManagerProvider) {
    this.navigationDaoProvider = navigationDaoProvider;
    this.remoteConfigManagerProvider = remoteConfigManagerProvider;
  }

  @Override
  public PibmRepository get() {
    return newInstance(navigationDaoProvider.get(), remoteConfigManagerProvider.get());
  }

  public static PibmRepository_Factory create(Provider<NavigationDao> navigationDaoProvider,
      Provider<RemoteConfigManager> remoteConfigManagerProvider) {
    return new PibmRepository_Factory(navigationDaoProvider, remoteConfigManagerProvider);
  }

  public static PibmRepository newInstance(NavigationDao navigationDao,
      RemoteConfigManager remoteConfigManager) {
    return new PibmRepository(navigationDao, remoteConfigManager);
  }
}
