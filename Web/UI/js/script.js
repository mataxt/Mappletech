var $ = jQuery.noConflict();

// Progress Bar
$(document).ready(function ($) {
    "use strict";
    
    $('.skill-shortcode').appear(function () {
        $('.progress').each(function () {
            $('.progress-bar').css('width',  function () { return ($(this).attr('data-percentage') + '%')});
        });
    }, {accY: -100});
        
        
});

//Owl Carousel
$(document).ready(function() {
 
  $("#owl-demo").owlCarousel({
 
      slideSpeed : 1000,
      paginationSpeed : 500,
      singleItem:true,
      autoPlay: 6000
 
  });
});