package com.ramanbyte.pibm_in.di;

import com.ramanbyte.pibm_in.data.local.NavigationDao;
import com.ramanbyte.pibm_in.data.local.PibmDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideNavigationDaoFactory implements Factory<NavigationDao> {
  private final Provider<PibmDatabase> databaseProvider;

  public AppModule_ProvideNavigationDaoFactory(Provider<PibmDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public NavigationDao get() {
    return provideNavigationDao(databaseProvider.get());
  }

  public static AppModule_ProvideNavigationDaoFactory create(
      Provider<PibmDatabase> databaseProvider) {
    return new AppModule_ProvideNavigationDaoFactory(databaseProvider);
  }

  public static NavigationDao provideNavigationDao(PibmDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideNavigationDao(database));
  }
}
