// Generated by Dagger (https://dagger.dev).
package com.hxyc.myframework.base;

import com.hxyc.myframework.db.SP;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class BasePresenter_MembersInjector implements MembersInjector<BasePresenter> {
  private final Provider<SP> mSPProvider;

  public BasePresenter_MembersInjector(Provider<SP> mSPProvider) {
    this.mSPProvider = mSPProvider;
  }

  public static MembersInjector<BasePresenter> create(Provider<SP> mSPProvider) {
    return new BasePresenter_MembersInjector(mSPProvider);
  }

  @Override
  public void injectMembers(BasePresenter instance) {
    injectMSP(instance, mSPProvider.get());
  }

  @InjectedFieldSignature("com.hxyc.myframework.base.BasePresenter.mSP")
  public static void injectMSP(BasePresenter instance, SP mSP) {
    instance.mSP = mSP;
  }
}
