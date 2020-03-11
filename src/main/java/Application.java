import controller.GameController;
import controller.SimpleController;
import domain.result.SimpleResult;

public class Application {
	public static void main(String[] args) {
		new GameController().run(new SimpleController(), new SimpleResult());
	}
}
