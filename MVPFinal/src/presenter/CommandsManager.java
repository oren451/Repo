package presenter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.IModel;
import view.IView;

/**
 *
 * Manage the commands of the view and the model
 * 
 * @author orenk
 */

public class CommandsManager {

	class DirReadyCommand implements ICommand {
		@Override
		public void doCommand(final String[] args) {
			mView.dirReady(args);
		}
	}
	class DisplayFilesInPathCommand implements ICommand {

		@Override
		public void doCommand(final String[] args) {
			mModel.displayFilesInPath(args);
		}
	}

	class DisplayMessageCommand implements ICommand {

		@Override
		public void doCommand(final String[] args) {

			final StringBuilder sb = new StringBuilder();
			for (final String s : args) {
				sb.append(s + " ");
			}
			mView.printMessage(sb.toString());
		}
	}

	class ExitCommand implements ICommand {

		@Override
		public void doCommand(final String[] args) {
			mModel.exit();
			mView.exit();
		}

	}

	class GenerateMazeCommand implements ICommand {

		@Override
		public void doCommand(final String[] args) {
			mModel.generateMaze(args);
		}
	}

	class HelpCommand implements ICommand {

		@Override
		public void doCommand(final String[] args) {
			mModel.goDown(args);
		}
	}

	class LoadMazeCommand implements ICommand {

		@Override
		public void doCommand(final String[] args) throws IOException {
			mModel.loadMaze(args);
		}
	}

	class MazeIsReadyCommand implements ICommand {

		@Override
		public void doCommand(final String[] args) {
			final Maze3d maze = mModel.getMaze(args[0]);
			mView.generatedMaze(maze, args[0]);
		}
	}

	/**
	 * All Commands Classes
	 */

	class MoveBackCommand implements ICommand {
		@Override
		public void doCommand(final String[] args) {
			mModel.goBackward(args);
		}
	}

	class MoveDownCommand implements ICommand {

		@Override
		public void doCommand(final String[] args) {
			mModel.goDown(args);
		}
	}

	class MoveForwardCommand implements ICommand {

		@Override
		public void doCommand(final String[] args) {
			mModel.goForward(args);
		}
	}

	class MoveLeftCommand implements ICommand {

		@Override
		public void doCommand(final String[] args) {
			mModel.goLeft(args);
		}
	}

	class MoveLevelUpCommand implements ICommand {

		@Override
		public void doCommand(final String[] args) {
			mModel.goLevelUp(args);
		}
	}

	public class MovePlayerCommand implements ICommand {
		@Override
		public void doCommand(final String[] args) {
			final Position currPos = mModel.getCurrPosition();
			mView.move(currPos);
		}
	}

	class MoveRightCommand implements ICommand {

		@Override
		public void doCommand(final String[] args) {
			mModel.goRight(args);
		}
	}

	class SaveMazeCommand implements ICommand {

		@Override
		public void doCommand(final String[] args) throws IOException {
			mModel.saveMaze(args);
		}
	}

	class SetDbValuesCommand implements ICommand {

		@Override
		public void doCommand(final String[] args) {
			if (args.length > 0)
				mView.dbValues(args[0]);
			else
				mView.dbValues("");
		}
	}

	class SolutionIsReadyCommand implements ICommand {

		@Override
		public void doCommand(final String[] args) {
			final Solution<Position> solution = mModel.getSolution(args[0]);
			mView.displaySolution(solution);
		}
	}

	class SolveCommand implements ICommand {

		@Override
		public void doCommand(final String[] args) {
			mModel.solve(args);
		}
	}

	public class WinnerCommand implements ICommand {

		@Override
		public void doCommand(final String[] args) {
			mView.winner();
		}
	}

	private IModel mModel;

	private IView mView;

	private final HashMap<String, ICommand> mCommandMap;

	public CommandsManager(final IModel model, final IView view) {
		mModel = model;
		mView = view;
		mCommandMap = new HashMap<String, ICommand>();
		initCommandsMap();
	}

	public void executeCommand(final String commandLine) throws IOException {
		ICommand cmd;
		final String[] args = commandLine.split(" ");
		cmd = mCommandMap.get(args[0]);
		if (cmd == null)
			throw new IllegalArgumentException("Invalid Command!");
		cmd.doCommand(Arrays.copyOfRange(args, 1, args.length));
	}

	public HashMap<String, ICommand> getCommandMap() {
		return mCommandMap;
	}

	public IView getView() {
		return mView;
	}

	private void initCommandsMap() {
		mCommandMap.put(CommandsList.MOVE_UP_CMD, new MoveLevelUpCommand());
		mCommandMap.put(CommandsList.MOVE_DOWN_CMD, new MoveDownCommand());
		mCommandMap.put(CommandsList.MOVE_RIGHT_CMD, new MoveRightCommand());
		mCommandMap.put(CommandsList.MOVE_LEFT_CMD, new MoveLeftCommand());
		mCommandMap.put(CommandsList.MOVE_FORWARD_CMD, new MoveForwardCommand());
		mCommandMap.put(CommandsList.MOVE_BACK_CMD, new MoveBackCommand());
		mCommandMap.put(CommandsList.GENERATE_CMD, new GenerateMazeCommand());
		mCommandMap.put(CommandsList.HELP_CMD, new HelpCommand());
		mCommandMap.put(CommandsList.DISPLAY_DIR_CMD, new DisplayFilesInPathCommand());
		mCommandMap.put(CommandsList.DIR_READY_CMD, new DirReadyCommand());
		mCommandMap.put(CommandsList.SAVE_CMD, new SaveMazeCommand());
		mCommandMap.put(CommandsList.LOAD_CMD, new LoadMazeCommand());
		mCommandMap.put(CommandsList.SOLVE_CMD, new SolveCommand());
		mCommandMap.put(CommandsList.EXIT_CMD, new ExitCommand());
		mCommandMap.put(CommandsList.SET_DB_VALUES_CMD, new SetDbValuesCommand());
		mCommandMap.put(CommandsList.MAZE_IS_READY_CMD, new MazeIsReadyCommand());
		mCommandMap.put(CommandsList.SOLUTION_IS_READY_CMD, new SolutionIsReadyCommand());
		mCommandMap.put(CommandsList.DISPLAY_MESSAGE_CMD, new DisplayMessageCommand());
		mCommandMap.put(CommandsList.MOVE_CMD, new MovePlayerCommand());
		mCommandMap.put(CommandsList.WIN_CMD, new WinnerCommand());
	}

	public void setModelAndView(final IModel model, final IView view) {
		mModel = model;
		mView = view;
	}
}