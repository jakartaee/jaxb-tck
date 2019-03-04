/*
 * Copyright (c) 2002, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 *
 * */

package javasoft.sqe.javatest.lib;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.net.MalformedURLException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import javasoft.sqe.javatest.Status;
import javasoft.sqe.javatest.lib.MultiTest;


/**
 * Abstract base class for interactive tests.
 * <P>Interactive tests should be derived from this class.
 * This interactive test framework displays a frame that contains test
 * information, a test panel, and a user interface.
 * The user interface provides a button for pausing test execution, and
 * is used for getting the user's response.  It also displays the amount
 * of time left for test execution.
 *
 * <P>The default constructor of this class uses the "YesNo" interactive mode.
 * One non-default constructor requires the interactive mode (a String) to be
 * passed in; it should be either "YesNo" or "Done".
 * The other non-default constructor takes the interactive mode and a boolean
 * value for the testdirurlRequired parameter; the testdirurlRequired parameter
 * should be set to true if an argument of -TestDirURL is required by the test.
 * An example of a test that requires this parameter is a test that uses images
 * or other resources.
 *
 * <P>The three panels contained in the test frame (information panel, test
 * panel, and user interface panel) are displayed in a scrollpane.
 * The interactive test framework sets the frame's size to 640X480.
 * Tests can override this dimension by invoking the setFrameSize() method.
 * If the test frame is larger than the screen, the size of the frame is set
 * to the screensize.
 *
 * <P>Interactive test execution is controlled by a timeout; an interactive test
 * will time out if the user does not respond within a pre-determined amount
 * of time.  The default timeout is 90 seconds.  This variable can be
 * modified by invoking the setTimeout() method.  The amount of time left
 * for test execution is displayed in the test frame.  Test execution can be
 * paused by pressing the 'Pause' button contained in the test frame.
 *
 * <P>The "YesNo" mode should be used when a Yes/No user interface is needed.
 * This interface consists of a question, a Yes button, and a No button.
 * The Yes button indicates that the test has passed, and the No button
 * indicates test failure.
 * If the No button is pressed, a textfield is presented, which can be used
 * to provide test failure details.
 * <P>The "Done" mode should be used when a Done user interface is needed.
 * This interface consists of an instruction (initialized to "Press Done when
 * finished.") and a Done button.
 *
 * <P>Tests that use the Yes/No interface should invoke the following methods:
 * <DL>
 * <DT><B>addInfo(String)</B>
 * <DT><B>addTestPanel(Component)</B>
 * <DT><B>setQuestion(String)</B>
 * <DT><B>setStatusMessages(String, String)</B>
 * </DL>
 *
 * <P>Tests that use the Done interface should invoke the following methods:
 * <DL>
 * <DT><B>addInfo(String)</B>
 * <DT><B>addTestPanel(Component)</B>
 * <DT><B>setDoneInstruction(String)</B> --  (this is optional)
 * <DT><B>setStatusMessages(String, String)</B>
 * <DT><B>checkResult()</B>
 * </DL>
 *
 * <P>In addition, interactive tests can optionally invoke the following methods:
 * <DL>
 * <DT><B>setFrameSize(int, int)</B>
 * <DT><B>setFrameTitle(String)</B>
 * <DT><B>setTimeout(int)</B>
 * <DT><B>doTestCleanup()</B>
 * </DL>
 * <DL>
 * <DT><B>addInfo()</B> adds instructional/informational content to the interactive test
 * frame.
 * <DT><B>addTestPanel()</B> adds the test panel to the interactive test frame.
 * <DT><B>setQuestion()</B> sets the question that is presented to the Yes/No
 * interface user.
 * <DT><B>setStatusMessages()</B> sets the strings that are printed when tests pass and
 * fail.
 * <DT><B>setDoneInstruction()</B> sets the string that is presented to the Done
 * interface user.
 * <DT><B>checkResult()</B> is used by tests that use the Done interface to check
 * results and declare pass/fail.  This method should invoke <B>setStatus()</B>.
 * <DT><B>setFrameSize()</B> sets the size of the test frame (default size is 640X480).
 * <DT><B>setFrameTitle()</B> sets the title of the test frame.
 * <DT><B>setTimeout()</B> sets the test timeout (timeout defaults to 90 seconds).
 * <DT><B>doTestCleanup()</B> performs any necessary cleanup that the derived test needs
 * to do.
 * </DL>
 *
 * <P>For examples of how to use this class see the
 * tests/api/java_awt/interactive test classes.
 *
 * @see javasoft.sqe.javatest.lib.MultiTest
 *
 * @author Gauri Sharma
 */
