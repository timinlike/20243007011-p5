package com.example.a20243007011_p5;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 获取输入框
        EditText inputMunicipality = findViewById(R.id.inputMunicipality);

        // 2. 监听键盘的“Go/完成”按钮
        inputMunicipality.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE) {
                // 获取用户输入的文字
                String rawInput = inputMunicipality.getText().toString();

                // 3. 调用 TextProcessor 验证
                String validatedName = TextProcessor.processMunicipality(rawInput);

                // 4. 验证失败：显示错误
                if (validatedName == null) {
                    inputMunicipality.setError("Invalid municipality. Please enter Beijing, Shanghai, Tianjin, or Chongqing.");
                    return true; // 拦截事件，停留在当前页面
                }

                // 5. 验证成功：跳转并传值
                Intent intent = new Intent(MainActivity.this, Municipality.class);
                intent.putExtra("municipality_name", validatedName);
                startActivity(intent);

                return true;
            }
            return false;
        });
    }
}