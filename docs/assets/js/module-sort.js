document.addEventListener("DOMContentLoaded", () => {
  const button = document.getElementById("sort-toggle");
  const container = document.getElementById("module-posts");

  if (!button || !container) return;

  const STORAGE_KEY = "uol:module-sort-order";

  const sortArticles = (order) => {
    const articles = Array.from(container.querySelectorAll("article"));

    articles.sort((a, b) => {
      const da = a.dataset.date;
      const db = b.dataset.date;
      return order === "asc" ? da.localeCompare(db) : db.localeCompare(da);
    });

    articles.forEach((a) => container.appendChild(a));
  };

  const updateButton = (order) => {
    button.dataset.order = order;
    button.textContent = order === "desc" ? "Newest First ↑" : "Oldest First ↓";
  };

  const savedOrder = localStorage.getItem(STORAGE_KEY);
  const initialOrder =
    savedOrder === "asc" || savedOrder === "desc" ? savedOrder : "asc";

  updateButton(initialOrder);
  sortArticles(initialOrder);

  button.addEventListener("click", () => {
    const newOrder = button.dataset.order === "desc" ? "asc" : "desc";

    localStorage.setItem(STORAGE_KEY, newOrder);
    updateButton(newOrder);
    sortArticles(newOrder);
  });
});
