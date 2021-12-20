var systemTheme = window.matchMedia("(prefers-color-scheme: dark)")
changeMermaidTheme(systemTheme);
systemTheme.addEventListener('change', changeMermaidTheme);
function changeMermaidTheme(e) {
  if (e.matches) {
    mermaid.initialize({startOnLoad: true,theme:'dark'});
  } else {
    mermaid.initialize({startOnLoad: true,theme:'default'});
  }
  mermaid.init(undefined,'.language-mermaid');
}
