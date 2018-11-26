package com.shop.visitors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shop.visitors.model.Visitor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public final class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    List<Visitor> pList;
    Context mContext;

    public PersonAdapter(List<Visitor> pList, Context context) {
        this.pList = pList;
        this.mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PersonAdapter.ViewHolder holder, int position) {
        Visitor person = pList.get(position);
        String name = (person.getName() == null || person.getName().isEmpty()) ? mContext.getResources().getString(R.string.no_visitors): person.getName();
        holder.mVisiterName.setText(name);
        holder.mVisitTime.setText(getFormatedTime(person.getArriveTime())+" - "+getFormatedTime(person.getLeaveTime()));
    }

    private String getFormatedTime(long timestamp) {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(tz);
        String localTime = sdf.format(new Date(timestamp * 1000));
        return localTime;
    }
    @Override
    public int getItemCount() {
        return pList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mVisiterName;
        public TextView mVisitTime;

        public ViewHolder(View itemView) {
            super(itemView);
            mVisiterName = itemView.findViewById(R.id.vis_name);
            mVisitTime = itemView.findViewById(R.id.vis_time);
        }
    }
}
