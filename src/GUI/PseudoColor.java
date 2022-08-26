package GUI;
import java.io.InputStream;

import Game.Couleur;
import Game.Partie;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PseudoColor extends StackPane{
	//attributes
	private  ImageView black;
	private  ImageView white;
	private Text text;
	
	//constructor
	public PseudoColor(Partie p) {
		//initialisation de la view du pion noir
		Class<?> clazz = PseudoColor.class; 
		InputStream input = clazz.getResourceAsStream("../images/BlackPion.png");
		Image image = new Image(input);
		ImageView view = new ImageView(image);

		//initialisation de la view du pion blanc
		Class<?> clazz2 = PseudoColor.class; 
		InputStream input2 = clazz2.getResourceAsStream("../images/WhitePion.png");
		Image image2 = new Image(input2);
		ImageView view2 = new ImageView(image2);
		
		//initialisation du pseudo inplay
		Text t1 = new Text(p.getInplay().getPseudo());
		
		//initialisation des attributs
		this.black = view;
		this.white = view2;
		this.text = t1;

		//Ajout des pions et pseudos en fonction du joueur inPlay
		if(p.getInplay().getColor() == Couleur.Noir){
			this.text.setFill(Color.WHITE);
			this.getChildren().add(this.black);
			this.getChildren().add(this.text);
		}else{
			this.text.setFill(Color.BLACK);
			this.getChildren().add(this.white);
			this.getChildren().add(this.text);
		}
	}
}