public abstract class InteractiveTest extends MultiTest {
    private Status status;
    private Panel panel = null; // Panel for test
    private Panel infoPanel = null; // User info
    private Panel controlPanel = null; // User interface
    private Panel buttonPanel; // Yes/No buttons
    private Panel failureInfoPanel = null; // Test failure info
    private TimePanel timePanel; // Panel for time display and pause button
    private Panel userPanel; // Panel for buttonPanel and failureInfoPanel
    private Label userLabel; // Label for user question/instr. text
    private TextField failureInfoField; // Textfield for failure info
    private Button yesButton, noButton, doneButton;
    private GridBagLayout gridbag = new GridBagLayout();
    private GridBagConstraints c = new GridBagConstraints();
    private int timeout = 90; // Set by invoking setTimeout()
    private int timeRemaining; // Remaining time for test execution
    private Thread timerThread; // Thread to allow user to pause test execution
    private boolean testExecutionPaused = false; // Set to true if user pauses test execution
    private Listener listener = null;
    private String mode = null; // Test mode; Yes/No or Done
    private boolean testdirurlRequired = false; // True if test requires -TestDirURL parameter
    private String testdir = null; // Directory indicated by TestDirURL parameter
    private boolean frameSizeSet = false; // Indicates whether test has set frame size
    private Toolkit toolkit;
    private Dimension frameSize; // Size of test frame
    private Dimension screenSize; // Size of screen
    private ScrollPane scrollPane = new ScrollPane(); // Scrollpane for test
    public Font XXXLrgDialogFont = new Font("Dialog", Font.PLAIN, 26);
    public Font XXLrgDialogFont = new Font("Dialog", Font.PLAIN, 24);
    public Font XLrgDialogFont = new Font("Dialog", Font.PLAIN, 22);
    public Font lrgDialogFont = new Font("Dialog", Font.PLAIN, 20);
    public Font medDialogFont = new Font("Dialog", Font.PLAIN, 16);
    public Font smDialogFont = new Font("Dialog", Font.PLAIN, 14);
    public Font XsmDialogFont = new Font("Dialog", Font.PLAIN, 12);

    /**
     * Main test frame.
     * Created in setUp().
     */
    protected Frame testFrame = null;

    /**
     * Question presented to a user of the "YesNo" interface.
     * Set by invoking setQuestion().
     */
    protected String question = "";

    /**
     * Instruction presented to a user of the "Done" interface.
     * Set by invoking setDoneInstruction().
     */
    protected String doneInstruction = "Press Done when finished.";

    /**
     * Message printed if test passes.
     * Set by invoking setStatusMessages().
     */
    protected String passMessage = "";

    /**
     * Message printed if test fails.
     * Set by invoking setStatusMessages().
     */
    protected String failMessage = "";

    /**
     * A string representation of the test directory URL passed
     * in via the TestDirURL argument.
     * A derived class can retrieve the full test directory URL
     * from this variable.
     */
    public String testdirurl = null;

    /**
     * Default constructor; it uses the "Yes/No" interactive mode.
     */
    public InteractiveTest() {
        this("YesNo");
    }

    /**
     * Constructor which uses the specifed mode.
     *
     * @param mode User interface mode.
     *         "Yes/No" or "Done".
     */
    protected InteractiveTest(String mode) {
        this.mode = mode;
    }

