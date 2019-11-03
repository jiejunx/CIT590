package kaleidoscope;

import java.awt.Color;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;



/**
 * This is the Model class for a bouncing ball. It is an Observable,
 * which means that it can notifyObservers that something in the
 * model has changed, and they should take appropriate actions.
 * 
 * @author David Matuszek
 * @author Jiejun Xie

 */
public class Model extends Observable {
    public final int size;

    private int xLimit, yLimit;
    private int xDelta;
    private int yDelta;
    private Timer timer;
    private Random rand;
    private Color color;
    private int offset;
    private int timerSpeed = 40;
    private int num;
    //num = rand.nextInt(3);
    private int xPosition;
    private int yPosition;
    private int randnum;
    private int reflections = 8;


    /**
     * Sets the "walls" that the ball should bounce off from.
     * 
     * @param xLimit The position (in pixels) of the wall on the right.
     * @param yLimit The position (in pixels) of the floor.
     */
    
    public Model(int xLimit, int yLimit){
      //System.out.println(this.BALL_SIZE);
     
      setLimits(xLimit, yLimit); 
   //   System.out.println(xLimit);
      this.rand = new Random();
      this.size = rand.nextInt(50) + 20;
      this.randnum = rand.nextInt(3);
      xPosition = rand.nextInt(xLimit/2)+1;    
      yPosition = rand.nextInt(yLimit/2)+1;

      System.out.println("yes");
       // randomly selects a size for the circle and square Model objects
      creatcolor();    
      this.xDelta = 4 + rand.nextInt(10);  // randomly selects a 'speed' for horizontal movement between 5 and 15
      this.yDelta =  4 + rand.nextInt(10);
      
      //int maxOffset = Math.min(xLimit, yLimit) - BALL_SIZE;
      // this.offset = rand.nextInt(maxOffset / 2);
    };
    
    public int randomint(){
       // this.rand = new Random();
        return this.randnum;
    }
    
    public int getReflect(){
        // this.rand = new Random();
         return this.reflections;
     }
    
    public int fourReflect(){
        // this.rand = new Random();
    	reflections = 4;
         return this.reflections;
     }
    
    
    public int eightReflect(){
        // this.rand = new Random();
    	reflections = 8;
         return this.reflections;
     }
    
    public void setLimits(int xLimit, int yLimit) {
      
        this.xLimit = xLimit - size;
        this.yLimit = yLimit - size;
        //xPosition = rand.nextInt(xLimit/2);
        //yPosition = rand.nextInt(yLimit/2);
        xPosition = Math.min(xPosition, xLimit);
        yPosition = Math.min(yPosition, yLimit);}
    	
    
    
    public void creatcolor() {
    	float r = rand.nextFloat();
    	System.out.println(r);
    	System.out.println(r);
    	float g = rand.nextFloat();
    	float b = rand.nextFloat();
        color = new Color(r,g,b);
    }
    
    public int getXLimits(){
    	return xLimit;
    }

    public int getYLimits(){
    	return yLimit;
    }
 
    
    public void setcolor() {
    	
    	this.color = color;
    }
    
    public Color getcolor() { 	
    	return color;
    }

    public int getNumber() {
    	
        this.num = rand.nextInt(3);
        return num;
    }
    /**
     * @return The balls X position.
     */
    public int getX() {
    	//xPosition = rand.nextInt(xLimit/2);
        return xPosition;
    }

    /**
     * @return The balls Y position.
     */
    public int getY() {
    	
        return yPosition;
    }
    
    /**
     * Tells the ball to start moving. This is done by starting a Timer
     * that periodically executes a TimerTask. The TimerTask then tells
     * the ball to make one "step."
     */
    public void start() {
        timer = new Timer(true);
        timer.schedule(new Strobe(), 0, timerSpeed); // 25 times a second        
    }
    
  
    
    public void reduceSpeed() {
        timerSpeed = timerSpeed + 5; // 25 times a second   
        timer.schedule(new Strobe(), 0, timerSpeed); // 25 times a second        

    }
    
    public void speedUp() {
        timerSpeed = timerSpeed- 5; // 25 times a second  
        timer.schedule(new Strobe(), 0, timerSpeed); // 25 times a second        

    }
    
    public void increase() {
        timer = new Timer(true);
       ; // 25 times a second        
    }
    
    /**
     * Tells the ball to stop where it is.
     */
    public void pause() {
        timer.cancel();
    }
    
    /**
     * Tells the ball to advance one step in the direction that it is moving.
     * If it hits a wall, its direction of movement changes.
     */
    public void makeOneStep() {
        // Do the work
        xPosition += xDelta;
        if (xPosition < 0 || xPosition >= xLimit) {
            xDelta = -xDelta;
            xPosition += xDelta;
        }
        yPosition += yDelta;
        if (yPosition < 0 || yPosition >= yLimit) {
            yDelta = -yDelta;
            yPosition += yDelta;
        }
        // Notify observers
        setChanged();
        notifyObservers();
    }
    
    /**
     * Tells the model to advance one "step."
     */
    private class Strobe extends TimerTask {
        @Override
        public void run() {
            makeOneStep();
        }
    }
    

}