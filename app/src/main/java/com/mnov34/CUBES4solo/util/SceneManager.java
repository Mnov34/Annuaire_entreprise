package com.mnov34.CUBES4solo.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.WeakHashMap;

@Component
public class SceneManager {

    private final ApplicationContext applicationContext;

    private Stage primaryStage;
    private Scene currentScene;

    @Setter
    private Label currentViewTitle;

    @Setter
    private AnchorPane mainContentArea;
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
            currentScene = new Scene(root);

            if (currentViewTitle != null) currentViewTitle.setText(sceneType.getTitle());

            currentScene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(currentScene);
            primaryStage.setTitle(sceneType.getTitle());
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
            if (currentViewTitle != null) currentViewTitle.setText(sceneType.getTitle());

            mainContentArea.getChildren().setAll(view);

            currentScene = mainContentArea.getScene();
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

    @Getter
    public enum SceneType {
        LOGIN("/fxml/login.fxml", "Connexion"),
        HOME("/fxml/dashboard.fxml", "Dashboard"),
        EMPLOYEE_LIST("/fxml/employee_list.fxml", "Annuaire"),
        SITES("/fxml/sites.fxml", "Sites"),
        DEPARTMENTS("/fxml/departments.fxml", "Services");

        private final String fxmlPath;
        private final String title;

        SceneType(String fxmlPath, String title) {
            this.fxmlPath = fxmlPath;
            this.title = title;
        }

    }
}