    /**
     * Constructor which uses the specified mode, and requires a
     * TestDirURL parameter to be supplied to the derived test if
     * testdirurlRequired is true.
     *
     * @param mode User interface mode.
     *         "Yes/No" or "Done".
     * @param testdirurlRequired Whether TestDirURL parameter is required or not.
     *                       true or false.
     */
    protected InteractiveTest(String mode, boolean testdirurlRequired) {
        this.mode = mode;
        this.testdirurlRequired = testdirurlRequired;
    }

    /**
     *
     * Checks if the TestDirURL argument is supplied.
     *
     * @exception SetupException raised if the TestDirURL argument is not
     * specified.
     *
     */
    protected void init() throws SetupException {
        if ((testdirurlRequired) && (testdirurl == null)) {
            throw new SetupException("No TestDirURL specified");
        }
    }

    /**
     * Decodes current work and test directories.
     *
     * This method tries to decode an InteractiveTest-specific argument,
     * a test directory supplied after "-TestDirURL" from argv starting
     * with the current index.
     *
     * @param argv execute arguments from the test harness or from the
     *             command line.
     * @param index current index into argv.
     *
     * @return number of arguments decoded if InteractiveTest-specific
     * argument is recognized, or
     *         <CODE>super.decodeArg(argv, index)</CODE> otherwise.
     *
     * @exception SetupException raised when an invalid argument is passed,
     * or another error occurred.
     *
     */
    protected int decodeArg(String argv[], int index) throws SetupException {
        if (argv[index].equals("-TestDirURL")) {
            if (++index < argv.length && !argv[index].startsWith("-")) {
                try {
                    testdirurl = (argv[index]).substring(0,
                            (argv[index]).lastIndexOf("/") + 1);
                    URL testdirURL = new URL(testdirurl);
                    testdir = (testdirURL.getProtocol().equals("file")) ?
                            testdirURL.getFile().replace('/',
                            File.separatorChar) : "";
                    return 2;
                } catch (MalformedURLException e) {
                    throw new SetupException("Invalid URL: " + argv[index]);
                }
            } else {
                throw new SetupException("No TestDirURL specified");
            }
        } else {
            return super.decodeArg(argv, index);
        }
    }

    /**
     * Invokes each test case.
     *
     * @see javasoft.sqe.javatest.Status
     *
     * @param m Testcase to execute
     * @return Status of running the test case
     */
    protected Status invokeTestCase(Method m)
            throws IllegalAccessException, InvocationTargetException {
        Object[] testArgs = { };
        toolkit = Toolkit.getDefaultToolkit();
        screenSize = toolkit.getScreenSize();
        status = null;
        setUp();
        Object o = m.invoke(this, testArgs);

        // Create Yes/No or Done user interface as required
        if (mode.equals("YesNo")) {
            createYNInterface();
        } else if (mode.equals("Done")) {
            createDoneInterface();
        }

        // Set size of frame to screensize if frame is

        // larger than screen
        frameSize = testFrame.getSize();

        if ((frameSize.width > screenSize.width)
                || (frameSize.height > screenSize.height)) {
            testFrame.setSize(screenSize);
        }
        testFrame.setVisible(true);
        testExecutionPaused = false;
        timeRemaining = timeout;
        startRecordingTime();

        try {
            waitForStatus();
        } catch (InterruptedException e) {
            return Status.failed(e.toString());
        }
        cleanUp();
        return (status); // status is an accumulation
    }

    /**
     * Sets pass/fail test status.
     * Called by listener when the user indicates that the test is done by
     * pressing the Yes button, the No button, or the Done button.
     *
     * @see javasoft.sqe.javatest.Status
     *
     * @param status Pass/Fail status.
     */
    protected synchronized void setStatus(Status status) {
        this.status = status;
        notify();
    }

    /**
     * Starts the thread that updates the amount of time left for test
     * execution.
     */
    protected void startRecordingTime() {
        timePanel.start();
    }

