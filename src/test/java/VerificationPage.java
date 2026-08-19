import Data.DataHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement verificationField = $("[data-test-id=code] input");
    private final SelenideElement verificationButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        verificationField.should(Condition.visible);
    }

    public DashboardPage validVerification(DataHelper.VerificationCode verificationCode) {
        verificationField.setValue(verificationCode.getCode());
        verificationButton.click();
        return new DashboardPage();
    }
}
