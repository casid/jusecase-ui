package org.jusecase.scenegraph.color;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.data.Offset;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ColorTest {
    Color color;

    @Test
    public void defaultColor() {
        color = new Color();
        thenColorIs(0, 0, 0, 1);
    }

    @Test
    public void hex_black() {
        color = new Color("#000000");
        thenColorIs(0, 0, 0, 1);
    }

    @Test
    public void hex_white() {
        color = new Color("#ffffff");
        thenColorIs(1, 1, 1, 1);
    }

    @Test
    public void hex_grey() {
        color = new Color("#808080");
        thenColorIs(0.5, 0.5, 0.5, 1);
    }

    @Test
    public void hex_red() {
        color = new Color("#ff0000");
        thenColorIs(1.0, 0.0, 0.0, 1);
    }

    @Test
    public void hex_green() {
        color = new Color("#00ff00");
        thenColorIs(0.0, 1.0, 0.0, 1);
    }

    @Test
    public void hex_blue() {
        color = new Color("#0000ff");
        thenColorIs(0.0, 0.0, 1.0, 1);
    }

    @Test
    public void hex_blue_withoutHash() {
        color = new Color("0000ff");
        thenColorIs(0.0, 0.0, 1.0, 1);
    }

    @Test
    public void hex_red_alpha() {
        color = new Color("#ff000080");
        thenColorIs(1.0, 0.0, 0.0, 0.5);
    }

    @Test
    public void hex_white_short() {
        color = new Color("#fff");
        thenColorIs(1, 1, 1, 1);
    }

    @Test
    public void hex_white_alpha() {
        color = new Color("#fff", 0.1);
        thenColorIs(1, 1, 1, 0.1);
    }

    @Test
    public void hex_blue_short() {
        color = new Color("#00f");
        thenColorIs(0, 0, 1, 1);
    }

    @Test
    public void hex_blue_short_withoutHash() {
        color = new Color("00f");
        thenColorIs(0, 0, 1, 1);
    }

    @Test
    public void hex_alpha_blue() {
        color = new Color("#00f", 0.5);
        thenColorIs(0, 0, 1, 0.5);
    }

    @Test
    public void doubles_luminance() {
        color = new Color(0.5);
        thenColorIs(0.5, 0.5, 0.5, 1);
    }

    @Test
    public void doubles_luminance_alpha() {
        color = new Color(0.5, 0.3);
        thenColorIs(0.5, 0.5, 0.5, 0.3);
    }

    @Test
    public void doubles_rgb() {
        color = new Color(0.1, 0.2, 0.3);
        thenColorIs(0.1, 0.2, 0.3, 1);
    }

    @Test
    public void doubles_rgba() {
        color = new Color(0.1, 0.2, 0.3, 0.4);
        thenColorIs(0.1, 0.2, 0.3, 0.4);
    }

    @Test
    public void hex_tooShort() {
        Throwable throwable = catchThrowable(() -> color = new Color("0f"));
        assertThat(throwable).isInstanceOf(NumberFormatException.class).hasMessage("Failed to parse color from '0f'");
    }

    @Test
    public void hex_tooLong() {
        Throwable throwable = catchThrowable(() -> color = new Color("00000000000f"));
        assertThat(throwable).isInstanceOf(NumberFormatException.class).hasMessage("Failed to parse color from '00000000000f'");
    }

    @Test
    public void hex_invalidFormat() {
        Throwable throwable = catchThrowable(() -> color = new Color("00bart"));
        assertThat(throwable).isInstanceOf(NumberFormatException.class).hasMessage("Failed to parse color from '00bart'");
    }

    private void thenColorIs(double r, double g, double b, double a) {
        SoftAssertions s = new SoftAssertions();
        s.assertThat(color.getRed()).describedAs("red").isCloseTo(r, Offset.offset(0.01));
        s.assertThat(color.getGreen()).describedAs("green").isCloseTo(g, Offset.offset(0.01));
        s.assertThat(color.getBlue()).describedAs("blue").isCloseTo(b, Offset.offset(0.01));
        s.assertThat(color.getAlpha()).describedAs("alpha").isCloseTo(a, Offset.offset(0.01));
        s.assertAll();
    }
}