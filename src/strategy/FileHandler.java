package strategy;

import java.io.File;

/**
 * Represent interface for manipulation with files.
 */
public interface FileHandler {
	void save(File file);
	void open(File file);
}