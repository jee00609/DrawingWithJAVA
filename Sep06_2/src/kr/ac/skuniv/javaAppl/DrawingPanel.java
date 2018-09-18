package kr.ac.skuniv.javaAppl;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

public class DrawingPanel extends JPanel {
	ArrayList<Figure> figs;
	FigureType figType;
	
	public DrawingPanel() {
		super();
		figs = new ArrayList<Figure>();
		figType = FigureType.RECTANGLE;
	}
	
	void setFigureType(FigureType figType) {
		this.figType = figType;
	}
	
	void addFigure(int pressX, int pressY, int releaseX, int releaseY) {
		if (figType == FigureType.RECTANGLE) {
			int x = Math.min(pressX, releaseX);
			int y = Math.min(pressY, releaseY);
			int width = Math.abs(pressX - releaseX);
			int height = Math.abs(pressY - releaseY);
			figs.add(new Rectangle(x, y, width, height));
		}
		else if (figType == FigureType.CIRCLE) {
			int dx = pressX - releaseX;
			int dy = pressY - releaseY;
			int radius = (int)Math.sqrt(dx*dx + dy*dy);
			figs.add(new Circle(pressX, pressY, radius));
		}
		repaint(); // �߰�
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Figure fig : figs)
			fig.draw(g);
		
		System.out.println("Paint");//�ܼ� ��� �߰�
	}
	
	void read(ObjectInputStream ois) {
		try {
			figs = (ArrayList<Figure>)ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();
	}
	
	void write(ObjectOutputStream oos) {
		try {
			oos.writeObject(figs);//figs�� array����Ʈ��. 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
