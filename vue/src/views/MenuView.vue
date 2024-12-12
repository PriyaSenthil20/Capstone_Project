<template>
  <div class="menu-view">
    <nav-options />
    <h1>Our Menu</h1>
    <div class="menu-gallery">
      <div v-for="item in menuItems" :key="item.id" class="menu-item" @click="navigateToOrder(item)">
        <img :src="item.image" :alt="item.name" class="menu-image" />
        <h2>{{ item.name }}</h2>
        <p>{{ item.description }}</p>
        <p><strong>Price:</strong> ${{ item.price }}</p>
      </div>
    </div>
    <h1>Photo Gallery</h1>
    <div class="photo-gallery">
      <div v-for="(photo, index) in photoGallery" :key="index" class="photo-item">
        <img :src="photo.image" :alt="photo.description" class="gallery-image" />
        <p>{{ photo.description }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import NavOptions from "../components/NavOptions.vue";

export default {
  components: {
    NavOptions,
  },
  data() {
    return {
      menuItems: [
        {
          id: 1,
          name: "Pepperoni Pizza",
          description: "A delicious classic topped with pepperoni and mozzarella cheese.",
          price: "12.99",
          image: new URL("../assets/WholePepperoni.jpg", import.meta.url).href,
        },
        {
          id: 2,
          name: "Cheese Pizza",
          description: "Rich, gooey, and cheesy – a pizza lover's dream.",
          price: "10.99",
          image: new URL("../assets/WholeCheese.jpg", import.meta.url).href,
        },
        {
          id: 3,
          name: "Apollo Special",
          description: "A supreme blend of toppings for a truly satisfying bite.",
          price: "15.99",
          image: new URL("../assets/Apollo.jpg", import.meta.url).href,
        },
        {
          id: 4,
          name: "Lei'Ed",
          description: "A masterful selection for a pizza king.",
          price: "15.99",
          image: new URL("../assets/Leied.jpg", import.meta.url).href,
        },
        {
          id: 5,
          name: "The Master",
          description: "Can you handle the Master?",
          price: "15.99",
          image: new URL("../assets/master.jpg", import.meta.url).href,
        },
      ],
      photoGallery: [
        {
          image: new URL("../assets/ModelA.jpg", import.meta.url).href,
          description: "Pizza ready to serve.",
        },
        {
          image: new URL("../assets/ModelB.jpg", import.meta.url).href,
          description: "Fresh ingredients on display.",
        },
        {
          image: new URL("../assets/ModelC.jpg", import.meta.url).href,
          description: "A snapshot of our pizza preparation process.",
        },
      ],
    };
  },
  methods: {
    navigateToOrder(item) {
      this.$router.push({ name: "customerOrder", query: { menuItem: JSON.stringify(item) } });
    },
  },
};
</script>

<style scoped>
.menu-view {
  text-align: center;
  padding: 20px;
}

.menu-gallery,
.photo-gallery {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 20px;
}

.menu-item,
.photo-item {
  width: 300px;
  padding: 20px;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  cursor: pointer;
  transition: transform 0.3s ease;
}

.menu-item:hover {
  transform: scale(1.05);
}

.menu-image,
.gallery-image {
  max-width: 100%;
  border-radius: 8px;
  margin-bottom: 10px;
}

.menu-item h2,
.photo-item h2 {
  font-size: 1.5rem;
  margin-bottom: 10px;
}

.menu-item p,
.photo-item p {
  font-size: 1rem;
  color: #555;
  margin-bottom: 10px;
}

/* Section Headers */
.menu-view h1 {
  font-size: 2rem;
  color: #d84d22;
  margin-bottom: 20px;
}
</style>
