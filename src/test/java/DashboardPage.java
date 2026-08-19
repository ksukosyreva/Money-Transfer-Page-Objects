import Data.DataHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement header = $$("[data-test-id=dashboard]").find(exactText("Личный кабинет"));
//            .should(Condition.text("Личный кабинет"));
    private final SelenideElement firstCardButton = $("[data-test-id=92df3f1c-a033-48e6-8390-206f6b1f56c0] .button__text");
    private final SelenideElement secondCardButton = $("[data-test-id=0f3f5c2a-249e-4c3d-8287-09f7a039391d] .button__text");
    private final ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        header.should(Condition.visible).should(Condition.text("Личный кабинет")).getText().trim();
    }

    private SelenideElement getCard(DataHelper.CardInfo cardInfo) {
        return cards.find(Condition.attribute("data-test-id", cardInfo.getTestId()));
    }

    public int getBalance(DataHelper.CardInfo cardInfo) {
       String text = getCard(cardInfo).text();
       return extractBalance(text);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage selectCard(DataHelper.CardInfo cardInfo) {
        getCard(cardInfo).$("button").click();
        return new TransferPage();
    }

//    public TransferPage selectSecondCard() {
//        secondCardButton.click();
//        return new TransferPage();
//    }


}


