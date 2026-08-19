package Data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static VerificationCode code() {
        return new VerificationCode("12345");
    }

    public static CardInfo getFirtCardInfo() {
        return new CardInfo("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    public static int calculateTransferAmount(int balance) {
        int amount = balance / 10;
        return amount;
    }

    public static int calculateTransferOverBalanceAmount (int balance) {
        int amount = balance + 100;
        return amount;
    }

    @Value
    public static class AuthInfo {
        String login;
        String  password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String testId;
    }

}
