package com.mnov34.CUBES4solo.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.WeakHashMap;

@Component
public class SceneManager {

    private final ApplicationContext applicationContext;

    private Stage primaryStage;
    private final Map<SceneType, Node> viewCache = new WeakHashMap<>();

    public SceneManager(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Sets the primary stage for the application.
     *
     * @param primaryStage the main stage of the JavaFX application
     */
    public void setPrimaryStage(Stage primaryStage) {
        if (primaryStage == null) {
            throw new IllegalArgumentException("Primary stage cannot be null.");
        }
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        if (primaryStage == null) {
            throw new IllegalStateException("Primary stage not set. Call setPrimaryStage() before accessing it.");
        }
        return primaryStage;
    }

    /**
     * Switches the scene of the primary stage.
     *
     * @param sceneType the type of the scene to switch to
     */
    public void switchScene(SceneType sceneType) {
        if (primaryStage == null) {
            throw new IllegalStateException("Primary stage not set. Call setPrimaryStage() before switching scenes.");
        }

        try {
            FXMLLoader loader = createLoader(sceneType.getFxmlPath());
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Optional<URL> css = Optional.ofNullable(getClass().getResource("/css/styles.css"));
            if (css.isPresent()) {
                String cssString = css.get().toExternalForm();
                scene.getStylesheets().add(cssString);
            }

            primaryStage.setScene(scene);
            primaryStage.setTitle(sceneType.getTitle());
            primaryStage.setMaximized(false);
            primaryStage.setMaximized(true);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load scene: " + sceneType.getTitle(), e);
        }
    }

    /**
     * Loads a view into the specified content area.
     *
     * @param sceneType the type of the view to load
     */
    public void loadView(SceneType sceneType) {
        try {
            Node view = viewCache.get(sceneType);
            if (view == null) {
                FXMLLoader loader = createLoader(sceneType.getFxmlPath());
                view = loader.load();
                viewCache.put(sceneType, view);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load view: " + sceneType.getTitle(), e);
        }
    }

    private FXMLLoader createLoader(String fxmlPath) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        return fxmlLoader;
    }

    public enum SceneType {
        LOGIN("/fxml/login.fxml", "Connexion"),
        HOME("/fxml/home.fxml", "Accueil"),
        USER_LIST("/fxml/user_list.fxml", "Annuaire"),
        ADMIN("/fxml/admin.fxml", "Admin Panel"),;

        private final String fxmlPath;
        private final String title;

        SceneType(String fxmlPath, String title) {
            this.fxmlPath = fxmlPath;
            this.title = title;
        }

        public String getFxmlPath() {
            return fxmlPath;
        }

        public String getTitle() {
            return title;
        }
    }

}
