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
public class Project391 implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Project391());
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
        gl.glTranslatef(9.7f, 2.5f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();
        
        

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
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
//------------------------------------------------------------------------------------------------------------------------------
        //reflect
        gl.glTranslatef(-12.0f, 2.5f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();
        
        

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
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

//------------------------------------------------------------------------------------------------------------------------------
        gl.glTranslatef(15.5f, 5.8f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
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
        
        
        gl.glBegin(GL.GL_POINTS);
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(4.0f, 0.0f, 0.0f);  // Top Left
        
        gl.glEnd();
        gl.glLoadIdentity();
//        gl.glTranslatef(-13.5f, -5.0f, 0.0f);
//-----------------------------------------------------------------------------------------------------------------------------
        //Reflect
        gl.glTranslatef(-17.8f, 5.8f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
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
        
        
        gl.glBegin(GL.GL_POINTS);
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(4.0f, 0.0f, 0.0f);  // Top Left
        
        gl.glEnd();
        gl.glLoadIdentity();
//-----------------------------------------------------------------------------------------------------------------------------
        gl.glTranslatef(9.7f, -5.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
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
//        gl.glTranslatef(-8.1f, 6.5f, 0.0f);
//------------------------------------------------------------------------------------------------------------------------------
        //reflect
        gl.glTranslatef(-12.0f, -5.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
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
//------------------------------------------------------------------------------------------------------------------------------
        gl.glTranslatef(15.5f, -1.3f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
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
//----------------------------------------------------------------------------------------------------------------------
        //reflect
        gl.glTranslatef(-17.8f, -1.3f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );   // Set the current drawing color to light blue
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

//----------------------------------------------------------------------------------------------------------------------
        gl.glTranslatef(9.7f, 9.5f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
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
//----------------------------------------------------------------------------------------------------------------------------------------
        //refelct
        gl.glTranslatef(-12.0f, 9.5f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
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

//----------------------------------------------------------------------------------------------------------------------------------------
        gl.glTranslatef(21.2f, 2.3f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );   // Set the current drawing color to light blue
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
//------------------------------------------------------------------------------------------------------------------------------------
        //reflect
        gl.glTranslatef(-23.5f, 2.3f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
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

//------------------------------------------------------------------------------------------------------------------------------------

        gl.glTranslatef(9.7f, -1.3f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
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
//------------------------------------------------------------------------------------------------------------------------------
        //reflect
        gl.glTranslatef(-12.0f, -1.3f, -50.0f);
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
//------------------------------------------------------------------------------------------------------------------------------
        gl.glTranslatef(15.5f, 2.3f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
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
//---------------------------------------------------------------------------------------------------------------------------------------
        //reflect
        gl.glTranslatef(-17.8f, 2.3f, -50.0f);
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

//---------------------------------------------------------------------------------------------------------------------------------------

        gl.glTranslatef(9.7f, 6.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
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
//-------------------------------------------------------------------------------------------------------------------------------------------
        //reflect
        gl.glTranslatef(-12.0f, 6.0f, -50.0f);
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
//-------------------------------------------------------------------------------------------------------------------------------------------
        //reflect
//-------------------------------------------------------------------------------------------------------------------------------------------
        gl.glTranslatef(10.2f, 6.0f, -50.0f);
        gl.glRotatef(45.0f, 0.0f, 0.0f, -1.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
        gl.glVertex3f(0.0f, 0.0f, 0.0f);  //73
        gl.glVertex3f(5.0f, 0.0f, 0.0f);  //72
        gl.glVertex3f(5.0f, 0.6f, 0.0f); //95
        gl.glVertex3f(0.0f, 0.6f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
//-------------------------------------------------------------------------------------------------------------------------------------------
        //reflect
        gl.glTranslatef(-13.5f, 6.0f, -50.0f);
        gl.glRotatef(135.0f, 0.0f, 0.0f, -1.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(0.0f, 0.0f, 0.0f);  //73
        gl.glVertex3f(5.0f, 0.0f, 0.0f);  //72
        gl.glVertex3f(5.0f, 0.6f, 0.0f); //95
        gl.glVertex3f(0.0f, 0.6f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
        
//-------------------------------------------------------------------------------------------------------------------------------------------
        gl.glTranslatef(13.7f, 2.0f, -50.0f);
        gl.glRotatef(135.0f, 0.0f, 0.0f, -1.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
        gl.glVertex3f(0.0f, 0.0f, 0.0f);  //73
        gl.glVertex3f(5.0f, 0.0f, 0.0f);  //72
        gl.glVertex3f(5.0f, 0.6f, 0.0f); //95
        gl.glVertex3f(0.0f, 0.6f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
//------------------------------------------------------------------------------------------------------------------------------------------
        //reflect
        gl.glTranslatef(-17.0f, 2.0f, -50.0f);
        gl.glRotatef(45.0f, 0.0f, 0.0f, -1.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(0.0f, 0.0f, 0.0f);  //73
        gl.glVertex3f(5.0f, 0.0f, 0.0f);  //72
        gl.glVertex3f(5.0f, 0.6f, 0.0f); //95
        gl.glVertex3f(0.0f, 0.6f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
//-------------------------------------------------------------------------------------------------------------------------------------------

        gl.glTranslatef(15.5f, -5.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
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
//-------------------------------------------------------------------------------------------------------------------------------------------
        //refelct
        gl.glTranslatef(-17.8f, -5.0f, -50.0f);
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
//-------------------------------------------------------------------------------------------------------------------------------------------
        gl.glTranslatef(21.2f, -1.3f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, 1.4f, 0.0f);  // Top Left
        gl.glVertex3f(2.0f, 1.4f, 0.0f);   // Top Right
        gl.glVertex3f(2.0f, -1.4f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, -1.4f, 0.0f); // Bottom Left

        gl.glEnd();

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
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
//---------------------------------------------------------------------------------------------------------------------------------------------
        //refelct
        gl.glTranslatef(-23.5f, -1.3f, -50.0f);
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

//---------------------------------------------------------------------------------------------------------------------------------------------        
        
        gl.glTranslatef(18.9f, -2.0f, -50.0f);
        gl.glRotatef(133.0f, 0.0f, 0.0f, -1.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
        gl.glVertex3f(0.0f, 0.0f, 0.0f);  //73
        gl.glVertex3f(5.0f, 0.0f, 0.0f);  //72
        gl.glVertex3f(5.0f, 0.6f, 0.0f); //95
        gl.glVertex3f(0.0f, 0.6f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
//----------------------------------------------------------------------------------------------------------------------------------------------
        //reflect
        gl.glTranslatef(-22.2f, -2.0f, -50.0f);
        gl.glRotatef(47.0f, 0.0f, 0.0f, -1.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(0.0f, 0.0f, 0.0f);  //73
        gl.glVertex3f(5.0f, 0.0f, 0.0f);  //72
        gl.glVertex3f(5.0f, 0.6f, 0.0f); //95
        gl.glVertex3f(0.0f, 0.6f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
        
//----------------------------------------------------------------------------------------------------------------------------------------------


        //Draw Big Parallelogram
        gl.glTranslatef(2f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );    // Set the current drawing color to light blue
        gl.glVertex3f(0.0f, 1.0f, 0.0f);  //73
        gl.glVertex3f(0.0f, 3.8f, 0.0f);  //72
        gl.glVertex3f(4.5f, 2.8f, 0.0f); //95
        gl.glVertex3f(4.5f, 0.0f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
        
        //refelct
        gl.glTranslatef(-4.7f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3d(0.560784f,0.737255f, 0.560784f );   // Set the current drawing color to light blue
        gl.glVertex3f(0.2f, 1.0f, 0.0f);  //73
        gl.glVertex3f(0.2f, 3.8f, 0.0f);  //72
        gl.glVertex3f(-4.1f, 2.8f, 0.0f); //95
        gl.glVertex3f(-4.1f, 0.0f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
        
        //Draw Small Parallelogram
        gl.glTranslatef(2f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(-3.0f, 2.0f, 0.0f);  //73
        gl.glVertex3f(-3.0f, 4.8f, 0.0f);  //72
        gl.glVertex3f(-0.4f, 3.8f, 0.0f); //95
        gl.glVertex3f(-0.4f, 1.0f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
        
        //refelct
        gl.glTranslatef(-3.8f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(2.4f, 2.0f, 0.0f);  //73
        gl.glVertex3f(2.4f, 4.8f, 0.0f);  //72
        gl.glVertex3f(-0.3f, 3.8f, 0.0f); //95
        gl.glVertex3f(-0.3f, 1.0f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
        
        //Draw Parallelogram on top of house
        gl.glTranslatef(2f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(-3.0f, 5.5f, 0.0f);  //73
        gl.glVertex3f(-3.0f, 9.3f, 0.0f);  //72
        gl.glVertex3f(0.2f, 8.3f, 0.0f); //95
        gl.glVertex3f(0.2f, 4.5f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
        
        //refelct
        gl.glTranslatef(-4.4f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(3.0f, 5.5f, 0.0f);  //73
        gl.glVertex3f(3.0f, 9.3f, 0.0f);  //72
        gl.glVertex3f(-0.2f, 8.3f, 0.0f); //95
        gl.glVertex3f(-0.2f, 4.5f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
        
        //Draw Parallelogram inside on top of house
        gl.glTranslatef(2f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.5f, 6.0f, 0.0f);  //73
        gl.glVertex3f(-2.5f, 8.4f, 0.0f);  //72
        gl.glVertex3f(-0.2f, 7.8f, 0.0f); //95
        gl.glVertex3f(-0.2f, 5.2f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
        
        gl.glTranslatef(-4.4f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(2.5f, 6.0f, 0.0f);  //73
        gl.glVertex3f(2.5f, 8.4f, 0.0f);  //72
        gl.glVertex3f(0.2f, 7.8f, 0.0f); //95
        gl.glVertex3f(0.2f, 5.2f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
        
        //Draw Light Bulb 
        gl.glTranslatef(2.5f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(-1.0f, 8.0f, 0.0f);  
        gl.glVertex3f(-0.2f, 9.8f, 0.0f); 
        gl.glVertex3f(-1.2f, 10.8f, 0.0f);
        gl.glVertex3f(-3.6f, 11.1f, 0.0f);  
          
        
        gl.glEnd();
        gl.glLoadIdentity();
        
        //refelct
        gl.glTranslatef(-4.7f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(1.0f, 8.0f, 0.0f);  
        gl.glVertex3f(0.2f, 9.8f, 0.0f); 
        gl.glVertex3f(1.2f, 10.8f, 0.0f);
        gl.glVertex3f(3.6f, 11.1f, 0.0f);  
          
        
        gl.glEnd();
        gl.glLoadIdentity();
        
        
        //Draw Light ray 1
        gl.glTranslatef(3f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
        gl.glVertex3f(0.6f, 7.0f, 0.0f);  //73
        gl.glVertex3f(5.6f, 8.4f, 0.0f);  //72
        
        gl.glEnd();
        gl.glLoadIdentity();
        
        //refelct
        gl.glTranslatef(-5.3f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
        gl.glVertex3f(-0.6f, 7.0f, 0.0f);  //73
        gl.glVertex3f(-5.6f, 8.4f, 0.0f);  //72
        
        gl.glEnd();
        gl.glLoadIdentity();
        
        //Draw Light ray 2
        gl.glTranslatef(3f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
        gl.glVertex3f(0.6f, 6.0f, 0.0f);  //73
        gl.glVertex3f(9.6f, 6.0f, 0.0f);  //72
        
        gl.glEnd();
        gl.glLoadIdentity();
        
        //refelct
        gl.glTranslatef(-5.3f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
        gl.glVertex3f(-0.6f, 6.0f, 0.0f);  //73
        gl.glVertex3f(-9.6f, 6.0f, 0.0f);  //72
        

        gl.glEnd();
        gl.glLoadIdentity();
        
        //Draw Light ray 3
        gl.glTranslatef(3f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
        gl.glVertex3f(0.6f, 5.0f, 0.0f);  //73
        gl.glVertex3f(5.6f, 3.6f, 0.0f);  //72
        

        gl.glEnd();
        gl.glLoadIdentity();
        
        //refelct
        gl.glTranslatef(-5.3f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
        gl.glVertex3f(-0.6f, 5.0f, 0.0f);  //73
        gl.glVertex3f(-5.6f, 3.6f, 0.0f);  //72
        

        gl.glEnd();
        gl.glLoadIdentity();
        
        //Draw House
        gl.glTranslatef(2f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f( 0.32f, 0.49f, 0.46f);    // Set the current drawing color to light blue
        gl.glVertex3f(-3.0f, 1.3f, 0.0f);  //73
        gl.glVertex3f(4.5f, -0.8f, 0.0f);  //72
        gl.glVertex3f(4.5f, -17.8f, 0.0f); //95
        gl.glVertex3f(-3.0f, -20.6f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
        
        //refelct
        gl.glTranslatef(-4.3f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.184314f, 0.309804f, 0.184314f);    // Set the current drawing color to light blue
        gl.glVertex3f(3.0f, 1.3f, 0.0f);  //73
        gl.glVertex3f(-4.5f, -0.8f, 0.0f);  //72
        gl.glVertex3f(-4.5f, -17.8f, 0.0f); //95
        gl.glVertex3f(3.0f, -20.6f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
        
        //Draw Small Parallelogram inside house
        gl.glTranslatef(2f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(-1.8f, -2.0f, 0.0f);  //73
        gl.glVertex3f(-1.8f, 0.5f, 0.0f);  //72
        gl.glVertex3f(0.0f, -0.2f, 0.0f); //95
        gl.glVertex3f(0.0f, -2.8f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
        
        //reflect
        
        gl.glTranslatef(-4.4f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(1.8f, -2.0f, 0.0f);  //73
        gl.glVertex3f(1.8f, 0.5f, 0.0f);  //72
        gl.glVertex3f(0.0f, -0.2f, 0.0f); //95
        gl.glVertex3f(0.0f, -2.8f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
        
        //Draw Small Parallelogram inside house
        gl.glTranslatef(2f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(0.8f, -3.0f, 0.0f);  //73
        gl.glVertex3f(0.8f, -0.5f, 0.0f);  //72
        gl.glVertex3f(2.6f, -1.0f, 0.0f); //95
        gl.glVertex3f(2.6f, -3.8f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
        
        //refelct
        gl.glTranslatef(-4.4f, 9.0f, -50.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(-0.8f, -3.0f, 0.0f);  //73
        gl.glVertex3f(-0.8f, -0.5f, 0.0f);  //72
        gl.glVertex3f(-2.6f, -1.0f, 0.0f); //95
        gl.glVertex3f(-2.6f, -3.8f, 0.0f);   //98

        gl.glEnd();
        gl.glLoadIdentity();
        
        //Draw Curve inside House
        gl.glTranslatef(2f, 9.0f, -50.0f);
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
        
        //refelct
        gl.glTranslatef(-4.3f, 9.0f, -50.0f);
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
         
        
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(1, 0, 0);
        gl.glVertex3f(2, 1, 0);
        gl.glVertex3f(3, 2, 0);
        gl.glVertex3f(4, 3, 0);
        gl.glEnd();
        
        gl.glLoadIdentity();
        
        

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

}
