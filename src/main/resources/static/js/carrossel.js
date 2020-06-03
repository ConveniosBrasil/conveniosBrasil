$(document).ready(function(){
  $('.carousel').carousel({
    dist: 0,
    padding: 0,
    fullWidth: true,
    indicators: true,
  });
  
  setInterval(function() {
	$('.carousel').carousel('next');
  },3000);
});