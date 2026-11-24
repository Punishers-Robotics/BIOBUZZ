# Strategy: current plans and season priorities

Use this area for season goals, schedules, resources, sustainability,
competition strategy, and event retrospectives.
Keep technical implementation in [mechanical](../mechanical/README.md) or
[programming](../programming/README.md), and public activities in
[outreach](../outreach/README.md). Record fundraising activities and their results
in [outreach fundraising](../outreach/fundraising/README.md); link those records
here when explaining resource needs and sustainability decisions.

This page describes the **current plan**. Add dated planning decisions and
event lessons beside it using the [activity-record guide](../../templates/activity-record.md).
See the [student guide](../../README.md) for naming and review instructions.

**Student owners:** Not yet recorded  
**Last reviewed:** Not yet recorded

## Season goals and priorities

Draft reported first-MVP priority: focus only on pollen intake and shooting,
selected using the [September 26 design matrix](2026-09-26-first-mvp-design-matrix.md).
The matrix compares Pollen, Pollen + Nectar, and Dual-Launcher, with weighted totals
of 907, 842, and 794 respectively. Its arithmetic is checked; the ratings are
planning judgments, not verified performance measurements.
This is the initial MVP decision, not a confirmed whole-season scope.

## Iterative design plan and reported execution

**Status:** Draft; pending human review  
**Source:** Iteration-plan screenshot supplied in chat November 23, 2026,
attachment identifier
`8a663067-0ee4-446b-acf5-bb4c8db38f5e-fb49d1d3-0f90-4f09-bd41-0ec080b5cabf-clipboard.png`,
and the user's follow-up confirming all V1 items are completed.
No durable source link or original planning document was supplied.
The screenshot was inspected; implementation and performance have not been
independently verified. Plan adoption and V1 completion dates are not yet recorded.

The team's supplied definitions distinguish:

- **Steel thread:** the simplest end-to-end scoring cycle.
- **MVP:** ready for scrimmage and basic competition.
- **Incremental value:** improve the robot based on needed changes toward
  higher-level competition.

These are the team's planning definitions, not official FTC requirements.
The screenshot references "Steel thread & MVP" but does not expose a source URL.

### Ri3D: steel-thread requirements

| System | Requirements shown in the plan |
|---|---|
| Drive | TeleOp; autonomous leave, shoot, and park |
| Intake | Pollen only; fixed height |
| Outtake | Shoot into the hive only; avoid the flower for now |

The [September 12-14 Ri3D record](../mechanical/2026-09-12-robot-in-three-days.md)
reports completion/submission and a robot that moved and shot into the hive to
achieve tips. The newly supplied plan describes intended Ri3D requirements;
it does not independently confirm that every listed requirement was demonstrated.

### V1: MVP requirements and status

The user explicitly reported **all items in the V1 row completed**, including
the field-ball sensing item that the original plan described as "if possible."
This is a completion report, not a measured reliability assessment or proof of
scrimmage/competition participation.

| System | Listed V1 requirement | Reported status |
|---|---|---|
| Drive | TeleOp | Completed |
| Drive | Autonomous operation alone, with an alliance, and with different colors and start locations | Completed |
| Intake | Retractable wedge to empty flowers | Completed |
| Intake | Pollen-only intake with SWYFT wheels | Completed |
| Transfer | Pollen-only transfer, described as "molded silicon" | Completed; material wording needs clarification |
| Transfer | Pollen-only kicker using cut-down SWYFT wheels | Completed |
| Outtake | Pollen-only shooter | Completed |
| Outtake | Turret with adjustable hood | Completed |
| Other | Limelight to identify AprilTags / hive position | Completed |
| Other | Sense balls on the field, originally conditional on feasibility | Completed per follow-up |

The precise sensor/algorithm used for field-ball sensing is not specified; do not
assume it uses Limelight solely because it appears beside that requirement.
The source says "molded silicon"; confirm whether silicone or another material
is intended before using a more specific material claim.

### Current construction and next increment

The user reports that the robot is currently built using wood and that the team
plans to switch to metal after finalizing additional testing. The specific parts
made from wood, planned metal, tests, acceptance thresholds, timeline, and owners
have not yet been supplied. Do not treat the metal transition as completed or
the current prototype as fully validated.

The plan establishes Ri3D and V1 as successive stages. The exact design changes
between them, and how the September 26 matrix informed individual mechanisms,
remain to be documented.

In further intake on November 23, 2026, the user described a system-design
approach of decomposing goals into components and testing/iterating those
components. The [mechanical iteration summary](../mechanical/README.md#system-design-approach-and-component-iterations)
records reported wedge/hood/flower-emptier versions, the powered counter-roller,
drivetrain replacement, and belt adjustments. These add component-development
history, not a dated stage-by-stage reconstruction or independently verified
performance.

## Schedule, resources, and sustainability

Not yet recorded. Record milestones, role-based responsibilities, and approved
resource plans. Link verified fundraising results rather than duplicating them.
Do not store private financial records,
contact details, credentials, or confidential sponsor information here.

## Competition strategy and event lessons

The draft [game-strategy record](game-strategy.md), supplied November 23, 2026,
captures alliance-friendly design lessons reported from Exodus during DECODE
and distinguishes qualification ranking-point priorities from playoff scoring.
Its historical claims and ranking-point rule details have not been independently
verified.

Reported BIOBUZZ priorities are to maximize hive tips, especially in autonomous;
largely ignore flowers except to empty them in autonomous; and leave/park.
The [first-MVP matrix](2026-09-26-first-mvp-design-matrix.md) and iteration plan
above preserve related design choices and reported capabilities. Match results,
reliability targets, and a detailed objective-to-design decision trail remain open.

## Next work and open questions

The reported next step is additional testing before a wood-to-metal transition.
Capture test methods, results, acceptance conditions, design changes, and student
contributions as they become available. Plan/completion dates and material wording
also remain open. No portfolio text or award assessment has been changed.
