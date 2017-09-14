package org.jusecase.scenegraph.color;

public class HexParser {

    public static int parse(String hex) {
        int offset = hex.charAt(0) == '#' ? 1 : 0;
        int length = hex.length() - offset;

        if (length < 3) {
            throw parseError(hex);
        }

        if (length == 3) {
            return parseShort(hex, offset);
        }

        return parse(hex, offset, length);
    }

    private static int parseShort(String hex, int offset) {
        return parse("" +
                hex.charAt(offset) + hex.charAt(offset) +
                hex.charAt(offset + 1) + hex.charAt(offset + 1) +
                hex.charAt(offset + 2) + hex.charAt(offset + 2));
    }

    private static int parse(String hex, int offset, int length) {
        int color, alpha;
        if (length == 6) {
            color = parseInt(hex, hex.substring(offset));
            alpha = 0xff;
        } else if (length == 8) {
            color = parseInt(hex, hex.substring(offset, offset + 6));
            alpha = parseInt(hex, hex.substring(offset + 6));
        } else {
            throw parseError(hex);
        }

        return color | (alpha << 24);
    }

    private static int parseInt(String originalHex, String hex) {
        try {
            return Integer.parseInt(hex, 16);
        } catch (NumberFormatException e) {
            throw parseError(originalHex);
        }
    }

    private static NumberFormatException parseError(String hex) {
        return new NumberFormatException("Failed to parse color from '" + hex + "'");
    }
}
