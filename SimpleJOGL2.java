package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.GLUT;
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
public class SimpleJOGL2 implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new SimpleJOGL2());
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
    //For left part of blocks
    float Lposition = 0;
    float Ldirection = 1;
    float Lspeed = 0.5f;
    float Langle = 0.0f;
    
    //For right part of blocks
    float Rposition = 0;
    float Rdirection = 1;
    float Rspeed = 0.5f;
    float Rangle = 0.0f;
    
    //For lightRay 
    float Lightspeed = 0.05f;
    float Lightdirection = 1;
    float Lightposition = 0;
    
    float Rcolor = 1;
    float Lcolor = 1;
    
    float LscaleP = 0.0f; 
    float LscaleD = 1.0f;
    float LscaleS = 0.2f;
    
    float RscaleP = 0.0f;
    float RscaleD = 1.0f;
    float RscaleS = 0.2f;
    
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
//--------------------------------------Block Animation------------------------------------------------------------------------

//--------------------------------------Left Side--------------------------------------------------------------------------
        if (Rangle == 0) {
            
            Lposition += Lspeed * Ldirection;
            if (Lposition > 0) { //double size
                Ldirection = -1;
            } else if (Lposition < -15) {     // normal size
                Ldirection = 1;
            }
            if (Langle > -45 && Ldirection == -1) {
                Langle -= 0.5;
            } else {
                if (Langle < 0) {
                    Langle += 0.5;
                }
            }
        }

        //-------------------Right side --------------------
        if (Langle == 0) {
           
            Rposition += Rspeed * Rdirection;
            if (Rposition < 0) { //double size
                Rdirection = 1;
            } else if (Rposition > 15) {     // normal size
                Rdirection = -1;
            }
            if (Rangle < 45 && Rdirection == 1) {
                Rangle += 0.5;
            } else {
                if (Rangle > 0) {
                    Rangle -= 0.5;
                }
            }
             

        }
        //-------------------LightRay Animation-------------------

        Lightposition += Lightspeed * Lightdirection;
        if (Lightposition > 1.3) {
            Lightdirection = -1;
        } else if (Lightposition <= -1.3) {
            Lightdirection = 1;
        }
        
        drawLeftPart(drawable);
        drawRightPart(drawable);
        drawMiddlePart(drawable);
        drawLightRays(drawable);

//-------------------------------------------------------------------------------------------------------------------------------------------
        //Lines 
//-------------------------------------------------------------------------------------------------------------------------------------------
//      Line #1
        gl.glTranslatef(10.2f, 6.0f, -50.0f);
        gl.glRotatef(45.0f, 0.0f, 0.0f, -1.0f);
        
        gl.glTranslatef(0, Rposition*50, Rposition*50);
        if(!(Lposition == 0))
            gl.glColor3f(0.32f, 0.49f, 0.46f);
        else
            gl.glColor3f(1.0f, 1.0f, 1.0f);
        //gl.glRotatef(angle, 0, 0, 1);
        
        DrawConnectedLines(drawable);
        
//-------------------------------------------------------------------------------------------------------------------------------------------
//      reflected Line #1

        gl.glTranslatef(-13.5f, 6.0f, -50.0f);
        gl.glRotatef(135.0f, 0.0f, 0.0f, -1.0f);
        
        gl.glTranslatef(0, Lposition*50, -Lposition*50);
        if(!(Rposition == 0))
            gl.glColor3f(0.184314f, 0.309804f, 0.184314f);
        else
            gl.glColor3f(1.0f, 1.0f, 1.0f);
        //gl.glRotatef(angle, 0, 0, 1);
        
        DrawReflectedLines(drawable);

//-------------------------------------------------------------------------------------------------------------------------------------------
//      Line #2
        gl.glTranslatef(13.7f, 2.0f, -50.0f);
        gl.glRotatef(135.0f, 0.0f, 0.0f, -1.0f);
        gl.glTranslatef(-Rposition*50, 0, Rposition*50);
        if(!(Lposition == 0))
            gl.glColor3f(0.32f, 0.49f, 0.46f);
        else
           gl.glColor3f(1.0f, 1.0f, 1.0f);
        DrawConnectedLines(drawable);
