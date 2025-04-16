const apiBaseUrl = "http://localhost:8080/clients";

// Add new client
document.getElementById("clientForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  
  const data = {
    emailId: document.getElementById("emailId").value,
    firstName: document.getElementById("firstName").value || null,
    lastName: document.getElementById("lastName").value || null,
    mobileNumber: document.getElementById("mobileNumber").value || null,
    isActive: document.getElementById("isActive").checked
  };

  try {
    const res = await fetch(apiBaseUrl, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data)
    });

    if (!res.ok) throw new Error("Failed to add client");
    alert("Client added successfully!");
    document.getElementById("clientForm").reset();
    loadAllClients();
  } catch (err) {
    alert("Error: " + err.message);
  }
});

// Search clients
async function searchClients() {
  const query = document.getElementById("searchInput").value;
  if (!query) return;

  try {
    const res = await fetch(`${apiBaseUrl}/search?query=${query}`);
    const clients = await res.json();
    displayClients(clients);
  } catch (err) {
    alert("Search failed.");
  }
}

// Load all clients
async function loadAllClients() {
  try {
    const res = await fetch(apiBaseUrl + "/all");
    const clients = await res.json();
    displayClients(clients);
  } catch (err) {
    alert("Failed to load clients.");
  }
}

// Display clients
function displayClients(clients) {
  const result = document.getElementById("result");
  result.innerHTML = "";
  if (clients.length === 0) {
    result.innerHTML = "<p>No clients found.</p>";
    return;
  }

  clients.forEach(client => {
    result.innerHTML += `
      <div class="client-card">
        <p><strong>Email:</strong> ${client.emailId}</p>
        <p><strong>Name:</strong> ${client.firstName || ""} ${client.lastName || ""}</p>
        <p><strong>Mobile:</strong> ${client.mobileNumber || "N/A"}</p>
        <p><strong>Status:</strong> ${client.isActive ? "Active" : "Inactive"}</p>
      </div>
    `;
  });
}

// Load all on page load
window.onload = loadAllClients;
