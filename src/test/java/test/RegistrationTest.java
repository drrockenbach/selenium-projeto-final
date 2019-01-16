package test;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;

import page.RegistrationPage;


public class RegistrationTest {

    private WebDriver driver;
    
    private RegistrationPage page;
    
    @Before
    public void preCondicao() {
        
        System.setProperty("webdriver.chrome.driver", "C:\\dev\\Selenium\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://demoqa.com/");
        
        page = new RegistrationPage(driver);
        page.clicaEmRegistrationLink();
    }
    
    @Test
    public void deveCriarUmRegistro() {
        
        
        Faker faker = new Faker(new Locale("pt_BR"));
        page.preencheNome(faker.name().firstName(), faker.name().lastName());
        
        page.preencheEstadoCivilSolteiro();
        page.escolheDanca();
        
        page.escolhePais("Brazil");
        
        page.preencheDataNascimento("29", "6", "1986");
        
        page.preencheDadosDeContato("313484", "teste", "teste@gmail.com");
        
        page.digitaSenhaEConfirmacao("senha123", "senha123");
        
        page.enviaFormulario();
        
        Assert.assertTrue(page.mensagemSucessoRegistroApareceu());
        Assert.assertTrue(page.mensagemSucessoEstaCorreta());
        
    }
    
    @Test
    public void deveAvisarObrigatoriedadeDeSobrenome() {
        
        RegistrationPage page = new RegistrationPage(driver);
        page.clicaEmRegistrationLink();
        page.preencheNome("Maria", "");
        page.preencheEstadoCivilSolteiro();
        
        Assert.assertTrue(page.mesagemObrigatoriedadeNomeApareceu());
        
    }
    
    @Test
    public void deveValidarQuantidadeMinimaDeDigitosDeTelefone() {
        RegistrationPage page = new RegistrationPage(driver);
        
        page.clicaEmRegistrationLink();
        page.preencheDadosDeContato("313484", "teste", "teste@gmail.com");
        
        Assert.assertTrue(page.mensagemQtdMinimaTelefoneApareceu());
    }
    
}
