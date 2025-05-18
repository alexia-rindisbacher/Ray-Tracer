package RayTracer;
import java.util.LinkedList;
/**
 * Represents a rectangular grid of using a linked list of columns where the top row is held by private field, row.
 * Width and Height are final ints representing the intial width and height and cannot be changed once intialized.
 * Individual pixels are accessed by their coordinates where enumeration begins from 0.
 *
 * @author Alexia Rindisbacher
 * @version 0.1
 * @since May 18
 */

public class Canvas {
    private final int width;
    private final int height;
    private LinkedList<LinkedList<Color>> row;

    public Canvas(int width, int height){
            this.width = width;
            this.height = height;
            this.row = new LinkedList<>();
            for (int i = 0 ; i < width ; i++){
                LinkedList<Color> newColumn = new LinkedList<>();
                for (int j = 0; j < height ; j++){
                    newColumn.add(new Color(0,0,0));
                }
                row.add(newColumn);
            }
    }

    public int getWidth(){return width;}

    public int getHeight(){return height;}

    public void writePixel(int r, int c, Color o ){
        if (r > (width - 1) || r < 0 || c > (height - 1) || c < 0){
            throw new IndexOutOfBoundsException("Pixel Location must be within the canvas");
        }

        row.get(r).get(c).replace(o);
    }

    public Color getPixel(int r, int c){
        return row.get(r).get(c);
    }
    @Override
    public String toString(){
        String canvas = "";

        for (int i = 0 ; i < height ; i++){
            for (int j = 0; j < width ; j++){
                canvas += this.row.get(j).get(i);
            }
            canvas += "\n";
        }


        return canvas;
    }
}


