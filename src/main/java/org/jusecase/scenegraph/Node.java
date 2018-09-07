package org.jusecase.scenegraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Node {

    private Node parent;
    private List<Node> children;
    private boolean visible = true;

    public final void add(Node node) {
        add(node, -1);
    }

    public void add(Node node, int index) {
        if (children == null) {
            children = new ArrayList<>();
        }

        node.parent = this;
        if (index == -1) {
            children.add(node);
        } else {
            children.add(index, node);
        }
    }

    public final void addFirst(Node node) {
        add(node, 0);
    }

    public final void addLast(Node node) {
        add(node);
    }

    public final void addAll(Node... nodes) {
        for (Node node : nodes) {
            add(node);
        }
    }

    public void remove(Node node) {
        if (children == null) {
            return;
        }

        node.parent = null;
        children.remove(node);

        if (children.isEmpty()) {
            children = null;
        }
    }

    public final void removeAll() {
        if (children == null) {
            return;
        }

        for (Node child : children) {
            child.parent = null;
        }

        children = null;
    }

    public Node getChild(int index) {
        if (children == null) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: 0");
        }
        return children.get(index);
    }

    public int getChildIndex(Node child) {
        if (children == null) {
            return -1;
        }
        return children.indexOf(child);
    }

    public void bringToFront(Node child) {
        setChildIndex(child, getChildCount() - 1);
    }

    public void sendToBack(Node child) {
        setChildIndex(child, 0);
    }

    public void setChildIndex(Node child, int index) {
        if (children == null) {
            return;
        }

        if (children.remove(child)) {
            children.add(index, child);
        }
    }

    public Node getSibling(Node child, int indexOffset) {
        int childIndex = getChildIndex(child);
        if (childIndex < 0) {
            return null;
        }

        childIndex += indexOffset;
        if (childIndex < 0 || childIndex >= children.size()) {
            return null;
        }

        return children.get(childIndex);
    }

    public <T extends Node> T getSibling(T child, int indexOffset, Class<T> nodeClass) {
        int childIndex = getChildIndex(child);
        if (childIndex < 0) {
            return null;
        }

        int signum = indexOffset >= 0 ? 1 : -1;
        for (int i = 0; ; ++i) {
            int nextIndex = childIndex + signum * i;
            if (nextIndex < 0 || nextIndex >= children.size()) {
                return null;
            }

            Node sibling = children.get(nextIndex);
            if (nodeClass.isInstance(sibling)) {
                if (indexOffset == 0) {
                    return nodeClass.cast(sibling);
                }
                indexOffset -= signum;
            }
        }
    }

    public int getChildCount() {
        return children == null ? 0 : children.size();
    }

    public <T extends Node> int getChildCount(Class<T> nodeClass) {
        int count = 0;
        if (children != null) {
            for (Node child : children) {
                if (nodeClass.isInstance(child)) {
                    ++count;
                }
            }
        }
        return count;
    }

    public List<Node> getChildren() {
        if (children == null) {
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableList(children);
        }
    }

    public <T extends Node> void visitAll(Class<T> nodeClass, Consumer<T> visitor) {
        visitBottomUp(nodeClass, visitor);
    }

    public <T extends Node> void visitAllChildren(Class<T> nodeClass, Consumer<T> visitor) {
        visitBottomUpChildren(nodeClass, visitor);
    }

    public <T extends Node> void visitAllDirectChildren(Class<T> nodeClass, Consumer<T> visitor) {
        if (children != null) {
            for (Node child : children) {
                if (nodeClass.isInstance(child)) {
                    visitor.accept(nodeClass.cast(child));
                }
            }
        }
    }

    public <T extends Node> void visitAllDirectChildren(Class<T> nodeClass, Visitor<T> visitor) {
        if (children != null) {
            int index = 0;
            for (Node child : children) {
                if (nodeClass.isInstance(child)) {
                    visitor.visit(nodeClass.cast(child), index++);
                }
            }
        }
    }

    public <T extends Node> void visitBottomUpChildren(Class<T> nodeClass, Consumer<T> visitor) {
        if (children != null) {
            for (Node child : children) {
                if (nodeClass.isInstance(child)) {
                    visitor.accept(nodeClass.cast(child));
                }
                child.visitBottomUpChildren(nodeClass, visitor);
            }
        }
    }

    public <T extends Node> void visitBottomUp(Class<T> nodeClass, Consumer<T> visitor) {
        if (nodeClass.isInstance(this)) {
            visitor.accept(nodeClass.cast(this));
        }

        if (children != null) {
            for (Node child : children) {
                child.visitBottomUp(nodeClass, visitor);
            }
        }
    }

    /**
     * @param visitor return true if your done visiting, otherwise false
     */
    public <T extends Node> void visitBottomUp(Class<T> nodeClass, Predicate<T> visitor) {
        if (nodeClass.isInstance(this) && visitor.test(nodeClass.cast(this))) {
            return;
        }

        if (children != null) {
            for (Node child : children) {
                child.visitBottomUp(nodeClass, visitor);
            }
        }
    }

    /**
     * @param visitor return true if your done visiting, otherwise false
     */
    public boolean visitTopDown(Predicate<Node> visitor) {
        if (children != null) {
            for (int i = children.size() - 1; i >= 0; --i) {
                Node child = children.get(i);
                if (child.visitTopDown(visitor)) {
                    return true;
                }
            }
        }
        return visitor.test(this);
    }

    /**
     * @param visitor return true if your done visiting, otherwise false
     */
    public <T extends Node> boolean visitTopDown(Class<T> nodeClass, Predicate<T> visitor) {
        if (children != null) {
            for (int i = children.size() - 1; i >= 0; --i) {
                Node child = children.get(i);
                if (child.visitTopDown(nodeClass, visitor)) {
                    return true;
                }
            }
        }
        return nodeClass.isInstance(this) && visitor.test(nodeClass.cast(this));
    }

    public Node getParent() {
        return parent;
    }

    public void removeFromParent() {
        parent.remove(this);
    }

    public boolean isRenderable() {
        return false;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public interface Visitor<T extends Node> {
        void visit(T node, int index);
    }
}
