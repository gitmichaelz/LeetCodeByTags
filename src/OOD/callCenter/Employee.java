package OOD.callCenter;
/* Super class for Director, Manager, Respondent. */
public abstract class Employee {
    private Call currentCall = null;
    Rank rank;
    private CallHandler callHandler;

    public Employee(CallHandler handler) {
        callHandler = handler;
    }

    /* Start the conversation */
    public void receiveCall(Call call) {
        currentCall = call;
    }

    /* The issue is resolved. finish the call. */
    public void finishCall() {
        if(currentCall != null) {
            //disconnect the call
            currentCall.disconnect();
            //free the employee
            currentCall = null;
        }
        /* check if there is a call waiting in queue */
        assignNewCall();
    }

    /* The issue has not been resolved. Escalate the call, and assign a new call to the employee. */
    public void escalateAndReassign() {
        if(currentCall != null) {
            //escalate call
            currentCall.incrementRank();
            callHandler.dispatchCall(currentCall);

            //free the employee
            currentCall = null;
        }
        assignNewCall();
    }

    /* Assign a new call to an employee, if the employee is free*/
    public boolean assignNewCall() {
        if(!isFree()) {
            return false;
        }
        return callHandler.assignCall(this);
    }

    public boolean isFree() {
        return currentCall == null;
    }

    public Rank getRank() {
        return rank;
    }


}
