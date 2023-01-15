import java.util.ArrayList;
import java.awt.Point;
import java.util.Random;
//Gage Standard
//Ethan Fitzgerald
//Jade Timothy

public class ConvexHull {
    //Checks to see if the value has already been detected, if it has the value is returned, else it is added to the ArrayList
    public static void encasingLinePopulation(ArrayList<Point> elp, Point p1p2){
        for (Point pn : elp){
            if (pn == p1p2) {
                return;
            }
        }
        elp.add(p1p2);
    }
    public static void main(String [] args){
        ArrayList<Point> points = new ArrayList<>();   //Using an arraylist that contains a Java created Point class.
        ArrayList<Point> encasingLine = new ArrayList<>();        // Each cell will contain a Point object that stores an x and y value.
        Random rand = new Random();
        //Random point generator that enables the points to be set
        for(int i = 0; i < 9; i++){
            int x = rand.nextInt(40-15)-15;
            int y = rand.nextInt(40-15)-15;
            Point pt = new Point();
            pt.setLocation(x,y);
            points.add(pt);
            System.out.println("[x=" + (int)pt.getX() + ",y=" + (int)pt.getY() + "]");
        } //This for loop takes in user input to initialize the Arraylist.
        System.out.println("--------Solution----------");
        for (int i = 0; i < points.size() - 1; i++){    //
            for (int j = i + 1; j < points.size(); j++){
                Point p1 = points.get(i); Point p2 = points.get(j);
                double a, b, c;
                int isSign = 0;
                //Math to calculate the region in which the point will be located
                a = p2.getY() - p1.getY();
                b = p1.getX() - p2.getX();
                c = p1.getX() * p2.getY() - p1.getY() * p2.getX(); //
                boolean twoPointSeg = true;
                for (int k = 0; k < points.size(); k++){
                    Point p3 = points.get(k);
                    double position = (a * p3.getX()) + (b * p3.getY()) - c;
                    //Find the regions in which the point will be
                    if (isSign != 0){
                        if (position > 0 && isSign < 0){
                            twoPointSeg = false; break;
                        }
                        else if (position < 0 && isSign > 0){
                            twoPointSeg = false; break;
                        }
                    }
                    else{
                        if (position < 0)
                            isSign = -1;
                        else if (position > 0)
                            isSign = 1;
                    }
                }
                //Sends the points to the method to determine if they are part of the solution
                if(twoPointSeg == true){
                    encasingLinePopulation(encasingLine, p1);
                    encasingLinePopulation(encasingLine, p2);
                }
            }
        }
        //Loop that displays the solution for the user
        for (int i = encasingLine.size() - 1; i >= 0; i--){
            System.out.println("[x=" + (int)encasingLine.get(i).getX() + ",y=" + (int)encasingLine.get(i).getY() + "]");
        }
    }
}