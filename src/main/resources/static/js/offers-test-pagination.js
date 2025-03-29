// Use var instead of const
var currentPage = 0;
var pageSize = 2;
// Load default data on page load
window.onload = function() {
	loadOffers(currentPage);
};


// Call rest api 
function loadOffers(page = 0) {
	const originCity = document.getElementById('originCity').value;
	const destinationCity = document.getElementById('destinationCity').value;

	fetch(`/api/offers?page=${page}&size=${pageSize}&originCity=${originCity}&destinationCity=${destinationCity}`)
		.then(response => response.json())
		.then(data => {
			const offers = data.content;
			const totalPages = data.totalPages;
			displayOffers(offers);
			updatePagination(page, totalPages);
		})
		.catch(error => console.error('Error fetching data:', error));
}

// Function to display the offers on the page
function displayOffers(offers) {
	const container = document.getElementById('hotelCardsContainer');
	container.innerHTML = ''; // Clear the current cards

	if (offers.length === 0) {
		document.getElementById('noResultsMessage').classList.remove('d-none');
	} else {
		document.getElementById('noResultsMessage').classList.add('d-none');
		offers.forEach(offer => {
			
			const offerCard = `
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
                     
			container.innerHTML += offerCard;
		});
	}
}

// Function to update pagination buttons
function updatePagination(currentPage, totalPages) {
	const pagination = document.getElementById('pagination');
	const prevPageBtn = document.getElementById('prevPage');
	const nextPageBtn = document.getElementById('nextPage');

	if (currentPage > 0) {
		prevPageBtn.disabled = false;
	} else {
		prevPageBtn.disabled = true;
	}

	if (currentPage < totalPages - 1) {
		nextPageBtn.disabled = false;
	} else {
		nextPageBtn.disabled = true;
	}

	pagination.classList.remove('d-none');
}

// Handle search button click
function searchOffers() {
	currentPage = 0; // Reset to first page on search
	loadOffers(currentPage);
}

// Handle page change
function changePage(direction) {
	currentPage += direction;
	loadOffers(currentPage);
}



