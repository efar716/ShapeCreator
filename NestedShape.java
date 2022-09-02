
import java.awt.Color;
import java.util.*;

class NestedShape extends RectangleShape {

    private ArrayList<Shape> innerShapes = new ArrayList<Shape>();

    public NestedShape() {

        super();
        createInnerShape(0, 0, this.getWidth() / 2, this.getHeight() / 2, this.getColor(), PathType.BOUNCE,
                this.getText(), ShapeType.RECTANGLE);

    }

    public NestedShape(int x, int y, int width, int height, int marginWidth, int marginHeight, Color c, PathType pt,
            String text) {

        super(x, y, width, height, marginWidth, marginHeight, c, pt, text);
        createInnerShape(0, 0, this.getWidth() / 2, this.getHeight() / 2, this.getColor(), PathType.BOUNCE,
                this.getText(), ShapeType.RECTANGLE);

    }

    public NestedShape(int width, int height) {

        super(0, 0, width, height, DEFAULT_MARGIN_WIDTH, DEFAULT_MARGIN_HEIGHT, Color.black, PathType.BOUNCE, "");

    }

    public Shape createInnerShape(int x, int y, int w, int h, Color c, PathType pt, String text, ShapeType st) {
        Shape inner;

        if (st == ShapeType.RECTANGLE) {
            inner = new RectangleShape(x, y, w, h, this.width, this.height, c, pt, text);
        }

        else if (st == ShapeType.OVAL) {
            inner = new OvalShape(x, y, w, h, this.width, this.height, c, pt, text);
        }

        else {
            inner = new NestedShape(x, y, w, h, this.width, this.height, c, pt, text);
        }

        innerShapes.add(inner);
        inner.setParent(this);
        return inner;

    }

    public Shape getInnerShapeAt(int index) {

        return innerShapes.get(index);

    }

    public int getSize() {

        return innerShapes.size();

    }

    public int indexOf(Shape s) {

        return innerShapes.indexOf(s);
    }

    public void add(Shape s) {

        innerShapes.add(s);
        s.setParent(this);

    }

    public void remove(Shape s) {

        innerShapes.remove(s);
        s.setParent(null);

    }

    public ArrayList<Shape> getAllInnerShapes() {

        return innerShapes;
    }

    public String getPathTypeName() {
        return path.getClass().getName();
    }

    // public Shape getParent()

    public void setWidth(int w) {

        this.width = w;
        for (int i = 0; i < innerShapes.size(); i++) {

            this.getInnerShapeAt(i).setMarginSize(w, height);
        }

    }

    public void setHeight(int h) {

        this.height = h;
        for (int i = 0; i < innerShapes.size(); i++) {

            this.getInnerShapeAt(i).setMarginSize(width, h);
        }

    }

    public void setColor(Color c) {

        this.color = c;
        for (int i = 0; i < innerShapes.size(); i++) {

            this.getInnerShapeAt(i).setColor(c);
        }

    }

    public void setText(String t) {

        this.text = t;
        for (int i = 0; i < innerShapes.size(); i++) {

            this.getInnerShapeAt(i).setText(t);
        }

    }

    public void draw(Painter p) {
        p.setPaint(Color.black);
        // p.setPaint(this.getColor());
        p.drawRect(x, y, width, height);
        p.translate(x, y);
        for (int i = 0; i < innerShapes.size(); i++) {

            this.getInnerShapeAt(i).draw(p);
        }
        p.translate(-x, -y);

    }

    public void move() {
        path.move();
        for (int i = 0; i < innerShapes.size(); i++) {

            getInnerShapeAt(i).move();
        }

    }

}