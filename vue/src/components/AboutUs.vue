<template>
  <div class="about-us-container">
    <div class="about-header">
      <h1>About Us</h1>
      <p>
        Discover the story behind Rocco's Pizza, where every bite is a celebration of fresh
        ingredients, flavorful recipes, and the love for great food!
      </p>
    </div>

    <div class="about-section">
      <div class="about-box">
        <h2>Our Mission</h2>
        <p>
          Welcome to <strong>Rocco's Pizza</strong>, your ultimate pizza destination where every bite
          is an adventure! Our mission is simple: to serve the most flavorful, creative, and
          unforgettable pizzas in town.
        </p>
        <p>
          Whether you're a fan of the timeless <strong>Margherita</strong> or something bold like our
          <strong>BBQ Chicken Supreme</strong>, we promise each pizza is crafted with care and
          passion.
        </p>
      </div>

      <div class="about-box">
        <h2>Our Story</h2>
        <p>
          It all began in <strong>2024</strong> when we decided to break away from the ordinary and
          bring something special to the table. From the very first dough we kneaded by hand to the
          freshly-picked local veggies we use, we’ve been committed to delivering the highest
          quality pizza experience.
        </p>
        <p>
          Our secret? It’s simple. We never compromise on taste, focusing on top-notch ingredients,
          perfectly balanced flavors, and that <strong>crispy golden crust</strong> that makes each
          slice irresistible.
        </p>
      </div>

      <div class="about-box">
        <h2>Why Choose Us?</h2>
        <ul>
          <li>
            Weekly-changing <strong>specials</strong> to keep your taste buds excited.
          </li>
          <li>
            Fast, friendly <strong>delivery service</strong> to bring the pizza to your door.
          </li>
          <li>
            A passion for quality and a commitment to unforgettable flavors.
          </li>
        </ul>
        <p>
          Thank you for choosing <strong>Rocco's Pizza</strong>—where passion for pizza meets the
          love of great food, and every slice is an invitation to savor something truly amazing.
        </p>
      </div>
    </div>

    <div class="photo-gallery">
      <h2>Our Journey in Pictures</h2>
      <div class="gallery">
        <div v-for="(photo, index) in photoGallery" :key="index" class="photo-item">
          <img :src="photo.image" :alt="photo.description" class="gallery-image" />
          <p>{{ photo.description }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      photoGallery: [
        {
          image: new URL("../assets/pic5.jpg", import.meta.url).href,
          description: "Pizza ready to serve.",
        },
        {
          image: new URL("../assets/Pic1.jpg", import.meta.url).href,
          description: "We LOVE Pizza!",
        },
        {
          image: new URL("../assets/pic3.jpg", import.meta.url).href,
          description: "Fresh ingredients on display.",
        },
        {
          image: new URL("../assets/pic6.jpg", import.meta.url).href,
          description: "The pizza so big, it does not fit the frame.",
        },
        {
          image: new URL("../assets/ModelA.jpg", import.meta.url).href,
          description: "Hot Pizza, Cool Vibes.",
        },
      ],
    };
  },
};
</script>

<style scoped>
.about-us-container {
  font-family: "Arial", sans-serif;
  color: #333;
  padding: 20px;
}

.about-header {
  text-align: center;
  margin-bottom: 30px;
}

.about-header h1 {
  font-size: 3rem;
  color: #d84d22;
}

.about-header p {
  font-size: 1.5rem;
  margin-top: 10px;
  color: #555;
}

.about-section {
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-top: 20px;
}

.about-box {
  background-color: #fdf3e4;
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}

.about-box h2 {
  font-size: 2rem;
  margin-bottom: 10px;
  color: #d84d22;
}

.about-box p,
.about-box ul {
  font-size: 1.3rem;
  line-height: 1.8;
}

.about-box ul {
  list-style-type: disc;
  margin-left: 20px;
  color: #555;
}

.photo-gallery {
  margin-top: 40px;
}

.photo-gallery h2 {
  font-size: 2rem;
  text-align: center;
  margin-bottom: 20px;
  color: #d84d22;
}

.gallery {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
}

.photo-item {
  background-color:#fdf3e4;
  border-radius: 12px;
  padding: 10px;
  text-align: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.gallery-image {
  max-width: 100%;
  height: 150px;
  object-fit: cover;
  border-radius: 8px;
  margin-bottom: 10px;
}

.photo-item p {
  font-size: 1.2rem;
  color: #555;
}
</style>
