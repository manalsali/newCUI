package main_cardiopointer;

import heart_panel.Controls;
import heart_panel.HeartInterface;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.lwjgl.opengl.GLContext;

import menu.MenuInterface;
import cross_section_panel.CrossSectionInterface;

public class Main  {

	public static JFrame frame = new JFrame();
	Container cp;
	static JPanel heartPanel;
	JPanel crossSections;
	public static HeartInterface heart;
	
	public Main() {

		frame.setTitle("Cardiopointer");
		frame.setJMenuBar(new MenuInterface().getMenuBar());
		cp  = frame.getContentPane();
		cp.setLayout(new GridLayout(1,2));
		
		heartPanel = new JPanel(new BorderLayout());
		heart = new HeartInterface();
		heartPanel.add(heart.panel);
		heartPanel.add(new Controls(), BorderLayout.SOUTH);
		crossSections = new JPanel(new GridLayout(1,1));
		
		crossSections.add(new CrossSectionInterface());
		cp.add(heartPanel);
		cp.add(crossSections);
		
		// generic stuff 
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public void addHeartPanel() {
		
	}
	
	public static void removeHeartPanel() {
		
		heart.canvas.repaint();
		heart.canvas.invalidate();
		heart.canvas.revalidate();
		frame.repaint();
		frame.pack();
		
		heart.startthread();
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main();
				// TODO Auto-generated method stub
			}
		});
	}
	
	
}