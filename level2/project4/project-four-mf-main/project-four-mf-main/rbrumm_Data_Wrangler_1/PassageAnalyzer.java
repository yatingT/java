import java.util.LinkedList;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import javafx.util.Duration;

//--== CS400 File Header Information ==--
//Name: Bill Yan
//Email: wyan34@wisc.edu
//Team:  MF
//Role: Front End Developer
//TA:  Harit
//Lecturer: Gary Dahl
//Notes to Grader: <optional extra notes>

/**
 * Passage Analyzer class that utilizes JavaFX
 * as front end component
 * @author Bill Yan
 *
 */
public class PassageAnalyzer extends Application
{
	TextField input;
	Button load;
	Button analyze;
	Button find;
	TextArea passageBox;
	TextArea outputBox;
	AnchorPane mainPanel;
	PassageBE tool;
	FadeTransition effect;
	
	public static void main(String[] in)
	{
		launch(in);
	}

	@Override
	public void start(Stage window) throws Exception 
	{
		// Tools and presets needed
		tool = new PassageBE();
		mainPanel = new AnchorPane();
		Scene canvas = new Scene(mainPanel);
		
		// Configure the fade transition used
		effect = new FadeTransition();
		effect.setCycleCount(3);
		effect.setDuration(Duration.seconds(0.2));
		effect.setFromValue(0);
		effect.setToValue(1);
		effect.setNode(mainPanel);
		
		// User Input section
		input = new TextField();
		input.setPromptText("Enter the path to passage or the word to find here");
		input.setFont(Font.font("Calibri",16.0));
		input.setPrefWidth(400);
		input.setOnAction(new loadHandler());
		
		Label inputLabel = new Label("Input:");
		inputLabel.setFont(Font.font("Calibri",FontPosture.ITALIC,16.0));
		inputLabel.setLabelFor(input);
		
		// Buttons
		load = new Button("Load");
		load.setPrefWidth(60);
		load.setOnAction(new loadHandler());
		canvas.getAccelerators().put(new KeyCodeCombination(KeyCode.L, KeyCombination.SHORTCUT_DOWN), () -> load.fire());
		
		analyze = new Button("Analyze");
		analyze.setPrefWidth(60);
		analyze.setOnAction(new analyzeHandler());
		canvas.getAccelerators().put(new KeyCodeCombination(KeyCode.J, KeyCombination.SHORTCUT_DOWN), () -> analyze.fire());
		
		find = new Button("Find");
		find.setPrefWidth(60);
		find.setOnAction(new findHandler());
		canvas.getAccelerators().put(new KeyCodeCombination(KeyCode.F, KeyCombination.SHORTCUT_DOWN), () -> find.fire());
		
		FlowPane inputGroup = new FlowPane(inputLabel, input, load, analyze, find);
		inputGroup.setPadding(new Insets(30.0,10.0,30.0,10.0));
		inputGroup.setHgap(10.0);
		inputGroup.setVgap(5.0);
		inputGroup.setAlignment(Pos.CENTER);
		
		// Passage Box Section
		passageBox = new TextArea();
		passageBox.setWrapText(true);
		passageBox.setPromptText("Enter your passage here");
		passageBox.setFont(Font.font(16.0));
		
		Label passageBoxLabel = new Label("Passage Text:");
		passageBoxLabel.setLabelFor(passageBox);
		passageBoxLabel.setFont(Font.font("Calibri",FontPosture.ITALIC,16.0));
		
		FlowPane passageBoxGroup = new FlowPane(passageBoxLabel,passageBox);
		passageBoxGroup.setOrientation(Orientation.VERTICAL);
		passageBoxGroup.setVgap(5.0);
		passageBoxGroup.setAlignment(Pos.TOP_LEFT);
		
		// Program Output Section
		outputBox = new TextArea();
		outputBox.setWrapText(true);
		outputBox.setFont(Font.font(16.0));
		outputBox.setEditable(false);
		outputBox.setText("Welcome to the Passage Analyzer app\n"
						+ "Use the buttons to execute your actions\n"
						+ "Or presss Ctrl + L to load\n"
						+ "Press Ctrl + J to analyze\n"
						+ "Press Ctrl + F to find\n");
		
		Label outputBoxLabel = new Label("Output:");
		outputBoxLabel.setLabelFor(outputBox);
		outputBoxLabel.setFont(Font.font("Calibri",FontPosture.ITALIC,16.0));
		
		FlowPane outputBoxGroup = new FlowPane(outputBoxLabel,outputBox);
		outputBoxGroup.setOrientation(Orientation.VERTICAL);
		outputBoxGroup.setVgap(5.0);
		outputBoxGroup.setAlignment(Pos.TOP_LEFT);
		
		// Menu Bar section
		MenuBar bar = new MenuBar();
		
		ColorPicker BG = new ColorPicker();
		BG.valueProperty().addListener(new colorHandler(mainPanel));
		MenuItem backGroundColor = new MenuItem("Background Color", BG);
		
		ColorPicker T = new ColorPicker();
		T.valueProperty().addListener(new textColorHandler(input, inputLabel, passageBox, passageBoxLabel, outputBox, outputBoxLabel));
		MenuItem textColor = new MenuItem("Text Color", T);
		
		Menu settings = new Menu("Settings");
		settings.getItems().addAll(backGroundColor, textColor);
		
		bar.getMenus().add(settings);
		
		// Compile Section
		mainPanel.setPadding(new Insets(10));
		mainPanel.getChildren().addAll(bar, inputGroup, passageBoxGroup, outputBoxGroup);

		window.heightProperty().addListener((observable, oldValue, newValue) -> 
		{
			double width = (double)window.getWidth()/2 - 40;
			passageBox.setPrefWidth(width);
			outputBox.setPrefWidth(width);
			
			double height = (double)newValue - inputGroup.getHeight() - 80 - passageBoxLabel.getHeight();
			passageBox.setPrefHeight(height);
			outputBox.setPrefHeight(height);
			
			mainPanel.setTopAnchor(passageBoxGroup, inputGroup.getHeight());
			mainPanel.setTopAnchor(outputBoxGroup, inputGroup.getHeight());
		});
		window.widthProperty().addListener((observable, oldValue, newValue) ->
		{
			double width = (double)newValue/2 - 40;
			passageBox.setPrefWidth(width);
			outputBox.setPrefWidth(width);
			
			double height = (double)window.getHeight() - inputGroup.getHeight() - 80 - passageBoxLabel.getHeight();
			passageBox.setPrefHeight(height);
			outputBox.setPrefHeight(height);
			
			mainPanel.setTopAnchor(passageBoxGroup, inputGroup.getHeight());
			mainPanel.setTopAnchor(outputBoxGroup, inputGroup.getHeight());
		});
		
		// Adding mysterious context menu
		MenuItem link = new MenuItem("Link to a mysterious website");
		MenuItem link2 = new MenuItem("Link to another one");
		link.setOnAction((event) -> 
		{
			HostServices locale = PassageAnalyzer.this.getHostServices();
			locale.showDocument("http://www.bilibili.com");
		});
		link2.setOnAction((event) ->
		{
			HostServices locale = PassageAnalyzer.this.getHostServices();
			locale.showDocument("http://wis.edu");
		});
		ContextMenu surprise = new ContextMenu(link,link2);
		canvas.setOnContextMenuRequested((event) -> surprise.show(mainPanel,event.getScreenX(),event.getScreenY()));
		canvas.setOnMouseClicked((event) -> surprise.hide());;
		
		
		// Decorate the window
		window.setScene(canvas);
		window.setTitle("Passage Analyzer");
		window.getIcons().add(new Image("file:icon.png"));
		window.setHeight(540);
		window.setWidth(800);		
		window.show();
		
		// Organize the initial layout
		mainPanel.setLeftAnchor(inputGroup, 0.0);
		mainPanel.setRightAnchor(inputGroup, 0.0);
		mainPanel.setTopAnchor(inputGroup, 0.0);
		mainPanel.setLeftAnchor(passageBoxGroup, 10.0);
		mainPanel.setTopAnchor(passageBoxGroup, 90.0);
		mainPanel.setBottomAnchor(passageBoxGroup, 10.0);
		mainPanel.setRightAnchor(outputBoxGroup, 10.0);
		mainPanel.setTopAnchor(outputBoxGroup, 90.0);
		mainPanel.setBottomAnchor(outputBoxGroup, 10.0);
		mainPanel.setLeftAnchor(bar, 0.0);
		mainPanel.setRightAnchor(bar, 0.0);
		passageBox.setPrefHeight(350);
		outputBox.setPrefHeight(350);
	}
	
