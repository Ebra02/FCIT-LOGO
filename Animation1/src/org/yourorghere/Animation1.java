package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * SimpleJOGL2.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel)
 * <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Animation1 implements GLEventListener {
    boolean firsttrigger = true;
    int countertrigger = 0;
    
    boolean secondtrigger = false;
    
    float housetrans = -15f; 
    double housrotation = 45;
    double housrotationafter = 0;
    boolean uprotatehouse = false;
    boolean uphouse = false;
    boolean rotatehouseafter;
    boolean rotatehouseafterforward;
    int rotatehousecounter = 0;
    boolean rotatehousecounterforward = false;
    int rotatehouseafterrange = 3;
    
    float firstsquareY = -20;
    boolean upfirstsquare = true; 
    
    float secondsquareY = -18;
    float secondsquareX = 13;
    double secondsqaurerotat = 30;
    boolean upsecondsquareY = false;
    boolean upsecondsquareX = false;
    boolean secondrotation;
    
    float thirdsquareY = -20;
    float thirdsquareX = 15;
    boolean upthirdsquareY = false;
    boolean upthirdsquareX = false;
    
    float fourthsquareY = -20;
    float fourthsquareX = 17;
    double fourthsquarerotat = -30;
    boolean upfourthsquareY = false;
    boolean upfourthsquareX = false;
    boolean fourthrotation;
    
    float fifthsquareY = -18;
    float fifthsquareX = 18;
    double fifthsquarerotat = -30;
    boolean upfifthsquareY = false;
    boolean upfifthsquareX = false;
    boolean fifthrotation;
    
    float sixthsquareY = -19.5f;
    boolean upsixthsquare = false; 
    
    //Curved Window color
    float CurvedWindowR = 0.30f;
    float CurvedWindowG = 0.30f;
    float CurvedWindowB = 1f;
    
    //TriSmoothSquares color
    float TriSmoothSquaresR = 0.30f;
    float TriSmoothSquaresG = 0.30f;
    float TriSmoothSquaresB = 1f;
    
    //left side animation
    float trans = -30f;
    boolean transleft = false;
    boolean transstop = true;
    
    
    //TriSmoothSquares reflect color
    float TriSmoothSquaresreflectR = 0.30f;
    float TriSmoothSquaresreflectG = 0.30f;
    float TriSmoothSquaresreflectB = 1f;
    boolean leftcolor;
    
    //light off
    float lightRGB = 0f;
    boolean lightRGBon = false;
    
    //light on
    float posX = 8.4f;
    float posXreflect = -10.8f;
    
    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Animation1());
        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!

            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 100.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
