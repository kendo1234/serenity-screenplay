package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import screenplay.GoogleSearchResults;
import screenplay.SearchForKeyword;
import screenplay.StartWith;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.containsString;



@RunWith(SerenityRunner.class)
public class GoogleSearchLiveTest {

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();

    }

    private Actor kitty = Actor.named("kitty");

    @Before
    public void setup() {
        kitty.can(BrowseTheWeb.with(driver));
    }

    @Test
    public void whenGoogleBaeldungThenShouldSeeEugen() {
        givenThat(kitty).wasAbleTo(StartWith.googleSearchPage());

        when(kitty).attemptsTo(SearchForKeyword.of("baeldung"));

        then(kitty).should(seeThat(GoogleSearchResults.displayed(), hasItem(containsString("Eugen (Baeldung)"))));
    }


}