# BIOBUZZ student documentation

**Describe your work in chat, notes, or a transcript; AI can prepare Markdown
records under `records` for your review.** You can also edit Markdown directly.
The separate `portfolio` folder is a curated presentation of selected evidence,
not the place to keep all your notes. The separate `requirements` folder defines
the official award criteria and the team's targets, not team accomplishments.
Files elsewhere under `doc` are inherited
SDK documentation; leave those separate from team material.

## Folder map

```text
doc\team\
  README.md                     Start here: where to put your work
  intake-guide.md               How chat and supplied information become records
  intake-subjects.md            Optional prompts for each subject
  requirements\                Official references and team award targets
    README.md                   Sources, versions, and Inspire/Think priorities
    biobuzz-2026-2027\           Award criteria and portfolio submission rules
  records\                      Markdown source of truth
    mechanical\README.md        Current robot design and hardware details
    programming\README.md       Current programming and control details
    outreach\README.md          Current outreach activities and impact
      fundraising\README.md     Fundraising and sponsorship activities
      mentoring\README.md       Support for other teams and learners
      recruitment\README.md     Student and mentor recruitment
      stem-awareness\README.md  Community STEM demonstrations and workshops
      community-service\README.md  Service addressing community needs
    team\README.md              Current learning, mentoring, and team development
    strategy\README.md          Current plans, resources, and competition strategy
    assets\README.md            Photos, diagrams, and supporting evidence
  templates\
    activity-record.md          Shared output guide for dated records
  portfolio\                    Curated Typst pages and shared layouts
    README.md                   PDF build and review instructions
    editorial-guide.md          Evidence-backed award language and page planning
    page-plan.md                Provisional budget, story register, and Typst roadmap
    review-guide.md             Consistent review and human action/stop conditions
    review-report-template.md   Reusable report with coverage and closure tracking
    content\                    Shortened portfolio narratives
```

## Where does my documentation go?

| What you are documenting | Markdown area | Examples |
|---|---|---|
| How the robot is built and why | [Mechanical](records/mechanical/README.md) | Mechanisms, CAD decisions, wiring, prototypes, hardware tests |
| How the robot behaves and how code works | [Programming](records/programming/README.md) | Autonomous paths, TeleOp controls, sensors, tuning, software tests |
| How we engage and support our community | [Outreach](records/outreach/README.md) | Fundraising, mentoring, recruitment, STEM awareness, community service |
| How our own students learn and lead | [Team development](records/team/README.md) | Training, mentoring, onboarding, student roles, knowledge transfer |
| How we plan and sustain the season | [Strategy](records/strategy/README.md) | Goals, schedules, competition strategy, resource plans, event lessons |

For work spanning areas, put the main record where its main question belongs
and link to it from the other area. For example, document a mechanism revision in
mechanical and link its sensor-control tests from programming. Do not copy the same
results into multiple records. The [award targets](requirements/README.md) define
the criteria we aim to support; these record folders are not award categories.

Outreach has five [category folders](records/outreach/README.md#outreach-categories).
Put dated outreach records in the relevant category, not directly in the outreach
root. Student and mentor recruitment share one folder; distinguish their audiences
in the records. External mentoring belongs in outreach; internal mentoring and
onboarding belong in team development. Fundraising activities belong in outreach;
overall resource planning belongs in strategy.

## Current details versus dated evidence

Each area's **`README.md` is its living current-details page**. Update it when the
design, behavior, plan, or status changes. Keep it concise enough that a new student
can understand the current situation. Add links to the dated records that explain
how the team got there.

For a test, design decision, meeting, event, or learning activity, AI can use the
[activity-record template](templates/activity-record.md) to prepare a dated record.
It is an output guide, not a questionnaire. Direct editors can copy and adapt it.
Use `YYYY-MM-DD-short-topic.md` with the activity date, for example:

```text
records\mechanical\2026-11-22-intake-revision.md
records\programming\2026-11-22-autonomous-repeatability.md
records\outreach\stem-awareness\2026-11-22-library-workshop.md
```

These are filename examples, not activities the team claims to have completed.
Keep dated records after the current summary changes. Correct inaccurate facts
explicitly; do not rewrite old results to match a newer design.

## How to contribute

1. Describe what happened in chat, paste notes, or supply a transcript, and ask
   the agent to record it. Include the activity date and supporting evidence if known.
2. AI reads existing documentation, chooses an area, and prepares draft updates
   to the current-details page, a dated record, or both.
3. Resolve consequential questions; optional missing details can remain
   `Not yet recorded`. Reported claims are not automatically verified.
4. Review the proposed Markdown changes, source descriptions, and evidence links.
   Raw inputs are not stored by default.
5. Have a student or mentor confirm factual accuracy and permissions before the
   record is marked reviewed. Keep any unverified claims or open questions visible.

See the [intake guide](intake-guide.md) for the full process and
[subject guidance](intake-subjects.md) for optional prompts. The
[repository instructions](../../AGENTS.md) direct agents to these guides;
there is no unattended intake service.

For direct editing, use the same record format and review process. Link dated
records from their area's current-details page and keep source and review status.

Use ordinary Markdown headings, lists, tables, and relative links. For example,
from a mechanical record, `[Test photo](../assets/mechanical/intake-test.jpg)`
links to an image and `[Relevant code](../../../../TeamCode/src/main/java/)`
links to the robot source directory. Link specific files and commits when
describing an implementation or measurement.

From an outreach category record, assets are one level farther away:
`[Event photo](../../assets/outreach/stem-awareness/workshop.jpg)`.
Use the shared record template rather than editing it in place.

Keep credentials, private contact details, confidential sponsor information,
and sensitive student information out of the repository. Record only approved
public information. See [assets guidance](records/assets/README.md) before
adding photos or data.

## From records to portfolio

The Markdown records are the detailed source of truth. Portfolio editors select
verified evidence, write a shorter narrative in `portfolio\content`, and retain
source-record links in those files. Keep factual corrections in the records first,
then update any portfolio passage that uses them.

Use the [portfolio editorial guide](portfolio/editorial-guide.md) to connect
supported evidence explicitly to award criteria and judge-readable language.
Inspire and Think are the primary targets; all six supporting Team Attributes/MCI
awards are available for evidence-based assessment. Criteria coverage is not
an award prediction.

The [review process](portfolio/review-guide.md) separates portfolio edits, missing
documentation, needed team work, and interview preparation. Humans choose actions;
reviewers use stable closure conditions instead of starting endless rewrite loops.

There is **no automatic Markdown-to-Typst conversion or rubric evaluation yet**.
Editing records alone does not rebuild or change the portfolio PDF. Follow the
[portfolio instructions](portfolio/README.md) when updating publishing content.
