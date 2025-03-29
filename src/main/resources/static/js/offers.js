document.addEventListener("DOMContentLoaded", function() {
	fetchOffers(); // Load all offers initially
});

let currentPage = 1;
const itemsPerPage = 2;
let filteredOffers = [];

function fetchOffers(originCity = "", destinationCity = "") {
	const apiUrl =  
		  `/api/offers?originCity=${originCity}&destinationCity=${destinationCity}`
		 ;

	return fetch(apiUrl) // Ensure fetch promise is returned
        .then(response => response.json())
        .then(data => {
            filteredOffers = data;
            currentPage = 1;
            renderHotelCards();
        })
        .catch(error => console.error("Error fetching offers:", error));
}

function renderHotelCards() {
	const startIndex = (currentPage - 1) * itemsPerPage;
	const endIndex = startIndex + itemsPerPage;
	const currentOffers = filteredOffers.slice(startIndex, endIndex);
	const hotelCardsContainer = document.getElementById("hotelCardsContainer");
	const noResultsMessage = document.getElementById("noResultsMessage");

	const paginationContainer = document.querySelector(".pagination");

    hotelCardsContainer.innerHTML = ""; // Clear existing cards

    // Show error message if no results are found
    if (filteredOffers.length === 0) {
        noResultsMessage.classList.remove("d-none");
        paginationContainer.classList.add("d-none"); // Hide pagination if no results
    } else {
        noResultsMessage.classList.add("d-none");
        paginationContainer.classList.remove("d-none");
		currentOffers.forEach(offer => {
			const card = `
				<div class="col-md-6">
					<div class="hotel-card">
						<img src="${offer.image || 'https://via.placeholder.com/180'}" class="hotel-image" alt="${offer.hotelName}">
						<div class="card-body">
							<h5 class="card-title">${offer.hotelName}</h5>
							<p class="card-text">From: ${offer.from} to ${offer.to}</p>
							<p class="card-text">Travel End Date: ${offer.travelEndDate}</p>
							<p class="offer-price">Offer Price: ${offer.offerPrice}</p>
							<p class="card-text"><i class="fas fa-map-marker-alt"></i> ${offer.originCity} → <i class="fas fa-plane"></i> ${offer.destinationCity}</p>
						</div>
					</div>
				</div>
			`;
			hotelCardsContainer.innerHTML += card;
		});
	}

	updatePaginationButtons();
}

function updatePaginationButtons() {
	document.getElementById("prevPage").disabled = currentPage === 1;
	document.getElementById("nextPage").disabled = currentPage * itemsPerPage >= filteredOffers.length;
}

function changePage(direction) {
	const totalPages = Math.ceil(filteredOffers.length / itemsPerPage);

	if ((direction === -1 && currentPage === 1) || (direction === 1 && currentPage >= totalPages)) {
		return; // Prevent going out of bounds
	}

	currentPage += direction;
	renderHotelCards();
}

 function searchOffers() {
    const searchButton = document.querySelector(".search-btn");

    if (!searchButton) return; // Ensure the button exists

    const originalButtonText = searchButton.innerHTML;

    // Show loading state
    searchButton.disabled = true;
    searchButton.innerHTML = `<i class="fas fa-spinner fa-spin"></i>`;

    const originCity = document.getElementById("originCity").value.trim();
    const destinationCity = document.getElementById("destinationCity").value.trim();

    fetchOffers(originCity, destinationCity)
        .then(() => {
            // Ensure UI updates after data load
            renderHotelCards();
            updatePaginationButtons();
        })
        .catch(error => console.error("Error fetching offers:", error))
        .finally(() => {
            // Restore button state AFTER successful or failed request
            searchButton.disabled = false;
            searchButton.innerHTML = originalButtonText;
        });
 

}
