package com.symdev;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        BootController ui = new BootController();
        AnimationService animator = new AnimationService();

        Scene scene = new Scene(ui, 800, 600);
        stage.setScene(scene);
        stage.setTitle("SymDev OS Boot");
        stage.show();

        // Logo entrance animation removed as per request
        ui.getLogo().setOpacity(1.0);
        ui.getLogo().setScaleX(1.0);
        ui.getLogo().setScaleY(1.0);
        
        animator.runBootSequence(null, progress -> {
            // Only dots are animated
        });

        scene.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ESCAPE")) System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
