package com.example.marcadorderotina;

import javafx.animation.FadeTransition;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.InputStream;
import java.util.Objects;

public class PreeloaderApp extends Preloader {

    public void start(Stage primaryStage) {
        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/samurai.gif"))); // Substitua "imagem.png" pelo caminho da sua imagem

        // Carregar a imagem do resource

        // Criar um ImageView para exibir a imagem
        ImageView x = new ImageView(image3);
       // Cria um ImageView para exibir o GIF
        x.maxWidth(188);
        x.maxHeight(158);
        x.minHeight(158);
        x.minWidth(188);
        x.setFitWidth(188);
        x.setFitHeight(158);
        x.setTranslateX(-150);
        x.setTranslateY(-20);
        Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/shimazu.png"))); // Substitua "imagem.png" pelo caminho da sua imagem

        // Carregar a imagem do resource

        // Criar um ImageView para exibir a imagem
        ImageView s = new ImageView(image4);
        s.setTranslateX(-25);
        StackPane root = new StackPane();
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(7), x);
        fadeTransition.setFromValue(1); // Opacidade inicial (totalmente opaca)
        fadeTransition.setToValue(0);   // Opacidade final (totalmente transparente)
        fadeTransition.setCycleCount(1);
        fadeTransition.play();
        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(7), s);
        fadeTransition2.setFromValue(1); // Opacidade inicial (totalmente opaca)
        fadeTransition2.setToValue(0);   // Opacidade final (totalmente transparente)
        fadeTransition2.setCycleCount(1);
        fadeTransition2.play();
        root.getChildren().addAll(x,s);
        root.setStyle("-fx-background-color: transparent;");
        Scene scene = new Scene(root, 500, 200, Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.show();
}}
