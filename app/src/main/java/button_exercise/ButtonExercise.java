package button_exercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sajulga_androiddev.R;

public class ButtonExercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_button_exercise);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.paneMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnClose = findViewById(R.id.btnClose);
        Button btnToast = findViewById(R.id.btnToast);
        Button btnChangeBG  = findViewById(R.id.btnChangeBG);
        Button btnChangeButtonBG   = findViewById(R.id.btnChangeButtonBG);
        Button btnDisappear  = findViewById(R.id.btnDisappear);

        btnClose.setOnClickListener((view) -> {
            Intent intent1 = new Intent(ButtonExercise.this, AnotherActivity.class);
            startActivity(intent1);
        });

        btnToast.setOnClickListener((view) -> {
            Toast.makeText(this, "This is a toast button", Toast.LENGTH_LONG).show();
        });

        btnChangeBG.setOnClickListener((view) -> {
            ConstraintLayout layout = findViewById(R.id.paneMain);
            layout.setBackgroundColor(0xFF00FF00);
        });

        btnChangeButtonBG.setOnClickListener((view) -> {
            btnChangeButtonBG.setBackgroundColor(0xFF00FFFF);
        });

        btnDisappear.setOnClickListener((view) -> {
            btnDisappear.setVisibility(View.INVISIBLE);
        });
    }
}