//---------------------------------------------------------------------------------------------------------------------------
        //BackGround
        gl.glTranslatef(0.0f, 0.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(-50.0f, 50.0f, 0.0f);  // Top Left
        gl.glVertex3f(50.0f, 50.0f, 0.0f);   // Top Right
        gl.glVertex3f(50.0f, -50.0f, 0.0f);  // Bottom Right
        gl.glVertex3f(-50.0f, -50.0f, 0.0f); // Bottom Left

        gl.glEnd();
        gl.glLoadIdentity();
//---------------------------------------------------------------------------------------------------------------------------
//      #2
//        gl.glTranslatef(9.7f, 2.5f, -50.0f);
//        DrawSmootSquare(drawable);
        
        gl.glTranslatef(secondsquareX, secondsquareY, -50.0f);
        gl.glRotated(secondsqaurerotat, 0, 0, 1);
        DrawSmootSquare(drawable);

//------------------------------------------------------------------------------------------------------------------------------
//      #8
        gl.glTranslatef(-12.0f, 2.5f, -50.0f);
        gl.glTranslatef(trans, 0f, 0f);
        DrawSmootSquare(drawable);


//------------------------------------------------------------------------------------------------------------------------------
//      #4
//        gl.glTranslatef(15.5f, 5.8f, -50.0f);
//        DrawSmootSquare(drawable);
        
        gl.glTranslatef(fourthsquareX, fourthsquareY, -50.0f);
        gl.glRotated(fourthsquarerotat, 0, 0, 1);
        DrawSmootSquare(drawable);

//-----------------------------------------------------------------------------------------------------------------------------
//      #10
        gl.glTranslatef(-17.8f, 5.8f, -50.0f);
        gl.glTranslatef(trans, 0f, 0f);
        DrawSmootSquare(drawable);
        

//-----------------------------------------------------------------------------------------------------------------------------
//      #3
//        gl.glTranslatef(9.7f, -5.0f, -50.0f);
//        DrawSmootSquare(drawable);
        
        gl.glTranslatef(thirdsquareX, thirdsquareY, -50.0f);
        DrawSmootSquare(drawable);

//------------------------------------------------------------------------------------------------------------------------------
//      #9
        gl.glTranslatef(-12.0f, -5.0f, -50.0f);
        gl.glTranslatef(trans, 0f, 0f);
        DrawSmootSquare(drawable);

//------------------------------------------------------------------------------------------------------------------------------
//      #5
//        gl.glTranslatef(15.5f, -1.3f, -50.0f);
//        DrawSmootSquare(drawable);
        
        gl.glTranslatef(fifthsquareX, fifthsquareY, -50.0f);
        gl.glRotated(fifthsquarerotat, 0, 0, 1);
        DrawSmootSquare(drawable);

//----------------------------------------------------------------------------------------------------------------------
//      #11
        gl.glTranslatef(-17.8f, -1.3f, -50.0f);
        gl.glTranslatef(trans, 0f, 0f);
        DrawSmootSquare(drawable);
        
//----------------------------------------------------------------------------------------------------------------------

//      #1
//        gl.glTranslatef(9.7f, 9.5f, -50.0f);
//        DrawSmootSquare(drawable);
        
        gl.glTranslatef(9.7f, firstsquareY, -50.0f);
        DrawSmootSquare(drawable);

//----------------------------------------------------------------------------------------------------------------------------------------
//      #7
        gl.glTranslatef(-12.0f, 9.5f, -50.0f);
        gl.glTranslatef(trans, 0f, 0f);
        DrawSmootSquare(drawable);
        
//----------------------------------------------------------------------------------------------------------------------------------------

//      #6
//        gl.glTranslatef(21.2f, 2.3f, -50.0f);
//        DrawSmootSquare(drawable);
        
        gl.glTranslatef(21.2f, sixthsquareY, -50.0f);
        DrawSmootSquare(drawable);

//------------------------------------------------------------------------------------------------------------------------------------
//      #12
        gl.glTranslatef(-23.5f, 2.3f, -50.0f);
        gl.glTranslatef(trans, 0f, 0f);
        DrawSmootSquare(drawable);
        
//------------------------------------------------------------------------------------------------------------------------------------

//      #14
        gl.glTranslatef(9.7f, -1.3f, -50.0f);
        DrawTriSmoothSquares(drawable);
        
//------------------------------------------------------------------------------------------------------------------------------
//      #19
        gl.glTranslatef(-12.0f, -1.3f, -50.0f);
        DrawReflectedTriSquares(drawable);

//------------------------------------------------------------------------------------------------------------------------------
//      #15

        gl.glTranslatef(15.5f, 2.3f, -50.0f);
        DrawTriSmoothSquares(drawable);
        
//---------------------------------------------------------------------------------------------------------------------------------------
//      #20

        gl.glTranslatef(-17.8f, 2.3f, -50.0f);
        DrawReflectedTriSquares(drawable);
  
//---------------------------------------------------------------------------------------------------------------------------------------
//      #13
        gl.glTranslatef(9.7f, 6.0f, -50.0f);
        DrawTriSmoothSquares(drawable);
        
//-------------------------------------------------------------------------------------------------------------------------------------------
//      #18
        gl.glTranslatef(-12.0f, 6.0f, -50.0f);
        DrawReflectedTriSquares(drawable);

//-------------------------------------------------------------------------------------------------------------------------------------------
        //Lines 
//-------------------------------------------------------------------------------------------------------------------------------------------

//      Line #1

        gl.glTranslatef(10.2f, 6.0f, -50.0f);
        gl.glRotatef(45.0f, 0.0f, 0.0f, -1.0f);
        DrawConnectedLines(drawable);
//-------------------------------------------------------------------------------------------------------------------------------------------
//      reflected Line #1

        gl.glTranslatef(-13.5f, 6.0f, -50.0f);
        gl.glRotatef(135.0f, 0.0f, 0.0f, -1.0f);
        DrawReflectedLines(drawable);
        

//-------------------------------------------------------------------------------------------------------------------------------------------
//      Line #2
        gl.glTranslatef(13.7f, 2.0f, -50.0f);
        gl.glRotatef(135.0f, 0.0f, 0.0f, -1.0f);
        DrawConnectedLines(drawable);
//------------------------------------------------------------------------------------------------------------------------------------------
//      reflected Line #2
        
        gl.glTranslatef(-17.0f, 2.0f, -50.0f);
        gl.glRotatef(45.0f, 0.0f, 0.0f, -1.0f);
        DrawReflectedLines(drawable);

//-------------------------------------------------------------------------------------------------------------------------------------------
//      #16
        gl.glTranslatef(15.5f, -5.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        DrawTriSmoothSquares(drawable);

//-------------------------------------------------------------------------------------------------------------------------------------------
//      #21

        gl.glTranslatef(-17.8f, -5.0f, -50.0f);
        DrawReflectedTriSquares(drawable);
        
//-------------------------------------------------------------------------------------------------------------------------------------------
//      #17
        gl.glTranslatef(21.2f, -1.3f, -50.0f);
        DrawTriSmoothSquares(drawable);

//---------------------------------------------------------------------------------------------------------------------------------------------
//      #22
        gl.glTranslatef(-23.5f, -1.3f, -50.0f);
        DrawReflectedTriSquares(drawable);

//---------------------------------------------------------------------------------------------------------------------------------------------   
//      Line #3

        gl.glTranslatef(18.9f, -2.0f, -50.0f);
        gl.glRotatef(133.0f, 0.0f, 0.0f, -1.0f);
        DrawConnectedLines(drawable);
//----------------------------------------------------------------------------------------------------------------------------------------------
//      reflected Line #3

        gl.glTranslatef(-22.2f, -2.0f, -50.0f);
        gl.glRotatef(47.0f, 0.0f, 0.0f, -1.0f);
        DrawReflectedLines(drawable);

//----------------------------------------------------------------------------------------------------------------------------------------------
//      Curved Window

        gl.glTranslatef(2f, 9.0f, -50.0f);
        DrawCurvedWindow(drawable);

//----------------------------------------------------------------------------------------------------------------------------------------------
//      refelcted window

        gl.glTranslatef(-4.7f, 9.0f, -50.0f);
        DrawReflectedCurvedWindow(drawable);
        
//----------------------------------------------------------------------------------------------------------------------------------------------
        
////      Draw Small Window Above the house
//        gl.glTranslatef(2f, 9.0f, -50.0f);
//        SmallWindow(drawable);
//        
//        //refelct
//        gl.glTranslatef(-3.8f, 9.0f, -50.0f);
//        reflectedSmallWindow(drawable);
        
//      Draw Small Window Above the house
        gl.glTranslatef(2f, housetrans, -50.0f);
        gl.glRotated(housrotation, 1, 0, 0);
        gl.glRotated(housrotationafter, 1, 0, 0);
        SmallWindow(drawable);
        
        //refelct
        gl.glTranslatef(-3.8f, housetrans, -50.0f);
        gl.glRotated(housrotation, 1, 0, 0);
        gl.glRotated(housrotationafter, 1, 0, 0);
        reflectedSmallWindow(drawable);
        
        
//--------------------------------------TOP OF HOUSE-------------------------------------------------------
////      Draw Light house Window

        
//      Draw Light house Window
        gl.glTranslatef(2f, housetrans, -50.0f);
        gl.glRotated(housrotation, 1, 0, 0);
        gl.glRotated(housrotationafter, 1, 0, 0);
        lightHouseWindow(drawable);

        //refelct
        gl.glTranslatef(-4.4f, housetrans, -50.0f);
        gl.glRotated(housrotation, 1, 0, 0);
        gl.glRotated(housrotationafter, 1, 0, 0);
        lightHouseReflectedWindow(drawable);
        
//---------------------------------------------------------------------------------------------------------

//        //Draw Parallelogram inside on top of house

        
        //Draw Parallelogram inside on top of house
        gl.glTranslatef(2f, housetrans, -50.0f);
        gl.glRotated(housrotation, 1, 0, 0);
        gl.glRotated(housrotationafter, 1, 0, 0);
        windowGlass(drawable);


        gl.glTranslatef(-4.4f, housetrans, -50.0f);
        gl.glRotated(housrotation, 1, 0, 0);
        gl.glRotated(housrotationafter, 1, 0, 0);
        reflectedWindowGlass(drawable);
        
        //Draw Light Bulb (OUTER)
        gl.glTranslatef(2.5f, housetrans, -50.0f);
        gl.glRotated(housrotation, 1, 0, 0);
        gl.glRotated(housrotationafter, 1, 0, 0);
        lightBulb(drawable);

        //refelct
        gl.glTranslatef(-4.7f, housetrans, -50.0f);
        gl.glRotated(housrotation, 1, 0, 0);
        gl.glRotated(housrotationafter, 1, 0, 0);
        reflectedLightBulb(drawable);

              
//--------------------------------------LIGHT RAYS------------------------------------------------
        //Draw Light ray 1------------------------------
        gl.glTranslatef(3f, 9.0f, -50.0f);
        lightRay1(drawable);
        

        //refelct
        gl.glTranslatef(-5.3f, 9.0f, -50.0f);
        reflectedLightRay1(drawable);


        //Draw Light ray 2-----------------------------------
        gl.glTranslatef(3f, 9.0f, -50.0f);
        lightRay2(drawable);

        //refelct
        gl.glTranslatef(-5.3f, 9.0f, -50.0f);
        reflectedLightRay2(drawable);

        //Draw Light ray 3---------------------------
        gl.glTranslatef(3f, 9.0f, -50.0f);
        lightRay3(drawable);

        //refelct
        gl.glTranslatef(-5.3f, 9.0f, -50.0f);
        reflectedLightRay3(drawable);
        
        //cover
        gl.glTranslatef(posX, 15.4f, -50.0f);
        gl.glScaled(3, 2, 1);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(1f, 1f, 1f);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();
        gl.glLoadIdentity();
        
        //cover reflection
        gl.glTranslatef(posXreflect, 15.4f, -50.0f);
        gl.glScaled(3, 2, 1);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(1f, 1f, 1f);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();
        gl.glLoadIdentity();
        
//-----------------------------------HOUSE-------------------------------------------------------------
//        //Draw House

        
        //Draw House
        gl.glTranslatef(2f, housetrans, -50.0f);
        gl.glRotated(housrotation, 1, 0, 0);
        gl.glRotated(housrotationafter, 1, 0, 0);
        house(drawable);


        //refelcted House
        gl.glTranslatef(-4.3f, housetrans, -50.0f);
        gl.glRotated(housrotation, 1, 0, 0);
        gl.glRotated(housrotationafter, 1, 0, 0);
        reflectedHouse(drawable);
        
//--------------------------------------INSIDE HOUSE-----------------------------------------------
////      Small Window Inside House

        
//      Small Window Inside House
        gl.glTranslatef(2f, housetrans, -50.0f);
        gl.glRotated(housrotation, 1, 0, 0);
        gl.glRotated(housrotationafter, 1, 0, 0);
        houseWindow(drawable);

        //reflect
        gl.glTranslatef(-4.4f, housetrans, -50.0f);
        gl.glRotated(housrotation, 1, 0, 0);
        gl.glRotated(housrotationafter, 1, 0, 0);
        reflectedHouseWindow(drawable);


//      Small Window Inside House
        gl.glTranslatef(2f, housetrans, -50.0f);
        gl.glRotated(housrotation, 1, 0, 0);
        gl.glRotated(housrotationafter, 1, 0, 0);
        houseWindow2(drawable);


//      reflect
        gl.glTranslatef(-4.4f, housetrans, -50.0f);
        gl.glRotated(housrotation, 1, 0, 0);
        gl.glRotated(housrotationafter, 1, 0, 0);
        reflectedHouseWindow2(drawable);


//      Draw Feather
        gl.glTranslatef(2f, housetrans, -50.0f);
        gl.glRotated(housrotation, 1, 0, 0);
        gl.glRotated(housrotationafter, 1, 0, 0);
        feather(drawable);

//      Reflect Feather
        gl.glTranslatef(-4.3f, housetrans, -50.0f);
        gl.glRotated(housrotation, 1, 0, 0);
        gl.glRotated(housrotationafter, 1, 0, 0);
        reflectedFeather(drawable);
        
//-----------------------------------------------------------------------------------------------------------------
        //First tringger
        if (firsttrigger) {
            
            countertrigger++;
            if (countertrigger >= 200) {
                CurvedWindowR = 0.760784f;
                CurvedWindowG = 0.937255f;
                CurvedWindowB = 0.560784f;
                firsttrigger =false;
            }
        }
        
        //House animation
        if (firsttrigger == false) {
            
        
        if (uprotatehouse) {
            
        }else
            housrotation-=0.3;
        if (housrotation <= 0) {
            uprotatehouse = true;
        }
        
        
        
        if (uphouse) {
            
        }else if (housrotation < 20) {
            
            housetrans+= 0.2f;
        }
            
        if (housetrans >= 9.0f) {
            uphouse = true;
            rotatehouseafter = true;
        }
      
        
        if (rotatehouseafter) {
            if (rotatehousecounterforward) {
                
            }else if (housrotationafter > rotatehouseafterrange) {
            rotatehouseafterforward = false;
            
            }else if (housrotationafter < -rotatehouseafterrange) {
            rotatehouseafterforward = true;
            }
            if (rotatehousecounterforward) {
                
            }else if (rotatehouseafterforward) {
            rotatehousecounter+=1;    
            housrotationafter+=1;
                }else  {
            rotatehousecounter+=1;
            housrotationafter-=1;
            }
        }
        
        
            
        if (rotatehousecounter >= 60 && housrotationafter == 0 ) {
            rotatehousecounterforward = true;
            secondtrigger = true;
        }
        }
//-------------------------------------------------------------------------------------------------------------------------------------------
        //second trigger
        if (secondtrigger) {
            TriSmoothSquaresR = 0.32f;
            TriSmoothSquaresG = 0.49f;
            TriSmoothSquaresB = 0.46f;
            secondtrigger = true;
        }
        
        //Square animation
        
        
        //First square animation
        if (secondtrigger) {
            if (upfirstsquare) {
            firstsquareY+=0.2;
            if (firstsquareY >= 9.5) {
                upfirstsquare = false;
                upthirdsquareY = true;
                upthirdsquareX = true;
            }
        }
        }
        
        //Second square animation
        if (upsecondsquareY) {
            secondsquareY+=0.2;
            if (secondsquareY >= 2.5) {
                upsecondsquareY = false;
                secondrotation = true;
                
            }
        
        }
        if (upsecondsquareX) {
            secondsquareX-=0.070;
            if (secondsquareX <= 9.7) {
                upsecondsquareX = false;
                
                
            }
        }
        if (secondrotation) {
            secondsqaurerotat+=1;
            if (secondsqaurerotat >= 360) {
                secondrotation = false;
                leftcolor = true;
            }
        }
        
        //3rd square animation
        if (upthirdsquareY) {
            thirdsquareY+=0.2;
            if (thirdsquareY >= -5) {
                upthirdsquareY = false;
                upsixthsquare = true;
            }
        
        }
        if (upthirdsquareX) {
            thirdsquareX-=0.070;
            if (thirdsquareX <= 9.7) {
                upthirdsquareX = false;
                
            }
        }
        
        //Fourth square animation
        if (upfourthsquareY) {
            fourthsquareY+=0.2;
            if (fourthsquareY >= 5.8) {
                upfourthsquareY = false;
                fourthrotation = true;
                upsecondsquareX = true;
                upsecondsquareY = true;
            }
        
        }
        if (upfourthsquareX) {
            fourthsquareX-=0.070;
            if (fourthsquareX <= 15.5) {
                upfourthsquareX = false;
                
                
            }
        }
        if (fourthrotation) {
            fourthsquarerotat-=1;
            if (fourthsquarerotat <= -360) {
                fourthrotation = false;
            }
        }
        
        //Fifth square animation
        if (upfifthsquareY) {
            fifthsquareY+=0.2;
            if (fifthsquareY >= -1.3) {
                upfifthsquareY = false;
                fifthrotation = true;
                upfourthsquareX = true;
                upfourthsquareY = true;
            }
        
        }
        if (upfifthsquareX) {
            fifthsquareX-=0.070;
            if (fifthsquareX <= 15.5) {
                upfifthsquareX = false;
                
            }
        }
        if (fifthrotation) {
            fifthsquarerotat-=1;
            if (fifthsquarerotat <= -360) {
                fifthrotation = false;
            }
        }
        
        //Sixth square animation
        if (upsixthsquare) {
            sixthsquareY+=0.2;
            if (sixthsquareY >= 2.3) {
                upsixthsquare = false;
                upfifthsquareX = true;
                upfifthsquareY = true;
            }
        
        }
        
        // left side animation
        if (leftcolor) {
            TriSmoothSquaresreflectR = 0.184314f;
            TriSmoothSquaresreflectG = 0.309804f;
            TriSmoothSquaresreflectB = 0.184314f;
            transleft = true;
        }
        if (transleft) {
            if (transstop) {
            trans+= 0.5;
            if (trans >= 0) {
                transstop = false;
                lightRGBon = true;
            }
        }
        }
        if (lightRGBon) {
            lightRGB = 1f;
            posX+=0.03;
            posXreflect-=0.03;
            if (posX >= 20 && posXreflect <= 20) {
                lightRGBon = false;
            }
        }
        

        // Flush all drawing operations to the graphics card
        gl.glFlush();

    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    private void glLoadIdentity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void DrawPartOfCircle(float xc, float yc, float r, float part, GL gl) {
//        gl.glColor3d(0.560784f,0.737255f, 0.560784f );
        gl.glTranslatef(xc, yc, 0.0f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2d(0, 0);
        for (float theta = 0; theta <= part * 2 * PI; theta += 1.0f / 100 * r) {
            float x = r * (float) cos(theta);
            float y = r * (float) sin(theta);
            gl.glVertex2f(x, y);
        }
        gl.glEnd();
    }
    
    public void DrawSmootSquare(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.760784f, 0.937255f, 0.560784f);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.760784f, 0.937255f, 0.560784f);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.6f, 0.8f, 0.0f);  // Top Left
        gl.glVertex3f(2.6f, 0.8f, 0.0f);   // Top Right
        gl.glVertex3f(2.6f, -0.8f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.6f, -0.8f, 0.0f); // Bottom Left

        gl.glEnd();

        DrawPartOfCircle(2.0f, 0.8f, 0.60f, 0.25f, gl);
        gl.glTranslatef(-2.0f, 0.8f, 0.0f);
        gl.glRotatef(90, 0, 0, 1);
        DrawPartOfCircle(-0.8f, 2.0f, 0.60f, 0.25f, gl);

        gl.glRotatef(90, 0, 0, 1);
        gl.glTranslatef(2.0f, -0.8f, 0.0f);
        DrawPartOfCircle(-2.0f, 2.4f, 0.60f, 0.25f, gl);
        gl.glRotatef(90, 0, 0, 1);
        DrawPartOfCircle(-0.0f, 4.0f, 0.60f, 0.25f, gl);
        gl.glLoadIdentity();
        
    }
    
    public void DrawTriSmoothSquares(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(TriSmoothSquaresR, TriSmoothSquaresG, TriSmoothSquaresB);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(TriSmoothSquaresR, TriSmoothSquaresG, TriSmoothSquaresB);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.6f, 0.8f, 0.0f);  // Top Left
        gl.glVertex3f(2.6f, 0.8f, 0.0f);   // Top Right
        gl.glVertex3f(2.6f, -0.8f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.6f, -0.8f, 0.0f); // Bottom Left

        gl.glEnd();

        DrawPartOfCircle(2.0f, 0.8f, 0.60f, 0.25f, gl);
        gl.glTranslatef(-2.0f, 0.8f, 0.0f);
        gl.glRotatef(90, 0, 0, 1);
        DrawPartOfCircle(-0.8f, 2.0f, 0.60f, 0.25f, gl);

        gl.glRotatef(90, 0, 0, 1);
        gl.glTranslatef(2.0f, -0.8f, 0.0f);
        DrawPartOfCircle(-2.0f, 2.4f, 0.60f, 0.25f, gl);
        gl.glRotatef(90, 0, 0, 1);
        DrawPartOfCircle(-0.0f, 4.0f, 0.60f, 0.25f, gl);
        gl.glLoadIdentity();
    }
    
    public void DrawReflectedTriSquares(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(TriSmoothSquaresreflectR, TriSmoothSquaresreflectG, TriSmoothSquaresreflectB);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(TriSmoothSquaresreflectR, TriSmoothSquaresreflectG, TriSmoothSquaresreflectB);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.6f, 0.8f, 0.0f);  // Top Left
        gl.glVertex3f(2.6f, 0.8f, 0.0f);   // Top Right
        gl.glVertex3f(2.6f, -0.8f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.6f, -0.8f, 0.0f); // Bottom Left

        gl.glEnd();

        DrawPartOfCircle(2.0f, 0.8f, 0.60f, 0.25f, gl);
        gl.glTranslatef(-2.0f, 0.8f, 0.0f);
        gl.glRotatef(90, 0, 0, 1);
        DrawPartOfCircle(-0.8f, 2.0f, 0.60f, 0.25f, gl);

        gl.glRotatef(90, 0, 0, 1);
        gl.glTranslatef(2.0f, -0.8f, 0.0f);
        DrawPartOfCircle(-2.0f, 2.4f, 0.60f, 0.25f, gl);
        gl.glRotatef(90, 0, 0, 1);
        DrawPartOfCircle(-0.0f, 4.0f, 0.60f, 0.25f, gl);
        gl.glLoadIdentity();
    }
    
    public void DrawConnectedLines(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(TriSmoothSquaresR, TriSmoothSquaresG, TriSmoothSquaresB);    // Set the current drawing color to light blue
        gl.glVertex3f(0.0f, 0.0f, 0.0f);  //73
        gl.glVertex3f(5.0f, 0.0f, 0.0f);  //72
        gl.glVertex3f(5.0f, 0.6f, 0.0f); //95
        gl.glVertex3f(0.0f, 0.6f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void DrawReflectedLines(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(TriSmoothSquaresreflectR, TriSmoothSquaresreflectG, TriSmoothSquaresreflectB);    // Set the current drawing color to light blue
        gl.glVertex3f(0.0f, 0.0f, 0.0f);  //73
        gl.glVertex3f(5.0f, 0.0f, 0.0f);  //72
        gl.glVertex3f(5.0f, 0.6f, 0.0f); //95
        gl.glVertex3f(0.0f, 0.6f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void DrawCurvedWindow(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(CurvedWindowR, CurvedWindowG, CurvedWindowB);    // Set the current drawing color to light blue
        gl.glVertex3f(0.4f, 0.4f, 0.0f);  //bottom left
        gl.glVertex3f(0.5f, 3.3f, 0.0f);  //top left
        gl.glVertex3f(4.0f, 2.3f, 0.0f); //top right
        gl.glVertex3f(4.0f, -0.6f, 0.0f);   //bottom right
        
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(CurvedWindowR, CurvedWindowG, CurvedWindowB);    // Set the current drawing color to light blue
        gl.glVertex3f(-0.1f, 1.2f, 0.0f);  //bottom left
        gl.glVertex3f(-0.1f, 2.8f, 0.0f);  //top left
        gl.glVertex3f(4.5f, 1.5f, 0.0f); //top right
        gl.glVertex3f(4.5f, -0.2f, 0.0f);   //bottom right
        
        gl.glEnd();
        gl.glRotatef(-8, 0, 0, 1);
         DrawPartOfCircle(3.6f, 2.11f, 0.67f, 0.25f, gl);
         gl.glRotatef(89, 0, 0, 1);
         DrawPartOfCircle(0.5f, 3.4f, 0.67f, 0.25f, gl);
         gl.glRotatef(89, 0, 0, 1);
         DrawPartOfCircle(-0.25f, 1.5f, 0.67f, 0.25f, gl);
         gl.glRotatef(98, 0, 0, 1);
         DrawPartOfCircle(1.0f, 3.37f, 0.6f, 0.25f, gl);
        gl.glLoadIdentity();
    }
    
    public void DrawReflectedCurvedWindow(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(CurvedWindowR, CurvedWindowG, CurvedWindowB);   // Set the current drawing color to light blue
        gl.glVertex3f(0.0f, 0.4f, 0.0f);  //Bottom Right
        gl.glVertex3f(0.0f, 3.15f, 0.0f);  //Top Right
        gl.glVertex3f(-3.5f, 2.2f, 0.0f); //Top Left
        gl.glVertex3f(-3.5f, -0.6f, 0.0f);   //Bottom Left

        gl.glEnd();
        
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(CurvedWindowR, CurvedWindowG, CurvedWindowB);   // Set the current drawing color to light blue
        gl.glVertex3f(0.5f, 1.2f, 0.0f);  //Bottom Right
        gl.glVertex3f(0.5f, 2.8f, 0.0f);  //Top Right
        gl.glVertex3f(-4.0f, 1.6f, 0.0f); //Top Left
        gl.glVertex3f(-4.0f, -0.1f, 0.0f);   //Bottom Left

        gl.glEnd();
        
        gl.glRotatef(9, 0, 0, 1);
        DrawPartOfCircle(0.34f, 2.5f, 0.6f, 0.25f, gl);
        gl.glRotatef(90, 0, 0, 1);
        DrawPartOfCircle(-0.34f, 3.4f, 0.6f, 0.25f, gl);
        gl.glRotatef(90, 0, 0, 1);
        DrawPartOfCircle(0.31f, 1.58f, 0.61f, 0.25f, gl);
        gl.glRotatef(94, 0, 0, 1);
        DrawPartOfCircle(-0.26f, 3.4f, 0.65f, 0.25f, gl);
        gl.glLoadIdentity();
    }
    
    public void SmallWindow(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(-3.0f, 1.4f, 0.0f);  //73
        gl.glVertex3f(-3.0f, 3.8f, 0.0f);  //72
        gl.glVertex3f(-0.4f, 3.1f, 0.0f); //95
        gl.glVertex3f(-0.4f, 0.7f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void reflectedSmallWindow(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(2.5f, 1.4f, 0.0f);  //73
        gl.glVertex3f(2.5f, 3.8f, 0.0f);  //72
        gl.glVertex3f(-0.1f, 3.1f, 0.0f); //95
        gl.glVertex3f(-0.1f, 0.7f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void lightHouseWindow(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(-3.0f, 4.2f, 0.0f);  //73   -0.8 y
        gl.glVertex3f(-3.0f, 8.0f, 0.0f);  //72
        gl.glVertex3f(0.2f,  7.0f, 0.0f); //95
        gl.glVertex3f(0.2f,  3.2f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void lightHouseReflectedWindow(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(3.0f,  4.2f, 0.0f);  //73
        gl.glVertex3f(3.0f,  8.0f, 0.0f);  //72
        gl.glVertex3f(-0.2f, 7.0f, 0.0f); //95
        gl.glVertex3f(-0.2f, 3.2f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
        
        
    }
    
    public void windowGlass(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(lightRGB, lightRGB, lightRGB);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.3f, 4.8f, 0.0f);  //73
        gl.glVertex3f(-2.3f, 7.0f, 0.0f);  //72
        gl.glVertex3f(-0.5f, 6.5f, 0.0f); //95
        gl.glVertex3f(-0.5f, 4.3f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void reflectedWindowGlass(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(lightRGB, lightRGB, lightRGB);    // Set the current drawing color to light blue
        gl.glVertex3f(2.3f, 4.8f, 0.0f);  //73 
        gl.glVertex3f(2.3f, 7.0f, 0.0f);  //72 
        gl.glVertex3f(0.5f, 6.5f, 0.0f); //95 
        gl.glVertex3f(0.5f, 4.3f, 0.0f);   //98 

        gl.glEnd();
        gl.glLoadIdentity();
        
    }
    
    public void lightBulb(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.5f, 7.6f, 0.0f);
        gl.glVertex3f(-2.5f, 7.9f, 0.0f);
        gl.glVertex3f(-2.5f, 8.2f, 0.0f);
        gl.glVertex3f(-2.6f, 8.5f, 0.0f);
        gl.glVertex3f(-2.7f, 8.8f, 0.0f);
        gl.glVertex3f(-2.9f, 9.1f, 0.0f);
        gl.glVertex3f(-3.1f, 9.4f, 0.0f);
        gl.glVertex3f(-3.3f, 9.7f,0.0f);
        gl.glVertex3f(-3.45f, 9.8f,0.0f);
        gl.glVertex3f(-3.65f,9.8f,0.0f);
 
        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void reflectedLightBulb(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(2.5f, 7.6f, 0.0f);
        gl.glVertex3f(2.5f, 7.9f, 0.0f);
        gl.glVertex3f(2.5f, 8.2f, 0.0f);
        gl.glVertex3f(2.6f, 8.5f, 0.0f);
        gl.glVertex3f(2.7f, 8.8f, 0.0f);
        gl.glVertex3f(2.9f, 9.1f, 0.0f);
        gl.glVertex3f(3.1f, 9.4f, 0.0f);
        gl.glVertex3f(3.3f, 9.7f,0.0f);
        gl.glVertex3f(3.45f, 9.8f,0.0f);
        gl.glVertex3f(3.65f,9.8f,0.0f);

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void lightRay1(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_LINE_STRIP);  
        
        gl.glColor3f(0.0f, 0.70f, 0.0f);    // Set the current drawing color to light green
        gl.glVertex3f(-0.5f, 5.7f, 0.0f);  //73
        gl.glVertex3f(2.55f, 6.15f, 0.0f);  //73
        //
        gl.glColor3f(0.32f, 0.49f, 0.46f);    // Set the current drawing color to dark green
        gl.glVertex3f(2.55f, 6.15f, 0.0f);  //73
        gl.glVertex3f(5.6f, 6.6f, 0.0f);  //72
        
        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void lightRay2(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_LINE_STRIP);
        
        gl.glColor3f(0.0f, 0.70f, 0.0f);    // Set the current drawing color to light green
        gl.glVertex3f(-0.5f, 5.2f, 0.0f);  //73
        gl.glVertex3f(4.6f, 5.2f, 0.0f);  //73
        //
        gl.glColor3f(0.32f, 0.49f, 0.46f);  // Set the current drawing color to dark green
        gl.glVertex3f(4.6f, 5.2f, 0.0f);  //73
        gl.glVertex3f(9.6f, 5.2f, 0.0f);  //72

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void lightRay3(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_LINE_STRIP);
        
        gl.glColor3f(0.0f, 0.70f, 0.0f);    // Set the current drawing color to light green
        gl.glVertex3f(-0.5f, 4.7f, 0.0f);  //73
        gl.glVertex3f(2.55f, 4.25f, 0.0f);  //73
        //
        gl.glColor3f(0.32f, 0.49f, 0.46f); // Set the current drawing color to dark blue
        gl.glVertex3f(2.55f, 4.25f, 0.0f);  //73
        gl.glVertex3f(5.6f, 3.8f, 0.0f);  //72

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void reflectedLightRay1(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_LINE_STRIP);
        
        gl.glColor3f(0.0f, 0.70f, 0.0f);    // Set the current drawing color to light green
        gl.glVertex3f(0.5f, 5.7f, 0.0f);  //73
        gl.glVertex3f(-2.55f, 6.15f, 0.0f);  //73
        //
        gl.glColor3f(0.32f, 0.49f, 0.46f);    // Set the current drawing color to dark green
        gl.glVertex3f(-2.55f, 6.15f, 0.0f);  //73
        gl.glVertex3f(-5.6f, 6.6f, 0.0f);  //72

        gl.glEnd();
        gl.glLoadIdentity();
        
    }
    
    public void reflectedLightRay2(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_LINE_STRIP);
        
        gl.glColor3f(0.0f, 0.70f, 0.0f);    // Set the current drawing color to light green
        gl.glVertex3f(0.5f, 5.2f, 0.0f);  //73
        gl.glVertex3f(-4.6f, 5.2f, 0.0f);  //73
        //
        gl.glColor3f(0.32f, 0.49f, 0.46f);  // Set the current drawing color to dark green
        gl.glVertex3f(-4.6f, 5.2f, 0.0f);  //73
        gl.glVertex3f(-9.6f, 5.2f, 0.0f);  //72

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void reflectedLightRay3(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_LINE_STRIP);
        
        gl.glColor3f(0.0f, 0.70f, 0.0f);    // Set the current drawing color to light green
        gl.glVertex3f(0.5f, 4.7f, 0.0f);  //73
        gl.glVertex3f(-2.55f, 4.25f, 0.0f);  //73
        //
        gl.glColor3f(0.32f, 0.49f, 0.46f);  //Set the current drawing color to dark blue
        gl.glVertex3f(-2.55f, 4.25f, 0.0f);  //73
        gl.glVertex3f(-5.6f, 3.8f, 0.0f);  //72

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void house(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
        gl.glVertex3f(-3.0f, 1.3f, 0.0f);  //73
        gl.glVertex3f(4.5f, -0.8f, 0.0f);  //72
        gl.glVertex3f(4.5f, -17.8f, 0.0f); //95
        gl.glVertex3f(-3.0f, -20.6f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void reflectedHouse(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(3.0f, 1.3f, 0.0f);  //73
        gl.glVertex3f(-4.5f, -0.8f, 0.0f);  //72
        gl.glVertex3f(-4.5f, -17.8f, 0.0f); //95
        gl.glVertex3f(3.0f, -20.6f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void houseWindow(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(-1.8f, -2.0f, 0.0f);  //73
        gl.glVertex3f(-1.8f,  0.0f, 0.0f);  //72
        gl.glVertex3f(0.0f,  -0.5f, 0.0f); //95
        gl.glVertex3f(0.0f,  -2.5f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void houseWindow2(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(0.8f, -2.6f, 0.0f);  //73
        gl.glVertex3f(0.8f, -0.7f, 0.0f);  //72
        gl.glVertex3f(2.6f, -1.2f, 0.0f); //95
        gl.glVertex3f(2.6f, -3.1f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void reflectedHouseWindow(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(1.8f, -2.0f, 0.0f);  //73
        gl.glVertex3f(1.8f,  0.0f, 0.0f);  //72
        gl.glVertex3f(0.0f, -0.5f, 0.0f); //95
        gl.glVertex3f(0.0f, -2.5f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void reflectedHouseWindow2(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(-0.8f, -2.6f, 0.0f);  //73
        gl.glVertex3f(-0.8f, -0.7f, 0.0f);  //72
        gl.glVertex3f(-2.6f, -1.2f, 0.0f); //95
        gl.glVertex3f(-2.6f, -3.1f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void feather(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glLineWidth(4.5f);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(-3.0f, -3.3f, 0.0f);  //73
        gl.glVertex3f(-1.0f, -6.8f, 0.0f);  //72
        gl.glVertex3f(-0.5f, -8.8f, 0.0f); //95
        gl.glVertex3f(-0.5f, -10.8f, 0.0f); //95
        gl.glVertex3f(-0.5f, -12.8f, 0.0f); //95
        gl.glVertex3f(-1.0f, -14.8f, 0.0f); //95
        gl.glVertex3f(-0.5f, -15.8f, 0.0f); //95
        gl.glVertex3f(-0.3f, -16.3f, 0.0f); //95
        gl.glVertex3f(-0.5f, -16.8f, 0.0f); //95
        gl.glVertex3f(-0.7f, -17.0f, 0.0f); //95
        gl.glVertex3f(-1.5f, -18.8f, 0.0f); //95
        gl.glVertex3f(-3.0f, -20.6f, 0.0f); //95

        gl.glEnd();
        gl.glLoadIdentity();
    }
    
    public void reflectedFeather(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(3.0f, -3.3f, 0.0f);  //73
        gl.glVertex3f(1.0f, -6.8f, 0.0f);  //72
        gl.glVertex3f(0.5f, -8.8f, 0.0f); //95
        gl.glVertex3f(0.5f, -10.8f, 0.0f); //95
        gl.glVertex3f(0.5f, -12.8f, 0.0f); //95
        gl.glVertex3f(1.0f, -14.8f, 0.0f); //95
        gl.glVertex3f(0.5f, -15.8f, 0.0f); //95
        gl.glVertex3f(0.3f, -16.3f, 0.0f); //95
        gl.glVertex3f(0.5f, -16.8f, 0.0f); //95
        gl.glVertex3f(0.7f, -17.0f, 0.0f); //95
        gl.glVertex3f(1.5f, -18.8f, 0.0f); //95
        gl.glVertex3f(3.0f, -20.6f, 0.0f); //95

        gl.glEnd();
        gl.glLoadIdentity();
    }
}
