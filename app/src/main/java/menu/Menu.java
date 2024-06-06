package menu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sajulga_androiddev.R;

public class Menu extends AppCompatActivity {


    Button btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.paneMainMenu), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnMain = findViewById(R.id.btnMain);
        Toolbar toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
    }



    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu_exercise, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemBg) {
            btnMain.setBackgroundColor(0xFFFF0000);
            Toast.makeText(this, "BG color is changed", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.itemOpacity) {
            btnMain.setBackgroundColor(0x10FF0000);
            Toast.makeText(this, "Opacity is changed", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.itemText) {
            btnMain.setText("Sir Von Gwapo");
            Toast.makeText(this, "Text is changed", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.itemFontSize) {
            btnMain.setTextSize(20);
            Toast.makeText(this, "Font size is changed", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.itemShape) {
            // TODO: set to something else
            btnMain.setBackgroundResource(R.drawable.rectangle);
            Toast.makeText(this, "Shape is changed", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.itemExit) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}