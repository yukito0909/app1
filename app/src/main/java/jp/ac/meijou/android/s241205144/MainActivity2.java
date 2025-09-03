package jp.ac.meijou.android.s241205144;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Optional;

import jp.ac.meijou.android.s241205144.databinding.ActivityMain2Binding;
import jp.ac.meijou.android.s241205144.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {
    private ActivityMain2Binding binding;
    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->{
                switch (result.getResultCode()){
                    case RESULT_OK -> {
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra("ret"))
                                .map(text -> "Result: " + text)
                                .ifPresent(text -> binding.textView6.setText(text));
                    }
                    case RESULT_CANCELED -> {
                        binding.textView6.setText("Result: Canceled");

                    }
                    default -> {
                        binding.textView6.setText("Result: Inkown(" + result.getResultCode() + ")");
                    }
            }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.buttonA.setOnClickListener(v -> {
            var intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });

        binding.buttonB.setOnClickListener(v -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://abehiroshi.la.coocan.jp/"));
            startActivity(intent);

        });

        binding.button18.setOnClickListener( v -> {
            var text = binding.editTextText2.getText().toString();
            var intent = new Intent(this, MainActivity4.class);
            intent.putExtra("title", text);
            startActivity(intent);
        });
        binding.button19.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity4.class);
            getActivityResult.launch(intent);
        });
    }
}