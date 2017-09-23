package org.jusecase.scenegraph.math;


public final class Matrix3x2 implements Cloneable {

    public static Matrix3x2 multiply(Matrix3x2 a, Matrix3x2 b) {
        return multiply(a, b, new Matrix3x2());
    }

    public static Matrix3x2 multiply(Matrix3x2 a, Matrix3x2 b, Matrix3x2 result) {
        result.a = a.a * b.a + a.b * b.c;
        result.b = a.a * b.b + a.b * b.d;
        result.c = a.c * b.a + a.d * b.c;
        result.d = a.c * b.b + a.d * b.d;
        result.tx = a.a * b.tx + a.b * b.ty + a.tx;
        result.ty = a.c * b.tx + a.d * b.ty + a.ty;
        return result;
    }

    public static Matrix3x2 orthoProjection(float width, float height) {
        return orthoProjection(width, height, new Matrix3x2());
    }

    public static Matrix3x2 orthoProjection(float width, float height, Matrix3x2 result) {
        result.a = 2 / width;
        result.b = 0;
        result.c = 0;
        result.d = -2 / height;
        result.tx = -1;
        result.ty = 1;

        return result;
    }

    public float a;
    public float b;
    public float c;
    public float d;
    public float tx;
    public float ty;

    public Matrix3x2() {
        this(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
    }

    public Matrix3x2(float a, float b, float c, float d, float tx, float ty) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.tx = tx;
        this.ty = ty;
    }

    public Matrix3x2 multiply(Matrix3x2 matrix) {
        return Matrix3x2.multiply(this, matrix);
    }

    public Matrix3x2 inverse() {
        return inverse(new Matrix3x2());
    }

    public Matrix3x2 inverse(Matrix3x2 result) {
        float det = determinant();
        if( det == 0.0f ) {
            result.set(this);
        } else {
            det = 1.0f / det;

            result.a = det * d;
            result.b = -det * b;
            result.c = -det * c;
            result.d = det * a;
            result.tx = -(tx * result.a + ty * result.b);
            result.ty = -(tx * result.c + ty * result.d);
        }
        return result;
    }

    public float determinant() {
        return a * d - c * b;
    }

    @Override
    public String toString() {
        return "Matrix3x2{" + "a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", tx=" + tx + ", ty=" + ty + '}';
    }

    public Vector2 transformPoint(float x, float y) {
        return transformPoint(x, y, new Vector2());
    }

    public Vector2 transformPoint(float x, float y, Vector2 result) {
        result.x = a * x + b * y + tx;
        result.y = c * x + d * y + ty;
        return result;
    }

    public void transformPoint(float[] p) {
        float x = a * p[0] + b * p[1] + tx;
        float y = c * p[0] + d * p[1] + ty;
        p[0] = x;
        p[1] = y;
    }

    public boolean isAxisAligned() {
        return b == 0.0 && c == 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix3x2 matrix3x2 = (Matrix3x2) o;

        if (Double.compare(matrix3x2.a, a) != 0) return false;
        if (Double.compare(matrix3x2.b, b) != 0) return false;
        if (Double.compare(matrix3x2.c, c) != 0) return false;
        if (Double.compare(matrix3x2.d, d) != 0) return false;
        if (Double.compare(matrix3x2.tx, tx) != 0) return false;
        return Double.compare(matrix3x2.ty, ty) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(a);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(c);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(d);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(tx);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ty);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public void set(Matrix3x2 matrix) {
        a = matrix.a;
        b = matrix.b;
        c = matrix.c;
        d = matrix.d;
        tx = matrix.tx;
        ty = matrix.ty;
    }

    @Override
    public Matrix3x2 clone() {
        try {
            return (Matrix3x2)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
