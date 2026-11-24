# BIOBUZZ portfolio prototype

This is the starting point for a repo-first, student-owned FTC portfolio:
students edit curated content, shared Typst layouts keep formatting consistent,
and GitHub Actions builds a PDF for review. No Typst account or subscription is
required. The robot's Gradle build is unchanged.

Start with the Markdown [team records](../README.md#where-does-my-documentation-go)
to document current details and dated evidence. This folder contains the shorter
publishing version, not the team's working notebook.

The [requirements area](../requirements/README.md) holds the 2026-2027 BIOBUZZ
criteria and confirmed Inspire/Think priorities separately from team records.
Follow the [editorial guide](editorial-guide.md) to use award language explicitly
and connect each narrative to supporting records.

The [starting page plan](page-plan.md) records the provisional cover/content budget,
an evidence-linked story register, and the Typst implementation sequence.
It is an internal roadmap, not a final allocation or an automatically rendered page.

The [review guide](review-guide.md) and [report template](review-report-template.md)
provide a separate, human-directed review process. It reports stable criterion
coverage, evidence gaps, and concrete actions without automatic rewrites or an
award score. No review or approval of the placeholder pages has been performed.

## Student quick start: chat, check, preview

Students do not need to edit Typst. In a repository-connected chat:

1. Say **"Record this update about our intake..."** and describe the work naturally.
   Review the proposed Markdown changes and resolve consequential questions.
2. Separately say **"Update portfolio story MECH-01 from those records and build
   the preview. Keep the existing layout and label missing evidence."**
3. Open `build\portfolio.pdf` or download the **Portfolio PDF** Actions artifact
   after the publishing-source changes are pushed. Check both the page and its facts.
4. Say **"Review MECH-01 once using the review guide; report actions, don't edit."**
   Choose which findings to address before asking for a focused recheck.

These are requests to an agent, not special commands or an unattended service.
Record intake alone never changes publishing copy. A reviewer does not rewrite it.
No student-usability trial has been conducted yet.

## What is ready

`main.typ` currently assembles one selected mechanical story, **MECH-01**, from
`content/mechanical.typ`: strategy, first-MVP choices, and component iterations.
Its records are linked in the [story register](page-plan.md#turn-ideas-into-stories)
and identified in Typst source comments. Outcomes are team-reported, not verified.
`content/programming.typ` and `content/outreach.typ` remain unused sample prompts.
`template.typ` owns typography, colors, identification, captions, evidence cards,
and comparison layouts. These are lightweight Typst files, **not Markdown**;
Markdown conversion is not part of this prototype.

The mechanical story is a real-content draft, not approved publishing copy.
The remaining sample files are writing prompts, not team accomplishments.
The green palette,
11-point body text, and page plan are provisional design choices. US Letter is
a permitted page size under the referenced BIOBUZZ rules, but this prototype is
not an event-ready submission. Supply the team number, completed identification,
logo/colors, approved photos, and actual evidence, and review all
[submission requirements](../requirements/biobuzz-2026-2027/portfolio-rules.md)
before preparing an event edition.

## Local setup and preview

Download the **0.15.1** compiler for your platform from the
[official Typst release](https://github.com/typst/typst/releases/tag/v0.15.1).
Extract it and add the folder containing `typst.exe` to your PATH on Windows,
or invoke the executable by its full path. No global installer is required.
Confirm `typst --version` reports `0.15.1`, matching CI.

From the repository root, run this PowerShell command (also used by CI):

```powershell
.\doc\team\portfolio\build.ps1
```

For an extracted compiler not on PATH, pass its actual executable path:
`.\doc\team\portfolio\build.ps1 -TypstPath 'C:\tools\typst\typst.exe'`.
The helper checks the pinned version, creates the build folder, and surfaces
compilation errors. Paths resolve from the script's location, not the caller's.
PowerShell must permit running this repository script under your device policy;
the direct compiler commands below are an alternative without changing that policy.

Direct compilation:

```powershell
New-Item -ItemType Directory -Force doc\team\portfolio\build | Out-Null
typst compile --root doc\team\portfolio --ignore-system-fonts doc\team\portfolio\main.typ doc\team\portfolio\build\portfolio.pdf
```

Open `doc\team\portfolio\build\portfolio.pdf` in your PDF viewer. For continuous
rebuilds while editing, run:

```powershell
typst watch --root doc\team\portfolio --ignore-system-fonts doc\team\portfolio\main.typ doc\team\portfolio\build\portfolio.pdf
```

Stop the watcher with Ctrl+C. The template uses Typst's embedded Libertinus Serif
font; ignoring system fonts keeps local and CI layout consistent. It has no
external Typst packages. Generated files under `build` are ignored by Git.

## Student contribution flow

1. Use the chat-first steps above, or edit publishing content directly on a branch.
2. Link source records and preserve reported/verified distinctions; never invent
   dates, measurements, or evidence to make a page look complete.
3. Open a pull request targeting the team's documentation branch while this
   prototype is being developed (`documentationWorkflow`), or the approved branch
   once the team adopts it.
4. In the **Portfolio PDF** Actions run, download the `portfolio-<commit>` artifact,
   unzip it, and review the PDF alongside the source changes.
5. Have a student or mentor review wording, evidence, photo permissions, and
   printed readability before merging.

The workflow runs for portfolio changes on pull requests and branch pushes,
and can be started manually from GitHub's Actions tab once it is present on the
repository's default branch. Actions must be enabled in the repository.
Artifacts last 30 days; they are previews, not permanent competition submissions.
For an event, preserve the approved source commit and attach the approved PDF
to a GitHub Release manually.

## Content and layout edits

Use `==` for section headings, `===` for subheadings, and `*text*` for emphasis.
Keep file imports and component calls intact. Edit prose inside square brackets;
shared components apply styling automatically.

To add a photo, create `doc\team\portfolio\assets`, put the approved image there,
and change a content file's photo call to:

```typst
#photo-panel(source: "/assets/mechanism.jpg")[
  Actual revision, test date, and factual explanation of what the photo shows.
]
```

Paths in Typst source use forward slashes. Use `/assets/...` for this component:
the leading slash resolves from the portfolio root supplied with `--root`, not
your computer's filesystem root. Images are contained within the panel rather
than cropped. Keep the original evidence and its permission information in the
Markdown [records](../README.md#where-does-my-documentation-go) and
[record assets](../records/assets/README.md). Copy only approved publishing images
into this portfolio's assets folder and link back to the source record from the
relevant content file. Curate and shorten the evidence rather than duplicating
the entire season notebook. Markdown changes do not automatically update the PDF.

Layout owners edit `template.typ` and select or extend the shared page types.
Do not quietly shrink text or margins to accommodate more content.

## What comes next

This is a visual prototype, **not an award compliance or publishing gate**.
Content can flow onto extra pages; successful compilation does not prove a topic
fits on one page. No overflow is intentionally clipped, but the PDF must be
reviewed for layout problems. There is no fit report, page-limit enforcement,
rubric scoring, AI rewrite trigger, or automatic publication yet.

Next, use the referenced season's official rules and award criteria to establish
the page plan, evidence requirements, readability standards, and measured
content-fit checks. AI may then propose bounded revisions to over-budget content,
preserving facts and leaving layout changes and final approval to humans.
