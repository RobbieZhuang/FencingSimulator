package mechanics;

import java.awt.Rectangle;

public class Hitbox {
    private double lX, tY;
    private double rX, bY;
    private double length, height;
    private Rectangle r;

    public Hitbox(double lX, double tY, double length, double height) {
        this.lX = lX;
        this.tY = tY;
        this.rX = lX + length;
        this.bY = tY + height;
        this.length = length;
        this.height = height;
        this.r = new Rectangle((int)lX,(int)tY,(int)length,(int)height);
    }

    public void setHitBox(double lX, double tY) {
        this.lX = lX;
        this.tY = tY;
        this.bY = tY + height;
        this.rX = lX + length;
        this.r = new Rectangle((int)lX,(int)tY,(int)length,(int)height);
    }
    
    public boolean collidesWith(Hitbox h) {
    	/*
        double hLX = h.getlX();
        double hRX = h.getrX();
        double hTY = h.gettY();
        double hBY = h.getbY();

        // If two points are on the same side (RETURN FALSE)
        // This part checks the left and right sides
        if (sameSign(crossProduct(lX, bY, lX, tY, hLX, hTY), crossProduct(lX, bY, lX, tY, hRX, hTY)) || sameSign(crossProduct(rX, bY, rX, tY, hLX, hTY), crossProduct(rX, bY, rX, tY, hRX, hTY))) {
            return false;
        }
        System.out.println("jsehduijsfhdruisfhdsd");
        // This checks up and down
        return !(sameSign(crossProduct(lX, tY, rX, tY, hLX, hTY), crossProduct(lX, tY, rX, tY, hLX, hBY)) || sameSign(crossProduct(lX, bY, rX, bY, hLX, hTY), crossProduct(lX, bY, rX, bY, hLX, hBY)));
    	*/
    	return (r.intersects(h.getRectangle()));
    }

    public Rectangle getRectangle(){
    	return r;
    }
    private double crossProduct(double lineX1, double lineY1, double lineX2, double lineY2, double pointX, double pointY) {
        lineX2 -= lineX1;
        pointX -= lineX1;
        lineY2 -= lineY1;
        pointY -= lineY1;
        return (lineX2 * pointY - lineY2 * pointX);
    }

    private boolean sameSign(double a, double b) {
        return !(a * b <= 0);
    }

    public double getlX() {
        return lX;
    }

    public double gettY() {
        return tY;
    }
    

    public void setXY (double lX, double tY){
        this.lX = lX;
        this.rX = lX + length;
    	this.tY = tY;
        this.bY = tY + height;
    	this.r = new Rectangle((int)lX, (int)tY,(int)length, (int)height);
    }

    public double getrX() {
        return rX;
    }

    public double getbY() {
        return bY;
    }

    public double getLength() {
        return length;
    }

    public double getHeight() {
        return height;
    }
}