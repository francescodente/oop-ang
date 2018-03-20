package oopang.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * This class loads the given GameScene to its corresponding fxml file.
 */
public final class SceneLoader {

    private static final SceneLoader SINGLETON = new SceneLoader();
    private static final String PATH_START = "/scenes/";
    private static final String PATH_END = ".fxml";

    private Map<GameScene, String> pathMap;

    private SceneLoader() {
        this.pathMap = Arrays.stream(GameScene.values())
                .collect(Collectors.toMap(s -> s, s -> PATH_START + s.toString().toLowerCase() + PATH_END));
    }

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
     */
    public SceneWrapper getScene(final GameScene sceneTag) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        final Parent parent = loader.load(this.getClass().getResourceAsStream(pathMap.get(sceneTag)));
        return new SceneWrapper(new Scene(parent), loader.getController());
    }
}