    /**
     * Waits for the user to indicate test completion.
     * Times out if the user doesn't respond within the timeout limit.
     *
     * @exception InterruptedException If another thread has interrupted the
     *            current thread.
     */
    protected synchronized void waitForStatus() throws InterruptedException {
        try {
            while ((status == null) & (timeRemaining > 0)) {
                if (!testExecutionPaused) {
                    wait(1000);
                    timeRemaining--;
                } else {
                    wait(10000);
                }
            }
        } catch (InterruptedException e) {}

        if (status == null) {
            status = Status.failed("No response from user.");
        }
    }

    /**
     * Pauses test execution.
     */
    protected synchronized void pauseTestExecution() {
        testExecutionPaused = true;
        notify();
    }

    /**
     * Resumes test execution.
     */
    protected synchronized void resumeTestExecution() {
        testExecutionPaused = false;
        notify();
    }

    /**
     * Adds user information to the information panel in the test frame.
     *
     * @param info Information to add to user information panel.
     */
    protected void addInfo(String info) {
        infoPanel.add(new Label(info, Label.CENTER));
    }

    /**
     * Adds a scrollpane containing the test panel to the test frame.
     *
     * @param testpanel Panel containing test.
     */
    protected void addTestPanel(Component testpanel) {
        addTestPanel("Center", testpanel);
    }

    /**
     * Adds a scrollpane containing the test panel to the test frame with the
     * specified border layout orientation.
     * Should be used if more than one test panel is to be added to the test
     * frame.
     * For example, image comparison can be performed by loading 2 image panels.
     *
     * @param orientation Orientation in a border layout ("West" or "East")
     *
     * @param testpanel Panel containing test.
     */
    protected void addTestPanel(String orientation, Component testpanel) {
        panel.add(orientation, testpanel);
    }

    /**
     * Sets the question presented to a user of the Yes/No user interface.
     *
     * @param question Question presented to user using Yes/No interface.
     */
    protected void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Sets the messages that get printed upon test pass/fail.
     *
     * @param passMessage Message printed if test passes.
     *
     * @param failMessage Message printed if test fails.
     */
    protected void setStatusMessages(String passMessage, String failMessage) {
        this.passMessage = passMessage;
        this.failMessage = failMessage;
    }

    /**
     * Sets the instruction for pressing the Done button.
     *
     * @param doneInstruction Instruction presented to user using Done interface.
     */
    protected void setDoneInstruction(String doneInstruction) {
        this.doneInstruction = doneInstruction;
    }

    /**
     * Sets the test frame's title.
     *
     * @param title Title given to test frame.
     */
    protected void setFrameTitle(String title) {
        testFrame.setTitle(title);
    }

    /**
     * Sets the test frame's size.
     *
     * @param width Width of test frame.
     *
     * @param height Height of test frame.
     */
    protected void setFrameSize(int width, int height) {
        testFrame.setSize(width, height);
    }

    /**
     * Sets the test's timeout.
     *
     * @param seconds Number of seconds that elapse before the test times out.
     */
    protected void setTimeout(int seconds) {
        timeout = seconds;
    }

    /**
     * Returns the time remaining for test execution.
     *
     * @return timeRemaining Time remaining for test execution
     */
    protected int getTimeRemaining() {
        return timeRemaining;
    }

    /**
     * Checks the test's result and declares pass/fail.
     * Overridden by self-checking tests to determine the test result after
     * user presses the Done button.
     * This method should be overriden if the derived test uses the Done user
     * interface.  The checkResult method should invoke setStatus after
     * determining the test result.
     */
    protected void checkResult() {
        setStatus(Status.failed("No result checking was performed by test."));
    }

    /**
     * Performs cleanup that is specific to the derived test.
     * Test should override this method if the test needs to do cleanup before
     * exiting.
     * Tests which display Container objects (such as Frame and Dialog) should
     * override this method to dispose() the containers.
     */
    protected void doTestCleanup() {}

