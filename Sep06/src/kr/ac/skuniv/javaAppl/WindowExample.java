package kr.ac.skuniv.javaAppl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class WindowExample {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("DrawFigure");
		frame.setLocation(500, 400);
		frame.setPreferredSize(new Dimension(500, 400));
		
		Container contentPane = frame.getContentPane();
		DrawingPanel panel = new DrawingPanel();
		contentPane.add(panel);
		
		panel.addMouseListener(new MouseListener() {
			int pressX,pressY;
			
			@Override
			public void mouseReleased(MouseEvent e) {
				int releaseX=e.getX();
				int releaseY=e.getY();
				panel.addFigure(pressX, pressY, releaseX, releaseY);
				//삭제 panel.repaint();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				pressX=e.getX();
				pressY=e.getY();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);//여기에 바로 추가
		
		JMenu filemenu = new JMenu("File");
		
		menuBar.add(filemenu);
		JMenuItem openItem = new JMenuItem("open");
		filemenu.add(openItem);
		
		menuBar.add(filemenu);
		JMenuItem saveItem = new JMenuItem("save");
		filemenu.add(saveItem);
		
		JMenu menu = new JMenu("Figure");
		menuBar.add(menu);
		
		ButtonGroup group = new ButtonGroup();
		
		JRadioButtonMenuItem rbMenuItem1 = new JRadioButtonMenuItem("Rectangle");
		group.add(rbMenuItem1);
		menu.add(rbMenuItem1);
		
		rbMenuItem1.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setFigureType(FigureType.RECTANGLE);
			}
		});
		rbMenuItem1.setSelected(true);
	
		JRadioButtonMenuItem rbMenuItem2 = new JRadioButtonMenuItem("Cirlce");
		group.add(rbMenuItem2);
		menu.add(rbMenuItem2);
		
		rbMenuItem2.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setFigureType(FigureType.CIRCLE);
			}
		});
		
		//frame.setJMenuBar(menuBar);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
