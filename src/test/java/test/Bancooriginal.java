package test;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Bancooriginal {
	WebDriver Tester;


	@Before
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		Tester = new ChromeDriver();
		// indicar URL
		Tester.get("https://www.4devs.com.br/gerador_de_pessoas");
		// indicar driver segundo
		JavascriptExecutor js = (JavascriptExecutor) Tester;
		// indicar para rolar a tela
		js.executeScript("window.scrollBy(0,1000)");
		// pausa sugerida
		Thread.sleep(3000);

		Tester.findElement(By.xpath("//*[@id='bt_gerar_pessoa']")).click();
		Thread.sleep(6000);
		String cpf = Tester.findElement(By.id("cpf")).getText();
		String email = Tester.findElement(By.id("email")).getText();
		String nome = Tester.findElement(By.id("nome")).getText();
		String celular = Tester.findElement(By.id("celular")).getText();
		
		Tester.quit();

		Tester = new ChromeDriver();

		Tester.get("https://nubank.com.br/");
		Thread.sleep(3000);
		Tester.findElement(By.id("field-cpf")).sendKeys(cpf);
		Tester.findElement(By.id("test")).click();
		Thread.sleep(3000);
		Tester.findElement(By.id("field-name")).sendKeys(nome);
		Tester.findElement(By.id("field-phone")).sendKeys(celular);
		Tester.findElement(By.id("field-email")).sendKeys(email);
		Tester.findElement(By.id("field-emailConfirmation")).sendKeys(email);
		Tester.findElement(By.cssSelector("#label-accepted > span.sc-bYWUiG.gaLLqc > svg")).click();
		Tester.findElement(By.cssSelector("#complete-form-drawer > div > div > div.sc-kxtUkE.jKEmYc > form > div >"
				+ " div.sc-dsKijY.dTZaxR > div > button")).click();
		Robot robo = Robot();
		
		robo.setAutoDelay(1000);
		  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		  Rectangle retanguloprint = new Rectangle(
				  (int)screenSize.getWidth(),
				  (int)screenSize.getHeight()
				  );
		  BufferedImage imageBuffer = robo.createScreenCapture(retanguloprint);
		  
		  File arquivoImg = new File("C:\\Users\\Pichau\\eclipse-workspacenovoprojeto\\.validaçãoPRT/tela.png");
		
		  try {
			  ImageIO.write(imageBuffer, "png", arquivoImg);
			  if(arquivoImg.exists()) {
				  
				  System.out.println(arquivoImg.getAbsolutePath());
			  }
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	
		}

	
     
	 private Robot Robot() {
		// TODO Auto-generated method stub
		return null;
	}



	@After
	public void tearDown() throws Exception {
		String info = Tester.findElement(By.cssSelector(
				"#complete-form-drawer > div > div > div.sc-kxtUkE.jKEmYc > form > div > div.sc-dsKijY.gNRsVw > div.sc-dsKijY.yLhaF >"
						+ " div:nth-child(1) > h2"))
				.getText();
		System.out.println(info);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
