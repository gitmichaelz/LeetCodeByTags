package OOD.callCenter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* CallHandler represents the body of the program,
 * and all incoming calls are handled by it first.
 */
public class CallHandler {
    //we have 3 levels of employees: respondent, manager, directory
    private final int LEVELS = 3;
    private final int NUM_RESPONDENT = 10;
    private final int NUM_MANAGERS = 4;
    private final int NUM_DIRECTORS = 2;

    /* List of employees, by level.
     * employeeLevels[0] = respondents
     * employeeLevels[1] = managers
     * employeeLevels[2] = directors
     */
    List<List<Employee>> employeeLevels;

    /* queues for each calls rank*/
    List<Queue<Call>> callQueues;

    public CallHandler() {
        employeeLevels = new ArrayList<>(LEVELS);
        callQueues = new ArrayList<>(LEVELS);
        for(int i = 0; i < LEVELS; i++) {
            callQueues.add(new LinkedList<>());
        }

        //create responders
        List<Employee> respondents = new ArrayList<>(NUM_RESPONDENT);
        for(int i = 0; i < NUM_RESPONDENT; i++) {
            respondents.add(new Respondent(this));
        }
        employeeLevels.add(respondents);

        //create managers
        List<Employee> managers = new ArrayList<>(NUM_MANAGERS);
        for(int i = 0; i < NUM_MANAGERS; i++) {
            managers.add(new Manager(this));
        }
        employeeLevels.add(managers);

        //create directors
        List<Employee> directors =  new ArrayList<>(NUM_DIRECTORS);
        for(int i = 0; i < NUM_DIRECTORS; i++) {
            directors.add(new Director(this));
        }
        employeeLevels.add(directors);
    }

    /* Get the first available employee who can handle this call*/
    public Employee getHandlerForCall(Call call) {
        for(int level = call.getRank().getValue(); level < LEVELS; level++) {
            List<Employee> employeeLevel = employeeLevels.get(level);
            for(Employee e : employeeLevel) {
                if(e.isFree()) {
                    return e;
                }
            }
        }
        return null;
    }

    /* Route the call to an available employee. or saves in a queue if no employee available. */
    public void dispatchCall(Caller caller) {
        Call call = new Call(caller);
        dispatchCall(call);
    }
    /* Route the call to an available employee. or saves in a queue if no employee available. */
    public void dispatchCall(Call call) {
        /* Try to route the call to an employee with minimal rank. */
        Employee emp = getHandlerForCall(call);
        if(emp != null) {
            emp.receiveCall(call);
            call.setHandler(emp);
        } else {
            //place the call into the corresponding call queue according to the rank. */
            call.reply("Please wait for free employee to reply.");
            callQueues.get(call.getRank().getValue()).add(call);
        }
    }

    /* An employee got free. Look for a waiting call that he/she can serve.
     * Return true if we are able to assign a call, false otherwise.
     */
    public boolean assignCall(Employee employee) {
        /* Check the queues.starting from the highest rank this employee can serve. */
        for(int rank = employee.getRank().getValue(); rank >= 0; rank--) {
            Queue<Call> curQueue = callQueues.get(rank);
            if(!curQueue.isEmpty()) {
                Call call = curQueue.poll();
                if(call != null) {
                    employee.receiveCall(call);
                    return true;
                }
            }
        }
        return false;
    }


}
