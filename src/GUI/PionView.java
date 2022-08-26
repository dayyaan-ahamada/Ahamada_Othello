package GUI;

import java.io.InputStream;

import Game.Couleur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PionView extends ImageView{
	//constructor
	public PionView(Couleur color) {
		Class<?> clazz = PionView.class;
		if(color == Couleur.Noir) {
			InputStream input = clazz.getResourceAsStream("../images/BlackPion.png");
			Image image = new Image(input);
			this.setImage(image);
		}else {
			InputStream input = clazz.getResourceAsStream("../images/WhitePion.png");
			Image image = new Image(input);
			this.setImage(image);
		}
	}
}
