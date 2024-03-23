var menuItem = document.querySelectorAll('.menu-item');

function selectLink() {
  menuItem.forEach(element => {
    element.classList.remove('active');
  });
  this.classList.add('active');
}


menuItem.forEach(element => {
  element.addEventListener('click', selectLink);
});


var btnExpand = document.querySelector('#btn-expand');
var navMenu = document.querySelector('.nav-menu');

btnExpand.addEventListener('click', function(){
  navMenu.classList.toggle('expand');
})