//------------------------------------------------------------------------------------------------------------------------------------------
//      reflected Line #2

        gl.glTranslatef(-17.0f, 2.0f, -50.0f);
        gl.glRotatef(45.0f, 0.0f, 0.0f, -1.0f);
        gl.glTranslatef(Lposition*50, 0, -Lposition*50);
        if(!(Rposition == 0))
           gl.glColor3f(0.184314f, 0.309804f, 0.184314f);
        else
            gl.glColor3f(1.0f, 1.0f, 1.0f);
        DrawReflectedLines(drawable);

//---------------------------------------------------------------------------------------------------------------------------------------------   
//      Line #3
        gl.glTranslatef(18.9f, -2.0f, -50.0f);
        gl.glRotatef(133.0f, 0.0f, 0.0f, -1.0f);
        gl.glTranslatef(-Rposition*50, 0, Rposition*50);
        if(!(Lposition == 0))
            gl.glColor3f(0.32f, 0.49f, 0.46f);
        else
            gl.glColor3f(1.0f, 1.0f, 1.0f);
        DrawConnectedLines(drawable);
//----------------------------------------------------------------------------------------------------------------------------------------------
//      reflected Line #3

        gl.glTranslatef(-22.2f, -2.0f, -50.0f);
        gl.glRotatef(47.0f, 0.0f, 0.0f, -1.0f); 
        gl.glTranslatef(Lposition*50, 0, -Lposition*50);
        if(!(Rposition == 0))
            gl.glColor3f(0.184314f, 0.309804f, 0.184314f);
        else
           gl.glColor3f(1.0f, 1.0f, 1.0f);
        DrawReflectedLines(drawable);

