# Mechanical: current robot details

Use this area for mechanisms, CAD, materials, wiring, hardware integration,
design decisions, and physical tests. Put code and control behavior in
[programming](../programming/README.md) and link related records.

This page describes the **current design**. Add dated design and test notes
beside it using the [activity-record guide](../../templates/activity-record.md).
See the [student guide](../../README.md) for naming and review instructions.

**Student owners:** Not yet recorded  
**Last reviewed:** Not yet recorded

## Current design and goals

The team's [game strategy](../strategy/game-strategy.md#biobuzz-priorities)
prioritizes hive tips, especially in autonomous, while largely setting aside
flowers except for autonomous emptying. This is the reported strategic context
for the design, not evidence of achieved match performance.

The reported first-MVP goal was pollen intake and shooting, selected through the
[September 26 strategy/design matrix](../strategy/2026-09-26-first-mvp-design-matrix.md).
The [iteration plan and execution summary](../strategy/README.md#iterative-design-plan-and-reported-execution)
now records the user's November 23, 2026 report that all listed V1 MVP requirements
are completed. This is draft reported status; actual performance has not been
independently verified.

## Mechanism and hardware details

The reported V1 includes a retractable flower-emptying wedge, pollen-only SWYFT
wheel intake, transfer and kicker, pollen-only shooter, and a turret with adjustable
hood. See the [canonical V1 requirements table](../strategy/README.md#v1-mvp-requirements-and-status)
for component details and the unresolved "molded silicon" material description.

The user reports the robot is currently built using wood, with a transition to
metal planned after additional testing. Wood plates are identified in the
component-iteration report below; a full material inventory, dimensions, CAD,
specific metal, and construction tradeoffs have not yet been recorded.

Additional component details and reported changes are captured below, including
the powered counter-roller, drivetrain replacement, and belt-spacing adjustments.

## System-design approach and component iterations

**Status:** Draft; pending human review  
**Source:** User-supplied description in chat November 23, 2026; no durable source
link supplied.  
**Evidence status:** Reported design work and qualitative observations; no CAD,
test logs, measurements, or videos were inspected for these claims.  
**Iteration dates and student contributors:** Not yet recorded

The team reports following system-design principles: discuss the desired outcome,
break it into smaller components, and aim to test and iterate component by
component toward the desired overall design. The examples below describe work
reported as performed after choosing the approach. They do not establish that
every component has completed validation.

| Component | Reported iterations or change | Purpose and reported result |
|---|---|---|
| Intake wedges | Printed approximately 5-7 different pairs | Mechanically guide balls into a desired triangle pattern for consistent transfer. Quantified transfer consistency has not been supplied. |
| Turret hoods and counter-roller | Printed 5+ hoods, adjusting thickness and angles to reduce backspin; ultimately selected a powered counter-roller | The user reports that the counter-roller removed backspin completely and spins twice as fast as the flywheel. Measurement methods and operating conditions have not been supplied. |
| Drivetrain | Started with bevel gears; replaced that design with a more familiar drivetrain used previously | The original drivetrain was reported as inconsistent; the replacement reportedly greatly improved driving performance. The source does not identify a specific failure mode or numerical comparison. |
| Belts and plate spacing | Adjusted belt distances after the first wood plates were cut because the belts were too loose | The user reports that everything ran more smoothly after the adjustments. Exact spacing changes, tension checks, and affected mechanisms have not been supplied. |
| Flower emptier | Printed 5+ versions, varying thickness and angle | Intended to maximize efficient emptying and intake. Specific timing, success rates, and the final geometry have not been supplied. |

Version counts remain approximate as reported. Intake-wedge counts refer to
**pairs**, not individual pieces. Keep the intake wedges and flower emptier
iterations distinct; the source does not identify them as the same component.

The counter-roller speed relationship is reported as twice the flywheel speed.
The source does not specify whether this is a commanded speed, a gearing-based
ratio, or measured rotational speed. Roller diameter, rotation direction, and
measurement details have not been supplied; do not infer surface-speed ratios
or a validated zero-backspin result under all conditions.

These updates establish that wood plates were used in the prototype, but do not
identify every part made from wood. The described changes are not yet assigned
to specific Ri3D/V1 dates. In particular, they do not resolve which flower-emptying
version appeared in the [reported Ri3D video mention](2026-09-12-robot-in-three-days.md#supporting-evidence-and-follow-up).

## Tests, decisions, and evidence

Draft reported milestone: [September 12-14 Ri3D completion and submission](2026-09-12-robot-in-three-days.md).
The reported robot moved and shot balls into the hive to achieve tips.
The [iteration plan](../strategy/README.md#iterative-design-plan-and-reported-execution)
identifies Ri3D as the steel-thread stage preceding V1. The component summary
above adds reported design changes, but their stage-by-stage chronology and
performance measurements remain unrecorded. The historical Ri3D milestone does
not establish the current configuration or reliability.

## Next work and open questions

Additional testing before moving from wood to metal is planned. Test methods,
acceptance thresholds, results, transition timing, and owners are not yet recorded.
Useful supporting evidence includes version comparisons, CAD or photos,
before/after transfer and driving tests, and counter-roller/backspin observations
with operating conditions. Preserve failed approaches and what motivated each
change rather than only documenting the final component.
