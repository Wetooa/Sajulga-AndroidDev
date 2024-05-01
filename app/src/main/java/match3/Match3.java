package match3;


import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.example.sajulga_androiddev.R;

public class Match3 extends AppCompatActivity {

    private static final int BOARD_LENGTH = 5;
    private static final int BOARD_WIDTH = 5;
    private TextView txtPoints;
    private Button btnReset;
    private Cell[][] board = new Cell[5][5];
    private Integer points = 0;
    private List<Coordinate> matches = new ArrayList<>();
    private Cell clicked1;


    public int checkDirection(Coordinate start, DeltaChange change, int times) {
        int i = start.getRow();
        int j = start.getCol();

        int r = i + change.getDr();
        int c = j + change.getDc();

        if (r >= 0 && c >= 0 && r < BOARD_LENGTH && c < BOARD_WIDTH && board[i][j].getColor().equals(board[r][c].getColor())) {
            times = checkDirection(new Coordinate(r, c), change, times + 1);
        }

        if (times >= 3)
            matches.add(start);

        return times;
    }

    public void checkDirection(Coordinate start, DeltaChange change) {
        checkDirection(start, change, 1);
    }

    public boolean checkBoard() {
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                checkDirection(new Coordinate(i, j), new DeltaChange(0, 1));
                checkDirection(new Coordinate(i, j), new DeltaChange(1, 0));
            }
        }


        for (Coordinate match : matches) {
            int r = match.getRow();
            int c = match.getCol();
            board[r][c].changeColor();
        }


        boolean found = !matches.isEmpty();

        HashSet<Coordinate> unique = new HashSet<>(matches);

        points += Math.max(0, unique.size() - 2);
        matches.clear();
        return found;
    }


    public void reset() {

        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                board[i][j].changeColor();
            }
        }
        points = 0;
        update();
    }

    public void update() {
        while (checkBoard()) ;

        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                board[i][j].update();
            }
        }

        txtPoints.setText(String.valueOf(points));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_match3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.paneMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                Button btn = findViewById(this.getResources().getIdentifier("btn" + String.valueOf(i) + String.valueOf(j), "id", this.getPackageName()));
                board[i][j] = new Cell(btn, new Coordinate(i, j));
            }
        }

        txtPoints = findViewById(R.id.txtPoints);
        btnReset = findViewById(R.id.btnReset);


        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                final int r = i;
                final int c = j;
                board[i][j].getButton().setOnClickListener(view -> {
                    if (clicked1 == null) {
                        clicked1 = board[r][c];
                        clicked1.setSelected(true);

                    } else if (board[r][c].getCoordinate().equals(clicked1.getCoordinate())) {
                        clicked1.setSelected(false);
                        clicked1 = null;

                    } else if (board[r][c].getCoordinate().isAdjacent(clicked1.getCoordinate())) {
                        board[r][c].swapColors(clicked1);
                        clicked1.setSelected(false);
                        clicked1 = null;
                    }

                    update();
                });
            }
        }


        btnReset.setOnClickListener(view -> {
            reset();
        });


        reset();
    }
}
