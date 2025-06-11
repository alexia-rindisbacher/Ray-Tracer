package RayTracer;
import java.io.IOException;
import java.util.LinkedList;
import java.io.File;
import java.io.FileWriter;
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

    public Canvas(int width, int height, Color x){
        this.width = width;
        this.height = height;
        this.row = new LinkedList<>();
        for (int i = 0 ; i < width ; i++){
            LinkedList<Color> newColumn = new LinkedList<>();
            for (int j = 0; j < height ; j++){
                newColumn.add(new Color(x.getRed(), x.getGreen(), x.getBlue()));
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


    public String canvasToPPM(){
        String s = "P3\n" + width + " " + height + "\n225\n";
        //if width * 12 is greater than 70 we should add a new line after the 5th pixel + red and green

        for (int i = 0 ; i < height ; i++){
            for (int j = 0; j < width ; j++){
                double red = this.row.get(j).get(i).getRed();
                double green = this.row.get(j).get(i).getGreen();
                double blue = this.row.get(j).get(i).getBlue();

                //Checks red
                if (red > 1){
                    s +=  225 + " ";
                }
                else if (red < 0){
                    s +=  0 + " ";
                }
                else{
                    s += Math.round(red * 255) + " ";
                }

                //Checks green
                if (green > 1){
                    s +=  225 + " ";
                }
                else if (green < 0){
                    s +=  0 + " ";
                }
                else{
                    s += Math.round(green * 255)  + " ";
                }


                if (j == 5){
                    s += "\n";
                }

                //Checks blue
                if (blue > 1){
                    s +=  225 + " ";
                }
                else if (blue < 0){
                    s +=  0 + " ";
                }
                else{
                    s += Math.round(blue * 255)  + " ";
                }

            }
            s += "\n";
        }

        return s;
    }

    public File stringToPPM(String fileName){
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(this.canvasToPPM());
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return null;
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


