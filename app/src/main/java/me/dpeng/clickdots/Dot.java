package me.dpeng.clickdots;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.NonNull;

/**
 * Created by Daniel Peng on 7/22/2018.
 *
 */

public class Dot implements Comparable<Dot>{
    private int x;
    private int y;
    private int diameter;
    private int radius; // not necessary (since we have diameter) but used to draw things faster
    private int color;
    private int splitLevel;

    public Dot(int x, int y, int diameter, int color, int splitLevel) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.radius = (int)Math.ceil(((float)diameter)/2.0);
        this.color = color;
        this.splitLevel = splitLevel;
    }

    public void draw(Canvas canvas, Paint paint, boolean clearBackGround) {

        if(Utilities.isSquareMode) {
            paint.setColor(this.color);
            canvas.drawRect(x, y, x + diameter, y + diameter, paint);
        } else {
            if(clearBackGround) {
                // clear the background if needed
                canvas.drawRect(x, y, x + diameter, y + diameter, GameView.clearPaint);
            }
            // draw the actual circle
            paint.setColor(this.color);
            canvas.drawCircle(x + radius, y + radius, radius, paint);
        }

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
        this.radius = diameter/2;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSplitLevel() {
        return splitLevel;
    }

    public void setSplitLevel(int splitLevel) {
        this.splitLevel = splitLevel;
    }

    /**
     * Returns true if position x, y is within the bounding box of this Dot, and false otherwise
     * @param touchX
     * @param touchY
     * @return
     */
    public boolean isTouchInside(float touchX, float touchY) {
        return touchX > x && touchX < x + diameter &&
                touchY > y && touchY < y + diameter;
    }

    // sorts based on y position. If they are the same, sorts based on x position.
    @Override
    public int compareTo(@NonNull Dot o) {
        if(this.y > o.getY()) {
            return 1;
        } else if (this.y < o.getY()) {
            return -1;
        } else {
            return this.x > o.getX() ? 1 : (this.x < o.getX() ? -1 : 0);
        }
    }

    @Override
    public String toString() {
        return x + "," + y + "," + color + "," + splitLevel + ";";
    }
}


