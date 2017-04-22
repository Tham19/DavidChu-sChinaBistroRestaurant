package com.github.thamvannguyen.core.util;

import java.util.Collection;

/**
 * Created by huynhduychuong on 10/31/2016.
 */
public class OtpProvider implements OtpSource {
    private static final int PIN_LENGTH = 6; // HOTP or TOTP
    private static final int REFLECTIVE_PIN_LENGTH = 9; // ROTP

    @Override
    public int enumerateAccounts(Collection<String> result) {
        return 0;
    }

    @Override
    public String getNextCode(String accountName) throws OtpSourceException {
//            return getCurrentCode(accountName, null);
        return "";
    }

    // This variant is used when an additional challenge, such as URL or
    // transaction details, are included in the OTP request.
    // The additional string is appended to standard HOTP/TOTP state before
    // applying the MAC function.
    @Override
    public String respondToChallenge(String accountName, String challenge) throws OtpSourceException {
//            if (challenge == null) {
//                return getCurrentCode(accountName, null);
//            }
//            try {
//                byte[] challengeBytes = challenge.getBytes("UTF-8");
//                return getCurrentCode(accountName, challengeBytes);
//            } catch (UnsupportedEncodingException e) {
//                return "";
//            }
        return "";
    }


//
//        private String getCurrentCode(String username, byte[] challenge) throws OtpSourceException {
//            // Account name is required.
//            if (username == null) {
//                throw new OtpSourceException("No account name");
//            }
//
//            OtpType type = mAccountDb.getType(username);
//            String secret = getSecret(username);
//
//            long otp_state = 0;
//
//            if (type == OtpType.TOTP) {
//                // For time-based OTP, the state is derived from clock.
//                otp_state =
//                        mTotpCounter.getValueAtTime(Utilities.millisToSeconds(mTotpClock.currentTimeMillis()));
//            } else if (type == OtpType.HOTP){
//                // For counter-based OTP, the state is obtained by incrementing stored counter.
//                mAccountDb.incrementCounter(username);
//                Integer counter = mAccountDb.getCounter(username);
//                otp_state = counter.longValue();
//            }
//
//            return computePin(secret, otp_state, challenge);
//        }
//
//        public OtpProvider(AccountDb accountDb, TotpClock totpClock) {
//            this(DEFAULT_INTERVAL, accountDb, totpClock);
//        }
//
//        public OtpProvider(int interval, AccountDb accountDb, TotpClock totpClock) {
//            mAccountDb = accountDb;
//            mTotpCounter = new TotpCounter(interval);
//            mTotpClock = totpClock;
//        }

    /**
     * Computes the one-time PIN given the secret key.
     *
     * @param secret the secret key
     * @param otp_state current token state (counter or time-interval)
     * @param challenge optional challenge bytes to include when computing passcode.
     * @return the PIN
     */
//        private String computePin(String secret, long otp_state, byte[] challenge)
//                throws OtpSourceException {
//            if (secret == null || secret.length() == 0) {
//                throw new OtpSourceException("Null or empty secret");
//            }
//
//            try {
//                PasscodeGenerator.Signer signer = AccountDb.getSigningOracle(secret);
//                PasscodeGenerator pcg = new PasscodeGenerator(signer,
//                        (challenge == null) ? PIN_LENGTH : REFLECTIVE_PIN_LENGTH);
//
//                return (challenge == null) ?
//                        pcg.generateResponseCode(otp_state) :
//                        pcg.generateResponseCode(otp_state, challenge);
//            } catch (GeneralSecurityException e) {
//                throw new OtpSourceException("Crypto failure", e);
//            }
//        }

    /**
     * Reads the secret key that was saved on the phone.
     * @param user Account name identifying the user.
     * @return the secret key as base32 encoded string.
     */
//        String getSecret(String user) {
//            return mAccountDb.getSecret(user);
//        }

    /**
     * Default passcode timeout period (in seconds)
     */
    public static final int DEFAULT_INTERVAL = 30;

}
