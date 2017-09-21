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

    private Matrix3x2 localMatrix = new Matrix3x2();
    private Matrix3x2 globalMatrix = new Matrix3x2();
    private Matrix3x2 globalMatrixInverse = new Matrix3x2();

    private boolean dirtyLocalMatrix;
    private boolean dirtyGlobalMatrix;
    private boolean dirtyGlobalMatrixInverse;

    private static final Vector2 HIT_TEST = new Vector2();

    public boolean hitTest(double x, double y) {
        Vector2 p = globalToLocal(x, y, HIT_TEST);
        return p.x >= 0 && p.x <= this.width && p.y >= 0.0 && p.y <= this.height;
    }

    public Vector2 globalToLocal(double x, double y) {
        return globalToLocal(x, y, new Vector2());
    }

    public Vector2 globalToLocal(double x, double y, Vector2 result) {
        return getGlobalMatrixInverse().transformPoint(x, y, result);
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
        dirtyLocalMatrix = true;

        if (!dirtyGlobalMatrix) {
            visitAll(Node2d.class, Node2d::resetGlobalMatrix);
        }
    }

    private void resetGlobalMatrix() {
        dirtyGlobalMatrix = true;
        dirtyGlobalMatrixInverse = true;
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
        if (dirtyLocalMatrix) {
            updateLocalMatrix();
            dirtyLocalMatrix = false;
        }
        return localMatrix;
    }

    private void updateLocalMatrix() {
        double pivotX = this.pivotX * width;
        double pivotY = this.pivotY * height;
        if (rotation == 0) {
            localMatrix.a = scaleX;
            localMatrix.b = 0.0;
            localMatrix.c = 0.0;
            localMatrix.d = scaleY;
            localMatrix.tx = x - pivotX * scaleX;
            localMatrix.ty = y - pivotY * scaleY;
        } else {
            double radians = Math.toRadians(rotation);
            double cosinus = Math.cos(radians);
            double sinus = Math.sin(radians);
            localMatrix.a = cosinus * scaleX;
            localMatrix.b = sinus * scaleX;
            localMatrix.c = -sinus * scaleY;
            localMatrix.d = cosinus * scaleY;
            localMatrix.tx = x - pivotX * localMatrix.a + pivotY * localMatrix.c;
            localMatrix.ty = y + pivotX * localMatrix.b - pivotY * localMatrix.d;
        }
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
        if (dirtyGlobalMatrix) {
            updateGlobalMatrix();
            dirtyGlobalMatrix = false;
        }
        return globalMatrix;
    }

    private void updateGlobalMatrix() {
        Node2d parent = (Node2d) getParent();
        if (parent != null) {
            Matrix3x2.multiply(parent.getGlobalMatrix(), getLocalMatrix(), globalMatrix);
        } else {
            globalMatrix.set(getLocalMatrix());
        }
    }

    public Matrix3x2 getGlobalMatrixInverse() {
        if (dirtyGlobalMatrixInverse) {
            getGlobalMatrix().inverse(globalMatrixInverse);
            dirtyGlobalMatrixInverse = false;
        }
        return globalMatrixInverse;
    }

    @Override
    public Node2d clone() {
        Node2d clone = (Node2d) super.clone();
        clone.localMatrix = localMatrix.clone();
        clone.globalMatrix = globalMatrix.clone();
        clone.globalMatrixInverse = globalMatrixInverse.clone();
        return clone;
    }

    public Node2d setPivot(double x, double y) {
        pivotX = x;
        pivotY = y;
        resetMatrices();
        return this;
    }
}
