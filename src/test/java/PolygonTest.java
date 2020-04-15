import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.svg.SVGPoint;
import polygon.Polygon;
import reader.SVGReader;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

public class PolygonTest {

    private static ArrayList<String> counties = new ArrayList<>();
    private static ArrayList<String> cities = new ArrayList<>();

    @Before
    public void setup() {
        counties.add("Schleswig-Holstein");
        counties.add("Sachsen-Anhalt");
        counties.add("Sachsen");
        counties.add("Saarland");
        counties.add("Rheinland-Pfalz");
        counties.add("Nordrhein-Westfalen");
        counties.add("Niedersachsen");
        counties.add("Mecklenburg-Vorpommern");
        counties.add("Hessen");
        counties.add("Hamburg");
        counties.add("Bremen");
        counties.add("Brandenburg");
        counties.add("Berlin");
        counties.add("Baden__x26__Württemberg");
        counties.add("Thüringen");
        counties.add("Bayern");

        cities.add("München");
        cities.add("BerlinStadt");
        cities.add("Stuttgart");
        cities.add("Saarbrücken");
        cities.add("Wiesbaden");
        cities.add("Mainz");
        cities.add("Düsseldorf");
        cities.add("BremenStadt");
        cities.add("Erfurt");
        cities.add("Dresden");
        cities.add("Magdeburg");
        cities.add("Hannover");
        cities.add("Hamburg");
        cities.add("Kiel");
        cities.add("Schwerin");
        cities.add("Potsdam");
    }

    @Test
    public void calculateTheSizeForGermanysCounties() {

        SVGReader c = new SVGReader(1000);

        HashMap<String, ArrayList<SVGPoint>> pathsOfCounties = new HashMap<>();
        HashMap<String, Double> areasOfCounties = new HashMap<String, Double>();

        double calculatedAreaForGermany = 0.0;
        for (String county : counties) {
            ArrayList<SVGPoint> points = c.readCountyPathFromSVG("DeutschlandMitStaedten.svg", county);
            pathsOfCounties.put(county, points);
            areasOfCounties.put(county, Polygon.calculatePolygonArea(points));
        }

        for (Double value : areasOfCounties.values()) {
            calculatedAreaForGermany += value;
        }

        double realAreaOfGermany = 357386;
        double scale = realAreaOfGermany / calculatedAreaForGermany;
        for (HashMap.Entry<String, Double> entry : areasOfCounties.entrySet()) {
            System.out.println("Fläche von " + entry.getKey() + ": " + (entry.getValue() * scale));
        }

    }

    @Test
    public void checkWhereGivenCitiesAreLocated() {

        SVGReader c = new SVGReader(400);
        HashMap<String, ArrayList<SVGPoint>> pathsOfCounties = new HashMap<>();

        for (String county : counties) {
            ArrayList<SVGPoint> points = c.readCountyPathFromSVG("DeutschlandMitStaedten.svg", county);
            pathsOfCounties.put(county, points);
            System.out.println(county + " erfolgreich geparsed");
        }

        for (String city : cities) {
            Point2D.Double cityPoint = c.getPoint("DeutschlandMitStaedten.svg", city);
            for (HashMap.Entry<String, ArrayList<SVGPoint>> entry : pathsOfCounties.entrySet()) {
                System.out.print("Die Stadt " + city);
                if (Polygon.isPointInsidePolygon(entry.getValue(), 400, cityPoint)) {
                    System.out.print(" liegt in " + entry.getKey());
                    System.out.println("");
                } else {
                    System.out.print(" liegt nicht in " + entry.getKey());
                    System.out.println("");
                }
                System.out.println("-------------------------------------");
            }
        }
    }

}
