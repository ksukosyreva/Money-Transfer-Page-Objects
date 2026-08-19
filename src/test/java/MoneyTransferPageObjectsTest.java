import Data.DataHelper;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferPageObjectsTest {
    @Test
    void shouldSuccessTransferMoneyFromSecondOwnCardToFirst() {
        var info = DataHelper.getAuthInfo();
        var verification = DataHelper.code();
        var firstCardInfo = DataHelper.getFirtCardInfo();
        var secondCardInfo = DataHelper.getSecondCardInfo();

        var loginPage = Selenide.open("http://localhost:9999/", LoginPage.class);
        var verificationPage = loginPage.validLogin(info);
        var dashboardPage = verificationPage.validVerification(verification);
        int firstCardBalance = dashboardPage.getBalance(firstCardInfo);
        int secondCardBalance = dashboardPage.getBalance(secondCardInfo);
        int transferAmount = DataHelper.calculateTransferAmount(secondCardBalance);
        var transferPage = dashboardPage.selectCard(firstCardInfo);
        var dashboardPageAfter = transferPage.Transfer(secondCardInfo, transferAmount);
        int firstCardBalanceAfter = dashboardPageAfter.getBalance(firstCardInfo);
        int secondCardBalanceAfter = dashboardPageAfter.getBalance(secondCardInfo);

        assertEquals(secondCardBalance - transferAmount, secondCardBalanceAfter);
        assertEquals(firstCardBalance + transferAmount, firstCardBalanceAfter);
    }

    @Test
    void shouldSuccessTransferMoneyFromFirstOwnCardToSecond() {
        var info = DataHelper.getAuthInfo();
        var verification = DataHelper.code();
        var firstCardInfo = DataHelper.getFirtCardInfo();
        var secondCardInfo = DataHelper.getSecondCardInfo();

        var loginPage = Selenide.open("http://localhost:9999/", LoginPage.class);
        var verificationPage = loginPage.validLogin(info);
        var dashboardPage = verificationPage.validVerification(verification);
        int firstCardBalance = dashboardPage.getBalance(firstCardInfo);
        int secondCardBalance = dashboardPage.getBalance(secondCardInfo);
        int transferAmount = DataHelper.calculateTransferAmount(secondCardBalance);
        var transferPage = dashboardPage.selectCard(secondCardInfo);
        var dashboardPageAfter = transferPage.Transfer(firstCardInfo, transferAmount);
        int firstCardBalanceAfter = dashboardPage.getBalance(firstCardInfo);
        int secondCardBalanceAfter = dashboardPage.getBalance(secondCardInfo);

        assertEquals(secondCardBalance + transferAmount, secondCardBalanceAfter);
        assertEquals(firstCardBalance - transferAmount, firstCardBalanceAfter);
    }

    @Test
    void shouldBackErrorIfTransferAmountIsMoreThanBalanceOnSecondCardToFirst () {
        var info = DataHelper.getAuthInfo();
        var verification = DataHelper.code();
        var firstCardInfo = DataHelper.getFirtCardInfo();
        var secondCardInfo = DataHelper.getSecondCardInfo();

        var loginPage = Selenide.open("http://localhost:9999/", LoginPage.class);
        var verificationPage = loginPage.validLogin(info);
        var dashboardPage = verificationPage.validVerification(verification);
        int firstCardBalance = dashboardPage.getBalance(firstCardInfo);
        int secondCardBalance = dashboardPage.getBalance(secondCardInfo);
        int amountTransferOver = DataHelper.calculateTransferOverBalanceAmount(secondCardBalance);
        var transferPage = dashboardPage.selectCard(secondCardInfo);
        var transferPageAfter = transferPage.failedTransfer(secondCardInfo, amountTransferOver);

    }

}
