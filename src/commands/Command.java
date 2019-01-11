package commands;

/**
 * <h3>Represent inferface for all commands in the application.</h3>
 * 
 * Define two methods:
 * <ul> 
 * <li>{@link #execute()} - Execute some command.</li>
 * <li>{@link #unexecute()} - Undo some command.</li>
 * <ul>
 */
public interface Command {
	void execute();
	void unexecute();
}