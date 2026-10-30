package com.example.a20243007011_p5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Municipality extends AppCompatActivity {

    private String currentMunicipality = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        // 1. 获取所有组件
        TextView nameTv = findViewById(R.id.municipalityName);
        TextView infoTv = findViewById(R.id.municipalityInfo);
        ImageView imageIv = findViewById(R.id.municipalityImage);
        Button visitBtn = findViewById(R.id.visitButton);

        // 2. 接收 MainActivity 传来的数据并安全验证
        String receivedName = getIntent().getStringExtra("municipality_name");
        currentMunicipality = TextProcessor.processMunicipality(receivedName);

        // 如果传过来的数据无效，直接关闭当前页面（安全处理）
        if (currentMunicipality == null) {
            finish();
            return;
        }

        // 3. 设置城市名称
        nameTv.setText(currentMunicipality);

        // 4. 获取数据源
        String[] infos = getResources().getStringArray(R.array.municipalities_array);

        // 5. 根据城市定制 UI 和文字 (利用 switch 判断)
        switch (currentMunicipality) {
            case "Beijing":
                nameTv.setBackgroundColor(ContextCompat.getColor(this, R.color.municipality_Beijing));
                nameTv.setTextColor(ContextCompat.getColor(this, R.color.color_textlight));
                infoTv.setTextColor(ContextCompat.getColor(this, R.color.municipality_Beijing));
                imageIv.setImageResource(R.drawable.beijing);
                visitBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.municipality_Beijing));
                infoTv.setText(infos[0]); // 索引0是北京
                break;

            case "Shanghai":
                nameTv.setBackgroundColor(ContextCompat.getColor(this, R.color.municipality_shanghai));
                nameTv.setTextColor(ContextCompat.getColor(this, R.color.color_textlight));
                infoTv.setTextColor(ContextCompat.getColor(this, R.color.municipality_shanghai));
                imageIv.setImageResource(R.drawable.shanghai);
                visitBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.municipality_shanghai));
                infoTv.setText(infos[1]); // 索引1是上海
                break;

            case "Chongqing":
                nameTv.setBackgroundColor(ContextCompat.getColor(this, R.color.municipality_chongqing));
                nameTv.setTextColor(ContextCompat.getColor(this, R.color.color_textlight));
                infoTv.setTextColor(ContextCompat.getColor(this, R.color.municipality_chongqing));
                imageIv.setImageResource(R.drawable.chongqing);
                visitBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.municipality_chongqing));
                infoTv.setText(infos[2]); // 索引2是重庆
                break;

            case "Tianjin":
                nameTv.setBackgroundColor(ContextCompat.getColor(this, R.color.municipality_tianjin));
                nameTv.setTextColor(ContextCompat.getColor(this, R.color.color_textlight));
                infoTv.setTextColor(ContextCompat.getColor(this, R.color.municipality_tianjin));
                imageIv.setImageResource(R.drawable.tianjin);
                visitBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.municipality_tianjin));
                infoTv.setText(infos[3]); // 索引3是天津
                break;
        }

        // 6. 隐式 Intent 打开官网 (第八步)
        visitBtn.setOnClickListener(v -> {
            String url = "";
            switch (currentMunicipality) {
                case "Beijing": url = "https://english.beijing.gov.cn/"; break;
                case "Shanghai": url = "https://english.shanghai.gov.cn/"; break;
                case "Tianjin": url = "https://en.tj.gov.cn/"; break;
                case "Chongqing": url = "https://english.cq.gov.cn/"; break;
            }

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            // 安全处理：检查是否有能处理该 Intent 的应用（如浏览器）
            if (browserIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(browserIntent);
            } else {
                Toast.makeText(this, "No browser app found.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}