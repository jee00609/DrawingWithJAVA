package kr.ac.skuniv.javaAppl;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class WindowExample {
	static final JFileChooser fc = new JFileChooser();
	static DrawingPanel panel;
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("DrawFigure");
		frame.setLocation(500, 400);
		frame.setPreferredSize(new Dimension(500, 400));
		
		Container contentPane = frame.getContentPane();
		
		panel = new DrawingPanel(); //static DrawingPanel panel;가 있기에 다시 선언할때 조심!
		contentPane.add(panel);
		
		panel.addMouseListener(new MouseListener() {
			int pressX,pressY;
			
			@Override
			public void mouseReleased(MouseEvent e) {
				int releaseX=e.getX();
				int releaseY=e.getY();
				panel.addFigure(pressX, pressY, releaseX, releaseY);
				//삭제 panel.repaint();
				System.out.println("release");
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				pressX=e.getX();
				pressY=e.getY();
				System.out.println("press");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("exit");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("enter");
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("click");
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);//여기에 바로 추가
		
		JMenu filemenu = new JMenu("File");
		menuBar.add(filemenu);
		
		JMenuItem newItem = new JMenuItem("New");
		filemenu.add(newItem);
		newItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//newFile();
			}
		});
		
		JMenuItem openItem = new JMenuItem("open");
		filemenu.add(openItem);
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				open();
			}
		});
		
		menuBar.add(filemenu);
		JMenuItem saveItem = new JMenuItem("save");
		filemenu.add(saveItem);
		saveItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				save();
			}
		});
		
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
	
	static void open() {
		if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
			ObjectInputStream ois = null;
			File file= fc.getSelectedFile();
			try {
				ois = new ObjectInputStream(new FileInputStream(file));
				panel.read(ois);
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally{
				try {
					ois.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	static void save() {
		if(fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
			ObjectOutputStream oos = null;
			File file= fc.getSelectedFile();
			try {
				oos = new ObjectOutputStream(new FileOutputStream(file));
				panel.write(oos);
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally{
				try {
					oos.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

}
