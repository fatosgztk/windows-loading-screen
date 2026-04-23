package com.symdev;

import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class BootController extends StackPane {
    private final Group logo;
    private final Pane spinner;

    public BootController() {
        setStyle("-fx-background-color: black;");
        
        // Logoyu oluştur ve yukarı taşı
        logo = createWindowsLogo(180);
        logo.setTranslateY(-65);

        // 6 bilyeli spinner oluştur ve logonun altına yerleştir
        spinner = createSpinner(55);
        spinner.setTranslateY(145);
        
        getChildren().addAll(logo, spinner);
    }

    private Group createWindowsLogo(double size) {
        Group group = new Group();
        Color winBlue = Color.web("#0078D7");
        double w = size * 0.75;
        double h = size * 0.85;
        double hGap = 7.0;
        double vGapActive = 7.0;
        double yShift = 4.0;
        
        double wL = w * 0.38;
        double wR = w * 0.62;
        double hLeft = h * 0.78;
        double hRight = h * 1.0;

        // Koordinat sınırları
        double x1 = -(wL + hGap/2);
        double x2 = -hGap/2;
        double x3 = hGap/2;
        double x4 = wR + hGap/2;
        double totalWidth = x4 - x1;

        // Doğrusal yükseklik fonksiyonu
        java.util.function.BiFunction<Double, Double, Double> calcY = (x, side) -> {
            double currentH = hLeft + (hRight - hLeft) * (x - x1) / totalWidth;
            double factor = 0.88;
            return side * (currentH / 2.0) * factor;
        };

        // Üst parçalar
        Polygon p1 = new Polygon(
            x1, calcY.apply(x1, -1.0) + yShift, 
            x2, calcY.apply(x2, -1.0) + yShift, 
            x2, -vGapActive/2 + yShift, 
            x1, -vGapActive/2 + yShift
        );
        Polygon p2 = new Polygon(
            x3, calcY.apply(x3, -1.0) + yShift, 
            x4, calcY.apply(x4, -1.0) + yShift, 
            x4, -vGapActive/2 + yShift, 
            x3, -vGapActive/2 + yShift
        );

        // Alt parçalar
        Polygon p3 = new Polygon(
            x1, vGapActive/2 + yShift, 
            x2, vGapActive/2 + yShift, 
            x2, -calcY.apply(x2, -1.0) + yShift, 
            x1, -calcY.apply(x1, -1.0) + yShift
        );
        Polygon p4 = new Polygon(
            x3, vGapActive/2 + yShift, 
            x4, vGapActive/2 + yShift, 
            x4, -calcY.apply(x4, -1.0) + yShift, 
            x3, -calcY.apply(x3, -1.0) + yShift
        );

        p1.setFill(winBlue); p2.setFill(winBlue); p3.setFill(winBlue); p4.setFill(winBlue);
        group.getChildren().addAll(p1, p2, p3, p4);
        return group;
    }

    private Pane createSpinner(double radius) {
        Pane container = new Pane();
        container.setMaxSize(0, 0);

        for (int i = 0; i < 6; i++) {
            Circle dot = new Circle(5.5, Color.WHITE);
            double baseOpacity = 1.0 - (i * 0.15);
            dot.setOpacity(baseOpacity);

            Timeline timeline = new Timeline();
            for (int angle = 0; angle <= 360; angle += 5) {
                double rad = Math.toRadians(angle - 90);
                double tProgress = angle / 360.0;
                
                // Alt noktada (180 derece) kaybolma efekti
                double currentOpacity = baseOpacity;
                if (angle >= 150 && angle <= 210) {
                    currentOpacity = baseOpacity * (Math.abs(angle - 180) / 30.0);
                }

                double easedTime = tProgress * 2.4 + 0.28 * Math.sin(tProgress * 2 * Math.PI);

                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(easedTime),
                    new KeyValue(dot.translateXProperty(), radius * Math.cos(rad)),
                    new KeyValue(dot.translateYProperty(), radius * Math.sin(rad)),
                    new KeyValue(dot.opacityProperty(), currentOpacity)
                ));
            }
            
            timeline.setDelay(Duration.millis(i * 190));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();

            container.getChildren().add(dot);
        }
        return container;
    }

    public Group getLogo() { return logo; }
}