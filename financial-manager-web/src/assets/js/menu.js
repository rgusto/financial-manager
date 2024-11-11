const body = document.querySelector("body"),
  sidebar = body.querySelector(".sidebar"),
  sidebarToggle = body.querySelector(".sidebar-toggle"),
  searchBtn = body.querySelector(".search-box"),
  modeSwitch = body.querySelector(".mode-toggle-switch"),
  modeText = body.querySelector(".mode-text");

sidebarToggle.addEventListener("click", () => {
  sidebar.classList.toggle("close");
  if (sidebar.classList.contains("close")) {
    localStorage.setItem("status", "close");
  } else {
    localStorage.setItem("status", "open");
  }
});

modeSwitch.addEventListener("click", () => {
  body.classList.toggle("dark");
  if (body.classList.contains("dark")) {
    modeText.innerText = "Claro";
    localStorage.setItem("mode", "dark");
  } else {
    modeText.innerText = "Escuro";
    localStorage.setItem("mode", "light");
  }
});

searchBtn.addEventListener("click", () => {
  sidebar.classList.remove("close");
});

let getMode = localStorage.getItem("mode");
if (getMode && getMode === "dark") {
  body.classList.toggle("dark");
}

let getStatus = localStorage.getItem("status");
if (getStatus && getStatus === "close") {
  sidebar.classList.toggle("close");
}
