/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;
import fitbox.controller.RealizarActividadController;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Lluis
 */
public class ObjetoWebCam implements Runnable{
    
    private boolean finalizado = false;
    private String nombreVideo = "";
    JFrame window;
    private String ruta=""; 

    public String getRuta() {
        return ruta;
    }
    
    public ObjetoWebCam(String nombre){
        this.nombreVideo = nombre;
    }
    
    public void run(){
    window = new JFrame("Test Webcam Panel");
                window.setUndecorated(true);
                Webcam webcam = Webcam.getDefault();
                WebcamPanel panel = new WebcamPanel(webcam);
                
                window.add(panel);   
                window.setLocation(800,475);
                window.pack();
                window.setVisible(true); 
                
                // Grabar en video
                
                File archivo = new File(nombreVideo+".wmv");
                ruta = archivo.getAbsolutePath();
                IMediaWriter writer = ToolFactory.makeWriter(archivo.getName());
                Dimension size = webcam.getViewSize();
                
                writer.addVideoStream(0, 0,ICodec.ID.CODEC_ID_WMV2,size.width, size.height);
                long start = System.currentTimeMillis();
                
                
                for(int i =0; finalizado == false ;i++) {
                        System.out.println("Capture frame " + i);

                        BufferedImage image = ConverterFactory.convertToType(webcam.getImage(), BufferedImage.TYPE_3BYTE_BGR);
                        IConverter converter = ConverterFactory.createConverter(image, IPixelFormat.Type.YUV420P);

                        IVideoPicture frame = converter.toPicture(image, (System.currentTimeMillis() - start) * 1000);
                        frame.setKeyFrame(i == 0);
                        frame.setQuality(0);

                        writer.encodeVideo(0, frame);
                    try {
                        // 20 FPS
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RealizarActividadController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                writer.close();
}
    @Override
    public void finalize(){
       if(!finalizado){
            this.finalizado=true;
            this.window.dispose();
        }
    }
}

