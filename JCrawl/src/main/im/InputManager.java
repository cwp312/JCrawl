package main.im;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.gmm.GameManager;

/**
 * Input handler class
 * @author Arda Yucel
 * @see KeyListener
 */
public class InputManager implements KeyListener {
	/*
	 * 0 - W 1 - A 2 - S 3 - D
	 */
	private boolean keyPressed[] = { false, false, false, false, false, false,
			false };
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			keyPressed[0] = true;
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			keyPressed[1] = true;
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			keyPressed[2] = true;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			keyPressed[3] = true;
			break;
		case KeyEvent.VK_SPACE:
			keyPressed[4] = true;
			break;
		case KeyEvent.VK_ESCAPE:
			keyPressed[6] = true;
			break;
		}
		GameManager.setKeyPressed(keyPressed);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			keyPressed[0] = false;
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			keyPressed[1] = false;
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			keyPressed[2] = false;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			keyPressed[3] = false;
			break;
		case KeyEvent.VK_SPACE:
			keyPressed[4] = false;
			break;
		case KeyEvent.VK_ESCAPE:
			keyPressed[6] = false;
			break;
		}
		GameManager.setKeyPressed(keyPressed);
	}

	public void keyTyped(KeyEvent arg0) {
	}
}
