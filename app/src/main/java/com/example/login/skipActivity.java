package com.example.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("HandlerLeak")
public class skipActivity extends AppCompatActivity {
    ImageButton ib00, ib01, ib02, ib10, ib11, ib12, ib20, ib21, ib22;
    Button restartBtn;
    TextView timeTv;
    //每行的图片个数//
    private final int imageX = 3;//行//
    private final int imageY = 3;//列//

    //图片总数//
    private final int imgCount = imageX * imageY;
    //空白区域//
    private int blankSwap = imgCount - 1;
    //初始化空白区域位置//
    private int blankImgid = R.id.pt_ib_02x02;

    //    定义技术时间的变量
    int time = 0;
    //    存放碎片的数组，便于进行统一的管理
    private final int[] image = {R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j};
    //    声明一个图片数组的下标数组，随机排列这个数组
    private final int[] imageIndex = new int[image.length];

    @SuppressLint("HandlerLeak")
    Handler handler;

    {
        //noinspection deprecation
        handler = new Handler() {
            @SuppressLint("SetTextI18n")
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    time++;
                    timeTv.setText("时间：" + time + "秒");
                    handler.sendEmptyMessageDelayed(1, 1000);
                }
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_skip);
        initView();
//    打乱碎片的函数
        disruptRandom();
        handler.sendEmptyMessageDelayed(1, 1000);
    }

    //随机打乱数组当中的元素，以不规则的形式进行图片显示
    private void disruptRandom() {
        for (int i = 0; i < imageIndex.length; i++) {
            imageIndex[i] = i;
        }
//    规定二十次随机选择两个角标对应的值进行交换
        int rand1, rand2;
        for (int j = 0; j < 30; j++) {
//        随机生成第一个角标 生成0-8之间的随机数
            rand1 = (int) (Math.random() * (imageIndex.length - 1));
//        第二次随机生成的角标不能和第一次随机生成的相同
            do {
                rand2 = (int) (Math.random() * (imageIndex.length - 1));
            } while (rand1 == rand2);
//        交换两个角标上对应的值
            swap(rand1, rand2);
        }
//    随机到指定的控件上
        ib00.setImageResource(image[imageIndex[0]]);
        ib01.setImageResource(image[imageIndex[1]]);
        ib02.setImageResource(image[imageIndex[2]]);
        ib10.setImageResource(image[imageIndex[3]]);
        ib11.setImageResource(image[imageIndex[4]]);
        ib12.setImageResource(image[imageIndex[5]]);
        ib20.setImageResource(image[imageIndex[6]]);
        ib21.setImageResource(image[imageIndex[7]]);
        ib22.setImageResource(image[imageIndex[8]]);


    }

    //    交换数组指定角标上的数据
    private void swap(int rand1, int rand2) {
        int temp = imageIndex[rand1];
        imageIndex[rand1] = imageIndex[rand2];
        imageIndex[rand2] = temp;
    }

    //初始化控件
    private void initView() {
        ib00 = findViewById(R.id.pt_ib_00x00);
        ib01 = findViewById(R.id.pt_ib_00x01);
        ib02 = findViewById(R.id.pt_ib_00x02);
        ib10 = findViewById(R.id.pt_ib_01x00);
        ib11 = findViewById(R.id.pt_ib_01x01);
        ib12 = findViewById(R.id.pt_ib_01x02);
        ib20 = findViewById(R.id.pt_ib_02x00);
        ib21 = findViewById(R.id.pt_ib_02x01);
        ib22 = findViewById(R.id.pt_ib_02x02);
        timeTv = findViewById(R.id.time);
        restartBtn = findViewById(R.id.pt_btn_restart);

    }

    public void onClick(View view) {
        int id = view.getId();
//        九个按钮执行的点击事件的逻辑

        if (id == R.id.pt_ib_00x00) {
            move(R.id.pt_ib_00x00, 0);
        } else if (id == R.id.pt_ib_00x01) {
            move(R.id.pt_ib_00x01, 1);
        } else if (id == R.id.pt_ib_00x02) {
            move(R.id.pt_ib_00x02, 2);
        } else if (id == R.id.pt_ib_01x00) {
            move(R.id.pt_ib_01x00, 3);
        } else if (id == R.id.pt_ib_01x01) {
            move(R.id.pt_ib_01x01, 4);
        } else if (id == R.id.pt_ib_01x02) {
            move(R.id.pt_ib_01x02, 5);
        } else if (id == R.id.pt_ib_02x00) {
            move(R.id.pt_ib_02x00, 6);
        } else if (id == R.id.pt_ib_02x01) {
            move(R.id.pt_ib_02x01, 7);
        } else if (id == R.id.pt_ib_02x02) {
            move(R.id.pt_ib_02x02, 8);
        }
    }

    //将图片和空白区域交换
    private void move(int imagebuttonId, int site) {
        //判断照片的位置//
        int sitex = site % imageX;
        int sitey = site / imageY;
        //空白区域图标//
        int blankx = blankSwap % imageX;
        int blanky = blankSwap / imageY;
        //移动条件：在同一行，列数相减，绝对值为一；在同一列，列数相减，绝对值为一；//
        int x = Math.abs(sitex - blankx);
        int y = Math.abs(sitey - blanky);
        if ((x == 0 && y == 1) || (y == 0 && x == 1)) {
            //通过ID，查找到可移动的按钮//
            ImageButton clickButton = findViewById(imagebuttonId);
            clickButton.setVisibility(View.INVISIBLE);
            //查找到空白的按钮//
            ImageButton blankButton = findViewById(blankImgid);
            //将空白区域设为图片//
            blankButton.setImageResource(image[imageIndex[site]]);
            //移动之前不可见，移动之后可见//
            blankButton.setVisibility(View.VISIBLE);
            //将改变角标的过程记录存储到图片位置数组中//
            swap(site, blankSwap);
            //新的空白区域位置更新等于传入的点击按钮的位置//
            blankSwap = site;
            blankImgid = imagebuttonId;

        }
        //判断是否完成拼图//
        judgeGamover();
    }

    private void judgeGamover() {
        boolean loop=true;
        for(int i=0;i<imageIndex.length;i++){
            if(imageIndex[i]!=i){
                loop=false;
                break;
            }

        }
        if(loop){
            //拼图成功，停止计时//
            handler.removeMessages(1);
            //禁止移动按钮//
            ib00.setClickable(false);
            ib01.setClickable(false);
            ib02.setClickable(false);
            ib10.setClickable(false);
            ib11.setClickable(false);
            ib12.setClickable(false);
            ib20.setClickable(false);
            ib21.setClickable(false);
            ib22.setClickable(false);
            ib22.setImageResource(image[8]);
            ib22.setVisibility(View.VISIBLE);
            //弹出成功对话框//
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("恭喜，拼图成功！您的用时为" + time + "秒！")
                    .setPositiveButton("确认", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @SuppressLint("SetTextI18n")
    public void restart(View view){
        restore();
        //重新开始允许移动碎片//

        disruptRandom();
        handler.removeMessages(1);
        time = 0;
        timeTv.setText("时间：" + time + "秒");
        handler.sendEmptyMessageDelayed(1, 1000);

    }

    private void restore() {
        ib00.setClickable(true);
        ib01.setClickable(true);
        ib02.setClickable(true);
        ib10.setClickable(true);
        ib11.setClickable(true);
        ib12.setClickable(true);
        ib20.setClickable(true);
        ib21.setClickable(true);
        ib22.setClickable(true);
        ImageButton clickBtn = findViewById(blankImgid);
        clickBtn.setVisibility(View.VISIBLE);
        ImageButton blankBtn=findViewById(R.id.pt_ib_02x02);
        blankBtn.setVisibility(View.INVISIBLE);
        blankImgid=R.id.pt_ib_02x02;
        blankSwap=imgCount-1;
    }
}