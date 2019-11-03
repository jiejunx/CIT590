/**
 * This is an example of the basic "Bouncing Ball" animation, making
 * use of the Model-View-Controller design pattern and the Timer and
 * Observer/Observable classes.
 */
package kaleidoscope;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;









/**
 * The Controller sets up the GUI and handles all the controls (buttons,
 * menu items, etc.)
 * 
 * @author David Matuszek
 * @author <Your name goes here>
 * @author <Your name goes here>
 */
public class Controller extends JFrame {
    JPanel buttonPanel = new JPanel();
   // buttonPanel.set
   
    JButton runButton = new JButton("Run");
    JButton stopButton = new JButton("Stop");
    JButton increaseSpeed = new JButton("IncreaseSpeed");
    JButton reduceSpeed = new JButton("reduceSpeed");
    JButton addFigure = new JButton("AddFigure");
    JButton removeFigure = new JButton("removeFigure");
    
    JComboBox comboBox = new JComboBox();
    JComboBox comboBox2 = new JComboBox();

    JTextField field = new JTextField();


    JButton Clearing = new JButton("Clearing");
    JButton restarting = new JButton("Restarting");
    JButton color = new JButton("ChangeColor");
    
    Timer timer;
    private int timerSpeed = 50;
    /** The Model is the object that does all the computations. It is
     * completely independent of the Controller and View objects. */
    Model model;
    
    /** The View object displays what is happening in the Model. */
    View view;
    private ArrayList<Model> models;
    
    /**
     * Runs the bouncing ball program.
     * @param args Ignored.
     */
    public static void main(String[] args) {
        Controller c = new Controller();
       // ArrayList<Model> listOfModels = new ArrayList<Model>();     // The model is independent from the other classes
        //model = new Model();
        c.init();
        c.display();
    }

    /**
     * Sets up communication between the components.
     *
    /*
 
*/
    /**
     * Sets up communication between the components.
     */
    private void init() {
        ArrayList<Model> listOfModels = new ArrayList<Model>();     // The model is independent from the other classes
        listOfModels.add(new Model(800,1000));
        listOfModels.add(new Model(800,1000));
        listOfModels.add(new Model(800,1000));
        listOfModels.add(new Model(800,1000));
        listOfModels.add(new Model(800,1000));


        this.models = listOfModels;
   
           // The model is independent from the other classes

        view = new View(models);  // The view needs to know what model to look at 
        // The model needs to give permission to be observed
        for (Model model: models){
        	model.addObserver(view);
        }

        view.addFigures(listOfModels);
    	
    }
    
 
    /**
     * Displays the GUI.
     */
    private void display() {
        layOutComponents();
        attachListenersToComponents();
        setSize(1000, 800);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    /*
    public ArrayList<Model> fourReflect(){
    	
    	ArrayList<Model> listnew;
        for (Model model: models){    	
        	model.fourReflect();	
        	listnew.add(model);
        }
        return listnew;
    }*/
    
   
    
    public void reduceSpeed() {
        for (int i = 0; i < models.size(); i++){
        	models.get(i).reduceSpeed();
        }      
}
    
    public void speedUp() {
        for (int i = 0; i < models.size(); i++){
        	models.get(i).speedUp();
        }          
}
    
    
    public void start(){
    	//if (models==null)
   	 for (Model model : models){
        	model.start();
   	 } 	 
    }
    
    public void startNull(){
    	  timer = new Timer(true);
    	
    }
    
    
    public void pause(){
      	 for (Model model : models){
           	model.pause();
      	 } 	 
       }
    
    public void remove(){
   	if(models.size() > 1){
		models.remove(models.size() - 1); // pops last item in models ArrayList and removes it from the screen
	}
	if (models.size() <= 1) removeFigure.setEnabled(false); // prevents user from removing all figures
}
    
    
    
    /**
     * Arranges the various components in the GUI.
     */
    private void layOutComponents() {
        setLayout(new BorderLayout());
        this.add(BorderLayout.SOUTH, buttonPanel);
        buttonPanel.setLayout(new GridLayout(2,6));
        buttonPanel.add(runButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(increaseSpeed);
        buttonPanel.add(reduceSpeed);
        buttonPanel.add(addFigure);
        buttonPanel.add(removeFigure);  
       // buttonPanel.add(numberFigure);
        buttonPanel.add(new JLabel("number of Figure:"));
        buttonPanel.add(comboBox);
        


        comboBox.addItem("1 Figure");
        comboBox.addItem("2 Figure");
        comboBox.addItem("4 Figure");
        comboBox.addItem("8 Figure");

        
        buttonPanel.add(new JLabel("number of reflections:"),comboBox2);
        comboBox2.addItem("4 reflections");
        comboBox2.addItem("8 reflections");

        buttonPanel.add(comboBox2);

        buttonPanel.add(Clearing);
        buttonPanel.add(restarting);  
        buttonPanel.add(color);  
       
        addFigure.setEnabled(false);
        removeFigure.setEnabled(false);
        stopButton.setEnabled(false);
        increaseSpeed.setEnabled(false);
        reduceSpeed.setEnabled(false);
        comboBox.setVisible(false);
        comboBox2.setVisible(false);

        this.add(BorderLayout.CENTER, view);
    }
    
    
    
    /**
     * Attaches listeners to the components, and schedules a Timer.
     */
    private void attachListenersToComponents() {
        // The Run button tells the Model to start
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                runButton.setEnabled(false);
                stopButton.setEnabled(true);
                increaseSpeed.setEnabled(true);
                reduceSpeed.setEnabled(true);      
                addFigure.setEnabled(true);    
                removeFigure.setEnabled(true);
                comboBox.setVisible(true);
                comboBox2.setVisible(true);

                start();
            }
        });
        
        
        // The Stop button tells the Model to pause
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                runButton.setEnabled(true);
                stopButton.setEnabled(false);
                reduceSpeed.setEnabled(false);
                increaseSpeed.setEnabled(false);
                addFigure.setEnabled(false);  
                removeFigure.setEnabled(false); 
                comboBox.setVisible(false);
                comboBox2.setVisible(false);
                Clearing.setEnabled(false); 
                restarting.setEnabled(false); 
                color.setEnabled(false); 

