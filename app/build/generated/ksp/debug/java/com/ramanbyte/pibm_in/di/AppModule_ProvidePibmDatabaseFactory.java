package com.ramanbyte.pibm_in.di;

import android.content.Context;
import com.ramanbyte.pibm_in.data.local.PibmDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AppModule_ProvidePibmDatabaseFactory implements Factory<PibmDatabase> {
  private final Provider<Context> contextProvider;

  public AppModule_ProvidePibmDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public PibmDatabase get() {
    return providePibmDatabase(contextProvider.get());
  }

  public static AppModule_ProvidePibmDatabaseFactory create(Provider<Context> contextProvider) {
    return new AppModule_ProvidePibmDatabaseFactory(contextProvider);
  }

  public static PibmDatabase providePibmDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providePibmDatabase(context));
  }
}
