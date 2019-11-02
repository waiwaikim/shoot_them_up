package _04interfaces.E9_13;

import java.awt.*;

public class BetterRectangle extends Rectangle {

    public BetterRectangle(int x, int y, int width, int height){

        super(x, y, width, height);
        super.setLocation(x, y);
        super.setSize(width, height);
    }

    public int getPerimeter(){
        return 2* (width+height);
    }

    public int getArea(){
        return (width*height);
    }
}