                pause();
                }
            }
        );
        
        increaseSpeed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                runButton.setEnabled(false);
                stopButton.setEnabled(true);
                reduceSpeed.setEnabled(true);
                increaseSpeed.setEnabled(true);
                addFigure.setEnabled(true);   
                removeFigure.setEnabled(true);  
                speedUp();
                pause();//why??
                start(); 
            }
        });
        
        reduceSpeed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	
                runButton.setEnabled(false);
                stopButton.setEnabled(true);
                reduceSpeed.setEnabled(true);
                increaseSpeed.setEnabled(true);              
                addFigure.setEnabled(true); 
                removeFigure.setEnabled(true);
                reduceSpeed();
                pause();//why??
                start();
            }
        });
        
        
        
        
        addFigure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	
                runButton.setEnabled(false);
                stopButton.setEnabled(true);
                reduceSpeed.setEnabled(true);
                increaseSpeed.setEnabled(true);              
                addFigure.setEnabled(true); 
                removeFigure.setEnabled(true);
                //reduceSpeed();
                pause();//why??
                models.add(new Model(view.getWidth(), view.getHeight()));
                start();
            }
        });
      
        removeFigure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	
                runButton.setEnabled(false);
                stopButton.setEnabled(true);
                reduceSpeed.setEnabled(true);
                increaseSpeed.setEnabled(true);              
                addFigure.setEnabled(true); 
                removeFigure.setEnabled(true);
                addFigure.setEnabled(true); 
                //reduceSpeed();
                pause();//why??
                if(models.size()>1)models.remove(models.size() - 1);
                else{removeFigure.setEnabled(false);}

               // models.add(new Model(view.getWidth(), view.getHeight()));
              //  models.
                start();
            }
        });
        
        
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //
                // Get the source of the component, which is our combo
                // box.
                //
                JComboBox comboBox = (JComboBox) event.getSource();

                Object selected = comboBox.getSelectedItem();
                pause();//why??
                if(selected.toString().equals("1 Figure")){
                	while(models.size()>1){
                		models.remove(models.size() - 1);
                	}
                }
                else if(selected.toString().equals("2 Figure")){
                	while(models.size()>2){
                		models.remove(models.size() - 1);
                	}              	
                	while(models.size() < 2){
                		models.add(new Model(view.getWidth(), view.getHeight()));
                	}
                }
                
                else if(selected.toString().equals("4 Figure")){
                	while(models.size() > 4){
                		models.remove(models.size() - 1);
                	}              	
                	while(models.size() < 4){
                		models.add(new Model(view.getWidth(), view.getHeight()));
                	}
                }
                
                
                else if(selected.toString().equals("8 Figure")){
                	while(models.size() > 8){
                		models.remove(models.size() - 1);
                	}              	
                	while(models.size() < 8){
                		models.add(new Model(view.getWidth(), view.getHeight()));
                	}
                }
                start();
            }
        });
    
           
        
        
        comboBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
              
                JComboBox comboBox = (JComboBox) event.getSource();

                Object selected = comboBox.getSelectedItem();
                pause();//why??
                if(selected.toString().equals("4 reflections")){
                    for (Model model: models){    	
                    	model.fourReflect();
                }
             
                }
                
                
                
                if(selected.toString().equals("8 reflections")){
                    for (Model model: models){    	
                    	model.eightReflect();
                }}
                start();
            }
        });
     
      
        
       
        
        Clearing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {         	
                runButton.setEnabled(false);
                stopButton.setEnabled(true);
                reduceSpeed.setEnabled(true);
                increaseSpeed.setEnabled(true);              
                addFigure.setEnabled(true); 
                removeFigure.setEnabled(true);
                //reduceSpeed();
            
            	
                while(models.size() >= 1){
            		models.remove(models.size() - 1);
            	}  
                pause();//why??

                start();
            }
        });
        
        restarting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {         	
                runButton.setEnabled(false);
                stopButton.setEnabled(true);
                reduceSpeed.setEnabled(true);
                increaseSpeed.setEnabled(true);              
                addFigure.setEnabled(true); 
                removeFigure.setEnabled(true);
                //reduceSpeed();
               // pause();//why??

                while(models.size() >= 1){
            		models.remove(models.size() - 1);
            	}  
                
                pause();//why??

                models.add(new Model(view.getWidth(), view.getHeight()));
                models.add(new Model(view.getWidth(), view.getHeight()));
                models.add(new Model(view.getWidth(), view.getHeight()));

                start();               
            }
        });
        
        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {         	
                runButton.setEnabled(false);
                stopButton.setEnabled(true);
                reduceSpeed.setEnabled(true);
                increaseSpeed.setEnabled(true);              
                addFigure.setEnabled(true); 
                removeFigure.setEnabled(true);
                //reduceSpeed();
                pause();//why??
                for (Model model: models){    	
                	model.creatcolor();
                	model.setcolor();
            }                
                start();
            }
        });
        
        
        
        // When the window is resized, the Model is given the new limits
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
        
            	for (int i = 0;  i < models.size(); i++){
            		models.get(i).setLimits(view.getWidth(), view.getHeight());
            		
            }}}); }}   
   

