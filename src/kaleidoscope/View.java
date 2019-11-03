package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JPanel;

import kaleidoscope.Model;

/**
 * The View "observes" and displays what is going on in the Model.
 * In this example, the Model contains only a single bouncing ball.
 * 
 * @author David Matuszek
 * @author Your name goes here
 * @author Your name goes here
 */
public class View extends JPanel implements Observer {
    
	int offsetX;
	int offsetY;
	
	private ArrayList<Model> models;

    /** This is what we will be observing. */
 
	   //Model model;
       // ArrayList <model> models; 

    /**
     * Constructor.
     * @param models The Model whose working is to be displayed.
     */
    View(ArrayList<Model> models) {
        this.models = models;
    }
    
    public void addFigures(ArrayList<Model> models) {
    	this.models = models;
    }
    

    /**
     * Displays what is going on in the Model. Note: This method should
     * NEVER be called directly; call repaint() instead.
     * 
     * @param g The Graphics on which to paint things.
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {
    	
    	if(models == null)g.setColor(Color.BLACK);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
	//	g.setColor(models.getcolor()); 
		//drawPolygon(g, models.get(i));

        
        for (int i = 0; i < models.size(); i++){ // Iterates through list of models and draws them on screen
        	g.setColor(models.get(i).getcolor());
        	if (models.get(i).randomint()==0)drawPolygon(g, models.get(i));
        	
        	if (models.get(i).randomint()==1)drawRect(g, models.get(i)); 
        	if (models.get(i).randomint()==2)drawOval(g, models.get(i)); 
	
    		//g.setColor(model.getcolor());
    		// drawPolygon(g, models.get(i));
    		//if (models.get(i).getNumber() == 1) drawOval(g, models.get(i));
    		//if (models.get(i).getNumber() == 2) drawRect(g, models.get(i));

    		}
    }
    
    public void drawOval(Graphics g, Model model){
    	  offsetX = getWidth()/2 - getHeight()/2;
    	  offsetY = -getWidth()/2 + getHeight()/2;
    	  
    	  if(model.getReflect() ==8){
    	  g.fillOval(model.getX(), model.getY(),
                  model.size/2, model.size/2);
    	  
       	  g.fillOval(getWidth() - model.getX(), getHeight()- model.getY(),
                  model.size/2, model.size/2);
    	
       	  g.fillOval(getWidth() - model.getX(),  model.getY(),
                  model.size/2, model.size/2);
       	  
       	  g.fillOval(model.getX(),  getHeight()- model.getY(),
                  model.size/2, model.size/2);
       	  
       	  g.fillOval( model.getY() + offsetX ,model.getX() + offsetY,
                  model.size/2, model.size/2);
       	  g.fillOval(getHeight()- model.getY()+ offsetX,getWidth() - model.getX()  + offsetY, 
                  model.size/2, model.size/2);
       	  g.fillOval( model.getY() + offsetX, getWidth() - model.getX()  + offsetY, 
                  model.size/2, model.size/2);
       	  g.fillOval( getHeight()- model.getY() + offsetX, model.getX()  + offsetY, 
                  model.size/2, model.size/2);
    	  
    	  if(model.getReflect()==4){
    		  
    		  g.fillOval(model.getX(), model.getY(),
                      model.size/2, model.size/2);
        	  
           	  g.fillOval(getWidth() - model.getX(), getHeight()- model.getY(),
                      model.size/2, model.size/2);
        	
           	  g.fillOval(getWidth() - model.getX(),  model.getY(),
                      model.size/2, model.size/2);
           	  
           	  g.fillOval(model.getX(),  getHeight()- model.getY(),
                      model.size/2, model.size/2);
    		  
    	  }}
}
    
    public void drawRect(Graphics g, Model model){
  	  offsetX = getWidth()/2 - getHeight()/2;
  	  offsetY = -getWidth()/2 + getHeight()/2;
  	  
  	  if(model.getReflect() == 8){
  	      g.fillRect(model.getX(), model.getY(),
         			model.size/2, model.size/2);    	
  	  
     	  g.fillRect(getWidth() - model.getX(), getHeight()- model.getY(),
         			model.size/2, model.size/2);    	
  	
     	  g.fillRect(getWidth() - model.getX(),  model.getY(),
         			model.size/2, model.size/2);    	

     	  
     	  g.fillRect(model.getX(),  getHeight()- model.getY(),
         			model.size/2, model.size/2);    	
     	  
     	  g.fillRect( model.getY() + offsetX ,model.getX() + offsetY,
         			model.size/2, model.size/2);    	

  	  
     	  g.fillRect(getHeight()- model.getY()+ offsetX,getWidth() - model.getX()  + offsetY, 
         			model.size/2, model.size/2);    	

  	
     	  g.fillRect( model.getY() + offsetX, getWidth() - model.getX()  + offsetY, 
         			model.size/2, model.size/2);    	

     	  
     	  g.fillRect( getHeight()- model.getY() + offsetX, model.getX()  + offsetY, 
         			model.size/2, model.size/2);  }
  	  if(model.getReflect() == 4){
  		  g.fillRect(model.getX(), model.getY(),
       			model.size/2, model.size/2);    	
	  
   	  g.fillRect(getWidth() - model.getX(), getHeight()- model.getY(),
       			model.size/2, model.size/2);    	
	
   	  g.fillRect(getWidth() - model.getX(),  model.getY(),
       			model.size/2, model.size/2);    	

   	  
   	  g.fillRect(model.getX(),  getHeight()- model.getY(),
       			model.size/2, model.size/2);    
  		  
  	  }

  }
    
    
    public void drawPolygon(Graphics g, Model model){
    	  offsetX = getWidth()/2 - getHeight()/2;
    	  offsetY = -getWidth()/2 + getHeight()/2;
    	  int[] array = new int[]{model.getX(), model.getX() + model.size/2 , model.getX()};
    	  int x1 = array[0]; int x2 = array[1]; int x3 = array[2];
    	  
    	  int[]array2 = new int[] {model.getY(), model.getY(), model.getY() + model.size/2};
    	  int y1 = array2[0]; int y2 = array2[1]; int y3 = array2[2]; 
    	  
    	   int[] arrayX2 = new int[]{getWidth() - x1 + model.size/2, getWidth()- x2+model.size/2, getWidth() - x3+model.size/2};
           int[] arrayY2 = new int[] { getHeight() - y1 + model.size/2,  getHeight()-y2 + model.size/2,  getHeight() - y3 + model.size/2};
    	  
      	  if(model.getReflect() == 8){
    	  g.fillPolygon(array, array2,3);
    	      
       
    	  g.fillPolygon(arrayX2, arrayY2,3);

    	  g.fillPolygon(array, arrayY2,3);

    	  g.fillPolygon(arrayX2, array2,3);
    	 
    	  int[] arrayY3 = new int[]{model.getX() + offsetY, model.getX() + model.size/2 + offsetY , model.getX() + offsetY};
     	  int[] arrayX3 = new int[] {model.getY()+offsetX, model.getY()+offsetX, model.getY() + model.size/2 +offsetX};
     	  g.fillPolygon(arrayX3, arrayY3,3);	
     	  int[] arrayY4 = new int[]{getWidth() - x1 + model.size/2 + offsetY, getWidth()- x2+model.size/2 + offsetY, getWidth() - x3+model.size/2 + offsetY};
          int[] arrayX4 = new int[] { getHeight() - y1 + model.size/2 +offsetX,  getHeight()-y2 + model.size/2 +offsetX,  getHeight() - y3 + model.size/2 +offsetX};
     	  g.fillPolygon(arrayX4, arrayY4,3);	

     	  g.fillPolygon(arrayX3, arrayY4,3);	
     	  g.fillPolygon(arrayX4, arrayY3,3);	}
      	  if(model.getReflect() == 4){
      	  
      	  g.fillPolygon(array, array2,3);
      	      
      	  g.fillPolygon(arrayX2, arrayY2,3);

      	  g.fillPolygon(array, arrayY2,3);

      	  g.fillPolygon(arrayX2, array2,3);
      		  
      	  }


       	
    }

    /**
     * When an Observer notifies Observers (and this View is an Observer),
     * this is the method that gets called.
     * 
     * @param obs Holds a reference to the object being observed.
     * @param arg If notifyObservers is given a parameter, it is received here.
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable obs, Object arg) {
        repaint();
    }
}