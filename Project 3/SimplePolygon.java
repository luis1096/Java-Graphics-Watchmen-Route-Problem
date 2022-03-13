package Assignment3;
import java.awt.Graphics;
/*
 * AUTHOR: Luis Oliveros
 * PID: 6131616
 */
import java.util.Random;
/*
 * AUTHOR: Luis Oliveros
 * PID: 6131616
 */

/**
 * This class represents a rectilinear polygon with edges that intersect 
 * at right angles to the axis. It is given by a n > 4 pairs of points. 
 */
public class SimplePolygon extends GeometricObject
{
   
    private Point[] vertexList;
    private int size;
    private int vertNumber;
    private int data;

    public SimplePolygon()
    {
    	size = 100;
        vertexList = new Point[size];
        vertNumber = 0; 
        
    }
    
    public Point getVertex(int i)
    {
    	Point v = new Point();
    	v = vertexList[i];
    	return v; 
    }
    
    public Point[] getVertexList() 
    {
    	return vertexList;
    }
    
    public LineSegment[] getEdges()
    {
    	LineSegment[] edges = new LineSegment[vertNumber];
    	for(int i = 0;i < vertNumber;i++)
    	{
    		edges[i] = new LineSegment(vertexList[i], vertexList[(i + 1) % vertNumber]);
    	}
    	return edges;
    }

	@Override
	public void draw(Graphics g) 
	{
		int[] xCord = new int[vertNumber];
		int[] yCord = new int[vertNumber];
		 
		for(int i = 0;i < vertNumber;i++)
		{
			xCord[i] = (int)vertexList[i].getX();
			yCord[i] = (int)vertexList[i].getY();
		}
		
		g.setColor(getInteriorColor());
		g.fillPolygon(xCord, yCord, vertNumber);
		g.setColor(getBoundaryColor());
		g.drawPolygon(xCord, yCord, vertNumber);
	}
	
	public void addVertex(Point v)
	{
		vertexList[vertNumber] = v;
		vertNumber++;
	}
	
	public void setRecPolygonData(int d)
	{
		data = d;
	}
	
	public int getRecPolygonData() 
	{
		return data;
	}
	
	public double area()
	{
		if(vertNumber < 4)
		{
			return Double.NaN;
		}
		
		double sum1 = 0.0;
		double sum2 = 0.0;
		Point v1;
		Point v2;
		
		for(int i=0; i<vertNumber; i++)
		{
			v1 = vertexList[i];
			v2 = vertexList[(i+1) % vertNumber];
			sum1 += v1.getX() * v2.getY();
			sum2 += v2.getX() * v1.getY();
		}
		double area = 0.5 * Math.abs(sum1 - sum2);
		return area;
	}
	
	public Rectangle boundingRectangle() 
	{
		double x1 = greatestX();
		double y1 = smallestY();
		double x2 = smallestX();
		double y2 = greatestY();
		Point p1 = new Point(x1,y1);
		Point p2 = new Point(x2,y2);
		 
		Rectangle r =  new Rectangle(p1, p2);
		return r;
	}
	
	public int getNumberOfVertices()
	{
		return vertNumber;
	}
	
	public double greatestX()
	{
		double max = vertexList[0].getX();
		for(int i = 0; i < vertNumber; i++)
		{
			double x = vertexList[i].getX();
			if(x > max)
			{
				max = x;
			}
		}
		return max;
	}
	
	public double greatestY()
	{
		double max = vertexList[0].getY();
		for(int i = 0; i < vertNumber; i++)
		{
			double y = vertexList[i].getY();
			if(y > max)
			{
				max = y;
			}
		}
		return max;
	}
	
	public String toString()
	{
		String str = "POLY SIMPLE " + super.toString() + "\n";
		for(int i = 0; i < vertNumber; i++)
		{
	        str += vertexList[i] + "\n";
		}

	        return str;
	}
	
	public double smallestX()
	{
		double min = vertexList[0].getX();
		for(int i = 0; i < vertNumber; i++)
		{
			double x = vertexList[i].getX();
			if(x < min)
			{
				min = x;
			}
		}
		return min;
	}
	
	public double smallestY()
	{
		double min = vertexList[0].getY();
		for(int i = 0; i < vertNumber; i++)
		{
			double y = vertexList[i].getY();
			if(y < min)
			{
				min = y;
			}
		}
		return min;
	}
	
	public void translate(Vector v)
	{
	  for(Point p: vertexList)
	  {
		  p.translate(v);
	  }
	}
	
	public boolean isPointInRecPolygon(Point p)
	{
		int j = 1;
		for(int i =0; i < vertNumber - 1; i++)
		{
			//double x =  p.getX();
			//double y =  p.getY();
			Point n1 = vertexList[i];
			Point n2 = vertexList[j];
			if(n1.getX() == n2.getX())
			{
				//vertical edge
				if(!(smallestY() <= p.getY() && p.getY() < greatestY()))
				{
					return false;
				}
				
			}
			else
			{
				//horizontal edge
				if(!(smallestX() <= p.getX() && p.getX() < greatestX()))
				{
					return false;
				}
			}
			j++;
					
		}
		return true;
	}
	
	
}
     