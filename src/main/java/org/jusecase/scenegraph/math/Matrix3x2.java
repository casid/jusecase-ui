package org.jusecase.scenegraph.math;


public final class Matrix3x2 {

    public static final Matrix3x2 IDENTITY = new Matrix3x2(1.0, 0.0, 0.0, 1.0, 0.0, 0.0);

    public static Matrix3x2 multiply(Matrix3x2 a, Matrix3x2 b) {
        return new Matrix3x2(
                a.a * b.a + a.b * b.c,
                a.a * b.b + a.b * b.d,
                a.c * b.a + a.d * b.c,
                a.c * b.b + a.d * b.d,
                a.a * b.tx + a.b * b.ty + a.tx,
                a.c * b.tx + a.d * b.ty + a.ty
        );
    }

    public static Matrix3x2 orthoProjection(double width, double height) {
        return new Matrix3x2(2 / width, 0, 0, -2 / height, -1, 1);
    }

    public final double a;
    public final double b;
    public final double c;
    public final double d;
    public final double tx;
    public final double ty;


    public Matrix3x2(double a, double b, double c, double d, double tx, double ty) {
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
        double det = determinant();
        if( det == 0.0f ) {
            return this;
        }

        det = 1.0 / det;

        double a = det * this.d;
        double b = -det * this.b;
        double c = -det * this.c;
        double d = det * this.a;

        return new Matrix3x2(a, b, c, d, -(tx * a + ty * b), -(tx * c + ty * d));
    }

    public double determinant() {
        return a * d - c * b;
    }

    @Override
    public String toString() {
        return "Matrix3x2{" + "a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", tx=" + tx + ", ty=" + ty + '}';
    }

    public Vector2 transformPoint(double x, double y) {
        return new Vector2(a * x + b * y + tx, c * x + d * y + ty);
    }

    public void transformPoint(float[] p) {
        float x = (float) (a * p[0] + b * p[1] + tx);
        float y = (float) (c * p[0] + d * p[1] + ty);
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
}
