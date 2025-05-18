package RayTracer;
import java.util.LinkedList;

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

    public void writePixel(int r, int c, Color o ){
        if (r > (width - 1) || r < 0 || c > (height + 1) || c < 0){
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
                canvas = canvas + this.row.get(j).get(i) + "";
            }
            canvas = canvas + "\n";
        }


        return canvas;
    }
}


