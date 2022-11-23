/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.reporte to edit this template
 */
package reporte;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joan
 */
public class reporte {
    public void reporteBuild() throws FileNotFoundException{
    
       
         
          Document pdf = new Document();
     
              
              String ruta = System.getProperty("user.home");
             
           //Crear la primera captura de pantalla
         
              
             
              
          //Crear PDF
          
          String sol = "";
         
          
        try {
            PdfWriter.getInstance(pdf, new FileOutputStream(ruta + "/Desktop/" + sol + ".pdf"));
        } catch (DocumentException ex) {
            Logger.getLogger(reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
          pdf.open();  
       //crear la primera tabla
        PdfPTable tabla1 = new PdfPTable(4);
        tabla1.addCell("Candidato");
        tabla1.addCell("Carrera");
        tabla1.addCell("Año");
        tabla1.addCell("Miembro");

        //crear la segunda tabla
        PdfPTable tabla2 = new PdfPTable(4);
        tabla2.addCell("Candidato");
        tabla2.addCell("Carrera");
        tabla2.addCell("Año");
        tabla2.addCell("Presidente");

        //organizar de menor a mayor
       

       
           
      //banner para el pdf
     
            
      //banner.setAlignment(Chunk.ALIGN_CENTER);
       //crear todos los paragrap necesarios
       Paragraph intro = new Paragraph("");
       
       intro.setAlignment(Paragraph.ALIGN_CENTER);
      //  Paragraph mATRICULA = new Paragraph("Matrícula: " + E.getMatricula());
      //  Paragraph votos = new Paragraph("Votos totales: " + E.getVotos());
        Paragraph p = new Paragraph("Orden según votos por miembro");
          p.setAlignment(Paragraph.ALIGN_CENTER);
        Paragraph pa = new Paragraph("Orden según votos por presidente");
        pa.setAlignment(Paragraph.ALIGN_CENTER);  
      Paragraph grafico1= new Paragraph("Estadísticas de las boletas");
      grafico1.setAlignment(Paragraph.ALIGN_CENTER);  
      Paragraph grafico2= new Paragraph("Estadísticas de la asistencia al voto"); 
      grafico2.setAlignment(Paragraph.ALIGN_CENTER);     
      
            
       

    }
}
