package editor;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import editor.ReadFile;
import editor.LinkedListDeque;
import java.util.Scanner;
import java.io.BufferedReader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import edu.princeton.cs.algs4.Stopwatch;


public class Editor extends Application {
    /**read input file*/
    private static String read;
    /**all typed string*/
    private static String characterTyped="";
    private static String inputfilename;
    private final Rectangle cursor;
    private static LinkedListDeque<Character> wholetext= new LinkedListDeque<>();

    public Editor(){
        // Create a rectangle to surround the text that gets displayed.  Initialize it with a size
        // of 0, since there isn't any text yet.
        cursor = new Rectangle(0, 0);
    }
    public static void readinput(String input){
        /**read input file*/
        inputfilename=input;

        /**output the runtime
         * this line takes the majority of the runtime*/
        //Stopwatch sw2 = new Stopwatch();
        read=ReadFile.ReadFile(input);
        //System.out.println("@2@"+sw2.elapsedTime()+"@2@");

        characterTyped=""+read;
        wholetext = readAsLinkedList.read(input);
        wholetext.CurrentPosToLast();

        }

    private class RectangleBlinkEventHandler implements EventHandler<ActionEvent> {
        private int currentColorIndex = 0;
        private Color[] boxColors =
                {Color.BLACK,Color.WHITE};

        RectangleBlinkEventHandler() {
            // Set the color to be the first color in the list.
            changeColor();
        }

        private void changeColor() {
            cursor.setFill(boxColors[currentColorIndex]);
            currentColorIndex = (currentColorIndex + 1) % boxColors.length;
        }

        @Override
        public void handle(ActionEvent event) {
            changeColor();
        }
    }

