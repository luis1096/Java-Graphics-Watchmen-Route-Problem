package Assignment3;
import javax.swing.*;
import java.awt.Color;
import java.util.*;
/*
 * AUTHOR: Luis Oliveros
 * PID: 6131616
 */

public class Assignment3Framework
{
    public static void main(String[] args) throws InterruptedException 
    {
        new Assignment3Framework();
    }
    
    public Assignment3Framework() throws InterruptedException 
    {   
    	/*
    	int frameWidth = 800;
    	int frameHeight = 800;
    
        //Defining the geometric objects that represent the problem data
        int pointNumber = 10;
        Line[] lineArray = new Line[pointNumber];
        
        //creating a set of random points
        Random rnd = new Random();
        double a,b,c;
        for(int i=0; i<pointNumber; i++)
        {
            a = 1 + rnd.nextInt(5);
            b = 1 + rnd.nextInt(5);
            c = -(100 + rnd.nextInt(4000));
            lineArray[i] = new Line(a,b,c);
            lineArray[i].setBoundaryColor(Color.red); 
        }
      
         
        //graphing
        FrameDisplay frame = new FrameDisplay(frameWidth, frameHeight, lineArray);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.repaint();
        */
  
    	
     	int frameWidth = 800;
    	int frameHeight = 800;
        int dir = 0;//index of initial translation direction
        Random r = new Random();
    	GeometricObject[] g = new GeometricObject[10];
    	SimplePolygon[] sp = new SimplePolygon[10];
    	Vector[] v = new Vector[8];//array of Vectors(Directions)
    	
    	v[0] = new Vector(0, -1);//north
    	v[1] = new Vector(0, 1);//south
    	v[2] = new Vector(1,0);//east
    	v[3] = new Vector(-1,0);//west
    	v[4] = new Vector(1,1);//
    	v[5] = new Vector(1,-1);//
    	v[6] = new Vector(-1,-1);//
    	v[7] = new Vector(-1,1);//
 
    	
        Point p = new Point(5, 543);
        Point p1 = new Point(5, 713);
        Point p2 = new Point(249, 713);
        Point p3 = new Point(249, 543);
        Point p4 = new Point(200, 543);  
        Point p5 = new Point(200, 429);
        Point p6 = new Point(50, 429);
        Point p7 = new Point(50, 543);
        
        sp[1] = new SimplePolygon();
        sp[1].addVertex(p);
        sp[1].addVertex(p1);
        sp[1].addVertex(p2);
        sp[1].addVertex(p3);
        sp[1].addVertex(p4);
        sp[1].addVertex(p5);
        sp[1].addVertex(p6);
        sp[1].addVertex(p7);  
        //Tower Mario
        
        p = new Point(500, 601);
        p1 = new Point(500, 711);
        p2 = new Point(695, 711);
        p3 = new Point(695, 488); 
        p4 = new Point(601, 488);
        p5 = new Point(601, 604); 
        p6 = new Point(500, 604); 
 
        sp[2] = new SimplePolygon();
        sp[2].addVertex(p);
        sp[2].addVertex(p1);
        sp[2].addVertex(p2);
        sp[2].addVertex(p3);
        sp[2].addVertex(p4);
        sp[2].addVertex(p5);
        sp[2].addVertex(p6);
        //Mario pipe
        
        p = new Point(346, 605);
        p1 = new Point(346, 715);
        p2 = new Point(394, 715);
        p3 = new Point(394, 605); 
          
        sp[3] = new SimplePolygon();
        sp[3].addVertex(p);
        sp[3].addVertex(p1);
        sp[3].addVertex(p2);
        sp[3].addVertex(p3);
        //Mario
        
        p = new Point(505, 372);
        p1 = new Point(505, 395);
        p2 = new Point(474, 395);
        p3 = new Point(474, 454); 
        p4 = new Point(575, 454);
        p5 = new Point(575, 399); 
        p6 = new Point(548, 399); 
        p7 = new Point(548, 372); 
        
        
        sp[4] = new SimplePolygon();
        sp[4].addVertex(p);
        sp[4].addVertex(p1);
        sp[4].addVertex(p2);
        sp[4].addVertex(p3);
        sp[4].addVertex(p4);
        sp[4].addVertex(p5);
        sp[4].addVertex(p6);
        sp[4].addVertex(p7);
        //Cloud 1
        
        p = new Point(204, 142);
        p1 = new Point(204, 172);
        p2 = new Point(176, 172);
        p3 = new Point(176, 229);  
        p4 = new Point(325, 229);
        p5 = new Point(325, 172);
        p6 = new Point(300, 172);
        p7 = new Point(300, 142); 
        sp[5] = new SimplePolygon();
        sp[5].addVertex(p);
        sp[5].addVertex(p1);
        sp[5].addVertex(p2); 
        sp[5].addVertex(p3);
        sp[5].addVertex(p4);
        sp[5].addVertex(p5);
        sp[5].addVertex(p6); 
        sp[5].addVertex(p7);
        //Cloud 2
        
        p = new Point(982/2, 962/2);
        p1 = new Point(982/2, (962/2)+10);
        p2 = new Point((982/2)+10, (962/2)+10);
        p3 = new Point((982/2)+10, 962/2); 
        sp[0] = new SimplePolygon();
        sp[0].addVertex(p);
        sp[0].addVertex(p1);
        sp[0].addVertex(p2); 
        sp[0].addVertex(p3);
        //watchman
        
        sp[0].setInteriorColor(Color.RED);
        FrameDisplay frame = new FrameDisplay(frameWidth, frameHeight, sp);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    
        p = new Point(0,0);
        p1 = new Point(0,84);
        p2 = new Point(800,84);
        p3 = new Point(800,0); 
        sp[6] = new SimplePolygon();
        sp[6].addVertex(p);
        sp[6].addVertex(p1);
        sp[6].addVertex(p2); 
        sp[6].addVertex(p3);
        //wall up
        
        p = new Point(0,0);
        p1 = new Point(0,800);
        p2 = new Point(5,800);
        p3 = new Point(5,0); 
        sp[7] = new SimplePolygon();
        sp[7].addVertex(p);
        sp[7].addVertex(p1);
        sp[7].addVertex(p2); 
        sp[7].addVertex(p3);
        //wall left
        
        p = new Point(0,712);
        p1 = new Point(0,800);
        p2 = new Point(800,800);
        p3 = new Point(800,712); 
        sp[8] = new SimplePolygon();
        sp[8].addVertex(p);
        sp[8].addVertex(p1);
        sp[8].addVertex(p2); 
        sp[8].addVertex(p3);
        //wall down
        
        p = new Point(795,0);
        p1 = new Point(795,800);
        p2 = new Point(800,800);
        p3 = new Point(800,0); 
        sp[9] = new SimplePolygon();
        sp[9].addVertex(p);
        sp[9].addVertex(p1);
        sp[9].addVertex(p2); 
        sp[9].addVertex(p3); 
        //wall right
     
    	while(true)
    	{
    		boolean intersection = false;
    		Thread.sleep(1);
    		for(int i = 0; i < sp[0].getNumberOfVertices(); i++)
    		{
    			sp[0].getVertex(i).translate(v[dir]);
    		}
    		
    		for(int i = 1; i < sp.length; i++) 
    		{
    			if(Algorithms.isThereAnIntersection(sp[0], sp[i]))
    			{
    				intersection = true;
    				break;
    			}
    		}
    		
    		if(intersection)
    		{
    			for(int i = 0; i < sp[0].getNumberOfVertices(); i++)
        		{
        			sp[0].getVertex(i).translate(v[dir].neg()); 
        		}
    			dir = (dir +  Math.abs(r.nextInt(8))) % v.length;

    		}
    		 
    		frame.repaint();
    	}
    	
    }
    
}