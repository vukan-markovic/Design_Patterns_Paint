package strategy;

import java.io.File;

public interface FileHandler {
	void save(File file);
	void open(File file);
}