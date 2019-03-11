package O01;

import javax.swing.*;
import java.awt.*;

//TODO Transform the code to be used safely in a concurrent context.
public class Ball {

	//TODO  Find an archive named Ball.png
	private String Ball = "Ball.png"; 

	private double x,y,dx,dy;
	private double v,fi;
	private Image image;
	private final int IMG_TAM_X,IMG_TAM_Y;

	public Ball() {

		ImageIcon ii = new ImageIcon(this.getClass().getResource(Ball));
		image = ii.getImage();
		
		//TODO Depend of image size
        // El tamaño de la imagen Ball.png coincide con el especificado, 32x32
		IMG_TAM_X = 32;
		IMG_TAM_Y = 32;
		
		x = Billiards.Width/4-16;
		y = Billiards.Height/2-16;
		v = 5;
		fi =  Math.random() * Math.PI * 2;

	}

	public synchronized void move() {

		v = v*Math.exp(-v/1000);
		dx = v*Math.cos(fi);
		dy = v*Math.sin(fi);
		if (Math.abs(dx) < 1 && Math.abs(dy) < 1) {

			dx = 0;
			dy = 0;

		}
		x += dx;   
		y += dy;
		
		reflect();
		
		//TODO Check postcondition
		// assert (x > Board.LEFTBOARD && x < Board.RIGHTBOARD && y > Board.TOPBOARD && y < Board.BOTTOMBOARD);

		assert x < Board.RIGHTBOARD: String.format("Posición[x: (%f)] fuera del tablero por la derecha", x);
		assert x > Board.LEFTBOARD: String.format("Posición[x: (%f)] fuera del tablero por la izquierda", x);
		assert y > Board.TOPBOARD: String.format("Posición[y: (%f)] fuera del tablero por  arriba", y);
		assert y < Board.BOTTOMBOARD: String.format("Posición[y: (%f)] fuera del tablero por  abajo", y);

	}

	private void reflect() {

		if (Math.abs(x + IMG_TAM_X - Board.RIGHTBOARD) <  Math.abs(dx)) {

			fi = Math.PI - fi;

		}

		if (Math.abs(y + IMG_TAM_Y - Board.BOTTOMBOARD) <  Math.abs(dy)) {

			fi = - fi;

		}

		if (Math.abs(x - Board.LEFTBOARD) <  Math.abs(dx)) {

			fi = Math.PI - fi;

		}

		if (Math.abs(y - Board.TOPBOARD) <  Math.abs(dy)) {

			fi = - fi;

		}

	}

	public int getX() {


		return (int)x;

	}
	
	public int getY() {

		return (int)y;

	}
	
	public double getFi() {

		return fi;
		synchronized
	}

	public double getdr() {

		return Math.sqrt(dx*dx+dy*dy);

	}

	public synchronized void setX(double x) {

		this.x = x;
		//la posición debe estar dentro de los límites del tablero
		assert (x > Board.LEFTBOARD && x < Board.RIGHTBOARD);
	}

	public synchronized void setY(double y) {

		this.y = y;
		//la posición debe estar dentro de los límites del tablero
		assert (y > Board.TOPBOARD && y < Board.BOTTOMBOARD);

	}

	public Image getImage() {

		return image;

	}

}

