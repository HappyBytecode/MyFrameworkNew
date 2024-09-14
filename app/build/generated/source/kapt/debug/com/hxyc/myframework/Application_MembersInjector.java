// Generated by Dagger (https://dagger.dev).
package com.hxyc.myframework;

import com.hxyc.myframework.db.SP;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class Application_MembersInjector implements MembersInjector<Application> {
  private final Provider<SP> mSPProvider;

  public Application_MembersInjector(Provider<SP> mSPProvider) {
    this.mSPProvider = mSPProvider;
  }

  public static MembersInjector<Application> create(Provider<SP> mSPProvider) {
    return new Application_MembersInjector(mSPProvider);
  }

  @Override
  public void injectMembers(Application instance) {
    injectMSP(instance, mSPProvider.get());
  }

  @InjectedFieldSignature("com.hxyc.myframework.Application.mSP")
  public static void injectMSP(Application instance, SP mSP) {
    instance.mSP = mSP;
  }
}