    /** Makes the text bounding box change color periodically. */
    public void makeRectangleColorChange() {
        // Create a Timeline that will call the "handle" function of RectangleBlinkEventHandler
        // every 1 second.
        final Timeline timeline = new Timeline();
        // The rectangle should continue blinking forever.
        timeline.setCycleCount(Timeline.INDEFINITE);
        RectangleBlinkEventHandler cursorChange = new RectangleBlinkEventHandler();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), cursorChange);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    private Group textroot;
    private double scrollmoved;
    private ScrollBar scrollBar;
    @Override
    public void start(Stage primaryStage) {
        // Create a Node that will be the parent of all things displayed on the screen.
        Group root = new Group();
        textroot = new Group();
        root.getChildren().add(textroot);
        // The Scene represents the window: its height and width will be the height and width
        // of the window displayed.
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);

        // To get information about what keys the user is pressing, create an EventHandler.
        // EventHandler subclasses must override the "handle" function, which will be called
        // by javafx.
        EventHandler<KeyEvent> keyEventHandler =
                new KeyEventHandler(textroot, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Register the event handler to be called for all KEY_PRESSED and KEY_TYPED events.
        scene.setOnKeyTyped(keyEventHandler);
        scene.setOnKeyPressed(keyEventHandler);

        scene.setOnMouseClicked(new MouseClickEventHandler(root));
        // All new Nodes need to be added to the root in order to be displayed.
        textroot.getChildren().add(cursor);
        makeRectangleColorChange();
        primaryStage.setTitle("word editor");

        // Make a vertical scroll bar on the right side of the screen.
        scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.VERTICAL);
        // Set the height of the scroll bar so that it fills the whole window.
        scrollBar.setPrefHeight(WINDOW_HEIGHT);

        // Set the range of the scroll bar.
        scrollBar.setMin(0);
        scrollBar.setMax(WINDOW_HEIGHT);

        // Add the scroll bar to the scene graph, so that it appears on the screen.
        root.getChildren().add(scrollBar);

        double usableScreenWidth = WINDOW_WIDTH - scrollBar.getLayoutBounds().getWidth();
        scrollBar.setLayoutX(usableScreenWidth);

        /** When the scroll bar changes position, change the height of Josh. */
        scrollBar.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldValue,
                    Number newValue) {
                //
                scrollmoved = newValue.doubleValue();
                textroot.setLayoutY(-newValue.doubleValue());
            }
        });

        // This is boilerplate, necessary to setup the window where things are displayed.
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    private double mouseClickedX;
    private double mouseClickedY;
    /** An event handler that displays the current position of the mouse whenever it is clicked. */
    private class MouseClickEventHandler implements EventHandler<MouseEvent> {

        MouseClickEventHandler(Group root) {
        }

        @Override
        public void handle(MouseEvent mouseEvent) {
            // Because we registered this EventHandler using setOnMouseClicked, it will only called
            // with mouse events of type MouseEvent.MOUSE_CLICKED.  A mouse clicked event is
            // generated anytime the mouse is pressed and released on the same JavaFX node.
            mouseClickedX = mouseEvent.getX();
            mouseClickedY = mouseEvent.getY();
            wholetext.MouseClickPos(mouseClickedX,mouseClickedY);

            cursor.setHeight(13.96);
            cursor.setWidth(1);

            // For rectangles, the position is the upper left hand corner.
            /**recalculate CurrentX and CurrentY*/
            wholetext.CurrentpRecalibrate();

            //double tempd = wholetext.getLastText().getLayoutBounds().getWidth();
            //int lastTextWidth =(int) Math.round(tempd);
            cursor.setX(wholetext.CurrentX+5);
            cursor.setY(wholetext.CurrentY+0);

        }
    }


    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;

    /** An EventHandler to handle keys that get pressed. */
    private class KeyEventHandler implements EventHandler<KeyEvent> {
        public int textCenterX;
        public int textCenterY;

        private static final int STARTING_FONT_SIZE = 12;
        private static final int STARTING_TEXT_POSITION_X = 250;
        private static final int STARTING_TEXT_POSITION_Y = 250;

        /** The Text to display on the screen. */
        private Text displayText = new Text(STARTING_TEXT_POSITION_X, STARTING_TEXT_POSITION_Y, "");
        private int fontSize = STARTING_FONT_SIZE;

        private String fontName = "Verdana";





        KeyEventHandler(final Group textroot, int windowWidth, int windowHeight) {
            textCenterX = 5;
            textCenterY = 0;

            // Initialize some empty text and add it to root so that it will be displayed.
            displayText = new Text(textCenterX, textCenterY, "");
            // Always set the text origin to be VPos.TOP! Setting the origin to be VPos.TOP means
            // that when the text is assigned a y-position, that position corresponds to the
            // highest position across all letters (for example, the top of a letter like "I", as
            // opposed to the top of a letter like "e"), which makes calculating positions much
            // simpler!
            displayText.setTextOrigin(VPos.TOP);
            displayText.setFont(Font.font(fontName, fontSize));

            // All new Nodes need to be added to the root in order to be displayed.
            textroot.getChildren().add(displayText);

            /**display the text of the input file*/
            displayText.setText(characterTyped);

            centerText();

        }



        @Override
        public void handle(KeyEvent keyEvent) {
                if (keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
                    // Use the KEY_TYPED event rather than KEY_PRESSED for letter keys, because with
                    // the KEY_TYPED event, javafx handles the "Shift" key and associated
                    // capitalization.

                    wholetext.addAfterCurrentPos(keyEvent.getCharacter().charAt(0));
                    //characterTyped = wholetext.reCulDisplayText();
                    char a = 13;
                    char b = 10;
                    //System.out.print(wholetext.getCurrentPos());
                    //System.out.print(wholetext.getLast().items);

                    if (wholetext.getCurrentPos() == 13) {
                        /**when enter is pressed, add \r\n*/
                        wholetext.removeAtCurrentPos();
                        wholetext.addAfterCurrentPos(a);
                        wholetext.addAfterCurrentPos(b);
                        wholetext.NewLineAndArrayitem(wholetext.returnListCurPos());
                        //wholetext.CurrentPosToLast();
                        characterTyped = wholetext.reCulDisplayText();
                        wholetext.callstoredInfo(wholetext.returnListCurPos(),wholetext.getCurrentPos(),"enter");
                        System.out.print("enter");
                    } else if (wholetext.Wrap(b, a) != null) {
                        /**word wrap*/
                        characterTyped = wholetext.reCulDisplayText();
                        //wholetext.CurrentPosToLast();
                    } else if (wholetext.size() > 0 && wholetext.getFirst() == 8) {
                        wholetext.removeAtCurrentPos();
                        wholetext.removeAtCurrentPos();
                        //wholetext.CurrentPosToLast();
                    } else if (wholetext.size() > 0 && wholetext.getFirst() != 8) {
                        if (wholetext.size() >= 1 && wholetext.getCurrentPos() == 8) {
                            wholetext.removeAtCurrentPos();
                            Character tempc =wholetext.removeAtCurrentPos();
                            wholetext.removeNull();
                            wholetext.callstoredInfo(wholetext.returnListCurPos(),tempc,"remove");
                            System.out.print("*"+tempc+"*");
                            System.out.print(wholetext.returnListCurPos().items);
                        }
                        /**store information for redo and undo*/
                        /**a little bug here, turns out when pressed ctrl+z, key_typed is also activated*/
                        else if (!keyEvent.isShortcutDown()) {
                            wholetext.callstoredInfo(wholetext.returnListCurPos(), wholetext.getCurrentPos(), "add");
                            System.out.print("*here*");
                        }
                        characterTyped = wholetext.reCulDisplayText();


                    }
                    /**try to snap when the cursor is not in the screen, it's ugly now
                     * also there is a problem with the mouseclick when scrollbar is around
                    if (wholetext.CurrentY< scrollmoved){
                        textroot.setLayoutY(scrollmoved - wholetext.CurrentY);
                        //scrollBar.adjustValue(scrollmoved - wholetext.CurrentY);
                    }*/
                    /**display*/
                    displayText.setText(characterTyped);
                    keyEvent.consume();
                    centerText();

                } else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                    // Arrow keys should be processed using the KEY_PRESSED event, because KEY_PRESSED
                    // events have a code that we can check (KEY_TYPED events don't have an associated
                    // KeyCode).
                    KeyCode code = keyEvent.getCode();
                    if (code == KeyCode.UP) {
                        wholetext.CurrentPosUp();
                        centerText();
                    } else if (code == KeyCode.DOWN) {
                        wholetext.CurrentPosDown();
                        centerText();
                    } else if (code == KeyCode.LEFT) {
                        wholetext.CurrentPosLeft();
                        centerText();
                    } else if (code == KeyCode.RIGHT) {
                        wholetext.CurrentPosRight();
                        centerText();
                    } else if (keyEvent.isShortcutDown()) {
                        /**make sure the control button is pressed*/
                        if (code == KeyCode.S) {
                            //System.out.print(inputfilename);
                            wholetext.SaveFile(inputfilename);
                            //wholetext.printlineItem();//test  see the output is correct.
                            //System.out.print(wholetext.reCulDisplayText());
                        } else if (code == KeyCode.Z){
                            wholetext.undo();
                        } else if (code == KeyCode.Y){
                            wholetext.redo();
                        }
                    }
                }
            }

        private void centerText() {
            /**size of current text and the cursor*/

            // Calculate the position so that the text will be centered on the screen.
            double textTop = textCenterY ;
            double textLeft = textCenterX ;

            // Re-size and re-position the bounding box.
            cursor.setHeight(13.96);
            cursor.setWidth(1);

            // For rectangles, the position is the upper left hand corner.
            /**recalculate CurrentX and CurrentY*/
            wholetext.CurrentpRecalibrate();

            //double tempd = wholetext.getLastText().getLayoutBounds().getWidth();
            //int lastTextWidth =(int) Math.round(tempd);
            cursor.setX(wholetext.CurrentX+textCenterX);
            cursor.setY(wholetext.CurrentY+textCenterY);

            // Re-position the text.
            displayText.setX(textLeft);
            displayText.setY(textTop);
            // Make sure the text appears in front of any other objects you might add.
            displayText.toFront();

        }
    }

    public static void main(String[] args) {
        Stopwatch sw = new Stopwatch();
        Editor.readinput(args[0]);
        System.out.println("spend "+sw.elapsedTime()+" second to open the file");
        launch(args);
        }

}