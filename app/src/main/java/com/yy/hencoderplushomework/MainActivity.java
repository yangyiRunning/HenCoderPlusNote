package com.yy.hencoderplushomework;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.yy.hencoderplushomework.view.AvatarView;
import com.yy.hencoderplushomework.view.DashBoardView;
import com.yy.hencoderplushomework.view.PieChartView;
import com.yy.hencoderplushomework.view.SportView;
import com.yy.hencoderplushomework.view.TextAlignView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangyi 2018年11月05日23:42:58
 */
public class MainActivity extends AppCompatActivity {

    private FrameLayout container;
    public static final String PIE_CHART_VIEW = "饼图";
    public static final String DASH_BOARD_VIEW = "仪表盘";
    public static final String SPORT_VIEW = "运动表盘";
    public static final String TEXT_ALIGN_VIEW = "文本内容无关大小绝对靠边";
    public static final String AVATAR_VIEW = "圆形头像";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView buttonRecyclerView = findViewById(R.id.buttonRecyclerView);
        buttonRecyclerView.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false));
        buttonRecyclerView.setAdapter(new ButtonAdapter(this,
                getStrings()));

        container = findViewById(R.id.container);

    }

    class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder> {

        private Context context;
        private List<String> mapList;

        ButtonAdapter(Context context, List<String> mapList) {
            this.context = context;
            this.mapList = mapList;
        }

        @NonNull
        @Override
        public ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ButtonViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_button, null));
        }

        @Override
        public void onBindViewHolder(@NonNull ButtonViewHolder buttonViewHolder, int i) {
            buttonViewHolder.bindView(i);
        }

        @Override
        public int getItemCount() {
            return mapList == null ? 0 : mapList.size();
        }

        class ButtonViewHolder extends RecyclerView.ViewHolder {

            String text;
            Button itemButton;

            ButtonViewHolder(@NonNull View itemView) {
                super(itemView);

                itemButton = itemView.findViewById(R.id.itemButton);
                itemButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        container.removeAllViews();
                        switch (text) {
                            case PIE_CHART_VIEW:
                                container.addView(new PieChartView(context));
                                break;
                            case DASH_BOARD_VIEW:
                                container.addView(new DashBoardView(context));
                                break;
                            case SPORT_VIEW:
                                container.addView(new SportView(context));
                                break;
                            case TEXT_ALIGN_VIEW:
                                container.addView(new TextAlignView(context));
                                break;
                            case AVATAR_VIEW:
                                container.addView(new AvatarView(context));
                                break;
                            default:
                                break;
                        }
                    }
                });
            }

            void bindView(int position) {
                text = mapList.get(position);
                itemButton.setText(text);
            }

        }
    }

    private List<String> getStrings() {
        List<String> strings = new ArrayList<>();
        strings.add(PIE_CHART_VIEW);
        strings.add(DASH_BOARD_VIEW);
        strings.add(SPORT_VIEW);
        strings.add(TEXT_ALIGN_VIEW);
        strings.add(AVATAR_VIEW);
        return strings;
    }
}
