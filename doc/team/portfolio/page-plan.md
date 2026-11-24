# BIOBUZZ portfolio: starting story map and page budget

**Planning baseline recorded:** November 22, 2026  
**Season:** 2026-2027 BIOBUZZ  
**Primary targets:** Inspire and Think  
**Status:** Provisional starting map; not a final page allocation or event approval  
**Target event and content cutoff:** Not yet recorded

This captures the initial planning discussion as a goal for editorial and Typst
work. It describes proposed subjects, not activities or results already documented.
Read the [official requirements](../requirements/README.md),
[editorial guide](editorial-guide.md), and [review guide](review-guide.md).

## Purpose and limits

Build an evidence-backed story of students' engineering journey, team development,
and community contribution. Use award language clearly when supported, without
forcing every activity into an award or repeating the same evidence.

Plan for one identification cover and **at most 15 content pages**. Fifteen is
a maximum, not a target to fill. Cover material does not count as award evidence.
The page numbers below refer to content pages; physical PDF page numbers will
include the cover. Final positions may change as evidence and layout develop.

## Provisional budget

| Content pages | Budget | Main purpose | Potential award connections |
|---|---|---|---|
| Cover | Separate | Team number, season/event identification, and approved branding | Identification only; no exclusive award evidence |
| 1 | 1 page | Team introduction, season objectives, and student contributions | Inspire |
| 2-4 | 3 pages | Outreach strategy, recruitment, STEM relationships, and supported outcomes | Reach, Connect |
| 5-6 | 2 pages | Sustainability, progress tracking, leadership development, and organizational risk management | Sustain, Inspire |
| 7 | 1 page | Whole-robot overview and design aligned with game strategy | Design |
| 8-10 | 3 pages | Mechanical alternatives, iterations, tests, calculations where relevant, and applied learning | Think, Innovate, Design |
| 11-13 | 3 pages | Programming/control architecture, external feedback, development, and reliability | Think, Control |
| 14-15 | Up to 2 pages | Reserve for the strongest evidence or a topic needing more room | Allocate only after reviewing evidence |

The base allocation is 13 content pages plus up to two reserve pages.
Reallocate deliberately; do not create filler, duplicate summaries, or shrink
text to preserve an arbitrary slot.

These connections are candidates, not a claim of criterion coverage. Not every
outreach category or award needs a dedicated page. Fundraising, mentoring,
recruitment, awareness, and service earn space through relevant supported evidence.
Likewise, calculations and external-feedback narratives belong only where the
team actually used them.

Inspire needs documentary strengths across MCI, Team Attributes, and Think.
Evaluate all six supporting awards, but choose supporting paths with humans after
examining evidence. Think's four qualifying topics are alternatives, not four
mandatory pages.

## Turn ideas into stories

For each existing team idea, create an entry in the register below before
assigning a final page or implementing a layout. A story can support several
criteria or use more than one page. Use a stable story ID independent of page
number, so reallocation does not lose its evidence history.

The first selected story is now in draft layout. Links to subject overviews
are navigation aids, not proof of accomplishments:
[mechanical](../records/mechanical/README.md),
[programming](../records/programming/README.md),
[outreach](../records/outreach/README.md),
[team development](../records/team/README.md), and
[strategy](../records/strategy/README.md).

