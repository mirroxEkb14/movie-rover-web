/* slider logic using "DOM" */
let nextDom = document.getElementById('next');
let prevDom = document.getElementById('prev');

let carouselDom = document.querySelector('.carousel');
let SliderDom = carouselDom.querySelector('.carousel .list');
let thumbnailBorderDom = document.querySelector('.carousel .thumbnail');
let thumbnailItemsDom = thumbnailBorderDom.querySelectorAll('.item');
let timeDom = document.querySelector('.carousel .time');

thumbnailBorderDom.appendChild(thumbnailItemsDom[0]);
let timeRunning = 3000;
let timeAutoNext = 8000;

nextDom.onclick = function(){
    showSlider('next');    
}
prevDom.onclick = function(){
    showSlider('prev');    
}

let runTimeOut;
let runNextAuto = setTimeout(() => {
    next.click();
}, timeAutoNext)

function showSlider(type){
    let  SliderItemsDom = SliderDom.querySelectorAll('.carousel .list .item');
    let thumbnailItemsDom = document.querySelectorAll('.carousel .thumbnail .item');
    
    if(type === 'next'){
        SliderDom.appendChild(SliderItemsDom[0]);
        thumbnailBorderDom.appendChild(thumbnailItemsDom[0]);
        carouselDom.classList.add('next');
    }else{
        SliderDom.prepend(SliderItemsDom[SliderItemsDom.length - 1]);
        thumbnailBorderDom.prepend(thumbnailItemsDom[thumbnailItemsDom.length - 1]);
        carouselDom.classList.add('prev');
    }
    clearTimeout(runTimeOut);
    runTimeOut = setTimeout(() => {
        carouselDom.classList.remove('next');
        carouselDom.classList.remove('prev');
    }, timeRunning);

    clearTimeout(runNextAuto);
    runNextAuto = setTimeout(() => {
        next.click();
    }, timeAutoNext)
}
/* 'DOMContentLoaded' event listener */
document.addEventListener('DOMContentLoaded', () => {
    //redirection logic for "TRY OUT" buttons
    const tryOutButtons = document.querySelectorAll('.btn-try-out');
    tryOutButtons.forEach(button => {
        button.addEventListener('click', () => {
            window.open('https://t.me/MovieRoverBot', '_blank');
        });
    });

    // redirection logic for "SEE MORE" buttons
    const seeMoreButtons = document.querySelectorAll('.btn-see-more');
    seeMoreButtons.forEach(button => {
        button.addEventListener('click', () => {
            const path = button.getAttribute('data-path');
            window.location.href = path;
        });
    });
});
/* scrolling to the "footer" when clicking "Contacts" */
document.addEventListener('DOMContentLoaded', () => {
    const contactsLink = document.getElementById('contacts-link');
    const footer = document.getElementById('footer');

    contactsLink.addEventListener('click', (event) => {
        event.preventDefault();
        footer.scrollIntoView({ behavior: 'smooth' });
    });
});