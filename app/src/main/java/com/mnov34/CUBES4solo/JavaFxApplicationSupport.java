package com.mnov34.CUBES4solo;

import com.mnov34.CUBES4solo.util.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JavaFxApplicationSupport extends Application {

    private static ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = new SpringApplicationBuilder(JavaFxApplicationSupport.class)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            SceneManager sceneManager = springContext.getBean(SceneManager.class);
            sceneManager.setPrimaryStage(primaryStage);
            sceneManager.switchScene(SceneManager.SceneType.USER_LIST);
            primaryStage.setTitle("Connexion");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        springContext.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
