package Assignment3;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
/**
 * This class implements several algorithms.
 */
/*
 * AUTHOR: Luis Oliveros
 * PID: 6131616
 */

public class Algorithms
{
	 /**
     * Determines the closest pair of points. Brute-force solution is used.
     * 
     * @param points input points. Array is assumed to be fully populated.
     * @param indices output array containing indices of closest pair
     */
    public static void closestPairOfPoints(Point[] points, int[] indices)
    {
        double minDistance = Double.MAX_VALUE;
        for (int i=0; i<points.length; i++)
        {
            for (int j=i+1; j<points.length; j++)
            {
               double d = points[i].distance(points[j]);
               if (d<minDistance)
               {
                   minDistance = d;
                   indices[0] = i;  
                   indices[1] = j; 
               }
            } 
        }
    }

    public static int isPointContainedIn(GeometricObject[] g, Point p)
    {
        SimplePolygon[] rArray = (SimplePolygon[])g; 
        
        for (int i=0; i < g.length; i++)
        {
            if (rArray[i].isPointInRecPolygon(p)) 
                return i;  
        }
        
        return -1; 
    }
    
    public static boolean isThereAnIntersection(SimplePolygon pol1, SimplePolygon pol2)
    {
    	LineSegment[] edges1 = pol1.getEdges();
    	LineSegment[] edges2 = pol2.getEdges();
    	
    	for(int i = 0; i < pol1.getNumberOfVertices(); i++)
    	{
    		LineSegment e1 = edges1[i];
    		for(int j = 0; j < pol2.getNumberOfVertices(); j++)
    		{
    			LineSegment e2 = edges2[j];
    			Point p = new Point();
    			if (e1.intersect(e2, p) == 1)
    			{
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    /**
     * Given a set of non-vertical line segments, determines whether any two
     * line segments intersect.
     *
     * @param lsArray array of line segments
     * @return an intersection point if one exists, null otherwise
     */
    public static Point isThereAnIntersection(LineSegment[] lsArray)
    {
        //output: an intersection point, if one exists
        Point output = null;
        //data structure: sweep line
        SweepLine sweepLine = new SweepLine();
        //data structure: array list of events
        ArrayList<Event> eventList = new ArrayList<>();
        for (int i=0; i< lsArray.length; i++)
        {
            //each line segment generates two events
            Event e1 = new Event("left endpoint", lsArray[i]);
            eventList.add(e1);
            Event e2 = new Event("right endpoint", lsArray[i]);
            eventList.add(e2);
        }
        eventList.sort(new EventComparator());
        label:
        	
        for(int i=0; i<eventList.size(); i++)
        {
            Event e = eventList.get(i);
            LineSegment s, pre, suc;
            Point p = new Point();
            switch (e.type)
            {
                case "left endpoint":
                    s = e.segment;
                    sweepLine.add(s);
                    pre = sweepLine.predecessor(s);
                    if (pre != null && s.intersect(pre, p) == 1)
                    {
                        output = p;
                        break label;
                    }
                    suc = sweepLine.successor(s);
                    if (suc != null && s.intersect(suc, p) == 1)
                    {
                        output = p;
                        break label;
                    }
                    break;
                case "right endpoint":
                    s = e.segment;
                    pre = sweepLine.predecessor(s);
                    suc = sweepLine.successor(s);
                    if (pre != null && suc != null && pre.intersect(suc, p) == 1)
                    {
                        output = p;
                        break label;
                    }
                    sweepLine.remove(s);
                    break;
            }
        }
        return output;
    }
}
/**
 * Represents an event point, namely, a left endpoint or a right endpoint.
 */
class Event
{
    public String type;
    public LineSegment segment;
    Event(String t, LineSegment s)
    {
        type = t;
        segment = s;
    }
}

class SweepLine
{
    private ArrayList<LineSegment> sl;
    SweepLine()
    {
        sl = new ArrayList<>();
    }
    /**
     * Adds a line segment; sweep line remains sorted.
     *
     * @param index index of a line segment
     */
    public void add(LineSegment segm)
    {
        //sl.add(segm);
        int i=0;
        int n = sl.size();
        double x = segm.getBegin().getX();
        double y = segm.getBegin().getY();
        while (i<n &&
               y>new Line(sl.get(i).getBegin(), sl.get(i).getEnd()).getY(x))
        {
            i++;
        }
        sl.add(i, segm);
    }
    
    public LineSegment predecessor (LineSegment s)
    {
        int loc = sl.indexOf(s);
        if (loc == -1) //s not found in this sweep line
            throw new IllegalArgumentException();
        if (loc != 0) //it's not the first one (no predecessor)
            return sl.get(loc-1);
        else //first line segment, no predecessor
            return null;
    }
    
    public void remove(LineSegment s)
    {
        if (!sl.remove(s)) throw new IllegalArgumentException();
    }
    
    public String toString()
    {
        String str = "";
        for (int i=0; i<sl.size(); i++)
        {
            str += sl.get(i) + "\n";
        }
        return str;
    }
    
    public LineSegment successor (LineSegment s)
    {
        int loc = sl.indexOf(s);
        if (loc == -1) //s not found in this sweep line
            throw new IllegalArgumentException();
        if (loc != sl.size()-1) //it's not the last one (no successor)
            return sl.get(loc+1);
        else //last line segment, no predecessor
            return null;
    }
}

/**
 * Performs comparison of two Event objects
 */
class EventComparator implements Comparator<Event>
{
    public int compare(Event e1, Event e2)
    {
            Point p1, p2;
            if (e1.type.equals("left endpoint"))
                p1 = e1.segment.getBegin();
            else
                p1 = e1.segment.getEnd();
            if (e2.type.equals("left endpoint"))
                p2 = e2.segment.getBegin();
            else
                p2 = e2.segment.getEnd();
            if (p1.getX() < p2.getX()) return -1;
            else
                if (p1.getX() == p2.getX()) return 0;
                else return 1;
    }
}
