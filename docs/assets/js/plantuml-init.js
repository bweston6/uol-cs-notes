$("code.language-plantuml").each(function() {
  const prefersDarkScheme = window.matchMedia("(prefers-color-scheme: dark)");
  if (prefersDarkScheme.matches) {
    var src = "http://www.plantuml.com/plantuml/svg/" + window.plantumlEncoder.encode("skinparam monochrome reverse\nskinparam backgroundColor transparent\n" + $(this).text());
  } else {
    var src = "http://www.plantuml.com/plantuml/svg/" + window.plantumlEncoder.encode($(this).text());
  }
  $(this).replaceWith($('<img>').attr({'src': src, 'loading': 'lazy'}));
});
