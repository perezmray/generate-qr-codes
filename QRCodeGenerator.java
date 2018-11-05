package generate.main;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {
	
	//this is the only input parameter needed, just change the FILE_NAME to what you want
	//private static final String FILE_NAME = "this is a code for qr";
	
	//this controls the location of where the image will be stored (assuming writeToPath is used)
	private static final String QR_CODE_FILE_PATH = ("./test.txt");
	
	//these are the parameters used to set the image width and height
	private static final int _W = 350;
	private static final int _H = 350;
	
	//this method does all of the work by calling the google objects to generate image
	//by using the parameters set above
	private static void generateQRCodeImage(String text, int width, int height, String filepath)
		throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text,  BarcodeFormat.QR_CODE,  width, height);
		
		Path path = FileSystems.getDefault().getPath(filepath);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	}
	
	//this is the main method: calls the other method or calls exceptions
	public static void main(String[] args){
		try {
			//new code starts here:
			FileReader fr = new FileReader(QR_CODE_FILE_PATH);
			BufferedReader br = new BufferedReader(fr);
			
			String str;
			
			while ((str = br.readLine()) != null) {
				
				System.out.println(str);
				String FILE_NAME = str;
				String QR_CODE_IMAGE_PATH = ("./images/" + FILE_NAME + ".png");
				generateQRCodeImage(str, _W, _H, QR_CODE_IMAGE_PATH);
				
			}
			br.close();
			//old code resumes here:
			//generateQRCodeImage(FILE_NAME, _W, _H, QR_CODE_IMAGE_PATH);		
		} catch (WriterException e) {
			System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
		}
	}

}
