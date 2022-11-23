/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.reporte to edit this template
 */
package utiles;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Vector;
import modelos.Departamento;

/**
 *
 * @author joan
 */
public class reporte {

    public static void reporteBuild(Departamento d, boolean flag,int anno, Tupla<Tupla<Integer, Integer>,Tupla<Object[], Object[]>> informacionEncuestas, Vector<String> preguntas,Vector<String>justificaciones,String conlcusiones) throws FileNotFoundException, DocumentException {
     
        Document pdf = new Document();

        String ruta = System.getProperty("user.dir");

        if (!flag) {
            PdfWriter.getInstance(pdf, new FileOutputStream(ruta + "/" + d.getNombreDepartamento() + ".pdf"));
        }
        PdfWriter.getInstance(pdf, new FileOutputStream(ruta + System.getProperty("file.separator") + "Desktop" + System.getProperty("file.separator") + d.getNombreDepartamento() + ".pdf"));
        pdf.open();
        //poniendo el encabezado del pdf
        Paragraph encabezado = new Paragraph("Las Tunas " + LocalDate.now()+"\n\n");
        encabezado.setAlignment(Paragraph.ALIGN_LEFT);
        pdf.add(encabezado);
        Paragraph departamentoOBJ = new Paragraph("Informe sobre los resultados del instrumento aplicado en el " + d.getNombreDepartamento() + " sobre el desempeño de los cuadros.\n");
        departamentoOBJ.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        pdf.add(departamentoOBJ);
        
        Paragraph plantlla = new Paragraph("Plantilla de trabajadores: "+informacionEncuestas.getElemento1().getElemento1()+"\n");
        plantlla.setAlignment(Paragraph.ALIGN_LEFT);
        pdf.add(plantlla);
        double porcientoDeAsistencia= (informacionEncuestas.getElemento1().getElemento1()*100)/informacionEncuestas.getElemento1().getElemento2();
        Paragraph muestra = new Paragraph("Muestra total: "+informacionEncuestas.getElemento1().getElemento2()+", para un "+porcientoDeAsistencia);
        muestra.setAlignment(Paragraph.ALIGN_LEFT);
        pdf.add(muestra);
        Paragraph personaOBJ = new Paragraph("Con relación al trabajo de "+d.getPersonaObjetivo()+"\n");
        personaOBJ.setAlignment(Paragraph.ALIGN_LEFT);
        pdf.add(personaOBJ);
        for (int i = 0; i < informacionEncuestas.getElemento2().getElemento1().length; i++) {
        String pregunta= preguntas.elementAt(i);
        Tupla t = (Tupla<Integer[],Vector<String>>)informacionEncuestas.getElemento2().getElemento1()[i];
        Paragraph preguntaP = new Paragraph(pregunta+"\n");
        preguntaP.setAlignment(Paragraph.ALIGN_LEFT);
        pdf.add(preguntaP);
            List lista = new List(true);
        Integer[] preguntasOpciones= (Integer[]) t.getElemento1();
        double siPorciento = (preguntasOpciones[0]*100)/preguntasOpciones[0]+preguntasOpciones[1]+preguntasOpciones[2];
        double noPorciento = (preguntasOpciones[1]*100)/preguntasOpciones[0]+preguntasOpciones[1]+preguntasOpciones[2];
        double enOcacionesPorciento = (preguntasOpciones[2]*100)/preguntasOpciones[0]+preguntasOpciones[1]+preguntasOpciones[2];
        lista.add(new ListItem("Porciento de si: "+siPorciento));
        lista.add(new ListItem("Porciento de si: "+noPorciento));
        lista.add(new ListItem("Porciento de si: "+enOcacionesPorciento));
        pdf.add(lista);
        Paragraph salto = new Paragraph("\n");
        pdf.add(salto);
        }
        //cargando la encuesta secuendaria
        for (int i = 0; i < informacionEncuestas.getElemento2().getElemento2().length; i++) {
        String pregunta= "";
        pregunta = switch (i) {
                case 6 -> preguntas.elementAt(14);
                case 7 -> preguntas.elementAt(15);
                default -> preguntas.elementAt(i);
            };
        Tupla t = (Tupla<Integer[],Vector<String>>)informacionEncuestas.getElemento2().getElemento2()[i];
        Paragraph preguntaP = new Paragraph(pregunta+"\n");
        preguntaP.setAlignment(Paragraph.ALIGN_LEFT);
        pdf.add(preguntaP);
            List lista = new List(true);
        Integer[] preguntasOpciones= (Integer[]) t.getElemento1();
        double siPorciento = (preguntasOpciones[0]*100)/preguntasOpciones[0]+preguntasOpciones[1]+preguntasOpciones[2];
        double noPorciento = (preguntasOpciones[1]*100)/preguntasOpciones[0]+preguntasOpciones[1]+preguntasOpciones[2];
        double enOcacionesPorciento = (preguntasOpciones[2]*100)/preguntasOpciones[0]+preguntasOpciones[1]+preguntasOpciones[2];
        lista.add(new ListItem("Porciento de si: "+siPorciento));
        lista.add(new ListItem("Porciento de si: "+noPorciento));
        lista.add(new ListItem("Porciento de si: "+enOcacionesPorciento));
        pdf.add(lista);
        Paragraph salto = new Paragraph("\n");
        pdf.add(salto);
        }
        Paragraph justificacion = new Paragraph("hay criterios que se concentran en el "+d.getNombreDepartamento()+" relacionados con:\n");
        justificacion.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        pdf.add(justificacion);
        List jus = new List(true);
        for (String justificacione : justificaciones) {
            jus.add(justificacione);
        }
        pdf.add(jus);
    }
}
 