package javaPart;

import java.io.File;
import java.io.IOException;

import cplusplusElements.Project_image;

public class ProcessAllLayers {
	GUIMAIN guiman;
	
	public ProcessAllLayers(GUIMAIN guiman) {
		this.guiman = guiman;
	}
	
	public void processAll() {
		for(int i = 0; i < guiman.project.getLayers().getElem().size(); i++) {
			
		     String tmpPath = "tmpProject\\layer" + i + ".bmp";
		     File photo = new File(tmpPath);
		     File exef = new File("testiranjeZaSutrasnjiLab\\Debug\\testiranjeZaSutrasnjiLab.exe");
		     File mFormat = new File("preOperation.toza");
		     String absPhoto = photo.getAbsolutePath();
		     String absmFormat = mFormat.getAbsolutePath();
		     String absExe = exef.getAbsolutePath();
			 String file = absExe + " " + absmFormat + " " + absPhoto;
			 
			 Project_image tmp = new Project_image();
			 tmp.addLayer(new File(tmpPath).getAbsolutePath());
			 tmp.setComposite(guiman.project.getComposite());
			 tmp.writePreOperate();
			 
		     Runtime runtime = Runtime.getRuntime();
				
				try {
					Process process = runtime.exec(file);
					process.waitFor();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
	    		 
		}
		}
	}


