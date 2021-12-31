package OOD.elevatorSystem;
/*
 * class (Passenger, Elevator car, Floors, Doors, Button Panels(elevator buttons, HallButton), Dispatcher/Scheduler(select the most
 *  appropriate elevator car to the passenger when the passenger calls a car), The whole elevator system (which is singleton), Monitoring Object(System),
 * )
 *
 *
 * 1. Passenger (实际上并不需要这个class, 因为我们是一个电梯控制系统，不是电梯模拟系统，这也是我们在Parking lot系统设计里不需要Vehicle class的原因)
 * 2. Elevator Car{Four state: 1> idle, when its idle, which floor does the car stay at.
 *                             2> moving same direction towards passenger & direction passenger wants to go
 *                             3> moving same direction towards passenger but opposite direction passenger wants to go
 *                             4> Elevator car going away from the passenger
 *
 *    transfer passenger from one floor to another
 *    open door only when it's idle at a floor
 *
 *    three specs of elevator car: number of passenger, max load, max speed can move up or down
 *    elevatorMotion:{ move(DestinationFloor), stop()}
 *    discuss:
 *          minimize the wait time of a individual passenger/minimize the wait time of the whole system?
 *          improve throughput? Saving power usage?
 *
 *
 * 3. Floors, Zones(if we have 200 floors, we'd better have different operational zones. zones are set of floors, we need to divide our
 *      elevator cars into different grouping, which are responsible for different zones?) for one operational zone, how many elevator cars do we need?
 *
 * 4. Door{open(), close(), isOpen()}
 * 5. Button Panels interface { pressDown(), isPressed() }
 *                => two children classes implement it: (ElevatorButton,  HallButton), why we need two types of buttons,
 *          implementation of pressDown method is totally different. (HallButton is only for up and down, elevator button can indicate which floor you are going to)
 *
 * 6. Elevator motion interface { move(destinationFloor), stop() }
 *
 * 6. Dispatcher{Dispatch/Scheduling Algorithm:}
 *           => 1. First Come First Serve, all the request go to dispatcher and dispatcher adds the request into a queue, and get the nearest elevator, need to consider the four state of the elevator, and in this strategy,
 *                 the dispatcher will ignore the 3> and 4> states, and find the nearest one from cars in state 1> and 2>;
 *                 the flaws of this algorithm: it will first completely serve the first passenger and then it will go to the second one.(The correct approach is car should pick up any passengers in same direction)
 *           => 2. Shortest Seek Time First, it will ensure minimum movement of the elevator compared to the FCFS algorithm. two approach to implement:
 *                 i>  priorityQueue: enqueue the request based on the distance of the requesting floor from the elevator floor, and pick the topmost request from pq. But
 *                     this minHeap will change, as position of the elevator changes, it also changes.
 *                 ii> array: with length of the number of floors. From certain index of array, it will scan which is the nearest request.
 *                the flaws of the SSTF strategy. there is a possiblity of starvation. for example: most passengers are in floor 5, while there is one passenger is far from
 *                that floor, say 15,  the elevator will keep serving most passengers in the 5th floor. And similar to FCFS, this strategy also doesnot support serving multiple
 *                requests in parallel.
 *           => 3. Scan. Also called Elevator Algorithm as well. the approach is:
 *                 two boolean array. one for down direction, the other for up direction. When an elevator car is moving, at every floor, it will check if the entry is set or not. If true, it will stop and open
 *                 the door and mark the entry here as false.
 *           => 4. Look
 * 7. Elevator System (Singleton)
 * 8. Emergency alarm/brake
 * 8. Monitoring System(monitor the status of elevator cars, the doors, button panels, etc)
 */

/*
 * Use cases:
 * 1. Calling the elevator(passenger wants to go from one floor to another, he will push a button which will actually go to the dispatcher/scheduler,
 *  the dispatcher then  will figure out which elevator car is the most appropriate car it should schedule to send to that floor.)
 *
 * 2. Move/stop elevator
 *
 * 3. Open/close elevator doors
 *
 * 4. Indicating direction(when the elevator car is going up and down, we need to show its direction inside the elevator as well as show it outside
 *  whenever it comes on the floor we notify the passengers there)
 *
 * 5. Indicating elevator position/Floor (inside the elevator, there is a panel, it shows where the elevator is present right now, it actually indicates
 *  the passengers if they need to hop off from the elevator at that floor, say this is your destination floor now.)
 *
 * 6. Triggering Emergency breaks
 *
 * 7. Making Emergency calls
 */
public class ElevatorSystem {
}
