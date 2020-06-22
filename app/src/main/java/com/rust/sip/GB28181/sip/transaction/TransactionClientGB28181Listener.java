package com.rust.sip.GB28181.sip.transaction;


import com.rust.sip.GB28181.sip.message.Message;

public interface TransactionClientGB28181Listener {


    public void onTransGB28181ProvisionalResponse(MESSAGETransactionClient tc, Message resp);

    /**
     * When the TransactionClient goes into the "Completed" state receiving a
     * 2xx response
     */
    public void onTransGB28181SuccessResponse(MESSAGETransactionClient tc, Message resp);

    /**
     * When the TransactionClient goes into the "Completed" state receiving a
     * 300-699 response
     */
    public void onTransGB28181FailureResponse(MESSAGETransactionClient tc, Message resp);

    /**
     * When the TransactionClient goes into the "Terminated" state, caused by
     * transaction timeout
     */
    public void onTransGB28181Timeout(MESSAGETransactionClient tc);


}