| Story ID and proposed title | Main claim to support | Source records and evidence status | Award/criterion IDs | Proposed page/space | Best visual or layout | Missing information and next human action | Planning state |
|---|---|---|---|---|---|---|---|
| MECH-01: Strategy into design | Compare first-MVP options, then iterate components in response to problems | [Matrix](../records/strategy/2026-09-26-first-mvp-design-matrix.md) (image transcribed; arithmetic checked); [component history](../records/mechanical/README.md#system-design-approach-and-component-iterations), [game strategy](../records/strategy/game-strategy.md), and [V1 status](../records/strategy/README.md#v1-mvp-requirements-and-status) (reported, not independently verified) | THINK-1A/B/C/D and THINK-2; manual 6.3.2, Table 6-3. Other MCI paths not selected | One-page trial, physical page 1 without cover; final slot within mechanical pages 8-10 remains provisional | Condensed choice matrix and component-iteration table; no invented mechanism diagram | Human accuracy review; student contributions; dates and before/after evidence for selected iterations. Do not treat this trial as event-ready | Draft layout |

Planning states are `Idea`, `Awaiting documentation`, `Ready to draft`,
`Draft layout`, and `Human reviewed`. These are workflow states, not evidence
verification or award ratings. Do not mark a story reviewed without confirmation.

An entry is ready to draft when its main point is clear, relevant records are
available, consequential factual uncertainties are identified, and there is a
supported basis for its proposed award connection. If information is missing,
route it through [intake](../intake-guide.md) rather than filling gaps with prose.

## First three real-content prototypes

Use real records to develop these before expanding the full document:

| Prototype | Purpose | Current Typst starting point |
|---|---|---|
| Mechanical iteration | Show a problem, alternatives or changes, evidence, and applied learning | `content/mechanical.typ`; comparison and photo components |
| Programming/control story | Explain a real behavior or feedback system, its development, and supported results | `content/programming.typ`; evidence cards and comparison components |
| Team-attribute story | Explain one supported Connect, Reach, or Sustain narrative | `content/outreach.typ` where appropriate; adapt or add content only after story selection |

`content/mechanical.typ` now contains MECH-01, based on the supplied records.
`main.typ` renders only this selected story for the first end-to-end trial.
Programming and outreach files remain unused layout prompts, not selected stories.
The trial's physical page 1 is not content page 1 of the final allocation above.
For a Sustain or internal team-development story, select the right source records
rather than treating the outreach prototype as the only source.

A useful narrative pattern is purpose, approach/alternatives, evidence/results,
then learning or the next decision. Adapt it to the story; not every page needs
every element or a numerical metric.

## Typst implementation sequence

1. Add the team's existing ideas to the story register, linking evidence and
   marking gaps. Confirm provisional allocations and supporting award paths
   before treating them as a review baseline.
2. Draft the three representative stories and use them to refine shared styling
   and comparison, diagram, photo, and results layouts in `template.typ`.
3. Review the rendered prototypes for accurate claims, clear award connections,
   readability, and fit. Address human-selected findings, not unlimited redesigns.
4. Add a dedicated identification cover once the team number, season/event details,
   and approved branding are supplied. Keep award evidence on content pages.
5. Assemble selected stories in `main.typ` in the agreed order. Add content files
   as needed; do not generate 15 empty pages or force one file per page.
6. Allocate reserve space only where stronger evidence warrants it. Update this
   map and the story register when allocations change.
7. Review the concrete edition against the official rules and the agreed scope.
   Page-count and content-fit checks still need implementation; a successful build
   does not establish compliance.

## When planning is ready to advance

The next milestone is a populated story register and three real-content prototypes,
not a polished 15-page submission. Each selected prototype needs a clear main point,
source references, accurate criterion connections, and visible unresolved gaps.

For the first steel-thread trial, the user selected **one mechanical story, PDF
build, and scoped review** on November 23, 2026. Its acceptance scope is a readable
single-page MECH-01 draft with source traceability, honest evidence boundaries,
and one review identifying human actions. The review does not select an MCI/TA
path, approve event submission, require the other two prototypes, or trigger
automatic rewrites. Keep existing typography and margins; no new styling standard
has been approved. After the build, freeze the artifact identity for that review.

Before a final edition, humans must confirm the page/story allocation, supporting
award paths, layout/readability standards, event instructions, and remaining
actions. Freeze those decisions for the revision cycle. Optional improvements
and unused reserve space do not move the finish line.

This Markdown plan is an internal roadmap. It does not generate Typst pages,
change the PDF automatically, or add a planning page to the submitted portfolio.
