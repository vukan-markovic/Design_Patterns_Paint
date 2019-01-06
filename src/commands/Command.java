package commands;

/**
 * 
 * @author Vukan Marković
 * 
 * Represent inferface for all commands in the application. 
 * Define two methods: 
 * {@link #execute()} - Execute some command.
 * {@link #unexecute()} - Undo some command.
 */
public interface Command {
	void execute();
	void unexecute();
}