//----------------------------------------------------------------------------------------------------------------------------------------------

        
        // Flush all drawing operations to the graphics card
        gl.glFlush();

    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
    public void drawLeftPart(GLAutoDrawable drawable){
            
        GL gl = drawable.getGL();
        
            //      #8
            gl.glTranslatef(-12.0f, 2.5f, -50.0f);
            gl.glTranslatef(Lposition, -Lposition, 0);
            gl.glRotatef(Langle, 0, 0, 1);
            DrawSmootSquare(drawable);
        
            //      #10
            gl.glTranslatef(-17.8f, 5.8f, -50.0f);
            gl.glTranslatef(Lposition, -Lposition, 0);
            gl.glRotatef(Langle, 0, 0, 1);
            DrawSmootSquare(drawable);
            
            //      #9
            gl.glTranslatef(-12.0f, -5.0f, -50.0f);
            gl.glTranslatef(Lposition, -Lposition, 0);
            gl.glRotatef(Langle, 0, 0, 1);
            DrawSmootSquare(drawable);
        
            //      #11
            gl.glTranslatef(-17.8f, -1.3f, -50.0f);
            gl.glTranslatef(Lposition, -Lposition, 0);
            gl.glRotatef(Langle, 0, 0, 1);
            DrawSmootSquare(drawable);
        
            //      #7
            gl.glTranslatef(-12.0f, 9.5f, -50.0f);
            gl.glTranslatef(Lposition, -Lposition, 0);
            gl.glRotatef(Langle, 0, 0, 1);
            DrawSmootSquare(drawable);
        
            //      #12
            gl.glTranslatef(-23.5f, 2.3f, -50.0f);
            gl.glTranslatef(Lposition, -Lposition, 0);
            gl.glRotatef(Langle, 0, 0, 1);
            DrawSmootSquare(drawable);
        
            //      #19
            gl.glTranslatef(-12.0f, -1.3f, -50.0f);
            gl.glTranslatef(Lposition, -Lposition, 0);
            gl.glRotatef(Langle, 0, 0, 1);
            DrawReflectedTriSquares(drawable);
        
            //      #19
            gl.glTranslatef(-12.0f, -1.3f, -50.0f);
            gl.glTranslatef(Lposition, -Lposition, 0);
            gl.glRotatef(Langle, 0, 0, 1);
            DrawReflectedTriSquares(drawable);
        
            //      #20
            gl.glTranslatef(-17.8f, 2.3f, -50.0f);
            gl.glTranslatef(Lposition, -Lposition, 0);
            gl.glRotatef(Langle, 0, 0, 1);
            DrawReflectedTriSquares(drawable);
        
            //      #18
            gl.glTranslatef(-12.0f, 6.0f, -50.0f);
            gl.glTranslatef(Lposition, -Lposition, 0);
            gl.glRotatef(Langle, 0, 0, 1);
            DrawReflectedTriSquares(drawable);
        
            //      #21
            gl.glTranslatef(-17.8f, -5.0f, -50.0f);
            gl.glTranslatef(Lposition, -Lposition, 0);
            gl.glRotatef(Langle, 0, 0, 1);
            DrawReflectedTriSquares(drawable);
        
            //      #22
            gl.glTranslatef(-23.5f, -1.3f, -50.0f);
            gl.glTranslatef(Lposition, -Lposition, 0);
            gl.glRotatef(Langle, 0, 0, 1);
            DrawReflectedTriSquares(drawable);
    }
    
    public void drawRightPart(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        
            //      #1
            gl.glTranslatef(9.7f, 9.5f, -50.0f);
            gl.glTranslatef(Rposition, Rposition, 0);
            gl.glRotatef(Rangle, 0, 0, 1);
            DrawSmootSquare(drawable);
        
            //      #2
            gl.glTranslatef(9.7f, 2.5f, -50.0f);
            gl.glTranslatef(Rposition, Rposition, 0);
            gl.glRotatef(Rangle, 0, 0, 1);
            DrawSmootSquare(drawable);
            
            //      #4
            gl.glTranslatef(15.5f, 5.8f, -50.0f);
            gl.glTranslatef(Rposition, Rposition, 0);
            gl.glRotatef(Rangle, 0, 0, 1);
            DrawSmootSquare(drawable);
            
            //      #3
            gl.glTranslatef(9.7f, -5.0f, -50.0f);
            gl.glTranslatef(Rposition, Rposition, 0);
            gl.glRotatef(Rangle, 0, 0, 1);
            DrawSmootSquare(drawable);
            
            //      #5
            gl.glTranslatef(15.5f, -1.3f, -50.0f);
            gl.glTranslatef(Rposition, Rposition, 0);
            gl.glRotatef(Rangle, 0, 0, 1);
            DrawSmootSquare(drawable);
            
            //      #6
            gl.glTranslatef(21.2f, 2.3f, -50.0f);
            gl.glTranslatef(Rposition, Rposition, 0);
            gl.glRotatef(Rangle, 0, 0, 1);
            DrawSmootSquare(drawable);
            
            //      #14
            gl.glTranslatef(9.7f, -1.3f, -50.0f);
            gl.glTranslatef(Rposition, Rposition, 0);
            gl.glRotatef(Rangle, 0, 0, 1);
            DrawTriSmoothSquares(drawable);
            
            //      #15
            gl.glTranslatef(15.5f, 2.3f, -50.0f);
            gl.glTranslatef(Rposition, Rposition, 0);
            gl.glRotatef(Rangle, 0, 0, 1);
            DrawTriSmoothSquares(drawable);
            
            //      #13
            gl.glTranslatef(9.7f, 6.0f, -50.0f);
            gl.glTranslatef(Rposition, Rposition, 0);
            gl.glRotatef(Rangle, 0, 0, 1);
            DrawTriSmoothSquares(drawable);
            
            //      #16
            gl.glTranslatef(15.5f, -5.0f, -50.0f);
            gl.glTranslatef(Rposition, Rposition, 0);
            gl.glRotatef(Rangle, 0, 0, 1);
            DrawTriSmoothSquares(drawable);
            
            //      #17
            gl.glTranslatef(21.2f, -1.3f, -50.0f);
            gl.glTranslatef(Rposition, Rposition, 0);
            gl.glRotatef(Rangle, 0, 0, 1);
            DrawTriSmoothSquares(drawable);
    }
    
    public void drawMiddlePart (GLAutoDrawable drawable){
        
        GL gl = drawable.getGL();
        
        // Curved Window
        gl.glTranslatef(2f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        DrawCurvedWindow(drawable);
        // Refelcted curved window
        gl.glTranslatef(-4.7f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        DrawReflectedCurvedWindow(drawable);
        
        // Draw Small Window Above the house
        gl.glTranslatef(2f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        SmallWindow(drawable);
        //refelct
        gl.glTranslatef(-3.8f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        reflectedSmallWindow(drawable);
        
        // Draw Light house Window
        gl.glTranslatef(2f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        lightHouseWindow(drawable);
        //refelct
        gl.glTranslatef(-4.4f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        lightHouseReflectedWindow(drawable);
        
        //Draw Parallelogram inside on top of house
        gl.glTranslatef(2f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        windowGlass(drawable);
        //refelct
        gl.glTranslatef(-4.4f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        reflectedWindowGlass(drawable);

        //Draw Light Bulb (OUTER)
        gl.glTranslatef(2.5f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        lightBulb(drawable);
        //refelct
        gl.glTranslatef(-4.7f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        reflectedLightBulb(drawable);
        
        //Draw House
        gl.glTranslatef(2f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        house(drawable);
        //refelcted House
        gl.glTranslatef(-4.3f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        reflectedHouse(drawable);
        
        // Small Window Inside House
        gl.glTranslatef(2f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        houseWindow(drawable);
        //reflect
        gl.glTranslatef(-4.4f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        reflectedHouseWindow(drawable);

        // Small Window Inside House
        gl.glTranslatef(2f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        houseWindow2(drawable);
        // reflect
        gl.glTranslatef(-4.4f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        reflectedHouseWindow2(drawable);
        
        // Draw Feather
        gl.glTranslatef(2f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        feather(drawable);
        // Reflect Feather
        gl.glTranslatef(-4.3f, 9.0f, -50.0f);
        gl.glTranslatef(0, 0, Lightposition*2);
        reflectedFeather(drawable);
        
    }
    
    public void drawLines(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        
    }
    
    public void drawLightRays (GLAutoDrawable drawable){
        
        GL gl = drawable.getGL();
        
       //Draw Light ray 1------------------------------
        gl.glTranslatef(3f, 9.0f, -50.0f);
        gl.glTranslatef(0, Lightposition, 0);
        lightRay1(drawable);

        //refelct
        gl.glTranslatef(-5.3f, 9.0f, -50.0f);
        gl.glTranslatef(0, Lightposition, 0);
        reflectedLightRay1(drawable);

        //Draw Light ray 2-----------------------------------
        gl.glTranslatef(3f, 9.0f, -50.0f);
        gl.glTranslatef(0, Lightposition, 0);
        lightRay2(drawable);

        //refelct
        gl.glTranslatef(-5.3f, 9.0f, -50.0f);
        gl.glTranslatef(0, Lightposition, 0);
        reflectedLightRay2(drawable);

        //Draw Light ray 3---------------------------
        gl.glTranslatef(3f, 9.0f, -50.0f);
        gl.glTranslatef(0, Lightposition, 0);
        lightRay3(drawable);

        //refelct
        gl.glTranslatef(-5.3f, 9.0f, -50.0f);
        gl.glTranslatef(0, Lightposition, 0);
        reflectedLightRay3(drawable);
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

    public void DrawSmootSquare(GLAutoDrawable drawable) {
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

    public void DrawTriSmoothSquares(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
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

    public void DrawReflectedTriSquares(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
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

    public void DrawConnectedLines(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
//        if(!(Lposition == 0))
//            gl.glColor3f(0.32f, 0.49f, 0.46f);
//        else
//            gl.glColor3f(0.9f, 0.0f, 0.5f);    // Set the current drawing color to light blue
        
        gl.glVertex3f(0.0f, 0.0f, 0.0f);  //73
        gl.glVertex3f(5.0f, 0.0f, 0.0f);  //72
        gl.glVertex3f(5.0f, 0.6f, 0.0f); //95
        gl.glVertex3f(0.0f, 0.6f, 0.0f);   //98
        gl.glEnd();
        gl.glLoadIdentity();
    }

    public void DrawReflectedLines(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        //gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(0.0f, 0.0f, 0.0f);  //73
        gl.glVertex3f(5.0f, 0.0f, 0.0f);  //72
        gl.glVertex3f(5.0f, 0.6f, 0.0f); //95
        gl.glVertex3f(0.0f, 0.6f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
    }

    public void DrawCurvedWindow(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.760784f, 0.937255f, 0.560784f);    // Set the current drawing color to light blue
        gl.glVertex3f(0.4f, 0.4f, 0.0f);  //bottom left
        gl.glVertex3f(0.5f, 3.3f, 0.0f);  //top left
        gl.glVertex3f(4.0f, 2.3f, 0.0f); //top right
        gl.glVertex3f(4.0f, -0.6f, 0.0f);   //bottom right

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.760784f, 0.937255f, 0.560784f);    // Set the current drawing color to light blue
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

    public void DrawReflectedCurvedWindow(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.760784f, 0.937255f, 0.560784f);   // Set the current drawing color to light blue
        gl.glVertex3f(0.0f, 0.4f, 0.0f);  //Bottom Right
        gl.glVertex3f(0.0f, 3.15f, 0.0f);  //Top Right
        gl.glVertex3f(-3.5f, 2.2f, 0.0f); //Top Left
        gl.glVertex3f(-3.5f, -0.6f, 0.0f);   //Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.760784f, 0.937255f, 0.560784f);   // Set the current drawing color to light blue
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

    public void SmallWindow(GLAutoDrawable drawable) {
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

    public void reflectedSmallWindow(GLAutoDrawable drawable) {
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

    public void lightHouseWindow(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(-3.0f, 4.2f, 0.0f);  //73   -0.8 y
        gl.glVertex3f(-3.0f, 8.0f, 0.0f);  //72
        gl.glVertex3f(0.2f, 7.0f, 0.0f); //95
        gl.glVertex3f(0.2f, 3.2f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
    }

    public void lightHouseReflectedWindow(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(3.0f, 4.2f, 0.0f);  //73
        gl.glVertex3f(3.0f, 8.0f, 0.0f);  //72
        gl.glVertex3f(-0.2f, 7.0f, 0.0f); //95
        gl.glVertex3f(-0.2f, 3.2f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();

    }

    public void windowGlass(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.3f, 4.8f, 0.0f);  //73
        gl.glVertex3f(-2.3f, 7.0f, 0.0f);  //72
        gl.glVertex3f(-0.5f, 6.5f, 0.0f); //95
        gl.glVertex3f(-0.5f, 4.3f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
    }

    public void reflectedWindowGlass(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(2.3f, 4.8f, 0.0f);  //73 
        gl.glVertex3f(2.3f, 7.0f, 0.0f);  //72 
        gl.glVertex3f(0.5f, 6.5f, 0.0f); //95 
        gl.glVertex3f(0.5f, 4.3f, 0.0f);   //98 

        gl.glEnd();
        gl.glLoadIdentity();

    }

    public void lightBulb(GLAutoDrawable drawable) {
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
        gl.glVertex3f(-3.3f, 9.7f, 0.0f);
        gl.glVertex3f(-3.45f, 9.8f, 0.0f);
        gl.glVertex3f(-3.65f, 9.8f, 0.0f);

        gl.glEnd();
        gl.glLoadIdentity();
    }

    public void reflectedLightBulb(GLAutoDrawable drawable) {
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
        gl.glVertex3f(3.3f, 9.7f, 0.0f);
        gl.glVertex3f(3.45f, 9.8f, 0.0f);
        gl.glVertex3f(3.65f, 9.8f, 0.0f);

        gl.glEnd();
        gl.glLoadIdentity();
    }

    public void lightRay1(GLAutoDrawable drawable) {
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

    public void lightRay2(GLAutoDrawable drawable) {
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

    public void lightRay3(GLAutoDrawable drawable) {
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

    public void reflectedLightRay1(GLAutoDrawable drawable) {
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

    public void reflectedLightRay2(GLAutoDrawable drawable) {
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

    public void reflectedLightRay3(GLAutoDrawable drawable) {
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

    public void house(GLAutoDrawable drawable) {
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

    public void reflectedHouse(GLAutoDrawable drawable) {
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

    public void houseWindow(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(-1.8f, -2.0f, 0.0f);  //73
        gl.glVertex3f(-1.8f, 0.0f, 0.0f);  //72
        gl.glVertex3f(0.0f, -0.5f, 0.0f); //95
        gl.glVertex3f(0.0f, -2.5f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
    }

    public void houseWindow2(GLAutoDrawable drawable) {
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

    public void reflectedHouseWindow(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(1.8f, -2.0f, 0.0f);  //73
        gl.glVertex3f(1.8f, 0.0f, 0.0f);  //72
        gl.glVertex3f(0.0f, -0.5f, 0.0f); //95
        gl.glVertex3f(0.0f, -2.5f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
    }

    public void reflectedHouseWindow2(GLAutoDrawable drawable) {
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

    public void feather(GLAutoDrawable drawable) {
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

    public void reflectedFeather(GLAutoDrawable drawable) {
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
