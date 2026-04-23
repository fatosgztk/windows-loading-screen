package com.symdev;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.util.function.Consumer;

public class AnimationService {
    private final String[] bootMessages = {
        "Starting system...",
        "Loading modules...",
        "Initializing UI...",
        "Preparing environment...",
        "Finalizing setup..."
    };

    public void runBootSequence(Label label, Consumer<Integer> onProgressUpdate) {
        new Thread(() -> {
            int progress = 0;
            try {
                for (String msg : bootMessages) {
                    if (label != null) {
                        Platform.runLater(() -> {
                            animateTextTransition(label, msg);
                        });
                    }

                    for (int j = 0; j < 20; j++) {
                        Thread.sleep((long) (Math.random() * 150 + 50));
                        progress += 1;
                        if (progress > 100) progress = 100;
                        if (onProgressUpdate != null) {
                            onProgressUpdate.accept(progress);
                        }
                    }
                }
                if (label != null) {
                    Platform.runLater(() -> label.setText("System Ready"));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    private void animateTextTransition(Label label, String newText) {
        if (label == null) return;
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), label);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(e -> {
            label.setText(newText);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), label);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(0.7);
            fadeIn.play();
        });
        fadeOut.play();
    }

    public void applyLogoEntrance(Node logo) {
        if (logo == null) return;
        FadeTransition fade = new FadeTransition(Duration.seconds(2), logo);
        fade.setFromValue(0);
        fade.setToValue(1);

        ScaleTransition scale = new ScaleTransition(Duration.seconds(3), logo);
        scale.setFromX(0.85);
        scale.setFromY(0.85);
        scale.setToX(1.0);
        scale.setToY(1.0);
        scale.setInterpolator(Interpolator.EASE_BOTH);

        new ParallelTransition(fade, scale).play();
    }
}