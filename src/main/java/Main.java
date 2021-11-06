import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.Vierhoek;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<Vierhoek>vierhoeken=readJson();
        int maxX=getMaxX(vierhoeken);
        int maxY=getMaxY(vierhoeken);
        MySvgGenerator.makeSvg(vierhoeken,maxX,maxY);


    }

    private static int getMaxX(ArrayList<Vierhoek> vierhoeken) {
        Integer maxX=vierhoeken
                .stream()
                .flatMap(rectangles->rectangles.coordinaten.stream())
                .mapToInt(coordinaat->coordinaat.x)
                .max().orElseThrow(NoSuchElementException::new);
        return maxX;
    }

    private static int getMaxY(ArrayList<Vierhoek> vierhoeken) {
        Integer maxY=vierhoeken
                .stream()
                .flatMap(rectangles->rectangles.coordinaten.stream())
                .mapToInt(coordinaat->coordinaat.y)
                .max().orElseThrow(NoSuchElementException::new);
        return maxY;
    }

    private static ArrayList<Vierhoek> readJson() throws FileNotFoundException {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Vierhoek>>(){}.getType();
        ArrayList<Vierhoek>vierhoeken = gson.fromJson(new FileReader("rectangles.json"), listType);
        return vierhoeken;
    }
}
