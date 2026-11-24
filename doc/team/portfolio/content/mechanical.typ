#import "../template.typ": brand

// Story MECH-01. Think: manual 6.3.2, Table 6-3, THINK-1A/B/C/D.
// Sources: records/strategy/game-strategy.md; records/strategy/README.md;
// records/strategy/2026-09-26-first-mvp-design-matrix.md;
// records/mechanical/README.md#system-design-approach-and-component-iterations.
// Paths above are relative to doc/team. Outcomes remain team-reported.

== Compare choices: focus the first MVP
Our strategy prioritizes hive tips, especially in autonomous, and making it
easier for an alliance partner to contribute. On September 26, 2026, we used
a weighted matrix to choose a pollen-only intake and shooting scope for our
first minimum viable product (MVP).

#table(
  columns: (2fr, 1fr, 3fr),
  inset: 6pt,
  stroke: 0.4pt + brand.accent,
  table.header([*Design option*], [*Weighted total*], [*Performance / simplicity / MVP readiness*]),
  [*Pollen (selected)*], [*907*], [8 / 10 / 10],
  [Pollen + Nectar], [842], [9 / 8 / 9],
  [Dual-Launcher], [794], [10 / 8 / 8],
)

Across eight criteria, we sum weight multiplied by score; weights total 100.
Reliability (20) and serviceability (18) carry the most weight. We accept a lower
performance rating for simplicity and MVP readiness. Ratings are not measured results.

== Engineering process: iterate component by component
We break the desired outcome into components and aim to test and refine each.
Selected examples of our reported development work:

#table(
  columns: (1.1fr, 2fr, 2.4fr),
  inset: 6pt,
  stroke: 0.4pt + brand.accent,
  table.header([*Component*], [*Change or alternatives*], [*Purpose / reported observation*]),
  [Intake], [5-7 printed wedge pairs],
  [Guide balls into a triangle pattern for consistent transfer.],
  [Shooter], [5+ hood versions; then a powered counter-roller],
  [Adjust thickness/angles to reduce backspin. Team reports backspin removed; no measurement supplied.],
  [Drivetrain], [Bevel gears replaced with a familiar prior design],
  [Inconsistency prompted the change; team reports greatly improved driving.],
)

== Lessons applied and next validation
Inconsistency and loose belts led to drivetrain and belt-spacing changes.
V1 capabilities are reported complete in wood; more testing will inform a move
to metal. Next, document student contributions and dated before/after tests.

#v(4pt)
#text(size: 10pt)[Draft prepared from team records with GitHub Copilot assistance.]
