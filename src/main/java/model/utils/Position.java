package model.utils;

import java.util.Objects;

public class Position {
    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isOutOfBounds(int width, int height){
        if(0 > x || x >= width) return true;
        if(0 > y || y >= height) return true;
        return false;

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
    public Position getDown(){
        return new Position(x, y  + 1);
    }

    public Position getNext(DirectionToken direction){
        switch(direction){
            case LEFT : {
                return getLeft();
            }
            case DOWN : {
                return getDown();
            }
            case UP : {
                return getUp();
            }
            case RIGHT : {
                return getRight();
            }
        }
        return this;
    }
    public Position getUp(){
        return new Position(x, y - 1);
    }

    public Position getLeft(){
        return new Position(x - 1, y);
    }

    public Position getRight(){
        return new Position(x + 1, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
