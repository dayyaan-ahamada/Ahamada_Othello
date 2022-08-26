package GUI;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import DB.Connect;
import DB.Selecter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Score extends VBox{
	private Button retour = new Button("Retour");

	@SuppressWarnings("unchecked")
	public Score() {
		
		//general VBox options
		VBox box1 = new VBox();
		VBox box2 = new VBox();
		VBox box3 = new VBox();
		VBox.setVgrow(box1, Priority.ALWAYS);
		VBox.setVgrow(box2, Priority.ALWAYS);
		VBox.setVgrow(box3, Priority.ALWAYS);
		this.setStyle("-fx-alignment:center");
				
		//Image VBox options		
		Class<?> clazz = GameOver.class; 
		InputStream input = clazz.getResourceAsStream("../images/Othello.png");
		Image image = new Image(input);
		ImageView view = new ImageView(image);
		view.setPreserveRatio(true);
		view.fitWidthProperty().bind(this.widthProperty());
		box1.getChildren().addAll(view);
		this.getChildren().add(box1);
		
		//score box options
		TableView<ScoreModel> table = new TableView<ScoreModel>();
        table.setEditable(true);

		TableColumn<ScoreModel, String> pseudoColumn = new TableColumn<ScoreModel, String>("Pseudo");
		pseudoColumn.setPrefWidth(300);
		pseudoColumn.setCellValueFactory(new PropertyValueFactory<ScoreModel, String>("pseudo"));
		TableColumn<ScoreModel, Integer> scoreColumn = new TableColumn<ScoreModel, Integer>("Score");
		scoreColumn.setPrefWidth(300);
		scoreColumn.setCellValueFactory(new PropertyValueFactory<ScoreModel, Integer>("score"));

		table.getColumns().addAll(pseudoColumn, scoreColumn);
		Connect connect = new Connect();
		connect.connectDB();
		Selecter selecter = new Selecter();
		List<ScoreModel> scoresList = new ArrayList<ScoreModel>();
		scoresList = selecter.select();
		final ObservableList<ScoreModel> data = FXCollections.observableArrayList(scoresList);
		table.setItems(data);
		
		box2.setMaxSize(600, 600);
		box2.getChildren().add(table);
		this.getChildren().add(box2);

		//Buttons VBox options		
		box3.setStyle("-fx-alignment: center;");
		this.retour.setMaxSize(300, 100);
		VBox.setVgrow(this.retour, Priority.ALWAYS);
		box3.getChildren().add(this.retour);
		this.getChildren().add(box3);
	}
	
	@SuppressWarnings("unchecked")
	public void rechargeTab() {
		this.getChildren().clear();
		VBox box1 = new VBox();
		VBox box2 = new VBox();
		VBox box3 = new VBox();
		VBox.setVgrow(box1, Priority.ALWAYS);
		VBox.setVgrow(box2, Priority.ALWAYS);
		VBox.setVgrow(box3, Priority.ALWAYS);
		this.setStyle("-fx-alignment:center");
				
		//Image VBox options		
		Class<?> clazz = GameOver.class; 
		InputStream input = clazz.getResourceAsStream("Othello.png");
		Image image = new Image(input);
		ImageView view = new ImageView(image);
		view.setPreserveRatio(true);
		view.fitWidthProperty().bind(this.widthProperty());
		box1.getChildren().addAll(view);
		this.getChildren().add(box1);
		
		//score box options
		TableView<ScoreModel> table = new TableView<ScoreModel>();
        table.setEditable(true);

		TableColumn<ScoreModel, String> pseudoColumn = new TableColumn<ScoreModel, String>("Pseudo");
		pseudoColumn.setPrefWidth(300);
		pseudoColumn.setCellValueFactory(new PropertyValueFactory<ScoreModel, String>("pseudo"));
		TableColumn<ScoreModel, Integer> scoreColumn = new TableColumn<ScoreModel, Integer>("Score");
		scoreColumn.setPrefWidth(300);
		scoreColumn.setCellValueFactory(new PropertyValueFactory<ScoreModel, Integer>("score"));

		table.getColumns().addAll(pseudoColumn, scoreColumn);
		Connect connect = new Connect();
		connect.connectDB();
		Selecter selecter = new Selecter();
		List<ScoreModel> scoresList = new ArrayList<ScoreModel>();
		scoresList = selecter.select();
		final ObservableList<ScoreModel> data = FXCollections.observableArrayList(scoresList);
		table.setItems(data);
		
		box2.setMaxSize(600, 600);
		box2.getChildren().add(table);
		this.getChildren().add(box2);

		//Buttons VBox options		
		box3.setStyle("-fx-alignment: center;");
		this.retour.setMaxSize(300, 100);
		VBox.setVgrow(this.retour, Priority.ALWAYS);
		box3.getChildren().add(this.retour);
		this.getChildren().add(box3);
	}
	
	public Button getRetour() {
		return this.retour;
	}
}
