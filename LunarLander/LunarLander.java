import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;

/**
    This program uses images of a spacecraft and a background drawing of the
    moon's surface that were provided by the instrutor. The program will 
    initially display the spacecraft on the moon's surface. There will be a 
    short animation of the spacecraft moving across the screen. When that 
    finishes the user can press the space bar. This will start the second
    animation which causes the spacecraft to slowly lift off the surface.

    @author Jeremy Hill
    @version 1.8.0_271
 */
public class LunarLander extends Application 
{
    /**
    * The main method calls the Application class launch
    * @param args the command line arguments
    */
    public static void main(String[] args) 
    {
        launch(args);
    }


    /**
    * The start method takes a Stage object as an argument. It also adds the
    * surface and lander images and passes them to ImageView objects which
    * will be displayed in a Pane object. The lander ImageView will have two
    * TranslateTransitions applied to it. One on scene load and another when
    * the user presses the space bar. 
    * @param primaryStage Stage object to display scene
    */
    @Override
    public void start(Stage primaryStage)
    {
        // Constants for the drawing
        final double X1 = 0;
        final double Y1 = 350;
        final double X2 = 320;
        final double Y2 = -100;

        // Create ImageView for the moon's surface
        Image surfaceImage = new Image("file:lunar surface.png");
        ImageView surfaceView = new ImageView(surfaceImage);
        surfaceView.setPreserveRatio(true);

        // Create ImageView for the lunar lander
        Image landerImage = new Image("file:lunar lander.png");
        ImageView landerView = new ImageView(landerImage);
        landerView.setPreserveRatio(true);

        // Create initial Transition object
        TranslateTransition landerTransitionStart = 
                    new TranslateTransition(new Duration(2000.0), landerView);
        // Set Transition values
        landerTransitionStart.setFromX(X1);
        landerTransitionStart.setFromY(Y1);
        landerTransitionStart.setToX(X2);
        landerTransitionStart.setToY(Y1);

        // Create button triggered Transition object
        TranslateTransition landerTransitionKeyPress = 
                    new TranslateTransition(new Duration(3000.0), landerView);
        // Set Transition values
        landerTransitionKeyPress.setFromX(X2);
        landerTransitionKeyPress.setFromY(Y1);
        landerTransitionKeyPress.setToX(X2);
        landerTransitionKeyPress.setToY(Y2);

        // Create a Pane to display the ImageView objects
        Pane root = new Pane(surfaceView, landerView);

        // Create a scene and display it
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.show();

        // Play the initial animation
        landerTransitionStart.play();

        // Handle the event object created by pressing the space bar
        mainScene.setOnKeyPressed(e ->
        {
            landerTransitionKeyPress.play();
        });
    }
}