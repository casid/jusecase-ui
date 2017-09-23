package org.jusecase.scenegraph;

import org.jusecase.scenegraph.math.Bounds;
import org.jusecase.scenegraph.math.Matrix3x2;
import org.jusecase.scenegraph.math.Vector2;

public class Node2d extends Node {
    private float x;
    private float y;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float rotation;

    private float width;
    private float height;

    private float pivotX;
    private float pivotY;

    private Matrix3x2 localMatrix = new Matrix3x2();
    private Matrix3x2 globalMatrix = new Matrix3x2();
    private Matrix3x2 globalMatrixInverse = new Matrix3x2();

    private boolean dirtyLocalMatrix;
    private boolean dirtyGlobalMatrix;
    private boolean dirtyGlobalMatrixInverse;

    private static final Vector2 HIT_TEST = new Vector2();

    public boolean hitTest(float x, float y) {
        Vector2 p = globalToLocal(x, y, HIT_TEST);
        return p.x >= 0 && p.x <= this.width && p.y >= 0.0 && p.y <= this.height;
    }

    public Vector2 globalToLocal(float x, float y) {
        return globalToLocal(x, y, new Vector2());
    }

    public Vector2 globalToLocal(float x, float y, Vector2 result) {
        return getGlobalMatrixInverse().transformPoint(x, y, result);
    }

    @Override
    public void add(Node node, int index) {
        super.add(node, index);

        if (node instanceof Node2d) {
            ((Node2d)node).resetMatrices();
        }
    }

    public Node2d setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        resetMatrices();
        return this;
    }

    public float getX() {
        return x;
    }

    public Node2d setX(float x) {
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

    public float getY() {
        return y;
    }

    public Node2d setY(float y) {
        this.y = y;
        resetMatrices();
        return this;
    }

    public float getWidth() {
        return width;
    }

    public Node2d setWidth(float width) {
        this.width = width;
        return this;
    }

    public float getHeight() {
        return height;
    }

    public Node2d setHeight(float height) {
        this.height = height;
        return this;
    }

    public Node2d setSize(float width, float height) {
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
        float pivotX = this.pivotX * width;
        float pivotY = this.pivotY * height;
        if (rotation == 0) {
            localMatrix.a = scaleX;
            localMatrix.b = 0.0f;
            localMatrix.c = 0.0f;
            localMatrix.d = scaleY;
            localMatrix.tx = x - pivotX * scaleX;
            localMatrix.ty = y - pivotY * scaleY;
        } else {
            float radians = (float)Math.toRadians(rotation);
            float cos = (float)Math.cos(radians);
            float sin = (float)Math.sin(radians);
            localMatrix.a = cos * scaleX;
            localMatrix.b = sin * scaleX;
            localMatrix.c = -sin * scaleY;
            localMatrix.d = cos * scaleY;
            localMatrix.tx = x - pivotX * localMatrix.a + pivotY * localMatrix.c;
            localMatrix.ty = y + pivotX * localMatrix.b - pivotY * localMatrix.d;
        }
    }

    public Node2d setScale(float scale) {
        return setScale(scale, scale);
    }

    public Node2d setScale(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        resetMatrices();
        return this;
    }

    public Node2d setScaleX(float scaleX) {
        this.scaleX = scaleX;
        resetMatrices();
        return this;
    }

    public float getScaleX() {
        return scaleX;
    }

    public Node2d setScaleY(float scaleY) {
        this.scaleY = scaleY;
        resetMatrices();
        return this;
    }

    public float getScaleY() {
        return scaleY;
    }

    public Node2d setRotation(float rotation) {
        this.rotation = rotation;
        resetMatrices();
        return this;
    }

    public float getRotation() {
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

    public Node2d setPivot(float x, float y) {
        pivotX = x;
        pivotY = y;
        resetMatrices();
        return this;
    }
}
