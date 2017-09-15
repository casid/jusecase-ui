package org.jusecase.scenegraph.color;

public final class Color {
    public static final Color BLACK = new Color(0.0);
    public static final Color WHITE = new Color(1.0);

    private static final double scale = 1.0 / 255.0;

    private final int bgra;

    public Color() {
        this.bgra = 0xff000000;
    }

    public Color(String hex) {
        bgra = HexParser.parse(hex);
    }

    public Color(String hex, double alpha) {
        bgra = (HexParser.parse(hex) & 0x00ffffff) | ((int)(255.0 * alpha) << 24);
    }

    public Color(double luminance) {
        this(luminance, 1.0);
    }

    public Color(double luminance, double alpha) {
        this(luminance, luminance, luminance, alpha);
    }

    public Color(double red, double green, double blue) {
        this(red, green, blue, 1.0);
    }

    public Color(double red, double green, double blue, double alpha) {
        int r = (int)(255.0 * red);
        int g = (int)(255.0 * green);
        int b = (int)(255.0 * blue);
        int a = (int)(255.0 * alpha);
        bgra = b | (g << 8) | (r << 16) | (a << 24);
    }

    public double getBlue() {
        return scale * (bgra & 0xff);
    }

    public double getGreen() {
        return scale * ((bgra >> 8) & 0xff);
    }

    public double getRed() {
        return scale * ((bgra >> 16) & 0xff);
    }

    public double getAlpha() {
        return scale * ((bgra >> 24) & 0xff);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Color color = (Color) o;

        return bgra == color.bgra;
    }

    @Override
    public int hashCode() {
        return bgra;
    }

    @Override
    public String toString() {
        return "Color{" + "r=" + getRed() + "g=" + getGreen() + "b=" + getBlue() + "a=" + getAlpha() + "}";
    }
}
