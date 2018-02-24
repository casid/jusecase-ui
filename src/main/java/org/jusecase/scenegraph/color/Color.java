package org.jusecase.scenegraph.color;

public final class Color implements Cloneable {
    private static final float scale = 1.0f / 255.0f;

    public float r;
    public float g;
    public float b;
    public float a;

    public Color() {
        this(0, 1);
    }

    public Color(String hex) {
        this(HexParser.parse(hex));
    }

    public Color(String hex, float alpha) {
        this((HexParser.parse(hex) & 0x00ffffff) | ((int) (255.0 * alpha) << 24));
    }

    public Color(float luminance) {
        this(luminance, 1.0f);
    }

    public Color(float luminance, float alpha) {
        this(luminance, luminance, luminance, alpha);
    }

    public Color(float red, float green, float blue) {
        this(red, green, blue, 1.0f);
    }

    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    private Color(int bgra) {
        b = scale * (bgra & 0xff);
        g = scale * ((bgra >> 8) & 0xff);
        r = scale * ((bgra >> 16) & 0xff);
        a = scale * ((bgra >> 24) & 0xff);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Color color = (Color) o;
        return Float.compare(color.r, r) == 0 &&
                Float.compare(color.g, g) == 0 &&
                Float.compare(color.b, b) == 0 &&
                Float.compare(color.a, a) == 0;
    }

    @Override
    public int hashCode() {
        int result = (r != +0.0f ? Float.floatToIntBits(r) : 0);
        result = 31 * result + (g != +0.0f ? Float.floatToIntBits(g) : 0);
        result = 31 * result + (b != +0.0f ? Float.floatToIntBits(b) : 0);
        result = 31 * result + (a != +0.0f ? Float.floatToIntBits(a) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Color{" + "r=" + r + "g=" + g + "b=" + b + "a=" + a + "}";
    }

    public void set(Color color) {
        r = color.r;
        g = color.g;
        b = color.b;
        a = color.a;
    }

    public void setHsb(float hue, float saturation, float brightness) {
        if (saturation == 0) {
            r = g = b = brightness;
        } else {
            float h = (hue - (float)Math.floor(hue)) * 6.0f;
            float f = h - (float)java.lang.Math.floor(h);
            float p = brightness * (1.0f - saturation);
            float q = brightness * (1.0f - saturation * f);
            float t = brightness * (1.0f - (saturation * (1.0f - f)));
            switch ((int) h) {
                case 0:
                    r = brightness;
                    g = t;
                    b = p;
                    break;
                case 1:
                    r = q;
                    g = brightness;
                    b = p;
                    break;
                case 2:
                    r = p;
                    g = brightness;
                    b = t;
                    break;
                case 3:
                    r = p;
                    g = q;
                    b = brightness;
                    break;
                case 4:
                    r = t;
                    g = p;
                    b = brightness;
                    break;
                case 5:
                    r = brightness;
                    g = p;
                    b = q;
                    break;
            }
        }

    }

    public Color random() {
        r = (float) Math.random();
        g = (float) Math.random();
        b = (float) Math.random();
        return this;
    }

    public Color randomHue() {
        setHsb((float)Math.random(), 1, 1);
        return this;
    }

    @Override
    public Color clone() {
        try {
            return (Color) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
