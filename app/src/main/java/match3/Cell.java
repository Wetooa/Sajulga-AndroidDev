package match3;


import android.widget.Button;

public class Cell {


    public static final int[] Color = {0xFFFF0000, 0xFF00FF00, 0xFF0000FF, 0xFFFFFF00};


    private Button button;
    private int color;
    private Coordinate pos;


    private boolean isSelected = false;


    public Cell(Button button, Coordinate pos) {
        this.button = button;
        this.pos = pos;
        this.color = getRandomColor();
    }


    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }


    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public boolean isSelected() {
        return isSelected;
    }


    public void update() {
        button.setBackgroundColor(color);

        if (isSelected) {
            // TODO: setborder thing here
            button.setBackgroundColor(color - 0x50000000);
        }
    }

    public void changeColor() {
        int newColor;
        while ((newColor = getRandomColor()) == this.color) ;
        this.color = newColor;
    }


    public static int getRandomColor() {
        int r = (int) (Math.random() * Color.length);
        return Color[r];
    }


    public Coordinate getCoordinate() {
        return pos;
    }


    public void swapColors(Cell other) {
        int temp = color;
        this.setColor(other.getColor());
        other.setColor(temp);
    }
}
