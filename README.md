# State Machine

A prototype project to handle state changes in pure java, using JDK 23 pattern matching and sealed classes

The following parts are defined:

* A **State** represent a node in the state machine graph
* An **Event** represent a trigger from a **State** to another (or to itself potentially); it is a sealed interface that
  defines a hierarchy between admin and user events. Their distinction lets define a different guarding and logging
  strategy potentially at each level of the hierarchy.
* The State transitions are bounded to the definition of the specific event; these are implemented with records that
  check for the initial state to be legal in the compact constructor and defined a custom constructor where the
  destination State is coded in the class.
* The StateMachineService is the heart of the behavior, where custom services can be injected in the event (if defined
  as Event behavior), see. JwtService for implementing RBAC and EventLogService to audit State change in a custom
  repository.