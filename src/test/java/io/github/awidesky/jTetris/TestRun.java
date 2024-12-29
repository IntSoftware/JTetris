package io.github.awidesky.jTetris;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import javax.swing.SwingUtilities;

import org.junit.jupiter.api.Test;

class TestRun {

	@Test
	void test() throws InterruptedException {
		Object lock = new Object();
		Tetris tetris = new Tetris(() ->  {
			synchronized (lock) {
				lock.notify();
			}
		});
		assertDoesNotThrow(() -> SwingUtilities.invokeAndWait(() -> tetris.startGame()));
		synchronized (lock) {
			lock.wait();
		}
		assertTrue(new File("TetrisHighScore.bin").exists());
	}

}
