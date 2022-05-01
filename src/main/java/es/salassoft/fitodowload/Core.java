 package es.salassoft.fitodowload;
 
 import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import es.salassoft.fitodowload.guitools.MainLogger;
import es.salassoft.fitodowload.httptools.FileDown;
import es.salassoft.fitodowload.model.ProductoFito;
 
 
 
 
 
 public class Core
 {
   private static final String excelProductos = "https://www.mapa.gob.es/es/agricultura/temas/sanidad-vegetal/productos-fitosanitarios/registro/productos/ListadoProductos.asp";
   
   private static final String pfdUtlBase= "https://www.mapa.gob.es/agricultura/pags/fitos/registro/productos/pdf/";
   private String directorioDescargas = "c:\\fito";
   
 
   public void ejecutarDescarga()
   {
		   File dirDescargaDirecta = new File(directorioDescargas);
		   if(!dirDescargaDirecta.exists()) {
			   dirDescargaDirecta.mkdir();  //Lo creo
		   }
 
        	  String today = new SimpleDateFormat("dd-mm-yyyy").format(new Date());
             String fileUrlProductos =  directorioDescargas+"\\ListadoProductos_"+today+".xls";
             FileDown.download(excelProductos,fileUrlProductos);
 
             MainLogger.logTrace("Se ha descargado el excel de procutos en la ruta:"+ fileUrlProductos);
             try {
				Document doc = Jsoup.parse(new File(fileUrlProductos),"ISO-8859-1");
				Elements elements = doc.body().getElementsByTag("table");
						
				List<ProductoFito> productos = new ArrayList<ProductoFito>();
				for (Element el: elements) {
					  el.tagName();
					  Elements trs = el.getElementsByTag("tr");
					   MainLogger.logTrace("Se han encontrado  "+ (trs.size()-1)+ " productos para descargar");
					   MainLogger.logTrace("Se procede a la descarga en la ruta "+directorioDescargas+"\\pdfs\\");
					   //Verifico si existe directorio : 
					   File pdfDir = new File(directorioDescargas+"\\pdfs\\");
					   if(!pdfDir.exists()) {
						   pdfDir.mkdir();  //Lo creo
					   }
					   
					  for (Element tr: trs) {
						  Elements ths = tr.children();
						  MainLogger.logTrace("--------------------------------------------");
						  if(!"Nº Registro".equals((ths.get(0).text()))){

							  ProductoFito producto = new ProductoFito();
							  producto.setnRegistro(ths.get(0).text());
							  producto.setNombreComercial(ths.get(1).text());
							  producto.setTitular(ths.get(2).text());
							  producto.setFormulado(ths.get(3).text());
								  
							  producto.setNombreComercial(producto.getNombreComercial().replace("/", " "));
							  producto.setNombreComercial(producto.getNombreComercial().replace("\\", " "));
							  producto.setNombreComercial(producto.getNombreComercial().replace("*", " "));

							  MainLogger.logTrace("++ Descarando "+producto.getnRegistro()+"_"+producto.getNombreComercial()+".pdf");
							  FileDown.download(pfdUtlBase+producto.getnRegistro()+".pdf",directorioDescargas+"\\pdfs\\"+producto.getnRegistro()+"_"+producto.getNombreComercial()+".pdf");
							  productos.add(producto);
						  }
						 
					  }
				  }
				 MainLogger.logTrace("--------------------------------------------");
				 MainLogger.logTrace("FIN Descarga");
				 System.out.println("FIN Descarga");
			} catch (IOException e) {
				 MainLogger.logTrace("ERROR AL LEER EL EXCEL DE FITOS "+e);
			}

 
         
 

      
   }
   
   private void microPausa() {
     try {
       Thread.sleep(50L);
     }
     catch (Exception localException) {}
   }
   
 
 
 
 
   public static void main(String[] args)
   {
     Core m = new Core();

     m.ejecutarDescarga();
   }
   
 
 
 
   public String getDirectorioDescargas()
   {
     return this.directorioDescargas;
   }
   
   public void setDirectorioDescargas(String directorioDescargas) {
     this.directorioDescargas = directorioDescargas;
   }
 }


/* Location:              C:\Users\salas\Downloads\fitoDowload\fitoDowload\fitodowloader.jar!\es\salassoft\fitodowload\Core.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */