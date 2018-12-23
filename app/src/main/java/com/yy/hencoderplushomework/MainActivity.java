package com.yy.hencoderplushomework;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yy.hencoderplushomework.touch.TouchPerClickView;
import com.yy.hencoderplushomework.view.AvatarView;
import com.yy.hencoderplushomework.view.FloatingEdit;
import com.yy.hencoderplushomework.view.ImageTextView;
import com.yy.hencoderplushomework.view.MultiLineTextView;
import com.yy.hencoderplushomework.view.PagingRotateView;
import com.yy.hencoderplushomework.view.DashBoardView;
import com.yy.hencoderplushomework.view.PieChartView;
import com.yy.hencoderplushomework.view.SecondOrderBezier;
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
    public static final String MULTI_LINE_TEXT_VIEW = "多行文字";
    public static final String AVATAR_VIEW = "圆形头像";
    public static final String PAGING_ROTATE = "翻页+波纹";
    public static final String IMAGE_TEXT = "图文混合排版";
    public static final String MATERIAL_EDIT_TEXT = "floating编辑框";
    public static final String FLOW_LAYOUT = "流式布局";
    public static final String SQUARE_IMAGE_VIEW = "方形imageView";
    public static final String CIRCLE_VIEW = "圆形View";
    public static final String BEZIER_VIEW = "二阶贝塞尔";
    public static final String TOUCH_VIEW_PER_CLICK = "触摸模拟点击";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView buttonRecyclerViewNumber1 = findViewById(R.id.buttonRecyclerView_number1);
        buttonRecyclerViewNumber1.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false));
        buttonRecyclerViewNumber1.setAdapter(new ButtonAdapter(this,
                getStringsNumber1()));
        SnapHelper snapHelperNumber1 = new LinearSnapHelper();
        snapHelperNumber1.attachToRecyclerView(buttonRecyclerViewNumber1);

        RecyclerView buttonRecyclerViewNumber2 = findViewById(R.id.buttonRecyclerView_number2);
        buttonRecyclerViewNumber2.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false));
        buttonRecyclerViewNumber2.setAdapter(new ButtonAdapter(this,
                getStringsNumber2()));
        SnapHelper snapHelperNumber2 = new LinearSnapHelper();
        snapHelperNumber2.attachToRecyclerView(buttonRecyclerViewNumber2);

        RecyclerView buttonRecyclerViewNumber3 = findViewById(R.id.buttonRecyclerView_number3);
        buttonRecyclerViewNumber3.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false));
        buttonRecyclerViewNumber3.setAdapter(new ButtonAdapter(this,
                getStringsNumber3()));
        SnapHelper snapHelperNumber3 = new LinearSnapHelper();
        snapHelperNumber3.attachToRecyclerView(buttonRecyclerViewNumber3);

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
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
                            case PAGING_ROTATE:
                                container.addView(new PagingRotateView(context));
                                break;
                            case MULTI_LINE_TEXT_VIEW:
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    container.addView(new MultiLineTextView(context));
                                }
                                break;
                            case IMAGE_TEXT:
                                container.addView(new ImageTextView(context));
                                break;
                            case MATERIAL_EDIT_TEXT:
                                LinearLayout linearLayout = new LinearLayout(context);
                                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT
                                ));
                                linearLayout.setOrientation(LinearLayout.VERTICAL);
                                AppCompatEditText editText = new AppCompatEditText(context);
                                editText.setLayoutParams(new ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT
                                ));
                                editText.setHint("作为参照的原始EditText");
                                editText.setBackgroundColor(ContextCompat.getColor(
                                        context, android.R.color.holo_blue_light
                                ));
                                linearLayout.addView(editText);
                                FloatingEdit floatingEdit = new FloatingEdit(context);
                                floatingEdit.setFloatingUse(true);
                                linearLayout.addView(floatingEdit);
                                container.addView(linearLayout);
                                break;
                            case SQUARE_IMAGE_VIEW:
                                View squareImgView = LayoutInflater.from(context).inflate(R.layout.view_square_img, null);
                                container.addView(squareImgView);
                                break;
                            case FLOW_LAYOUT:

                                break;
                            case CIRCLE_VIEW:
                                View circleImgView = LayoutInflater.from(context).inflate(R.layout.view_circle, null);
                                container.addView(circleImgView);
                                break;
                            case BEZIER_VIEW:
                                container.addView(new SecondOrderBezier(context));
                                break;
                            case TOUCH_VIEW_PER_CLICK:
                                TouchPerClickView touchPerClickView = new TouchPerClickView(context);
                                touchPerClickView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                container.addView(touchPerClickView);
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

    private List<String> getStringsNumber1() {
        List<String> strings = new ArrayList<>();
        strings.add(PAGING_ROTATE);
        strings.add(PIE_CHART_VIEW);
        strings.add(DASH_BOARD_VIEW);
        strings.add(SPORT_VIEW);
        strings.add(AVATAR_VIEW);
        strings.add(IMAGE_TEXT);
        strings.add(MULTI_LINE_TEXT_VIEW);
        strings.add(TEXT_ALIGN_VIEW);
        return strings;
    }

    private List<String> getStringsNumber2() {
        List<String> strings = new ArrayList<>();
        strings.add(MATERIAL_EDIT_TEXT);
        strings.add(FLOW_LAYOUT);
        strings.add(SQUARE_IMAGE_VIEW);
        strings.add(CIRCLE_VIEW);
        strings.add(BEZIER_VIEW);
        return strings;
    }

    private List<String> getStringsNumber3(){
        List<String> strings = new ArrayList<>();
        strings.add(TOUCH_VIEW_PER_CLICK);
        return strings;
    }
}
