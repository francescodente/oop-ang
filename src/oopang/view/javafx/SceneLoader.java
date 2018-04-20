package oopang.view.javafx;

import java.io.IOException;
import java.util.Locale;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import oopang.view.GameScene;

/**
 * This class loads the given GameScene to its corresponding fxml file.
 */
public final class SceneLoader {

    private static final SceneLoader SINGLETON = new SceneLoader();
    private static final String PATH_START = "/scenes/";
    private static final String PATH_END = ".fxml";

    /**
     * Returns the single instance of the SceneLoader.
     * @return
     *      the instance.
     */
    public static SceneLoader getLoader() {
        return SINGLETON;
    }

    /**
     * Loads the given scene.
     * @param sceneTag
     *      the {@link GameScene} to be loaded
     * @return
     *      the Scene related to the given tag
     * @throws IOException
     *      if the file is not loaded correctly
     */
    public SceneWrapper getScene(final GameScene sceneTag) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        final String path = PATH_START + sceneTag.toString().toLowerCase(Locale.ROOT) + PATH_END;
        final Parent parent = loader.load(this.getClass().getResourceAsStream(path));
        return new SceneWrapper(new Scene(parent), loader.getController());
    }
}
