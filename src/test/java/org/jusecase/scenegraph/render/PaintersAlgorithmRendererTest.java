package org.jusecase.scenegraph.render;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jusecase.scenegraph.Node;
import org.jusecase.scenegraph.Node2d;
import org.jusecase.scenegraph.Quad;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PaintersAlgorithmRendererTest {

    Node2d root = new Quad();
    Node2d node1 = new Quad();
    Node2d node2 = new Quad();
    Node2d node2_1 = new Quad();
    Node2d node2_2 = new Quad();
    Node2d node2_2_1 = new Quad();

    List<Node> renderedNodes = new ArrayList<>();

    Renderer renderer = new PaintersAlgorithmRenderer(new Renderer() {
        @Override
        public void render(Node node) {
            renderedNodes.add(node);
        }

        @Override
        public void dispose() {
        }
    });

    @BeforeEach
    public void setUp() {
        root.add(node1.setX(0).setSize(10, 10));
        root.add(node2.setX(1).setSize(10, 10));
        node2.add(node2_1.setX(2).setSize(10, 10));
        node2.add(node2_2.setX(3).setSize(10, 10));
        node2_2.add(node2_2_1.setX(4).setSize(10, 10));
    }

    @Test
    public void render() {
        whenSceneIsRendered();
        thenRenderedNodesAre(root, node1, node2, node2_1, node2_2, node2_2_1);
    }

    public void whenSceneIsRendered() {
        renderer.render(root);
    }

    @Test
    public void notRenderableNode() {
        root.removeAll();
        root.add(new Node2d()); // Node2d itself is not renderable

        whenSceneIsRendered();

        thenRenderedNodesAre(root);
    }

    @Test
    public void invisibleNode() {
        node2.setVisible(false);

        whenSceneIsRendered();

        thenRenderedNodesAre(root, node1);
    }

    private void thenRenderedNodesAre(Node... values) {
        assertThat(renderedNodes).containsExactly(values);
    }
}