	// Event handlers below
	class loadHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent arg0) 
		{
			String path = input.getText();
			
			if (path.equals(""))
			{
				outputBox.setText("No path was specified, please try again");
				effect.play();
				return;
			}
			
			Passage passage = tool.analyzePath(path);
			String text = passage.getText();
			
			if (text == null)
			{
				outputBox.setText("The file specified by the path is not found, please try again");
				effect.play();
			}
			else
				passageBox.setText(text);
		}
	}
	
	class analyzeHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent e)
		{
			String text = passageBox.getText();
			
			if (text.equals(""))
			{
				outputBox.setText("No passage is found inside the passage box, please try again");
				effect.play();
				return;
			}
			
			Passage passage = tool.analyzePassage(text);
			
			int wordCount = tool.getWordCount(passage);
			String list = tool.getWordList(passage);
			String mostUsed = tool.getMostUsedWord(passage);
			String prompt = "Your passage contains a total of " + wordCount + " words\n\n" + 
			                "Your most used word is: " + mostUsed + "\n\n" + "The list of words you used is: \n" + list;
			
			outputBox.setText(prompt);
		}
	}
	
	class findHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent e)
		{
			String word = input.getText().trim();
			
			if (word.equals(""))
			{
				outputBox.setText("No word was entered, please try again");
				effect.play();
				return;
			}
			
			String text = passageBox.getText();
			
			if (text.equals(""))
			{
				outputBox.setText("No passage is found inside the passage box, please try again");
				effect.play();
				return;
			}
			
			if (text.contains(word))
				highLight(word);
			else
				outputBox.setText("The word/phrase: '" + word + "' is not present in the passage");
		}
		
		private void highLight(String word)
		{
			int index = passageBox.getCaretPosition();
			passageBox.requestFocus();
			
			String leftText = passageBox.getText().substring(index);
			if (leftText.contains(word))
			{
				int add = leftText.indexOf(word);
				passageBox.selectRange(index+add, index+add+word.length());
			}
			else
			{
				index = passageBox.getText().indexOf(word);
				passageBox.selectRange(index, index+word.length());
			}
		}
	}
	
	class colorHandler implements ChangeListener<Color>
	{
		LinkedList<Node> items;

		public colorHandler(Node ... nodes)
		{
			super();
			add(nodes);
		}
		
		@Override
		public void changed(ObservableValue<? extends Color> list, Color oldValue, Color newValue) 
		{
			items.forEach((node) -> node.setStyle("-fx-background-color:#" + newValue.toString().substring(2)));
		}
		
		public void add(Node ... nodes)
		{
			if (items == null)
				items = new LinkedList<Node>();
			
			for (Node n : nodes)
				items.add(n);
		}
	}
	
	class textColorHandler extends colorHandler
	{
		public textColorHandler(Node ... nodes)
		{
			super(nodes);
		}
		
		@Override
		public void changed(ObservableValue<? extends Color> list, Color oldValue, Color newValue)
		{
			items.forEach((node) -> node.setStyle("-fx-text-fill:#" + newValue.toString().substring(2)));
		}
	}
}
