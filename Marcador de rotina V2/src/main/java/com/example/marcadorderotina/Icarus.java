package com.example.marcadorderotina;

import java.io.*;
import java.time.LocalDate;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Tooltip;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Button;
import javafx.scene.control.Label;



import javafx.scene.control.CheckBox;

import java.util.HashSet;
import java.util.Set;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

import java.util.ArrayList;
import java.util.List;


public class Icarus extends Application {
    private Double ch1;
    private Double ch2;
    private Double ch3;
    private  String nomeDoArquivo = "true.db";
    // Flag para controlar se o botão foi clicado
    private  boolean botaoClicado = false;
    private LocalDate lastClickedDate;

    private boolean start2ChamadoHoje = false;


    // Obtendo a URL do recurso do arquivo do banco de dados
    private int segundos = 3;

    // Construindo a URL de conexão JDBC

    private String mensage;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    public double soma;
    private String[] args;
    private Window primaryStage;
    private Button okButton;
    private String selectedMode;
    private String nome;
    private  Connection conn;
    private int contador = 3;
    private Label labelNumero;
    private LocalDate ultimaDataRegistro;
    private LocalDate dataAtual;

    private final String PREF_KEY_LAST_CLICKED_DATE = "lastClickedDate";

    @Override
    public void start(Stage primaryStage) {


        // Define a data e hora atual no fuso horário do Brasil
        ZonedDateTime brazilDateTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

        // Obtém o dia da semana atual como um inteiro (1 para segunda-feira, 2 para terça-feira, etc.)
        int dayOfWeekInt = brazilDateTime.getDayOfWeek().getValue();
        String message;

        // Cria um conjunto para armazenar os dias sem repetição
        Set<Integer> diasSemRepeticao = new HashSet<>();

        // Adiciona o dia da semana atual ao conjunto
        diasSemRepeticao.add(dayOfWeekInt);

        // Define a mensagem com base no dia da semana atual
        switch (dayOfWeekInt) {
            case 1:
                message = "Hoje é Segunda-feira! Vamos analisar seu dia";
                break;
            case 2:
                message = "Hoje é Terça-feira! Vamos analisar seu dia";
                break;
            case 3:
                message = "Hoje é Quarta-feira! Vamos analisar seu dia!";
                break;
            case 4:
                message = "Hoje é Quinta-feira! Vamos analisar seu dia!";
                break;
            case 5:
                message = "Hoje é Sexta! Vamos analisar seu dia!";
                break;
            case 6:
                message = "Hoje é Sábado! Vamos analisar seu dia!";
                break;
            case 7:
                message = "Hoje é Domingo! Vamos analisar seu dia";
                break;
            default:
                message = "Erro ao detectar o dia da semana.";
                break;
        }
        // Carregar a imagem do resource
        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/x.png"))); // Substitua "imagem.png" pelo caminho da sua imagem

        // Carregar a imagem do resource

        // Criar um ImageView para exibir a imagem
        ImageView x = new ImageView(image3);

        // Carregar a imagem do resource
        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/mn.png"))); // Substitua "imagem.png" pelo caminho da sua imagem

        // Criar um ImageView para exibir a imagem
        ImageView mn = new ImageView(image2);
        Image image5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/v.png"))); // Substitua "imagem.png" pelo caminho da sua imagem

        // Criar um ImageView para exibir a imagem
        ImageView v = new ImageView(image5);
        Image image6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/er.png"))); // Substitua "imagem.png" pelo caminho da sua imagem
        v.setTranslateY(0);
        v.setTranslateX(190);
        // Criar um ImageView para exibir a imagem
        ImageView er = new ImageView(image6);
        er.setTranslateY(0);
        er.setTranslateX(190);
        Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/next.png"))); // Substitua "imagem.png" pelo caminho da sua imagem

        // Criar um ImageView para exibir a imagem
        ImageView next = new ImageView(image4);
        x.setTranslateX(220);
        x.setTranslateY(-80);
        next.setTranslateX(200);
        next.setTranslateY(20);
        mn.setTranslateX(185);
        mn.setTranslateY(-80);
        mn.setOnMouseClicked(event -> {
            primaryStage.setIconified(true); // Minimiza a janela
        });

        // Adiciona um evento de fechar a tela
        x.setOnMouseClicked(event -> {
            // Fecha a aplicação quando a imagem é clicada
            Platform.exit();
        });

        // Criar um ImageView para exibir a imagem
        // Cria um ImageView para exibir a imagem
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/or.png")));
        ImageView backgroundView = new ImageView(backgroundImage);
        // Cria um Text para exibir a mensagem
        Text text = new Text(message);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Text text2 = new Text("Você estudou 1 hora ou mais hoje?\ndigite" +
                " Sim ou Não");
        text2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Text text3 = new Text("Você fez exercícos por 40 minutos?\ndigite" +
                " Sim ou Não");
        text3.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Text text4 = new Text("Você se alimentou no horário correto?\ndigite" +
                " Sim ou Não");
        text4.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Text text5 = new Text("Organizou seu horário de dormir?\ndigite" +
                " Sim ou Não");
        text5.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        text.setFill(Color.BLUE);
        text.setTranslateY(0);
        text.setTranslateX(0);
        text2.setTranslateY(0);
        text2.setTranslateX(0);
        text3.setTranslateY(0);
        text3.setTranslateX(0);
        v.setOpacity(0);
        er.setOpacity(0);

        // Cria um TextField para o campo de entrada
        TextField textField = new TextField();
        textField.setPromptText("Digite a resposta aqui");
        TextField textField2 = new TextField();
        textField.setPromptText("Digite a resposta aqui");
        TextField textField3 = new TextField();
        textField.setPromptText("Digite a resposta aqui");
        TextField textField4 = new TextField();
        textField.setPromptText("Digite a resposta aqui");
        TextField textField5 = new TextField();
        textField.setPromptText("Digite a resposta aqui");
        textField.setMaxWidth(100);
        textField2.setMaxWidth(100);
        textField3.setMaxWidth(100);
        textField4.setMaxWidth(100);
        textField5.setMaxWidth(130);
        Label pp = new Label("Nome:");
        pp.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        pp.setTranslateY(38);
        pp.setTranslateX(-100);


// Adiciona um evento de ação para o TextField
        textField.setOnAction(event -> {
            String userInput = textField.getText().trim();

            // Variável para decremento
            // Cria uma transição de opacidade para o primeiro elemento
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), v);
            fadeTransition.setFromValue(1); // Opacidade inicial (totalmente opaca)
            fadeTransition.setToValue(0);   // Opacidade final (totalmente transparente)
            fadeTransition.setCycleCount(1);
            // Número de ciclos
            // Cria uma transição de opacidade para o segundo elemento
            FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1.5), er);
            fadeTransition2.setFromValue(1); // Opacidade inicial (totalmente opaca)
            fadeTransition2.setToValue(0);   // Opacidade final (totalmente transparente)
            fadeTransition2.setCycleCount(1); // Número de ciclos

            // Verifica se a entrada do usuário é igual a "sim" (ignorando maiúsculas e minúsculas)
            if (userInput.equalsIgnoreCase("sim")) {

                soma += 25.0;
                // Inicia a transição para  primeiro elemento quando a condição for verdadeira
                fadeTransition.play();
                StackPane novoPane = new StackPane();
                novoPane.getChildren().addAll(backgroundView, x, mn, v, text3, textField2);
                primaryStage.setScene(new Scene(novoPane, 500, 200));
            } else if (userInput.equalsIgnoreCase("não")) {
                // Inicia a transição para o segundo elemento quando a condição for falsa
                fadeTransition2.play();
                StackPane novoPane = new StackPane();
                novoPane.getChildren().addAll(backgroundView, x, mn, er, text3, textField2);
                primaryStage.setScene(new Scene(novoPane, 500, 200));

            } else {


            }
        });

        textField2.setOnAction(event -> {
            String userInput = textField2.getText().trim();

            // Cria uma transição de opacidade para o primeiro elemento
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), v);
            fadeTransition.setFromValue(1); // Opacidade inicial (totalmente opaca)
            fadeTransition.setToValue(0);   // Opacidade final (totalmente transparente)
            fadeTransition.setCycleCount(1); // Número de ciclos

            // Cria uma transição de opacidade para o segundo elemento
            FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1.5), er);
            fadeTransition2.setFromValue(1); // Opacidade inicial (totalmente opaca)
            fadeTransition2.setToValue(0);   // Opacidade final (totalmente transparente)
            fadeTransition2.setCycleCount(1); // Número de ciclos

            // Verifica se a entrada do usuário é igual a "sim" (ignorando maiúsculas e minúsculas)
            if (userInput.equalsIgnoreCase("sim")) {
                // Inicia a transição para o primeiro elemento quando a condição for verdadeira
                fadeTransition.play();
                soma += 25.0;
                StackPane novoPane = new StackPane();
                novoPane.getChildren().addAll(backgroundView, x, mn, v, text4, textField3);
                primaryStage.setScene(new Scene(novoPane, 500, 200));
            } else if (userInput.equalsIgnoreCase("não")) {
                // Inicia

                // a transição para o segundo elemento quando a condição for falsa
                fadeTransition2.play();
                StackPane novoPane = new StackPane();
                novoPane.getChildren().addAll(backgroundView, x, mn, er, text4, textField3);
                primaryStage.setScene(new Scene(novoPane, 500, 200));
            } else {


            }
        });

        textField3.setOnAction(event -> {
            String userInput = textField3.getText().trim();

            // Cria uma transição de opacidade para o primeiro elemento
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), v);
            fadeTransition.setFromValue(1); // Opacidade inicial (totalmente opaca)
            fadeTransition.setToValue(0);   // Opacidade final (totalmente transparente)
            fadeTransition.setCycleCount(1); // Número de ciclos

            // Cria uma transição de opacidade para o segundo elemento
            FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1.5), er);
            fadeTransition2.setFromValue(1); // Opacidade inicial (totalmente opaca)
            fadeTransition2.setToValue(0);   // Opacidade final (totalmente transparente)
            fadeTransition2.setCycleCount(1); // Número de ciclos

            // Verifica se a entrada do usuário é igual a "sim" (ignorando maiúsculas e minúsculas)
            if (userInput.equalsIgnoreCase("sim")) {
                // Inicia a transição para o primeiro elemento quando a condição for verdadeira
                soma += 25.0;
                fadeTransition.play();
                StackPane novoPane = new StackPane();
                novoPane.getChildren().addAll(backgroundView, x, mn, v, textField4, text5);
                primaryStage.setScene(new Scene(novoPane, 500, 200));
            } else if (userInput.equalsIgnoreCase("não")) {
                // Inicia a transição para o segundo elemento quando a condição for falsa
                fadeTransition2.play();
                StackPane novoPane = new StackPane();
                novoPane.getChildren().addAll(backgroundView, x, mn, er, textField4, text5);
                primaryStage.setScene(new Scene(novoPane, 500, 200));
            } else {

            }
        });
        textField4.setOnAction(event -> {
            String userInput = textField4.getText().trim();

            // Cria uma transição de opacidade para o primeiro elemento
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), v);
            fadeTransition.setFromValue(1); // Opacidade inicial (totalmente opaca)
            fadeTransition.setToValue(0);   // Opacidade final (totalmente transparente)
            fadeTransition.setCycleCount(1); // Número de ciclos

            // Cria uma transição de opacidade para o segundo elemento
            FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1.5), er);
            fadeTransition2.setFromValue(1); // Opacidade inicial (totalmente opaca)
            fadeTransition2.setToValue(0);   // Opacidade final (totalmente transparente)
            fadeTransition2.setCycleCount(1); // Número de ciclos

            // Verifica se a entrada do usuário é igual a "sim" (ignorando maiúsculas e minúsculas)
            if (userInput.equalsIgnoreCase("sim")) {
                soma += 25.0;

                soma += getValueFromTextField(textField);
                soma += getValueFromTextField(textField2);
                soma += getValueFromTextField(textField3);
                soma += getValueFromTextField(textField4);
                Text text6 = new Text("O seu progresso do dia foi:\n" + soma + "%");

                text6.setFont(Font.font("Arial", FontWeight.BOLD, 15));
                ;
                fadeTransition.play();
                StackPane novoPane = new StackPane();
                novoPane.getChildren().addAll(backgroundView, x, mn, v, text6);
                primaryStage.setScene(new Scene(novoPane, 500, 200));
            } else if (userInput.equalsIgnoreCase("não")) {
                // Inicia a transição para o segundo elemento quando a condição for falsa
                soma += getValueFromTextField(textField);
                soma += getValueFromTextField(textField2);
                soma += getValueFromTextField(textField3);
                soma += getValueFromTextField(textField4);
                Text text6 = new Text("O seu progresso do dia foi:\n" + soma + "%");

                text6.setFont(Font.font("Arial", FontWeight.BOLD, 15));
                fadeTransition2.play();
                StackPane novoPane = new StackPane();
                novoPane.getChildren().addAll(backgroundView, x, mn, text6);
                primaryStage.setScene(new Scene(novoPane, 500, 200));
            } else {


            }
        });

        // Cria um layout StackPane para organizar a imagem e o texto
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundView, text, x, mn, next, textField5, pp
        );
        textField.setTranslateY(40);
        textField.setTranslateX(0);
        textField2.setTranslateY(40);
        textField2.setTranslateX(0);
        textField3.setTranslateY(40);
        textField3.setTranslateX(0);
        textField4.setTranslateY(40);
        textField4.setTranslateX(0);
        textField5.setTranslateY(40);
        textField5.setTranslateX(0);
        next.setOnMouseClicked(event -> {

            ;
            String nome = textField5.getText().trim();
            pagina2(nome);

            // Chame a função de destino com o valor da Label
            if (!nome.isEmpty()) {
                if (nomeExisteNoBancoDeDados(nome)) {
                    redirecionarParaOutraPagina(nome);
                } else {
                    pagina2(nome);
                }
            }
        });

        // Configura a cena e exibe a janela
        Scene scene = new Scene(root, 500, 200);

        // Oculta o menu de fechar e minimizar e define o fundo da janela como transparente


        primaryStage.setScene(scene);
        primaryStage.setTitle("Mensagem Diária Personalizada");
        primaryStage.show();

    }

    private void start2(String nome) {

        System.out.println("Nome fornecido para start2: " + nome);

        Stage primaryStage3 = new Stage();

        calcularSomaTotal(nome);
        calcularSomaTotal2(nome);
        calcularSomaTotal3(nome);



        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/x.png"))); // Substitua "imagem.png" pelo caminho da sua imagem

        // Carregar a imagem do resource

        // Criar um ImageView para exibir a imagem
        ImageView x = new ImageView(image3);

        // Carregar a imagem do resource
        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/mn.png"))); // Substitua "imagem.png" pelo caminho da sua imagem

        // Criar um ImageView para exibir a imagem
        ImageView mn = new ImageView(image2);
        Image image5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/v.png"))); // Substitua "imagem.png" pelo caminho da sua imagem

        // Criar um ImageView para exibir a imagem
        ImageView v = new ImageView(image5);
        Image image6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/er.png"))); // Substitua "imagem.png" pelo caminho da sua imagem
        v.setTranslateY(0);
        v.setTranslateX(190);
        // Criar um ImageView para exibir a imagem
        ImageView er = new ImageView(image6);
        er.setTranslateY(0);
        er.setTranslateX(190);
        Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/next.png"))); // Substitua "imagem.png" pelo caminho da sua imagem

        // Criar um ImageView para exibir a imagem
        ImageView next = new ImageView(image4);
        x.setTranslateX(220);
        x.setTranslateY(-80);
        next.setTranslateX(200);
        next.setTranslateY(20);
        mn.setTranslateX(185);
        mn.setTranslateY(-80);
        mn.setOnMouseClicked(event -> {
            primaryStage3.setIconified(true); // Minimiza a janela
        });

        // Adiciona um evento de fechar a tela
        x.setOnMouseClicked(event -> {
            // Fecha a aplicação quando a imagem é clicada
            Platform.exit();
        });

        // Criar um ImageView para exibir a imagem
        // Cria um ImageView para exibir a imagem
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/or.png")));
        ImageView backgroundView = new ImageView(backgroundImage);
        // Cria um Text para exibir a mensagem

        Text text2 = new Text("Você estudou 1 hora ou mais hoje?\ndigite" +
                " Sim ou Não");
        text2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Text text3 = new Text("Você fez exercícos por 40 minutos?\ndigite" +
                " Sim ou Não");
        text3.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Text text4 = new Text("Você se alimentou no horário correto?\ndigite" +
                " Sim ou Não");
        text4.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Text text5 = new Text("Organizou seu horário de dormir?\ndigite" +
                " Sim ou Não");
        text5.setFont(Font.font("Arial", FontWeight.BOLD, 15));


        text2.setTranslateY(0);
        text2.setTranslateX(0);
        text3.setTranslateY(0);
        text3.setTranslateX(0);
        v.setOpacity(0);
        er.setOpacity(0);

        // Cria um TextField para o campo de entrada
        TextField textField = new TextField();
        textField.setPromptText("Digite a resposta aqui");
        TextField textField2 = new TextField();
        textField.setPromptText("Digite a resposta aqui");
        TextField textField3 = new TextField();
        textField.setPromptText("Digite a resposta aqui");
        TextField textField4 = new TextField();
        textField.setPromptText("Digite a resposta aqui");
        TextField textField5 = new TextField();
        textField.setPromptText("Digite a resposta aqui");
        textField.setMaxWidth(100);
        textField2.setMaxWidth(100);
        textField3.setMaxWidth(100);
        textField4.setMaxWidth(100);
        textField5.setMaxWidth(130);
        Label pp = new Label("Nome:");
        pp.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        pp.setTranslateY(38);
        pp.setTranslateX(-100);


        // Cria um layout StackPane para organizar a imagem e o texto
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundView, x, mn, text2, textField
        );
        textField.setTranslateY(40);
        textField.setTranslateX(0);
        textField2.setTranslateY(40);
        textField2.setTranslateX(0);
        textField3.setTranslateY(40);
        textField3.setTranslateX(0);
        textField4.setTranslateY(40);
        textField4.setTranslateX(0);
        textField5.setTranslateY(40);
        textField5.setTranslateX(0);


        textField.setOnAction(event -> {

            String userInput = textField.getText().trim();

            // Variável para decremento
            // Cria uma transição de opacidade para o primeiro elemento
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), v);
            fadeTransition.setFromValue(1); // Opacidade inicial (totalmente opaca)
            fadeTransition.setToValue(0);   // Opacidade final (totalmente transparente)
            fadeTransition.setCycleCount(1);
            // Número de ciclos
            // Cria uma transição de opacidade para o segundo elemento
            FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1.5), er);
            fadeTransition2.setFromValue(1); // Opacidade inicial (totalmente opaca)
            fadeTransition2.setToValue(0);   // Opacidade final (totalmente transparente)
            fadeTransition2.setCycleCount(1); // Número de ciclos

            // Verifica se a entrada do usuário é igual a "sim" (ignorando maiúsculas e minúsculas)
            if (userInput.equalsIgnoreCase("sim")) {

                soma += 25.0;
                double valor = 25.0;
                registrarValor(nome, valor);

                // Inicia a transição para  primeiro elemento quando a condição for verdadeira
                fadeTransition.play();
                StackPane novoPane = new StackPane();
                novoPane.getChildren().addAll(backgroundView, x, mn, v, text3, textField2);
                primaryStage3.setScene(new Scene(novoPane, 500, 200));
            } else if (userInput.equalsIgnoreCase("não")) {
                // Inicia a transição para o segundo elemento quando a condição for falsa
                fadeTransition2.play();
                StackPane novoPane = new StackPane();
                novoPane.getChildren().addAll(backgroundView, x, mn, er, text3, textField2);
                primaryStage3.setScene(new Scene(novoPane, 500, 200));

            } else {


            }
        });

        textField2.setOnAction(event -> {
            String userInput = textField2.getText().trim();

            // Cria uma transição de opacidade para o primeiro elemento
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), v);
            fadeTransition.setFromValue(1); // Opacidade inicial (totalmente opaca)
            fadeTransition.setToValue(0);   // Opacidade final (totalmente transparente)
            fadeTransition.setCycleCount(1); // Número de ciclos

            // Cria uma transição de opacidade para o segundo elemento
            FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1.5), er);
            fadeTransition2.setFromValue(1); // Opacidade inicial (totalmente opaca)
            fadeTransition2.setToValue(0);   // Opacidade final (totalmente transparente)
            fadeTransition2.setCycleCount(1); // Número de ciclos

            // Verifica se a entrada do usuário é igual a "sim" (ignorando maiúsculas e minúsculas)
            if (userInput.equalsIgnoreCase("sim")) {
                // Inicia a transição para o primeiro elemento quando a condição for verdadeira
                fadeTransition.play();
                soma += 25.0;
                double valor2 = 25.0;
                registrarValor2(nome, valor2);

                StackPane novoPane = new StackPane();
                novoPane.getChildren().addAll(backgroundView, x, mn, v, text4, textField3);
                primaryStage3.setScene(new Scene(novoPane, 500, 200));
            } else if (userInput.equalsIgnoreCase("não")) {
                // Inicia

                // a transição para o segundo elemento quando a condição for falsa
                fadeTransition2.play();
                StackPane novoPane = new StackPane();
                novoPane.getChildren().addAll(backgroundView, x, mn, er, text4, textField3);
                primaryStage3.setScene(new Scene(novoPane, 500, 200));
            } else {


            }
        });

        textField3.setOnAction(event -> {
            String userInput = textField3.getText().trim();

            // Cria uma transição de opacidade para o primeiro elemento
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), v);
            fadeTransition.setFromValue(1); // Opacidade inicial (totalmente opaca)
            fadeTransition.setToValue(0);   // Opacidade final (totalmente transparente)
            fadeTransition.setCycleCount(1); // Número de ciclos

            // Cria uma transição de opacidade para o segundo elemento
            FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1.5), er);
            fadeTransition2.setFromValue(1); // Opacidade inicial (totalmente opaca)
            fadeTransition2.setToValue(0);   // Opacidade final (totalmente transparente)
            fadeTransition2.setCycleCount(1); // Número de ciclos

            // Verifica se a entrada do usuário é igual a "sim" (ignorando maiúsculas e minúsculas)
            if (userInput.equalsIgnoreCase("sim")) {
                // Inicia a transição para o primeiro elemento quando a condição for verdadeira
                soma += 25.0;
                double valor3 = 25.0;
                registrarValor3(nome, valor3);

                fadeTransition.play();
                StackPane novoPane = new StackPane();
                novoPane.getChildren().addAll(backgroundView, x, mn, v, textField4, text5);
                primaryStage3.setScene(new Scene(novoPane, 500, 200));
            } else if (userInput.equalsIgnoreCase("não")) {
                // Inicia a transição para o segundo elemento quando a condição for falsa
                fadeTransition2.play();
                StackPane novoPane = new StackPane();
                novoPane.getChildren().addAll(backgroundView, x, mn, er, textField4, text5);
                primaryStage3.setScene(new Scene(novoPane, 500, 200));
            } else {

            }
        });
        textField4.setOnAction(event -> {
            String userInput = textField4.getText().trim();

            // Cria uma transição de opacidade para o primeiro elemento
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), v);
            fadeTransition.setFromValue(1); // Opacidade inicial (totalmente opaca)
            fadeTransition.setToValue(0);   // Opacidade final (totalmente transparente)
            fadeTransition.setCycleCount(1); // Número de ciclos

            // Cria uma transição de opacidade para o segundo elemento
            FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1.5), er);
            fadeTransition2.setFromValue(1); // Opacidade inicial (totalmente opaca)
            fadeTransition2.setToValue(0);   // Opacidade final (totalmente transparente)
            fadeTransition2.setCycleCount(1); // Número de ciclos

            // Verifica se a entrada do usuário é igual a "sim" (ignorando maiúsculas e minúsculas)
            if (userInput.equalsIgnoreCase("sim")) {
                soma += 25.0;

                // Criação do texto do cronômetro
                Text texto = new Text("Redirecionando em " + segundos + " segundos...");
                texto.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                texto.setFill(Color.BLUE);
                texto.setTranslateX(0);
                texto.setTranslateY(40);

                soma += getValueFromTextField(textField);
                soma += getValueFromTextField(textField2);
                soma += getValueFromTextField(textField3);
                soma += getValueFromTextField(textField4);
                double valor4 = 25.0;
                registrarValor4(nome, valor4);


                Text text6 = new Text("O seu progresso do dia foi:\n" + soma + "%");
                text6.setFont(Font.font("Arial", FontWeight.BOLD, 15));
                fadeTransition.play();

                StackPane novoPane = new StackPane();
                novoPane.getChildren().addAll(backgroundView, x, mn, v, text6, texto);
                primaryStage3.setScene(new Scene(novoPane, 500, 200));

                // Criando a timeline para atualizar o cronômetro a cada segundo e redirecionar após 3 segundos
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                    segundos--;
                    // Atualiza o texto do cronômetro
                    texto.setText("Redirecionando em " + segundos + " segundos...");
                    if (segundos == 2) {
                        redirecionarParaOutraPagina(nome);
                    }
                }));
                timeline.play(); // Inicia a timeline
            } else if (userInput.equalsIgnoreCase("não")) {
                // Inicia a transição para o segundo elemento quando a condição for falsa
                soma += getValueFromTextField(textField);
                soma += getValueFromTextField(textField2);
                soma += getValueFromTextField(textField3);
                soma += getValueFromTextField(textField4);
                Text text6 = new Text("O seu progresso do dia foi:\n" + soma + "%");

                // Criação do texto do cronômetro
                Text texto = new Text("Redirecionando em " + segundos + " segundos...");

                    redirecionarParaOutraPagina(nome);

                texto.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                texto.setFill(Color.BLUE);
                texto.setTranslateX(0);
                texto.setTranslateY(40);
                text6.setFont(Font.font("Arial", FontWeight.BOLD, 15));
                fadeTransition2.play();

                StackPane novoPane = new StackPane();
                novoPane.getChildren().addAll(backgroundView, x, mn, text6, texto);

                // Criando a timeline para redirecionar após 3 segundos
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {

                        redirecionarParaOutraPagina(nome);

                }));
                timeline.play();

                primaryStage3.setScene(new Scene(novoPane, 500, 200));
            } else {
                // Caso contrário, fazer algo diferente
            }



        });

        // Configura a cena e exibe a janela
        Scene scene = new Scene(root, 500, 200);

        // Oculta o menu de fechar e minimizar e define o fundo da janela como transparente
        primaryStage3.initStyle(StageStyle.TRANSPARENT);

        primaryStage3.setScene(scene);
        primaryStage3.setTitle("Mensagem Diária Personalizada");
        primaryStage3.show();

    }

    private boolean nomeExisteNoBancoDeDados(String nome) {
        // Obter o recurso do arquivo do banco de dados
        String jarDir = System.getProperty("user.dir");
        String dbPath = jarDir + File.separator + "true.db";


        // Construindo a URL de conexão JDBC
        String url = "jdbc:sqlite:" + dbPath;
        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS total FROM name WHERE nome = ?");
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar o banco de dados: " + e.getMessage());
        } 

        return false;
    }

    private void redirecionarParaOutraPagina(String nome) {
        // Implemente o redirecionamento para outra página aqui
        Stage secondStage2 = new Stage();

        //List<PieChart.Data> pieChartData = consultarDadosDoSQLite();
        List<PieChart.Data> pieChartData = consultarDadosDoSQLite(nome);

        PieChart pieChart = new PieChart();

        pieChart.setMaxWidth(130);
        pieChart.setMinWidth(130);


        pieChart.setMaxHeight(130);
        pieChart.setMinHeight(130);
        pieChart.getData().addAll(pieChartData);
        pieChart.setLabelsVisible(false);
        pieChart.setTranslateX(85);
        pieChart.setTranslateY(-20);
        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/bg2.png"))); // Substitua "imagem.png" pelo caminho da sua imagem
        String iii = nome;
        Label ic = new Label("Seja bem-vindo:" + iii);
        ic.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(0.6);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/next.png"))); // Substitua "imagem.png" pelo caminho da sua imagem

        // Criar um ImageView para exibir a imagem
        ImageView next = new ImageView(image4);
        next.setTranslateX(110);
        next.setTranslateY(70);
        // Definir a cor da sombra
        dropShadow.setColor(Color.WHITE);
        ic.setEffect(dropShadow);
        ic.setTranslateX(-63);
        ic.setTranslateY(-50);
        Label labelNumero = new Label();
        labelNumero.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        labelNumero.setEffect(dropShadow);
        labelNumero.setTranslateX(-90);
        labelNumero.setTranslateY(-18);


// Criar um ImageView para exibir a imagem
        ImageView bg2 = new ImageView(image2);
        Image image6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/mn.png"))); // Substitua "imagem.png" pelo caminho da sua imagem

        // Criar um ImageView para exibir a imagem
        ImageView mn = new ImageView(image6);

        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/x.png"))); // Substitua "imagem.png" pelo caminho da sua imagem

        // Carregar a imagem do resource

        // Criar um ImageView para exibir a imagem
        ImageView x = new ImageView(image3);
        x.setOnMouseClicked(event -> {
            // Fecha a aplicação quando a imagem é clicada
            Platform.exit();
        });

        for (PieChart.Data data : pieChart.getData()) {
            data.getNode().setOnMouseEntered(event -> {
                // Exibir tooltip ao passar o mouse sobre o segmento do gráfico de pizza
                String tooltipText = data.getName() + ": " + (int) data.getPieValue();
                Tooltip tooltip = new Tooltip(tooltipText);
                tooltip.setMaxWidth(200); // Defina a largura máxima do tooltip
                Tooltip.install(data.getNode(), tooltip);
            });
            data.getNode().setOnMouseExited(event -> {
                // Remover tooltip ao retirar o mouse do segmento do gráfico de pizza
                Tooltip.uninstall(data.getNode(), null);
            });
        }


        mn.setOnMouseClicked(event -> {
            secondStage2.setIconified(true); // Minimiza a janela
        });

        mn.setTranslateX(95);
        mn.setTranslateY(-130);
        Label yyy = new Label();
        exibirValorArmazenado(nome, yyy);
        yyy.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        yyy.setEffect(dropShadow);
        yyy.setTranslateX(-68);
        yyy.setTranslateY(15);


        next.setOnMouseClicked(event -> handleNextClick(nome));

        x.setTranslateX(125);
        x.setTranslateY(-130);


        StackPane fly = new StackPane();
        fly.getChildren().addAll(bg2, x, mn, ic, next, labelNumero, yyy, pieChart);
        Scene scene3 = new Scene(fly, 300, 300);
        secondStage2.setScene(scene3);

        // Configurar o modality para o pop-up (para bloquear interações com a stage principal)


        // Exibir a segunda stage (pop-up)
        secondStage2.initStyle(StageStyle.TRANSPARENT);


        incrementarNumero(nome, labelNumero);
        secondStage2.show();

    }

    private void pagina2(String nome) {
        // Implemente o registro do nome no banco de dados ou outra ação necessária aqui
        // Criando uma nova janela
        Stage secondStage = new Stage();


        Label label = new Label("Digite o nome:");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        ;

        CheckBox checkBox1 = new CheckBox("Fácil");
        CheckBox checkBox2 = new CheckBox("Normal");
        CheckBox checkBox3 = new CheckBox("Difícil");
        okButton = new Button("OK");

        // Configurar o evento do botão
        okButton.setOnAction(event -> {
            if (!selectedMode.isEmpty()) {
                // Exibe um alerta com a seleção
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Seleção registrada");
                alert.setHeaderText(null);
                alert.setContentText("Você selecionou: " + selectedMode);
                alert.showAndWait();

                // Registrar a seleção no banco de dados SQLite
                registrarSelecao(selectedMode, nome);
                redirecionarParaOutraPagina(nome);


            } else {
                // Exibe um alerta se nenhum CheckBox estiver selecionado
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Nenhuma seleção");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, selecione uma opção.");
                alert.showAndWait();
            }
        });

        Image image6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/mn.png"))); // Substitua "imagem.png" pelo caminho da sua imagem

        // Criar um ImageView para exibir a imagem
        ImageView mn = new ImageView(image6);


        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/x.png"))); // Substitua "imagem.png" pelo caminho da sua imagem

        // Carregar a imagem do resource

        // Criar um ImageView para exibir a imagem
        ImageView x = new ImageView(image3);
