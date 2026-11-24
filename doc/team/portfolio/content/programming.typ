#import "../template.typ": comparison, evidence-card

== Control objective
Describe a real autonomous or driver-control challenge.
Explain the desired behavior and how the team will measure success.

#evidence-card("Control sequence")[
  *Observe:* identify sensor readings or robot state.

  *Decide:* explain the condition that selects the next action.

  *Act:* describe the mechanism or drive command and its stopping condition.
]

#comparison[
  === Implementation
  Link the relevant source file and revision.
  Explain the students' design choices, including how existing libraries
  are used. Distinguish team-written code from library capabilities.
][
  === Verification
  Describe the test setup, starting conditions, and repeated trials.
  Record failures as well as successes, including recovery behavior.
]

== Evidence and improvement
Add a telemetry plot, annotated code excerpt, or path diagram.
State what changed between tests and whether the change improved performance.

#evidence-card("Results to supply")[
  Record the metric, units, trial count, date, and evidence location.
  Identify any remaining limitation rather than claiming untested reliability.
]
