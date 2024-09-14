package com.hxyc.myframework.module.actcen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hxyc.myframework.Application;
import com.hxyc.myframework.R;
import com.hxyc.myframework.base.LibBaseFragment;
import com.hxyc.myframework.bean.PromoteItemEntity;
import com.hxyc.myframework.db.SP;
import com.hxyc.myframework.event.TestEvent;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PromoteValueFragment extends LibBaseFragment
        implements PromoteValueContract.View {

    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    @BindView(R.id.recycler_view_basic)
    RecyclerView mRecyclerViewBasic;

    @BindView(R.id.cons_basic)
    View basicView;

    @BindView(R.id.tv_test)
    TextView tvTest;
    @BindView(R.id.tv_event_update)
    TextView tvEventUpdate;
    @BindView(R.id.iv_pic)
    ImageView imageView;


    @Inject
    PromoteValuePresenter mPresenter;

    @Inject
    SP mSp;

    private PromoteValueAdapter mAdapterBasic;

    public static PromoteValueFragment newInstance() {
        PromoteValueFragment fragment = new PromoteValueFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_promote_value, container, false);
        ButterKnife.bind(this, mView);
        EventBus.getDefault().register(this);
        initView();
        return mView;
    }

    private void initView() {
        mRecyclerViewBasic.setLayoutManager(new LinearLayoutManager(requireContext()));
        mAdapterBasic = new PromoteValueAdapter(requireContext());
        mRecyclerViewBasic.setAdapter(mAdapterBasic);

        tvEventUpdate.setText("获取数据");
        mSp.putString("testValue", "SP的测试");
        tvTest.setText(mSp.getString("testValue"));
        Glide.with(getContext()).load("https://img1.baidu.com/it/u=413643897,2296924942&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500")
                .error(R.mipmap.ic_launcher)
                .into(imageView);
        mSmartRefreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> mPresenter.subscribe());
        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {});
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DaggerPromoteValueComponent.builder()
                .appComponent(Application.getAppComponent())
                .promoteValueModule(new PromoteValueModule(this))
                .build().inject(this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showBasic(List<PromoteItemEntity.BasicBean> basicBeans) {
        if (basicBeans != null && basicBeans.size() > 0) {
            mAdapterBasic.setAll(basicBeans);
            basicView.setVisibility(View.VISIBLE);
        } else {
            basicView.setVisibility(View.GONE);
        }

    }

    @OnClick(R.id.tv_event_update)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_event_update:
                mPresenter.subscribe();
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TestEvent event) {
        tvEventUpdate.setEnabled(false);
        tvEventUpdate.setText("网络出错了");
    }
}
