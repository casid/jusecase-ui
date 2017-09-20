package org.jusecase.scenegraph;

import org.jusecase.scenegraph.texture.Texture;
import org.jusecase.scenegraph.texture.TextureAtlas;

/**
 * ----------------
 * - lt - ct - rt -
 * -              -
 * - lc - cc - rc -
 * -              -
 * - lb - cb - rb -
 * ----------------
 */
public class Image9Slice extends Node2d {
    private final Image lt;
    private final Image lc;
    private final Image lb;

    private final Image ct;
    private final Image cc;
    private final Image cb;

    private final Image rt;
    private final Image rc;
    private final Image rb;

    public Image9Slice(TextureAtlas atlas, String image) {
        this(atlas, image, "-");
    }

    public Image9Slice(TextureAtlas atlas, String image, String suffix) {
        this(
                atlas.get(image + suffix + "lt"),
                atlas.get(image + suffix + "lc"),
                atlas.get(image + suffix + "lb"),
                atlas.get(image + suffix + "ct"),
                atlas.get(image + suffix + "cc"),
                atlas.get(image + suffix + "cb"),
                atlas.get(image + suffix + "rt"),
                atlas.get(image + suffix + "rc"),
                atlas.get(image + suffix + "rb")
        );
    }

    public Image9Slice(Texture lt, Texture lc, Texture lb, Texture ct, Texture cc, Texture cb, Texture rt, Texture rc, Texture rb) {
        add(this.lt = new Image(lt));
        add(this.lc = new Image(lc));
        add(this.lb = new Image(lb));
        add(this.ct = new Image(ct));
        add(this.cc = new Image(cc));
        add(this.cb = new Image(cb));
        add(this.rt = new Image(rt));
        add(this.rc = new Image(rc));
        add(this.rb = new Image(rb));

        setSize(lc.getWidth() + cc.getWidth() + rc.getWidth(), ct.getHeight() + cc.getHeight() + cb.getHeight());
    }

    @Override
    public Node2d setWidth(double width) {
        super.setWidth(width);

        double centerWidth = getWidth() - lc.getWidth() - rc.getWidth();

        ct.setX(lc.getWidth()).setWidth(centerWidth);
        cc.setX(lc.getWidth()).setWidth(centerWidth);
        cb.setX(lc.getWidth()).setWidth(centerWidth);

        rt.setX(lc.getWidth() + centerWidth);
        rc.setX(lc.getWidth() + centerWidth);
        rb.setX(lc.getWidth() + centerWidth);

        return this;
    }

    @Override
    public Node2d setHeight(double height) {
        super.setHeight(height);

        double centerHeight = getHeight() - ct.getHeight() - cb.getHeight();

        lc.setY(lt.getHeight()).setHeight(centerHeight);
        cc.setY(lt.getHeight()).setHeight(centerHeight);
        rc.setY(lt.getHeight()).setHeight(centerHeight);

        lb.setY(lt.getHeight() + centerHeight);
        cb.setY(lt.getHeight() + centerHeight);
        rb.setY(lt.getHeight() + centerHeight);

        return this;
    }
}