    /**
     * Creates a frame that contains a scrollpane with user info, the test
     * panel, and the user interface.
     * Creates a listener for pausing test execution and for receiving the
     * user's response.
     */
    protected void setUp() {

        // Frame to contain info, test panel, and user interface
        testFrame = new Frame();

        // set frame size to a default size; test can override frame size
        testFrame.setSize(640, 480);
        testFrame.setLayout(new BorderLayout());
        testFrame.setBackground(Color.lightGray);

        // Panel to contain info, test panel, and user interface
        panel = new Panel();
        panel.setLayout(new BorderLayout());

        // Panel to display usage and expected result info
        infoPanel = new Panel();
        infoPanel.setLayout(new GridLayout(0, 1));
        infoPanel.setBackground(new Color(225, 235, 225)); // light blue
        infoPanel.setForeground(Color.black);

        // Panel to display user interface
        controlPanel = new Panel();
        controlPanel.setLayout(gridbag);
        controlPanel.setForeground(Color.black);

        // Add scrollpane with panel containing user info and user interface

        // to test frame
        panel.add("North", infoPanel);
        panel.add("South", controlPanel);
        scrollPane.add(panel);
        scrollPane.setScrollPosition(0, 0); // reset the viewing window to the top
        testFrame.add("Center", scrollPane);

        // Listener for user actions
        listener = new Listener();
    }

    /**
     * Removes the test frame.
     * Called when the test is done.
     */
    protected void cleanUp() {
        doTestCleanup();

        if (timePanel != null) {
            timePanel.stop();
        }

        if (testFrame != null) {
            testFrame.dispose();
        }
    }

    /**
     * Creates the user interface for the Yes/No interactive mode.
     */
    protected void createYNInterface() {
        userPanel = new Panel();
        userLabel = new Label(question, Label.CENTER);
        buttonPanel = new Panel();
        failureInfoPanel = new Panel();
        yesButton = new Button("Yes");
        yesButton.setBackground(Color.green);
        yesButton.addActionListener(listener); // add listener for user's "yes" response
        noButton = new Button("No");
        noButton.setBackground(Color.red);
        noButton.addActionListener(listener); // add listener for user's "no" response
        failureInfoField = new TextField();

        // Add panel containing pause button and remaining time info
        timePanel = new TimePanel();
        c.insets = new Insets(15, 0, 0, 0);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(timePanel, c);
        controlPanel.add(timePanel);

        // Add question
        c.insets = new Insets(30, 0, 10, 0);
        gridbag.setConstraints(userLabel, c);
        userLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        controlPanel.add(userLabel);

        // Add Yes/No buttons to buttonPanel
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        // Add textfield for test failure info to failureInfoPanel
        GridBagLayout gridbag2 = new GridBagLayout();
        GridBagConstraints c2 = new GridBagConstraints();
        failureInfoPanel.setLayout(gridbag2);
        c2.weightx = 1;
        c2.fill = GridBagConstraints.HORIZONTAL;
        gridbag2.setConstraints(failureInfoField, c2);
        failureInfoField.setBackground(Color.white);
        failureInfoField.setForeground(Color.black);
        failureInfoPanel.add(failureInfoField);
        failureInfoField.addActionListener(listener);

        // Add buttonPanel and failureInfoPanel as cards to userPanel
        userPanel.setLayout(new CardLayout());
        userPanel.add("buttons", buttonPanel);
        userPanel.add("textfield", failureInfoPanel);

        // Add userPanel to controlPanel
        c.insets = new Insets(0, 0, 10, 0);
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        gridbag.setConstraints(userPanel, c);
        controlPanel.add(userPanel);
    }

    /**
     * Creates the user interface for the Done interactive mode.
     */
    protected void createDoneInterface() {
        Label dLabel = new Label(doneInstruction);
        doneButton = new Button("Done");
        doneButton.setBackground(Color.green);
        doneButton.setForeground(Color.black);
        doneButton.addActionListener(listener); // add listener for user's "done" response

        // Add panel containing pause button and remaining time info
        timePanel = new TimePanel();
        c.insets = new Insets(15, 0, 0, 0);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(timePanel, c);
        controlPanel.add(timePanel);

        // Add done instruction
        c.insets = new Insets(30, 0, 10, 0);
        gridbag.setConstraints(dLabel, c);
        dLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        controlPanel.add(dLabel);

        // Add Done button
        c.insets = new Insets(0, 0, 10, 0);
        gridbag.setConstraints(doneButton, c);
        controlPanel.add(doneButton);
    }

    /**
     * Gets test failure info from user.
     * Presents textfield for user to provide test failure details.
     */
    protected void getFailureInfo() {

        // Display textfield
        ((CardLayout) userPanel.getLayout()).show(userPanel, "textfield");

        // Update label to request test failure info
        userLabel.setText(
                "Please provide test failure details below and press <ENTER> or <RETURN>.");
        controlPanel.validate();
        testFrame.validate();
        failureInfoField.requestFocus();
    }

    /**
     * Updates the label of the Pause/Resume button.
     *
     * @param o Pause/Resume button
     * @param newLabel New label of button
     */
    protected void setButtonText(Object o, String newLabel) {
        ((Button) o).setLabel(newLabel);
    }

    /**
     * Returns the textfield that is used for getting test failure
     * info from user.
     *
     * @return textfield that is used for getting test failure info
     */
    protected Component getFailureInfoField() {
        return failureInfoField;
    }

    /**
     * Returns the text that user enters in test failure info textfield
     *
     * @return text that user enters in test failure info textfield
     */
    protected String getFailureInfoText() {
        return failureInfoField.getText();
    }


    /**
     * Listener for pausing test execution and for receiving the user's
     * response.
     */
    protected class Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String name = e.getActionCommand();
            Object source = e.getSource();
            Button b = null;

            if (name == "Yes") {
                setStatus(Status.passed(passMessage)); // pass
            } else if (name == "No") {
                setButtonText(source, "Resume"); // stop the timer
                pauseTestExecution();
                getFailureInfo();
            } else if (name == "Done") {
                checkResult(); // test should override this if using Done interface
            } else if (name == "Pause") {
                setButtonText(source, "Resume");
                pauseTestExecution();
            } else if (name == "Resume") {
                setButtonText(source, "Pause");
                resumeTestExecution();
            } else if (source == getFailureInfoField()) {
                String failureInfo = getFailureInfoText();
                setStatus(Status.failed(failMessage + " " + failureInfo)); // fail
            }
        }
    }


    /**
     * Panel to display remaining time and pause button.
     */
    protected class TimePanel extends Panel implements Runnable {
        private Label timeRemainingLabel;
        private Label timeRemainingField;
        private Button pauseButton;

        TimePanel() {
            setLayout(new FlowLayout());
            setBackground(new Color(120, 217, 255));
            timeRemainingLabel = new Label("Time remaining:");
            timeRemainingLabel.setForeground(Color.black);
            timeRemainingLabel.setFont(new Font("Dialog", Font.ITALIC, 14));
            timeRemainingField = new Label("      ");
            timeRemainingField.setForeground(Color.black);
            timeRemainingField.setFont(new Font("Dialog", Font.ITALIC, 14));
            pauseButton = new Button("Pause");
            pauseButton.setBackground(Color.yellow);
            pauseButton.setForeground(Color.black);
            add(timeRemainingLabel);
            add(timeRemainingField);
            add(new Label("           "));
            add(pauseButton);
            pauseButton.addActionListener(listener);
        }

        void start() {
            timerThread = new Thread(this);
            timerThread.start();
        }

        synchronized void stop() {
            timerThread = null;
            notify();
        }

        public synchronized void run() {
            try {
                Thread thisThread = Thread.currentThread();
                //while (true) {
                while (timerThread == thisThread) {
                    wait(1000);
                    update();
                }
            } catch (InterruptedException e) {}
        }

        private void update() {
            if (timeRemainingField != null) {
                timeRemainingField.setText(secondsToString(timeRemaining));
            }
        }

        private final String secondsToString(int time) {
            StringBuffer sb = new StringBuffer(5);
            int seconds = time % 60;
            int minutes = time / 60;

            if (minutes < 10) {
                sb.append('0');
            }
            sb.append(minutes);
            sb.append(':');

            if (seconds < 10) {
                sb.append('0');
            }
            sb.append(seconds);
            return sb.toString();
        }
    }
}
