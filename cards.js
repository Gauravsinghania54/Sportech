var cards = document.getElementsByClassName("card");

var toggleCards = function() {
 
    if($(this).hasClass('open')){
    $(this).removeClass('open');
    $('.card').removeClass('faded');
  } else {
    $('.card').removeClass('open');
    $(this).addClass('open');
    $(this).removeClass('faded');
    $('.card').not($(this)).addClass('faded');
  }
  
  $('body, html').animate({
    scrollTop: $(this).offset().top
  }, 500)
};

for (var i = 0 ; i < cards.length ; i++) {
  var clickEvent = (function() {
  if ('ontouchstart' in document.documentElement === true) {
  return 'touchstart';
  }
  else {
  return 'click';
  }
})();
    cards[i].addEventListener(clickEvent, toggleCards, false);
}


var clickEvent = (function() {
  if ('ontouchstart' in document.documentElement === true) {
  return 'touchstart';
  }
  else {
  return 'click';
  }
})();