// Adicionando um listener para cada checkbox
        x.setOnMouseClicked(event -> {
            // Fecha a aplicação quando a imagem é clicada
            Platform.exit();
        });


        mn.setOnMouseClicked(event -> {
            secondStage.setIconified(true); // Minimiza a janela
        });

        mn.setTranslateX(95);
        mn.setTranslateY(-130);


        x.setTranslateX(125);
        x.setTranslateY(-130);

        checkBox1.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        ;
        checkBox2.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        ;
        checkBox3.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        ;
        checkBox1.setOnAction(event -> {
            if (checkBox1.isSelected()) {
                selectedMode = "Modo fácil";
                checkBox2.setSelected(false);
                checkBox3.setSelected(false);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Nível");
                alert.setHeaderText(null);
                alert.setContentText("Se você selecionar o nível fácil vai ter" +
                        "que fazer 50% toodo dia");
                alert.showAndWait();
            }
        });

        checkBox2.setOnAction(event -> {
            if (checkBox2.isSelected()) {
                selectedMode = "Modo normal";
                checkBox1.setSelected(false);
                checkBox3.setSelected(false);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Nível normal");
                alert.setHeaderText(null);
                alert.setContentText("Se você selecionar o nível nonrmal vai ter" +
                        "que fazer 75% todo dia");
                alert.showAndWait();
            }
        });

        checkBox3.setOnAction(event -> {
            if (checkBox3.isSelected()) {
                selectedMode = "Modo difícil";
                checkBox1.setSelected(false);
                checkBox2.setSelected(false);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Nível díficil");
                alert.setHeaderText(null);
                alert.setContentText("Se você selecionar o díficil vai ter" +
                        "que fazer 100% toodo dia");
                alert.showAndWait();
            }
        });
        Label op = new Label("Nome para cadastro:");
        op.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        TextField po = new TextField();
        po.setMaxWidth(135);
        checkBox1.setTranslateY(-15);
        checkBox1.setTranslateX(-70);
        checkBox2.setTranslateY(-15);
        checkBox2.setTranslateX(0);
        checkBox3.setTranslateY(-15);
        checkBox3.setTranslateX(70);
        po.setTranslateX(55);
        po.setTranslateY(20);
        op.setTranslateY(20);
        op.setTranslateX(-75);


        // Chame a função de destino com o valor da Label
        // Botão OK para fechar o alerta

        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/bg.png"))); // Substitua "imagem.png" pelo caminho da sua imagem

        // Criar um ImageView para exibir a imagem
        ImageView bg = new ImageView(image2);

        // Criar a segunda stage (pop-up)


        okButton.setTranslateX(0);
        okButton.setTranslateY(65);
        StackPane fly = new StackPane();
        fly.getChildren().addAll(bg, checkBox1, checkBox2, checkBox3, x, mn,
                okButton, op, po);
        Scene scene2 = new Scene(fly, 300, 300);
        secondStage.setScene(scene2);
        secondStage.initStyle(StageStyle.TRANSPARENT);

        // Configurar o modality para o pop-up (para bloquear interações com a stage principal)


        // Exibir a segunda stage (pop-up)
        secondStage.show();

    }


    private void registrarSelecao(String selectedMode, String nome) {
        try {
            // Nome do arquivo do banco de dados
            String jarDir = System.getProperty("user.dir");
            String dbPath = jarDir + File.separator + "true.db";

            // Obtendo a URL do recurso do arquivo do banco de dados

            // Construindo a URL de conexão JDBC
            String url = "jdbc:sqlite:" + dbPath;

            // Comando SQL para inserir a seleção na tabela 'selecao'
            String sql = "INSERT INTO name(ch,nome) VALUES (?,?)";

            try (Connection conn = DriverManager.getConnection(url);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, selectedMode);
                pstmt.setString(2, nome);

                pstmt.executeUpdate();
                System.out.println("Seleção registrada com sucesso no SQLite.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao registrar a seleção no SQLite: " + e.getMessage());
        }
    }


    private Connection getConnection() throws SQLException {
        String jarDir = System.getProperty("user.dir");
        String dbPath = jarDir + File.separator + "true.db";

        String url = "jdbc:sqlite:" + dbPath;
        return DriverManager.getConnection(url);
    }

    public void incrementarNumero(String nome, Label labelNumero) {
        try (Connection conn = getConnection()) {
            if (foiAtualizadoHoje(conn, nome)) {
                int numeroAtual = getNumeroAtual(conn, nome);

                labelNumero.setText("Total de dias:" + numeroAtual);
                return;
            }

            int numeroAtual = getNumeroAtual(conn, nome);
            int novoNumero = numeroAtual + 1;

            labelNumero.setText("Total de dias:" + novoNumero);
            updateNumero(conn, nome, novoNumero);
            registrarDataAtualizacao(conn, nome);

            System.out.println("Número atualizado com sucesso no banco de dados.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o número no banco de dados: " + e.getMessage());
        }
    }

    private void exibirAlerta(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private int getNumeroAtual(Connection conn, String nome) throws SQLException {
        String sqlSelect = "SELECT numero FROM name WHERE nome = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {
            pstmt.setString(1, nome);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getInt("numero") : 0;
            }
        }
    }

    private void updateNumero(Connection conn, String nome, int novoNumero) throws SQLException {
        String sqlUpdate = "UPDATE name SET numero = ? WHERE nome = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
            pstmt.setInt(1, novoNumero);
            pstmt.setString(2, nome);
            pstmt.executeUpdate();
        }
    }

    private int getTotalDias(String nome) throws SQLException {
        try (Connection conn = getConnection()) {
            LocalDate hoje = LocalDate.now();
            String sqlSelect = "SELECT numero FROM name WHERE nome = ? AND data = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {
                pstmt.setString(1, nome);
                pstmt.setString(2, hoje.toString());
                try (ResultSet rs = pstmt.executeQuery()) {
                    return rs.next() ? rs.getInt("numero") : 0;
                }
            }
        }
    }

    private boolean foiAtualizadoHoje(Connection conn, String nome) throws SQLException {
        LocalDate hoje = LocalDate.now();
        String sqlVerificar = "SELECT COUNT(*) AS total FROM name WHERE nome = ? AND data = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlVerificar)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, hoje.toString());
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt("total") > 0;
            }
        }
    }

    private void registrarDataAtualizacao(Connection conn, String nome) throws SQLException {
        LocalDate hoje = LocalDate.now();
        String sqlAtualizarData = "UPDATE name SET data = ? WHERE nome = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlAtualizarData)) {
            pstmt.setString(1, hoje.toString());
            pstmt.setString(2, nome);
            pstmt.executeUpdate();
        }
    }

    private int getValueFromTextField(TextField textField) {
        return 0;
    }

    public void main() {
    }


    private void armazenarValor(String nome, double valor) {
        // Nome do arquivo do banco de dados
        String jarDir = System.getProperty("user.dir");
        String dbPath = jarDir + File.separator + "true.db";


        // Construindo a URL de conexão JDBC
        String url = "jdbc:sqlite:" + dbPath;

        // Comando SQL para inserir o valor na tabela 'valores'
        String sql = "UPDATE name SET soma1 = ? WHERE nome = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valor);
            stmt.executeUpdate();
            System.out.println("Valor armazenado com sucesso: " + valor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void armazenarValor2(String nome, double valor2) {
        // Nome do arquivo do banco de dados
        String jarDir = System.getProperty("user.dir");
        String dbPath = jarDir + File.separator + "true.db";


        // Construindo a URL de conexão JDBC
        String url = "jdbc:sqlite:" + dbPath;

        // Comando SQL para inserir o valor na tabela 'valores'
        String sql = "UPDATE name SET soma2 = ? WHERE nome = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valor2);
            stmt.executeUpdate();
            System.out.println("Valor armazenado com sucesso: " + valor2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void armazenarValor3(String nome, double valor3) {
        // Nome do arquivo do banco de dados
        String jarDir = System.getProperty("user.dir");
        String dbPath = jarDir + File.separator + "true.db";


        // Construindo a URL de conexão JDBC
        String url = "jdbc:sqlite:" + dbPath;

        // Comando SQL para inserir o valor na tabela 'valores'
        String sql = "UPDATE name SET soma3 = ? WHERE nome = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valor3);
            stmt.executeUpdate();
            System.out.println("Valor armazenado com sucesso: " + valor3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void armazenarValor4(String nome, double valor4) {
        // Nome do arquivo do banco de dados
        String jarDir = System.getProperty("user.dir");
        String dbPath = jarDir + File.separator + "true.db";


        // Construindo a URL de conexão JDBC
        String url = "jdbc:sqlite:" + dbPath;

        // Comando SQL para inserir o valor na tabela 'valores'
        String sql = "UPDATE name SET soma4 = ? WHERE nome = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valor4);
            stmt.executeUpdate();
            System.out.println("Valor armazenado com sucesso: " + valor4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private List<PieChart.Data> consultarDadosDoSQLite(String nome) {
        String jarDir = System.getProperty("user.dir");
        String dbPath = jarDir + File.separator + "true.db";


        // Construindo a URL de conexão JDBC
        String url = "jdbc:sqlite:" + dbPath;
        List<PieChart.Data> pieChartData = new ArrayList<>();

        try {
            final String nomeFinal = nome; // Declaração final ou efetivamente final da variável 'nome'
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement("SELECT soma1, soma2, soma3, soma4 FROM name WHERE nome = ?");
            stmt.setString(1, nomeFinal); // Use a variável final ou efetivamente final
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                double valor1 = rs.getDouble("soma1");
                double valor2 = rs.getDouble("soma2");
                double valor3 = rs.getDouble("soma3");
                double valor4 = rs.getDouble("soma4");

                // Adicionar os dados ao pieChartData
                pieChartData.add(new PieChart.Data("Estudo", valor1));
                pieChartData.add(new PieChart.Data("Exercícios", valor2));
                pieChartData.add(new PieChart.Data("Alimentação", valor3));
                pieChartData.add(new PieChart.Data("Horário", valor4));
            }

            conn.close(); // Fechar a conexão após o uso
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pieChartData;
    }
    private void exibirValorArmazenado(String nome, Label yyy) {
        // Nome do arquivo do banco de dados
        String jarDir = System.getProperty("user.dir");
        String dbPath = jarDir + File.separator + "true.db";


        // Construindo a URL de conexão JDBC
        String url = "jdbc:sqlite:" + dbPath;
        // Comando SQL para selecionar o valor mais recente da tabela 'valores' com base no nome
        String sql = "SELECT soma1,soma2,soma3,soma4 FROM name WHERE nome = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double valor = rs.getDouble("soma1");
                double valor2 = rs.getDouble("soma2");
                double valor3 = rs.getDouble("soma3");
                double valor4 = rs.getDouble("soma4");
                yyy.setText("Progresso de:" + (valor + valor2 + valor3 + valor4) + "%");
                System.out.println("Valor armazenado no banco de dados para " + nome + ": " + valor);
            } else {
                System.out.println("Nenhum valor encontrado no banco de dados para " + nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void calcularSomaTotal(String nome ) {
        String jarDir = System.getProperty("user.dir");
        String dbPath = jarDir + File.separator + "true.db";


        // Construindo a URL de conexão JDBC
        String url = "jdbc:sqlite:" + dbPath;
        String sql = "SELECT soma1, soma2, soma3, soma4,verificar FROM name WHERE nome = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double valor1 = rs.getDouble("soma1");
                double valor2 = rs.getDouble("soma2");
                double valor3 = rs.getDouble("soma3");
                double valor4 = rs.getDouble("soma4");
                int verificar = rs.getInt("verificar");

                double somaTotal = valor1 + valor2 + valor3 + valor4;

                // Exibir alerta com base no valor calculado
                if (somaTotal > 50.0 && verificar==1) {
                    Alert ui = new Alert(Alert.AlertType.INFORMATION);
                    ui.setTitle(null);
                    ui.setHeaderText(null);
                    ui.setContentText("Parabéns! Você atingiu a meta de hoje no nível fácuk!!!");
                    ui.showAndWait();
                }

                return;
            } else {
                System.out.println("Nenhum valor encontrado no banco de dados para " + nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return; // Retorna 0 se não houver dados encontrados
    }
    private void calcularSomaTotal2(String nome) {
        String jarDir = System.getProperty("user.dir");
        String dbPath = jarDir + File.separator + "true.db";

        // Obtendo a URL do recurso do arquivo do banco de dados

        // Construindo a URL de conexão JDBC
        String url = "jdbc:sqlite:" + dbPath;
        String sql = "SELECT soma1, soma2, soma3, soma4,verificar FROM name WHERE nome = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double valor1 = rs.getDouble("soma1");
                double valor2 = rs.getDouble("soma2");
                double valor3 = rs.getDouble("soma3");
                double valor4 = rs.getDouble("soma4");
                int verificar = rs.getInt("verificar");

                double somaTotal = valor1 + valor2 + valor3 + valor4;

                // Exibir alerta com base no valor calculado
                if (somaTotal > 75.0 && verificar==1) {
                    Alert ui = new Alert(Alert.AlertType.INFORMATION);
                    ui.setTitle(null);
                    ui.setHeaderText(null);
                    ui.setContentText("Parabéns! Você atingiu a meta de hoje no nível normal!!!");
                    ui.showAndWait();

                }

                return;
            } else {
                System.out.println("Nenhum valor encontrado no banco de dados para " + nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return; // Retorna 0 se não houver dados encontrados
    }
    private void calcularSomaTotal3(String nome) {
        String jarDir = System.getProperty("user.dir");
        String dbPath = jarDir + File.separator + "true.db";


        // Construindo a URL de conexão JDBC
        String url = "jdbc:sqlite:" + dbPath;
        String sql = "SELECT soma1, soma2, soma3, soma4,verificar FROM name WHERE nome = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double valor1 = rs.getDouble("soma1");
                double valor2 = rs.getDouble("soma2");
                double valor3 = rs.getDouble("soma3");
                double valor4 = rs.getDouble("soma4");
                int verificar = rs.getInt("verificar");

                double somaTotal = valor1 + valor2 + valor3 + valor4;

                // Exibir alerta com base no valor calculado
                if (somaTotal > 100.0  && verificar == 1) {
                    Alert ui = new Alert(Alert.AlertType.INFORMATION);
                    ui.setTitle(null);
                    ui.setHeaderText(null);
                    ui.setContentText("Parabéns! Você atingiu a meta de hoje no nível difícil!!!");
                    ui.showAndWait();
                }

                return;
            } else {
                System.out.println("Nenhum valor encontrado no banco de dados para " + nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return; // Retorna 0 se não houver dados encontrados
    }



    private void registrarValor(String nome, double valor) {
        String jarDir = System.getProperty("user.dir");
        String dbPath = jarDir + File.separator + "true.db";


        // Construindo a URL de conexão JDBC
        String url = "jdbc:sqlite:" + dbPath;
        // Comando SQL para selecionar o valor mais recente da tabela 'valores' com base no nome
        String sql = "UPDATE name SET soma1 = ? WHERE nome = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valor);
            stmt.setString(2, nome);

            stmt.executeUpdate();

            System.out.println("Valor registrado com sucesso para " + nome + ": " + valor);
        } catch (SQLException e) {
            System.err.println("Erro ao registrar valor no banco de dados: " + e.getMessage());
        }
    }
    private void registrarValor2(String nome, double valor2) {
        String jarDir = System.getProperty("user.dir");
        String dbPath = jarDir + File.separator + "true.db";


        // Construindo a URL de conexão JDBC
        String url = "jdbc:sqlite:" + dbPath;
        // Comando SQL para selecionar o valor mais recente da tabela 'valores' com base no nome
        String sql = "UPDATE name SET soma2 = ? WHERE nome = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valor2);
            stmt.setString(2, nome);
            stmt.executeUpdate();

            System.out.println("Valor registrado com sucesso para " + nome + ": " + valor2);
        } catch (SQLException e) {
            System.err.println("Erro ao registrar valor no banco de dados: " + e.getMessage());
        }
    }
    private void registrarValor3(String nome, double valor3) {
        String jarDir = System.getProperty("user.dir");
        String dbPath = jarDir + File.separator + "true.db";


        // Construindo a URL de conexão JDBC
        String url = "jdbc:sqlite:" + dbPath;
        // Comando SQL para selecionar o valor mais recente da tabela 'valores' com base no nome
        String sql = "UPDATE name SET soma3 = ? WHERE nome = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valor3);
            stmt.setString(2, nome);
            stmt.executeUpdate();

            System.out.println("Valor registrado com sucesso para " + nome + ": " + valor3);
        } catch (SQLException e) {
            System.err.println("Erro ao registrar valor no banco de dados: " + e.getMessage());
        }
    }


    private void registrarValor4(String nome, double valor4) {
        String jarDir = System.getProperty("user.dir");
        String dbPath = jarDir + File.separator + "true.db";


        // Construindo a URL de conexão JDBC
        String url = "jdbc:sqlite:" + dbPath;
        // Comando SQL para selecionar o valor mais recente da tabela 'valores' com base no nome
        String sql = "UPDATE name SET soma4 = ? WHERE nome = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valor4);
            stmt.setString(2, nome);
            stmt.executeUpdate();

            System.out.println("Valor registrado com sucesso para " + nome + ": " + valor4);
        } catch (SQLException e) {
            System.err.println("Erro ao registrar valor no banco de dados: " + e.getMessage());
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void handleNextClick(String nome) {
        // Verificar se o botão já foi clicado
        if (!botaoClicado) {
            try {
                String jarDir = System.getProperty("user.dir");
                String dbPath = jarDir + File.separator + "true.db";

                // Obtendo a URL do recurso do arquivo do banco de dados

                // Construindo a URL de conexão JDBC
                String url = "jdbc:sqlite:" + dbPath;
                // Estabelecer a conexão com o banco de dados
                Connection connection = DriverManager.getConnection(url);

                // Verificar se já foi clicado hoje
                LocalDate today = LocalDate.now();
                if (!wasClickedToday(connection, today, nome)) {
                    // Se ainda não foi clicado hoje, atualizar o valor de verificar para 1
                    updateVerificationValueTo1(connection, nome);

                    // Executar a ação correspondente
                    start2(nome);

                    // Marcar o botão como clicado
                    botaoClicado = true;
                } else {
                    showAlert("Aviso", "Você só pode clicar no botão uma vez por dia.");
                }

                // Fechar a conexão com o banco de dados
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Aviso", "Você já clicou no botão hoje.");
        }
    }

    // Método para verificar se já foi clicado hoje
    private boolean wasClickedToday(Connection connection, LocalDate date, String nome) throws SQLException {
        String selectSQL = "SELECT verificar FROM name WHERE nome = ? AND data = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, date.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next() && resultSet.getInt("verificar") == 1;
    }

    // Método para atualizar o valor de verificar para 1 ao clicar no botão
    private void updateVerificationValueTo1(Connection connection, String nome) throws SQLException {
        String updateSQL = "UPDATE name SET verificar = 1, data = ? WHERE nome = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
        preparedStatement.setString(1, LocalDate.now().toString());
        preparedStatement.setString(2, nome);
        preparedStatement.executeUpdate();
    }
    public static void main(String[] args) {
        launch(args);
    }

}