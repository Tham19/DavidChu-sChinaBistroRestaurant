package com.github.thamvannguyen.core.util;

import java.util.Collection;

/**
 * Created by huynhduychuong on 10/31/2016.
 */
public interface OtpSource {
    int enumerateAccounts(Collection<String> result);

    /**
     * Return the next OTP code for specified username.
     * Invoking this function may change internal state of the OTP generator,
     * for example advancing the counter.
     *
     * @param accountName Username, email address or other unique identifier for the account.
     * @return OTP as string code.
     */
    String getNextCode(String accountName) throws OtpSourceException;

    /**
     * Generate response to a given challenge based on next OTP code.
     * Subclasses are not required to implement this method.
     *
     * @param accountName Username, email address or other unique identifier for the account.
     * @param challenge   Server specified challenge as UTF8 string.
     * @return Response to the challenge.
     * @throws UnsupportedOperationException if the token does not support
     *                                       challenge-response extension for this account.
     */
    String respondToChallenge(String accountName, String challenge) throws OtpSourceException;


}
