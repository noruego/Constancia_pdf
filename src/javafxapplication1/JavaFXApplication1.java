/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class JavaFXApplication1 extends Application {
    String nombre_prof="Profesor",depa="Departamento";
   Date fechaDate = new Date();
    SimpleDateFormat formateador = new SimpleDateFormat("dd '/' MMMM '/' yyyy", new Locale("es","mx"));
    String profesor="",departamento="";
    Conexion con = new Conexion();
    @Override
    public void start(Stage primaryStage) {
       con.getCon();
       Button btn = new Button();
        TextField txt =new TextField();
        btn.setText("Generar PDF");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("accion del boton");
                generate_pdf(txt.getText());
            }
        });
        
        StackPane root = new StackPane();
        
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        
        btn.setPrefSize(100, 20);
        txt.setPrefSize(200, 20);
        hbox.getChildren().addAll(txt, btn);

    
        
        root.getChildren().add(hbox);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Alumnos atendidos");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    private void generate_pdf(String rfc)
    {
        con.get_profesor_ago_dic(rfc);
        con.get_profesor_ene_jun(rfc);
        int alumnos_atendi=0;
        int alumnos_atendi2=0;
        if(con.materia_ago_dic[0]!=null)
        {
            profesor =con.materia_ago_dic[0].getProfesor();
            departamento =  con.materia_ago_dic[0].getCarrera();
        }else
        {
            profesor =con.materia_ene_jun[0].getProfesor();
            departamento =  con.materia_ene_jun[0].getCarrera();
        }
        try {

            OutputStream file = new FileOutputStream(new File("file.pdf"));
            Document document = new Document();
             PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
            //document.newPage();
            
            
            FileInputStream template = new FileInputStream("constancia.pdf");

//Load it into a reader
        PdfReader reader = new PdfReader(template);

        //Get the page of the template you like
        PdfImportedPage page = writer.getImportedPage(reader, 1);

        //Now you can add it to you report
        PdfContentByte cb = writer.getDirectContent();
        cb.addTemplate(page,0,0);
            writer.setStrictImageSequence(true);

            Paragraph parrafo_fecha = new Paragraph("\n\n\n\n\nCelaya, Guanajuato, "+formateador.format(fechaDate));
            parrafo_fecha.setAlignment(Element.ALIGN_RIGHT);
            document.add(parrafo_fecha);
            
            
            Font negritas = new Font();
            negritas.setStyle(Font.BOLD);
            negritas.setSize(10);
            
            Font font_tabla_negritas = new Font();
            font_tabla_negritas.setStyle(Font.BOLD);
            font_tabla_negritas.setSize(8);
            
            Font font_tabla = new Font();
            font_tabla.setStyle(Font.NORMAL);
            font_tabla.setSize(8);
            
            Font negritas_miniatura = new Font();
            negritas_miniatura.setStyle(Font.NORMAL);
            negritas_miniatura.setSize(8);
            
            
            Paragraph parrafo_depto = new Paragraph("DEPARTAMENTO DE SERVICIOS ESCOLARES",negritas);
            parrafo_depto.setAlignment(Element.ALIGN_RIGHT);
            document.add(parrafo_depto);
           
            Paragraph parrafo_asunto = new Paragraph("ASUNTO: Constancia",negritas);           
            parrafo_asunto.setAlignment(Element.ALIGN_RIGHT);
            document.add(parrafo_asunto);
            
            Paragraph parrafo_corres = new Paragraph("AQUIEN CORRESPONDA",negritas);           
            parrafo_corres.setAlignment(Element.ALIGN_LEFT);
            document.add(parrafo_corres);
            
            Paragraph parrafo_por_medio = new Paragraph("Por medio de la presente, se hace constar que el C."+ profesor + ",adscrito al "
                    + departamento+" de este instituto,impartio las materias materias que se muestran en la siguiente relaciòn, ademas de "
                            + "la clave y el numero de alumnos atendidos por materia en los periodos:");           
            parrafo_por_medio.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(parrafo_por_medio);
            
            Paragraph parrafo_ene_jun = new Paragraph("\nENERO-JUNIO 2017",negritas);           
            parrafo_ene_jun.setAlignment(Element.ALIGN_CENTER);
            document.add(parrafo_ene_jun);
            
            //TABLA
            
             
            
            
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(90);           
            
            Paragraph parrafo_clave = new Paragraph("Clave",font_tabla_negritas);           
            parrafo_corres.setAlignment(Element.ALIGN_CENTER);
            table.addCell(parrafo_clave);
            
            Paragraph parrafo_materia = new Paragraph("Materia",font_tabla_negritas);           
            parrafo_materia.setAlignment(Element.ALIGN_CENTER);
            table.addCell(parrafo_materia);
            
            Paragraph parrafo_nivel = new Paragraph("Nivel",font_tabla_negritas);           
            parrafo_nivel.setAlignment(Element.ALIGN_CENTER);
            table.addCell(parrafo_nivel);
            
            Paragraph parrafo_espe = new Paragraph("Especialidad",font_tabla_negritas);           
            parrafo_espe.setAlignment(Element.ALIGN_CENTER);
            table.addCell(parrafo_espe);
            
            Paragraph parrafo_alumnos = new Paragraph("Alumnos Atendidos",font_tabla_negritas);           
            parrafo_alumnos.setAlignment(Element.ALIGN_CENTER);
            table.addCell(parrafo_alumnos);
          
            for (int i = 0; i < con.con_alumnos1; i++) {
                table.addCell(new Phrase(con.materia_ene_jun[i].getCve_materia(),font_tabla));
                table.addCell(new Phrase(con.materia_ene_jun[i].getMateria(),font_tabla));
                table.addCell(new Phrase(con.materia_ene_jun[i].getNivel(),font_tabla));
                table.addCell(new Phrase(con.materia_ene_jun[i].getCarrera(),font_tabla));
                table.addCell(new Phrase(String.valueOf(con.materia_ene_jun[i].getNo_alumnos()),font_tabla));
                alumnos_atendi+=con.materia_ene_jun[i].getNo_alumnos();
            }
            
             
            // Si desea crear una celda de mas de una columna
            // Cree un objecto Cell y cambie su propiedad span
             
            PdfPCell celdaFinal = new PdfPCell(new Paragraph("Total",font_tabla));
             
            // Indicamos cuantas columnas ocupa la celda
            celdaFinal.setColspan(4);
            table.addCell(celdaFinal);
            table.addCell(new Phrase(String.valueOf(alumnos_atendi),font_tabla_negritas)); 
            // Agregamos la tabla al documento  
            
            document.add(table);
             
            
            Paragraph parrafo_ago_dic = new Paragraph("\nAGOSTO-DICIEMBRE 2017",negritas);           
            parrafo_ago_dic.setAlignment(Element.ALIGN_CENTER);
            document.add(parrafo_ago_dic);

            
            PdfPTable table2 = new PdfPTable(5);
            table2.setWidthPercentage(90);
            
            table2.addCell(parrafo_clave);
            
            table2.addCell(parrafo_materia);
            
            table2.addCell(parrafo_nivel);
            
            table2.addCell(parrafo_espe);
            
            table2.addCell(parrafo_alumnos);
           
            for (int i = 0; i < con.con_alumnos2; i++) {
                table2.addCell(new Phrase(con.materia_ago_dic[i].getCve_materia(),font_tabla));
                table2.addCell(new Phrase(con.materia_ago_dic[i].getMateria(),font_tabla));
                table2.addCell(new Phrase(con.materia_ago_dic[i].getNivel(),font_tabla));
                table2.addCell(new Phrase(con.materia_ago_dic[i].getCarrera(),font_tabla));
                table2.addCell(new Phrase(String.valueOf(con.materia_ago_dic[i].getNo_alumnos()),font_tabla));
                alumnos_atendi2+=con.materia_ago_dic[i].getNo_alumnos();
            }
             
            // Si desea crear una celda de mas de una columna
            // Cree un objecto Cell y cambie su propiedad span
             
            
            // Indicamos cuantas columnas ocupa la celda
            celdaFinal.setColspan(4);
            table2.addCell(celdaFinal);
            table2.addCell(new Phrase(String.valueOf(alumnos_atendi2),font_tabla_negritas)); 
            // Agregamos la tabla al documento  
            
            document.add(table2);
            
            PdfPTable table3 = new PdfPTable(5);
            table3.setWidthPercentage(90);
            
            PdfPCell celdaFinal2 = new PdfPCell(new Paragraph("TOTAL ALUMNOS ATENDIDOS EN EL AÑO",negritas));
            celdaFinal2.setColspan(4);
            table3.addCell(celdaFinal2);
            table3.addCell(new Phrase(String.valueOf(alumnos_atendi+alumnos_atendi2),negritas));
              
            document.add(table3);
            
            Paragraph parrafo_sirva = new Paragraph("Sirva la presente para los fines que al interesado convengan.");           
            //parrafo_sirva.setAlignment(Element.ALIGN_RIGHT);
            document.add(parrafo_sirva);

            Paragraph parrafo_atentamente = new Paragraph("ATENTAMENTE",negritas);           
            //parrafo_sirva.setAlignment(Element.ALIGN_RIGHT);
            document.add(parrafo_atentamente);
            
            Paragraph parrafo_la_tecnica = new Paragraph("La tecnica por un México mejor",negritas_miniatura);           
            //parrafo_sirva.setAlignment(Element.ALIGN_RIGHT);
            document.add(parrafo_la_tecnica);
            
            Paragraph parrafo_ing = new Paragraph("\n\nING. OSCAR GRIMALDO AGUAYO",negritas);           
            //parrafo_sirva.setAlignment(Element.ALIGN_RIGHT);
            document.add(parrafo_ing);
            Paragraph parrafo_jefe = new Paragraph("JEFE DEL DEPARTAMENTO DE SERVICIOS ESCOLARES",negritas);           
            //parrafo_sirva.setAlignment(Element.ALIGN_RIGHT);
            document.add(parrafo_jefe);
            
            
            
            document.close();
            file.close();
            
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
