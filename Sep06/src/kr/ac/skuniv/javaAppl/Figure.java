package kr.ac.skuniv.javaAppl;

import java.awt.*;

abstract class Figure {
	protected int x, y;
	
	public Figure(int x, int y) {
		this.x = x;
		this.y = y;
	}
	abstract void draw(Graphics g);
}

class Rectangle extends Figure {
	private int width, height;
	
	public Rectangle(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}

	@Override
	void draw(Graphics g) {
		g.drawRect(x, y, width, height);
	}
}

class Circle extends Figure {
	private int radius;
	
	public Circle(int x, int y, int radius) {
		super(x, y);
		this.radius = radius;
	}

	@Override
	void draw(Graphics g) {
		g.drawOval(x - radius, y - radius, radius*2, radius*2);
	}
}