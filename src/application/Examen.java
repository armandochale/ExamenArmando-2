package application;





import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import java.lang.Math;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Examen extends Application {

	int segundos=0;
	int first=0;
	int second=0;
	int third=0;
	int result=0;
	Timer timer;
	int num1=0;
	int num2=0;

    @Override
    public void start(Stage primaryStage) throws Exception{

        BorderPane pnl = new BorderPane();
        
        Button btn = new Button();
        btn.setText("Aceptar");
        Button btn1 = new Button();
        btn1.setText("Reiniciar");
        
       
        TextField numT1 =new TextField();


        num1=(int)(Math.random()*1000);
		num2=(int)(Math.random()*1000);



        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
				if(numT1.getText().length()!=0) {
					result = Integer.parseInt(numT1.getText());

					if ((num1 * num2) == result) {
						timer.cancel();
						int a=getSegundos();
						Alert error = new Alert(AlertType.INFORMATION);
						error.setTitle("Tiempo");
						error.setHeaderText("Segundos utilizados en resolver la multiplicación: "+a);
						error.setContentText(null);
						error.showAndWait();
						num1=(int)(Math.random()*1000);
						num2=(int)(Math.random()*1000);
						segundos=0;
						numT1.clear();
						pnl.setCenter(addGridPane(numT1,num1,num2));
					} else {
						Alert error = new Alert(AlertType.ERROR);
						error.setTitle("ERROR");
						error.setHeaderText("Se ha equivocado");
						error.setContentText(null);
						error.showAndWait();
					}
				}else{
					Alert error = new Alert(AlertType.WARNING);
					error.setTitle("ERROR");
					error.setHeaderText("Esta vacio el TextField");
					error.setContentText(null);
					error.showAndWait();
				}



            	
            	
            }
        });

		btn1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timer.cancel();
				num1=(int)(Math.random()*1000);
				num2=(int)(Math.random()*1000);
				segundos=0;
				numT1.clear();
				pnl.setCenter(addGridPane(numT1,num1,num2));

			}
		});

        pnl.setBottom(addGridPane2(btn,btn1));
        pnl.setCenter(addGridPane(numT1,num1,num2));


        primaryStage.setTitle("Examen - Ejercicio 1");
        primaryStage.setScene(new Scene(pnl, 600, 400));
        primaryStage.show();

    }

    
    public GridPane addGridPane(TextField numT1, int num1, int num2) {
	    GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(10, 0, 10, 10));
	    grid.setStyle("-fx-background-color: #C84FF8;");
		Hilo a=new Hilo();
		a.start();
	    // Title in column 3, row 1
	    Text Title = new Text(" ");
	    Title.setFont(Font.font("Arial", FontWeight.BOLD, 60));
	    grid.add(Title, 3, 0);

	    // Title in column 4, row 1
	    Text Title2 = new Text("Examen ");
	    Title2.setFont(Font.font("Arial", FontWeight.BOLD, 60));
	    grid.add(Title2, 4, 0);

		Text text3 = new Text("Obten numero "+num1+" x "+num2);
		text3.setFont(Font.font("Arial", FontWeight.BOLD, 28));
		grid.add(text3,  4,1);



	    Text text1 = new Text("             Numero 1:");
	    grid.add(text1,  3, 4);
	    grid.add(numT1,4,4 );




	    return grid;
	}

    public GridPane addGridPane2(Button btn,Button btn1){

    	GridPane grid2 = new GridPane();
    	grid2.setHgap(10);
	    grid2.setVgap(10);
	    grid2.setPadding(new Insets(0, 80, 70, 110));
	    grid2.setStyle("-fx-background-color: #DE8DFE;");
	    grid2.add(btn, 19, 1);
		grid2.add(btn1, 8, 1);



    	return grid2;
    }
    public void error(){
    	Alert resul =new Alert(AlertType.ERROR);
    	resul.setTitle("�ACCION INCORRECTA!");
    	resul.setHeaderText("Hay 2 numeros iguales se debe cambiar uno de ellos");
    	resul.setContentText(null);
    	resul.showAndWait();
    }
    
	public class Hilo extends Thread{
    	public void run(){

			timer=new Timer();
    		timer.schedule(new Contar(),0,1000);
		}
	}
	class Contar extends TimerTask{

    	public void run(){
    		segundos++;
		}
	}
    public static void main(String[] args) {
        launch(args);
    }
	public int getSegundos(){
    	return this.segundos;
	}
}