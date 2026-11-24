#let brand = (
  team: "BIOBUZZ",
  identification: "2026-2027 BIOBUZZ / Team number pending",
  accent: rgb("#245C45"),
  pale: rgb("#EDF4EF"),
)

#let portfolio(body) = {
  set document(title: "BIOBUZZ portfolio prototype", author: brand.team)
  set page(
    paper: "us-letter",
    margin: (x: 0.65in, y: 0.7in),
    footer: context [
      #set text(size: 9pt)
      #brand.team #h(1fr) WORKING DRAFT #h(1fr) #counter(page).display("1")
    ],
  )
  set text(font: "Libertinus Serif", size: 11pt, fill: rgb("#202824"))
  set par(leading: 0.65em)
  set heading(numbering: none)
  show heading.where(level: 1): set text(size: 24pt, fill: brand.accent)
  show heading.where(level: 2): set text(size: 15pt, fill: brand.accent)
  show heading.where(level: 3): set text(size: 12pt, fill: brand.accent)
  body
}

#let topic-page(
  title,
  category,
  body,
  notice: [
    *Prototype only.* Prompts and placeholders below are not team accomplishments
    or verified award evidence.
  ],
) = page[
  #text(size: 10pt, weight: "bold", fill: brand.accent)[#brand.team / #category]
  #heading(level: 1)[#title]
  #text(size: 9pt)[#brand.identification]
  #v(8pt)
  #block(fill: brand.pale, inset: 9pt, width: 100%)[
    #notice
  ]
  #v(8pt)
  #body
]

#let evidence-card(label, body) = block(
  width: 100%,
  fill: brand.pale,
  inset: 12pt,
)[
  *#label*

  #body
]

#let comparison(left, right) = grid(
  columns: (1fr, 1fr),
  gutter: 16pt,
  left,
  right,
)

#let photo-panel(caption, source: none) = figure(
  if source == none {
    rect(width: 100%, height: 1.8in, fill: brand.pale, stroke: brand.accent)[
      #align(center + horizon)[Photo or diagram placeholder]
    ]
  } else {
    image(source, width: 100%, height: 1.8in, fit: "contain")
  },
  caption: caption,
)
