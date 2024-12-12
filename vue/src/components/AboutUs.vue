<template>
  <div class="aboutUs-message">
    <div class="aboutText">
      <h1>About Us</h1>
      <p>
        Welcome to Rocco's Pizza, your ultimate pizza destination where every bite is an adventure!
        Born from a love of fresh ingredients, great company, and the irresistible charm of classic
        Italian recipes, we started Pizza Haven with one mission: to serve up the most flavorful,
        creative, and unforgettable pizzas in town. Whether you're a fan of the timeless Margherita
        or ready to try something bold like our BBQ Chicken Supreme, we promise every pizza is
        crafted with care and passion.
      </p>
      <p>
        Our story begins in 2016, when we decided it was time to break away from the ordinary and
        bring something special to the table. From the very first dough we kneaded by hand to the
        freshly-picked local veggies we use, we’ve been committed to delivering the highest quality
        pizza experience. Our secret? It’s simple. We never compromise on taste, always focusing on
        top-notch ingredients, perfectly balanced flavors, and that crispy, golden crust that makes
        each slice irresistible.
      </p>
      <p>
        But it’s not just about the pizza. At Rocco's Pizza, we believe that great food brings
        people together. Whether you’re here to celebrate a special occasion, have a casual meal
        with friends, or just grab a quick slice, we treat every customer like family. Our cozy
        ambiance, friendly service, and inviting atmosphere make it the perfect place to unwind and
        enjoy your meal.
      </p>
      <p>
        Looking for something exciting? Our specials change weekly, so there’s always something new
        to try! And if you can’t make it in, don’t worry—we’ve got you covered with fast, friendly
        delivery right to your door.
      </p>
      <p>
        Thank you for choosing Rocco's Pizza—where passion for pizza meets the love of great food,
        and every slice is an invitation to savor something truly amazing. We can’t wait to share
        our creations with you!
      </p>
    </div>
  </div>
</template>

<style scoped>
.aboutUs-message {
  font-family: sans-serif;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  padding: 2rem;
}

.aboutText {
  max-width: 800px;
  font-size: 1.1rem;
  line-height: 1.6;
  color: #333;
  text-align: justify;
}

.aboutText h1 {
  font-size: 2.5rem;
  margin-bottom: 1.5rem;
  color: #e63946;
}

.aboutText p {
  margin-bottom: 1rem;
}

.aboutText p::first-letter {
  font-size: 2rem;
  font-weight: bold;
  color: #e63946;
}

@media (max-width: 768px) {
  .aboutText {
    padding: 1rem;
    font-size: 1rem;
  }

  .aboutText h1 {
    font-size: 2rem;
  }
}
</style>
