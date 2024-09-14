package com.hxyc.myframework.module.actcen;

import android.content.Context;
import android.view.View;

import com.hxyc.myframework.R;
import com.hxyc.myframework.adapter.SuperAdapter;
import com.hxyc.myframework.adapter.internal.SuperViewHolder;
import com.hxyc.myframework.bean.PromoteItemEntity;
import com.hxyc.myframework.db.SP;

import java.util.ArrayList;


public class PromoteValueAdapter extends SuperAdapter<PromoteItemEntity.BasicBean> {
    private SP mSP;

    public PromoteValueAdapter(Context context) {
        super(context, new ArrayList<>(), R.layout.item_promote_hx_value_layout);
        mSP = new SP(context);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int position, PromoteItemEntity.BasicBean item) {
        holder.setText(R.id.tv_title, item.getName());
        holder.setText(R.id.tv_sub_title, item.getSubTitle());

        holder.getView(R.id.tv_link_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mList.get(position).getSubType()) {
                    case 101://添加紧急联系人
//                        AddPersonActivity.Companion.startActivity(mContext, AddPersonActivity.ADD_PERSON_TYPE_NEW, null, false);
                        break;
                    case 102://完成首单
                    case 201://201快车完单
                    case 202:// 202出租车完单
                    case 301:// 301完成叫车
//                        MainActivity.startIntent(mContext);
                        break;
                    case 203://203团餐完单
//                        WebActivity.Companion.actionStart(mContext,
//                                mList.get(position).getGoUrl() + "?token=" + RetrofitRequestTool.getToken(mSP) + "&notitle=1",
//                                null,
//                                WebActivity.MEAL_TYPE);
                        break;
                    case 302://302订单评价
//                        NewRouteActivity.startIntent(mContext);
                        break;
                    case 303://303意见反馈
//                        FeedbackActivity.startIntent(mContext);
                        break;
                    default:
                        break;
                }
            }
        });

        //101、添加紧急联系人  102完成首单
        if (mList.get(position).getSubType() == 101 || mList.get(position).getSubType() == 102) {
            if (mList.get(position).getIsGoHx() == 0) {
                holder.setText(R.id.tv_link_title, "已完成");
                holder.getView(R.id.tv_link_title).setSelected(true);
                holder.getView(R.id.tv_link_title).setClickable(false);
            } else {
                if (mList.get(position).getSubType() == 101) {
                    holder.setText(R.id.tv_link_title, "去设置");
                } else {
                    holder.setText(R.id.tv_link_title, "去打车");
                }
                holder.getView(R.id.tv_link_title).setSelected(false);
                holder.getView(R.id.tv_link_title).setClickable(true);
            }
        } else {
            switch (mList.get(position).getSubType()) {
                case 201://201快车完单
                case 202:// 202出租车完单
                    holder.setText(R.id.tv_link_title, "去打车");
                    break;
                case 203://203团餐完单
                    holder.setText(R.id.tv_link_title, "去下单");
                    break;
                case 301:// 301完成叫车
                    holder.setText(R.id.tv_link_title, "去叫车");
                    break;
                case 302://302订单评价
                    holder.setText(R.id.tv_link_title, "去评价");
                    break;
                case 303://303意见反馈
                    holder.setText(R.id.tv_link_title, "去完成");
                    break;
                default:
                    break;
            }
            holder.getView(R.id.tv_link_title).setSelected(false);
            holder.getView(R.id.tv_link_title).setClickable(true);
        }
    }

}