package screenplay;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

@DefaultUrl("https://www.google.com/ncr")
public class GoogleSearchPage extends PageObject {

    @FindBy(name = "q")
    private WebElement search;

    @FindBy(css = "._ksh")
    private WebElement result;

    public static final Target SEARCH_RESULT_TITLES = Target
            .the("search results")
            .locatedBy("._ksh");

    public static final Target SEARCH_INPUT_BOX = Target
            .the("search input box")
            .locatedBy("#lst-ib");

    public void searchFor(String keyword) {
        search.sendKeys(keyword, Keys.ENTER);
    }

    public void resultMatches(String expected) {
        withTimeoutOf(5, SECONDS)
                .waitFor(result)
                .waitUntilVisible();
        assertThat(result.getText(), containsString(expected));
    }


}