// Generated by Dagger (https://dagger.dev).
package com.hxyc.myframework.module.actcen;

import com.hxyc.myframework.db.SP;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class PromoteValueFragment_MembersInjector implements MembersInjector<PromoteValueFragment> {
  private final Provider<PromoteValuePresenter> mPresenterProvider;

  private final Provider<SP> mSpProvider;

  public PromoteValueFragment_MembersInjector(Provider<PromoteValuePresenter> mPresenterProvider,
      Provider<SP> mSpProvider) {
    this.mPresenterProvider = mPresenterProvider;
    this.mSpProvider = mSpProvider;
  }

  public static MembersInjector<PromoteValueFragment> create(
      Provider<PromoteValuePresenter> mPresenterProvider, Provider<SP> mSpProvider) {
    return new PromoteValueFragment_MembersInjector(mPresenterProvider, mSpProvider);
  }

  @Override
  public void injectMembers(PromoteValueFragment instance) {
    injectMPresenter(instance, mPresenterProvider.get());
    injectMSp(instance, mSpProvider.get());
  }

  @InjectedFieldSignature("com.hxyc.myframework.module.actcen.PromoteValueFragment.mPresenter")
  public static void injectMPresenter(PromoteValueFragment instance,
      PromoteValuePresenter mPresenter) {
    instance.mPresenter = mPresenter;
  }

  @InjectedFieldSignature("com.hxyc.myframework.module.actcen.PromoteValueFragment.mSp")
  public static void injectMSp(PromoteValueFragment instance, SP mSp) {
    instance.mSp = mSp;
  }
}