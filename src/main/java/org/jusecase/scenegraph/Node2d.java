package org.jusecase.scenegraph;

import org.jusecase.scenegraph.math.Bounds;
import org.jusecase.scenegraph.math.Matrix3x2;
import org.jusecase.scenegraph.math.Vector2;

public class Node2d extends Node {
    private double x;
    private double y;
    private double scaleX = 1.0;
    private double scaleY = 1.0;
    private double rotation;

    private double width;
    private double height;

    private double pivotX;
    private double pivotY;

    private Matrix3x2 localMatrix = Matrix3x2.IDENTITY;
    private Matrix3x2 globalMatrix = Matrix3x2.IDENTITY;
    private Matrix3x2 globalMatrixInverse = Matrix3x2.IDENTITY;


    public boolean hitTest(double x, double y) {
        Vector2 p = globalToLocal(x, y);
        return p.x >= 0 && p.x <= this.width && p.y >= 0.0 && p.y <= this.height;
    }

    public Vector2 globalToLocal(double x, double y) {
        return getGlobalMatrixInverse().transformPoint(x, y);
    }

    @Override
    public void add(Node node, int index) {
        super.add(node, index);

        if (node instanceof Node2d) {
            ((Node2d)node).resetMatrices();
        }
    }

    public Node2d setPosition(double x, double y) {
        this.x = x;
        this.y = y;
        resetMatrices();
        return this;
    }

    public double getX() {
        return x;
    }

    public Node2d setX(double x) {
        this.x = x;
        resetMatrices();
        return this;
    }

    private void resetMatrices() {
        if (localMatrix != null || globalMatrix != null) {
            localMatrix = null;
            visitAll(Node2d.class, Node2d::resetGlobalMatrix);
        }
    }

    private void resetGlobalMatrix() {
        this.globalMatrix = null;
        this.globalMatrixInverse = null;
    }

    public double getY() {
        return y;
    }

    public Node2d setY(double y) {
        this.y = y;
        resetMatrices();
        return this;
    }

    public double getWidth() {
        return width;
    }

    public Node2d setWidth(double width) {
        this.width = width;
        return this;
    }

    public double getHeight() {
        return height;
    }

    public Node2d setHeight(double height) {
        this.height = height;
        return this;
    }

    public Node2d setSize(double width, double height) {
        return this.setWidth(width).setHeight(height);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + '}';
    }

    public Matrix3x2 getLocalMatrix() {
        if (localMatrix == null) {
            double pivotX = this.pivotX * width;
            double pivotY = this.pivotY * height;
            if (rotation == 0) {
                localMatrix = new Matrix3x2(scaleX, 0.0, 0.0, scaleY, x - pivotX * scaleX, y - pivotY * scaleY);
            } else {
                double radians = Math.toRadians(rotation);
                double cosinus = Math.cos(radians);
                double sinus = Math.sin(radians);
                double a = cosinus * scaleX;
                double b = sinus * scaleX;
                double c = -sinus * scaleY;
                double d = cosinus * scaleY;
                double tx = x - pivotX * a + pivotY * c;
                double ty = y + pivotX * b - pivotY * d;
                localMatrix = new Matrix3x2(a, b, c, d, tx, ty);
            }
        }
        return localMatrix;
    }

    public Node2d setScale(double scale) {
        return setScale(scale, scale);
    }

    public Node2d setScale(double scaleX, double scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        resetMatrices();
        return this;
    }

    public Node2d setScaleX(double scaleX) {
        this.scaleX = scaleX;
        resetMatrices();
        return this;
    }

    public double getScaleX() {
        return scaleX;
    }

    public Node2d setScaleY(double scaleY) {
        this.scaleY = scaleY;
        resetMatrices();
        return this;
    }

    public double getScaleY() {
        return scaleY;
    }

    public Node2d setRotation(double rotation) {
        this.rotation = rotation;
        resetMatrices();
        return this;
    }

    public double getRotation() {
        return rotation;
    }

    public Matrix3x2 getGlobalMatrix() {
        if (globalMatrix == null) {
            Node2d parent = (Node2d) getParent();
            if (parent != null) {
                globalMatrix = parent.getGlobalMatrix().multiply(getLocalMatrix());
            } else {
                globalMatrix = getLocalMatrix();
            }
        }
        return globalMatrix;
    }

    public Matrix3x2 getGlobalMatrixInverse() {
        if (globalMatrixInverse == null) {
            globalMatrixInverse = getGlobalMatrix().inverse();
        }
        return globalMatrixInverse;
    }

    @Override
    public Node2d clone() {
        Node2d clone = (Node2d) super.clone();
        clone.localMatrix = null;
        clone.globalMatrix = null;
        clone.globalMatrixInverse = null;
        return clone;
    }

    public Bounds getGlobalBounds() {
        Matrix3x2 matrix = getGlobalMatrix();
        Vector2 a = matrix.transformPoint(x, y);
        Vector2 b = matrix.transformPoint(x + width, y + height);

        return new Bounds(a.x, b.x, a.y, b.y);
    }

    public Node2d setPivot(double x, double y) {
        pivotX = x;
        pivotY = y;
        resetMatrices();
        return this;
    }
}
