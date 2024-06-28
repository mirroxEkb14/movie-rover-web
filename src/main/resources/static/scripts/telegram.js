const images = [
    '/assets/telegram-img1.jpeg',
    '/assets/telegram-img2.jpeg',
    '/assets/telegram-img3.jpeg'
];

const durations = [5000, 4000, 3000];
let currentIndex = 0;
const photoSlide = document.querySelector('.photo-slide');

function changeBackgroundImage() {
    photoSlide.style.backgroundImage = `url(${images[currentIndex]})`;
    currentIndex = (currentIndex + 1) % images.length;
    setTimeout(changeBackgroundImage, durations[currentIndex]);
}
changeBackgroundImage();