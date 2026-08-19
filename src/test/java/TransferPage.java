import Data.DataHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement header = $("h1");
    private final SelenideElement amountField = $("[data-test-id=amount] .input__control");
    private final SelenideElement fromField = $("[data-test-id=from] .input__control");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private final SelenideElement errorMessage = $("[data-test-id=error-notification] .notification__content");

    public TransferPage() {
        header.should(Condition.visible).should(Condition.text("Пополнение карты"));
    }


    public DashboardPage Transfer (DataHelper.CardInfo fromCard, int amount) {
        amountField.setValue(String.valueOf(amount));
        fromField.setValue(fromCard.getCardNumber());
        transferButton.click();
        return new  DashboardPage();
    }

    public SelenideElement failedTransfer (DataHelper.CardInfo fromCard, int overAmount) {
        amountField.setValue(String.valueOf(overAmount));
        fromField.setValue(fromCard.getCardNumber());
        transferButton.click();
        return errorMessage.should(Condition.visible).should(Condition.text("Ошибка"));
    }
}
