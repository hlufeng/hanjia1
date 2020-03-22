package com.example.guochuang1.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.guochuang1.OrderListActivity;
import com.example.guochuang1.R;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    TextView tv1,tv2,tv3;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //
            }
        });
        //设置自定义顶部导航栏文字
        TextView tv_title = getActivity().findViewById(R.id.tv_title);
        tv_title.setText("个人中心");
        tv_title.setGravity(Gravity.CENTER);
        tv1 = root.findViewById(R.id.tv1);
        tv2 = root.findViewById(R.id.tv2);
        tv3 = root.findViewById(R.id.tv3);
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"暂未开放",Toast.LENGTH_SHORT).show();
            }
        });tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"暂未开放",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getActivity(), OrderListActivity.class);
                startActivity(intent);
            }
        });tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"暂未开放",Toast.LENGTH_SHORT).show();
            }
        });



        return root;
    }
}
