const apiUrl = 'http://localhost:80/latestcars.json';

fetch(apiUrl)
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
        data.forEach(car => {
            $(document).ready(function(){
                var newBox = $("<div class='box'></div>");
                var boxImg = $("<div class='box-img'></div>").append("<img src="+car.ImgUrl+" alt=''>");
                var p3 = $("<p3>"+car.YearOfProduction+"</p3>");
                var h3 = $("<h3>"+car.Name+"</h3>");
                var h2 = $("<h2>"+car.PricePerDay+"<span>$/day</span></h2>");
                var link = $("<a href='#' class='btn'>Rent Now</a>");
                newBox.append(boxImg, p3, h3, h2, link);
                $("#latest5").append(newBox);
            });
        });

        // Move the reveal effect outside of the loop
        $(document).ready(function(){
            sr.reveal('.services-container .box', { delay: 400, origin: 'top' });
        });
    })
    .catch(error => {
        console.error('Error during fetch:', error.message);
    });


  
  