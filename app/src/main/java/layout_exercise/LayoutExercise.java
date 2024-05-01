package layout_exercise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sajulga_androiddev.R;

public class LayoutExercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_layout_exercise);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.paneMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnMain = findViewById(R.id.btnMain);

        btnMain.setOnClickListener((view) -> {
            Toast.makeText(this, "Moving to main...", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(LayoutExercise.this, InstagramActivity.class);
            startActivity(intent1);
        });
    }
}