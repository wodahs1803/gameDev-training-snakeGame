package com.GameDevStudy.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener{

	public static boolean debug = false;
	
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private static boolean isRunning;
	
	public static int WIDTH = 240; //320;
	public static int HEIGHT = 160; //240;
	public static final int SCALE = 3; //2;
	/*
	public static int WIDTH = 320;
	public static int HEIGHT = 240;
	private final int SCALE = 2;
	*/
	
	public static int CUR_LEVEL =  1, MAX_LEVEL = 2;
	
	private BufferedImage image;

	private int framesGameOver = 0;

	
	public static Random rand;
	
	public static String gameState = "MENU";
	
	public Game(){
		rand = new Random();
		addKeyListener(this);
		addMouseListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		//Initializing Objects
	}
	
	public static boolean getRunning(){
		return isRunning;
	}
	
	public static void setRunning(boolean run){
		isRunning = run;
	}
	
	public void initFrame(){
		frame = new JFrame("Game #1");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop(){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.start();
	}
	
	public void update(){
		if(gameState == "NORMAL"){
			// TODO Auto-generated method stub
		}else if(gameState == "GAME_OVER"){
			System.out.println("Game Over!");

		}else if(gameState == "MENU"){
			// TODO Auto-generated method stub
			//menu.update();
		}
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		/*Game Rendering:*/
		//Graphics2D g2 = (Graphics2D) g;
		/********************/
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, (WIDTH*SCALE), (HEIGHT*SCALE), null);
		g.setFont(new Font("arial", Font.BOLD, 20));
		
		if(gameState == "GAME_OVER"){
			this.framesGameOver++;
			if(this.framesGameOver == 60){
				this.framesGameOver = 0;
				
			}
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor( new Color(0,0,0,100));
			g2.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.setColor(Color.white);
			g.drawString("Game Over", (WIDTH*SCALE)/2 - 85, (HEIGHT*SCALE)/2);
			g.setFont(new Font("arial", Font.BOLD, 15));

		}else if(gameState == "MENU"){
			// TODO Auto-generated method stub
			//menu.render(g);
		}
		
		bs.show();
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double fps = 60.0;
		//Nanoseconds
		double ns = 1000000000 / fps;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1){
				update();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000){
				System.out.println("FPS: "+frames);
				frames = 0;
				timer += 1000;
			}
		}
		
		stop();
		
	}
	
	//Controlling Stuff
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}