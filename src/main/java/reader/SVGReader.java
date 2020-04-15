package reader;

import org.apache.batik.bridge.*;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.dom.svg.SVGOMPathElement;
import org.apache.batik.dom.svg.SVGPathSupport;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGPoint;

import java.awt.geom.Point2D;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;

public class SVGReader {

    private int polygonPoints;

    public SVGReader(int points) {
        polygonPoints = points;
    }

    public ArrayList<SVGPoint> readCountyPathFromSVG(String f, String pathName) {

        ArrayList<SVGPoint> pPoints = new ArrayList<>();

        try {

            SVGDocument svgDoc;
            UserAgent userAgent;
            DocumentLoader loader;
            BridgeContext ctx;
            GVTBuilder builder;
            GraphicsNode rootGN;

            userAgent = new UserAgentAdapter();
            loader = new DocumentLoader(userAgent);
            ctx = new BridgeContext(userAgent, loader);
            ctx.setDynamicState(BridgeContext.DYNAMIC);
            builder = new GVTBuilder();

            URI fileURI = new File(f).toURI();
            String parser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory svgf = new SAXSVGDocumentFactory(parser);
            svgDoc = (SVGDocument) svgf.createDocument(fileURI.toString());

            rootGN = builder.build(ctx, svgDoc);

            Element element = svgDoc.getElementById(pathName);

            SVGOMPathElement path = (SVGOMPathElement) element;

            float totalPathLength = path.getTotalLength();
            float unit = totalPathLength / (float) polygonPoints;

            for (int j = 0; j < polygonPoints; j++) {
                SVGPoint tmp_point = SVGPathSupport.getPointAtLength(path, unit * j);
                pPoints.add(tmp_point);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pPoints;
    }

    public Point2D.Double getPoint(String f, String pathName) {

        Point2D.Double point = null;
        try {
            SVGDocument svgDoc;
            UserAgent userAgent;
            DocumentLoader loader;
            BridgeContext ctx;
            GVTBuilder builder;
            GraphicsNode rootGN;

            userAgent = new UserAgentAdapter();
            loader = new DocumentLoader(userAgent);
            ctx = new BridgeContext(userAgent, loader);
            ctx.setDynamicState(BridgeContext.DYNAMIC);
            builder = new GVTBuilder();

            URI fileURI = new File(f).toURI();
            String parser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory svgf = new SAXSVGDocumentFactory(parser);
            svgDoc = (SVGDocument) svgf.createDocument(fileURI.toString());

            rootGN = builder.build(ctx, svgDoc);

            Element element = svgDoc.getElementById(pathName);
            NamedNodeMap map = element.getAttributes();
            Node cx = map.item(1);
            Node cy = map.item(2);
            double x = Double.valueOf(cx.getFirstChild().getNodeValue());
            double y = Double.valueOf(cy.getFirstChild().getNodeValue());
            point = new Point2D.Double(x, y);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return point;
    }

}
