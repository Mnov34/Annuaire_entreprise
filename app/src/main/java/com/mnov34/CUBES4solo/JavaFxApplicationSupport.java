package com.mnov34.CUBES4solo;

import com.mnov34.CUBES4solo.user.LocalUserData;
import com.mnov34.CUBES4solo.util.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.Getter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JavaFxApplicationSupport extends Application {

    private static ConfigurableApplicationContext springContext;

    @Getter
    private static JavaFxApplicationSupport instance;

    @Getter
    private SceneManager sceneManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        springContext = new SpringApplicationBuilder(JavaFxApplicationSupport.class)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage primaryStage) {
        instance = this;
        sceneManager = springContext.getBean(SceneManager.class);

        LocalUserData.setProperty("isLogged", "false");

        createScene(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
        super.stop();
        System.exit(0);
    }

    private void createScene(Stage stage) {
        try {
            sceneManager.setPrimaryStage(stage);
            sceneManager.switchScene(SceneManager.SceneType.HOME);
            stage.setTitle("Connexion");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
