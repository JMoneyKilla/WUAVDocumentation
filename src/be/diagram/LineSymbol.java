package be.diagram;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineSymbol extends Line {
    private boolean isSelected;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private double initialMouseX;
    private double initialMouseY;

    public LineSymbol(double startX, double startY, double endX, double endY) {
        super(startX, startY, endX, endY);
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        isSelected = false;
        setStroke(Color.BLACK);
        setStrokeWidth(3.0);

        setOnMouseClicked(event -> {
            setSelected(!isSelected());
            toBack();
            event.consume();
        });

        setOnMousePressed(event -> {
            initialMouseX = event.getX();
            initialMouseY = event.getY();
            toBack();
            event.consume();
        });

        setOnMouseDragged(event -> {
            double currentMouseX = event.getX();
            double currentMouseY = event.getY();

            if (isSelected()) {
                double deltaX = currentMouseX - initialMouseX;
                double deltaY = currentMouseY - initialMouseY;

                if (initialMouseX < getStartX() + 5) {
                    updateStartPoint(getStartX() + deltaX, getStartY() + deltaY);
                } else if (initialMouseX > getEndX() - 5) {
                    updateEndPoint(getEndX() + deltaX, getEndY() + deltaY);
                } else {
                    updateLinePosition(deltaX, deltaY);
                }
            }

            initialMouseX = currentMouseX;
            initialMouseY = currentMouseY;
            toBack();
            event.consume();
        });
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        if (isSelected) {
            setStroke(Color.RED);
        } else {
            setStroke(Color.BLACK);
        }
    }

    public void updateStartPoint(double newX, double newY) {
        double deltaX = newX - startX;
        double deltaY = newY - startY;
        startX = newX;
        startY = newY;
        setStartX(getStartX() + deltaX);
        setStartY(getStartY() + deltaY);
    }

    public void updateEndPoint(double newX, double newY) {
        double deltaX = newX - endX;
        double deltaY = newY - endY;
        endX = newX;
        endY = newY;
        setEndX(getEndX() + deltaX);
        setEndY(getEndY() + deltaY);
    }

    public void updateLinePosition(double deltaX, double deltaY) {
        startX += deltaX;
        startY += deltaY;
        endX += deltaX;
        endY += deltaY;
        setStartX(getStartX() + deltaX);
        setStartY(getStartY() + deltaY);
        setEndX(getEndX() + deltaX);
        setEndY(getEndY() + deltaY);
    }
}
